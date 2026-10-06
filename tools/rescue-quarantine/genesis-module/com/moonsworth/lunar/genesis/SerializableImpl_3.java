package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Spliterators;
import java.util.Spliterator.OfLong;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;

@Immutable
@Annotation2
@GwtCompatible
public final class SerializableImpl_3 implements Serializable {
   private static final SerializableImpl_3 field1 = new SerializableImpl_3(new long[0]);
   private final long[] field2;
   private final transient int field3;
   private final int field4;

   public static SerializableImpl_3 method1() {
      return field1;
   }

   public static SerializableImpl_3 method2(long var0) {
      return new SerializableImpl_3(new long[]{var0});
   }

   public static SerializableImpl_3 method3(long var0, long var2) {
      return new SerializableImpl_3(new long[]{var0, var2});
   }

   public static SerializableImpl_3 method4(long var0, long var2, long var4) {
      return new SerializableImpl_3(new long[]{var0, var2, var4});
   }

   public static SerializableImpl_3 method5(long var0, long var2, long var4, long var6) {
      return new SerializableImpl_3(new long[]{var0, var2, var4, var6});
   }

   public static SerializableImpl_3 method6(long var0, long var2, long var4, long var6, long var8) {
      return new SerializableImpl_3(new long[]{var0, var2, var4, var6, var8});
   }

   public static SerializableImpl_3 method7(long var0, long var2, long var4, long var6, long var8, long var10) {
      return new SerializableImpl_3(new long[]{var0, var2, var4, var6, var8, var10});
   }

   public static SerializableImpl_3 method8(long var0, long... var2) {
      Preconditions.checkArgument(var2.length <= 2147483646, "the total number of elements must fit in an int");
      long[] var3 = new long[var2.length + 1];
      var3[0] = var0;
      System.arraycopy(var2, 0, var3, 1, var2.length);
      return new SerializableImpl_3(var3);
   }

   public static SerializableImpl_3 method9(long[] var0) {
      return var0.length == 0 ? field1 : new SerializableImpl_3(Arrays.copyOf(var0, var0.length));
   }

   public static SerializableImpl_3 method10(Collection<Long> var0) {
      return var0.isEmpty() ? field1 : new SerializableImpl_3(Longs.toArray(var0));
   }

   public static SerializableImpl_3 method11(Iterable<Long> var0) {
      return var0 instanceof Collection ? method10((Collection<Long>)var0) : method14().method3(var0).method7();
   }

   public static SerializableImpl_3 method12(LongStream var0) {
      long[] var1 = var0.toArray();
      return var1.length == 0 ? field1 : new SerializableImpl_3(var1);
   }

   public static SerializableImpl$Data12 method13(int var0) {
      Preconditions.checkArgument(var0 >= 0, "Invalid initialCapacity: %s", var0);
      return new SerializableImpl$Data12(var0);
   }

   public static SerializableImpl$Data12 method14() {
      return new SerializableImpl$Data12(10);
   }

   private SerializableImpl_3(long[] var1) {
      this(var1, 0, var1.length);
   }

   private SerializableImpl_3(long[] var1, int var2, int var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   public int length() {
      return this.field4 - this.field3;
   }

   public boolean isEmpty() {
      return this.field4 == this.field3;
   }

   public long get(int var1) {
      Preconditions.checkElementIndex(var1, this.length());
      return this.field2[this.field3 + var1];
   }

   public int indexOf(long var1) {
      for (int var3 = this.field3; var3 < this.field4; var3++) {
         if (this.field2[var3] == var1) {
            return var3 - this.field3;
         }
      }

      return -1;
   }

   public int lastIndexOf(long var1) {
      for (int var3 = this.field4 - 1; var3 >= this.field3; var3--) {
         if (this.field2[var3] == var1) {
            return var3 - this.field3;
         }
      }

      return -1;
   }

   public boolean contains(long var1) {
      return this.indexOf(var1) >= 0;
   }

   public void forEach(LongConsumer var1) {
      Preconditions.checkNotNull(var1);

      for (int var2 = this.field3; var2 < this.field4; var2++) {
         var1.accept(this.field2[var2]);
      }
   }

   public LongStream stream() {
      return Arrays.stream(this.field2, this.field3, this.field4);
   }

   public long[] toArray() {
      return Arrays.copyOfRange(this.field2, this.field3, this.field4);
   }

   public SerializableImpl_3 method15(int var1, int var2) {
      Preconditions.checkPositionIndexes(var1, var2, this.length());
      return var1 == var2 ? field1 : new SerializableImpl_3(this.field2, this.field3 + var1, this.field3 + var2);
   }

   private OfLong spliterator() {
      return Spliterators.spliterator(this.field2, this.field3, this.field4, 1040);
   }

   public List<Long> asList() {
      return new SerializableImpl$Data13(this);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof SerializableImpl_3)) {
         return false;
      }

      SerializableImpl_3 var2 = (SerializableImpl_3)var1;
      if (this.length() != var2.length()) {
         return false;
      }

      for (int var3 = 0; var3 < this.length(); var3++) {
         if (this.get(var3) != var2.get(var3)) {
            return false;
         }
      }

      return true;
   }

   @Override
   public int hashCode() {
      int var1 = 1;

      for (int var2 = this.field3; var2 < this.field4; var2++) {
         var1 *= 31;
         var1 += Longs.hashCode(this.field2[var2]);
      }

      return var1;
   }

   @Override
   public String toString() {
      if (this.isEmpty()) {
         return "[]";
      }

      StringBuilder var1 = new StringBuilder(this.length() * 5);
      var1.append('[').append(this.field2[this.field3]);

      for (int var2 = this.field3 + 1; var2 < this.field4; var2++) {
         var1.append(", ").append(this.field2[var2]);
      }

      var1.append(']');
      return var1.toString();
   }

   public SerializableImpl_3 method16() {
      return this.isPartialView() ? new SerializableImpl_3(this.toArray()) : this;
   }

   private boolean isPartialView() {
      return this.field3 > 0 || this.field4 < this.field2.length;
   }

   Object writeReplace() {
      return this.method16();
   }

   Object readResolve() {
      return this.isEmpty() ? field1 : this;
   }
}
