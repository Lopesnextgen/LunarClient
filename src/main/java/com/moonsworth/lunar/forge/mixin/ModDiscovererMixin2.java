package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModDiscoverer.class)
public class ModDiscovererMixin2 {
   @Overwrite
   public void findClasspathMods(ModClassLoader type) {
   }
}
