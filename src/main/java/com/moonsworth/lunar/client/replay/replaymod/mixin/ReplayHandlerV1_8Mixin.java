package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.replay.ReplayHandler;
import com.replaymod.replaystudio.replay.ReplayFile;
import java.io.IOException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReplayHandler.class)
public class ReplayHandlerV1_8Mixin {
   int length = 0;

   public ReplayHandlerV1_8Mixin() {
   }

   @Inject(method = "<init>", at = @At("TAIL"))
   public void ichor$init(ReplayFile replayfile1, boolean flag2, CallbackInfo callback3) {
      try {
         this.length = replayfile1.getMetaData().getDuration();
      } catch (IOException exception5) {
         exception5.printStackTrace();
      }
   }

   @Inject(method = "doJump", at = @At("HEAD"), cancellable = true)
   public void ichor$doJump(int value, boolean flag2, CallbackInfo callback3) {
      if (this.length == value) {
         callback3.cancel();
      }
   }
}
