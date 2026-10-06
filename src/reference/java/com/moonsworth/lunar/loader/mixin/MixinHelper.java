package com.moonsworth.lunar.loader.mixin;

import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.AnnotationNode;

public class MixinHelper {
   public static boolean method1(AnnotationNode annotationNode, Config config) {
      List var2 = annotationNode.values;
      Config var3 = null;
      Config var4 = null;
      ArrayList var5 = null;
      boolean var6 = false;

      for (byte var7 = 0; var7 < var2.size(); var7 += 2) {
         String var8 = var2.get(var7).toString();
         Object var9 = var2.get(var7 + 1);
         if ("max".equals(var8)) {
            Integer var18 = (Integer)var9;
            if (var18 != -1) {
               var4 = Config.method2(var18).orElse(null);
            }
         } else if ("min".equals(var8)) {
            Integer var17 = (Integer)var9;
            if (var17 != -1) {
               var3 = Config.method2(var17).orElse(null);
            }
         } else if ("value".equals(var8) && var9 instanceof List var10) {
            var5 = new ArrayList(var2.size());

            for (Object var12 : var10) {
               if (var12 instanceof Integer var13) {
                  Config.method2(var13).ifPresent(var5::add);
               } else if (var12 instanceof String var14) {
                  Config.get(var14).ifPresent(var5::add);
               }
            }
         } else if ("inverted".equals(var8)) {
            var6 = (Boolean)var9;
         } else if ("onReturn".equals(var8) && (Boolean)var9) {
            return true;
         }
      }

      if (var5 != null) {
         return var6 != var5.contains(config);
      }

      if (var6) {
         boolean var16 = false;
         if (var3 != null) {
            var16 |= config.method5(var3);
         }

         if (var4 != null) {
            var16 |= config.method3(var4);
         }

         return var16;
      } else {
         boolean var15 = true;
         if (var3 != null) {
            var15 &= config.method4(var3);
         }

         if (var4 != null) {
            var15 &= var4.method4(config);
         }

         return var15;
      }
   }
}
