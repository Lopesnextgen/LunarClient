package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.render.shader.DevShaderEditor;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.function.Consumer;

public class ShaderDebugJsBridge implements DriverGuiExtension, Extension {
   public ShaderDebugJsBridge() {
   }

   public GuiIterator getProvider() {
      ShaderDebugMod shaderdebugmod1 = Ref.method4().method40().method74();
      return !shaderdebugmod1.isValid() ? null : shaderdebugmod1.method27();
   }

   public JsonElement provide() {
      return this.RIROCIRRICRRHOOICOROOCHIOOIHHR();
   }

   @CallbackJS("toggleUniform")
   public static void method2(String text0, boolean flag1) {
      GlslBuiltin colorsaturationtype22 = DevShaderEditor.method1(text0);
      if (colorsaturationtype22 != null) {
         getProvider(arg2x -> arg2x.method7(colorsaturationtype22, flag1));
      }
   }

   @CallbackJS("toggleHiddenUniform")
   public static void method3(String text0, boolean flag1) {
      GlslBuiltin colorsaturationtype22 = DevShaderEditor.method1(text0);
      if (colorsaturationtype22 != null) {
         getProvider(arg2x -> arg2x.method8(colorsaturationtype22, flag1));
      }
   }

   @CallbackJS("updateResolution")
   public static void method4(int number0, int number1) {
      getProvider(arg2 -> arg2.method9(number0, number1));
   }

   @CallbackJS("updateShaderType")
   public static void method5(boolean flag0, boolean flag1) {
      getProvider(arg2 -> arg2.method10(flag0, flag1));
   }

   @CallbackJS("updateStaticShader")
   public static void method6(boolean flag0, String text1) {
      getProvider(arg2 -> arg2.method11(flag0, text1));
   }

   @CallbackJS("requestCustomShader")
   public static void method7(boolean flag0) {
      getProvider(arg1 -> arg1.method13(flag0));
   }

   @CallbackJS("updateMiscToggle")
   public static void method8(String text0, boolean flag1) {
      getProvider(arg2 -> arg2.method15(text0, flag1));
   }

   @CallbackJS("requestFeedAction")
   public static void method9(String text0) {
      getProvider(arg1 -> arg1.method16(text0));
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
   public static void method12(String text0, int number1) {
      getProvider(arg2 -> arg2.method24(text0, number1));
   }

   @CallbackJS("requestSamplerFile")
   public static void method13(int number0) {
      getProvider(arg1 -> arg1.method25(number0));
   }

   @CallbackJS("requestDeleteSampler")
   public static void method14(int number0) {
      getProvider(arg1 -> arg1.method26(number0));
   }

   private static void getProvider(Consumer<Shaderdebugmod> consumer0) {
      ShaderDebugMod shaderdebugmod1 = Ref.method4().method40().method74();
      if (shaderdebugmod1.isValid()) {
         Shaderdebugmod shaderdebugmod2 = shaderdebugmod1.method26();
         if (shaderdebugmod2 != null) {
            consumer0.accept(shaderdebugmod2);
         }
      }
   }
}
