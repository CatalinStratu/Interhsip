package com.example.aplicationsmicroservice.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService){
        this.resumeService = resumeService;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> fileUpload(@RequestParam("file") MultipartFile file){

        HttpStatus responseStatus;
        try {
            responseStatus = resumeService.uploadResume(file);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseStatus = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.ok(responseStatus);
    }

}