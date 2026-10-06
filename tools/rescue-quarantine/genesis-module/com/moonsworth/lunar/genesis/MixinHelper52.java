package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;

@Immutable
abstract class MixinHelper52 implements MixinHelper5_8 {
   @Override
   public <T> HashCode method10(T var1, Funnel<? super T> var2) {
      return this.method1().method14(var1, var2).method15();
   }

   @Override
   public HashCode method8(CharSequence var1) {
      int var2 = var1.length();
      return this.method2(var2 * 2).method13(var1).method15();
   }

   @Override
   public HashCode method9(CharSequence var1, Charset var2) {
      return this.method1().method14(var1, var2).method15();
   }

   @Override
   public HashCode method3(int var1) {
      return this.method2(4).method7(var1).method15();
   }

   @Override
   public HashCode method4(long var1) {
      return this.method2(8).method8(var1).method15();
   }

   @Override
   public HashCode method5(byte[] var1) {
      return this.method6(var1, 0, var1.length);
   }

   @Override
   public HashCode method6(byte[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
      return this.method2(var3).method4(var1, var2, var3).method15();
   }

   @Override
   public HashCode method7(ByteBuffer var1) {
      return this.method2(var1.remaining()).method5(var1).method15();
   }

   @Override
   public MixinHelper42_2 method2(int var1) {
      Preconditions.checkArgument(var1 >= 0, "expectedInputSize must be >= 0 but was %s", var1);
      return this.method1();
   }
}
