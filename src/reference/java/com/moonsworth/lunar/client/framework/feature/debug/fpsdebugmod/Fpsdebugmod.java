package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Fpsdebugmod {
   private final List<Fpsdebugmod.Data> field1 = new ArrayList<>();
   private final List<Fpsdebugmod.Data2> field2 = new ArrayList<>();

   public Fpsdebugmod method1(String var1, byte[] var2) {
      this.field1.add(new Fpsdebugmod.Data(var1, var2));
      return this;
   }

   public Fpsdebugmod method2(String var1, String var2) {
      return this.method1(var1, var2.getBytes(StandardCharsets.UTF_8));
   }

   public Fpsdebugmod method3(Fpsdebugmod var1) {
      this.field1.addAll(var1.field1);
      this.field2.addAll(var1.field2);
      return this;
   }

   public Fpsdebugmod method4(String var1, Exception var2) {
      this.field2.add(new Fpsdebugmod.Data2(var1, var2));
      Inventorymod2.method5(var2, "FpsDataWithError");
      return this;
   }

   public void method5(ZipOutputStream var1) {
      HashSet var2 = new HashSet();
      ArrayList var3 = new ArrayList<>(this.field1);
      if (!this.field2.isEmpty()) {
         String var4 = this.field2.stream().map(var0 -> var0.field1 + "\n" + var0.field2.getMessage()).collect(Collectors.joining("\n\n"));
         var3.add(new Fpsdebugmod.Data("errors.txt", var4.getBytes(StandardCharsets.UTF_8)));
      }

      for (Fpsdebugmod.Data var5 : var3) {
         String var6 = this.method6(var5.field1, var2);
         var2.add(var6);
         var1.putNextEntry(new ZipEntry(var6));
         var1.write(var5.field2);
         var1.closeEntry();
      }
   }

   private String method6(String var1, Set<String> var2) {
      int var3 = -1;
      String var4 = var1;
      String var5 = var1.substring(0, var1.lastIndexOf(46));
      String var6 = var1.substring(var1.lastIndexOf(46));

      while (var2.contains(var4)) {
         var4 = var5 + "-" + ++var3 + "." + var6;
      }

      return var4;
   }

   private class Data {
      private final String field1;
      private final byte[] field2;

      private Data(String var1, byte[] var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String method1() {
         return this.field1;
      }

      public byte[] method2() {
         return this.field2;
      }
   }

   private class Data2 {
      private final String field1;
      private final Exception field2;

      private Data2(String var1, Exception var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String method1() {
         return this.field1;
      }

      public Exception method2() {
         return this.field2;
      }
   }
}
