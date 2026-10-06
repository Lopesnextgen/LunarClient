package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.WindowsMouseBridge;
import com.moonsworth.lunar.bridge.DisplayBridge;
import com.moonsworth.lunar.ichor.util.KeepName;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "org.lwjgl.opengl.WindowsDisplay")
public abstract class WindowsDisplayMixin implements DisplayBridge {
   @Final
   @Shadow
   private static int XBUTTON1;
   @Final
   @Shadow
   private static int XBUTTON1;
   @Shadow
   private long hwnd;
   @Unique
   private Object lunar$mouse = null;
   @Unique
   private boolean lunar$useRawInput = false;
   @Unique
   private boolean lunar$rawInputEnabled = false;

   public WindowsDisplayMixin() {
   }

   @Shadow
   protected abstract void handleMouseButton(int number1, int number2, long number3);

   @Shadow
   private static int transformY(long number0, int number2) {
      throw new AssertionError();
   }

   @Shadow
   protected abstract long getHwnd();

   @Shadow
   private static native long defWindowProc(long number0, int number2, long number3, long number5);

   @Unique
   private native void nToggleRawInput(long number1, boolean flag3);

   @Unique
   private native boolean nGetRawMouseCoords(long number1, WindowsDisplayMixin.RawMouseInput data3);

   @Inject(method = "doHandleMessage", at = @At("HEAD"), cancellable = true)
   private void lunar$doHandleMessage(long number1, int number3, long number4, long number6, long number8, CallbackInfoReturnable<Long> callbackinforeturnable10) {
      if (number3 == XBUTTON1 && number4 >> 16 == XBUTTON1) {
         this.handleMouseButton(3, 1, number8);
         callbackinforeturnable10.setReturnValue(1L);
      }
   }

   public long bridge$getDisplayHandle() {
      return 0L;
   }

   public long bridge$getWindowHandle() {
      return this.hwnd;
   }

   public void bridge$toggleRawInput(boolean flag1) {
      if (!Display.isCreated()) {
         throw new IllegalStateException("Display not yet created.");
      }

      this.lunar$useRawInput = flag1;
      boolean flag2 = this.lunar$useRawInput && this.lunar$mouse != null && ((WindowsMouseBridge)this.lunar$mouse).bridge$isGrabbed();
      if (this.lunar$rawInputEnabled != flag2) {
         this.nToggleRawInput(this.hwnd, flag2);
         this.lunar$rawInputEnabled = flag2;
      }
   }

   @Inject(method = "createWindow", at = @At("TAIL"))
   private void lunar$rawMouseInput$createWindow$tail(CallbackInfo callback1) {
      boolean flag2 = this.lunar$useRawInput && this.lunar$mouse != null && ((WindowsMouseBridge)this.lunar$mouse).bridge$isGrabbed();
      if (this.lunar$rawInputEnabled != flag2) {
         this.nToggleRawInput(this.hwnd, flag2);
         this.lunar$rawInputEnabled = flag2;
      }
   }

   @Inject(method = "destroyWindow", at = @At("TAIL"))
   private void lunar$rawMouseInput$destroyWindow$tail(CallbackInfo callback1) {
      this.lunar$rawInputEnabled = false;
   }

   @WrapOperation(method = "createMouse", at = @At(value = "NEW", target = "(J)Lorg/lwjgl/opengl/WindowsMouse;"))
   @Coerce
   private Object lunar$rawMouseInput$createMouse(long number1, Operation<Object> operation3) {
      return this.lunar$mouse = operation3.call(new Object[]{number1});
   }

   @Inject(method = "destroyMouse", at = @At("TAIL"))
   private void lunar$rawMouseInput$destroyMouse(CallbackInfo callback1) {
      this.lunar$mouse = null;
   }

   @Inject(method = "grabMouse", at = @At("TAIL"))
   private void lunar$rawMouseInput$grabMouse(boolean flag1, CallbackInfo callback2) {
      boolean flag3 = flag1 && this.lunar$useRawInput;
      if (this.lunar$rawInputEnabled != flag3) {
         this.nToggleRawInput(this.hwnd, flag3);
         this.lunar$rawInputEnabled = flag3;
      }
   }

   @ModifyExpressionValue(
      method = "doHandleMessage",
      at = @At(value = "FIELD", target = "Lorg/lwjgl/opengl/WindowsDisplay;mouse:Lorg/lwjgl/opengl/WindowsMouse;", opcode = 180, ordinal = 0)
   )
   @Coerce
   private Object lunar$rawMouseInput$doHandleMessage$disableMouseMove(@Coerce Object obj1) {
      return this.lunar$rawInputEnabled ? null : obj1;
   }

   @ModifyReturnValue(
      method = "doHandleMessage",
      at = @At("RETURN"),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/WindowsDisplay;nTrackMouseEvent(J)Z"),
         to = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/WindowsDisplay;handleMouseScrolled(IJ)V")
      ),
      allow = 1
   )
   private long lunar$rawMouseInput$doHandleMessage$disableMouseMoveReturn(long number1, long number3, int number5, long number6, long number8) {
      return this.lunar$rawInputEnabled ? defWindowProc(number3, number5, number6, number8) : number1;
   }

   @Inject(method = "doHandleMessage", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/WindowsDisplay;defWindowProc(JIJJ)J", shift = Shift.BEFORE))
   private void lunar$rawMouseInput$doHandleMessage$wmInput(long number1, int number3, long number4, long number6, long number8, CallbackInfoReturnable<Long> callbackinforeturnable10) {
      if (number3 == 255) {
         if (this.lunar$rawInputEnabled) {
            WindowsDisplayMixin.RawMouseInput data11 = new WindowsDisplayMixin.RawMouseInput();
            boolean flag12 = this.nGetRawMouseCoords(number6, data11);
            if (flag12 && this.lunar$mouse != null) {
               int number13 = data11.x;
               int number14 = data11.y;
               if (data11.absolute) {
                  ((WindowsMouseBridge)this.lunar$mouse).bridge$handleMouseMoved(number13, transformY(this.getHwnd(), number14), number8);
               } else {
                  ((WindowsMouseBridge)this.lunar$mouse).bridge$handleMouseMovedRelative(number13, -number14, number8);
               }
            }
         }
      }
   }

   @Unique
   @KeepName
   private static final class RawMouseInput {
      public boolean absolute;
      public int x;
      public int y;

      private RawMouseInput() {
      }
   }
}
