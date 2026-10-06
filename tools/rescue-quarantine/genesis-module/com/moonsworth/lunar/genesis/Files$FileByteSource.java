package com.moonsworth.lunar.genesis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.google.common.io.ByteSource;
import com.google.common.base.Optional;
import com.google.common.io.ByteStreams;
import com.google.common.base.Preconditions;

final class Files$FileByteSource extends ByteSource {
   private final File field1;

   private Files$FileByteSource(File file1) {
      this.field1 = (File)Preconditions.checkNotNull(file1);
   }

   public FileInputStream openStream() {
      return new FileInputStream(this.field1);
   }

   public Optional<Long> method3() {
      return this.field1.isFile() ? Optional.method2(this.field1.length()) : Optional.method1();
   }

   public long size() {
      if (!this.field1.isFile()) {
         throw new FileNotFoundException(this.field1.toString());
      } else {
         return this.field1.length();
      }
   }

   public byte[] read() {
      MixinHelper15 mixinhelper151 = MixinHelper15.method1();

      try {
         FileInputStream stream2 = mixinhelper151.register(this.openStream());
         return ByteStreams.toByteArray(stream2, stream2.getChannel().size());
      } catch (Throwable exception7) {
         throw mixinhelper151.rethrow(exception7);
      } finally {
         mixinhelper151.close();
      }
   }

   public String toString() {
      return "Files.asByteSource(" + this.field1 + ")";
   }
}
