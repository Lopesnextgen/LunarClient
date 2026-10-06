package com.moonsworth.lunar.genesis;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

class Collections2$FilteredCollection<E> extends AbstractCollection<E> {
   final Collection<E> field1;
   final Predicate<? super E> field2;

   Collections2$FilteredCollection(Collection<E> list1, Predicate<? super E> predicateextension2) {
      this.field1 = list1;
      this.field2 = predicateextension2;
   }

   Collections2$FilteredCollection<E> method1(Predicate<? super E> predicateextension1) {
      return new Collections2$FilteredCollection<>(this.field1, Predicates.method8(this.field2, predicateextension1));
   }

   @Override
   public boolean add(E value1) {
      Preconditions.checkArgument(this.field2.apply(value1));
      return this.field1.add((E)value1);
   }

   @Override
   public boolean addAll(Collection<? extends E> list1) {
      for (Object obj3 : list1) {
         Preconditions.checkArgument(this.field2.apply(obj3));
      }

      return this.field1.addAll(list1);
   }

   @Override
   public void clear() {
      Iterables.method2(this.field1, this.field2);
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      if (MixinHelper39.safeContains(this.field1, obj1)) {
         Object obj2 = obj1;
         return this.field2.apply(obj2);
      } else {
         return false;
      }
   }

   @Override
   public boolean containsAll(Collection<?> list1) {
      return MixinHelper39.containsAllImpl(this, list1);
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
   public void forEach(Consumer<? super E> consumer1) {
      Preconditions.checkNotNull(consumer1);
      this.field1.forEach(arg2 -> {
         if (this.field2.test(arg2)) {
            consumer1.accept(arg2);
         }
      });
   }

   @Override
   public boolean remove(Object obj1) {
      return this.contains(obj1) && this.field1.remove(obj1);
   }

   @Override
   public boolean removeAll(Collection<?> list1) {
      return this.removeIf(list1::contains);
   }

   @Override
   public boolean retainAll(Collection<?> list1) {
      return this.removeIf(arg1x -> !list1.contains(arg1x));
   }

   @Override
   public boolean removeIf(Predicate<? super E> predicate1) {
      Preconditions.checkNotNull(predicate1);
      return this.field1.removeIf(arg2 -> this.field2.apply(arg2) && predicate1.test(arg2));
   }

   @Override
   public int size() {
      int index1 = 0;

      for (Object obj3 : this.field1) {
         if (this.field2.apply(obj3)) {
            index1++;
         }
      }

      return index1;
   }

   @Override
   public Object[] toArray() {
      return Lists.newArrayList(this.iterator()).toArray();
   }

   @Override
   public <T> T[] toArray(T[] items1) {
      return (T[])Lists.newArrayList(this.iterator()).toArray(items1);
   }
}
