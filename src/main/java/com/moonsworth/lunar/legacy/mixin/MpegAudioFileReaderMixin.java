package com.moonsworth.lunar.legacy.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "javazoom.spi.mpeg.sampled.file.MpegAudioFileReader", remap = false)
public abstract class MpegAudioFileReaderMixin {
   public MpegAudioFileReaderMixin() {
   }

   @ModifyArg(
      method = "getAudioFileFormat(Ljava/io/InputStream;J)Ljavax/sound/sampled/AudioFileFormat;",
      at = @At(value = "INVOKE", target = "Ljava/io/BufferedInputStream;<init>(Ljava/io/InputStream;I)V"),
      index = 1
   )
   private int lunar$clampId3v1LookupBuffer(int value) {
      return Math.max(1, value);
   }
}
