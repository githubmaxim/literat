package classes.stream;

import java.io.*;
import java.net.*;


public class ServerIO
{

   public static void main(String[] args)
    {

      try(ServerSocket server = new ServerSocket(4004, 50); //������ ������ � ������� ����� ��� � � ������� � ����������� �����������
          Socket clientSocket = server.accept())        //���� ���������� ������� ������� ������������ � ����� ����� � ���������� ���
       {
         System.out.println("Server ready and listen!");
         
         try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))

             /* ������ ������� ��� ������ � �������� ��������� - "in" � "out" .
                PrintWriter-�� �������� BufferWriter, ����� �� ����������� ������ ��������� "/n" � �� ������ 
                ������ ��� ����� flush() ������������� ��������� �� ������� �������!!(�� ����������� ��������� � ������� �������).
                � ������� ������� getInput/OutputStream ����������� ������� � �������� ������ ������*/
          {
/*3.*/      String word = in.readLine();                //���� ���� ������ ���-������ �������
            System.out.println("Client send: " + word);
/*2.*/      out.write("This is Server, you send: " + word + "\n");             //�����-�� ����� ��������� ��������� �� ������� �������� ���
            out.flush();                                                       //����������� ��������� � ������� �������
          }
       }
      catch(Exception e)
       {
         System.out.println(e);
       }
    }

}