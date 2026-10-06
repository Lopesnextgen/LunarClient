package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
class MixinHelper7$Data18 extends AbstractList<Character> implements Serializable, RandomAccess {
   final char[] field1;
   final int field2;
   final int field3;
   private static final long field4 = 0L;

   MixinHelper7$Data18(char[] var1) {
      this(var1, 0, var1.length);
   }

   MixinHelper7$Data18(char[] var1, int var2, int var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   @Override
   public int size() {
      return this.field3 - this.field2;
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   public Character get(int var1) {
      Preconditions.checkElementIndex(var1, this.size());
      return this.field1[this.field2 + var1];
   }

   @Override
   public boolean contains(Object var1) {
      return var1 instanceof Character && MixinHelper7_8.access$000(this.field1, (Character)var1, this.field2, this.field3) != -1;
   }

   @Override
   public int indexOf(Object var1) {
      if (var1 instanceof Character) {
         int var2 = MixinHelper7_8.access$000(this.field1, (Character)var1, this.field2, this.field3);
         if (var2 >= 0) {
            return var2 - this.field2;
         }
      }

      return -1;
   }

   @Override
   public int lastIndexOf(Object var1) {
      if (var1 instanceof Character) {
         int var2 = MixinHelper7_8.access$100(this.field1, (Character)var1, this.field2, this.field3);
         if (var2 >= 0) {
            return var2 - this.field2;
         }
      }

      return -1;
   }

   public Character set(int var1, Character var2) {
      Preconditions.checkElementIndex(var1, this.size());
      char var3 = this.field1[this.field2 + var1];
      this.field1[this.field2 + var1] = Preconditions.checkNotNull(var2);
      return var3;
   }

   @Override
   public List<Character> subList(int var1, int var2) {
      int var3 = this.size();
      Preconditions.checkPositionIndexes(var1, var2, var3);
      return var1 == var2 ? Collections.emptyList() : new MixinHelper7$Data18(this.field1, this.field2 + var1, this.field2 + var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (var1 instanceof MixinHelper7$Data18) {
         MixinHelper7$Data18 var2 = (MixinHelper7$Data18)var1;
         int var3 = this.size();
         if (var2.size() != var3) {
            return false;
         }

         for (int var4 = 0; var4 < var3; var4++) {
            if (this.field1[this.field2 + var4] != var2.field1[var2.field2 + var4]) {
               return false;
            }
         }

         return true;
      } else {
         return super.equals(var1);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;

      for (int var2 = this.field2; var2 < this.field3; var2++) {
         var1 = 31 * var1 + MixinHelper7_8.hashCode(this.field1[var2]);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(this.size() * 3);
      var1.append('[').append(this.field1[this.field2]);

      for (int var2 = this.field2 + 1; var2 < this.field3; var2++) {
         var1.append(", ").append(this.field1[var2]);
      }

      return var1.append(']').toString();
   }

   char[] toCharArray() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field3);
   }
}
