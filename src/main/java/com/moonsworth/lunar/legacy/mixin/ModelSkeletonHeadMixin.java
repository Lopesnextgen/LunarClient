package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ModelSkeletonHeadBridge;
import net.minecraft.client.model.ModelSkeletonHead;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ModelSkeletonHead.class)
public class ModelSkeletonHeadMixin implements ModelSkeletonHeadBridge {
   public ModelSkeletonHeadMixin() {
   }
}
