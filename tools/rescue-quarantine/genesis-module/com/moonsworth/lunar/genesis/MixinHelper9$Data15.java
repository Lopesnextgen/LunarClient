package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.CycleDetectingLockFactory;

class MixinHelper9$Data15 extends IllegalStateException {
   static final StackTraceElement[] field1 = new StackTraceElement[0];
   static final ImmutableSet<String> field2 = ImmutableSet.method4(
      CycleDetectingLockFactory.class.getName(), MixinHelper9$Data15.class.getName(), MixinHelper9$Data16.class.getName()
   );

   MixinHelper9$Data15(MixinHelper9$Data16 var1, MixinHelper9$Data16 var2) {
      super(var1.getLockName() + " -> " + var2.getLockName());
      StackTraceElement[] var3 = this.getStackTrace();
      int var4 = 0;

      for (int var5 = var3.length; var4 < var5; var4++) {
         if (MixinHelper9$Data14.class.getName().equals(var3[var4].getClassName())) {
            this.setStackTrace(field1);
            break;
         }

         if (!field2.contains(var3[var4].getClassName())) {
            this.setStackTrace(Arrays.copyOfRange(var3, var4, var5));
            break;
         }
      }
   }
}
