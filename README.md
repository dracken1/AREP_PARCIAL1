[![CircleCI](https://circleci.com/gh/circleci/AREP_LAB1.svg?style=svg)](https://circleci.com/gh/dracken1/AREP_LAB2/tree/master)

# Laboratorio 3 AREP
  Este repo fue desarrollado como laboratorio de la clase AREP de la Escuela Colombiana de Ingenieria Julio Garavito.

- Se resolvieron los siguientes ejercicios y retos:
````
EJERCICIO 1
Escriba un programa en el cual usted cree un objeto URL e imprima en pantalla cada uno de los componentes de una URL. Es decir , debe usar los siguientes métodos: getProtocol, getAuthority, getHost, getPort, getPath, getQuery, getFile, getRef. Asegúrese que ninguno imprima una cadena vacía, esto implica que la URL que use para su objeto debe contener datos suficientes.

EJERCICIO 2
Escriba una aplicación browser que dada una URL lea datos de esa dirección y que los almacene en un archivo con el nombre resultado.html. Intente ver este archivo en el navegador. Su implementación debe ser un programa que reciba el parámetro de URL por medio de la línea de comandos.

EJERCICIO 3
Usando sockets escriba un servidor que reciba un número y responda el cuadrado de este número. Escriba un cliente para probarlo y envíele una secuencia de 20 números.

RETO 1
Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo páginas html e imágenes. Construya un sitio web con javascript para probar su servidor. Despliegue su solución en Heroku.

````

# Example
  ```
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
  ```
    
  
# Getting Started
## Install with

```
git clone https://github.com/dracken1/CalcuComplejosCNYT.git
```

## Run with

Dentro de la carpeta AREP_LAB1, inice cmd e intrduzca los siguientes comandos:

```
mvn clean

mvn compile

mvn test
```

o tabmien:

```
mvn package
```

# Build with
El proyecto se desarrollo usando el lenguaje Java y la herramienta Intellij.

# Author
Nicolás Cárdenas Chaparro

# License
````
MIT License

Copyright (c) 2020 dracken1

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
````

# Acknowlegdments
- Practicing java
- Inspiration
