package com.moonsworth.lunar.client.framework.feature.rewind;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class Rewind2_2 {
   private static final Deflater deflater = new Deflater(1);
   private static final Inflater inflater = new Inflater();
   private static final byte[] field1 = new byte[8192];

   public static synchronized byte[] method1(byte[] var0) {
      deflater.reset();

      try (
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DeflaterOutputStream var2 = new DeflaterOutputStream(var1, deflater);
      ) {
         var2.write(var0);
         var2.finish();
         return var1.toByteArray();
      } catch (IOException var9) {
         var9.printStackTrace();
         return var0;
      }
   }

   public static synchronized byte[] method2(byte[] var0) {
      inflater.reset();

      try (
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         InflaterInputStream var2 = new InflaterInputStream(new ByteArrayInputStream(var0), inflater);
      ) {
         int var3;
         while ((var3 = var2.read(field1)) > 0) {
            var1.write(field1, 0, var3);
         }

         return var1.toByteArray();
      } catch (IOException var9) {
         var9.printStackTrace();
         return var0;
      }
   }

   static {
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
         deflater.end();
         inflater.end();
      }));
   }
}
