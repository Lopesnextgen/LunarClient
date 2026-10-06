package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.WindowsMouseBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "org.lwjgl.opengl.WindowsMouse")
public abstract class WindowsMouseMixin implements WindowsMouseBridge {
   @Shadow
   private int last_x;
   @Shadow
   private int last_y;
   @Shadow
   private boolean mouse_grabbed;
   @Shadow
   private int accum_dx;
   @Shadow
   private int accum_dy;

   public WindowsMouseMixin() {
   }

   @Shadow
   public abstract boolean isGrabbed();

   @Shadow
   public abstract void handleMouseMoved(int number1, int number2, long number3);

   @Shadow
   protected abstract void putMouseEventWithCoords(byte number1, byte number2, int number3, int number4, int number5, long number6);

   public boolean bridge$isGrabbed() {
      return this.isGrabbed();
   }

   public void bridge$handleMouseMoved(int number1, int number2, long number3) {
      this.handleMouseMoved(number1, number2, number3);
   }

   public void bridge$handleMouseMovedRelative(int number1, int number2, long number3) {
      int number5 = number1 + this.last_x;
      int number6 = number2 + this.last_y;
      if (number1 != 0 || number2 != 0) {
         this.accum_dx += number1;
         this.accum_dy += number2;
         this.last_x = number5;
         this.last_y = number6;
         long number7 = number3 * 1000000L;
         if (this.mouse_grabbed) {
            this.putMouseEventWithCoords((byte)-1, (byte)0, number1, number2, 0, number7);
         } else {
            this.putMouseEventWithCoords((byte)-1, (byte)0, number5, number6, 0, number7);
         }
      }
   }
}
