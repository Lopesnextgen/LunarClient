package com.moonsworth.lunar.client.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.Generated;

public final class ThreadModuleDump52 {
   public static String method1(String var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      GZIPOutputStream var2 = new GZIPOutputStream(var1);
      var2.write(var0.getBytes(StandardCharsets.UTF_8));
      var2.close();
      return Base64.getEncoder().encodeToString(var1.toByteArray());
   }

   public static String method2(String var0) {
      GZIPInputStream var1 = new GZIPInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(var0.getBytes())));
      BufferedReader var2 = new BufferedReader(new InputStreamReader(var1, StandardCharsets.UTF_8));
      StringBuilder var3 = new StringBuilder();

      String var4;
      while ((var4 = var2.readLine()) != null) {
         var3.append(var4);
      }

      return var3.toString();
   }

   @Generated
   private ThreadModuleDump52() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
