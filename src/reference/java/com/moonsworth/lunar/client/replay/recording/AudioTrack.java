package com.moonsworth.lunar.client.replay.recording;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4;

public class AudioTrack {
   private final String field1;
   private final File field2;
   private final DataOutputStream field3;

   public AudioTrack(String text1, File file2, boolean flag3) {
      this.field1 = text1;
      this.field2 = file2;
      file2.deleteOnExit();
      if (flag3) {
         this.field3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
      } else {
         this.field3 = null;
      }
   }

   public void method1(RewindhandlersNameplateCore4 rewindhandlersnameplatecore41) {
      rewindhandlersnameplatecore41.method2(this.field3);
   }

   public void method2(RewindhandlersNameplateCore4 rewindhandlersnameplatecore41) {
      rewindhandlersnameplatecore41.method3(this.field3);
   }

   public void close() {
      if (this.field3 != null) {
         this.field3.close();
      }
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public File getFile() {
      return this.field2;
   }

   @Generated
   public DataOutputStream method3() {
      return this.field3;
   }
}
