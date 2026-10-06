package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.replay.audio.LoopbackAudioRecorder;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.framework.Ref;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCcontext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

@Mixin(LibraryLWJGLOpenAL.class)
public class LibraryLWJGLOpenALMixin {
   public LibraryLWJGLOpenALMixin() {
   }

   @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lorg/lwjgl/openal/AL;create()V"))
   private void lunar$init(Operation<Void> operation1) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         RewindMod rewind2 = Ref.method4().method40().method85();
         if (rewind2.method19() && rewind2.method35().method57().method25()) {
            AL.create(null, 44100, 60, false, false);
            LoopbackAudioRecorder rewindhandlersnameplate_23 = rewind2.method35().method57().method27();
            rewindhandlersnameplate_23.method1();
            ALC10.alcMakeContextCurrent((ALCcontext)rewindhandlersnameplate_23.method6());
         } else {
            operation1.call(new Object[0]);
         }
      } else {
         operation1.call(new Object[0]);
      }
   }
}
