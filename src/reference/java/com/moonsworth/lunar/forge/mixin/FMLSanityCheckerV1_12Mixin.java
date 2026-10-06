package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.io.File;
import java.nio.file.Path;
import net.minecraftforge.fml.common.asm.FMLSanityChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FMLSanityChecker.class)
public class FMLSanityCheckerV1_12Mixin {
   @Shadow
   public static File fmlLocation;

   public FMLSanityCheckerV1_12Mixin() {
   }

   @Inject(method = "<clinit>", at = @At("HEAD"))
   private static void ichor$sanityCheckInit(CallbackInfo callback0) {
      fmlLocation = ((Path)((IchorPipeline)IchorAPI.getPipeline(callback0).orElseThrow()).method11("forge").orElseThrow()).toFile();
   }
}
