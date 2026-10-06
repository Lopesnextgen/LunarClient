package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.util.TeePrintStream;
import net.minecraft.init.Bootstrap;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
   public BootstrapMixin() {
   }

   @VersionGate(max = 0)
   @Inject(method = "register", at = @At("HEAD"))
   private static void lunar$forceRedirectOutputToLogging$v1_7(CallbackInfo callback0) {
      lunar$forceRedirectOutputToLogging();
   }

   @VersionGate(min = 1)
   @WrapOperation(method = "register", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;isDebugEnabled()Z"))
   private static boolean lunar$forceRedirectOutputToLogging$v1_8(Logger logger0, Operation<Boolean> operation1) {
      lunar$forceRedirectOutputToLogging();
      return false;
   }

   @Unique
   private static void lunar$forceRedirectOutputToLogging() {
      if (System.out instanceof TeePrintStream stream) {
         System.setOut(stream.method2());
      }

      if (System.err instanceof TeePrintStream stream) {
         System.setErr(stream.method2());
      }

      System.setErr(new com.moonsworth.lunar.legacy.wrapper.LoggerPrintStream("STDERR", System.err));
      System.setOut(new com.moonsworth.lunar.legacy.wrapper.LoggerPrintStream("STDOUT", System.out));
   }
}
