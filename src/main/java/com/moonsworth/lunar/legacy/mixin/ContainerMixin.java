package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ContainerBridge;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Container.class)
public abstract class ContainerMixin implements ContainerBridge {
   public ContainerMixin() {
   }
}
