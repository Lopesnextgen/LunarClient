package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MouseHelperBridge;
import com.moonsworth.lunar.client.framework.feature.inventorymod.mixin.InventoryScreenPreserver;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
import net.minecraft.util.MouseHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHelper.class)
public class MouseHelperMixin {
   public MouseHelperMixin() {
   }

   @Inject(method = "ungrabMouseCursor", at = @At("HEAD"), cancellable = true)
   private void lunar$onUngrabMouse(CallbackInfo callback1) {
      InventoryScreenPreserver guirewindhandlershandler22 = (InventoryScreenPreserver)com.moonsworth.lunar.client.framework.listener.DynamicListener.method9(
            InventoryScreenPreserver.class
         )
         .orElse(null);
      if (guirewindhandlershandler22 != null) {
         if (guirewindhandlershandler22.method4(Ref.method3().bridge$getCurrentScreen())) {
            callback1.cancel();
            if (Ref.MC_VERSION <= 5 && OperatingSystem.isMacos()) {
               MouseHelperBridge bridge3_273 = Bridge.method20();
               Mouse.setCursorPosition(bridge3_273.getX(), Display.getHeight() - bridge3_273.getY());
            }

            Mouse.setGrabbed(false);
         }
      }
   }
}
