package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.MixinNameplate;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.command.MixinNameplateImpl;
import com.moonsworth.lunar.client.command.MixinNameplateIterator;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandRegisterLegacy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class Nameplate_2 {
   public static List<String> method1(String var0) {
      return var0 != null && var0.startsWith("/") && var0.indexOf(32) >= 0 ? method3(var0) : Collections.emptyList();
   }

   public static String[] method2(String[] var0, String var1) {
      if (var1 != null && var1.startsWith("/")) {
         LinkedHashSet var2 = new LinkedHashSet(var0.length);
         var2.addAll(Arrays.asList(var0));
         return var2.addAll(method3(var1)) ? var2.toArray(new String[0]) : var0;
      } else {
         return var0;
      }
   }

   private static List<String> method3(String var0) {
      ArrayList var1 = new ArrayList();
      ClientEventBus.method29().method12(EventCommandRegisterLegacy.class, () -> new EventCommandRegisterLegacy(var1));
      ArrayList var2 = new ArrayList();

      for (MixinNameplate2 var4 : var1) {
         if (var4.isEnabled()) {
            var2.addAll(var4.method3(var0));
         }
      }

      return var2;
   }

   public static List<String> method4(MixinNameplateImpl var0, String var1) {
      boolean var2 = var1.startsWith("/");
      String var3 = var2 ? var1.substring(1) : var1;
      String var4 = var0.method5();
      int var5 = var3.indexOf(32);
      if (var5 < 0) {
         return var4.startsWith(var3) && !var4.equals(var3) ? List.of(var2 ? "/" + var4 : var4) : Collections.emptyList();
      }

      if (!var3.substring(0, var5).equals(var4)) {
         return Collections.emptyList();
      }

      String var6 = var3.substring(var5 + 1);
      return method5(var0, var6);
   }

   private static List<String> method5(MixinNameplate var0, String var1) {
      int var2 = var1.indexOf(32);
      if (var2 < 0) {
         ArrayList var10 = new ArrayList();

         for (MixinNameplate var12 : var0.getChildren()) {
            if (var12 instanceof MixinNameplateImpl var13) {
               if (var13.method5().startsWith(var1)) {
                  var10.add(var13.method5());
               }
            } else if (var12 instanceof MixinNameplateIterator var15) {
               Nameplate2_2 var16 = var15.method3();
               if (var16 != null) {
                  Nameplate3Handler var17 = new Nameplate3Handler(var1);
                  var16.provide(NameplateImpl.field1, var17);
                  var10.addAll(var17.method4());
               }
            }
         }

         return var10;
      } else {
         String var3 = var1.substring(0, var2);
         String var4 = var1.substring(var2 + 1);

         for (MixinNameplate var6 : var0.getChildren()) {
            if (var6 instanceof MixinNameplateImpl var7) {
               if (var7.method5().equals(var3)) {
                  return method5(var6, var4);
               }
            } else if (var6 instanceof MixinNameplateIterator var14) {
               if (var14.method10().method2()) {
                  Nameplate2_2 var8 = var14.method3();
                  if (var8 == null) {
                     return Collections.emptyList();
                  }

                  Nameplate3Handler var9 = new Nameplate3Handler(var1);
                  var8.provide(NameplateImpl.field1, var9);
                  return new ArrayList<>(var9.method4());
               }

               return method5(var6, var4);
            }
         }

         return Collections.emptyList();
      }
   }
}
