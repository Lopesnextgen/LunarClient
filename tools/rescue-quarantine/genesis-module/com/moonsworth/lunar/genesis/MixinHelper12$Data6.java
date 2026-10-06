package com.moonsworth.lunar.genesis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.base.Preconditions;

final class MixinHelper12$Data6 extends ByteSource {
   final long field1;
   final long field2;

   MixinHelper12$Data6(ByteSource var1, long var2, long var4) {
      this.field3 = var1;
      Preconditions.checkArgument(var2 >= 0L, "offset (%s) may not be negative", var2);
      Preconditions.checkArgument(var4 >= 0L, "length (%s) may not be negative", var4);
      this.field1 = var2;
      this.field2 = var4;
   }

   @Override
   public InputStream openStream() {
      return this.sliceStream(this.field3.openStream());
   }

   @Override
   public InputStream openBufferedStream() {
      return this.sliceStream(this.field3.openBufferedStream());
   }

   private InputStream sliceStream(InputStream var1) {
      if (this.field1 > 0L) {
         long var2;
         try {
            var2 = ByteStreams.skipUpTo(var1, this.field1);
         } catch (Throwable var10) {
            Throwable var4 = var10;
            MixinHelper15 var5 = MixinHelper15.method1();
            var5.register(var1);

            try {
               throw var5.rethrow(var4);
            } finally {
               var5.close();
            }
         }

         if (var2 < this.field1) {
            var1.close();
            return new ByteArrayInputStream(new byte[0]);
         }
      }

      return ByteStreams.limit(var1, this.field2);
   }

   @Override
   public ByteSource method2(long var1, long var3) {
      Preconditions.checkArgument(var1 >= 0L, "offset (%s) may not be negative", var1);
      Preconditions.checkArgument(var3 >= 0L, "length (%s) may not be negative", var3);
      long var5 = this.field2 - var1;
      return var5 <= 0L ? ByteSource.method12() : this.field3.method2(this.field1 + var1, Math.min(var3, var5));
   }

   @Override
   public boolean isEmpty() {
      return this.field2 == 0L || super.isEmpty();
   }

   @Override
   public SerializableBase_2<Long> method3() {
      SerializableBase_2 var1 = this.field3.method3();
      if (var1.isPresent()) {
         long var2 = (Long)var1.get();
         long var4 = Math.min(this.field1, var2);
         return SerializableBase_2.method2(Math.min(this.field2, var2 - var4));
      } else {
         return SerializableBase_2.method1();
      }
   }

   @Override
   public String toString() {
      return this.field3.toString() + ".slice(" + this.field1 + ", " + this.field2 + ")";
   }
}
