package classes.stream;

import java.io.*;
import java.net.*;

public class ClientIO
{

   public static void main(String[] args)
    {
      try(Socket clientSocket = new Socket("localhost", 4004); //������������� � ������� � ������ "localhost" ������ �� ���������� � ������ 4004
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));                     //��������� ����� ��� ������ � ����������
          BufferedReader in     = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //��������� ����� ��� ������ ��������� � �������
          BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) //��������� ����� ��� �������� ��������� �� ������
       {
         System.out.println("������� ���� ���������:");//
/*1.*/   String word = reader.readLine(); //���� ���� ������, ���-������ �� �������
/*2.*/   out.write(word + "\n"); //���������� ��������� �� ������
         out.flush(); //����������� ��������� � ������� �������
         
/*3.*/   String serverWord = in.readLine(); //���� � �������� ��������� � �������
         System.out.println(serverWord); //�������, ������� �� �����
       }
      catch(Exception e)
       {
         System.out.println(e);
       }
    }

}