package com.moonsworth.lunar.client.util.io;

import java.io.File;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import lombok.Generated;

public final class AtomicFileWriter {
   public static void method1(File file0, String text1) {
      file0.getParentFile().mkdirs();
      File file2 = new File(file0.getParentFile(), file0.getName() + ".tmp");
      Files.writeString(file2.toPath(), text1);

      try {
         Files.move(file2.toPath(), file0.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
      } catch (AtomicMoveNotSupportedException atomicmovenotsupportedexception4) {
         Files.move(file2.toPath(), file0.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
   }

   @Generated
   private AtomicFileWriter() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
