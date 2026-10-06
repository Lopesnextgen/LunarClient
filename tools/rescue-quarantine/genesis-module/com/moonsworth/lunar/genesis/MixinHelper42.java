package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Booleans;

@GwtCompatible
public abstract class MixinHelper42 {
   private static final MixinHelper42 field1 = new MixinHelper42() {
      @Override
      public MixinHelper42 method2(Comparable var1, Comparable var2) {
         return this.method9(var1.compareTo(var2));
      }

      @Override
      public <T> MixinHelper42 method3(@Nullable T var1, @Nullable T var2, java.util.Comparator<T> var3) {
         return this.method9(var3.compare(var1, var2));
      }

      @Override
      public MixinHelper42 method4(int var1, int var2) {
         return this.method9(MixinHelper122.compare(var1, var2));
      }

      @Override
      public MixinHelper42 method5(long var1, long var3) {
         return this.method9(Longs.compare(var1, var3));
      }

      @Override
      public MixinHelper42 method6(float var1, float var2) {
         return this.method9(Float.compare(var1, var2));
      }

      @Override
      public MixinHelper42 method7(double var1, double var3) {
         return this.method9(Double.compare(var1, var3));
      }

      @Override
      public MixinHelper42 method9(boolean var1, boolean var2) {
         return this.method9(Booleans.compare(var2, var1));
      }

      @Override
      public MixinHelper42 method10(boolean var1, boolean var2) {
         return this.method9(Booleans.compare(var1, var2));
      }

      MixinHelper42 method9(int var1) {
         return var1 < 0 ? MixinHelper42.field2 : (var1 > 0 ? MixinHelper42.field3 : MixinHelper42.field1);
      }

      @Override
      public int result() {
         return 0;
      }
   };
   private static final MixinHelper42 field2 = new MixinHelper42.Data2(-1);
   private static final MixinHelper42 field3 = new MixinHelper42.Data2(1);

   private MixinHelper42() {
   }

   public static MixinHelper42 method1() {
      return field1;
   }

   public abstract MixinHelper42 method2(Comparable<?> var1, Comparable<?> var2);

   public abstract <T> MixinHelper42 method3(@Nullable T var1, @Nullable T var2, java.util.Comparator<T> var3);

   public abstract MixinHelper42 method4(int var1, int var2);

   public abstract MixinHelper42 method5(long var1, long var3);

   public abstract MixinHelper42 method6(float var1, float var2);

   public abstract MixinHelper42 method7(double var1, double var3);

   @Deprecated
   public final MixinHelper42 method8(Boolean var1, Boolean var2) {
      return this.method10(var1, var2);
   }

   public abstract MixinHelper42 method9(boolean var1, boolean var2);

   public abstract MixinHelper42 method10(boolean var1, boolean var2);

   public abstract int result();

   private static final class Data2 extends MixinHelper42 {
      final int field4;

      Data2(int var1) {
         this.field4 = var1;
      }

      @Override
      public MixinHelper42 method2(@Nullable Comparable var1, @Nullable Comparable var2) {
         return this;
      }

      @Override
      public <T> MixinHelper42 method3(@Nullable T var1, @Nullable T var2, @Nullable Ordering<T> var3) {
         return this;
      }

      @Override
      public MixinHelper42 method4(int var1, int var2) {
         return this;
      }

      @Override
      public MixinHelper42 method5(long var1, long var3) {
         return this;
      }

      @Override
      public MixinHelper42 method6(float var1, float var2) {
         return this;
      }

      @Override
      public MixinHelper42 method7(double var1, double var3) {
         return this;
      }

      @Override
      public MixinHelper42 method9(boolean var1, boolean var2) {
         return this;
      }

      @Override
      public MixinHelper42 method10(boolean var1, boolean var2) {
         return this;
      }

      @Override
      public int result() {
         return this.field4;
      }
   }
}
