package com.moonsworth.lunar.genesis;

import java.io.File;
import java.io.FileOutputStream;
import com.google.common.io.ByteSink;
import com.google.common.io.FileWriteMode;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;

final class Files$FileByteSink extends ByteSink {
   private final File field1;
   private final ImmutableSet<FileWriteMode> field2;

   private Files$FileByteSink(File file1, FileWriteMode... items2) {
      this.field1 = (File)Preconditions.checkNotNull(file1);
      this.field2 = ImmutableSet.method13(items2);
   }

   public FileOutputStream openStream() {
      return new FileOutputStream(this.field1, this.field2.contains(FileWriteMode.APPEND));
   }

   public String toString() {
      return "Files.asByteSink(" + this.field1 + ", " + this.field2 + ")";
   }
}
