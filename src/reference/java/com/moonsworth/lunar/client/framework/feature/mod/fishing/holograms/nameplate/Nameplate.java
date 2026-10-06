package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.kyori.adventure.text.Component;

public class Nameplate {
   public static void method1(MixinHelper_4 var0, String var1, float var2, float var3, float var4, Nameplate.Type var5) {
      method3(var0, var1, var2, var3, var4, var5, -1);
   }

   public static void method2(MixinHelper_4 var0, Component var1, float var2, float var3, float var4, Nameplate.Type var5) {
      var0.push();
      var0.scale(var4, var4, 1.0F);
      if (var5 == Nameplate.Type.BORDER) {
         String var6 = AdventureTextBridge.getTextContent(var1);
         var0.method19(ThreadModuleDump63.method10(), var6, var2 / var4 - 1.0F, var3 / var4, -16777216, true);
         var0.method19(ThreadModuleDump63.method10(), var6, var2 / var4 + 1.0F, var3 / var4, -16777216, true);
         var0.method19(ThreadModuleDump63.method10(), var6, var2 / var4, var3 / var4 - 1.0F, -16777216, true);
         var0.method19(ThreadModuleDump63.method10(), var6, var2 / var4, var3 / var4 + 1.0F, -16777216, true);
      }

      var0.method11(ThreadModuleDump63.method10(), var1, var2 / var4, var3 / var4, -1, true);
      var0.pop();
      var0.method44(var0x -> var0x.method29().method33());
   }

   private static void method3(MixinHelper_4 var0, String var1, float var2, float var3, float var4, Nameplate.Type var5, int var6) {
      var0.push();
      var0.scale(var4, var4, 1.0F);
      if (var5 == Nameplate.Type.BORDER) {
         String var7 = AdventureChatFormatting.getTextWithoutFormattingCodes(var1);
         var0.method19(ThreadModuleDump63.method10(), var7, var2 / var4 - 1.0F, var3 / var4, -16777216, true);
         var0.method19(ThreadModuleDump63.method10(), var7, var2 / var4 + 1.0F, var3 / var4, -16777216, true);
         var0.method19(ThreadModuleDump63.method10(), var7, var2 / var4, var3 / var4 - 1.0F, -16777216, true);
         var0.method19(ThreadModuleDump63.method10(), var7, var2 / var4, var3 / var4 + 1.0F, -16777216, true);
      }

      var0.method19(ThreadModuleDump63.method10(), var1, var2 / var4, var3 / var4, var6, true);
      var0.pop();
      var0.method44(var0x -> var0x.method29().method33());
   }

   public enum Type {
      NORMAL,
      SHADOW,
      BORDER;
   }
}
