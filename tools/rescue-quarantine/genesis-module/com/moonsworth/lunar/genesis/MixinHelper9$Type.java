package com.moonsworth.lunar.genesis;

import java.util.logging.Level;
import com.google.common.util.concurrent.CycleDetectingLockFactory;

@Annotation2
public enum MixinHelper9$Type implements MixinHelper9$Extension2 {
   THROW {
      @Override
      public void handlePotentialDeadlock(MixinHelper9$Data10 var1) {
         throw var1;
      }
   },
   WARN {
      @Override
      public void handlePotentialDeadlock(MixinHelper9$Data10 var1) {
         CycleDetectingLockFactory.access$100().log(Level.SEVERE, "Detected potential deadlock", var1);
      }
   },
   DISABLED {
      @Override
      public void handlePotentialDeadlock(MixinHelper9$Data10 var1) {
      }
   };

   MixinHelper9$Type() {
   }
}
