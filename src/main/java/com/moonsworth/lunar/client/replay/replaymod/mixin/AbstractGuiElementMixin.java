package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiElement;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractGuiElement.class)
public class AbstractGuiElementMixin {
   @Shadow
   @Final
   @Mutable
   protected static ResourceLocation TEXTURE;

   public AbstractGuiElementMixin() {
   }

   @Inject(method = "<init>()V", at = @At("TAIL"))
   public void ichor$init(CallbackInfo callback1) {
      TEXTURE = new ResourceLocation("lunar", "replaymod/gui.png");
   }
}
