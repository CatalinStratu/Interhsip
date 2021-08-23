package com.example.aplicationsmicroservice.resume;

import com.example.aplicationsmicroservice.ResponseWrapper;
import com.example.aplicationsmicroservice.ResumeParserProgram;
import gate.util.GateException;
import org.apache.tika.exception.TikaException;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResumeService {

    private final AmqpTemplate rabbitTemplate;
    private final ResumeParserProgram resumeParserProgram;

    @Value("${com.rabbitmq.exchange}")
    private String exchange;

    @Value("${com.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    public ResumeService(AmqpTemplate rabbitTemplate, ResumeParserProgram resumeParserProgram) {
        this.rabbitTemplate = rabbitTemplate;
        this.resumeParserProgram = resumeParserProgram;
    }

    /**
     * Uploade resume
     *
     * @param file        candidate event ID
     * @return HTTP status
     */
    public HttpStatus uploadResume(@NotNull MultipartFile file) throws IOException {
        String uploadedFolder = System.getProperty("user.dir");
        if (uploadedFolder != null && !uploadedFolder.isEmpty()) {
            uploadedFolder += "/aplications-microservice/Resumes/";
        } else
            throw new RuntimeException("User Directory not found");
        ResponseWrapper responseWrapper = null;
        File tikkaConvertedFile;
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        Path path;
        try {
            path = Paths.get(uploadedFolder + file.getOriginalFilename());
            if (!Files.exists(path.getParent()))
                Files.createDirectories(path.getParent());
            path = Files.write(path, bytes);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());

        }

        try {
            tikkaConvertedFile = resumeParserProgram.parseToHTMLUsingApacheTikka(path.toAbsolutePath().toString());
        } catch (IOException | SAXException | TikaException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        JSONObject parsedJSON;
        if (tikkaConvertedFile != null) {
            try {
                parsedJSON = resumeParserProgram.loadGateAndAnnie(tikkaConvertedFile);
            } catch (GateException | IOException exception) {
                throw new RuntimeException(exception.getMessage());
            }
            responseWrapper = new ResponseWrapper();
            responseWrapper.setStatus(200);
            responseWrapper.setData(parsedJSON);
            responseWrapper.setMessage("Successfully parsed Resume!");
        }

        if (this.send(responseWrapper).equals(HttpStatus.OK))
            return HttpStatus.OK;

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus send(ResponseWrapper responseWrapper) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingkey, responseWrapper);
        } catch (Exception ex){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}
