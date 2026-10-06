package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.google.common.util.concurrent.CycleDetectingLockFactory;

@Annotation2
public final class MixinHelper9$Data14<E extends Enum<E>> extends CycleDetectingLockFactory {
   private final Map<E, MixinHelper9$Data16> field5;

   @Annotation4
   MixinHelper9$Data14(MixinHelper9$Extension2 var1, Map<E, MixinHelper9$Data16> var2) {
      super(var1);
      this.field5 = var2;
   }

   public ReentrantLock newReentrantLock(E var1) {
      return this.newReentrantLock((E)var1, false);
   }

   public ReentrantLock newReentrantLock(E var1, boolean var2) {
      return this.field3 == MixinHelper9$Type.DISABLED
         ? new ReentrantLock(var2)
         : new MixinHelper9$Data11(this, this.field5.get(var1), var2);
   }

   public ReentrantReadWriteLock newReentrantReadWriteLock(E var1) {
      return this.newReentrantReadWriteLock((E)var1, false);
   }

   public ReentrantReadWriteLock newReentrantReadWriteLock(E var1, boolean var2) {
      return this.field3 == MixinHelper9$Type.DISABLED
         ? new ReentrantReadWriteLock(var2)
         : new MixinHelper9$Data13(this, this.field5.get(var1), var2);
   }
}
