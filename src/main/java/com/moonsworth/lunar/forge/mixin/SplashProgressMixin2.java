package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SplashProgress.class)
public class SplashProgressMixin2 {
   @Overwrite
   public static void checkThreadState() {
   }

   @Overwrite
   public static void start() {
   }
}
