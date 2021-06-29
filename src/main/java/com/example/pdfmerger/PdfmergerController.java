package com.example.pdfmerger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfmergerController {

    @Autowired
    PdfmergerService service;

    @GetMapping(value = "/")
    public void testStatus(){
        System.out.println("UP");
    }

    @GetMapping(value = "/merged/{bookname}/{mainLanguage}/{secondaryLanguage}")
    public ResponseEntity<byte[]> getMerged(@PathVariable String bookname, @PathVariable String mainLanguage, @PathVariable String secondaryLanguage){
        byte[] contents = service.getMerged(bookname, mainLanguage, secondaryLanguage);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
}
