package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class MixinHelper5_2 {
   private static final ThreadLocal<char[]> field1 = new ThreadLocal<char[]>() {
      protected char[] initialValue() {
         return new char[1024];
      }
   };

   private MixinHelper5_2() {
   }

   static char[] charBufferFromThreadLocal() {
      return field1.get();
   }
}
