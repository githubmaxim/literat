package classes;

import java.lang.Exception;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


public class CompletFut2
{

   public static void main(String[] args) throws Exception
    {

      List<Integer> arraylist = Arrays.asList(1,2,3,4,5,6);



//��������� � ���������� �������
      CompletableFuture cf1 = CompletableFuture.supplyAsync(()->sum(arraylist)).thenApply(sum->delenie(sum)).handle((res,ex)->
                                            { if(ex != null) {System.out.println("handle - " + ex.getMessage()); return -1;}
                                              else{return res;}});

      System.out.println("\n��������� ���������� �������: " + cf1.get());



//��������� � ������� ������� ������� ��� ������������
      CompletableFuture cf2 = CompletableFuture.supplyAsync(()->Chisla1.sum(arraylist)).thenApply(sum->Chisla2.delenie(sum)).handle((res,ex)->
                                            { if(ex != null) {System.out.println("handle - " + ex.getMessage()); return -1;}
                                              else{return res;}});

      System.out.println("\n��������� ������� ������� ������� ��� ������������: " + cf2.get());



/* ��������� � ������� ������� ������� � ������������� �� ����������  :((( !!!!!!


      CompletableFuture cf3 = CompletableFuture.supplyAsync(() -> {
                                                                   Chisla22 ch21 = new Chisla22(arraylist);
                                                                   ch21.sum();
                                                                  })
                                               .thenApply(sum ->  {
                                                                   Chisla22 ch22 = new Chisla22(sum);
                                                                   ch22.delenie();
                                                                 })
                                               .handle((res,ex)->
                                                 { if(ex != null) {System.out.println("handle - " + ex.getMessage()); return -1;}
                                                   else{return res;}});

      System.out.println("\n��������� ������� ������� ������� C ������������: " + cf3.get());
*/

    }



//!!! ���������� ������:

   public static Integer sum(List<Integer> arrayl)
    {

/*   // ��� ����� ��������� reduce() ��������� �������������� � Optional (�������������� NULL) � �� ������������� �������� ��� try-catch:

      Optional<Integer> summa = arrayl.stream().skip(1).limit(3).reduce((x, y) -> x*y);
      return summa.get();
*/

    //��� ����� ��������� reduce() ���������� �������� ���� ���� � try-catch (�.�. ��� �� Optional) � �������� ���������� ���������� summa. 
    //�������� �������� �� ������ "throws Exception" � ������� ��� try-catch-�� � ������ main + ������� � main .handle() - ��� ����� ��������:

       Integer summa = 0;
       try
        {
         summa = arrayl.stream().skip(1).limit(3).reduce(0, (x, y) -> x*y);
        }
       catch (Exception e){System.out.println("sum() - " + e.getMessage());}
   
       return summa;
    }
   

   // � ��� ���� ����� ��� ������� �� 0, ����� handle() � main ������������ ���������
   public static Integer delenie(Integer sum)
    {
      Integer del = 1000 / sum;
      return del;
    }

}





//!!! �����-�� ������ ������� ������ ������� ��� ������������

class Chisla1
{
   public static Integer sum(List<Integer> arrayl)
    {
      Optional<Integer> summa = arrayl.stream().skip(1).limit(3).reduce((x, y) -> x*y);
      return summa.get();
    }
}


class Chisla2
{
   public static Integer delenie(Integer sum)
    {
      Integer del = 1000 / sum;
      return del;
    }
}




/*
!!! �����-�� ������ ������� ������ ������� � �������������
 � ���� �� ���������� :(((  !!!!!

class Chisla21
{
   List<Integer> arrayl;

   Chisla21(List<Integer> arrayl)
    {
      this.arrayl = arrayl;
    }

   public Integer sum()
    {
      Optional<Integer> summa = arrayl.stream().skip(1).limit(3).reduce((x, y) -> x*y);
      return summa.get();
    }
}


class Chisla22
{
   Integer summ;

   Chisla22(Integer summ)
    {
      this.summ = summ;
    }

   public Integer delenie()
    {
      Integer del = 1000 / summ;
      return del;
    }
}

*/
