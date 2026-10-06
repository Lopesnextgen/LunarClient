package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.common.LoadController;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LoadController.class)
public class LoadControllerMixin {
   public LoadControllerMixin() {
   }
}
