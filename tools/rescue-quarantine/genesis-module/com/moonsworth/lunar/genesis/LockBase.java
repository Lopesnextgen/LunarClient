package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

abstract class LockBase implements Lock {
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
   public boolean tryLock(long var1, TimeUnit var3) {
      return this.delegate().tryLock(var1, var3);
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
