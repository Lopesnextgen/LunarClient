package com.moonsworth.lunar.client.util.io;

import java.io.File;
import java.util.Set;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public final class FileExtensionFilter implements java.io.FilenameFilter {
   private final Set<String> field1;

   @Override
   public boolean accept(File file1, String text) {
      return this.field1.contains(FilenameUtils.getExtension(text));
   }

   public static FileExtensionFilter method1(String... items0) {
      return new FileExtensionFilter(Set.of(items0));
   }

   @Generated
   private FileExtensionFilter(Set<String> set) {
      this.field1 = set;
   }
}
