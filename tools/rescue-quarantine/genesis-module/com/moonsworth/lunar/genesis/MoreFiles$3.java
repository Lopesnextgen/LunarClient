package com.moonsworth.lunar.genesis;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import com.google.common.base.Predicate;

final class MoreFiles$3 implements Predicate<Path> {
   MoreFiles$3(LinkOption[] items1) {
      this.field1 = items1;
   }

   public boolean apply(Path path1) {
      return Files.isRegularFile(path1, this.field1);
   }

   @Override
   public String toString() {
      return "MoreFiles.isRegularFile(" + Arrays.toString(this.field1) + ")";
   }
}
