package classes;

import java.io.*;

class CreateFile
{

   public static void main(String[] args)
    {
      try 
       {
         File f1 = new File("D://java//example//src//classes//file.txt");
         System.out.println(f1.length());

         File f2 = new File("D://java//example//src//classes");
         File[] m = f2.listFiles();
         for(File s: m)
          {System.out.println(s);}
       }
      catch(Exception e)
       {System.out.println(e.getMessage());}
      
    }

}