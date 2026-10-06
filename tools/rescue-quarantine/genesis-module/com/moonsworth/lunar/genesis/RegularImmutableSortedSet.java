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
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSortedSet;

@GwtCompatible(serializable = true, emulated = true)
final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
   static final RegularImmutableSortedSet<Comparable> field13 = new RegularImmutableSortedSet<>(
      ImmutableList.method3(), Ordering.method1()
   );
   private final transient ImmutableList<E> field14;

   RegularImmutableSortedSet(ImmutableList<E> abstractcollectioniterator31, java.util.Comparator<? super E> comparator2) {
      super(comparator2);
      this.field14 = abstractcollectioniterator31;
   }

   Object[] internalArray() {
      return this.field14.internalArray();
   }

   int internalArrayStart() {
      return this.field14.internalArrayStart();
   }

   int internalArrayEnd() {
      return this.field14.internalArrayEnd();
   }

   public UnmodifiableIterator<E> method1() {
      return this.field14.method1();
   }

   @GwtIncompatible
   public UnmodifiableIterator<E> method33() {
      return this.field14.method29().method1();
   }

   public Spliterator<E> spliterator() {
      return this.method2().spliterator();
   }

   public void forEach(Consumer<? super E> consumer1) {
      this.field14.forEach(consumer1);
   }

   public int size() {
      return this.field14.size();
   }

   public boolean contains(@Nullable Object obj1) {
      try {
         return obj1 != null && this.unsafeBinarySearch(obj1) >= 0;
      } catch (ClassCastException classcastexception3) {
         return false;
      }
   }

   public boolean containsAll(Collection<?> list1) {
      if (list1 instanceof Multiset) {
         list1 = ((Multiset)list1).elementSet();
      }

      if (SortedIterables.hasSameComparator(this.comparator(), list1) && list1.size() > 1) {
         UnmodifiableIterator mixinhelperiterator32 = this.method1();
         Iterator iterator3 = list1.iterator();
         if (!mixinhelperiterator32.hasNext()) {
            return false;
         }

         Object obj4 = iterator3.next();
         Object obj5 = mixinhelperiterator32.next();

         try {
            while (true) {
               int number6 = this.unsafeCompare(obj5, obj4);
               if (number6 < 0) {
                  if (!mixinhelperiterator32.hasNext()) {
                     return false;
                  }

                  obj5 = mixinhelperiterator32.next();
               } else if (number6 == 0) {
                  if (!iterator3.hasNext()) {
                     return true;
                  }

                  obj4 = iterator3.next();
               } else if (number6 > 0) {
                  return false;
               }
            }
         } catch (NullPointerException | ClassCastException nullpointerexception7) {
            return false;
         }
      } else {
         return super.containsAll(list1);
      }
   }

   private int unsafeBinarySearch(Object obj1) {
      return Collections.binarySearch(this.field14, (E)obj1, this.unsafeComparator());
   }

   boolean isPartialView() {
      return this.field14.isPartialView();
   }

   int copyIntoArray(Object[] items1, int number2) {
      return this.field14.copyIntoArray(items1, number2);
   }

   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (!(obj1 instanceof Set)) {
         return false;
      }

      Set set2 = (Set)obj1;
      if (this.size() != set2.size()) {
         return false;
      }

      if (this.isEmpty()) {
         return true;
      }

      if (SortedIterables.hasSameComparator(this.HHCIIIIOCIROOHOCIIRICHCRICCOOC, set2)) {
         Iterator iterator3 = set2.iterator();

         try {
            UnmodifiableIterator mixinhelperiterator34 = this.method1();

            while (mixinhelperiterator34.hasNext()) {
               Object obj5 = mixinhelperiterator34.next();
               Object obj6 = iterator3.next();
               if (obj6 == null || this.unsafeCompare(obj5, obj6) != 0) {
                  return false;
               }
            }

            return true;
         } catch (ClassCastException classcastexception7) {
            return false;
         } catch (NoSuchElementException nosuchelementexception8) {
            return false;
         }
      } else {
         return this.containsAll(set2);
      }
   }

   public E first() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.field14.get(0);
      }
   }

   public E last() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.field14.get(this.size() - 1);
      }
   }

   public E lower(E value1) {
      int index2 = this.headIndex((E)value1, false) - 1;
      return index2 == -1 ? null : this.field14.get(index2);
   }

   public E floor(E value1) {
      int index2 = this.headIndex((E)value1, true) - 1;
      return index2 == -1 ? null : this.field14.get(index2);
   }

   public E ceiling(E value1) {
      int index2 = this.tailIndex((E)value1, true);
      return index2 == this.size() ? null : this.field14.get(index2);
   }

   public E higher(E value1) {
      int index2 = this.tailIndex((E)value1, false);
      return index2 == this.size() ? null : this.field14.get(index2);
   }

   ImmutableSortedSet<E> method28(E value1, boolean flag2) {
      return this.method6(0, this.headIndex((E)value1, flag2));
   }

   int headIndex(E value1, boolean flag2) {
      int number3 = Collections.binarySearch(this.field14, (E)Preconditions.checkNotNull(value1), this.comparator());
      if (number3 >= 0) {
         return flag2 ? number3 + 1 : number3;
      } else {
         return ~number3;
      }
   }

   ImmutableSortedSet<E> method29(E value1, boolean flag2, E value3, boolean flag4) {
      return this.method30((E)value1, flag2).method28(value3, flag4);
   }

   ImmutableSortedSet<E> method30(E value1, boolean flag2) {
      return this.method6(this.tailIndex((E)value1, flag2), this.size());
   }

   int tailIndex(E value1, boolean flag2) {
      int number3 = Collections.binarySearch(this.field14, (E)Preconditions.checkNotNull(value1), this.comparator());
      if (number3 >= 0) {
         return flag2 ? number3 : number3 + 1;
      } else {
         return ~number3;
      }
   }

   java.util.Comparator<Object> unsafeComparator() {
      return this.HHCIIIIOCIROOHOCIIRICHCRICCOOC;
   }

   RegularImmutableSortedSet<E> method6(int number1, int number2) {
      if (number1 == 0 && number2 == this.size()) {
         return this;
      } else {
         return number1 < number2
            ? new RegularImmutableSortedSet<>(this.field14.method26(number1, number2), this.HHCIIIIOCIROOHOCIIRICHCRICCOOC)
            : HCHHRHHCRIIORRRICOOCCOCHIRRRRR(this.HHCIIIIOCIROOHOCIIRICHCRICCOOC);
      }
   }

   int indexOf(@Nullable Object obj1) {
      if (obj1 == null) {
         return -1;
      }

      int number2;
      try {
         number2 = Collections.binarySearch(this.field14, (E)obj1, this.unsafeComparator());
      } catch (ClassCastException classcastexception4) {
         return -1;
      }

      return number2 >= 0 ? number2 : -1;
   }

   ImmutableList<E> method17() {
      return (ImmutableList<E>)(this.size() <= 1 ? this.field14 : new ImmutableSortedAsList(this, this.field14));
   }

   ImmutableSortedSet<E> method32() {
      java.util.Comparator comparator1 = Collections.reverseOrder(this.HHCIIIIOCIROOHOCIIRICHCRICCOOC);
      return this.isEmpty() ? HCHHRHHCRIIORRRICOOCCOCHIRRRRR(comparator1) : new RegularImmutableSortedSet<>(this.field14.method29(), comparator1);
   }
}
