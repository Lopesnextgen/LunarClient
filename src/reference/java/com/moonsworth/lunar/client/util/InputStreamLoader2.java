package com.moonsworth.lunar.client.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;
import lombok.Generated;

public class InputStreamLoader2 extends InputStream {
   private final InputStream field1;
   private ByteArrayOutputStream field2 = new ByteArrayOutputStream();
   private byte[] field3 = null;
   private int position = 0;
   private int mark = 0;

   public InputStreamLoader2(InputStream var1) {
      this.field1 = var1;
   }

   @Override
   public synchronized int read() {
      byte[] var1 = new byte[1];
      int var2 = this.read(var1, 0, 1);
      return var2 == -1 ? -1 : var1[0] & 0xFF;
   }

   @Override
   public synchronized int read(byte[] var1, int var2, int var3) {
      Objects.checkFromIndexSize(var2, var3, var1.length);
      if (var3 == 0) {
         return 0;
      }

      int var4 = this.position + var3;
      if (this.field2 != null && var4 > this.field2.size()) {
         int var5 = var4 - this.field2.size();
         byte[] var6 = new byte[Math.max(8192, var5)];

         int var7;
         while (this.field2.size() < var4 && (var7 = this.field1.read(var6)) != -1) {
            this.field2.write(var6, 0, var7);
         }

         if (this.field2.size() < var4) {
            this.field3 = this.field2.toByteArray();
            this.field1.close();
            this.field2 = null;
         }
      }

      byte[] var8 = this.field2 == null ? this.field3 : this.field2.toByteArray();
      if (this.position >= var8.length) {
         return -1;
      }

      int var9 = Math.min(var3, var8.length - this.position);
      System.arraycopy(var8, this.position, var1, var2, var9);
      this.position += var9;
      return var9;
   }

   @Override
   public int available() {
      return this.field3 != null ? this.field3.length - this.position : this.field1.available() + (this.field2.size() - this.position);
   }

   @Override
   public boolean markSupported() {
      return true;
   }

   @Override
   public synchronized void mark(int var1) {
      this.mark = this.position;
   }

   @Override
   public synchronized void reset() {
      this.position = this.mark;
      this.mark = 0;
   }

   @Override
   public void close() {
      this.field1.close();
      super.close();
   }

   @Generated
   public int getPosition() {
      return this.position;
   }
}
