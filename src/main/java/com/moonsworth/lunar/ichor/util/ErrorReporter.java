package com.moonsworth.lunar.ichor.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

public class ErrorReporter {
   private final ArrayList<Consumer<Throwable>> field1 = new ArrayList<>();
   private final HashSet<String> field2 = new HashSet<>();
   private final ArrayList<Throwable> field3 = new ArrayList<>();

   public ErrorReporter() {
   }

   public synchronized void method1(Consumer<Throwable> consumer1) {
      this.field1.add(consumer1);
      this.field3.forEach(consumer1);
   }

   public synchronized void method2(Throwable exception1) {
      String text2 = exception1.getClass().getName() + " " + exception1.getMessage();
      if (this.field2.add(text2)) {
         this.field3.add(exception1);

         for (Consumer consumer4 : this.field1) {
            consumer4.accept(exception1);
         }
      }
   }
}
