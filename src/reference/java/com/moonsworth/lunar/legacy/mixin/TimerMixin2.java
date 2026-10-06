package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Timer.class)
public abstract class TimerMixin2 {
   @Annotation2(max = 1)
   @Shadow
   public double timeSyncAdjustment;
   @Shadow
   public long lastSyncSysClock;
   @Shadow
   public float ticksPerSecond;
   @Shadow
   public float elapsedPartialTicks;
   @Shadow
   public float renderPartialTicks;
   @Shadow
   public int elapsedTicks;
   @Annotation2(max = 1)
   @Unique
   private float lunar$partialTicks;

   @Annotation2(max = 1)
   @Redirect(method = "updateTimer", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J"))
   @Dynamic
   private long lunar$overrideNanoTime() {
      Client var1 = ThreadModuleDump63.method4();
      if (var1 == null) {
         return System.nanoTime();
      }

      ModsSettings var2 = var1.method40();
      if (var2 == null) {
         return System.nanoTime();
      }

      Rewind var3 = var2.method85();
      if (!var3.method19()) {
         return System.nanoTime();
      }

      Rewindhandlers var4 = var3.method35().method41();
      return var4.getTime() * 1000000L;
   }

   @Annotation2(max = 1)
   @Inject(method = "updateTimer", at = @At(value = "FIELD", target = "Lnet/minecraft/util/Timer;lastSyncSysClock:J", ordinal = 1))
   private void lunar$disableSyncAdjustmentInRewind(CallbackInfo var1) {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         if (ThreadModuleDump63.method4().method40().method85().method19()) {
            this.timeSyncAdjustment = 1.0;
         }
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "updateTimer", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindReplaceTimer(CallbackInfo var1) {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         if (ThreadModuleDump63.method4().method40().method85().method19()) {
            var1.cancel();
            long var2 = Minecraft.getSystemTime();
            this.elapsedPartialTicks = (float)(var2 - this.lastSyncSysClock) / (1000.0F / this.ticksPerSecond);
            this.lastSyncSysClock = var2;
            this.lunar$partialTicks = this.lunar$partialTicks + this.elapsedPartialTicks;
            this.elapsedTicks = (int)this.lunar$partialTicks;
            this.lunar$partialTicks = this.lunar$partialTicks - this.elapsedTicks;
            if (this.elapsedTicks > 10) {
               this.elapsedTicks = 10;
            }

            this.renderPartialTicks = this.lunar$partialTicks;
         }
      }
   }
}
