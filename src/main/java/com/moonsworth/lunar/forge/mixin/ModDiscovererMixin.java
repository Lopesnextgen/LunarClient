package com.moonsworth.lunar.forge.mixin;

import java.io.File;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModDiscoverer.class)
public class ModDiscovererMixin {
   public ModDiscovererMixin() {
   }

   @Overwrite
   public void findClasspathMods(ModClassLoader type) {
   }

   @Overwrite
   public void findModDirMods(File file1, File[] items2) {
   }
}
