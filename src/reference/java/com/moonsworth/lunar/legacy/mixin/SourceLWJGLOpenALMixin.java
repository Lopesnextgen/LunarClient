package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.legacy.wrapper.ICodecImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import paulscode.sound.FilenameURL;
import paulscode.sound.SoundBuffer;
import paulscode.sound.Source;
import paulscode.sound.libraries.ChannelLWJGLOpenAL;
import paulscode.sound.libraries.SourceLWJGLOpenAL;

@Mixin(SourceLWJGLOpenAL.class)
public abstract class SourceLWJGLOpenALMixin extends Source {
   @Shadow
   private ChannelLWJGLOpenAL channelOpenAL;

   public SourceLWJGLOpenALMixin(
      boolean flag1,
      boolean flag2,
      boolean flag3,
      String text4,
      FilenameURL filenameurl5,
      SoundBuffer soundbuffer6,
      float value7,
      float value8,
      float value9,
      int number10,
      float value11,
      boolean flag12
   ) {
      super(flag1, flag2, flag3, text4, filenameurl5, soundbuffer6, value7, value8, value9, number10, value11, flag12);
   }

   @Inject(method = "preLoad", at = @At(value = "INVOKE", target = "Lpaulscode/sound/ICodec;read()Lpaulscode/sound/SoundBuffer;"), cancellable = true)
   private void lunar$fixJamsNpe$codec(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      if (this.codec == null) {
         this.preLoad = false;
         callbackinforeturnable1.setReturnValue(false);
      }
   }

   @Inject(method = "calculateDistance", at = @At("HEAD"), cancellable = true)
   private void lunar$fixJamsNpe$position(CallbackInfo callback1) {
      if (this.position == null) {
         callback1.cancel();
      }
   }

   @Inject(method = "play", at = @At(value = "INVOKE", target = "Lpaulscode/sound/ICodec;initialize(Ljava/net/URL;)Z"))
   private void lunar$provideChannel(CallbackInfo callback1) {
      if (this.codec instanceof ICodecImpl icodecimpl2) {
         icodecimpl2.method5(this.channelOpenAL);
      }
   }
}
