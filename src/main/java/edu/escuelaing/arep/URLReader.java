package edu.escuelaing.arep;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLReader {

    public void read(URL url){
        System.out.println("URL: "+url.toString());
        System.out.println("Protocol: "+url.getProtocol());
        System.out.println("Authority: "+url.getAuthority());
        System.out.println("Host: "+url.getHost());
        System.out.println("Port: "+url.getPort());
        System.out.println("path: "+url.getPath());
        System.out.println("Query: "+url.getQuery());
        System.out.println("File: "+url.getFile());
        System.out.println("Ref: "+url.getRef());
    }

    public  void readAndSaveURLPage(URL url) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))){
            String inputLine = null;
            File resultsfile = new File("C:\\Users\\nicolas.cardenas-c\\Downloads\\AREP_LAB_3\\src\\main\\java\\edu\\escuelaing\\arep\\resources\\results.html");
            FileWriter fw = new FileWriter(resultsfile);
            while((inputLine = reader.readLine()) != null){
                System.out.println(inputLine);
                fw.write(inputLine);
            }
            fw.close();
        } catch (IOException e){
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public static void main(String[] args) throws MalformedURLException {
        URLReader reader = new URLReader();
        URL url = new URL("https://www.google.es/");
        reader.read(url);
        System.out.println("//////////////");
        reader.readAndSaveURLPage(url);
    }
}