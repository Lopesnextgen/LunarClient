package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.IMetadataSectionBridge;
import net.minecraft.client.resources.data.IMetadataSection;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IMetadataSection.class)
public interface IMetadataSectionMixin extends IMetadataSectionBridge {
}
