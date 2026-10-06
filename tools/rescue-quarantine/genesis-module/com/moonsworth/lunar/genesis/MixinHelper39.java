package com.moonsworth.lunar.genesis;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Lists;
import com.google.common.base.Predicates;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;

@GwtCompatible
public final class MixinHelper39 {
   private MixinHelper39() {
   }

   public static <E> Collection<E> method1(Collection<E> var0, PredicateExtension<? super E> var1) {
      return var0 instanceof MixinHelper39.Data2
         ? ((MixinHelper39.Data2)var0).method1(var1)
         : new MixinHelper39.Data2<>(Preconditions.checkNotNull(var0), Preconditions.checkNotNull(var1));
   }

   static boolean safeContains(Collection<?> var0, @Nullable Object var1) {
      Preconditions.checkNotNull(var0);

      try {
         return var0.contains(var1);
      } catch (ClassCastException | NullPointerException var3) {
         return false;
      }
   }

   static boolean safeRemove(Collection<?> var0, @Nullable Object var1) {
      Preconditions.checkNotNull(var0);

      try {
         return var0.remove(var1);
      } catch (ClassCastException | NullPointerException var3) {
         return false;
      }
   }

   public static <F, T> Collection<T> method2(Collection<F> var0, MixinHelper24_2<? super F, T> var1) {
      return new MixinHelper39.Data5<>(var0, var1);
   }

   static boolean containsAllImpl(Collection<?> var0, Collection<?> var1) {
      for (Object var3 : var1) {
         if (!var0.contains(var3)) {
            return false;
         }
      }

      return true;
   }

   static String toStringImpl(Collection<?> var0) {
      StringBuilder var1 = newStringBuilderForCollection(var0.size()).append('[');
      boolean var2 = true;

      for (Object var4 : var0) {
         if (!var2) {
            var1.append(", ");
         }

         var2 = false;
         if (var4 == var0) {
            var1.append("(this Collection)");
         } else {
            var1.append(var4);
         }
      }

      return var1.append(']').toString();
   }

   static StringBuilder newStringBuilderForCollection(int var0) {
      MixinHelper18_3.checkNonnegative(var0, "size");
      return new StringBuilder((int)Math.min(var0 * 8L, 1073741824L));
   }

   static <T> Collection<T> cast(Iterable<T> var0) {
      return (Collection<T>)var0;
   }

