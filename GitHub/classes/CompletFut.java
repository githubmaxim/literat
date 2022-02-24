package classes;

import java.util.concurrent.CompletableFuture;
import java.io.*;
import java.util.stream.*;

public class CompletFut
{


   public static void main(String[] args) throws Exception
    {

// присоединение дополнительных действий состоящих !!из методов CompletableFuture!! 
//делается с помощью thenCompose()

      CompletableFuture<Integer> future = CompletableFuture
                 .supplyAsync(()->10)
                 .thenCompose(x->CompletableFuture.supplyAsync(()->x*2))
                 .thenCompose(x->CompletableFuture.supplyAsync(()->x*5));
      System.out.println(future.get());
    

// присоединение дополнительных действий состоящих не из методов CompletableFuture, а из элементарных 
//дествий (написанных тут-же) или же других (не CompletableFuture) методов делается с помощью thenApply()

      CompletableFuture<Integer> future1 = CompletableFuture
                 .supplyAsync(()->10)
                 .thenApply(x->x*2)
                 .thenApply(x->x*5);
      System.out.println(future1.get());


//получение результатов многих CompletableFuture в один CompletableFuture

      CompletableFuture<String> combined1 = CompletableFuture.supplyAsync(()->"I");
      CompletableFuture<String> combined2 = CompletableFuture.supplyAsync(()->"You");
      CompletableFuture<String> combined3 = CompletableFuture.supplyAsync(()->"He");

      //следующий код возвратит "null" т.к. Void. Т.е. метод allOf() для выполнения кода без возврата результата
      CompletableFuture<Void> combinedSum = CompletableFuture.allOf(combined1, combined2, combined3);
      System.out.println(combinedSum.get());

      //в случае необходимости возврата результата объединения нужно использовать Stream
      String comb = Stream.of(combined1, combined2, combined3)
                          .map(CompletableFuture::join)
                          .collect(Collectors.joining(" "));
      System.out.println(comb);


// обработка исключений общим методом handle()
      System.out.println("\nОбработка исключений:");
      CompletableFuture<Integer> result = CompletableFuture
                                           .supplyAsync(()->1000)
                                           .thenApply(x->1000-x)
                                           .thenApply(x->100/x)
                                           .handle((res,ex)->
                                                   {if(ex != null) {
                                                                    System.out.println(ex.getMessage());
                                                                     return -1;}
                                                    else {System.out.println(res);
                                                          return res;}
                                                   }                                                  
                                           );
   }
}