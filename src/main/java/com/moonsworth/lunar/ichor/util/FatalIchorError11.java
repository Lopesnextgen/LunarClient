package com.moonsworth.lunar.ichor.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

public class FatalIchorError11 {
   private final ArrayList<Consumer<Throwable>> field1 = new ArrayList<>();
   private final HashSet<String> field2 = new HashSet<>();
   private final ArrayList<Throwable> field3 = new ArrayList<>();

   public synchronized void method1(Consumer<Throwable> var1) {
      this.field1.add(var1);
      this.field3.forEach(var1);
   }

   public synchronized void method2(Throwable var1) {
      String var2 = var1.getClass().getName() + " " + var1.getMessage();
      if (this.field2.add(var2)) {
         this.field3.add(var1);

         for (Consumer var4 : this.field1) {
            var4.accept(var1);
         }
      }
   }
}
