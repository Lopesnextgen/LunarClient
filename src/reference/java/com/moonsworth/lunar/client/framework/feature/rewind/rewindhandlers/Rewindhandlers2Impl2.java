package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.common.hash.Hashing;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore3;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.DataInputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public class Rewindhandlers2Impl2 extends Rewindhandlers2<DataInputStream> {
   private final Rewind3 fileReader;
   private final RewindhandlersNameplateCore3 audioMetadata;
   private long decoderHandle;

   public Rewindhandlers2Impl2(
      File var1, String var2, com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2 var3, Rewind3 var4
   ) {
      super(var1, var2, var3);
      this.fileReader = var4;
      this.audioMetadata = (RewindhandlersNameplateCore3)ThreadModuleDump48.field22
         .fromJson(var4.method12("audio/" + this.mediaId + ".json"), RewindhandlersNameplateCore3.class);
      this.init();
      this.loadWaveform();
   }

   @Override
   public String getName() {
      return ThreadModuleDump63.method4().method67().method2("rewind", "audio" + StringUtils.capitalize(this.mediaId));
   }

   @Override
   protected void loadWaveform() {
      this.method9();
      super.loadWaveform();
      Opus.opus_decoder_destroy(this.decoderHandle);
   }

   @Override
   protected void createSource() {
      super.createSource();
      this.method9();
   }

   private void method9() {
      IntBuffer var1 = BufferUtils.createIntBuffer(1);
      this.decoderHandle = Opus.opus_decoder_create(this.audioMetadata.getFrequency(), this.audioMetadata.getChannels(), var1);
      if (var1.get() != 0) {
         throw new RuntimeException("Failed to create Opus decoder: " + var1.get());
      }
   }

   protected DataInputStream openInput() {
      return this.fileReader.method10("audio/" + this.mediaId + ".dat");
   }

   @Override
   protected GuiType2 readSampleFormat() {
      return GuiType2.getFormat(this.audioMetadata.getChannels(), this.audioMetadata.getBytesPerSample());
   }

   @Override
   protected int readSampleRate() {
      return this.audioMetadata.getFrequency();
   }

   @Override
   protected int readFrameSize() {
      return this.audioMetadata.getFrameSize();
   }

   @Override
   protected String computeHash() {
      return Hashing.md5().hashString(this.uri, StandardCharsets.UTF_8).toString();
   }

   @Override
   public long getDuration() {
      return this.audioMetadata.getDuration();
   }

   protected void method9(DataInputStream var1) {
      for (int var2 = 0; var2 < this.audioMetadata.getChannels(); var2++) {
         int var3 = ByteBufLoader.method12(var1);
         var1.skipBytes(var3);
      }
   }

   protected void decodeFrame(DataInputStream var1, ByteBuffer var2, ShortBuffer var3) {
      int var4 = ByteBufLoader.method12(var1);
      byte[] var5 = new byte[var4];
      int var6 = var1.read(var5);
      if (var6 == -1) {
         this.IICIIOHRCHCIOIIHROHHHOCRHIIIHR(true);
      } else {
         var2.clear();
         var2.put(var5);
         var2.flip();
         var3.clear();
         var3.limit(Opus.opus_decode(this.decoderHandle, var2, var3, this.audioMetadata.getFrameSize(), 0));
      }
   }

   @Override
   public void cleanup() {
      super.cleanup();
      Opus.opus_decoder_destroy(this.decoderHandle);
   }

   @Override
   public void reload() {
      Opus.opus_decoder_destroy(this.decoderHandle);
      super.reload();
   }

   @Override
   public Rewindhandlers2<DataInputStream> copy() {
      return new Rewindhandlers2Impl2(
         this.rootDirectory, this.uri, this.waveformRenderer, this.fileReader
      );
   }
}
