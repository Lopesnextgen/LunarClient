package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.replay.export.VideoResolution;
import com.moonsworth.lunar.client.replay.export.VideoOrientation;

public class CreateProjectBridge extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator implements DriverGuiExtension {
   private static final TextOption field6 = (TextOption)((Data)OptionFactory.method12("projectName")
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_TEXT_CURSOR_ALPHABET_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private static final EnumOption<VideoResolution> field7 = (EnumOption<VideoResolution>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
               "videoResolution", VideoResolution.p1080
            )
            .HORHROIOIOICIRHIOCOICHHHIHCIIO(com.moonsworth.lunar.client.driver.DriverFieldType.DROPDOWN))
         .method10(PhosphorIcon.PI_MONITOR02_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private static final MultiNumberOption<Integer> field8 = (MultiNumberOption<Integer>)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22(
               "customVideoResolution", new Integer[]{1920, 1080}
            )
            .method8(Codec.INT.listOf()))
         .method5(new String[]{"W", "H"})
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> field7.get() != VideoResolution.CUSTOM))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private static final EnumOption<VideoOrientation> field9 = (EnumOption<VideoOrientation>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
               "videoOrientation", VideoOrientation.HORIZONTAL
            )
            .HORHROIOIOICIRHIOCOICHHHIHCIIO(com.moonsworth.lunar.client.driver.DriverFieldType.DROPDOWN))
         .method10(PhosphorIcon.PI_REPEAT_SQUARE_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private static final IntegerOption field10 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "videoFramerate"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
            .OCRRICRIORICCCRHIOHORCICIHHICO(30, 240))
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(PhosphorIcon.PI_FILM_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private static final EnumOption<com.moonsworth.lunar.client.replay.audio.ReplayAudioChannels> field11 = (EnumOption<com.moonsworth.lunar.client.replay.audio.ReplayAudioChannels>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
               "audioType", com.moonsworth.lunar.client.replay.audio.ReplayAudioChannels.STEREO
            )
            .HORHROIOIOICIRHIOCOICHHHIHCIIO(com.moonsworth.lunar.client.driver.DriverFieldType.DROPDOWN))
         .method10(PhosphorIcon.PI_HEADPHONES_STROKE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private static boolean field12 = true;
   private static final List<ClientOption<?>> field13 = List.of(field6, field7, field8, field9, field10, field11);
   private static final List<ClientOption<?>> field14 = List.of(field7, field8, field9, field10, field11);

   public CreateProjectBridge() {
   }

   public static ExportSettings method2() {
      ExportSettings rewindhandlersnameplate0 = new ExportSettings();
      VideoResolution gui2extension1 = (VideoResolution)method8().get();
      VideoOrientation gui2extension22 = (VideoOrientation)method10().get();
      List list3 = (List)method9().get();
      int number4 = gui2extension1.getWidth();
      int number5 = gui2extension1.getHeight();
      if (gui2extension1 == VideoResolution.CUSTOM) {
         number4 = (Integer)list3.get(0);
         number5 = (Integer)list3.get(1);
      }

      if (gui2extension22 == VideoOrientation.VERTICAL && number4 > number5) {
         int number6 = number4;
         number4 = number5;
         number5 = number6;
      }

      number4 = Math.max(64, number4);
      number5 = Math.max(64, number5);
      rewindhandlersnameplate0.setWidth(number4);
      rewindhandlersnameplate0.setHeight(number5);
      rewindhandlersnameplate0.setFps((Integer)method11().get());
      rewindhandlersnameplate0.method16(rewindhandlersnameplate0.getFps());
      rewindhandlersnameplate0.method21(method12().get() == com.moonsworth.lunar.client.replay.audio.ReplayAudioChannels.STEREO);
      return rewindhandlersnameplate0;
   }

   public JsonElement provide() {
      return this.method128();
   }

   public JsonElement method128() {
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();

      for (ClientOption lightingextension4 : field12 ? field13 : field14) {
         OptionDataProvider guiextension5 = (OptionDataProvider)lightingextension4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
         if (guiextension5 != null) {
            array2.add(guiextension5.provide());
         }
      }

      json1.add("options", array2);
      return json1;
   }

   @CallbackJS("init")
   public static void init(String text0) {
      for (ClientOption lightingextension2 : field13) {
         lightingextension2.reset();
      }

      field12 = !text0.isEmpty();
      field6.method10(text0);
      field6.method3(text0);
   }

   @CallbackJS("updateValue")
   public static void method3(String text0, String text1) {
      for (ClientOption lightingextension3 : field13) {
         if (lightingextension3.getId().equals(text0)) {
            lightingextension3.method21(text1);
            break;
         }
      }
   }

   @CallbackJS("validate")
   public static String method4(String text0, String text1) {
      if (!text0.equals("projectName") && !text0.equals("rewindName")) {
         return "";
      }

      TranslationManager foghandler282 = Ref.method4().method67();
      if (!text1.isEmpty() && text1.matches("[^<>:\"/\\\\|?*]+")) {
         File file3 = new File(text0.equals("rewindName") ? RewindPaths.field7 : RewindPaths.field10, Bridge.getMinecraftVersion().method45());
         if (!new File(file3, text1).exists()) {
            return "";
         } else {
            return text0.equals("rewindName")
               ? foghandler282.method2("rewind", "recordingAlreadyExists", new Object[0])
               : foghandler282.method2("rewind", "projectAlreadyExists", new Object[0]);
         }
      } else {
         return foghandler282.method2("rewind", "invalidName", new Object[0]);
      }
   }

   @CallbackJS("rename")
   public static void method5(String text0, String text1) {
      File file2 = new File(text0);
      if (file2.isFile()) {
         String text3 = text0.substring(text0.lastIndexOf(46) + 1);
         if (!text1.endsWith("." + text3)) {
            text1 = text1 + "." + text3;
         }
      }

      File file4 = new File(file2.getParentFile(), text1);
      if (!file4.exists()) {
         file2.renameTo(file4);
         Ref.method4().method90().method15().method2();
      }
   }

   @Generated
   public static TextOption method7() {
      return field6;
   }

   @Generated
   public static EnumOption<VideoResolution> method8() {
      return field7;
   }

   @Generated
   public static MultiNumberOption<Integer> method9() {
      return field8;
   }

   @Generated
   public static EnumOption<VideoOrientation> method10() {
      return field9;
   }

   @Generated
   public static IntegerOption method11() {
      return field10;
   }

   @Generated
   public static EnumOption<com.moonsworth.lunar.client.replay.audio.ReplayAudioChannels> method12() {
      return field11;
   }
}
