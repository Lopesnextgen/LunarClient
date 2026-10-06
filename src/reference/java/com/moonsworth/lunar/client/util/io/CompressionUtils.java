package com.moonsworth.lunar.client.util.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.Generated;

public final class CompressionUtils {
   public static String method1(String text0) {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
      GZIPOutputStream gzipoutputstream2 = new GZIPOutputStream(bytearrayoutputstream1);
      gzipoutputstream2.write(text0.getBytes(StandardCharsets.UTF_8));
      gzipoutputstream2.close();
      return Base64.getEncoder().encodeToString(bytearrayoutputstream1.toByteArray());
   }

   public static String method2(String text0) {
      GZIPInputStream gzipinputstream1 = new GZIPInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(text0.getBytes())));
      BufferedReader reader2 = new BufferedReader(new InputStreamReader(gzipinputstream1, StandardCharsets.UTF_8));
      StringBuilder builder3 = new StringBuilder();

      String text4;
      while ((text4 = reader2.readLine()) != null) {
         builder3.append(text4);
      }

      return builder3.toString();
   }

   @Generated
   private CompressionUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
