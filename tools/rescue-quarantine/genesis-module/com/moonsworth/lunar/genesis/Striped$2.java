package com.moonsworth.lunar.genesis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.google.common.base.Supplier;

final class Striped$2 implements Supplier<Lock> {
   Striped$2() {
   }

   public Lock get() {
      return new ReentrantLock(false);
   }
}
