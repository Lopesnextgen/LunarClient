package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.chat.translation.SharedTranslations;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.ColorTransition;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.PanelPosition;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.config.override.SettingOverrideInterceptor;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.LongOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes.Type;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

public abstract class KeystrokeKey extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   protected final ToggleOption field8 = (ToggleOption)OptionFactory.method7("showCps").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field9 = (ToggleOption)OptionFactory.method7("textShadow").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field10 = (ToggleOption)OptionFactory.method7("border").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption keystrokesMode = (ColorOption)((Data)OptionFactory.method8("borderColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("textColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field13 = (ColorOption)((Data)OptionFactory.method8("textPressedColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field14 = (ColorOption)((Data)OptionFactory.method8("backgroundColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field15 = (ColorOption)((Data)OptionFactory.method8("backgroundPressedColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "boxSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(18.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(10.0F, 32.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final FloatOption field17 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 3.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LongOption field18 = (LongOption)((com.moonsworth.lunar.client.config.option.LongOption.Data)((com.moonsworth.lunar.client.config.option.LongOption.Data)OptionFactory.method3(
               "keyFadeDelay"
            )
            .HRHCHRICROCCHOHOROROIRIICHCRHH(75L))
         .HRICOROOOCCOCOROCRHHCRRIRCOICO(0L, 500L))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("animate").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final EnumOption<KeystrokeAnimationType> field20 = (EnumOption<KeystrokeAnimationType>)OptionFactory.method10("animationType", KeystrokeAnimationType.STACKED)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final EnumOption<KeystrokeTimerType> field21 = (EnumOption<KeystrokeTimerType>)OptionFactory.method10("timerType", KeystrokeTimerType.HALF)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final EnumOption<KeystrokeAnimationStyle> field22 = (EnumOption<KeystrokeAnimationStyle>)OptionFactory.method10("animation", KeystrokeAnimationStyle.RIPPLE)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final ToggleOption field23 = (ToggleOption)OptionFactory.method7("animateColor").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final ColorOption field24 = (ColorOption)((Data)OptionFactory.method8("animationStartColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1056964609))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final ColorOption field25 = (ColorOption)((Data)OptionFactory.method8("animationCenterColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final ColorOption field26 = (ColorOption)((Data)OptionFactory.method8("animationEndColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final FloatOption field27 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "duration"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.1F, 1.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final EnumOption<EasingFunction> field28 = (EnumOption<EasingFunction>)OptionFactory.method10("timingFunction", EasingFunction.LINEAR)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public KeystrokeKey(Framework7Extension framework7extension1, boolean flag2) {
      super(flag2);
      this.method14(ModTraits.field16, ChildModBinding.method5(() -> ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)framework7extension1).keystrokesMode.get() == Type.INDIVIDUAL, framework7extension1));
      this.method14(ModTraits.field1, new KeystrokeKey.KeystrokeKeyElement());
   }

   @Override
   public void method1(boolean flag1) {
      this.method14(ModTraits.field6, ModEnabledState.method6(flag1));
      this.method14(ModTraits.field4, new SettingOverrideInterceptor());
      this.method14(ModTraits.field9, ModSearchIndex.method7());
      this.method14(ModTraits.field10, PanelPosition.method9());
   }

   protected String method13() {
      KeyBindingBridge mixinhelper_151 = this.method15();
      return mixinhelper_151 == null ? "NONE" : mixinhelper_151.bridge$getUntranslatedKeyDescription();
   }

   protected abstract String method14();

   @Nullable
   protected abstract KeyBindingBridge method15();

   protected abstract int method16();

   protected abstract void method17();

   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10});
      lightingextension231.method7(
         this.field19,
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionProvider[]{
                  OptionFactory.method14("applyToAll")
                     .method4(
                        () -> ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1())
                           .method6(this)
                     )
               }
            );
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field20, this.field21, this.field22, this.field27, this.field28});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field23, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field24, this.field25, this.field26})
            );
         }
      );
      lightingextension231.method9(
         new ClientOption[]{this.field17, this.field16, this.keystrokesMode, this.field12, this.field13, this.field14, this.field15}
      );
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field18})).method2(this.field19::get);
      lightingextension231.method9(
         new OptionProvider[]{
            OptionFactory.method14("copyOptions")
               .method4(
                  () -> ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1())
                     .method8(this)
               )
         }
      );
      lightingextension231.method9(
         new OptionProvider[]{
            OptionFactory.method14("applyToAll")
               .method4(
                  () -> {
                     OptionContainer framework51x = (OptionContainer)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field14);
                     ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1())
                        .method7(this, framework51x.method2().stream().map(ClientOption::getId).toList());
                  }
               )
         }
      );
   }

   @Override
   public boolean isEnabled() {
      this.updateEnabled();
      return super.isEnabled();
   }

   public class KeystrokeKeyElement extends HudElementBase {
      private boolean field9 = false;
      private SyncedKeystrokeTimer field10;
      private KeystrokeRenderer keystrokesMode;
      private final List<KeystrokeAnimation> field12 = new LinkedList<>();
      private final ColorTransition field13 = new ColorTransition(0L, KeystrokeKey.this.field14, KeystrokeKey.this.field15);

      public KeystrokeKeyElement() {
         super(0.0F, 0.0F, com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT);
      }

      @Override
      public boolean method31() {
         return false;
      }

      private void method2(boolean flag1) {
         if ((Boolean)KeystrokeKey.this.field19.get()) {
            if (KeystrokeKey.this.field20.get() == KeystrokeAnimationType.SYNCED) {
               if (this.field10 == null || this.field10.isDone()) {
                  this.field10 = new SyncedKeystrokeTimer(((Float)KeystrokeKey.this.field27.get()).floatValue());
                  this.keystrokesMode = ((KeystrokeAnimationStyle)KeystrokeKey.this.field22.get()).create();
               }

               if (flag1) {
                  this.field10.method3();
               } else {
                  this.field10.method4();
               }
            } else if (flag1) {
               KeystrokeTimer keystrokes22 = ((KeystrokeTimerType)KeystrokeKey.this.field21.get())
                  .create(((Float)KeystrokeKey.this.field27.get()).floatValue());
               this.field12
                  .add(
                     new KeystrokeAnimation(
                        keystrokes22, ((KeystrokeAnimationStyle)KeystrokeKey.this.field22.get()).create(), (EasingFunction)KeystrokeKey.this.field28.get()
                     )
                  );
            }
         }
      }

      private int method3(float value1) {
         if (!(Boolean)KeystrokeKey.this.field23.get()) {
            return KeystrokeKey.this.field15.method14(this.getX() + this.getY());
         } else {
            return value1 < 1.0F
               ? this.method4(
                  KeystrokeKey.this.field24.method14(this.getX() + this.getY()),
                  KeystrokeKey.this.field25.method14(this.getX() + this.getY()),
                  MathUtils.method1(value1, 0.0F, 1.0F)
               )
               : this.method4(
                  KeystrokeKey.this.field25.method14(this.getX() + this.getY()),
                  KeystrokeKey.this.field26.method14(this.getX() + this.getY()),
                  MathUtils.method1(value1 - 1.0F, 0.0F, 1.0F)
               );
         }
      }

      private int method4(int number1, int number2, float value3) {
         int number4 = number1 >> 24 & 0xFF;
         int number5 = number1 >> 16 & 0xFF;
         int number6 = number1 >> 8 & 0xFF;
         int number7 = number1 & 0xFF;
         int number8 = number2 >> 24 & 0xFF;
         int number9 = number2 >> 16 & 0xFF;
         int number10 = number2 >> 8 & 0xFF;
         int number11 = number2 & 0xFF;
         float[] items12 = Color.RGBtoHSB(number5, number6, number7, null);
         float[] items13 = Color.RGBtoHSB(number9, number10, number11, null);
         float value14 = items12[0];
         float value15 = items13[0];
         if (value15 - value14 > 0.5F) {
            value14++;
         } else if (value15 - value14 < -0.5F) {
            value15++;
         }

         float value16 = (value14 + (value15 - value14) * value3) % 1.0F;
         float value17 = items12[1] + (items13[1] - items12[1]) * value3;
         float value18 = items12[2] + (items13[2] - items12[2]) * value3;
         int number19 = Color.HSBtoRGB(value16, value17, value18);
         int number20 = Math.round(number4 + (number8 - number4) * value3);
         int number21 = number19 >> 16 & 0xFF;
         int number22 = number19 >> 8 & 0xFF;
         int number23 = number19 & 0xFF;
         return number20 << 24 | number21 << 16 | number22 << 8 | number23;
      }

      private void method5(MixinHelper_4 mixinhelper_41) {
         mixinhelper_41.push();
         Iterator iterator2 = this.field12.iterator();

         while (iterator2.hasNext()) {
            KeystrokeAnimation keystrokes3 = (KeystrokeAnimation)iterator2.next();
            if (keystrokes3.method2().isDone()) {
               iterator2.remove();
            } else {
               keystrokes3.method1(this, this.method3(keystrokes3.method2().method2()), mixinhelper_41);
            }
         }

         if (KeystrokeKey.this.field20.get() == KeystrokeAnimationType.SYNCED && this.field10 != null && this.keystrokesMode != null) {
            float value4 = MathUtils.method1(this.field10.method1(), 0.0F, 1.0F);
            if (value4 != 0.0F) {
               this.keystrokesMode.method1(this, value4, this.method3(this.field10.method2()), mixinhelper_41);
            }
         }

         mixinhelper_41.pop();
      }

      public void method6(MixinHelper_4 mixinhelper_41, float value2) {
         KeyCode bridgetype_83 = KeystrokeKey.this.method15() == null ? KeyCode.KEY_NONE : KeystrokeKey.this.method15().bridge$getKey();
         com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes keystrokes4 = ((ChildModBinding)KeystrokeKey.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16))
            .method1();
         boolean flag5 = KeystrokeKey.this.method15() != null
            && keystrokes4.method33()
               .method1(KeystrokeKey.this.method13(), Bridge.method18().method1(bridgetype_83) && Ref.method3().bridge$getCurrentScreen() == null);
         if (this.field9 != flag5) {
            this.field9 = flag5;
            this.method2(flag5);
         }

         if ((Boolean)KeystrokeKey.this.field19.get()) {
            KeystrokeKey.this.field14.method11(mixinhelper_41, 0.0F, 0.0F, this.getWidth(), this.getHeight());
         } else {
            this.field13.setDurationMs((Long)KeystrokeKey.this.field18.get());
            LcuiScreen.method94(mixinhelper_41, 0.0F, 0.0F, this.getWidth(), this.getHeight(), this.field13.method2(flag5, value2));
         }

         this.method5(mixinhelper_41);
         ColorOption lightingextension42226 = flag5 ? KeystrokeKey.this.field13 : KeystrokeKey.this.field12;
         if (KeystrokeKey.this instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.DefaultKeystrokeKey
            && bridgetype_83 == KeystrokeKey.this.mc.bridge$getGameSettings().bridge$keyBindJump().bridge$getKey()) {
            float value12 = (Float)((ChildModBinding)KeystrokeKey.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1().field29.get();
            lightingextension42226.method11(
               mixinhelper_41, this.getWidth() / 2.0F - this.getWidth() / 6.0F, 3.0F, this.getWidth() / 3.0F, value12, (Boolean)KeystrokeKey.this.field9.get()
            );
         } else {
            float value7 = Ref.method10().method19();
            float value8 = this.getWidth() / 2.0F;
            float value13;
            if ((Boolean)KeystrokeKey.this.field8.get()) {
               value13 = this.getHeight() - value7 + 2.0F;
               float value10 = 0.6F;
               mixinhelper_41.push();
               mixinhelper_41.scale(value10, value10, 1.0F);
               String text11 = KeystrokeKey.this.method16() + " " + SharedTranslations.field1;
               mixinhelper_41.method44(arg0 -> arg0.method29().method14());
               mixinhelper_41.method29(
                  Ref.method10(),
                  text11,
                  value8 / value10,
                  value13 / value10,
                  ColorUtils.method14(lightingextension42226.method14(value8 + value13), 4),
                  (Boolean)KeystrokeKey.this.field9.get()
               );
               mixinhelper_41.pop();
               value13 = this.getHeight() / 4.0F - value7 / 4.0F;
            } else {
               value13 = this.getHeight() / 2.0F - value7 / 2.0F + 1.0F;
            }

            String text14 = KeystrokeKey.this.method14();
            if ((Float)KeystrokeKey.this.field16.get() < 14.0F
               && KeystrokeKey.this instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.DefaultKeystrokeKey) {
               text14 = text14.substring(0, 1);
            }

            mixinhelper_41.method44(arg0 -> arg0.method29().method14());
            lightingextension42226.method11(mixinhelper_41, text14, value8, value13, (Boolean)KeystrokeKey.this.field9.get());
         }

         if ((Boolean)KeystrokeKey.this.field10.get()) {
            KeystrokeKey.this.keystrokesMode
               .method11(mixinhelper_41, this, 0.0F, 0.0F, this.getWidth(), this.getHeight(), (Float)KeystrokeKey.this.field17.get());
         }
      }

      @Override
      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         KeystrokeKey.this.method17();
         highlightimpl1.method2().push();
         highlightimpl1.method2().method38(value2, value3, 0.0F);
         this.method6(highlightimpl1.method2(), value2 + value3);
         highlightimpl1.method2().pop();
      }

      @Override
      public boolean method4(boolean flag1) {
         if (!KeystrokeKey.this.isEnabled()) {
            this.method58(0.0F, 0.0F);
            return false;
         } else if (((ChildModBinding)KeystrokeKey.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1().keystrokesMode.get() == Type.GROUPED) {
            this.method58(0.0F, 0.0F);
            return false;
         } else {
            return true;
         }
      }

      @Override
      public boolean method30() {
         return this.method4(false);
      }

      @Override
      public void method18() {
         this.method6(com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT);
         this.ICHRCHCIHROCHRCIRCCCCHCRCCCHOH().reset();
         KeystrokeKey.this.field16.reset();
         if (KeystrokeKey.this instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.DefaultKeystrokeKey framework7extension21) {
            framework7extension21.method23();
         } else {
            super.method18();
         }
      }
   }
}
