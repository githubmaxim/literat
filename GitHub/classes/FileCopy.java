package classes;

import java.io.*;

class FileCopy
{

   public static void main(String[] args)
    {
      try(FileInputStream fis = new FileInputStream("D://java//example//src//classes//file.txt");
          FileOutputStream fos = new FileOutputStream("D://java//example//src//classes//copy_file.txt"))
       {
         int a;
         while((a = fis.read()) != -1)
          {
            fos.write(a);
          }
       }
      catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
      System.out.println("Hello");
    }

}
