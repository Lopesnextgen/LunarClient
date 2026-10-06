package com.moonsworth.lunar.client.util.highlight;

import java.io.File;
import java.util.Set;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public final class FilenameFilter implements java.io.FilenameFilter {
   private final Set<String> field1;

   @Override
   public boolean accept(File var1, String text) {
      return this.field1.contains(FilenameUtils.getExtension(text));
   }

   public static FilenameFilter method1(String... items) {
      return new FilenameFilter(Set.of(items));
   }

   @Generated
   private FilenameFilter(Set<String> var1) {
      this.field1 = var1;
   }
}
