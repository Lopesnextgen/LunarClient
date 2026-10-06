package com.moonsworth.lunar.genesis;

import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;

@GwtCompatible
final class AbstractListIterator<E> extends AbstractList<List<E>> implements RandomAccess {
   private final transient ImmutableList<List<E>> field1;
   private final transient int[] field2;

   static <E> List<List<E>> create(List<? extends List<? extends E>> var0) {
      ImmutableList.Data2 var1 = new ImmutableList.Data2(var0.size());

      for (List var3 : var0) {
         ImmutableList var4 = ImmutableList.method15(var3);
         if (var4.isEmpty()) {
            return ImmutableList.method3();
         }

         var1.method2(var4);
      }

      return new AbstractListIterator<>(var1.method6());
   }

   AbstractListIterator(ImmutableList<List<E>> var1) {
      this.field1 = var1;
      int[] var2 = new int[var1.size() + 1];
      var2[var1.size()] = 1;

      try {
         for (int var3 = var1.size() - 1; var3 >= 0; var3--) {
            var2[var3] = MixinHelper7_3.checkedMultiply(var2[var3 + 1], ((List)var1.get(var3)).size());
         }
      } catch (ArithmeticException var4) {
         throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
      }

      this.field2 = var2;
   }

   private int getAxisIndexForProductIndex(int var1, int var2) {
      return var1 / this.field2[var2 + 1] % this.field1.get(var2).size();
   }

   @Override
   public int indexOf(Object var1) {
      if (!(var1 instanceof List)) {
         return -1;
      }

      List var2 = (List)var1;
      if (var2.size() != this.field1.size()) {
         return -1;
      }

      ListIterator var3 = var2.listIterator();
      int var4 = 0;

      while (var3.hasNext()) {
         int var5 = var3.nextIndex();
         int var6 = this.field1.get(var5).indexOf(var3.next());
         if (var6 == -1) {
            return -1;
         }

         var4 += var6 * this.field2[var5 + 1];
      }

      return var4;
   }

   public ImmutableList<E> method1(final int var1) {
      Preconditions.checkElementIndex(var1, this.size());
      return new ImmutableList<E>() {
         @Override
         public int size() {
            return AbstractListIterator.this.field1.size();
         }

         @Override
         public E get(int var1x) {
            Preconditions.checkElementIndex(var1x, this.size());
            int var2 = AbstractListIterator.this.getAxisIndexForProductIndex(var1, var1x);
            return AbstractListIterator.this.field1.get(var1x).get(var2);
         }

         @Override
         boolean isPartialView() {
            return true;
         }
      };
   }

   @Override
   public int size() {
      return this.field2[0];
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.indexOf(var1) != -1;
   }
}
