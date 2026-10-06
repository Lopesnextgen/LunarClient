package com.moonsworth.lunar.genesis;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;
import com.google.common.hash.Funnel;

class AbstractCompositeHashFunction$1 implements Hasher {
   AbstractCompositeHashFunction$1(AbstractCompositeHashFunction mixinhelper5291, Hasher[] items2) {
      this.field2 = mixinhelper5291;
      this.field1 = items2;
   }

   public Hasher method2(byte number1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method2(number1);
      }

      return this;
   }

   public Hasher method3(byte[] items1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method3(items1);
      }

      return this;
   }

   public Hasher method4(byte[] items1, int number2, int number3) {
      for (Hasher mixinhelper42_27 : this.field1) {
         mixinhelper42_27.method4(items1, number2, number3);
      }

      return this;
   }

   public Hasher method5(ByteBuffer buffer1) {
      int number2 = buffer1.position();

      for (Hasher mixinhelper42_26 : this.field1) {
         ((Buffer)buffer1).position(number2);
         mixinhelper42_26.method5(buffer1);
      }

      return this;
   }

   public Hasher method6(short number1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method6(number1);
      }

      return this;
   }

   public Hasher method7(int number1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method7(number1);
      }

      return this;
   }

   public Hasher method8(long number1) {
      for (Hasher mixinhelper42_26 : this.field1) {
         mixinhelper42_26.method8(number1);
      }

      return this;
   }

   public Hasher method9(float value1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method9(value1);
      }

      return this;
   }

   public Hasher method10(double value1) {
      for (Hasher mixinhelper42_26 : this.field1) {
         mixinhelper42_26.method10(value1);
      }

      return this;
   }

   public Hasher method11(boolean flag1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method11(flag1);
      }

      return this;
   }

   public Hasher method12(char character1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method12(character1);
      }

      return this;
   }

   public Hasher method13(CharSequence text1) {
      for (Hasher mixinhelper42_25 : this.field1) {
         mixinhelper42_25.method13(text1);
      }

      return this;
   }

   public Hasher method14(CharSequence text1, Charset charset2) {
      for (Hasher mixinhelper42_26 : this.field1) {
         mixinhelper42_26.method14(text1, charset2);
      }

      return this;
   }

   public <T> Hasher method14(T value1, Funnel<? super T> serializableextension2) {
      for (Hasher mixinhelper42_26 : this.field1) {
         mixinhelper42_26.method14(value1, serializableextension2);
      }

      return this;
   }

   public HashCode method15() {
      return this.field2.method1(this.field1);
   }
}
