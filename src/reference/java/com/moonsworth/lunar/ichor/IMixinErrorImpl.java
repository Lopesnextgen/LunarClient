package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.api.IchorAPI;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler.ErrorAction;

public class IMixinErrorImpl implements IMixinErrorHandler {
   public ErrorAction onPrepareError(IMixinConfig var1, Throwable var2, IMixinInfo var3, ErrorAction var4) {
      IchorAPI.getPipeline(var3).map(IchorPipeline::method38).ifPresent(var1x -> var1x.method2(var2));
      return var4;
   }

   public ErrorAction onApplyError(String var1, Throwable var2, IMixinInfo var3, ErrorAction var4) {
      IchorAPI.getPipeline(var3).map(IchorPipeline::method38).ifPresent(var1x -> var1x.method2(var2));
      return var4;
   }
}
