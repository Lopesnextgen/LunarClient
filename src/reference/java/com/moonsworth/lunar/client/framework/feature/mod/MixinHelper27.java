package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper27 extends MixinHelper2_3 {
   public MixinHelper27(MixinHelper var1) {
      super(var1);
   }

   public void method1(String var1, int var2, int var3, boolean var4, float var5) {
      if (!(var5 < 0.001)) {
         if (this.method1().field7) {
            this.method6().method17();
            float var6 = this.getStringWidth(var1) * var5;
            var2 = (int)((var2 - var6 / 2.0F + 1.0F) / var5);
            var3 = (int)((var3 - 5) / var5);
            int var7 = var2;
            int var8 = var3;
            Runnable var9 = () -> {
               this.method16().push();
               this.method16().method40(var5, var5);
               this.method16().method18(ThreadModuleDump63.method10(), var1, var7, var8, -1, var4);
               this.method16().pop();
            };
            if (ThreadModuleDump63.MC_VERSION <= 29 && ThreadModuleDump63.MC_VERSION >= 6) {
               ThreadModuleDump70 var10 = ThreadModuleDump70.of(
                  (int)(var2 * var5), (int)(var3 * var5), (int)Math.ceil(var6), (int)Math.ceil(ThreadModuleDump63.method10().method19() * var5)
               );
               this.method14().method2(var10, var9);
               this.method14().method1(var10, 0.0F);
            } else {
               var9.run();
            }
         }
      }
   }

   public void method2(String var1, int var2, int var3, boolean var4, float var5) {
      if (!(var5 < 0.001)) {
         if (this.method1().field7) {
            this.method6().method17();
            Runnable var6 = () -> {
               this.method16().push();
               this.method16().method40(var5, var5);
               this.method16().method19(ThreadModuleDump63.method10(), var1, var2 / var5, var3 / var5, -1, var4);
               this.method16().pop();
            };
            if (ThreadModuleDump63.MC_VERSION <= 29 && ThreadModuleDump63.MC_VERSION >= 6) {
               float var7 = this.getStringWidth(var1) * var5;
               ThreadModuleDump70 var8 = ThreadModuleDump70.of(
                  var2, var3, (int)Math.ceil(var7), (int)Math.ceil(ThreadModuleDump63.method10().method19() * var5)
               );
               this.method14().method2(var8, var6);
               this.method14().method1(var8, 0.0F);
            } else {
               var6.run();
            }
         }
      }
   }

   public int getStringWidth(String var1) {
      return (int)ThreadModuleDump63.method10().bridge$getStringWidth(var1);
   }

   public float method3(String var1, int var2, int var3) {
      float var4 = (float)var2 / this.getStringWidth(var1);
      float var5 = var3 / 10.0F;
      return Math.max(0.0F, Math.min(var4, var5));
   }
}
