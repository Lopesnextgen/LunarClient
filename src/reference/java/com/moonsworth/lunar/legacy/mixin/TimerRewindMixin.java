package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.replay.recording.ReplayClock;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
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
public abstract class TimerRewindMixin {
   @VersionGate(max = 1)
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
   @VersionGate(max = 1)
   @Unique
   private float lunar$partialTicks;

   public TimerRewindMixin() {
   }

   @VersionGate(max = 1)
   @Redirect(method = "updateTimer", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J"))
   @Dynamic
   private long lunar$overrideNanoTime() {
      Client client1 = Ref.method4();
      if (client1 == null) {
         return System.nanoTime();
      }

      ModsSettings fogloader32 = client1.method40();
      if (fogloader32 == null) {
         return System.nanoTime();
      }

      RewindMod rewind3 = fogloader32.method85();
      if (!rewind3.method19()) {
         return System.nanoTime();
      }

      ReplayClock rewindhandlers4 = rewind3.method35().method41();
      return rewindhandlers4.getTime() * 1000000L;
   }

   @VersionGate(max = 1)
   @Inject(method = "updateTimer", at = @At(value = "FIELD", target = "Lnet/minecraft/util/Timer;lastSyncSysClock:J", ordinal = 1))
   private void lunar$disableSyncAdjustmentInRewind(CallbackInfo callback1) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         if (Ref.method4().method40().method85().method19()) {
            this.timeSyncAdjustment = 1.0;
         }
      }
   }

   @VersionGate(max = 1)
   @Inject(method = "updateTimer", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindReplaceTimer(CallbackInfo callback1) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         if (Ref.method4().method40().method85().method19()) {
            callback1.cancel();
            long number2 = Minecraft.getSystemTime();
            this.elapsedPartialTicks = (float)(number2 - this.lastSyncSysClock) / (1000.0F / this.ticksPerSecond);
            this.lastSyncSysClock = number2;
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
