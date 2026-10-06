package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;

public class MixinHelper3 {
   public static boolean method1(MixinNameplateImpl var0, String var1) {
      ThreadModuleDump27 var2 = new ThreadModuleDump27(var1.startsWith("/") ? var1.substring(1) : var1);
      MixinHelper22 var3 = new MixinHelper22();
      return method2(var0, var2, var3);
   }

   private static boolean method2(MixinNameplate var0, ThreadModuleDump27 var1, MixinHelper22 var2) {
      int var3 = var1.getCursor();
      if (!var0.method3(var1, var2)) {
         var1.setCursor(var3);
         return false;
      }

      if (var1.method2() && var1.peek() == ' ') {
         int var4 = var1.getCursor();
         var1.skipWhitespace();
         int var5 = var1.getCursor();

         for (MixinNameplate var7 : var0.getChildren()) {
            if (method2(var7, var1, var2)) {
               return true;
            }

            var1.setCursor(var5);
         }

         var1.setCursor(var4);
      }

      if (!var1.method2() && var0.method4() != null) {
         var0.method4().execute(var2);
         return true;
      } else {
         return false;
      }
   }
}
