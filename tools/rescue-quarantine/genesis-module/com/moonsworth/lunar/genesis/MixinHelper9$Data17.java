package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import com.google.common.util.concurrent.CycleDetectingLockFactory;

class MixinHelper9$Data17 extends WriteLock {
   @Weak
   final MixinHelper9$Data13 field1;

   MixinHelper9$Data17(CycleDetectingLockFactory var1, MixinHelper9$Data13 var2) {
      super(var2);
      this.field2 = var1;
      this.field1 = var2;
   }

   @Override
   public void lock() {
      CycleDetectingLockFactory.method5(this.field2, this.field1);

      try {
         super.lock();
      } finally {
         CycleDetectingLockFactory.method6(this.field1);
      }
   }

   @Override
   public void lockInterruptibly() {
      CycleDetectingLockFactory.method5(this.field2, this.field1);

      try {
         super.lockInterruptibly();
      } finally {
         CycleDetectingLockFactory.method6(this.field1);
      }
   }

   @Override
   public boolean tryLock() {
      CycleDetectingLockFactory.method5(this.field2, this.field1);

      try {
         return super.tryLock();
      } finally {
         CycleDetectingLockFactory.method6(this.field1);
      }
   }

   @Override
   public boolean tryLock(long var1, TimeUnit var3) {
      CycleDetectingLockFactory.method5(this.field2, this.field1);

      try {
         return super.tryLock(var1, var3);
      } finally {
         CycleDetectingLockFactory.method6(this.field1);
      }
   }

   @Override
   public void unlock() {
      try {
         super.unlock();
      } finally {
         CycleDetectingLockFactory.method6(this.field1);
      }
   }
}
