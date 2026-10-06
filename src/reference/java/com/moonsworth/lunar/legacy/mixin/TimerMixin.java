package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.TimerBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Timer.class)
public abstract class TimerMixin implements TimerBridge {
   @Shadow
   public long lastSyncSysClock;
   @Shadow
   @VersionGate(max = 1)
   public double lastHRTime;

   public TimerMixin() {
   }

   public void bridge$setLastMS(long value) {
      this.lastSyncSysClock = value;
      if (Ref.MC_VERSION <= 1) {
         this.lastHRTime = value / 1000.0;
      }
   }
}
