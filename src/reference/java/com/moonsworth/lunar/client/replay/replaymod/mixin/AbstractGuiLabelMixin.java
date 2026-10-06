package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiLabel;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractGuiLabel.class)
public abstract class AbstractGuiLabelMixin<T extends AbstractGuiLabel<T>> {
   public AbstractGuiLabelMixin() {
   }

   @Inject(
      method = "setColor(Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;)Lcom/replaymod/lib/de/johni0702/minecraft/gui/element/AbstractGuiLabel;",
      at = @At("HEAD"),
      cancellable = true
   )
   public void ichor$setColor(ReadableColor readablecolor1, CallbackInfoReturnable<T> callbackinforeturnable2) {
      if ((Boolean)Client.method109().method40().method64().method14().get() && readablecolor1 == ReadableColor.BLACK) {
         callbackinforeturnable2.setReturnValue(this);
      }
   }
}
