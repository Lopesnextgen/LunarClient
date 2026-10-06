package com.moonsworth.lunar.ichor.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class TeePrintStream extends PrintStream {
   private final PrintStream field1;
   private final boolean field2;
   private final Path field3;

   public TeePrintStream(PrintStream stream1, String text, boolean flag) {
      super(stream1);
      this.field1 = stream1;
      this.field2 = System.out == stream1;
      this.field3 = new File(text).toPath();

      try {
         Files.createDirectories(this.field3.getParent());
         if (!flag) {
            Files.write(this.field3, new byte[0], StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         } else if (!Files.exists(this.field3)) {
            Files.createFile(this.field3);
         }
      } catch (IOException exception5) {
         throw new RuntimeException("Failed to create log file", exception5);
      }
   }

   @Override
   public void println(@Nullable String text1) {
      super.println(text1);
      this.method1("\n");
   }

   @Override
   public void println(Object obj1) {
      super.println(obj1);
      this.method1("\n");
   }

   @Override
   public void print(String text1) {
      super.print(text1);
      this.method1(text1);
   }

   @Override
   public void print(Object obj1) {
      super.print(obj1);
      this.method1(String.valueOf(obj1));
   }

   protected void method1(@Nullable String text1) {
      if (text1 != null) {
         if ((!this.field2 || System.out instanceof TeePrintStream) && (this.field2 || System.err instanceof TeePrintStream)) {
            try {
               Files.write(this.field3, text1.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException exception3) {
               throw new RuntimeException(exception3);
            }
         }
      }
   }

   @Generated
   public PrintStream method2() {
      return this.field1;
   }
}
