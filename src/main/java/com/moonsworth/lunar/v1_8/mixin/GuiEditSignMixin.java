package com.moonsworth.lunar.v1_8.mixin;

import com.moonsworth.lunar.v1_8.optifine.wrapper.Wrapper;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.tileentity.TileEntitySign;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiEditSign.class)
public abstract class GuiEditSignMixin {
   @Shadow
   public TileEntitySign tileSign;

   public GuiEditSignMixin() {
   }

   @Inject(method = "initGui", at = @At("HEAD"))
   public void impl$initGui(CallbackInfo callback1) {
      Wrapper.currentlyEditedSign = this.tileSign;
   }

   @Inject(method = "onGuiClosed", at = @At("HEAD"))
   public void impl$onGuiClosed(CallbackInfo callback1) {
      Wrapper.currentlyEditedSign = null;
   }
}
