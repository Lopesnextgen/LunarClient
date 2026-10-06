package com.moonsworth.lunar.client.mod.render.particlechanger;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.widget.ApplyToAllNumberWidget;
import com.moonsworth.lunar.client.ui.widget.ApplyToAllInfoWidget;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework6;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.render.Gui2Extension;
import com.moonsworth.lunar.client.event.combat.PreAttackEntityEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ParticleRenderEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nullable;
import lombok.Generated;

public class ParticleChanger extends AbstractFeature {
   private final EnumMap<HorsestatsType2, ParticleStyle> children = new EnumMap<>(HorsestatsType2.class);
   private final ParticleChangerBlood bloodChild = ParticleChangerBlood.create(this);
   private final ToggleOption alwaysEnchantStrikes = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("alwaysEnchantStrikes")
      .method31();
   private final ToggleOption hideFirstPersonParticles = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideFirstPersonParticles")
      .method31();
   private final ToggleOption hideBlockBreakParticles = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideBlockBreakParticles")
      .method31();
   private final ToggleOption hideAllParticles = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideAllParticles")
      .method31();

   public ParticleChanger() {
      super(false);
      this.handle(ParticleRenderEvent.class, this::onParticleSpawn);
      this.handle(PreAttackEntityEvent.class, this::onEnchantedHit);
      this.method7(Framework.field11, Framework6.method3().method2(false));
   }

   @Override
   public String getId() {
      return "PARTICLE_CHANGER";
   }

   @Override
   protected List<Framework7Extension> method9() {
      ArrayList var1 = new ArrayList();
      var1.add(this.bloodChild);
      this.children.put(HorsestatsType2.BLOOD, this.bloodChild);

      for (HorsestatsType2 var5 : HorsestatsType2.values()) {
         if ((ThreadModuleDump63.field1 || var5.isAvailable()) && var5 != HorsestatsType2.BLOOD) {
            ParticleStyle var6 = ParticleStyle.method14(this, var5);
            var1.add(var6);
            this.children.put(var5, var6);
         }
      }

      return var1;
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method1(
         "generalOptions", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.alwaysEnchantStrikes, this.hideFirstPersonParticles, this.hideBlockBreakParticles, this.hideAllParticles})
      );
      var1.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
      var1.method1(
         "particleOptions",
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  ((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("scale")
                           .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllNumberWidget<>(var1xx, var2x, this.applyToAll(var1xx))))
                        .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
                     .method8(0.25F, 2.0F),
                  ((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("particleMultiplier")
                           .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllNumberWidget<>(var1xx, var2x, this.applyToAll(var1xx))))
                        .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
                     .method8(0.25F, 10.0F),
                  com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideParticle")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllInfoWidget(var1xx, var2x, this.applyToAll(var1xx)))
               }
            );
            ToggleOption var2 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("overlayColor")
               .method31();
            ColorOption var3 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("color")
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
               .method31();
            EnumOption var4 = (EnumOption)com.moonsworth.lunar.client.config.option.OptionFactory.method10("colorMode", Gui2Extension.OVERLAY)
               .method31();
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               var2,
               var4x -> var4x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new OptionSupplier[]{
                     var3, var4, com.moonsworth.lunar.client.config.option.OptionFactory.method14("applyToAll").method4(() -> this.applyToAll(var2, var3, var4))
                  }
               )
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.toggleAllButton(true), this.toggleAllButton(false)});
         }
      );
   }

   private ThreadModuleDump51 applyToAll(ClientOption<?>... var1) {
      return (var2, var3) -> {
         AlertExtension var4 = (AlertExtension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var4 == null) {
            return true;
         }

         var4.getChildren()
            .stream()
            .map(var0 -> (Framework5)var0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14))
            .filter(Objects::nonNull)
            .forEach(var1xx -> var1xx.method4(var1xxx -> {
               for (ClientOption var5 : var1) {
                  if (Objects.equals(var5.getId(), var1xxx.getId())) {
                     try {
                        var1xxx.method19(var5);
                     } catch (IllegalArgumentException var7) {
                        Slayer.warn("ParticleMod applyToAll", var7);
                     }
                  }
               }

               return false;
            }));
         return true;
      };
   }

   private ButtonOption toggleAllButton(boolean var1) {
      return (ButtonOption)com.moonsworth.lunar.client.config.option.OptionFactory.method14(var1 ? "enableAll" : "disableAll").method4(() -> {
         for (ParticleStyle var3 : this.children.values()) {
            ((ModEnabledState)var3.method7(Framework.field6)).setEnabled(var1);
         }
      }).method31();
   }

   private void onEnchantedHit(PreAttackEntityEvent var1) {
      if (this.alwaysEnchantStrikes.get()
         && var1.method2() instanceof BridgeExtension2_5
         && !(var1 instanceof ArmorStandBridge)
         && !var1.method2().bridge$isInvisible()
         && var1.method2().bridge$isAlive()) {
         ParticleStyle var2 = this.method7(HorsestatsType2.ENCHANTED_HIT);
         if (var2 != null) {
            float var3 = var2.getParticleCount(ThreadLocalRandom.current());

            for (int var4 = 0; var4 < var3; var4++) {
               this.mc.bridge$getEffectRenderer().bridge$emitParticleAtEntity(var1.method2(), HorsestatsType2.ENCHANTED_HIT);
            }
         }
      }
   }

   private void onParticleSpawn(ParticleRenderEvent var1) {
      if (this.hideAllParticles.get()) {
         var1.setCancelled(true);
      } else {
         ParticleStyle var2 = this.method7(var1.method1().bridge$getParticleType());
         if (var2 != null && var2.method14()) {
            var1.setCancelled(true);
         }
      }
   }

   @Nullable
   public ParticleStyle method7(HorsestatsType2 var1) {
      return var1 == null ? null : this.children.get(var1);
   }

   public boolean isHideFirstPersonParticlesEnabled() {
      return this.isEnabled() && this.hideFirstPersonParticles.get();
   }

   public boolean isHideBlockBreakParticlesEnabled() {
      return this.isEnabled() && this.hideBlockBreakParticles.get();
   }

   @Generated
   public ParticleChangerBlood getBloodChild() {
      return this.bloodChild;
   }
}
