package com.moonsworth.lunar.genesis;

import java.util.concurrent.Semaphore;
import com.google.common.base.Supplier;

final class Striped$4 implements Supplier<Semaphore> {
   Striped$4(int number1) {
      this.field1 = number1;
   }

   public Semaphore get() {
      return new Semaphore(this.field1, false);
   }
}
