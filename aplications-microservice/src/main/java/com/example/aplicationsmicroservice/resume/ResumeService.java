package com.example.aplicationsmicroservice.resume;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ResumeService {

    /**
     * Uploade resume
     *
     * @param file        candidate event ID
     * @return HTTP status
     */
    public HttpStatus uploadResume(@NotNull MultipartFile file) throws IOException {

        String uploadedFolder = System.getProperty("user.dir");
        if (uploadedFolder != null && !uploadedFolder.isEmpty()) {
            uploadedFolder += "/resumes/";
        }

        File convertFile = new File(uploadedFolder+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        return HttpStatus.OK;
    }
}
