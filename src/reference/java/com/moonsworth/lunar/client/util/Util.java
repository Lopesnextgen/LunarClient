package com.moonsworth.lunar.client.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.framework.codegen.TypeSpec;

public class Util {
   private static final String field1 = "    ";
   private final Path field2;
   private final List<TypeSpec> field3 = new ArrayList<>();

   public Util(Path var1) {
      this.field2 = var1;
   }

   public Util method1(TypeSpec var1) {
      this.field3.add(var1);
      return this;
   }

   public Util method2(TypeSpec... var1) {
      this.field3.addAll(List.of(var1));
      return this;
   }

   public Util method3(TypeSpec.Data var1) {
      this.field3.add(var1.method28());
      return this;
   }

   public Util method4(TypeSpec.Data... var1) {
      for (TypeSpec.Data var5 : var1) {
         this.method3(var5);
      }

      return this;
   }

   public void method5() {
      for (TypeSpec var2 : this.field3) {
         try {
            this.method6(var2.getPackageName(), var2.getClassName(), var2.method1(0));
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }
      }
   }

   private void method6(String var1, String var2, String var3) {
      Path var4 = this.field2;

      for (String var8 : var1.split("\\.")) {
         var4 = var4.resolve(var8);
      }

      var4 = var4.resolve(var2 + ".java");
      File var10 = new File(var4.toUri());
      var10.getParentFile().mkdirs();
      FileWriter var11 = new FileWriter(var10, false);
      var11.write(var3);
      var11.close();
   }

   public static String method7(int var0) {
      return "    ".repeat(var0);
   }

   public static String method8(String var0, int var1) {
      return "    ".repeat(var1) + var0;
   }
}
