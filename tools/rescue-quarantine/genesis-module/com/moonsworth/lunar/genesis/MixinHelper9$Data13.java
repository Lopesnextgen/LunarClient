package com.moonsworth.lunar.genesis;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.CycleDetectingLockFactory;

final class MixinHelper9$Data13 extends ReentrantReadWriteLock implements MixinHelper9$Extension {
   private final MixinHelper9$Data12 field1;
   private final MixinHelper9$Data17 field2;
   private final MixinHelper9$Data16 field3;

   private MixinHelper9$Data13(CycleDetectingLockFactory var1, MixinHelper9$Data16 var2, boolean var3) {
      super(var3);
      this.field4 = var1;
      this.field1 = new MixinHelper9$Data12(var1, this);
      this.field2 = new MixinHelper9$Data17(var1, this);
      this.field3 = Preconditions.checkNotNull(var2);
   }

   @Override
   public ReadLock readLock() {
      return this.field1;
   }

   @Override
   public WriteLock writeLock() {
      return this.field2;
   }

   @Override
   public MixinHelper9$Data16 method1() {
      return this.field3;
   }

   @Override
   public boolean isAcquiredByCurrentThread() {
      return this.isWriteLockedByCurrentThread() || this.getReadHoldCount() > 0;
   }
}
