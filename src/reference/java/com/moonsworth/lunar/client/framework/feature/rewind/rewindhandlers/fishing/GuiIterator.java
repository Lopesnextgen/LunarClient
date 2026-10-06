package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.util.List;
import lombok.Generated;

public class GuiIterator extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator implements DriverGuiExtensionLegacy {
   private static final TextOption field6 = (TextOption)((Data)OptionFactory.method12("projectName")
         .method15(PhosphorIconLegacy.PI_TEXT_CURSOR_ALPHABET_STROKE))
      .method31();
   private static final EnumOption<Gui2Extension> field7 = (EnumOption<Gui2Extension>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
               "videoResolution", Gui2Extension.p1080
            )
            .method16(com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy.DROPDOWN))
         .method10(PhosphorIconLegacy.PI_MONITOR02_STROKE))
      .method31();
   private static final MultiNumberOption<Integer> field8 = (MultiNumberOption<Integer>)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22(
               "customVideoResolution", new Integer[]{1920, 1080}
            )
            .method8(Codec.INT.listOf()))
         .method5(new String[]{"W", "H"})
         .method17(() -> field7.get() != Gui2Extension.CUSTOM))
      .method31();
   private static final EnumOption<Gui2Extension2> field9 = (EnumOption<Gui2Extension2>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
               "videoOrientation", Gui2Extension2.HORIZONTAL
            )
            .method16(com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy.DROPDOWN))
         .method10(PhosphorIconLegacy.PI_REPEAT_SQUARE_STROKE))
      .method31();
   private static final IntegerOption field10 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "videoFramerate"
               )
               .method4(60))
            .method7(30, 240))
         .method15(PhosphorIconLegacy.PI_FILM_STROKE))
      .method31();
   private static final EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension> field11 = (EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
               "audioType", com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension.STEREO
            )
            .method16(com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy.DROPDOWN))
         .method10(PhosphorIconLegacy.PI_HEADPHONES_STROKE))
      .method31();
   private static boolean field12 = true;
   private static final List<ClientOption<?>> field13 = List.of(field6, field7, field8, field9, field10, field11);
   private static final List<ClientOption<?>> field14 = List.of(field7, field8, field9, field10, field11);

   public static RewindhandlersNameplate method2() {
      RewindhandlersNameplate var0 = new RewindhandlersNameplate();
      Gui2Extension var1 = (Gui2Extension)method8().get();
      Gui2Extension2 var2 = (Gui2Extension2)method10().get();
      List var3 = (List)method9().get();
      int var4 = var1.getWidth();
      int var5 = var1.getHeight();
      if (var1 == Gui2Extension.CUSTOM) {
         var4 = (Integer)var3.get(0);
         var5 = (Integer)var3.get(1);
      }

      if (var2 == Gui2Extension2.VERTICAL && var4 > var5) {
         int var6 = var4;
         var4 = var5;
         var5 = var6;
      }

      var4 = Math.max(64, var4);
      var5 = Math.max(64, var5);
      var0.setWidth(var4);
      var0.setHeight(var5);
      var0.setFps((Integer)method11().get());
      var0.method16(var0.getFps());
      var0.method21(method12().get() == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension.STEREO);
      return var0;
   }

   public JsonElement provide() {
      return this.method128();
   }

   public JsonElement method128() {
      JsonObject var1 = new JsonObject();
      JsonArray var2 = new JsonArray();

      for (ClientOption var4 : field12 ? field13 : field14) {
         OptionDataProvider var5 = (OptionDataProvider)var4.method1(OptionTraits.field10);
         if (var5 != null) {
            var2.add(var5.provide());
         }
      }

      var1.add("options", var2);
      return var1;
   }

   @CallbackJS("init")
   public static void init(String var0) {
      for (ClientOption var2 : field13) {
         var2.reset();
      }

      field12 = !var0.isEmpty();
      field6.method10(var0);
      field6.method3(var0);
   }

   @CallbackJS("updateValue")
   public static void method3(String var0, String var1) {
      for (ClientOption var3 : field13) {
         if (var3.getId().equals(var0)) {
            var3.method21(var1);
            break;
         }
      }
   }

   @CallbackJS("validate")
   public static String method4(String var0, String var1) {
      if (!var0.equals("projectName") && !var0.equals("rewindName")) {
         return "";
      }

      TranslationManager var2 = ThreadModuleDump63.method4().method67();
      if (!var1.isEmpty() && var1.matches("[^<>:\"/\\\\|?*]+")) {
         File var3 = new File(var0.equals("rewindName") ? Gui.field7 : Gui.field10, Bridge.getMinecraftVersion().method45());
         if (!new File(var3, var1).exists()) {
            return "";
         } else {
            return var0.equals("rewindName")
               ? var2.method2("rewind", "recordingAlreadyExists", new Object[0])
               : var2.method2("rewind", "projectAlreadyExists", new Object[0]);
         }
      } else {
         return var2.method2("rewind", "invalidName", new Object[0]);
      }
   }

   @CallbackJS("rename")
   public static void method5(String var0, String var1) {
      File var2 = new File(var0);
      if (var2.isFile()) {
         String var3 = var0.substring(var0.lastIndexOf(46) + 1);
         if (!var1.endsWith("." + var3)) {
            var1 = var1 + "." + var3;
         }
      }

      File var4 = new File(var2.getParentFile(), var1);
      if (!var4.exists()) {
         var2.renameTo(var4);
         ThreadModuleDump63.method4().method90().method15().method2();
      }
   }

   @Generated
   public static TextOption method7() {
      return field6;
   }

   @Generated
   public static EnumOption<Gui2Extension> method8() {
      return field7;
   }

   @Generated
   public static MultiNumberOption<Integer> method9() {
      return field8;
   }

   @Generated
   public static EnumOption<Gui2Extension2> method10() {
      return field9;
   }

   @Generated
   public static IntegerOption method11() {
      return field10;
   }

   @Generated
   public static EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension> method12() {
      return field11;
   }
}
