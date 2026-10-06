package com.moonsworth.lunar.legacy.mixin;

import net.kyori.adventure.key.Key;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Key.class)
public interface KeyResourceLocationMixin {
   @Overwrite
   static Key key(String text, String text2) {
      return (Key)(new ResourceLocation(text, text2));
   }
}
