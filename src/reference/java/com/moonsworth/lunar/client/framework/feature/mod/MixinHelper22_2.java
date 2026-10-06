package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.client.util.ThreadModuleDump47.Data;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper22_2 extends MixinHelper2_3 {
   private Data offsets;

   public MixinHelper22_2(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void start() {
      this.offsets = new Data(0.0F);
   }

   public void method1(ThreadModuleDump70 var1, float var2) {
      this.method16().method44(var3 -> {
         float var4 = this.offsets.method2(var1);
         this.offsets.method1(var1, var4 + var2);
      });
   }

   public void method2(ThreadModuleDump70 var1, Runnable var2) {
      this.method16().method45(var1x -> var2.run());
      this.method16().method44(var3 -> {
         float var4 = this.offsets.method2(var1);
         var3.method38(0.0F, 0.0F, var4);
         var2.run();
         var3.method29().method5(var0 -> var0.method48());
         var3.method38(0.0F, 0.0F, -var4);
      });
   }
}
