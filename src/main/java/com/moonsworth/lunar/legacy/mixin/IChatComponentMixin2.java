package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.ChatComponentMarker;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;

@Annotation2(min = 1)
@Mixin(IChatComponent.class)
public interface IChatComponentMixin2 extends ChatComponentMarker {
}
