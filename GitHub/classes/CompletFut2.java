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



//Обращение к внутренним методам
      CompletableFuture cf1 = CompletableFuture.supplyAsync(()->sum(arraylist)).thenApply(sum->delenie(sum)).handle((res,ex)->
                                            { if(ex != null) {System.out.println("handle - " + ex.getMessage()); return -1;}
                                              else{return res;}});

      System.out.println("\nРезультат внутренних методов: " + cf1.get());



//Обращение к Внешним методам классов без конструктора
      CompletableFuture cf2 = CompletableFuture.supplyAsync(()->Chisla1.sum(arraylist)).thenApply(sum->Chisla2.delenie(sum)).handle((res,ex)->
                                            { if(ex != null) {System.out.println("handle - " + ex.getMessage()); return -1;}
                                              else{return res;}});

      System.out.println("\nРезультат внешних методов классов БЕЗ конструктора: " + cf2.get());



/* Обращение к Внешним методам классов с конструктором не получается  :((( !!!!!!


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

      System.out.println("\nРезультат внешних методов классов C конструктора: " + cf3.get());
*/

    }



//!!! Внутренние методы:

   public static Integer sum(List<Integer> arrayl)
    {

/*   // При таком написании reduce() результат заворачивается в Optional (переваривающий NULL) и не необходимости окружать код try-catch:

      Optional<Integer> summa = arrayl.stream().skip(1).limit(3).reduce((x, y) -> x*y);
      return summa.get();
*/

    //При таком написании reduce() необходимо обернуть этот блок в try-catch (т.к. это не Optional) и зараннее определить переменную summa. 
    //Пробовал написать на методе "throws Exception" и поймать его try-catch-ем в методе main + добавил в main .handle() - все равно ругалось:

       Integer summa = 0;
       try
        {
         summa = arrayl.stream().skip(1).limit(3).reduce(0, (x, y) -> x*y);
        }
       catch (Exception e){System.out.println("sum() - " + e.getMessage());}
   
       return summa;
    }
   

   // А вот этот метод при делении на 0, метод handle() в main отрабатывает нормально
   public static Integer delenie(Integer sum)
    {
      Integer del = 1000 / sum;
      return del;
    }

}





//!!! Такие-же только Внешние методы классов БЕЗ конструктора

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
!!! Такие-же только Внешние методы классов С конструктором
 С ними не получается :(((  !!!!!

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