   @Annotation2
   public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> var0) {
      return orderedPermutations(var0, Ordering.method1());
   }

   @Annotation2
   public static <E> Collection<List<E>> orderedPermutations(Iterable<E> var0, java.util.Comparator<? super E> var1) {
      return new MixinHelper39.Data<>(var0, var1);
   }

   @Annotation2
   public static <E> Collection<List<E>> permutations(Collection<E> var0) {
      return new MixinHelper39.Data4<>(ImmutableList.method15(var0));
   }

   private static boolean isPermutation(List<?> var0, List<?> var1) {
      if (var0.size() != var1.size()) {
         return false;
      }

      HashMultiset var2 = HashMultiset.method3(var0);
      HashMultiset var3 = HashMultiset.method3(var1);
      return var2.equals(var3);
   }

   private static final class Data<E> extends AbstractCollection<List<E>> {
      final ImmutableList<E> field1;
      final java.util.Comparator<? super E> field2;
      final int field3;

      Data(Iterable<E> var1, java.util.Comparator<? super E> var2) {
         this.field1 = ImmutableList.method19(var2, var1);
         this.field2 = var2;
         this.field3 = calculateSize(this.field1, var2);
      }

      private static <E> int calculateSize(List<E> var0, java.util.Comparator<? super E> var1) {
         int var2 = 1;
         int var3 = 1;

         int var4;
         for (var4 = 1; var3 < var0.size(); var4++) {
            int var5 = var1.compare(var0.get(var3 - 1), var0.get(var3));
            if (var5 < 0) {
               var2 = MixinHelper7_3.saturatedMultiply(var2, MixinHelper7_3.binomial(var3, var4));
               var4 = 0;
               if (var2 == Integer.MAX_VALUE) {
                  return Integer.MAX_VALUE;
               }
            }

            var3++;
         }

         return MixinHelper7_3.saturatedMultiply(var2, MixinHelper7_3.binomial(var3, var4));
      }

      @Override
      public int size() {
         return this.field3;
      }

      @Override
      public boolean isEmpty() {
         return false;
      }

      @Override
      public Iterator<List<E>> iterator() {
         return new MixinHelper39.Data3<>(this.field1, this.field2);
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         if (var1 instanceof List) {
            List var2 = (List)var1;
            return MixinHelper39.isPermutation(this.field1, var2);
         } else {
            return false;
         }
      }

      @Override
      public String toString() {
         return "orderedPermutationCollection(" + this.field1 + ")";
      }
   }

   static class Data2<E> extends AbstractCollection<E> {
      final Collection<E> field1;
      final PredicateExtension<? super E> field2;

      Data2(Collection<E> var1, PredicateExtension<? super E> var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      MixinHelper39.Data2<E> method1(PredicateExtension<? super E> var1) {
         return new MixinHelper39.Data2<>(this.field1, Predicates.method8(this.field2, var1));
      }

      @Override
      public boolean add(E var1) {
         Preconditions.checkArgument(this.field2.apply((E)var1));
         return this.field1.add((E)var1);
      }

      @Override
      public boolean addAll(Collection<? extends E> var1) {
         for (Object var3 : var1) {
            Preconditions.checkArgument(this.field2.apply((E)var3));
         }

         return this.field1.addAll(var1);
      }

      @Override
      public void clear() {
         Iterables.method2(this.field1, this.field2);
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         if (MixinHelper39.safeContains(this.field1, var1)) {
            Object var2 = var1;
            return this.field2.apply((E)var2);
         } else {
            return false;
         }
      }

      @Override
      public boolean containsAll(Collection<?> var1) {
         return MixinHelper39.containsAllImpl(this, var1);
      }

      @Override
      public boolean isEmpty() {
         return !Iterables.method5(this.field1, this.field2);
      }

      @Override
      public Iterator<E> iterator() {
         return Iterators.method9(this.field1.iterator(), this.field2);
      }

      @Override
      public Spliterator<E> spliterator() {
         return MixinHelper3_5.filter(this.field1.spliterator(), this.field2);
      }

      @Override
      public void forEach(Consumer<? super E> var1) {
         Preconditions.checkNotNull(var1);
         this.field1.forEach(var2 -> {
            if (this.field2.test(var2)) {
               var1.accept(var2);
            }
         });
      }

      @Override
      public boolean remove(Object var1) {
         return this.contains(var1) && this.field1.remove(var1);
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         return this.removeIf(var1::contains);
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         return this.removeIf(var1x -> !var1.contains(var1x));
      }

      @Override
      public boolean removeIf(Predicate<? super E> var1) {
         Preconditions.checkNotNull(var1);
         return this.field1.removeIf(var2 -> this.field2.apply(var2) && var1.test(var2));
      }

      @Override
      public int size() {
         int var1 = 0;

         for (Object var3 : this.field1) {
            if (this.field2.apply((E)var3)) {
               var1++;
            }
         }

         return var1;
      }

      @Override
      public Object[] toArray() {
         return Lists.newArrayList(this.iterator()).toArray();
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         return (T[])Lists.newArrayList(this.iterator()).toArray(var1);
      }
   }

   private static final class Data3<E> extends MixinHelperIterator32_2<List<E>> {
      @Nullable List<E> nextPermutation;
      final java.util.Comparator<? super E> field2;

      Data3(List<E> var1, java.util.Comparator<? super E> var2) {
         this.nextPermutation = Lists.newArrayList(var1);
         this.field2 = var2;
      }

      protected List<E> computeNext() {
         if (this.nextPermutation == null) {
            return (List<E>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }

         ImmutableList var1 = ImmutableList.method15(this.nextPermutation);
         this.calculateNextPermutation();
         return var1;
      }

      void calculateNextPermutation() {
         int var1 = this.findNextJ();
         if (var1 == -1) {
            this.nextPermutation = null;
         } else {
            int var2 = this.findNextL(var1);
            Collections.swap(this.nextPermutation, var1, var2);
            int var3 = this.nextPermutation.size();
            Collections.reverse(this.nextPermutation.subList(var1 + 1, var3));
         }
      }

      int findNextJ() {
         for (int var1 = this.nextPermutation.size() - 2; var1 >= 0; var1--) {
            if (this.field2.compare(this.nextPermutation.get(var1), this.nextPermutation.get(var1 + 1)) < 0) {
               return var1;
            }
         }

         return -1;
      }

      int findNextL(int var1) {
         Object var2 = this.nextPermutation.get(var1);

         for (int var3 = this.nextPermutation.size() - 1; var3 > var1; var3--) {
            if (this.field2.compare((E)var2, this.nextPermutation.get(var3)) < 0) {
               return var3;
            }
         }

         throw new AssertionError("this statement should be unreachable");
      }
   }

   private static final class Data4<E> extends AbstractCollection<List<E>> {
      final ImmutableList<E> field1;

      Data4(ImmutableList<E> var1) {
         this.field1 = var1;
      }

      @Override
      public int size() {
         return MixinHelper7_3.factorial(this.field1.size());
      }

      @Override
      public boolean isEmpty() {
         return false;
      }

      @Override
      public Iterator<List<E>> iterator() {
         return new MixinHelper39.Data6<>(this.field1);
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         if (var1 instanceof List) {
            List var2 = (List)var1;
            return MixinHelper39.isPermutation(this.field1, var2);
         } else {
            return false;
         }
      }

      @Override
      public String toString() {
         return "permutations(" + this.field1 + ")";
      }
   }

   static class Data5<F, T> extends AbstractCollection<T> {
      final Collection<F> field1;
      final MixinHelper24_2<? super F, ? extends T> field2;

      Data5(Collection<F> var1, MixinHelper24_2<? super F, ? extends T> var2) {
         this.field1 = Preconditions.checkNotNull(var1);
         this.field2 = Preconditions.checkNotNull(var2);
      }

      @Override
      public void clear() {
         this.field1.clear();
      }

      @Override
      public boolean isEmpty() {
         return this.field1.isEmpty();
      }

      @Override
      public Iterator<T> iterator() {
         return Iterators.method17(this.field1.iterator(), this.field2);
      }

      @Override
      public Spliterator<T> spliterator() {
         return MixinHelper3_5.map(this.field1.spliterator(), this.field2);
      }

      @Override
      public void forEach(Consumer<? super T> var1) {
         Preconditions.checkNotNull(var1);
         this.field1.forEach(var2 -> var1.accept((T)this.field2.apply(var2)));
      }

      @Override
      public boolean removeIf(Predicate<? super T> var1) {
         Preconditions.checkNotNull(var1);
         return this.field1.removeIf(var2 -> var1.test((T)this.field2.apply(var2)));
      }

      @Override
      public int size() {
         return this.field1.size();
      }
   }

   private static class Data6<E> extends MixinHelperIterator32_2<List<E>> {
      final List<E> field2;
      final int[] field3;
      final int[] field4;
      int j;

      Data6(List<E> var1) {
         this.field2 = new ArrayList<>(var1);
         int var2 = var1.size();
         this.field3 = new int[var2];
         this.field4 = new int[var2];
         Arrays.fill(this.field3, 0);
         Arrays.fill(this.field4, 1);
         this.j = Integer.MAX_VALUE;
      }

      protected List<E> computeNext() {
         if (this.j <= 0) {
            return (List<E>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }

         ImmutableList var1 = ImmutableList.method15(this.field2);
         this.calculateNextPermutation();
         return var1;
      }

      void calculateNextPermutation() {
         this.j = this.field2.size() - 1;
         int var1 = 0;
         if (this.j != -1) {
            while (true) {
               int var2 = this.field3[this.j] + this.field4[this.j];
               if (var2 >= 0) {
                  if (var2 != this.j + 1) {
                     Collections.swap(this.field2, this.j - this.field3[this.j] + var1, this.j - var2 + var1);
                     this.field3[this.j] = var2;
                     break;
                  }

                  if (this.j == 0) {
                     break;
                  }

                  var1++;
                  this.switchDirection();
               } else {
                  this.switchDirection();
               }
            }
         }
      }

      void switchDirection() {
         this.field4[this.j] = -this.field4[this.j];
         this.j--;
      }
   }
}
