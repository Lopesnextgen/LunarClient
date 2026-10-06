package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.lwjgl.openal.AL;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AL.class)
public class ALMixin {
   public ALMixin() {
   }

   @WrapOperation(
      method = "create(Ljava/lang/String;IIZZ)V",
      at = @At(
         value = "INVOKE",
         target = "Lorg/lwjgl/LWJGLUtil;getLibraryPaths(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/ClassLoader;)[Ljava/lang/String;"
      )
   )
   private static String[] lunar$addMacOsLib(String text, String[] items1, ClassLoader type, Operation<String[]> operation3) {
      if (items1.length == 1 && items1[0].equals("openal.dylib")) {
         items1 = new String[]{"libopenalsoft.dylib", "openal.dylib"};
      }

      return (String[])operation3.call(new Object[]{text, items1, type});
   }
}
