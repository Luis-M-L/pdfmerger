package com.example.pdfmerger;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.tools.PDFMerger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class PdfmergerService {

    public byte[] getMerged(String bookname, String mainLanguage, String secondaryLanguage) throws IOException {
        PDDocument mainPDF = getPDF(bookname, mainLanguage);
        PDDocument secondaryPDF = getPDF(bookname, secondaryLanguage);

        PDDocument merged = merge(mainPDF, secondaryPDF);
        return null;
    }

    public PDDocument getPDF(String name, String language) throws IOException {
        String path = String.format("D:\\PublicHalf\\Documents\\Proyectos\\Git\\pdfmerger\\%s %s.pdf", name, language);
        File file = new File(path);
        PDDocument document = PDDocument.load(file);
        return document;
    }

    public PDDocument merge(PDDocument main, PDDocument secondary) throws FileNotFoundException {
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

}
