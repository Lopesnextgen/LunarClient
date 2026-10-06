package com.moonsworth.lunar.forge.mixin;

import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ForgeEventFactory.class)
public class ForgeEventFactoryV1_12Mixin {
   public ForgeEventFactoryV1_12Mixin() {
   }

   @Redirect(
      method = "firePlayerLoadingEvent(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/storage/IPlayerFileData;Ljava/lang/String;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/ObfuscationReflectionHelper;getPrivateValue(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;"
      )
   )
   private static Object lunar$fixMapping(Class clazz0, Object obj1, String text2) {
      return ((SaveHandler)obj1).playersDirectory;
   }
}
