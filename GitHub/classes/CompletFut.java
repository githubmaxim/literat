package classes;

import java.util.concurrent.CompletableFuture;
import java.io.*;
import java.util.stream.*;

public class CompletFut
{


   public static void main(String[] args) throws Exception
    {

// ������������� �������������� �������� ��������� !!�� ������� CompletableFuture!! 
//�������� � ������� thenCompose()

      CompletableFuture<Integer> future = CompletableFuture
                 .supplyAsync(()->10)
                 .thenCompose(x->CompletableFuture.supplyAsync(()->x*2))
                 .thenCompose(x->CompletableFuture.supplyAsync(()->x*5));
      System.out.println(future.get());
    

// ������������� �������������� �������� ��������� �� �� ������� CompletableFuture, � �� ������������ 
//������� (���������� ���-��) ��� �� ������ (�� CompletableFuture) ������� �������� � ������� thenApply()

      CompletableFuture<Integer> future1 = CompletableFuture
                 .supplyAsync(()->10)
                 .thenApply(x->x*2)
                 .thenApply(x->x*5);
      System.out.println(future1.get());


//��������� ����������� ������ CompletableFuture � ���� CompletableFuture

      CompletableFuture<String> combined1 = CompletableFuture.supplyAsync(()->"I");
      CompletableFuture<String> combined2 = CompletableFuture.supplyAsync(()->"You");
      CompletableFuture<String> combined3 = CompletableFuture.supplyAsync(()->"He");

      //��������� ��� ��������� "null" �.�. Void. �.�. ����� allOf() ��� ���������� ���� ��� �������� ����������
      CompletableFuture<Void> combinedSum = CompletableFuture.allOf(combined1, combined2, combined3);
      System.out.println(combinedSum.get());

      //� ������ ������������� �������� ���������� ����������� ����� ������������ Stream
      String comb = Stream.of(combined1, combined2, combined3)
                          .map(CompletableFuture::join)
                          .collect(Collectors.joining(" "));
      System.out.println(comb);


// ��������� ���������� ����� ������� handle()
      System.out.println("\n��������� ����������:");
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