package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.cache.Cache;
import com.google.common.base.Equivalence;
import com.google.common.cache.CacheBuilder;

class AbstractMapLoader$Data41<K, V> extends MixinHelper31<K, V> implements Serializable {
   private static final long field1 = 1L;
   final AbstractMapLoader$Type4 field2;
   final AbstractMapLoader$Type4 field3;
   final Equivalence<Object> field4;
   final Equivalence<Object> field5;
   final long field6;
   final long field7;
   final long field8;
   final MixinHelper2_5<K, V> field9;
   final int field10;
   final MixinHelper12_6<? super K, ? super V> field11;
   final @Nullable MixinHelper19 field12;
   final MixinHelper8_4<? super K, V> field13;
   transient @Nullable Cache<K, V> field14;

   AbstractMapLoader$Data41(AbstractMapLoader_2<K, V> var1) {
      this(
         var1.field13,
         var1.field14,
         var1.field11,
         var1.field12,
         var1.field18,
         var1.field17,
         var1.field15,
         var1.field16,
         var1.field10,
         var1.field21,
         var1.field22,
         var1.field25
      );
   }

   private AbstractMapLoader$Data41(
      AbstractMapLoader$Type4 var1,
      AbstractMapLoader$Type4 var2,
      Equivalence<Object> var3,
      Equivalence<Object> var4,
      long var5,
      long var7,
      long var9,
      MixinHelper2_5<K, V> var11,
      int var12,
      MixinHelper12_6<? super K, ? super V> var13,
      MixinHelper19 var14,
      MixinHelper8_4<? super K, V> var15
   ) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var4;
      this.field6 = var5;
      this.field7 = var7;
      this.field8 = var9;
      this.field9 = var11;
      this.field10 = var12;
      this.field11 = var13;
      this.field12 = var14 != MixinHelper19.method1() && var14 != CacheBuilder.field8 ? var14 : null;
      this.field13 = var15;
   }

   CacheBuilder<K, V> method3() {
      CacheBuilder var1 = CacheBuilder.method1()
         .method16(this.field2)
         .method20(this.field3)
         .method5(this.field4)
         .method7(this.field5)
         .method10(this.field10)
         .method30(this.field11);
      var1.strictParsing = false;
      if (this.field6 > 0L) {
         var1.method23(this.field6, TimeUnit.NANOSECONDS);
      }

      if (this.field7 > 0L) {
         var1.method25(this.field7, TimeUnit.NANOSECONDS);
      }

      if (this.field9 != MixinHelper5$Type3.INSTANCE) {
         var1.method13(this.field9);
         if (this.field8 != -1L) {
            var1.method12(this.field8);
         }
      } else if (this.field8 != -1L) {
         var1.method11(this.field8);
      }

      if (this.field12 != null) {
         var1.method28(this.field12);
      }

      return var1;
   }

   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      CacheBuilder var2 = this.method3();
      this.field14 = var2.method35();
   }

   private Object readResolve() {
      return this.field14;
   }

   @Override
   protected Cache<K, V> method1() {
      return this.field14;
   }
}
