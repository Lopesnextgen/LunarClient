package com.moonsworth.lunar.client.framework.feature.crosshair;

import com.moonsworth.lunar.client.util.LunarLogger;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Crosshair {
   private final String field1;
   private final int field2;
   private final byte[] field3;
   private static final String field4 = "#";

   public Crosshair(String text1, int number2, byte[] items3) {
      this.field1 = text1;
      this.field2 = number2;
      this.field3 = items3;
   }

   @Nullable
   public static Crosshair method1(String text0) {
      String[] items1 = text0.trim().split("-");
      if (items1.length != 3) {
         return null;
      }

      String text2 = items1[0];
      if (!"LCCH".equals(text2) && !"LCCS".equals(text2)) {
         return null;
      }

      int number3;
      try {
         number3 = Integer.parseInt(items1[1]);
      } catch (NumberFormatException numberformatexception7) {
         return null;
      }

      byte[] items4;
      try {
         items4 = decode(items1[2]);
      } catch (DataFormatException dataformatexception6) {
         LunarLogger.warn("Crosshair code data parse failed " + text0, dataformatexception6);
         return null;
      }

      return new Crosshair(text2, number3, items4);
   }

   @Override
   public String toString() {
      if (!this.field1.equals("LCCH") && !this.field1.equals("LCCS")) {
         throw new IllegalArgumentException("Invalid crosshair type " + this.field1);
      } else {
         return this.field1 + "-" + this.field2 + "-" + encode(this.field3);
      }
   }

   @NotNull
   private static String encode(byte[] items0) {
      Deflater deflater1 = new Deflater();
      deflater1.setLevel(9);
      deflater1.setInput(items0);
      deflater1.finish();
      ByteArrayOutputStream bytearrayoutputstream2 = new ByteArrayOutputStream();
      byte[] items3 = new byte[1024];

      while (!deflater1.finished()) {
         int number4 = deflater1.deflate(items3);
         bytearrayoutputstream2.write(items3, 0, number4);
      }

      deflater1.end();
      String text6 = Base64.getEncoder().encodeToString(items0);
      String text5 = Base64.getEncoder().encodeToString(bytearrayoutputstream2.toByteArray()) + "#";
      return text5.length() < text6.length() ? text5 : text6;
   }

   @NotNull
   private static byte[] decode(String text0) {
      if (!text0.endsWith("#")) {
         return Base64.getDecoder().decode(text0);
      }

      text0 = text0.substring(0, text0.length() - 1);
      byte[] items1 = Base64.getDecoder().decode(text0);
      Inflater inflater2 = new Inflater();
      inflater2.setInput(items1);
      ByteArrayOutputStream bytearrayoutputstream3 = new ByteArrayOutputStream();
      byte[] items4 = new byte[1024];

      while (!inflater2.finished()) {
         int number5 = inflater2.inflate(items4);
         bytearrayoutputstream3.write(items4, 0, number5);
      }

      inflater2.end();
      return bytearrayoutputstream3.toByteArray();
   }

   public String type() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }

   public byte[] method3() {
      return this.field3;
   }
}
