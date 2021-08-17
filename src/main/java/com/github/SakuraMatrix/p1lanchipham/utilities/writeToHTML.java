package com.github.SakuraMatrix.p1lanchipham.utilities;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class writeToHTML {
    public static void main(String[]args) throws IOException{
        File htmlTemplateFile = new File("src/main/resources/template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8.name());

        String title = "New Page";
        String body = "This is the body";
        htmlString = htmlString.replace("$title", title);
        htmlString = htmlString.replace("$body", body);
        File newHtmlFile = new File("C:\\Users\\LanChi\\Desktop\\budget.html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString, StandardCharsets.UTF_8.name());

//        ClassLoader class = Thread.currentThread().getContextClassLoader();
//        InputStream is = class.getResourceAsStream("/template.html");
//        File f = new File("C:\\Users\\LanChi\\Desktop\\budget.html");
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//            bw.write(String.valueOf(is));
//            bw.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
