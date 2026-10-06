package com.moonsworth.lunar.client.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ThreadModuleDump4<T> {
   private final ArrayDeque<ThreadModuleDump4.Data<T>> field1 = new ArrayDeque<>();

   public void method1(T var1, long var2) {
      this.field1.addLast(new ThreadModuleDump4.Data<>((T)var1, var2));
   }

   public Optional<ThreadModuleDump4.Data<T>> method2(long var1, int var3) {
      this.method3(var1, var3);
      return Optional.ofNullable(this.field1.peekFirst());
   }

   public void method3(long var1, int var3) {
      while (!this.field1.isEmpty() && var1 - this.field1.peekFirst().method1() > var3) {
         this.field1.pollFirst();
      }
   }

   public List<ThreadModuleDump4.Data<T>> method4(long var1, int var3) {
      ArrayList var4 = null;

      while (!this.field1.isEmpty() && var1 - this.field1.peekFirst().method1() > var3) {
         if (var4 == null) {
            var4 = new ArrayList();
         }

         var4.add(this.field1.pollFirst());
      }

      return var4 == null ? List.of() : var4;
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

      public Data(T var1, long var2) {
         this.field1 = (T)var1;
         this.tick = var2;
      }

      public T value() {
         return this.field1;
      }

      public long method1() {
         return this.tick;
      }
   }
}
