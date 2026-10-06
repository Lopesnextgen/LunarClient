package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.render.shader.DevShaderEditor;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.function.Consumer;

public class Shaderdebugmod5 implements DriverGuiExtensionLegacy, Extension {
   public GuiIterator getProvider() {
      ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
      return !var1.isValid() ? null : var1.method27();
   }

   public JsonElement provide() {
      return this.provide();
   }

   @CallbackJS("toggleUniform")
   public static void method2(String var0, boolean var1) {
      GlslBuiltin var2 = DevShaderEditor.method1(var0);
      if (var2 != null) {
         getProvider(var2x -> var2x.method7(var2, var1));
      }
   }

   @CallbackJS("toggleHiddenUniform")
   public static void method3(String var0, boolean var1) {
      GlslBuiltin var2 = DevShaderEditor.method1(var0);
      if (var2 != null) {
         getProvider(var2x -> var2x.method8(var2, var1));
      }
   }

   @CallbackJS("updateResolution")
   public static void method4(int var0, int var1) {
      getProvider(var2 -> var2.method9(var0, var1));
   }

   @CallbackJS("updateShaderType")
   public static void method5(boolean var0, boolean var1) {
      getProvider(var2 -> var2.method10(var0, var1));
   }

   @CallbackJS("updateStaticShader")
   public static void method6(boolean var0, String var1) {
      getProvider(var2 -> var2.method11(var0, var1));
   }

   @CallbackJS("requestCustomShader")
   public static void method7(boolean var0) {
      getProvider(var1 -> var1.method13(var0));
   }

   @CallbackJS("updateMiscToggle")
   public static void method8(String var0, boolean var1) {
      getProvider(var2 -> var2.method15(var0, var1));
   }

   @CallbackJS("requestFeedAction")
   public static void method9(String var0) {
      getProvider(var1 -> var1.method16(var0));
   }

   @CallbackJS("requestHotReload")
   public static void method10() {
      getProvider(Shaderdebugmod::method28);
      LcuiScreen.method15();
   }

   @CallbackJS("createSampler")
   public static void method11() {
      getProvider(Shaderdebugmod::method23);
   }

   @CallbackJS("requestSamplerRename")
   public static void method12(String var0, int var1) {
      getProvider(var2 -> var2.method24(var0, var1));
   }

   @CallbackJS("requestSamplerFile")
   public static void method13(int var0) {
      getProvider(var1 -> var1.method25(var0));
   }

   @CallbackJS("requestDeleteSampler")
   public static void method14(int var0) {
      getProvider(var1 -> var1.method26(var0));
   }

   private static void getProvider(Consumer<Shaderdebugmod> var0) {
      ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
      if (var1.isValid()) {
         Shaderdebugmod var2 = var1.method26();
         if (var2 != null) {
            var0.accept(var2);
         }
      }
   }
}
