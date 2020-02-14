package edu.escuelaing.arep;

import java.net.*;
import java.util.Arrays;

import com.google.gson.Gson;

import java.io.*;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            while (true) {
                try {
                    System.out.println("Ready to receive ...");

                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String listreceived, listsize, outputLine;
                    listsize = in.readLine();
                    listreceived = in.readLine();
                    System.out.println("size: " + listsize + "list: " + listreceived);

                    int[] listtosortmerge = EchoServer.createListToSortMerge(listreceived, Integer.parseInt(listsize));

                    System.out.println("desordenada: " + Arrays.toString(listtosortmerge));
                    EchoServer.mergeSort(listtosortmerge, listtosortmerge.length);
                    System.out.println("ordenada: " + Arrays.toString(listtosortmerge));

                    outputLine = EchoServer.listtojson(listtosortmerge);
                    System.out.println(outputLine);
                    out.println(outputLine);
                    out.flush();

                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                } finally {
                    if (out != null)
                        out.close();
                    if (in != null)
                        in.close();
                    if (clientSocket != null)
                        clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        } finally {
            if (serverSocket != null)
                System.out.println("serversocket closed");
            serverSocket.close();
        }
    }

    public static String listtojson(int[] list) {
        String tmp;
        Gson gson = new Gson();
        tmp = gson.toJson(list);

        return tmp;
    }

    public static int[] createListToSortMerge(String list, int size) {
        int[] tmp = new int[size];
        int cont = 0;
        for (String str : list.split(" ")) {
            tmp[cont] = Integer.parseInt(str);
            cont++;
        }
        return tmp;
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

}
