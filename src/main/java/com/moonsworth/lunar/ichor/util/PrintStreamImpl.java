package com.moonsworth.lunar.ichor.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class PrintStreamImpl extends PrintStream {
   private final PrintStream field1;
   private final boolean field2;
   private final Path field3;

   public PrintStreamImpl(PrintStream var1, String text, boolean var3) {
      super(var1);
      this.field1 = var1;
      this.field2 = System.out == var1;
      this.field3 = new File(text).toPath();

      try {
         Files.createDirectories(this.field3.getParent());
         if (!var3) {
            Files.write(this.field3, new byte[0], StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         } else if (!Files.exists(this.field3)) {
            Files.createFile(this.field3);
         }
      } catch (IOException var5) {
         throw new RuntimeException("Failed to create log file", var5);
      }
   }

   @Override
   public void println(@Nullable String var1) {
      super.println(var1);
      this.method1("\n");
   }

   @Override
   public void println(Object var1) {
      super.println(var1);
      this.method1("\n");
   }

   @Override
   public void print(String var1) {
      super.print(var1);
      this.method1(var1);
   }

   @Override
   public void print(Object var1) {
      super.print(var1);
      this.method1(String.valueOf(var1));
   }

   protected void method1(@Nullable String var1) {
      if (var1 != null) {
         if ((!this.field2 || System.out instanceof PrintStreamImpl) && (this.field2 || System.err instanceof PrintStreamImpl)) {
            try {
               Files.write(this.field3, var1.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException var3) {
               throw new RuntimeException(var3);
            }
         }
      }
   }

   @Generated
   public PrintStream method2() {
      return this.field1;
   }
}
