package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.CrashReportBridge;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CrashReport.class)
public class CrashReportMixin implements CrashReportBridge {
   @Final
   @Shadow
   public String description;
   @Final
   @Shadow
   public Throwable cause;

   public CrashReportMixin() {
   }

   public String bridge$getTitle() {
      return this.description;
   }

   public Throwable bridge$getCause() {
      return this.cause;
   }
}
