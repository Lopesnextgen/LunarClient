package com.moonsworth.lunar.client.mod.render.lighting;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.ui.notification.Notification;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.framework.Ref;

public class Lighting extends AbstractFeature {
   private final ToggleOption fullBright = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("fullBright")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption brightnessBoost = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("brightnessBoost")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ModifierKeybindOption fullBrightToggle = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "fullBrightToggle"
         )
         .method18(this))
      .method5(KeyCode.KEY_NONE)
      .method31();
   private boolean confirmingShaderWarning;

   public Lighting() {
      super(true);
      this.handle(EventTick.class, arg1 -> this.applyGammaOverride());
   }

   public String getId() {
      return "LIGHTING";
   }

   public boolean method13() {
      return Ref.MC_VERSION <= 5 && Ref.method4().method40().method92().isEnabled() ? false : this.isFullBrightActive();
   }

   public boolean isFullBrightActive() {
      return this.isEnabled() && (Boolean)this.fullBright.get();
   }

   private void applyGammaOverride() {
      if (this.isEnabled()) {
         if (this.fullBright != null && (Boolean)this.fullBright.get()) {
            this.mc.bridge$getGameSettings().bridge$setGammaOverride(100.0F);
         } else if (this.brightnessBoost != null) {
            this.mc.bridge$getGameSettings().bridge$setGammaOverride((Float)this.brightnessBoost.get());
         }
      }
   }

   public void method3(boolean flag1) {
      if (flag1) {
         boolean flag2 = Bridge.method5().map(arg0 -> arg0.getConfig().hasShaders()).orElse(false);
         boolean flag3 = ExternalLinkRegistry.method2(Fishing2Extension.class)
            .map(arg0 -> arg0.lunar$areShadersEnabledInConfig() && !"(off)".equals(arg0.lunar$getShaderPack()))
            .orElse(false);
         if (flag2 || flag3) {
            Ref.method3().bridge$displayScreen(Bridge.method8().method18(new ConfirmScreen("gui.lightingConfirm", arg2x -> {
               this.confirmingShaderWarning = true;
               if (!arg2x) {
                  ((ModEnabledState)this.method7(ModTraits.field6)).setEnabled(false);
               } else {
                  if (flag2) {
                     Bridge.method5().map(arg0 -> arg0.getShaders().setShaderPack("OFF"));
                  } else {
                     ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(arg0 -> arg0.lunar$toggleShaders(false));
                  }

                  this.applyGammaOverride();
               }

               Ref.method3().bridge$displayScreen(null);
            }) {
               public void close() {
                  super.close();
                  if (!Lighting.this.confirmingShaderWarning) {
                     ((ModEnabledState)Lighting.this.method7(ModTraits.field6)).setEnabled(false);
                  }

                  Lighting.this.confirmingShaderWarning = false;
               }
            }));
            return;
         }

         this.applyGammaOverride();
      } else {
         if (Ref.method8() != null && (Boolean)this.fullBright.get() && Bridge.getMinecraftVersion().method21()) {
            String text4 = Ref.method4().method67().method2("gui.lightingMod", "disable_in_world_warning", new Object[0]);
            Ref.method4().method69().method3(text4).method10(5000L);
         }

         this.mc.bridge$getGameSettings().bridge$removeGammaOverride();
      }
   }

   public void disableAutomatically() {
      RewindMod rewind1 = Ref.method4().method40().method85();
      if (!rewind1.method19()) {
         if (this.isEnabled()) {
            String text2 = Ref.method4().method67().method2("gui.lightingMod", "disabled_automatically", new Object[0]);
            Notification gui2iterator3 = Ref.method4().method69().method3(text2);
            if (gui2iterator3 != null) {
               gui2iterator3.method10(3000L);
            }
         }

         ((ModEnabledState)this.method7(ModTraits.field6)).setEnabled(false);
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "performanceOptions", arg1x -> arg1x.method9(new ClientOption[]{this.fullBright, this.fullBrightToggle})
      );
      lightingextension231.method1("brightnessOptions", arg1x -> arg1x.method9(new ClientOption[]{this.brightnessBoost}));
      this.fullBright
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg0 -> {
               if (!arg0
                  && Ref.method8() != null
                  && Bridge.getMinecraftVersion().method21()
                  && Ref.method4().method69() != null
                  && !Ref.method4().method40().method85().method19()) {
                  String text1x = Ref.method4().method67().method2("gui.lightingMod", "disable_full_bright_in_world_warning", new Object[0]);
                  Ref.method4().method69().method3(text1x).method10(5000L);
               }
            }
         );
      this.fullBright.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.applyGammaOverride());
      this.brightnessBoost.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.applyGammaOverride());
      this.fullBrightToggle.method3(() -> {
         this.fullBright.method12(!(Boolean)this.fullBright.get());
         this.applyGammaOverride();
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method2(new String[]{"brightness", "fps", "fullbright"}).method11(this);
   }
}
