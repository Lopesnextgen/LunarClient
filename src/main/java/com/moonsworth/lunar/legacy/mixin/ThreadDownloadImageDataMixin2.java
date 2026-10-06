package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge20Extension;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ThreadDownloadImageData.class)
public abstract class ThreadDownloadImageDataMixin2 implements Bridge20Extension {
   @Shadow
   public Boolean imageFound;

   public void bridge$setImageFound(boolean flag) {
      this.imageFound = flag;
   }
}
