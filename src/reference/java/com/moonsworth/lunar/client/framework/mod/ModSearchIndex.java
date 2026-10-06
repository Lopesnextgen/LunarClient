package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Nameplate9;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import it.unimi.dsi.fastutil.Pair;
import java.util.HashSet;
import java.util.List;

public interface ModSearchIndex {
   List<String> method1();

   List<String> method2();

   void method3(Framework7Extension var1);

   boolean method4(String var1);

   boolean method5(String var1);

   boolean method6(String var1);

   static ModSearchIndex method7() {
      return new Nameplate9();
   }

   static Pair<List<String>, List<String>> method8(Framework7Extension framework7) {
      HashSet var1 = new HashSet();
      HashSet var2 = new HashSet();
      ModDetails var3 = (ModDetails)framework7.method1(Framework.field13);
      if (var3 != null) {
         String var4 = var3.getName();
         String[] var5 = var4.split(" ");
         var1.add(ThreadModuleDump56.method4(var4));

         for (String var9 : var5) {
            var9 = ThreadModuleDump56.method4(var9);
            var1.add(var9);
         }

         if (var3.method2() != null) {
            for (String var19 : var3.method2()) {
               String[] var21 = var19.split(" ");

               for (String var12 : var21) {
                  var1.add(ThreadModuleDump56.method4(var12));
               }
            }
         }
      }

      var1.add(ThreadModuleDump56.method4(framework7.getId()));
      Framework5 var14 = (Framework5)framework7.method1(Framework.field14);
      if (var14 != null) {
         FeatureToggleKeybind var15 = (FeatureToggleKeybind)framework7.method1(Framework.field21);
         ModifierKeybindOption var18 = var15 == null ? null : var15.method9();

         for (ClientOption var22 : var14.method2()) {
            if (var22 != var18) {
               String[] var25 = var22.getName().split(" ");

               for (String var13 : var25) {
                  var2.add(ThreadModuleDump56.method4(var13));
               }
            }
         }
      }

      AlertExtension var16 = (AlertExtension)framework7.method1(Framework.field5);
      if (var16 != null) {
         var16.method2(var2x -> {
            ModSearchIndex var3x = (ModSearchIndex)var2x.method1(Framework.field9);
            if (var3x != null) {
               var3x.method3(var2x);
               var1.addAll(var3x.method1());
               var2.addAll(var3x.method2());
            }
         });
      }

      return Pair.of(List.copyOf(var1), List.copyOf(var2));
   }
}
