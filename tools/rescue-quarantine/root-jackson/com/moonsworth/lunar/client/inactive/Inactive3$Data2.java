package com.moonsworth.lunar.client.inactive;

import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper32;
import com.moonsworth.lunar.MixinHelper3222;
import com.moonsworth.lunar.MixinHelper3223;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import software.bernie.geckolib3.core.builder.AnimationBuilder;

class Inactive3$Data2 extends MixinHelper102_4<AnimationBuilder> {
   private Inactive3$Data2() {
   }

   public AnimationBuilder method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      MixinHelper9_9 var3 = var1.method98();
      if (var3 instanceof MixinHelper3234 var10) {
         return new AnimationBuilder().addAnimation(var10.method39(), true);
      } else {
         AnimationBuilder var4 = new AnimationBuilder();
         if (var3 instanceof MixinHelper3223) {
            for (MixinHelper32 var8 : (MixinHelper3223)var3) {
               if (var8 instanceof MixinHelper3222 var9) {
                  this.method2(var4, var9);
               }
            }
         } else if (var3 instanceof MixinHelper3222 var6) {
            this.method2(var4, var6);
         }

         return var4;
      }
   }

   private void method2(AnimationBuilder var1, MixinHelper3222 var2) {
      if (var2.has("name") && var2.method8("name").method27()) {
         String var3 = var2.method8("name").method34();
         Boolean var4 = null;
         if (var2.has("loop") && var2.method8("loop").method28()) {
            var4 = var2.method8("loop").method35();
         }

         var1.addAnimation(var3, var4);
      }
   }
}
