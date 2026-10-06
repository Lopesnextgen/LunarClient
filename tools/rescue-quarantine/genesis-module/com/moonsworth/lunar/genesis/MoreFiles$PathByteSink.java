package com.moonsworth.lunar.genesis;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import com.google.common.io.ByteSink;
import com.google.common.base.Preconditions;

final class MoreFiles$PathByteSink extends ByteSink {
   private final Path field1;
   private final OpenOption[] field2;

   private MoreFiles$PathByteSink(Path path1, OpenOption... items2) {
      this.field1 = (Path)Preconditions.checkNotNull(path1);
      this.field2 = (OpenOption[])items2.clone();
   }

   public OutputStream openStream() {
      return Files.newOutputStream(this.field1, this.field2);
   }

   public String toString() {
      return "MoreFiles.asByteSink(" + this.field1 + ", " + Arrays.toString(this.field2) + ")";
   }
}
