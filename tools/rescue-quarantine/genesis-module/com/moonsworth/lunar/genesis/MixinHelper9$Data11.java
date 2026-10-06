package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import com.google.common.util.concurrent.CycleDetectingLockFactory;
import com.google.common.base.Preconditions;

final class MixinHelper9$Data11 extends ReentrantLock implements MixinHelper9$Extension {
   private final MixinHelper9$Data16 field1;

   private MixinHelper9$Data11(CycleDetectingLockFactory var1, MixinHelper9$Data16 var2, boolean var3) {
      super(var3);
      this.field2 = var1;
      this.field1 = Preconditions.checkNotNull(var2);
   }

   @Override
   public MixinHelper9$Data16 method1() {
      return this.field1;
   }

   @Override
   public boolean isAcquiredByCurrentThread() {
      return this.isHeldByCurrentThread();
   }

   @Override
   public void lock() {
      CycleDetectingLockFactory.method5(this.field2, this);

      try {
         super.lock();
      } finally {
         CycleDetectingLockFactory.method6(this);
      }
   }

   @Override
   public void lockInterruptibly() {
      CycleDetectingLockFactory.method5(this.field2, this);

      try {
         super.lockInterruptibly();
      } finally {
         CycleDetectingLockFactory.method6(this);
      }
   }

   @Override
   public boolean tryLock() {
      CycleDetectingLockFactory.method5(this.field2, this);

      try {
         return super.tryLock();
      } finally {
         CycleDetectingLockFactory.method6(this);
      }
   }

   @Override
   public boolean tryLock(long var1, TimeUnit var3) {
      CycleDetectingLockFactory.method5(this.field2, this);

      try {
         return super.tryLock(var1, var3);
      } finally {
         CycleDetectingLockFactory.method6(this);
      }
   }

   @Override
   public void unlock() {
      try {
         super.unlock();
      } finally {
         CycleDetectingLockFactory.method6(this);
      }
   }
}
