package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModDiscoverer.class)
public class ModDiscovererV1_12Mixin {
   public ModDiscovererV1_12Mixin() {
   }

   @Overwrite
   public void findClasspathMods(ModClassLoader type) {
   }
}
