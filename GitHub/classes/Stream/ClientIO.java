package classes.stream;

import java.io.*;
import java.net.*;

public class ClientIO
{

   public static void main(String[] args)
    {
      try(Socket clientSocket = new Socket("localhost", 4004); //запрашивается у сервера с именем "localhost" доступ на соединение с портом 4004
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));                     //создается поток для чтения с клавиатуры
          BufferedReader in     = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //создается поток для чтения сообщений с сервера
          BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) //создается поток для отправки сообщений на сервер
       {
         System.out.println("Введите ваше сообщение:");//
/*1.*/   String word = reader.readLine(); //ждем пока клиент, что-нибудь не напишет
/*2.*/   out.write(word + "\n"); //отправляем сообщение на сервер
         out.flush(); //выталкивает сообщение в сторону сервака
         
/*3.*/   String serverWord = in.readLine(); //ждем и получаем сообщение с сервера
         System.out.println(serverWord); //получив, выводим на экран
       }
      catch(Exception e)
       {
         System.out.println(e);
       }
    }

}