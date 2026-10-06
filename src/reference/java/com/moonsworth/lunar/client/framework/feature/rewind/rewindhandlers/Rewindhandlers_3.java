package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HighlightImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate_4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.MultiNumberOption.Data;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.config.option.IntegerNumberRange;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Rewindhandlers_3 extends com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator {
   private final Gui3 progressListener = new Gui3() {
      @Override
      public void onStart() {
         Rewindhandlers_3.this.downloading = true;
         Rewindhandlers_3.this.downloadProgress = 0.0F;
         Rewindhandlers_3.this.downloadMessage = "";
         this.method4();
      }

      @Override
      public void method1(long var1, long var3, float var5) {
         Rewindhandlers_3.this.downloadProgress = var5;
         this.method4();
      }

      @Override
      public void refresh() {
         Rewindhandlers_3.this.downloading = false;
         this.method4();
      }

      @Override
      public void method3(String var1, Exception var2) {
         Rewindhandlers_3.this.downloadMessage = var1;
         this.method4();
      }

      @Override
      public void onMessage(String var1) {
         Rewindhandlers_3.this.downloadMessage = var1;
         this.method4();
      }

      private void method4() {
         RewindHandlers var1 = ThreadModuleDump63.method4().method40().method85().method35();
         if (var1 != null) {
            Rewindhandlers_3.this.method1(var1);
         }
      }
   };
   private boolean downloading = false;
   private float downloadProgress = 0.0F;
   private String downloadMessage = "";
   private final EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension> fileFormatOption = (EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension>)OptionFactory.method10(
         "fileFormat", com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension.MP4
      )
      .method31();
   private final EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension2> codecOption = (EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension2>)OptionFactory.method10(
         "codec", com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension2.H264
      )
      .method31();
   private final EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3> encoderOption = (EnumOption<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3>)OptionFactory.method10(
         "encoder", com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3.LIBX264
      )
      .method31();
   private final Coordinates2 renderSettingsSection = new Coordinates2("renderSettings", List.of(this.fileFormatOption, this.codecOption, this.encoderOption));
   private final MultiNumberOption<Integer> videoResolutionOption = (MultiNumberOption<Integer>)((Data)OptionFactory.method22("videoResolution", new Integer[]{1920, 1080})
         .method8(Codec.INT.listOf()))
      .method5(new String[]{"W", "H"})
      .method31();
   private final IntegerOption videoFramerateOption = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "videoFramerate"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
            .OCRRICRIORICCCRHIOHORCICIHHICO(30, 60))
         .method17(() -> {
            NumberRule var1x = (NumberRule)this.videoFramerateOption.RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
            return var1x.getMax().equals(var1x.getMin());
         }))
      .method31();
   private final EnumOption<Gui2Extension2> bitrateOption = (EnumOption<Gui2Extension2>)OptionFactory.method10("bitrate", Gui2Extension2.AUTO_QUALITY)
      .method31();
   private final IntegerOption customBitrateOption = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "customBitrate"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(20000))
            .OCRRICRIORICCCRHIOHORCICIHHICO(1000, 100000))
         .method17(() -> this.bitrateOption.get() != Gui2Extension2.CUSTOM))
      .method31();
   private final Coordinates2 videoSettingsSection = new Coordinates2("videoSettings", List.of(this.videoResolutionOption, this.videoFramerateOption, this.bitrateOption, this.customBitrateOption));
   private final ToggleOption exportAudioOption = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("exportAudio").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<Gui2Extension> audioChannelsOption = (EnumOption<Gui2Extension>)OptionFactory.method10("channels", Gui2Extension.STEREO)
      .method31();
   private final EnumOption<Gui2Extension3> audioFrequencyOption = (EnumOption<Gui2Extension3>)OptionFactory.method10("frequency", Gui2Extension3.hz44100)
      .method31();
   private final Coordinates2 audioSettingsSection = new Coordinates2("audioSettings", List.of(this.exportAudioOption, this.audioChannelsOption, this.audioFrequencyOption));
   private final EnumOption<Gui2Extension4> renderRangeOption = (EnumOption<Gui2Extension4>)OptionFactory.method10("renderRange", Gui2Extension4.ENTIRE)
      .method31();
   private final TextOption renderInOption = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "renderIn"
            )
            .method2("00:00"))
         .method17(() -> this.renderRangeOption.get() != Gui2Extension4.REGION))
      .method31();
   private final TextOption renderOutOption = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "renderOut"
            )
            .method2("00:00"))
         .method17(() -> this.renderRangeOption.get() != Gui2Extension4.REGION))
      .method31();
   private final Coordinates2 exportSettingsSection = new Coordinates2("exportSettings", List.of(this.renderRangeOption, this.renderInOption, this.renderOutOption));
   protected final List<Coordinates2> sections = List.of(this.renderSettingsSection, this.videoSettingsSection, this.audioSettingsSection, this.exportSettingsSection);

   public Rewindhandlers_3(List<com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator> var1) {
      super(var1);
      this.renderRangeOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         RewindhandlersNameplate var1x = getRenderSettings();
         if (var1x != null) {
            var1x.method22(0);
            var1x.method23(var0 == Gui2Extension4.REGION ? getTimelineDuration() : 0);
         }
      });
      this.renderInOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         RewindhandlersNameplate var1x = getRenderSettings();
         if (var1x != null) {
            int var2 = parseTime(var0, var1x.getFps());
            if (var2 >= 0) {
               var1x.method22(Math.max(0, Math.min(var2, var1x.method11())));
            }
         }
      });
      this.renderOutOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         RewindhandlersNameplate var1x = getRenderSettings();
         if (var1x != null) {
            int var2 = parseTime(var0, var1x.getFps());
            if (var2 >= 0) {
               var2 = Math.min(var2, getTimelineDuration());
               var1x.method23(Math.max(var2, var1x.method10()));
            }
         }
      });
      this.fileFormatOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if (var1x != getRenderSettings().method2() && !var1x.getCodecs().contains(this.codecOption.get())) {
            this.codecOption.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1x.getCodecs().get(0));
         }

         getRenderSettings().method12(var1x);
      });
      this.codecOption
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            var1x -> {
               if (var1x != getRenderSettings().method3() && !var1x.getEncoders().contains(this.encoderOption.get())) {
                  this.encoderOption
                     .OIRHOOIICOCIOOHICRRRICORIHHIHC(
                        (com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3)this.encoderOption.getDefaultValue()
                     );
               }

               getRenderSettings().method13(var1x);
            }
         );
      this.encoderOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> getRenderSettings().method14(this.encoderOption.isDefault() ? null : var1x));
      this.videoResolutionOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         int var1x = (Integer)var0.get(0);
         int var2 = (Integer)var0.get(1);
         if (var1x >= 64 && var2 >= 64) {
            getRenderSettings().setWidth(var1x);
            getRenderSettings().setHeight(var2);
         }
      });
      this.videoFramerateOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> getRenderSettings().method16(var0));
      this.bitrateOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         getRenderSettings().method19(var1x);
         if (var1x != Gui2Extension2.CUSTOM) {
            getRenderSettings().method20(getRenderSettings().method1());
            this.customBitrateOption.method6(getRenderSettings().method8());
         }
      });
      this.customBitrateOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if (this.bitrateOption.get() == Gui2Extension2.CUSTOM) {
            getRenderSettings().method20(var1x);
         }
      });
      this.exportAudioOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> getRenderSettings().method17(var0));
      this.audioChannelsOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> getRenderSettings().method21(var0 == Gui2Extension.STEREO));
      this.audioFrequencyOption.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> getRenderSettings().setFrequency(var0.getFrequency()));
   }

   public boolean method1(String var1) {
      RewindhandlersNameplate var2 = getRenderSettings();
      if (var2 == null) {
         return false;
      } else if (this.renderInOption.getId().equals(var1)) {
         var2.method22(0);
         return true;
      } else if (this.renderOutOption.getId().equals(var1)) {
         var2.method23(Math.max(getTimelineDuration(), var2.method10()));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void method1(RewindHandlers var1) {
      if (!var1.method57().method26().method5()) {
         this.method3("properties", new JsonArray());
         this.method3("timelines", new JsonArray());
         this.method3("downloading", this.downloading);
         this.method3("downloadProgress", this.downloadProgress);
         this.method3("downloadMessage", this.downloadMessage);
         this.method3("encoders", false);
         this.method3("renderInFrame", 0);
         this.method3("renderOutFrame", 0);
      } else {
         RewindhandlersNameplate_4 var2 = var1.method57().method26().method8();
         RewindhandlersNameplate var3 = var1.method40().method37().method13();
         this.method3(var2, var3);
         this.method3("renderInFrame", var3 == null ? 0 : var3.method10());
         this.method3("renderOutFrame", var3 == null ? 0 : var3.method11());
         JsonArray var4 = new JsonArray();

         for (Coordinates2 var6 : this.sections) {
            if (var6 != this.audioSettingsSection
               || ((com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension)this.fileFormatOption.get()).isSupportsAudio()) {
               var4.add(this.method4(var2, var6));
            }
         }

         this.method3("properties", var4);
         JsonArray var10 = new JsonArray();

         for (HighlightImpl var7 : var1.method57().method28()) {
            File var8 = com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field11.toPath().resolve(var7.method4()).toFile();
            JsonObject var9 = new JsonObject();
            var9.addProperty("name", var8.getName());
            var9.addProperty("path", var8.getAbsolutePath());
            var9.addProperty("id", var7.getId().toString());
            var10.add(var9);
         }

         this.method3("timelines", var10);
         this.method3("encoders", var2.isDone());
      }
   }

   private void method3(RewindhandlersNameplate_4 var1, RewindhandlersNameplate var2) {
      if (var2 != null) {
         boolean var3 = var2.method11() > 0;
         int var4 = var2.getFps();
         this.renderRangeOption.method10(var3 ? Gui2Extension4.REGION : Gui2Extension4.ENTIRE);
         this.renderInOption.method10(formatTime(var2.method10(), var4));
         this.renderOutOption.method10(formatTime(var3 ? var2.method11() : getTimelineDuration(), var4));
         this.renderOutOption.method3(formatTime(getTimelineDuration(), var4));
         this.fileFormatOption.method10(var2.method2());
         this.codecOption.method10(var2.method3());
         this.codecOption.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var2.method2().getCodecs().get(0));
         if (var2.method4() == null) {
            if (!var1.method6().isEmpty()) {
               com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3 var5 = var1.method5(var2.method3());
               this.encoderOption.method10(var5);
               this.encoderOption.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var5);
            }
         } else {
            this.encoderOption.method10(var2.method4());
         }

         this.videoResolutionOption.method10(Lists.newArrayList(new Integer[]{var2.getWidth(), var2.getHeight()}));
         this.videoFramerateOption.method10(var2.method5());
         this.videoFramerateOption.method6(var2.getFps());
         this.videoFramerateOption
            .method2(
               com.moonsworth.lunar.client.config.option.OptionTraits.field7,
               (var1x, var2x) -> var2x == null
                  ? new IntegerNumberRange(Integer.MIN_VALUE, var2.getFps(), true, true)
                  : new IntegerNumberRange(var2x.getMin().intValue(), var2.getFps(), var2x.method1(), var2x.method2())
            );
         this.bitrateOption.method10(var2.method7());
         this.customBitrateOption.method10(var2.method1());
         this.exportAudioOption.method10(var2.method6());
         this.audioChannelsOption.method10(var2.method9() ? Gui2Extension.STEREO : Gui2Extension.MONO);
         this.audioFrequencyOption.method10(Gui2Extension3.valueOf(var2.getFrequency()));
      }
   }

   private JsonObject method4(RewindhandlersNameplate_4 var1, Coordinates2 var2) {
      JsonObject var3 = new JsonObject();
      JsonArray var4 = new JsonArray();
      ArrayList var5 = new ArrayList();

      for (ClientOption var7 : var2.options()) {
         if (!var7.isHidden() && !var5.contains(var7.getId())) {
            OptionDataProvider var8 = (OptionDataProvider)var7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
            if (var8 != null && var8.provide() instanceof JsonObject var9) {
               if (var7 == this.codecOption) {
                  JsonArray var14 = new JsonArray();

                  for (com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension2 var12 : ((com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension)this.fileFormatOption
                        .get())
                     .getCodecs()) {
                     var14.add(var12.provide());
                  }

                  var9.add("choices", var14);
               }

               if (var7 == this.encoderOption) {
                  JsonArray var15 = new JsonArray();

                  for (com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3 var17 : ((com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension2)this.codecOption
                        .get())
                     .getEncoders()) {
                     if (var1.method6().contains(var17)) {
                        var15.add(var17.provide());
                     }
                  }

                  var9.add("choices", var15);
               }

               var9.addProperty("canReset", !var7.isDefault());
               var4.add(var9);
            }
         } else {
            var5.addAll(var7.getChildren().stream().map(ClientOption::getId).toList());
         }
      }

      var3.addProperty("type", var2.type());
      var3.addProperty("name", Client.method109().method67().method2("rewind", var2.type(), new Object[0]));
      var3.addProperty("enabled", true);
      var3.add("keyframes", var4);
      JsonArray var13 = new JsonArray();
      var3.add("childProperties", var13);
      return var3;
   }

   private static String formatTime(int var0, int var1) {
      int var2 = (int)Math.round((double)var0 / Math.max(var1, 1));
      int var3 = var2 % 60;
      int var4 = var2 / 60 % 60;
      int var5 = var2 / 3600;
      return var5 > 0 ? String.format("%d:%02d:%02d", var5, var4, var3) : String.format("%02d:%02d", var4, var3);
   }

   private static int parseTime(String var0, int var1) {
      if (var0 != null && !var0.isBlank()) {
         String[] var2 = var0.trim().split(":");

         int var3;
         try {
            if (var2.length == 3) {
               var3 = Integer.parseInt(var2[0].trim()) * 3600 + Integer.parseInt(var2[1].trim()) * 60 + Integer.parseInt(var2[2].trim());
            } else if (var2.length == 2) {
               var3 = Integer.parseInt(var2[0].trim()) * 60 + Integer.parseInt(var2[1].trim());
            } else {
               if (var2.length != 1) {
                  return -1;
               }

               var3 = Integer.parseInt(var2[0].trim());
            }
         } catch (NumberFormatException var5) {
            return -1;
         }

         return Math.max(0, var3 * Math.max(var1, 1));
      } else {
         return -1;
      }
   }

   private static int getTimelineDuration() {
      RewindHandlers var0 = ThreadModuleDump63.method4().method40().method85().method35();
      return var0 == null ? 0 : var0.method40().method37().method3();
   }

   private static RewindhandlersNameplate getRenderSettings() {
      RewindHandlers var0 = ThreadModuleDump63.method4().method40().method85().method35();
      return var0 == null ? null : var0.method40().method37().method13();
   }

   @Generated
   public Gui3 getProgressListener() {
      return this.progressListener;
   }
}
