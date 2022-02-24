package classes;

import java.io.*;


class Sliyanie
{
   

   public static void main(String[] args)
    {
      try(SequenceInputStream sis = new SequenceInputStream(new FileInputStream("D://java//example//src//classes//file.txt"), new FileInputStream("D://java//example//src//classes//copy_file.txt"));
          FileOutputStream fos = new FileOutputStream("D://java//example//src//classes//sliyanie.txt"))
       {
         int a;
         while((a = sis.read()) != -1)
          {
            fos.write(a);
          }
       }
      catch(Exception e)
       {
         
;         System.out.println(e.getMessage());
       }
    }

}
