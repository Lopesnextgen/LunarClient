package com.moonsworth.lunar.client.replay.project;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class ReplayCompression {
   private static final Deflater deflater = new Deflater(1);
   private static final Inflater inflater = new Inflater();
   private static final byte[] field1 = new byte[8192];

   public ReplayCompression() {
   }

   public static synchronized byte[] method1(byte[] items0) {
      deflater.reset();

      try (
         ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
         DeflaterOutputStream deflateroutputstream2 = new DeflaterOutputStream(bytearrayoutputstream1, deflater);
      ) {
         deflateroutputstream2.write(items0);
         deflateroutputstream2.finish();
         return bytearrayoutputstream1.toByteArray();
      } catch (IOException exception9) {
         exception9.printStackTrace();
         return items0;
      }
   }

   public static synchronized byte[] method2(byte[] items0) {
      inflater.reset();

      try (
         ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
         InflaterInputStream inflaterinputstream2 = new InflaterInputStream(new ByteArrayInputStream(items0), inflater);
      ) {
         int number3;
         while ((number3 = inflaterinputstream2.read(field1)) > 0) {
            bytearrayoutputstream1.write(field1, 0, number3);
         }

         return bytearrayoutputstream1.toByteArray();
      } catch (IOException exception9) {
         exception9.printStackTrace();
         return items0;
      }
   }

   static {
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
         deflater.end();
         inflater.end();
      }));
   }
}
