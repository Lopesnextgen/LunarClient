package com.moonsworth.lunar.client.framework.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JavaFileWriter {
   private static final String field1 = "    ";
   private final Path field2;
   private final List<TypeSpec> field3 = new ArrayList<>();

   public JavaFileWriter(Path path1) {
      this.field2 = path1;
   }

   public JavaFileWriter method1(TypeSpec mixinhelper2221) {
      this.field3.add(mixinhelper2221);
      return this;
   }

   public JavaFileWriter method2(TypeSpec... items1) {
      this.field3.addAll(List.of(items1));
      return this;
   }

   public JavaFileWriter method3(TypeSpec.Data data1) {
      this.field3.add(data1.method28());
      return this;
   }

   public JavaFileWriter method4(TypeSpec.Data... items1) {
      for (TypeSpec.Data data5 : items1) {
         this.method3(data5);
      }

      return this;
   }

   public void method5() {
      for (TypeSpec mixinhelper2222 : this.field3) {
         try {
            this.method6(mixinhelper2222.getPackageName(), mixinhelper2222.getClassName(), mixinhelper2222.method1(0));
         } catch (IOException exception4) {
            throw new RuntimeException(exception4);
         }
      }
   }

   private void method6(String text1, String text2, String text3) {
      Path path4 = this.field2;

      for (String text8 : text1.split("\\.")) {
         path4 = path4.resolve(text8);
      }

      path4 = path4.resolve(text2 + ".java");
      File file10 = new File(path4.toUri());
      file10.getParentFile().mkdirs();
      FileWriter filewriter11 = new FileWriter(file10, false);
      filewriter11.write(text3);
      filewriter11.close();
   }

   public static String method7(int number0) {
      return "    ".repeat(number0);
   }

   public static String method8(String text0, int number1) {
      return "    ".repeat(number1) + text0;
   }
}
