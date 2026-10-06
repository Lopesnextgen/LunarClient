package com.moonsworth.lunar.genesis;

import java.io.File;

final class Files$2 extends MixinHelper17<File> {
   Files$2() {
   }

   public Iterable<File> children(File file1) {
      return MixinHelper16.access$200(file1);
   }

   public String toString() {
      return "Files.fileTreeTraverser()";
   }
}
