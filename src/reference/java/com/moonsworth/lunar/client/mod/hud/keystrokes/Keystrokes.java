package com.moonsworth.lunar.client.mod.hud.keystrokes;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.ui.menu.ModMenuWidget;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.PageState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeAnimationType;
import com.moonsworth.lunar.client.framework.feature.keystrokes.EasingFunction;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeAnimationStyle;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeTimerType;
import com.moonsworth.lunar.client.framework.listener.CpsListener;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.LongOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Generated;

public class Keystrokes extends AbstractFeature {
   private final CpsListener cpsListener = (CpsListener)this.method29(CpsListener.class);
   public static final float field9 = 0.0F;
   public static final float field10 = 0.0F;
   public final EnumOption<Keystrokes.Type> keystrokesMode = (EnumOption<Keystrokes.Type>)OptionFactory.method10(
         "keystrokesMode", Keystrokes.Type.GROUPED
      )
      .method31();
   public final ToggleOption keyStrokesClicks = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keyStrokesClicks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption leftCps = (ToggleOption)OptionFactory.method7("leftCPS").method31();
   public final ToggleOption rightCps = (ToggleOption)OptionFactory.method7("rightCPS").method31();
   public final ToggleOption keyStrokesMovement = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keyStrokesMovement").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption keyStrokesSpacebar = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keyStrokesSpacebar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption useArrows = (ToggleOption)OptionFactory.method7("useArrows").method31();
   public final ToggleOption textShadow = (ToggleOption)OptionFactory.method7("textShadow").method31();
   public final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   public final ToggleOption innerBorder = (ToggleOption)OptionFactory.method7("innerBorder").method31();
   public final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   public final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   public final ColorOption textPressedColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textPressedColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
      .method31();
   public final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   public final ColorOption backgroundPressedColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundPressedColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .method31();
   public final FloatOption boxSize = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "boxSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(18.0F))
         .method8(10.0F, 32.0F))
      .method31();
   public final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 3.0F))
      .method31();
   public final LongOption keyFadeDelay = (LongOption)((com.moonsworth.lunar.client.config.option.LongOption.Data)((com.moonsworth.lunar.client.config.option.LongOption.Data)OptionFactory.method3(
               "keyFadeDelay"
            )
            .HRHCHRICROCCHOHOROROIRIICHCRHH(75L))
         .method7(0L, 500L))
      .method31();
   public final FloatOption spacebarThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "spacebarThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 4.25F))
      .method31();
   public final ToggleOption animate = (ToggleOption)OptionFactory.method7("animate").method31();
   public final EnumOption<KeystrokeAnimationType> animationType = (EnumOption<KeystrokeAnimationType>)OptionFactory.method10("animationType", KeystrokeAnimationType.STACKED)
      .method31();
   public final EnumOption<KeystrokeTimerType> timerType = (EnumOption<KeystrokeTimerType>)OptionFactory.method10("timerType", KeystrokeTimerType.HALF)
      .method31();
   public final EnumOption<KeystrokeAnimationStyle> animation = (EnumOption<KeystrokeAnimationStyle>)OptionFactory.method10("animation", KeystrokeAnimationStyle.RIPPLE)
      .method31();
   public final ToggleOption animateColor = (ToggleOption)OptionFactory.method7("animateColor").method31();
   public final ColorOption animationStartColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "animationStartColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1056964609))
      .method31();
   public final ColorOption animationCenterColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "animationCenterColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .method31();
   public final ColorOption animationEndColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "animationEndColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .method31();
   public final FloatOption duration = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "duration"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.1F, 1.0F))
      .method31();
   public final EnumOption<EasingFunction> timingFunction = (EnumOption<EasingFunction>)OptionFactory.method10("timingFunction", EasingFunction.LINEAR)
      .method31();
   private final com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeLayout layout = new com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeLayout(
      this
   );
   private final List<CustomKeystrokeKey> customKeys = new ArrayList<>();

   public Keystrokes() {
      super(false);
      this.render(ModTraits.field1, new Keystrokes.Data());
      this.render(ModTraits.field11, PageState.method3().method2(false));
      this.keystrokesMode.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1 -> this.refreshLayout());
   }

   public String getId() {
      return "KEYSTROKES";
   }

   protected List<Framework7Extension> getChildMods() {
      ArrayList list1 = new ArrayList();
      list1.addAll(this.layout.method9());
      list1.addAll(this.customKeys);
      return list1;
   }

   public void reset() {
      super.reset();
      this.customKeys.clear();
      this.refreshLayout();
   }

   protected void render(CustomKeystrokeKey customkeystrokekey1) {
      this.customKeys.remove(customkeystrokekey1);
      this.refreshLayout();
   }

   private void refreshLayout() {
      this.render(ModTraits.field5).ifPresent(arg0 -> arg0.getChildren().clear());
      this.method5();
      LcuiScreen.method145();
      if (Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension621) {
         Bridge7_8 bridge7_86 = bridge5extension621.method2();
         if (bridge7_86 instanceof MainMenuButton bridge7task3 && bridge7task3.method17() instanceof Bridge7_8 bridge7_84) {
            bridge7_86 = bridge7_84;
         }

         if (bridge7_86 instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen bridge7iterator7
            && bridge7iterator7.method10().method17() instanceof FeatureSettingsWidget calculator2iterator38
            && calculator2iterator38.getFeature() instanceof Keystrokes) {
            FeatureSettingsWidget calculator2iterator310 = new FeatureSettingsWidget((ModMenuWidget)bridge7iterator7.method10().method17().method12().orElse(null), this);
            bridge7iterator7.method10().method2(calculator2iterator310);
         }
      }
   }

   public void load(JsonObject json1) {
      this.layout.init();
      this.customKeys.clear();
      if (json1 != null && !json1.isJsonNull()) {
         JsonObject json2 = json1.getAsJsonObject();

         for (String text4 : json2.keySet()) {
            if (text4.endsWith("_CUSTOM")) {
               try {
                  int number5 = Integer.parseInt(text4.replaceAll("[^0-9]", ""));
                  this.customKeys.add(new CustomKeystrokeKey(this, number5));
               } catch (Exception exception6) {
                  CrashReporter.method5(exception6, "Loading custom keystrokes key " + text4);
               }
            }
         }
      }

      this.refreshLayout();
      super.load(json1);
      this.layout
         .method3(
            this.layout.method9().stream().filter(com.moonsworth.lunar.client.framework.feature.keystrokes.DefaultKeystrokeKey::method28).toList()
         );
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.keystrokesMode});
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{((MixinCore9Extension)this.copyOptionsToOthers(ModTraits.field1)).method9()})
               .method3(() -> this.keystrokesMode.get() != Keystrokes.Type.GROUPED);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.keyStrokesClicks, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.leftCps, this.rightCps})
               )
               .method3(() -> this.keystrokesMode.get() != Keystrokes.Type.GROUPED);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.useArrows});
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.keyStrokesMovement, this.keyStrokesSpacebar, this.textShadow})
               .method3(() -> this.keystrokesMode.get() != Keystrokes.Type.GROUPED);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.border, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.innerBorder}))
               .method3(() -> this.keystrokesMode.get() != Keystrokes.Type.GROUPED);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.animate,
                  arg1xx -> {
                     arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("applyToAll").method4(() -> this.copyOptionsToOthers(this))});
                     arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.animationType, this.timerType, this.animation, this.duration, this.timingFunction});
                     arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                        this.animateColor, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.animationStartColor, this.animationCenterColor, this.animationEndColor})
                     );
                  }
               )
               .method3(() -> this.keystrokesMode.get() != Keystrokes.Type.GROUPED);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{this.borderThickness, this.boxSize, this.borderColor, this.textColor, this.textPressedColor, this.backgroundColor, this.backgroundPressedColor}
               )
               .method3(() -> this.keystrokesMode.get() != Keystrokes.Type.GROUPED);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.keyFadeDelay}).method3(this.animate::get);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.spacebarThickness});
            arg1x.method13("copyOptions");
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionProvider[]{
                  OptionFactory.method14("copyOptions")
                     .method4(
                        () -> this.render(ModTraits.field5)
                           .ifPresent(
                              arg1xx -> arg1xx.getChildren()
                                 .stream()
                                 .map(arg0 -> (com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeKey)arg0)
                                 .forEach(this::copyOptionsTo)
                           )
                     )
               }
            );
         }
      );
      lightingextension231.method13();
      ((SettingsSectionImpl)lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("addCustomKey").method4(() -> {
         int number1x = this.customKeys.stream().mapToInt(CustomKeystrokeKey::getKeyIndex).max().orElse(0) + 1;
         this.customKeys.add(new CustomKeystrokeKey(this, number1x));
         this.refreshLayout();
      })})).method2(() -> this.keystrokesMode.get() != Keystrokes.Type.INDIVIDUAL);
   }

   public void copyOptionsToOthers(Framework7Extension framework7extension1) {
      List list2 = Arrays.asList(
         this.animate.getId(),
         this.animationType.getId(),
         this.timerType.getId(),
         this.animation.getId(),
         this.animateColor.getId(),
         this.animationStartColor.getId(),
         this.animationCenterColor.getId(),
         this.animationEndColor.getId(),
         this.duration.getId(),
         this.timingFunction.getId()
      );
      this.copyOptions(framework7extension1, list2);
   }

   public void copyOptions(Framework7Extension framework7extension1, List<String> list2) {
      ModChildren alertextension3 = (ModChildren)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension3 != null) {
         for (Framework7Extension framework7extension5 : alertextension3.getChildren()) {
            if (framework7extension5 != framework7extension1) {
               for (String text7 : list2) {
                  OptionContainer framework58 = (OptionContainer)framework7extension5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
                  OptionContainer framework59 = (OptionContainer)framework7extension5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
                  if (framework58 != null && framework59 != null) {
                     Optional optional10 = framework58.method2().stream().filter(arg1x -> arg1x.getId().equals(text7)).findAny();
                     Optional optional11 = framework59.method2().stream().filter(arg1x -> arg1x.getId().equals(text7)).findAny();
                     if (optional10.isPresent() && optional11.isPresent()) {
                        ClientOption lightingextension12 = (ClientOption)optional10.get();
                        ClientOption lightingextension13 = (ClientOption)optional11.get();
                        lightingextension12.method13(lightingextension13.get());
                        if (Ref.method8() != null) {
                           Ref.method4().method41().method2(lightingextension12);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void copyOptionsTo(com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeKey framework7extension21) {
      if (framework7extension21 instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.DefaultKeystrokeKey framework7extension22) {
         if (framework7extension22.method25()) {
            framework7extension21.method13(ModTraits.field6).<ClientOption>flatMap(ModEnabledState::method1).ifPresent(arg1x -> arg1x.method10((Boolean)this.keyStrokesMovement.get()));
         }

         if (framework7extension22.method24()) {
            framework7extension21.method13(ModTraits.field6).<ClientOption>flatMap(ModEnabledState::method1).ifPresent(arg1x -> arg1x.method10((Boolean)this.keyStrokesClicks.get()));
         }

         if (framework7extension22.method27() == KeyCode.KEY_SPACE) {
            framework7extension21.method13(ModTraits.field6).<ClientOption>flatMap(ModEnabledState::method1).ifPresent(arg1x -> arg1x.method10((Boolean)this.keyStrokesSpacebar.get()));
         }

         if (framework7extension22.method27() == KeyCode.KEY_MOUSE1) {
            framework7extension21.field8.OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)this.leftCps.get());
         } else if (framework7extension22.method27() == KeyCode.KEY_MOUSE2) {
            framework7extension21.field8.OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)this.rightCps.get());
         }
      }

      framework7extension21.field9.OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)this.textShadow.get());
      framework7extension21.field10.OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)this.border.get());
      framework7extension21.field11.method11(this.borderColor);
      framework7extension21.field12.method11(this.textColor);
      framework7extension21.field13.method11(this.textPressedColor);
      framework7extension21.field14.method11(this.backgroundColor);
      framework7extension21.field15.method11(this.backgroundPressedColor);
      framework7extension21.field17.method1((Float)this.borderThickness.get());
      framework7extension21.field18.method1((Long)this.keyFadeDelay.get());
      framework7extension21.field19.OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)this.animate.get());
      framework7extension21.field20.OIRHOOIICOCIOOHICRRRICORIHHIHC((KeystrokeAnimationType)this.animationType.get());
      framework7extension21.field21.OIRHOOIICOCIOOHICRRRICORIHHIHC((KeystrokeTimerType)this.timerType.get());
      framework7extension21.field22.OIRHOOIICOCIOOHICRRRICORIHHIHC((KeystrokeAnimationStyle)this.animation.get());
      framework7extension21.field23.OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)this.animateColor.get());
      framework7extension21.field24.method11(this.animationStartColor);
      framework7extension21.field25.method11(this.animationCenterColor);
      framework7extension21.field26.method11(this.animationEndColor);
      framework7extension21.field27.method1((Float)this.duration.get());
      framework7extension21.field28.OIRHOOIICOCIOOHICRRRICORIHHIHC((EasingFunction)this.timingFunction.get());
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT);
      }

      public boolean method31() {
         return false;
      }

      public void registerOptions(RootSettingsBuilder lightingextension231) {
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         mixinhelper_45.push();
         mixinhelper_45.method38(value2, value3, 0.0F);
         Keystrokes.this.layout.method8(mixinhelper_45, value2 + value3);
         mixinhelper_45.pop();
         this.method58(Keystrokes.this.layout.method10(), Keystrokes.this.layout.method11());
      }

      public boolean reset(boolean flag1) {
         if (Keystrokes.this.keystrokesMode.get() == Keystrokes.Type.INDIVIDUAL) {
            this.method58(0.0F, 0.0F);
            return false;
         } else {
            return (Boolean)Keystrokes.this.keyStrokesMovement.get() || (Boolean)Keystrokes.this.keyStrokesClicks.get() || (Boolean)Keystrokes.this.keyStrokesSpacebar.get();
         }
      }

      public void resetPosition() {
         this.registerOptions(com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT);
         super.resetPosition();
      }
   }

   public enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      GROUPED("grouped"),
      INDIVIDUAL("individual");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method16(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
