package com.moonsworth.lunar.genesis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.google.common.io.ByteStreams;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

final class ByteSource$SlicedByteSource extends MixinHelper12_2 {
   final long field1;
   final long field2;

   ByteSource$SlicedByteSource(MixinHelper12_2 mixinhelper12_21, long number2, long number4) {
      this.field3 = mixinhelper12_21;
      Preconditions.checkArgument(number2 >= 0L, "offset (%s) may not be negative", number2);
      Preconditions.checkArgument(number4 >= 0L, "length (%s) may not be negative", number4);
      this.field1 = number2;
      this.field2 = number4;
   }

   public InputStream openStream() {
      return this.sliceStream(this.field3.openStream());
   }

   public InputStream openBufferedStream() {
      return this.sliceStream(this.field3.openBufferedStream());
   }

   private InputStream sliceStream(InputStream input1) {
      if (this.field1 > 0L) {
         long number2;
         try {
            number2 = ByteStreams.skipUpTo(input1, this.field1);
         } catch (Throwable exception10) {
            Throwable exception4 = exception10;
            MixinHelper15 mixinhelper155 = MixinHelper15.method1();
            mixinhelper155.register(input1);

            try {
               throw mixinhelper155.rethrow(exception4);
            } finally {
               mixinhelper155.close();
            }
         }

         if (number2 < this.field1) {
            input1.close();
            return new ByteArrayInputStream(new byte[0]);
         }
      }

      return ByteStreams.limit(input1, this.field2);
   }

   public MixinHelper12_2 method2(long number1, long number3) {
      Preconditions.checkArgument(number1 >= 0L, "offset (%s) may not be negative", number1);
      Preconditions.checkArgument(number3 >= 0L, "length (%s) may not be negative", number3);
      long number5 = this.field2 - number1;
      return number5 <= 0L ? MixinHelper12_2.method12() : this.field3.method2(this.field1 + number1, Math.min(number3, number5));
   }

   public boolean isEmpty() {
      return this.field2 == 0L || super.isEmpty();
   }

   public Optional<Long> method3() {
      Optional serializablebase_21 = this.field3.method3();
      if (serializablebase_21.isPresent()) {
         long number2 = (Long)serializablebase_21.get();
         long number4 = Math.min(this.field1, number2);
         return Optional.method2(Math.min(this.field2, number2 - number4));
      } else {
         return Optional.method1();
      }
   }

   public String toString() {
      return this.field3.toString() + ".slice(" + this.field1 + ", " + this.field2 + ")";
   }
}
