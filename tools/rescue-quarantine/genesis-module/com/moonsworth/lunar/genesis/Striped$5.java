package com.moonsworth.lunar.genesis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.google.common.base.Supplier;

final class Striped$5 implements Supplier<ReadWriteLock> {
   Striped$5() {
   }

   public ReadWriteLock get() {
      return new ReentrantReadWriteLock();
   }
}
