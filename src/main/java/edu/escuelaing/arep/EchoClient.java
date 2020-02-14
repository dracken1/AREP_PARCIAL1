package edu.escuelaing.arep;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EchoClient {

    //heroku url: https://obscure-plains-93870.herokuapp.com/

    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            while (true) {
                
                try {
                    out = new PrintWriter(echoSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                    out.flush();
                    System.out.println("Ready to send ...");
                    String listtosend = EchoClient.createListToSend(out);
                    out.println(listtosend);
                    System.out.println("response: " + in.readLine());
                } catch (UnknownHostException e) {
                    System.err.println("Don’t know about host!.");
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
                    System.out.println(e);
                    System.exit(1);
                }
            }
        } finally {
            if (echoSocket != null) {
                System.out.println("echosocket closed");
                echoSocket.close();
            }            
            if (in != null) {
                System.out.println("in closed");
                in.close();
            }  
            if (out != null) {
                System.out.println("out closed");
                out.close();
            }   
        }

    }

    public static String createListToSend(PrintWriter out) {
        String listtosend = "";
        Scanner myScan = new Scanner(System.in);
        System.out.println("Enter the list size");
        int listsize = myScan.nextInt();
        out.println(listsize);
        System.out.println("Enter the list content numbers");
        for (int i = 1; i <= listsize; i++) {
            System.out.println("Enter the " + i + " number");
            if (i == 1) {
                listtosend += myScan.nextInt();
            } else {
                listtosend += " ";
                listtosend += myScan.nextInt();
            }
        }
        System.out.println("list sended: " + listtosend);
        return listtosend;
    }
}