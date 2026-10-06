package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Spliterators;
import java.util.Spliterator.OfInt;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Immutable
@Annotation2
@GwtCompatible
public final class SerializableImpl2_2 implements Serializable {
   private static final SerializableImpl2_2 field1 = new SerializableImpl2_2(new int[0]);
   private final int[] field2;
   private final transient int field3;
   private final int field4;

   public static SerializableImpl2_2 method1() {
      return field1;
   }

   public static SerializableImpl2_2 method2(int var0) {
      return new SerializableImpl2_2(new int[]{var0});
   }

   public static SerializableImpl2_2 method3(int var0, int var1) {
      return new SerializableImpl2_2(new int[]{var0, var1});
   }

   public static SerializableImpl2_2 method4(int var0, int var1, int var2) {
      return new SerializableImpl2_2(new int[]{var0, var1, var2});
   }

   public static SerializableImpl2_2 method5(int var0, int var1, int var2, int var3) {
      return new SerializableImpl2_2(new int[]{var0, var1, var2, var3});
   }

   public static SerializableImpl2_2 method6(int var0, int var1, int var2, int var3, int var4) {
      return new SerializableImpl2_2(new int[]{var0, var1, var2, var3, var4});
   }

   public static SerializableImpl2_2 method7(int var0, int var1, int var2, int var3, int var4, int var5) {
      return new SerializableImpl2_2(new int[]{var0, var1, var2, var3, var4, var5});
   }

   public static SerializableImpl2_2 method8(int var0, int... var1) {
      Preconditions.checkArgument(var1.length <= 2147483646, "the total number of elements must fit in an int");
      int[] var2 = new int[var1.length + 1];
      var2[0] = var0;
      System.arraycopy(var1, 0, var2, 1, var1.length);
      return new SerializableImpl2_2(var2);
   }

   public static SerializableImpl2_2 method9(int[] var0) {
      return var0.length == 0 ? field1 : new SerializableImpl2_2(Arrays.copyOf(var0, var0.length));
   }

   public static SerializableImpl2_2 method10(Collection<Integer> var0) {
      return var0.isEmpty() ? field1 : new SerializableImpl2_2(MixinHelper122.toArray(var0));
   }

   public static SerializableImpl2_2 method11(Iterable<Integer> var0) {
      return var0 instanceof Collection ? method10((Collection<Integer>)var0) : method14().method3(var0).method7();
   }

   public static SerializableImpl2_2 method12(IntStream var0) {
      int[] var1 = var0.toArray();
      return var1.length == 0 ? field1 : new SerializableImpl2_2(var1);
   }

   public static SerializableImpl2$Data method13(int var0) {
      Preconditions.checkArgument(var0 >= 0, "Invalid initialCapacity: %s", var0);
      return new SerializableImpl2$Data(var0);
   }

   public static SerializableImpl2$Data method14() {
      return new SerializableImpl2$Data(10);
   }

   private SerializableImpl2_2(int[] var1) {
      this(var1, 0, var1.length);
   }

   private SerializableImpl2_2(int[] var1, int var2, int var3) {
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

   public int get(int var1) {
      Preconditions.checkElementIndex(var1, this.length());
      return this.field2[this.field3 + var1];
   }

   public int indexOf(int var1) {
      for (int var2 = this.field3; var2 < this.field4; var2++) {
         if (this.field2[var2] == var1) {
            return var2 - this.field3;
         }
      }

      return -1;
   }

   public int lastIndexOf(int var1) {
      for (int var2 = this.field4 - 1; var2 >= this.field3; var2--) {
         if (this.field2[var2] == var1) {
            return var2 - this.field3;
         }
      }

      return -1;
   }

   public boolean contains(int var1) {
      return this.indexOf(var1) >= 0;
   }

   public void forEach(IntConsumer var1) {
      Preconditions.checkNotNull(var1);

      for (int var2 = this.field3; var2 < this.field4; var2++) {
         var1.accept(this.field2[var2]);
      }
   }

   public IntStream stream() {
      return Arrays.stream(this.field2, this.field3, this.field4);
   }

   public int[] toArray() {
      return Arrays.copyOfRange(this.field2, this.field3, this.field4);
   }

   public SerializableImpl2_2 method15(int var1, int var2) {
      Preconditions.checkPositionIndexes(var1, var2, this.length());
      return var1 == var2 ? field1 : new SerializableImpl2_2(this.field2, this.field3 + var1, this.field3 + var2);
   }

   private OfInt spliterator() {
      return Spliterators.spliterator(this.field2, this.field3, this.field4, 1040);
   }

   public List<Integer> asList() {
      return new SerializableImpl2$Data2(this);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof SerializableImpl2_2)) {
         return false;
      }

      SerializableImpl2_2 var2 = (SerializableImpl2_2)var1;
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
         var1 += MixinHelper122.hashCode(this.field2[var2]);
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

   public SerializableImpl2_2 method16() {
      return this.isPartialView() ? new SerializableImpl2_2(this.toArray()) : this;
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
