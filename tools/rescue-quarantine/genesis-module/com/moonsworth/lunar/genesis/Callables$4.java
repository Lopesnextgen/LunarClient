package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.Callables;
import com.google.common.base.Supplier;

final class Callables$4 implements Runnable {
   Callables$4(Supplier supplierextension1, Runnable runnable2) {
      this.field1 = supplierextension1;
      this.field2 = runnable2;
   }

   @Override
   public void run() {
      Thread thread1 = Thread.currentThread();
      String text2 = thread1.getName();
      boolean flag3 = Callables.access$000((String)this.field1.get(), thread1);

      try {
         this.field2.run();
      } finally {
         if (flag3) {
            boolean flag6 = Callables.access$000(text2, thread1);
         }
      }
   }
}
