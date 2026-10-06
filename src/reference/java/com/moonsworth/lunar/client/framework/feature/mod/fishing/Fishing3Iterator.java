package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.Set;

final class Fishing3Iterator implements Fishing3_2 {
   @Override
   public void method1(Fishing5 var1) {
      for (Fishing_2 var3 : var1.method5().method1()) {
         this.method2(var1, var3);
      }
   }

   private void method2(Fishing5 var1, Fishing_2 var2) {
      if (var2.method7() != null) {
         String var3 = var2.getPrettyName();
         String var4 = var2.key();
         if (!var3.equals(var4)) {
            var1.method3(var3, var4);
            if (var1.method1().remove(var3)) {
               var1.method1().add(var4);
            }

            Set var5 = var1.method2().remove(var3);
            if (var5 != null) {
               var1.method2().put(var4, var5);

               for (String var7 : var5) {
                  var1.method3(var3 + ":" + var7, var4 + ":" + var7);
               }
            }
         }
      }

      if (var2.method3() && !var2.method4()) {
         for (Fishing_2 var9 : var2.method5()) {
            this.method2(var1, var9);
         }
      }
   }
}
