package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.inventorymod.Inventorymod;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrashReport.class)
public class CrashReportMixin2 {
   @Shadow
   @Final
   public Throwable cause;

   @Inject(
      method = "<init>",
      at = @At(value = "FIELD", target = "Lnet/minecraft/crash/CrashReport;cause:Ljava/lang/Throwable;", opcode = 181, shift = Shift.AFTER)
   )
   private void lunar$fixCause(CallbackInfo callbackInfo) {
      Inventorymod.method1(this.cause);
   }
}
