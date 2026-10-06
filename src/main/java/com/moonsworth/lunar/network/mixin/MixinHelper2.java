package com.moonsworth.lunar.network.mixin;

import java.util.Collection;
import java.util.Iterator;

public class MixinHelper2 {
   public static boolean method1(String[] var0, String var1) {
      for (String var5 : var0) {
         if (var1 == null && var5 == null) {
            return true;
         }

         if (var1 != null && var1.equalsIgnoreCase(var5)) {
            return true;
         }
      }

      return false;
   }

   public static String join(String[] var0, String var1) {
      int var2 = var0.length;
      if (var2 == 0) {
         return "";
      }

      StringBuilder var3 = new StringBuilder();
      var3.append(var0[0]);

      for (int var4 = 1; var4 < var2; var4++) {
         var3.append(var1).append(var0[var4]);
      }

      return var3.toString();
   }

   public static String method2(Collection<String> var0, String var1) {
      Iterator var2 = var0.iterator();
      StringBuilder var3 = new StringBuilder();
      if (var2.hasNext()) {
         var3.append((String)var2.next());
      }

      while (var2.hasNext()) {
         var3.append(var1).append((String)var2.next());
      }

      return var3.toString();
   }
}
