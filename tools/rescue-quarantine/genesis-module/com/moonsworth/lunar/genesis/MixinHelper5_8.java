package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Funnel;

@Immutable
@Annotation2
public interface MixinHelper5_8 {
   MixinHelper42_2 method1();

   MixinHelper42_2 method2(int var1);

   HashCode method3(int var1);

   HashCode method4(long var1);

   HashCode method5(byte[] var1);

   HashCode method6(byte[] var1, int var2, int var3);

   HashCode method7(ByteBuffer var1);

   HashCode method8(CharSequence var1);

   HashCode method9(CharSequence var1, Charset var2);

   <T> HashCode method10(T var1, Funnel<? super T> var2);

   int bits();
}
