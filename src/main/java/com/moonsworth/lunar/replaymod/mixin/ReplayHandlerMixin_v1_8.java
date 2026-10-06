package com.moonsworth.lunar.replaymod.mixin;

import com.replaymod.replay.ReplayHandler;
import com.replaymod.replaystudio.replay.ReplayFile;
import java.io.IOException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReplayHandler.class)
public class ReplayHandlerMixin_v1_8 {
   int length = 0;

   @Inject(method = "<init>", at = @At("TAIL"))
   public void ichor$init(ReplayFile var1, boolean var2, CallbackInfo var3) {
      try {
         this.length = var1.getMetaData().getDuration();
      } catch (IOException var5) {
         var5.printStackTrace();
      }
   }

   @Inject(method = "doJump", at = @At("HEAD"), cancellable = true)
   public void ichor$doJump(int var1, boolean var2, CallbackInfo var3) {
      if (this.length == var1) {
         var3.cancel();
      }
   }
}
