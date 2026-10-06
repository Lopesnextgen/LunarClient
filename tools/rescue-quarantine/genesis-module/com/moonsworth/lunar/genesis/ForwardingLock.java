package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

abstract class ForwardingLock implements Lock {
   ForwardingLock() {
   }

   abstract Lock delegate();

   @Override
   public void lock() {
      this.delegate().lock();
   }

   @Override
   public void lockInterruptibly() {
      this.delegate().lockInterruptibly();
   }

   @Override
   public boolean tryLock() {
      return this.delegate().tryLock();
   }

   @Override
   public boolean tryLock(long number1, TimeUnit timeunit3) {
      return this.delegate().tryLock(number1, timeunit3);
   }

   @Override
   public void unlock() {
      this.delegate().unlock();
   }

   @Override
   public Condition newCondition() {
      return this.delegate().newCondition();
   }
}
