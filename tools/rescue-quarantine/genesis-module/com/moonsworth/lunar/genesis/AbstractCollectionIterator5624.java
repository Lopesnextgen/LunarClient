package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;

@GwtCompatible(serializable = true, emulated = true)
final class AbstractCollectionIterator5624<E> extends ImmutableSortedSet<E> {
   static final AbstractCollectionIterator5624<Comparable> field13 = new AbstractCollectionIterator5624<>(
      ImmutableList.method3(), Ordering.method1()
   );
   private final transient ImmutableList<E> field14;

   AbstractCollectionIterator5624(ImmutableList<E> var1, java.util.Comparator<? super E> var2) {
      super(var2);
      this.field14 = var1;
   }

   @Override
   Object[] internalArray() {
      return this.field14.internalArray();
   }

   @Override
   int internalArrayStart() {
      return this.field14.internalArrayStart();
   }

   @Override
   int internalArrayEnd() {
      return this.field14.internalArrayEnd();
   }

   @Override
   public MixinHelperIterator3<E> method1() {
      return this.field14.method1();
   }

   @Annotation3
   @Override
   public MixinHelperIterator3<E> method33() {
      return this.field14.method29().method1();
   }

   @Override
   public Spliterator<E> spliterator() {
      return this.method17().spliterator();
   }

   @Override
   public void forEach(Consumer<? super E> var1) {
      this.field14.forEach(var1);
   }

   @Override
   public int size() {
      return this.field14.size();
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      try {
         return var1 != null && this.unsafeBinarySearch(var1) >= 0;
      } catch (ClassCastException var3) {
         return false;
      }
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      if (var1 instanceof Multiset) {
         var1 = ((Multiset)var1).elementSet();
      }

      if (MixinHelper9_6.hasSameComparator(this.comparator(), var1) && var1.size() > 1) {
         MixinHelperIterator3 var2 = this.method1();
         Iterator var3 = var1.iterator();
         if (!var2.hasNext()) {
            return false;
         }

         Object var4 = var3.next();
         Object var5 = var2.next();

         try {
            while (true) {
               int var6 = this.unsafeCompare(var5, var4);
               if (var6 < 0) {
                  if (!var2.hasNext()) {
                     return false;
                  }

                  var5 = var2.next();
               } else if (var6 == 0) {
                  if (!var3.hasNext()) {
                     return true;
                  }

                  var4 = var3.next();
               } else if (var6 > 0) {
                  return false;
               }
            }
         } catch (NullPointerException | ClassCastException var7) {
            return false;
         }
      } else {
         return super.containsAll(var1);
      }
   }

   private int unsafeBinarySearch(Object var1) {
      return Collections.binarySearch(this.field14, (E)var1, this.unsafeComparator());
   }

   @Override
   boolean isPartialView() {
      return this.field14.isPartialView();
   }

   @Override
   int copyIntoArray(Object[] var1, int var2) {
      return this.field14.copyIntoArray(var1, var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof Set)) {
         return false;
      }

      Set var2 = (Set)var1;
      if (this.size() != var2.size()) {
         return false;
      }

      if (this.isEmpty()) {
         return true;
      }

      if (MixinHelper9_6.hasSameComparator(this.field11, var2)) {
         Iterator var3 = var2.iterator();

         try {
            MixinHelperIterator3 var4 = this.method1();

            while (var4.hasNext()) {
               Object var5 = var4.next();
               Object var6 = var3.next();
               if (var6 == null || this.unsafeCompare(var5, var6) != 0) {
                  return false;
               }
            }

            return true;
         } catch (ClassCastException var7) {
            return false;
         } catch (NoSuchElementException var8) {
            return false;
         }
      } else {
         return this.containsAll(var2);
      }
   }

   @Override
   public E first() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.field14.get(0);
      }
   }

   @Override
   public E last() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.field14.get(this.size() - 1);
      }
   }

   @Override
   public E lower(E var1) {
      int var2 = this.headIndex((E)var1, false) - 1;
      return var2 == -1 ? null : this.field14.get(var2);
   }

   @Override
   public E floor(E var1) {
      int var2 = this.headIndex((E)var1, true) - 1;
      return var2 == -1 ? null : this.field14.get(var2);
   }

   @Override
   public E ceiling(E var1) {
      int var2 = this.tailIndex((E)var1, true);
      return var2 == this.size() ? null : this.field14.get(var2);
   }

   @Override
   public E higher(E var1) {
      int var2 = this.tailIndex((E)var1, false);
      return var2 == this.size() ? null : this.field14.get(var2);
   }

   @Override
   ImmutableSortedSet<E> method28(E var1, boolean var2) {
      return this.method6(0, this.headIndex((E)var1, var2));
   }

   int headIndex(E var1, boolean var2) {
      int var3 = Collections.binarySearch(this.field14, Preconditions.checkNotNull((E)var1), this.comparator());
      if (var3 >= 0) {
         return var2 ? var3 + 1 : var3;
      } else {
         return ~var3;
      }
   }

   @Override
   ImmutableSortedSet<E> method29(E var1, boolean var2, E var3, boolean var4) {
      return this.method30((E)var1, var2).method28((E)var3, var4);
   }

   @Override
   ImmutableSortedSet<E> method30(E var1, boolean var2) {
      return this.method6(this.tailIndex((E)var1, var2), this.size());
   }

   int tailIndex(E var1, boolean var2) {
      int var3 = Collections.binarySearch(this.field14, Preconditions.checkNotNull((E)var1), this.comparator());
      if (var3 >= 0) {
         return var2 ? var3 : var3 + 1;
      } else {
         return ~var3;
      }
   }

   java.util.Comparator<Object> unsafeComparator() {
      return this.field11;
   }

   AbstractCollectionIterator5624<E> method6(int var1, int var2) {
      if (var1 == 0 && var2 == this.size()) {
         return this;
      } else {
         return var1 < var2
            ? new AbstractCollectionIterator5624<>(this.field14.method26(var1, var2), this.field11)
            : HCHHRHHCRIIORRRICOOCCOCHIRRRRR(this.field11);
      }
   }

   @Override
   int indexOf(@Nullable Object var1) {
      if (var1 == null) {
         return -1;
      }

      int var2;
      try {
         var2 = Collections.binarySearch(this.field14, (E)var1, this.unsafeComparator());
      } catch (ClassCastException var4) {
         return -1;
      }

      return var2 >= 0 ? var2 : -1;
   }

   @Override
   ImmutableList<E> method17() {
      return this.size() <= 1 ? this.field14 : new AbstractCollectionIterator3322<>(this, this.field14);
   }

   @Override
   ImmutableSortedSet<E> method32() {
      java.util.Comparator var1 = Collections.reverseOrder(this.field11);
      return this.isEmpty() ? HCHHRHHCRIIORRRICOOCCOCHIRRRRR(var1) : new AbstractCollectionIterator5624<>(this.field14.method29(), var1);
   }
}
