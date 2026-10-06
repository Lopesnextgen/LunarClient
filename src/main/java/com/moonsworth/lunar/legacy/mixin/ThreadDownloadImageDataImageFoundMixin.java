package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ThreadDownloadImageDataBridge;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ThreadDownloadImageData.class)
public abstract class ThreadDownloadImageDataImageFoundMixin implements ThreadDownloadImageDataBridge {
   @Shadow
   public Boolean imageFound;

   public ThreadDownloadImageDataImageFoundMixin() {
   }

   public void bridge$setImageFound(boolean flag) {
      this.imageFound = flag;
   }
}
