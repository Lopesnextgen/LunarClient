package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.minecraft.IChatComponentMarker;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(max = 0)
@Mixin(IChatComponent.class)
public interface IChatComponentMixin extends IChatComponentMarker {
}
