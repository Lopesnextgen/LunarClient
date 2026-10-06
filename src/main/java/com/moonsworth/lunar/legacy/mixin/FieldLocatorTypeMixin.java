package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import net.optifine.reflect.FieldLocatorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FieldLocatorType.class)
public class FieldLocatorTypeMixin {
   public FieldLocatorTypeMixin() {
   }

   @VersionGate(max = 0)
   @Redirect(method = "getField", at = @At(value = "INVOKE", target = "Lnet/optifine/Config;log(Ljava/lang/String;)V"))
   private void lunar$optifine$silentReflectorLog_v1_7(String text1) {
   }

   @VersionGate(min = 1)
   @Redirect(method = "getField", at = @At(value = "INVOKE", target = "Lnet/optifine/Log;log(Ljava/lang/String;)V"))
   private void lunar$optifine$silentReflectorLog(String text1) {
   }
}
