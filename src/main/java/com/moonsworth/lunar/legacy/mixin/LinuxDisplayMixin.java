package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.DisplayBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "org.lwjgl.opengl.LinuxDisplay")
public class LinuxDisplayMixin implements DisplayBridge {
   @Shadow
   private static long display;
   @Shadow
   private static long current_window;

   public LinuxDisplayMixin() {
   }

   public long bridge$getDisplayHandle() {
      return display;
   }

   public long bridge$getWindowHandle() {
      return current_window;
   }
}
