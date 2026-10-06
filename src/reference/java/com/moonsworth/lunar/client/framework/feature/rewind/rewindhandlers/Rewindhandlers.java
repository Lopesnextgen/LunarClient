package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.bridge.horsestats.TimerBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption.Data;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Rewindhandlers {
   public static final int MILLIS_PER_TICK = 50;
   private long originTime;
   private long lastRealTime;
   private long time;
   private long rewindOffset;
   private DoubleOption speedOption = (DoubleOption)((Data)((Data)((Data)OptionFactory.method1("speed").method4(1.0))
            .method8(0.1, 10.0))
         .method5(true, false))
      .method31();
   private ToggleOption freezeOption = (ToggleOption)OptionFactory.method7("freeze").method31();
   private long tickStartTime;
   private long remainingForwardMs;
   private long remainingRewindMs;
   private long skipDurationMs;
   private long stepMsPerTick;
   private boolean skipFinished;
   private boolean rewindDirection;
   private boolean tickStepped;
   private boolean forwardDirection = true;
   private boolean timeAdvanced = false;

   public Rewindhandlers() {
      this.reset();
   }

   public void reset() {
      this.originTime = ThreadModuleDump63.method3().bridge$getRealSystemTime();
      this.lastRealTime = this.originTime;
      this.time = this.lastRealTime;
      this.rewindOffset = 0L;
      this.speedOption.reset();
      this.remainingForwardMs = 0L;
      this.remainingRewindMs = 0L;
      this.skipDurationMs = 0L;
      this.stepMsPerTick = 50L;
      this.skipFinished = false;
      this.rewindDirection = false;
      this.tickStepped = false;
      this.forwardDirection = true;
      this.timeAdvanced = false;
      ThreadModuleDump63.method3().bridge$getTimer().bridge$setLastMS(this.time);
      ThreadModuleDump63.method3().bridge$getTimer().method2(0.0F);
   }

   public void update() {
      long var1 = ThreadModuleDump63.method3().bridge$getRealSystemTime();
      if (!this.isPlayable()) {
         this.lastRealTime = var1;
      } else {
         long var3 = var1 - this.lastRealTime;
         if (var3 > 0L) {
            this.time += var3;
            this.remainingForwardMs -= var3;
            this.stepMsPerTick -= var3;
            this.lastRealTime = var1;
            this.timeAdvanced = true;
         }
      }
   }

   public void commitPendingTime() {
      if (this.timeAdvanced) {
         this.originTime = this.time;
         this.timeAdvanced = false;
      }
   }

   public boolean isPlayable() {
      return ThreadModuleDump63.method3().bridge$areResourcesLoaded() && !ThreadModuleDump63.method3().bridge$hasOverlay();
   }

   public void skip(long var1) {
      boolean var3 = var1 >= 0L;
      var1 = var3 ? var1 : -var1;
      if (var3 != this.forwardDirection) {
         TimerBridge var4 = ThreadModuleDump63.method3().bridge$getTimer();
         int var5 = (int)(var4.method1() * 50.0F);
         int var6 = Math.min(var5, (int)Math.min(var1, 50L));
         var4.method2((var5 - var6) / 50.0F);
         var1 -= var6;
         if (!var3) {
            this.rewindOffset += var6;
         } else {
            this.time += var6;
            var4.bridge$setLastMS(this.time);
         }

         if (var4.method1() > 0.0F) {
            return;
         }

         this.rewindDirection = !var3;
         ClientEventBus.method29().method12(EventClientTick.class, EventClientTick::new);
         ClientEventBus.method29().method12(EventClientTick.class, EventClientTick::new);
      }

      if (var3) {
         this.remainingForwardMs = var1;
      } else {
         this.remainingRewindMs = var1;
      }

      this.skipDurationMs = var1;
      this.forwardDirection = var3;
      this.stepMsPerTick = 50L;
      this.lastRealTime = ThreadModuleDump63.method3().bridge$getRealSystemTime();
      this.tickStepped = false;
   }

   public void tick(RewindHandlers var1) {
      if (this.isSkipping()) {
         boolean var2 = this.hasPendingRewind();
         long var3 = Math.min(this.stepMsPerTick, var2 ? this.remainingRewindMs : this.remainingForwardMs);
         this.time += var3;
         if (var2) {
            this.rewindOffset += var3 * 2L;
         }

         this.lastRealTime = ThreadModuleDump63.method3().bridge$getRealSystemTime();
         if (var2) {
            this.remainingRewindMs -= var3;
            if (this.remainingRewindMs <= 0L) {
               this.rewindDirection = true;
            }
         } else {
            this.remainingForwardMs -= var3;
            this.rewindDirection = false;
         }

         this.stepMsPerTick = 50L;
         this.tickStepped = true;
         if (!this.isSkipping()) {
            this.skipFinished = true;
            ThreadModuleDump63.method3().bridge$getTimer().bridge$setLastMS(this.time - var3);
            Highlight_3 var5 = ((Nameplate4)var1.method42().get()).method4();
            if (var5 != null && var3 == this.stepMsPerTick) {
               var5.method6();
            }
         }
      }
   }

   public boolean isSkipping() {
      return this.remainingForwardMs > 0L || this.remainingRewindMs > 0L;
   }

   public boolean isBusy() {
      return this.isSkipping() || this.skipFinished;
   }

   public boolean isReversing() {
      return this.hasPendingRewind() || this.rewindDirection;
   }

   public long getPlaybackTime() {
      return this.time - this.originTime - this.rewindOffset;
   }

   public long getProjectedTime() {
      return this.time + this.remainingForwardMs - this.remainingRewindMs - this.originTime - this.rewindOffset;
   }

   public void markTickStart() {
      this.tickStartTime = this.time;
   }

   public void shiftOriginTime(long var1) {
      this.originTime -= var1;
   }

   public float getPartialTick() {
      return Math.max(0.0F, Math.min(1.0F, (float)(this.time - this.tickStartTime) / 50.0F));
   }

   public boolean hasPendingRewind() {
      return this.remainingRewindMs > 0L;
   }

   public float getSkipProgress() {
      if (this.skipDurationMs <= 0L) {
         return 1.0F;
      }

      long var1 = this.skipDurationMs;
      long var3 = this.hasPendingRewind() ? this.remainingRewindMs : this.remainingForwardMs;
      return 1.0F - (float)var3 / (float)var1;
   }

   @Generated
   public long getTime() {
      return this.time;
   }

   @Generated
   public void setSpeedOption(DoubleOption var1) {
      this.speedOption = var1;
   }

   @Generated
   public DoubleOption getSpeedOption() {
      return this.speedOption;
   }

   @Generated
   public void setFreezeOption(ToggleOption var1) {
      this.freezeOption = var1;
   }

   @Generated
   public ToggleOption getFreezeOption() {
      return this.freezeOption;
   }

   @Generated
   public long getRemainingForwardMs() {
      return this.remainingForwardMs;
   }

   @Generated
   public long getSkipDurationMs() {
      return this.skipDurationMs;
   }

   @Generated
   public void setSkipFinished(boolean var1) {
      this.skipFinished = var1;
   }

   @Generated
   public void setRewindDirection(boolean var1) {
      this.rewindDirection = var1;
   }

   @Generated
   public boolean isTickStepped() {
      return this.tickStepped;
   }
}
