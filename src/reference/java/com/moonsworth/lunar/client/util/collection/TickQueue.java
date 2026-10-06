package com.moonsworth.lunar.client.util.collection;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TickQueue<T> {
   private final ArrayDeque<TickQueue.Data<T>> field1 = new ArrayDeque<>();

   public TickQueue() {
   }

   public void method1(T value1, long number2) {
      this.field1.addLast(new TickQueue.Data<>((T)value1, number2));
   }

   public Optional<TickQueue.Data<T>> method2(long number1, int number3) {
      this.method3(number1, number3);
      return Optional.ofNullable(this.field1.peekFirst());
   }

   public void method3(long number1, int number3) {
      while (!this.field1.isEmpty() && number1 - this.field1.peekFirst().method1() > number3) {
         this.field1.pollFirst();
      }
   }

   public List<TickQueue.Data<T>> method4(long number1, int number3) {
      ArrayList list4 = null;

      while (!this.field1.isEmpty() && number1 - this.field1.peekFirst().method1() > number3) {
         if (list4 == null) {
            list4 = new ArrayList();
         }

         list4.add(this.field1.pollFirst());
      }

      return list4 == null ? List.of() : list4;
   }

   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   public void clear() {
      this.field1.clear();
   }

   public class Data<T> {
      private final T field1;
      private final long tick;

      public Data(T value1, long number2) {
         this.field1 = (T)value1;
         this.tick = number2;
      }

      public T value() {
         return this.field1;
      }

      public long method1() {
         return this.tick;
      }
   }
}
