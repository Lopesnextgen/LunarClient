package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.pathing.player.RealtimeTimelinePlayer;
import com.replaymod.simplepathing.gui.GuiPathing;
import java.lang.reflect.Field;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.replaymod.simplepathing.gui.GuiPathing$10")
public class GuiPathingRealtimeTickMixin {
   @Shadow
   public GuiPathing this$0;

   public GuiPathingRealtimeTickMixin() {
   }

   @Inject(method = "run", at = @At(value = "INVOKE", target = "Lcom/google/common/util/concurrent/ListenableFuture;cancel(Z)Z", shift = Shift.AFTER))
   public void ichor$tickPlayer(CallbackInfo callback1) {
      Class clazz2 = this.this$0.getClass();
      Field field3 = clazz2.getDeclaredField("player");
      field3.setAccessible(true);
      RealtimeTimelinePlayer realtimetimelineplayer4 = (RealtimeTimelinePlayer)field3.get(this.this$0);
      realtimetimelineplayer4.onTick();
   }
}
