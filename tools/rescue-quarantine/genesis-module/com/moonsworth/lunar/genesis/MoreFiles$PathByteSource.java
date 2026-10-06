package com.moonsworth.lunar.genesis;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.base.Optional;
import com.google.common.io.ByteStreams;
import com.google.common.base.Preconditions;

final class MoreFiles$PathByteSource extends ByteSource {
   private static final LinkOption[] field1 = new LinkOption[0];
   private final Path field2;
   private final OpenOption[] field3;
   private final boolean field4;

   private MoreFiles$PathByteSource(Path path1, OpenOption... items2) {
      this.field2 = (Path)Preconditions.checkNotNull(path1);
      this.field3 = (OpenOption[])items2.clone();
      this.field4 = followLinks(this.field3);
   }

   private static boolean followLinks(OpenOption[] items0) {
      for (OpenOption openoption4 : items0) {
         if (openoption4 == LinkOption.NOFOLLOW_LINKS) {
            return false;
         }
      }

      return true;
   }

   public InputStream openStream() {
      return Files.newInputStream(this.field2, this.field3);
   }

   private BasicFileAttributes readAttributes() {
      return Files.readAttributes(this.field2, BasicFileAttributes.class, this.field4 ? field1 : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
   }

   public Optional<Long> method3() {
      BasicFileAttributes basicfileattributes1;
      try {
         basicfileattributes1 = this.readAttributes();
      } catch (IOException exception3) {
         return Optional.method1();
      }

      return !basicfileattributes1.isDirectory() && !basicfileattributes1.isSymbolicLink() ? Optional.method2(basicfileattributes1.size()) : Optional.method1();
   }

   public long size() {
      BasicFileAttributes basicfileattributes1 = this.readAttributes();
      if (basicfileattributes1.isDirectory()) {
         throw new IOException("can't read: is a directory");
      } else if (basicfileattributes1.isSymbolicLink()) {
         throw new IOException("can't read: is a symbolic link");
      } else {
         return basicfileattributes1.size();
      }
   }

   public byte[] read() {
      try (SeekableByteChannel seekablebytechannel1 = Files.newByteChannel(this.field2, this.field3)) {
         return ByteStreams.toByteArray(Channels.newInputStream(seekablebytechannel1), seekablebytechannel1.size());
      }
   }

   public CharSource method1(Charset charset1) {
      return (CharSource)(this.field3.length == 0 ? new Data$1(this, charset1) : super.method1(charset1));
   }

   public String toString() {
      return "MoreFiles.asByteSource(" + this.field2 + ", " + Arrays.toString(this.field3) + ")";
   }
}
