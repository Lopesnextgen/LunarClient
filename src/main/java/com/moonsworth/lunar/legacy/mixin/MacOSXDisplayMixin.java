package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.DisplayBridge;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "org.lwjgl.opengl.MacOSXDisplay")
public class MacOSXDisplayMixin implements DisplayBridge {
   public MacOSXDisplayMixin() {
   }

   public long bridge$getDisplayHandle() {
      return 0L;
   }

   public long bridge$getWindowHandle() {
      return 0L;
   }
}
