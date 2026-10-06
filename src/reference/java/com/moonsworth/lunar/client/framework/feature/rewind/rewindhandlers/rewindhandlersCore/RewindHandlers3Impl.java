package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.bridge.Bridge2_47;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSoundPlay;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.lwjgl.openal.AL10;

public class RewindHandlers3Impl extends RewindHandlers3 {
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("soundsOption").method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("replayGameSounds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field11;
   private final float field12;
   private final Bridge2_47 field13;
   private boolean paused = false;

   public RewindHandlers3Impl(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(EventClientTick.class, this::method1);
      this.handle(EventSoundPlay.class, this::method3);
      this.field12 = AL10.alGetListenerf(4106);
      AL10.alListenerf(4106, 1.0F);
      this.field11 = (FloatOption)((Data)((Data)((Data)OptionFactory.method2("soundsVolume").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(this.field12))
               .method8(0.0F, 1.0F))
            .method17(() -> !(Boolean)this.field9.get()))
         .method31();
      this.field13 = ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$getSoundEngine();
   }

   private void method1(EventClientTick var1) {
      AL10.alListenerf(4106, 1.0F);
   }

   public void method2(boolean var1) {
      if (this.paused != var1) {
         this.paused = var1;
         if (var1) {
            this.field13.bridge$pause();
         } else {
            this.field13.bridge$resume();
         }
      }
   }

   private void method3(EventSoundPlay var1) {
      Nameplate4 var2 = (Nameplate4)this.field8.get();
      boolean var3 = !var2.method18() || DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field10;
      if (var3 || this.paused || (Boolean)this.field9.get() && !(Boolean)this.field10.get()) {
         var1.setCancelled(true);
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
   public Bridge2_47 method21() {
      return this.field13;
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }
}
