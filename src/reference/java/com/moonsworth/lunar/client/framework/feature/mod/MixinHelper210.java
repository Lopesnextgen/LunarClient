package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper210 extends MixinHelper2_3 {
   private boolean batchRegistered;
   private ThreadModuleDump70 dirtyRect;

   public MixinHelper210(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void end() {
      this.method17();
   }

   public void method1(int var1, int var2, int value, int value2, int value3) {
      if (!this.method3().method5(var1, var2, value, value2)) {
         if (this.method1().field7) {
            this.method16().method45(var5x -> var5x.method1(var1, var2, var1 + value, var2 + value2, value3));
            this.method16().method44(var6 -> {
               if (!this.batchRegistered) {
                  this.batchRegistered = true;
                  Nameplate2.method3(var6.method29());
               }

               Nameplate2.method2(var1, var2, value, value2, value3);
               if (ThreadModuleDump63.MC_VERSION > 5 && ThreadModuleDump63.MC_VERSION < 29) {
                  if (this.dirtyRect == null) {
                     this.dirtyRect = ThreadModuleDump70.of(var1, var2, value, value2);
                  } else {
                     this.dirtyRect = ThreadModuleDump70.union(this.dirtyRect, ThreadModuleDump70.of(var1, var2, value, value2));
                  }
               }
            });
         }
      }
   }

   public void method17() {
      if (this.batchRegistered) {
         this.batchRegistered = false;
         this.method16().method44(var1 -> {
            Runnable var2 = () -> Nameplate2.method4(var1.method29());
            if (ThreadModuleDump63.MC_VERSION > 5 && ThreadModuleDump63.MC_VERSION < 29) {
               this.method14().method2(this.dirtyRect, var2);
               this.method14().method1(this.dirtyRect, 0.0F);
               this.dirtyRect = null;
            } else {
               var2.run();
            }
         });
      }
   }
}
