package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.ErrorReporter;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler.ErrorAction;

public class IchorErrorHandler implements IMixinErrorHandler {
   public IchorErrorHandler() {
   }

   public ErrorAction onPrepareError(IMixinConfig imixinconfig1, Throwable exception2, IMixinInfo imixininfo3, ErrorAction erroraction4) {
      IchorAPI.getPipeline(imixininfo3).<ErrorReporter>map(IchorPipeline::method38).ifPresent(arg1x -> arg1x.method2(exception2));
      return ErrorAction.NONE;
   }

   public ErrorAction onApplyError(String text1, Throwable exception2, IMixinInfo imixininfo3, ErrorAction erroraction4) {
      IchorAPI.getPipeline(imixininfo3).<ErrorReporter>map(IchorPipeline::method38).ifPresent(arg1x -> arg1x.method2(exception2));
      return ErrorAction.NONE;
   }
}
