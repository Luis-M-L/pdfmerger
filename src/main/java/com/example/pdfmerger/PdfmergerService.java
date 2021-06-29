package com.example.pdfmerger;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;

@Service
public class PdfmergerService {
/*
    @Autowired
    PDFMergerUtility mergerUtility;
*/
    public byte[] getMerged(String bookname, String mainLanguage, String secondaryLanguage){
        File mainFile = getFile(bookname, mainLanguage);
        File secondaryFile = getFile(bookname, secondaryLanguage);

        //PDDocument merged = merge(mainFile, secondaryFile);
        return null;
    }

    public File getFile(String name, String language){
        return new File("D:\\PublicHalf\\Documents\\Proyectos\\Git\\pdfmerger\\" + name);
    }

    public PDDocument merge(File main, File secondary) throws FileNotFoundException {
        //mergerUtility.addSource(secondary);
        //mergerUtility.addSource(main);
        //mergerUtility.appendDocument();
        return null;
    }

}
