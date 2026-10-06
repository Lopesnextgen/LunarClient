package com.moonsworth.lunar.client.mod.hud.audiosubtitles;

import com.moonsworth.lunar.bridge.SubtitleBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class AudioSubtitles extends AbstractFeature {
   private final List<SubtitleBridge> field8 = List.of(
      new AudioSubtitles.Data("Subtitle 1"), new AudioSubtitles.Data("Subtitle 2"), new AudioSubtitles.Data("Subtitle 3")
   );
   private final ToggleOption field9 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("border").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("textShadow").method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-872415232))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final FloatOption field15 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private List<SubtitleBridge> subtitles;
   private int field16;
   private Vec3Bridge field17;
   private Vec3Bridge field18;
   private Vec3Bridge field19;

   public AudioSubtitles() {
      super(false);
      this.method9(ModTraits.field18, arg0 -> arg0.method11(Config.field6));
      this.method9(ModTraits.field1, new AudioSubtitles.SubtitlesHudElement());
   }

   public String getId() {
      return "AUDIO_SUBTITLES";
   }

   public void method3(boolean flag1) {
      this.method13();
      if (flag1 && !Ref.method3().bridge$getGameSettings().bridge$isShowSubtitles()) {
         Ref.method4().method69().method7(NotificationType.WARNING, NotificationManager.method15("enableAudioSubtitles", new Object[0]));
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field11});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field9,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field10, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field15})
               )
            );
         }
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field12});
         arg1x.method9(new ClientOption[]{this.field13}).method1(new ClientOption[]{this.field9});
         arg1x.method9(new ClientOption[]{this.field14}).method1(new ClientOption[]{this.field10});
      });
   }

   public void method3(List<SubtitleBridge> list1, int number2, Vec3Bridge horsestats153, Vec3Bridge horsestats154, Vec3Bridge horsestats155) {
      this.subtitles = list1;
      this.field16 = number2;
      this.field17 = horsestats153;
      this.field18 = horsestats154;
      this.field19 = horsestats155;
   }

   private void method13() {
      this.subtitles = null;
      this.field16 = 0;
      this.field17 = this.field18 = this.field19 = null;
   }

   private static class Data implements SubtitleBridge {
      private final Component field1;

      public Data(String text1) {
         this.field1 = Component.text(text1);
      }

      public Component bridge$getText() {
         return this.field1;
      }

      public long bridge$getAliveTime() {
         return 0L;
      }

      @Nullable
      public Vec3Bridge bridge$getLocation() {
         return null;
      }
   }

   private class SubtitlesHudElement extends HudElementBase {
      public SubtitlesHudElement() {
         super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         List list5 = flag4 ? AudioSubtitles.this.field8 : AudioSubtitles.this.subtitles;
         this.method8(flag4 ? 80.0F : AudioSubtitles.this.field16, 9 * list5.size() + 0.5F);
         double value6 = Ref.method3().bridge$getGameSettings().bridge$getNotificationDisplayTime() * 3000.0;
         MixinHelper_4 mixinhelper_48 = highlightimpl1.method2();
         if ((Boolean)AudioSubtitles.this.field9.get()) {
            AudioSubtitles.this.field13.method11(mixinhelper_48, value2, value3, this.getWidth(), this.getHeight());
            if ((Boolean)AudioSubtitles.this.field10.get()) {
               AudioSubtitles.this.field14.method11(mixinhelper_48, this, value2, value3, this.getWidth(), this.getHeight(), (Float)AudioSubtitles.this.field15.get());
            }
         }

         mixinhelper_48.push();
         mixinhelper_48.method38(1.0F, 0.5F + this.getHeight(), 0.0F);
         float value9 = 0.0F;

         for (SubtitleBridge bridge4_1311 : list5) {
            double value12;
            double value14;
            if (!flag4) {
               Vec3Bridge horsestats1516 = bridge4_1311.bridge$getLocation();
               if (horsestats1516 == null) {
                  continue;
               }

               horsestats1516 = horsestats1516.bridge$subtract(AudioSubtitles.this.field17).bridge$normalize();
               value12 = -AudioSubtitles.this.field19.bridge$dotProduct(horsestats1516);
               value14 = -AudioSubtitles.this.field18.bridge$dotProduct(horsestats1516);
               if (Ref.MC_VERSION >= 20) {
                  value12 *= -1.0;
                  value14 *= -1.0;
               }
            } else {
               value14 = 1.0;
               value12 = 0.0;
            }

            float value21 = this.method2(bridge4_1311.bridge$getAliveTime() / value6);
            int number17 = ColorUtils.method34(AudioSubtitles.this.field12.method1(value9), value21);
            int number18 = 0xFF000000 | number17;
            value9 += 10.0F;
            mixinhelper_48.method38(0.0F, -9.0F, 0.0F);
            if (value14 <= 0.5) {
               if (value12 > 0.0) {
                  mixinhelper_48.method19(
                     Ref.method10(),
                     ">",
                     value2 + this.getWidth() - Ref.method10().bridge$getStringWidth(">") - 1.0F,
                     value3,
                     number18,
                     (Boolean)AudioSubtitles.this.field11.get()
                  );
               } else if (value12 < 0.0) {
                  mixinhelper_48.method19(Ref.method10(), "<", value2, value3, number18, (Boolean)AudioSubtitles.this.field11.get());
               }
            }

            Component component19 = bridge4_1311.bridge$getText();
            mixinhelper_48.method13(
               Ref.method10(),
               TextBridge.asBridge(component19),
               value2 + this.getWidth() / 2.0F - Ref.method10().bridge$getStringWidth(component19) / 2.0F,
               value3,
               number18,
               (Boolean)AudioSubtitles.this.field11.get()
            );
         }

         mixinhelper_48.pop();
      }

      private float method2(double value1) {
         return value1 < 0.0 ? 1.0F : (float)(value1 > 1.0 ? 0.29411764705882354 : 1.0 + value1 * -0.7058823529411765);
      }

      public void method18() {
         super.method18();
         this.method3(this.HHIHOHIIHCRIRIRRCCOHHRORHHCRIH());
      }

      public boolean method4(boolean flag1) {
         if (!Ref.method3().bridge$getGameSettings().bridge$isShowSubtitles()) {
            this.method8(0.0F, 0.0F);
            return false;
         } else {
            return flag1 || AudioSubtitles.this.subtitles != null && !AudioSubtitles.this.subtitles.isEmpty();
         }
      }

      public boolean method31() {
         return false;
      }
   }
}
