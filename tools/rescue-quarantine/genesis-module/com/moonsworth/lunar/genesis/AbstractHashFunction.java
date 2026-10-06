package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashFunction;
import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;

@Immutable
abstract class AbstractHashFunction implements HashFunction {
   AbstractHashFunction() {
   }

   public <T> HashCode method10(T value1, Funnel<? super T> serializableextension2) {
      return this.HCOOHHIHIHIIHORHIHRORHCICRRRIH().method14(value1, serializableextension2).method15();
   }

   public HashCode method8(CharSequence text1) {
      int number2 = text1.length();
      return this.method2(number2 * 2).method13(text1).method15();
   }

   public HashCode method9(CharSequence text1, Charset charset2) {
      return this.HCOOHHIHIHIIHORHIHRORHCICRRRIH().method14(text1, charset2).method15();
   }

   public HashCode method3(int number1) {
      return this.method2(4).method7(number1).method15();
   }

   public HashCode method4(long number1) {
      return this.method2(8).method8(number1).method15();
   }

   public HashCode method5(byte[] items1) {
      return this.method6(items1, 0, items1.length);
   }

   public HashCode method6(byte[] items1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number2 + number3, items1.length);
      return this.method2(number3).method4(items1, number2, number3).method15();
   }

   public HashCode method7(ByteBuffer buffer1) {
      return this.method2(buffer1.remaining()).method5(buffer1).method15();
   }

   public Hasher method2(int number1) {
      Preconditions.checkArgument(number1 >= 0, "expectedInputSize must be >= 0 but was %s", number1);
      return this.HCOOHHIHIHIIHORHIHRORHCICRRRIH();
   }
}
