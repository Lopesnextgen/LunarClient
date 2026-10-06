package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiLabel;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractGuiLabel.class)
public abstract class AbstractGuiLabel_v1_8<T extends AbstractGuiLabel<T>> {
   @Inject(
      method = "setColor(Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;)Lcom/replaymod/lib/de/johni0702/minecraft/gui/element/AbstractGuiLabel;",
      at = @At("HEAD"),
      cancellable = true
   )
   public void ichor$setColor(ReadableColor var1, CallbackInfoReturnable<T> var2) {
      if (Client.method109().method40().method64().method14().get() && var1 == ReadableColor.BLACK) {
         var2.setReturnValue(this);
      }
   }
}
