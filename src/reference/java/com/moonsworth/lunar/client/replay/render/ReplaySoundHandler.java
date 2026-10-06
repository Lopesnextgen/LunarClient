package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.SoundManagerBridge;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import org.lwjgl.openal.AL10;

public class ReplaySoundHandler extends RewindHandler {
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("soundsOption").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("replayGameSounds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption field11;
   private final float field12;
   private final SoundManagerBridge field13;
   private boolean paused = false;

   public ReplaySoundHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(EventTick.class, this::method1);
      this.handle(EventPlaySound.class, this::method3);
      this.field12 = AL10.alGetListenerf(4106);
      AL10.alListenerf(4106, 1.0F);
      this.field11 = (FloatOption)((Data)((Data)((Data)OptionFactory.method2("soundsVolume").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(this.field12))
               .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 1.0F))
            .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> !(Boolean)this.field9.get()))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field13 = Ref.method3().bridge$getSoundHandler().bridge$getSoundEngine();
   }

   private void method1(EventTick highlightimpl21) {
      AL10.alListenerf(4106, 1.0F);
   }

   public void method2(boolean flag1) {
      if (this.paused != flag1) {
         this.paused = flag1;
         if (flag1) {
            this.field13.bridge$pause();
         } else {
            this.field13.bridge$resume();
         }
      }
   }

   private void method3(EventPlaySound highlightimpl131) {
      ReplayContext nameplate42 = (ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get();
      boolean flag3 = !nameplate42.method18() || DriverViewportLegacy.method50().method63() != DriverRouteRegistry.field10;
      if (flag3 || this.paused || (Boolean)this.field9.get() && !(Boolean)this.field10.get()) {
         highlightimpl131.setCancelled(true);
      }
   }

   public void method14() {
      AL10.alListenerf(4106, this.field12);
   }

   public float method15() {
      return (Float)this.field11.get();
   }

   @Generated
   public ToggleOption method16() {
      return this.field9;
   }

   @Generated
   public ToggleOption method17() {
      return this.field10;
   }

   @Generated
   public FloatOption method19() {
      return this.field11;
   }

   @Generated
   public SoundManagerBridge method21() {
      return this.field13;
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }
}
