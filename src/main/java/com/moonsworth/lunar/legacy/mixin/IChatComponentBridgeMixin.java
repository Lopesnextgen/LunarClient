package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.minecraft.IChatComponentMarker;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(IChatComponent.class)
public interface IChatComponentBridgeMixin extends IChatComponentMarker {
}
