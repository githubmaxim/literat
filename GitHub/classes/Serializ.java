package classes;

import java.io.*;
import classes.ForSerializ;

class Serializ
{

   public static void main(String[] args)
    {
      try(FileOutputStream fos = new FileOutputStream("Object.rez");
          ObjectOutputStream oos = new ObjectOutputStream(fos))
       {
         ForSerializ fs = new ForSerializ();
         oos.writeObject(fs);
       }
      catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
    }

}