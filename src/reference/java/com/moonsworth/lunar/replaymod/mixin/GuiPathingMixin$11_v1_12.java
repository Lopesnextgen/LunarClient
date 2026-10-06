package com.moonsworth.lunar.replaymod.mixin;

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
public class GuiPathingMixin$11_v1_12 {
   @Shadow
   public GuiPathing this$0;

   @Inject(method = "run", at = @At(value = "INVOKE", target = "Lcom/google/common/util/concurrent/ListenableFuture;cancel(Z)Z", shift = Shift.AFTER))
   public void ichor$tickPlayer(CallbackInfo var1) {
      Class var2 = this.this$0.getClass();
      Field var3 = var2.getDeclaredField("player");
      var3.setAccessible(true);
      RealtimeTimelinePlayer var4 = (RealtimeTimelinePlayer)var3.get(this.this$0);
      var4.onTick();
   }
}
