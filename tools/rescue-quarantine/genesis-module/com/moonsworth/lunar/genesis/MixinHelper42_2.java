package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Funnel;

@Annotation2
@CanIgnoreReturnValue
public interface MixinHelper42_2 extends MixinHelper4_3 {
   MixinHelper42_2 method2(byte var1);

   MixinHelper42_2 method3(byte[] var1);

   MixinHelper42_2 method4(byte[] var1, int var2, int var3);

   MixinHelper42_2 method5(ByteBuffer var1);

   MixinHelper42_2 method6(short var1);

   MixinHelper42_2 method7(int var1);

   MixinHelper42_2 method8(long var1);

   MixinHelper42_2 method9(float var1);

   MixinHelper42_2 method10(double var1);

   MixinHelper42_2 method11(boolean var1);

   MixinHelper42_2 method12(char var1);

   MixinHelper42_2 method13(CharSequence var1);

   MixinHelper42_2 method14(CharSequence var1, Charset var2);

   <T> MixinHelper42_2 method14(T var1, Funnel<? super T> var2);

   HashCode method15();

   @Deprecated
   @Override
   int hashCode();
}
