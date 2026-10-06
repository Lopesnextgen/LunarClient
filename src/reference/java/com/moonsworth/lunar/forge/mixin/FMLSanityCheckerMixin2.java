package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.io.File;
import net.minecraftforge.fml.common.asm.FMLSanityChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FMLSanityChecker.class)
public class FMLSanityCheckerMixin2 {
   @Shadow
   public static File fmlLocation;

   @Inject(method = "<clinit>", at = @At("HEAD"))
   private static void ichor$sanityCheckInit(CallbackInfo callbackInfo) {
      fmlLocation = IchorAPI.getPipeline(callbackInfo).orElseThrow().method11("forge").orElseThrow().toFile();
   }
}
