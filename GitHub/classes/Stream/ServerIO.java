package classes.stream;

import java.io.*;
import java.net.*;


public class ServerIO
{

   public static void main(String[] args)
    {

      try(ServerSocket server = new ServerSocket(4004, 50); //создан соккет с номером порта как и у клиента и количеством подключений
          Socket clientSocket = server.accept())        //ждет соединение которое захочет подключиться к этому порту и подключает его
       {
         System.out.println("Server ready and listen!");
         
         try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))

             /* Создал объекты для приема и отправки сообщений - "in" и "out" .
                PrintWriter-ом обернуть BufferWriter, чтобы не заканчивать каждое сообщение "/n" и не писать 
                каждый раз метод flush() выталкивающий сообщения из буффера НЕВЫШЛО!!(не выталкивает сообщение в сторону сервака).
                С помощью методов getInput/OutputStream открываются входные и выходные потоки данных*/
          {
/*3.*/      String word = in.readLine();                //ждет пока клиент что-нибудь напишет
            System.out.println("Client send: " + word);
/*2.*/      out.write("This is Server, you send: " + word + "\n");             //сразу-же после получения сообщения от клиента отвечает ему
            out.flush();                                                       //выталкивает сообщение в сторону сервака
          }
       }
      catch(Exception e)
       {
         System.out.println(e);
       }
    }

}