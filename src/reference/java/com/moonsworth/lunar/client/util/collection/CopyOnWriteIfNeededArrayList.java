package com.moonsworth.lunar.client.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import javax.annotation.concurrent.NotThreadSafe;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@NotThreadSafe
public class CopyOnWriteIfNeededArrayList<T> implements ListExtension<T> {
   private List<T> innerList;
   private List<T> field1 = null;

   public CopyOnWriteIfNeededArrayList() {
      this(10);
   }

   public CopyOnWriteIfNeededArrayList(int number1) {
      this.innerList = new ArrayList<>(number1);
   }

   private void method1() {
      if (this.field1 == this.innerList) {
         this.innerList = new ArrayList<>(this.innerList);
         this.field1 = null;
      }
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      throw new UnsupportedOperationException("Use inside withImmutableView() instead!");
   }

   @Override
   public boolean add(T value1) {
      this.method1();
      return this.innerList.add((T)value1);
   }

   @Override
   public boolean remove(Object obj1) {
      this.method1();
      return this.innerList.remove(obj1);
   }

   @Override
   public boolean addAll(@NotNull Collection<? extends T> list1) {
      this.method1();
      return this.innerList.addAll(list1);
   }

   @Override
   public boolean addAll(int number1, @NotNull Collection<? extends T> list2) {
      this.method1();
      return this.innerList.addAll(number1, list2);
   }

   @Override
   public boolean removeAll(@NotNull Collection<?> list1) {
      this.method1();
      return this.innerList.removeAll(list1);
   }

   @Override
   public boolean retainAll(@NotNull Collection<?> list1) {
      this.method1();
      return this.innerList.retainAll(list1);
   }

   @Override
   public void clear() {
      this.method1();
      this.innerList.clear();
   }

   @Override
   public T get(int index1) {
      return this.innerList.get(index1);
   }

   @Override
   public T set(int index1, T value2) {
      this.method1();
      return this.innerList.set(index1, (T)value2);
   }

   @Override
   public void add(int index1, T value2) {
      this.method1();
      this.innerList.add(index1, (T)value2);
   }

   @Override
   public T remove(int index1) {
      this.method1();
      return this.innerList.remove(index1);
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator() {
      throw new UnsupportedOperationException("Use inside withImmutableView() instead!");
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator(int number1) {
      throw new UnsupportedOperationException("Use inside withImmutableView() instead!");
   }

   @NotNull
   public ListExtension<T> method2(int number1, int number2) {
      throw new UnsupportedOperationException("CopyOnWriteIfNeededArrayList doesn't support subList()!");
   }

   @Override
   public void method1(Consumer<ImmutableListView<T>> consumer1) {
      List list2 = this.field1;
      this.field1 = this.innerList;
      consumer1.accept(ImmutableListView.method1(this.innerList));
      if (this.field1 == this.innerList) {
         this.field1 = list2;
      }
   }

   @Override
   public void forEach(Consumer<? super T> consumer1) {
      this.method1(arg1x -> arg1x.forEach(consumer1));
   }

   @Generated
   @Override
   public int size() {
      return this.innerList.size();
   }

   @Generated
   @Override
   public boolean isEmpty() {
      return this.innerList.isEmpty();
   }

   @Generated
   @Override
   public boolean contains(Object obj1) {
      return this.innerList.contains(obj1);
   }

   @Generated
   @Override
   public Object[] toArray() {
      return this.innerList.toArray();
   }

   @Generated
   @Override
   public <E> E[] toArray(E[] items1) {
      return (E[])this.innerList.toArray(items1);
   }

   @Generated
   @Override
   public boolean containsAll(Collection<?> list1) {
      return this.innerList.containsAll(list1);
   }

   @Generated
   @Override
   public int indexOf(Object obj1) {
      return this.innerList.indexOf(obj1);
   }

   @Generated
   @Override
   public int lastIndexOf(Object obj1) {
      return this.innerList.lastIndexOf(obj1);
   }
}
