package com.moonsworth.lunar.client.framework.feature.screenshot;

import java.io.ByteArrayInputStream;
import java.util.function.LongConsumer;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ByteArrayInputStreamLoader extends ByteArrayInputStream {
   private long count;
   private LongConsumer field1;

   public ByteArrayInputStreamLoader(byte[] items1) {
      super(items1);
   }

   @Override
   public synchronized int read() {
      int number1 = super.read();
      if (number1 != -1) {
         this.count++;
         if (this.field1 != null) {
            this.field1.accept(this.count);
         }
      }

      return number1;
   }

   @Override
   public int read(@NotNull byte[] items1) {
      int number2 = super.read(items1);
      if (number2 != -1) {
         this.count += number2;
         if (this.field1 != null) {
            this.field1.accept(this.count);
         }
      }

      return number2;
   }

   @Override
   public synchronized int read(byte[] items1, int index2, int index3) {
      int number4 = super.read(items1, index2, index3);
      if (number4 != -1) {
         this.count += number4;
         if (this.field1 != null) {
            this.field1.accept(this.count);
         }
      }

      return number4;
   }

   @Generated
   public long getCount() {
      return this.count;
   }

   @Generated
   public void method1(LongConsumer longconsumer1) {
      this.field1 = longconsumer1;
   }
}
