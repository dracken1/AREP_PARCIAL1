package edu.escuelaing.arep;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {

    public final static int SOCKET_PORT = 36000;
    public final static String FILE_TO_SEND = "src\\main\\java\\edu\\escuelaing\\arep\\resources\\descarga.jpg";
    /*public final static String FILE_TO_SEND = "src\\main\\java\\edu\\escuelaing\\arep\\resources\\results.html";*/
    public static void main (String [] args ) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;
        Socket sock = null;
        PrintWriter out = null;
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while (true) {
                System.out.println("Waiting...");
                try {
                    sock = servsock.accept();
                    System.out.println("Accepted connection : " + sock);
                    out = new PrintWriter(
                            sock.getOutputStream(), true);
                    String outputLine = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>\n"
                            + "<html>\n"
                            + "<head>\n"
                            + "<meta charset=\"UTF-8\">\n"
                            + "<title>browser</title>\n"
                            + "<script>"
                            + "alert(\"file received:"+ FILE_TO_SEND +"\");"
                            + "</script>"
                            + "</head>\n"
                            + "<body>\n"
                            + "</body>\n"
                            + "</html>\n";
                    System.out.println(outputLine);
                    out.println(outputLine);
                    // send file
                    File myFile = new File (FILE_TO_SEND);
                    byte [] mybytearray  = new byte [(int)myFile.length()];
                    fis = new FileInputStream(myFile);
                    bis = new BufferedInputStream(fis);
                    bis.read(mybytearray,0,mybytearray.length);
                    os = sock.getOutputStream();
                    System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
                    os.write(mybytearray,0,mybytearray.length);
                    os.flush();
                    System.out.println("Done.");
                }
                finally {
                    if (bis != null) bis.close();
                    if (os != null) os.close();
                    if (sock!=null){
                        sock.close();
                        out.close();
                    }
                }
            }
        }
        finally {
            if (servsock != null) servsock.close();
        }
    }
}