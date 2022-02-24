package classes;

import java.io.*;

class ArrayInFile
{

   public static void main(String[] args)
    {
      //byte[] array = new byte[]{1, 2, 3}; - тут чего-то в файл выводит каракули!!
      
      String text = "Hello";
      byte[] array = text.getBytes();

      try(ByteArrayInputStream bais = new ByteArrayInputStream(array);
          FileOutputStream fos = new FileOutputStream("D://java//example//src//classes//ArrInFile.txt"))
       {
         int a;
         while((a=bais.read()) != -1)
          {
            fos.write(a);
          }
       }
      catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
    }

}