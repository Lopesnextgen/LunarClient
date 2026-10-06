package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.MovementInputMarker;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public abstract class MovementInputFromOptionsMixin extends MovementInput implements MovementInputMarker {
   public MovementInputFromOptionsMixin() {
   }

   @Inject(method = "updatePlayerMoveState", at = @At("HEAD"), cancellable = true)
   private void lunar$onUpdatePlayerMoveState(CallbackInfo callback1) {
      if (Ref.method4().method40().method85().method19()) {
         callback1.cancel();
      }
   }
}
