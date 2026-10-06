package com.moonsworth.lunar.client.replay.export;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.replay.gui.ProgressListener;
import com.moonsworth.lunar.client.replay.timeline.TimelineRenderJob;
import com.moonsworth.lunar.client.replay.export.ExportSettingsSection;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.replay.export.EncoderProbe;
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
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.replay.audio.AudioSampleRate;
import com.moonsworth.lunar.client.replay.audio.BitrateMode;
import com.moonsworth.lunar.client.replay.audio.ReplayAudioChannels;

public class ExportSettingsPanel extends com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider {
   private final ProgressListener field6 = new ProgressListener() {
      @Override
      public void onStart() {
         ExportSettingsPanel.this.field7 = true;
         ExportSettingsPanel.this.field8 = 0.0F;
         ExportSettingsPanel.this.field9 = "";
         this.method4();
      }

      @Override
      public void method1(long number1, long number3, float value5) {
         ExportSettingsPanel.this.field8 = value5;
         this.method4();
      }

      @Override
      public void method2() {
         ExportSettingsPanel.this.field7 = false;
         this.method4();
      }

      @Override
      public void method3(String text1, Exception exception2) {
         ExportSettingsPanel.this.field9 = text1;
         this.method4();
      }

      @Override
      public void onMessage(String text1) {
         ExportSettingsPanel.this.field9 = text1;
         this.method4();
      }

      private void method4() {
         RewindHandlers rewindhandlers1 = Ref.method4().method40().method85().method35();
         if (rewindhandlers1 != null) {
            ExportSettingsPanel.this.method1(rewindhandlers1);
         }
      }
   };
   private boolean field7 = false;
   private float field8 = 0.0F;
   private String field9 = "";
   private final EnumOption<com.moonsworth.lunar.client.replay.export.VideoFormat> field10 = (EnumOption<com.moonsworth.lunar.client.replay.export.VideoFormat>)OptionFactory.method10(
         "fileFormat", com.moonsworth.lunar.client.replay.export.VideoFormat.MP4
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<com.moonsworth.lunar.client.replay.export.VideoCodec> field11 = (EnumOption<com.moonsworth.lunar.client.replay.export.VideoCodec>)OptionFactory.method10(
         "codec", com.moonsworth.lunar.client.replay.export.VideoCodec.H264
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<com.moonsworth.lunar.client.replay.export.VideoEncoder> field12 = (EnumOption<com.moonsworth.lunar.client.replay.export.VideoEncoder>)OptionFactory.method10(
         "encoder", com.moonsworth.lunar.client.replay.export.VideoEncoder.LIBX264
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ExportSettingsSection field13 = new ExportSettingsSection("renderSettings", List.of(this.field10, this.field11, this.field12));
   private final MultiNumberOption<Integer> field14 = (MultiNumberOption<Integer>)((Data)OptionFactory.method22("videoResolution", new Integer[]{1920, 1080})
         .method8(Codec.INT.listOf()))
      .method5(new String[]{"W", "H"})
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final IntegerOption field15 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "videoFramerate"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
            .OCRRICRIORICCCRHIOHORCICIHHICO(30, 60))
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> {
            NumberRule nameplate1x = (NumberRule)this.field15.RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
            return nameplate1x.getMax().equals(nameplate1x.getMin());
         }))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<BitrateMode> field16 = (EnumOption<BitrateMode>)OptionFactory.method10("bitrate", BitrateMode.AUTO_QUALITY)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final IntegerOption field17 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "customBitrate"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(20000))
            .OCRRICRIORICCCRHIOHORCICIHHICO(1000, 100000))
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> this.field16.get() != BitrateMode.CUSTOM))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ExportSettingsSection field18 = new ExportSettingsSection("videoSettings", List.of(this.field14, this.field15, this.field16, this.field17));
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("exportAudio").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<ReplayAudioChannels> field20 = (EnumOption<ReplayAudioChannels>)OptionFactory.method10("channels", ReplayAudioChannels.STEREO)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<AudioSampleRate> field21 = (EnumOption<AudioSampleRate>)OptionFactory.method10("frequency", AudioSampleRate.hz44100)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ExportSettingsSection field22 = new ExportSettingsSection("audioSettings", List.of(this.field19, this.field20, this.field21));
   private final EnumOption<RenderRange> field23 = (EnumOption<RenderRange>)OptionFactory.method10("renderRange", RenderRange.ENTIRE)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final TextOption field24 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "renderIn"
            )
            .HIIIOHRRROCICIOIORRRIRCRCHHIII("00:00"))
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> this.field23.get() != RenderRange.REGION))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final TextOption field25 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "renderOut"
            )
            .HIIIOHRRROCICIOIORRRIRCRCHHIII("00:00"))
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> this.field23.get() != RenderRange.REGION))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ExportSettingsSection field26 = new ExportSettingsSection("exportSettings", List.of(this.field23, this.field24, this.field25));
   protected final List<ExportSettingsSection> field27 = List.of(this.field13, this.field18, this.field22, this.field26);

   public ExportSettingsPanel(List<com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider> list1) {
      super(list1);
      this.field23.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> {
         ExportSettings rewindhandlersnameplate1x = method8();
         if (rewindhandlersnameplate1x != null) {
            rewindhandlersnameplate1x.method22(0);
            rewindhandlersnameplate1x.method23(arg0 == RenderRange.REGION ? method7() : 0);
         }
      });
      this.field24.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> {
         ExportSettings rewindhandlersnameplate1x = method8();
         if (rewindhandlersnameplate1x != null) {
            int number2 = method6(arg0, rewindhandlersnameplate1x.getFps());
            if (number2 >= 0) {
               rewindhandlersnameplate1x.method22(Math.max(0, Math.min(number2, rewindhandlersnameplate1x.method11())));
            }
         }
      });
      this.field25.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> {
         ExportSettings rewindhandlersnameplate1x = method8();
         if (rewindhandlersnameplate1x != null) {
            int number2 = method6(arg0, rewindhandlersnameplate1x.getFps());
            if (number2 >= 0) {
               number2 = Math.min(number2, method7());
               rewindhandlersnameplate1x.method23(Math.max(number2, rewindhandlersnameplate1x.method10()));
            }
         }
      });
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (arg1x != method8().method2() && !arg1x.getCodecs().contains(this.field11.get())) {
            this.field11.OIRHOOIICOCIOOHICRRRICORIHHIHC(arg1x.getCodecs().get(0));
         }

         method8().method12(arg1x);
      });
      this.field11
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg1x -> {
               if (arg1x != method8().method3() && !arg1x.getEncoders().contains(this.field12.get())) {
                  this.field12
                     .OIRHOOIICOCIOOHICRRRICORIHHIHC(
                        (com.moonsworth.lunar.client.replay.export.VideoEncoder)this.field12.getDefaultValue()
                     );
               }

               method8().method13(arg1x);
            }
         );
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> method8().method14(this.field12.isDefault() ? null : arg1x));
      this.field14.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> {
         int number1x = (Integer)arg0.get(0);
         int number2 = (Integer)arg0.get(1);
         if (number1x >= 64 && number2 >= 64) {
            method8().setWidth(number1x);
            method8().setHeight(number2);
         }
      });
      this.field15.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> method8().method16(arg0));
      this.field16.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         method8().method19(arg1x);
         if (arg1x != BitrateMode.CUSTOM) {
            method8().method20(method8().method1());
            this.field17.method6(method8().method8());
         }
      });
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (this.field16.get() == BitrateMode.CUSTOM) {
            method8().method20(arg1x);
         }
      });
      this.field19.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> method8().method17(arg0));
      this.field20.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> method8().method21(arg0 == ReplayAudioChannels.STEREO));
      this.field21.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> method8().setFrequency(arg0.getFrequency()));
   }

   public boolean method1(String text1) {
      ExportSettings rewindhandlersnameplate2 = method8();
      if (rewindhandlersnameplate2 == null) {
         return false;
      } else if (this.field24.getId().equals(text1)) {
         rewindhandlersnameplate2.method22(0);
         return true;
      } else if (this.field25.getId().equals(text1)) {
         rewindhandlersnameplate2.method23(Math.max(method7(), rewindhandlersnameplate2.method10()));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      if (!rewindhandlers1.method57().method26().method5()) {
         this.method3("properties", new JsonArray());
         this.method3("timelines", new JsonArray());
         this.method3("downloading", this.field7);
         this.method3("downloadProgress", this.field8);
         this.method3("downloadMessage", this.field9);
         this.method3("encoders", false);
         this.method3("renderInFrame", 0);
         this.method3("renderOutFrame", 0);
      } else {
         EncoderProbe rewindhandlersnameplate_42 = rewindhandlers1.method57().method26().method8();
         ExportSettings rewindhandlersnameplate3 = rewindhandlers1.method40().method37().method13();
         this.method3(rewindhandlersnameplate_42, rewindhandlersnameplate3);
         this.method3("renderInFrame", rewindhandlersnameplate3 == null ? 0 : rewindhandlersnameplate3.method10());
         this.method3("renderOutFrame", rewindhandlersnameplate3 == null ? 0 : rewindhandlersnameplate3.method11());
         JsonArray array4 = new JsonArray();

         for (ExportSettingsSection coordinates26 : this.field27) {
            if (coordinates26 != this.field22
               || ((com.moonsworth.lunar.client.replay.export.VideoFormat)this.field10.get()).isSupportsAudio()) {
               array4.add(this.method4(rewindhandlersnameplate_42, coordinates26));
            }
         }

         this.method3("properties", array4);
         JsonArray array10 = new JsonArray();

         for (TimelineRenderJob highlightimpl7 : rewindhandlers1.method57().method28()) {
            File file8 = com.moonsworth.lunar.client.replay.project.RewindPaths.field11.toPath().resolve(highlightimpl7.method4()).toFile();
            JsonObject json9 = new JsonObject();
            json9.addProperty("name", file8.getName());
            json9.addProperty("path", file8.getAbsolutePath());
            json9.addProperty("id", highlightimpl7.getId().toString());
            array10.add(json9);
         }

         this.method3("timelines", array10);
         this.method3("encoders", rewindhandlersnameplate_42.isDone());
      }
   }

   private void method3(EncoderProbe rewindhandlersnameplate_41, ExportSettings rewindhandlersnameplate2) {
      if (rewindhandlersnameplate2 != null) {
         boolean flag3 = rewindhandlersnameplate2.method11() > 0;
         int number4 = rewindhandlersnameplate2.getFps();
         this.field23.CHRIHRCRCIOIHIROIHROCHHHCOICOI(flag3 ? RenderRange.REGION : RenderRange.ENTIRE);
         this.field24.method12(method5(rewindhandlersnameplate2.method10(), number4));
         this.field25.method12(method5(flag3 ? rewindhandlersnameplate2.method11() : method7(), number4));
         this.field25.method3(method5(method7(), number4));
         this.field10.CHRIHRCRCIOIHIROIHROCHHHCOICOI(rewindhandlersnameplate2.method2());
         this.field11.CHRIHRCRCIOIHIROIHROCHHHCOICOI(rewindhandlersnameplate2.method3());
         this.field11.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(rewindhandlersnameplate2.method2().getCodecs().get(0));
         if (rewindhandlersnameplate2.method4() == null) {
            if (!rewindhandlersnameplate_41.method6().isEmpty()) {
               com.moonsworth.lunar.client.replay.export.VideoEncoder gui2extension35 = rewindhandlersnameplate_41.method5(rewindhandlersnameplate2.method3());
               this.field12.CHRIHRCRCIOIHIROIHROCHHHCOICOI(gui2extension35);
               this.field12.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(gui2extension35);
            }
         } else {
            this.field12.CHRIHRCRCIOIHIROIHROCHHHCOICOI(rewindhandlersnameplate2.method4());
         }

         this.field14.CHRIHRCRCIOIHIROIHROCHHHCOICOI(Lists.newArrayList(new Integer[]{rewindhandlersnameplate2.getWidth(), rewindhandlersnameplate2.getHeight()}));
         this.field15.method12(rewindhandlersnameplate2.method5());
         this.field15.method6(rewindhandlersnameplate2.getFps());
         this.field15
            .method2(
               com.moonsworth.lunar.client.config.option.OptionTraits.field7,
               (arg1x, arg2x) -> arg2x == null
                  ? new IntegerNumberRange(Integer.MIN_VALUE, rewindhandlersnameplate2.getFps(), true, true)
                  : new IntegerNumberRange(arg2x.getMin().intValue(), rewindhandlersnameplate2.getFps(), arg2x.method1(), arg2x.method2())
            );
         this.field16.CHRIHRCRCIOIHIROIHROCHHHCOICOI(rewindhandlersnameplate2.method7());
         this.field17.method12(rewindhandlersnameplate2.method1());
         this.field19.method12(rewindhandlersnameplate2.method6());
         this.field20.CHRIHRCRCIOIHIROIHROCHHHCOICOI(rewindhandlersnameplate2.method9() ? ReplayAudioChannels.STEREO : ReplayAudioChannels.MONO);
         this.field21.CHRIHRCRCIOIHIROIHROCHHHCOICOI(AudioSampleRate.valueOf(rewindhandlersnameplate2.getFrequency()));
      }
   }

   private JsonObject method4(EncoderProbe rewindhandlersnameplate_41, ExportSettingsSection coordinates22) {
      JsonObject json3 = new JsonObject();
      JsonArray array4 = new JsonArray();
      ArrayList list5 = new ArrayList();

      for (ClientOption lightingextension7 : coordinates22.options()) {
         if (!lightingextension7.isHidden() && !list5.contains(lightingextension7.getId())) {
            OptionDataProvider guiextension8 = (OptionDataProvider)lightingextension7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
            if (guiextension8 != null && guiextension8.provide() instanceof JsonObject json9) {
               if (lightingextension7 == this.field11) {
                  JsonArray array14 = new JsonArray();

                  for (com.moonsworth.lunar.client.replay.export.VideoCodec gui2extension212 : ((com.moonsworth.lunar.client.replay.export.VideoFormat)this.field10
                        .get())
                     .getCodecs()) {
                     array14.add(gui2extension212.provide());
                  }

                  json9.add("choices", array14);
               }

               if (lightingextension7 == this.field12) {
                  JsonArray array15 = new JsonArray();

                  for (com.moonsworth.lunar.client.replay.export.VideoEncoder gui2extension317 : ((com.moonsworth.lunar.client.replay.export.VideoCodec)this.field11
                        .get())
                     .getEncoders()) {
                     if (rewindhandlersnameplate_41.method6().contains(gui2extension317)) {
                        array15.add(gui2extension317.provide());
                     }
                  }

                  json9.add("choices", array15);
               }

               json9.addProperty("canReset", !lightingextension7.isDefault());
               array4.add(json9);
            }
         } else {
            list5.addAll(lightingextension7.getChildren().stream().map(ClientOption::getId).toList());
         }
      }

      json3.addProperty("type", coordinates22.type());
      json3.addProperty("name", Client.method109().method67().method2("rewind", coordinates22.type(), new Object[0]));
      json3.addProperty("enabled", true);
      json3.add("keyframes", array4);
      JsonArray array13 = new JsonArray();
      json3.add("childProperties", array13);
      return json3;
   }

   private static String method5(int number0, int number1) {
      int number2 = (int)Math.round((double)number0 / Math.max(number1, 1));
      int number3 = number2 % 60;
      int number4 = number2 / 60 % 60;
      int number5 = number2 / 3600;
      return number5 > 0 ? String.format("%d:%02d:%02d", number5, number4, number3) : String.format("%02d:%02d", number4, number3);
   }

   private static int method6(String text0, int number1) {
      if (text0 != null && !text0.isBlank()) {
         String[] items2 = text0.trim().split(":");

         int number3;
         try {
            if (items2.length == 3) {
               number3 = Integer.parseInt(items2[0].trim()) * 3600 + Integer.parseInt(items2[1].trim()) * 60 + Integer.parseInt(items2[2].trim());
            } else if (items2.length == 2) {
               number3 = Integer.parseInt(items2[0].trim()) * 60 + Integer.parseInt(items2[1].trim());
            } else {
               if (items2.length != 1) {
                  return -1;
               }

               number3 = Integer.parseInt(items2[0].trim());
            }
         } catch (NumberFormatException numberformatexception5) {
            return -1;
         }

         return Math.max(0, number3 * Math.max(number1, 1));
      } else {
         return -1;
      }
   }

   private static int method7() {
      RewindHandlers rewindhandlers0 = Ref.method4().method40().method85().method35();
      return rewindhandlers0 == null ? 0 : rewindhandlers0.method40().method37().method3();
   }

   private static ExportSettings method8() {
      RewindHandlers rewindhandlers0 = Ref.method4().method40().method85().method35();
      return rewindhandlers0 == null ? null : rewindhandlers0.method40().method37().method13();
   }

   @Generated
   public ProgressListener method9() {
      return this.field6;
   }
}
