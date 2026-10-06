package com.moonsworth.lunar.client.mod.render.fov;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.bridge.optifine.OptifineConfigBridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.mixin.highlight.EventFovRender;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class Fov extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("staticFOV")
      .method31();
   private final IntegerOption field9 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("defaultFov")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(70))
         .method7(30, 110))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("smoothFov")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dynamicFlying")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field12 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("flyingFov")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(70))
         .method7(30, 110))
      .method31();
   private final FloatOption field13 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "flyingModifier"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 5.0F))
         .method6(2))
      .method31();
   private final FloatOption field14 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "flyingMin"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final FloatOption field15 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "flyingMax"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dynamicEffects")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field17 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("slownessFOV")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(70))
         .method7(30, 110))
      .method31();
   private final IntegerOption field18 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("speedFOV")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(70))
         .method7(30, 110))
      .method31();
   private final IntegerOption field19 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("speedTwoFOV")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(70))
         .method7(30, 110))
      .method31();
   private final FloatOption field20 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "movementModifier"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 5.0F))
         .method6(2))
      .method31();
   private final FloatOption field21 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "movementMin"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final FloatOption field22 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "movementMax"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dynamicSprint")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field24 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("sprintingFOV")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(70))
         .method7(30, 110))
      .method31();
   private final FloatOption field25 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "sprintModifier"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 5.0F))
         .method6(2))
      .method31();
   private final FloatOption field26 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "sprintMin"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final FloatOption field27 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "sprintMax"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dynamicBow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field29 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "aimingModifier"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 5.0F))
         .method6(2))
      .method31();
   private final FloatOption field30 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "aimingMin"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private final FloatOption field31 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "aimingMax"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(10.0F))
            .method8(-200.0F, 200.0F))
         .method6(2))
      .method31();
   private long lastUpdate = -1L;
   private float field32 = 70.0F;
   private float field33;

   public Fov() {
      super(false);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.FovInput.class, this::method2);
      this.handle(EventFovRender.class, this::method1);
   }

   public String getId() {
      return "FOV";
   }

   private void method1(EventFovRender highlightimpl211) {
      if (this.method13()) {
         highlightimpl211.setCancelled(true);
      } else {
         highlightimpl211.method2(this.method14());
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.FovInput data1) {
      float value2 = data1.IOIIOIOHRHCRHRCHOHHIOCCICCIIOH();
      if (this.method13()) {
         data1.method5(value2 = ((Integer)this.field9.get()).intValue());
      } else {
         boolean flag3 = false;
         Bridge5Extension_5 bridge5extension_54 = Ref.method7();
         if (bridge5extension_54 != null) {
            if (bridge5extension_54.bridge$isSprinting() && (Boolean)this.field23.get()) {
               value2 = ((Integer)this.field24.get()).intValue();
               flag3 = true;
            }

            if (bridge5extension_54.bridge$getPlayerCapabilities().bridge$isFlying() && (Boolean)this.field11.get()) {
               value2 = ((Integer)this.field12.get()).intValue();
               flag3 = true;
            }

            if (bridge5extension_54.bridge$isPotionActive(Bridge.method36().method1()) && (Boolean)this.field16.get()) {
               value2 = ((Integer)this.field17.get()).intValue();
               flag3 = true;
            }

            if (bridge5extension_54.bridge$isPotionActive(Bridge.method36().method2()) && (Boolean)this.field16.get()) {
               int number5 = bridge5extension_54.bridge$getActivePotionEffect(Bridge.method36().method2()).bridge$getAmplifier();
               value2 = ((Integer)(number5 == 0 ? this.field18 : this.field19).get()).intValue();
               flag3 = true;
            }
         }

         if (!flag3) {
            value2 = ((Integer)this.field9.get()).intValue();
         }
      }

      if (this.field33 != value2) {
         this.field33 = value2;
         this.lastUpdate = -1L;
      }

      if ((Boolean)this.field10.get() && this.field32 != this.field33) {
         long number7 = Ref.method3().bridge$getSystemTime();
         long number8 = this.lastUpdate == -1L ? 1L : Math.max(1L, number7 - this.lastUpdate);
         if (Ref.method4().method40().method85().method17(arg0 -> arg0.method40().method37().isPaused())) {
            number8 = 0L;
         }

         this.lastUpdate = number7;
         this.field32 = this.field32 + (this.field33 - this.field32) * MathUtils.method1(0.005F * (float)number8, 0.0F, 1.0F);
         if (Math.abs(this.field33 - this.field32) < 1.0E-4F) {
            this.field32 = this.field33;
         }
      } else {
         this.field32 = this.field33;
      }

      data1.method5(this.field32);
   }

   private boolean method13() {
      return (Boolean)this.field8.get() || !Bridge.method5().map(OptifineBridge::getConfig).<Boolean>map(OptifineConfigBridge::hasDynamicFov).orElse(true);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field8, this.field9});
         arg1x.method9(new ClientOption[]{this.field10}).method3(this.field8::get);
      });
      ((SettingsSectionImpl)lightingextension231.method7(
            this.field28, arg1x -> arg1x.method9(new ClientOption[]{this.field29, this.field30, this.field31})
         ))
         .method2(this.field8::get);
      ((SettingsSectionImpl)lightingextension231.method7(
            this.field16,
            arg1x -> arg1x.method9(
               new ClientOption[]{this.field18, this.field19, this.field17, this.field20, this.field21, this.field22}
            )
         ))
         .method2(this.field8::get);
      ((SettingsSectionImpl)lightingextension231.method7(
            this.field23, arg1x -> arg1x.method9(new ClientOption[]{this.field24, this.field25, this.field26, this.field27})
         ))
         .method2(this.field8::get);
      ((SettingsSectionImpl)lightingextension231.method7(
            this.field11, arg1x -> arg1x.method9(new ClientOption[]{this.field12, this.field13, this.field14, this.field15})
         ))
         .method2(this.field8::get);
   }

   private float method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return 1.0F;
      }

      boolean flag2 = bridge5extension_51.bridge$getPlayerCapabilities().bridge$isFlying();
      boolean flag3 = bridge5extension_51.bridge$isSprinting();
      double value4 = bridge5extension_51.bridge$getMovementSpeedAttribute();
      float value6 = bridge5extension_51.bridge$getPlayerCapabilities().bridge$getWalkSpeed();
      float value7 = 1.0F;
      if (flag2 && (Boolean)this.field11.get()) {
         value7 *= 1.0F + MathUtils.method3(0.1F * (Float)this.field13.get(), (Float)this.field14.get(), (Float)this.field15.get());
      }

      float value8 = (float)((value4 / value6 + 1.0) / 2.0);
      float value9 = (float)value4;
      float value10 = Math.abs((value9 / (flag3 ? 1.3F : 1.0F) - value6) / (value9 - value6));
      double value11 = !this.field16.get()
         ? 0.0
         : MathUtils.method3(value10 * (value8 - 1.0F) * (Float)this.field20.get(), (Float)this.field21.get(), (Float)this.field22.get());
      if (flag3 && (Boolean)this.field23.get()) {
         value11 += MathUtils.method3((1.0F - value10) * (value8 - 1.0F) * (Float)this.field25.get(), (Float)this.field26.get(), (Float)this.field27.get());
      }

      value7 = (float)(value7 * (1.0 + value11));
      if (value6 == 0.0F || Float.isNaN(value7) || Float.isInfinite(value7)) {
         value7 = 1.0F;
      }

      if ((Boolean)this.field28.get()) {
         ItemStackBridge bridgeextension_413 = !bridge5extension_51.bridge$isUsingItem() ? null : (ItemStackBridge)bridge5extension_51.bridge$getItemInUse().orElse(null);
         if (bridgeextension_413 != null && bridgeextension_413.bridge$getItem() == Bridge.method28().method8()) {
            float value14 = bridge5extension_51.bridge$getItemInUseDuration() / 20.0F;
            if (value14 > 1.0F) {
               value14 = 1.0F;
            } else {
               value14 *= value14;
            }

            value7 *= 1.0F - MathUtils.method3(value14 * 0.15F * (Float)this.field29.get(), (Float)this.field30.get(), (Float)this.field31.get());
         }
      }

      return value7;
   }
}
