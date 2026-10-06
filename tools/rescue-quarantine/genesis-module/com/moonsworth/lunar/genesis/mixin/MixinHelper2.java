package com.moonsworth.lunar.genesis.mixin;

import com.google.common.annotations.GwtCompatible;
import com.moonsworth.lunar.genesis.MixinHelper16_2;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMap;
import java.util.List;

@GwtCompatible
final class MixinHelper2 {
   private static final MixinHelper16_2 field1 = MixinHelper16_2.method1("");

   static ImmutableMap<String, MixinHelperType> method1(CharSequence var0) {
      ImmutableMap.Data2 var1 = ImmutableMap.method7();
      int var2 = var0.length();
      int var3 = 0;

      while (var3 < var2) {
         var3 += method2(Lists.newLinkedList(), var0, var3, var1);
      }

      return var1.method7();
   }

   private static int method2(List<CharSequence> var0, CharSequence var1, int var2, ImmutableMap.Data2<String, MixinHelperType> var3) {
      int var4 = var1.length();
      int var5 = var2;
      char var6 = 0;

      while (var5 < var4) {
         var6 = var1.charAt(var5);
         if (var6 == '&' || var6 == '?' || var6 == '!' || var6 == ':' || var6 == ',') {
            break;
         }

         var5++;
      }

      var0.add(0, method3(var1.subSequence(var2, var5)));
      if (var6 == '!' || var6 == '?' || var6 == ':' || var6 == ',') {
         String var7 = field1.method9(var0);
         if (var7.length() > 0) {
            var3.method1(var7, MixinHelperType.fromCode(var6));
         }
      }

      var5++;
      if (var6 != '?' && var6 != ',') {
         while (var5 < var4) {
            var5 += method2(var0, var1, var5, var3);
            if (var1.charAt(var5) == '?' || var1.charAt(var5) == ',') {
               var5++;
               break;
            }
         }
      }

      var0.remove(0);
      return var5 - var2;
   }

   private static CharSequence method3(CharSequence var0) {
      return new StringBuilder(var0).reverse();
   }
}
