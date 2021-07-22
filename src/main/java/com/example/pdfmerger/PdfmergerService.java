package com.example.pdfmerger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class PdfmergerService {

    @Value("${bookrepo.base.path}")
    public String BASE_PATH;

    public byte[] getMerged(String mainBookName, String secondaryBookName) throws IOException {
        PDDocument mainPDF = getPDF(mainBookName);
        PDDocument secondaryPDF = getPDF(secondaryBookName);

        PDDocument merged = merge(mainPDF, secondaryPDF);
        return convertIntoByteArray(merged);
    }

    public PDDocument getPDF(String name) throws IOException {
        String path = String.format(BASE_PATH + "%s.pdf", name);
        File file = new File(path);
        PDDocument document = PDDocument.load(file);
        return document;
    }

    public PDDocument merge(PDDocument main, PDDocument secondary) {
        PDDocument merged = new PDDocument();

        int mainNumOfPages = main.getNumberOfPages();
        int secondaryNumOfPages = secondary.getNumberOfPages();
        int mergedNumOfPages = mainNumOfPages + secondaryNumOfPages;

        for(int i = 0; merged.getNumberOfPages() < mergedNumOfPages; i++){
            if(i <= mainNumOfPages -1 && null != main.getPage(i)){
                merged.addPage(main.getPage(i));
            }
            if(i <= secondaryNumOfPages -1 && null != secondary.getPage(i)){
                merged.addPage(secondary.getPage(i));
            }
        }
        return merged;
    }

    private byte[] convertIntoByteArray(PDDocument merged) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        merged.save(stream);
        return stream.toByteArray();
    }

}
