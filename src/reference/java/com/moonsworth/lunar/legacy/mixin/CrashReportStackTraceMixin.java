package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.crash.IchorStackTraceFilter;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrashReport.class)
public class CrashReportStackTraceMixin {
   @Shadow
   @Final
   public Throwable cause;

   public CrashReportStackTraceMixin() {
   }

   @Inject(
      method = "<init>",
      at = @At(value = "FIELD", target = "Lnet/minecraft/crash/CrashReport;cause:Ljava/lang/Throwable;", opcode = 181, shift = Shift.AFTER)
   )
   private void lunar$fixCause(CallbackInfo callback1) {
      IchorStackTraceFilter.method1(this.cause);
   }
}
