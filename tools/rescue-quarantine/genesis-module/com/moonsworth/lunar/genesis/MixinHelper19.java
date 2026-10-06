package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper19 {
   private static final MixinHelper19 field1 = new MixinHelper19() {
      @Override
      public long read() {
         return MixinHelper14.systemNanoTime();
      }
   };

   protected MixinHelper19() {
   }

   public abstract long read();

   public static MixinHelper19 method1() {
      return field1;
   }
}
