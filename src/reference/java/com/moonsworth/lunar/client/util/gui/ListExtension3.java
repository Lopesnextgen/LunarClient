package com.moonsworth.lunar.client.util.gui;

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
public class ListExtension3<T> implements ListExtension<T> {
   private List<T> innerList;
   private List<T> field1 = null;

   public ListExtension3() {
      this(10);
   }

   public ListExtension3(int var1) {
      this.innerList = new ArrayList<>(var1);
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
   public boolean add(T var1) {
      this.method1();
      return this.innerList.add((T)var1);
   }

   @Override
   public boolean remove(Object var1) {
      this.method1();
      return this.innerList.remove(var1);
   }

   @Override
   public boolean addAll(@NotNull Collection<? extends T> var1) {
      this.method1();
      return this.innerList.addAll(var1);
   }

   @Override
   public boolean addAll(int var1, @NotNull Collection<? extends T> var2) {
      this.method1();
      return this.innerList.addAll(var1, var2);
   }

   @Override
   public boolean removeAll(@NotNull Collection<?> var1) {
      this.method1();
      return this.innerList.removeAll(var1);
   }

   @Override
   public boolean retainAll(@NotNull Collection<?> var1) {
      this.method1();
      return this.innerList.retainAll(var1);
   }

   @Override
   public void clear() {
      this.method1();
      this.innerList.clear();
   }

   @Override
   public T get(int var1) {
      return this.innerList.get(var1);
   }

   @Override
   public T set(int var1, T var2) {
      this.method1();
      return this.innerList.set(var1, (T)var2);
   }

   @Override
   public void add(int var1, T var2) {
      this.method1();
      this.innerList.add(var1, (T)var2);
   }

   @Override
   public T remove(int var1) {
      this.method1();
      return this.innerList.remove(var1);
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator() {
      throw new UnsupportedOperationException("Use inside withImmutableView() instead!");
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator(int var1) {
      throw new UnsupportedOperationException("Use inside withImmutableView() instead!");
   }

   @NotNull
   public ListExtension<T> method2(int var1, int var2) {
      throw new UnsupportedOperationException("CopyOnWriteIfNeededArrayList doesn't support subList()!");
   }

   @Override
   public void method1(Consumer<ListExtension2<T>> var1) {
      List var2 = this.field1;
      this.field1 = this.innerList;
      var1.accept(ListExtension2.method1(this.innerList));
      if (this.field1 == this.innerList) {
         this.field1 = var2;
      }
   }

   @Override
   public void forEach(Consumer<? super T> var1) {
      this.method1(var1x -> var1x.forEach(var1));
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
   public boolean contains(Object var1) {
      return this.innerList.contains(var1);
   }

   @Generated
   @Override
   public Object[] toArray() {
      return this.innerList.toArray();
   }

   @Generated
   @Override
   public <E> E[] toArray(E[] var1) {
      return (E[])this.innerList.toArray(var1);
   }

   @Generated
   @Override
   public boolean containsAll(Collection<?> var1) {
      return this.innerList.containsAll(var1);
   }

   @Generated
   @Override
   public int indexOf(Object var1) {
      return this.innerList.indexOf(var1);
   }

   @Generated
   @Override
   public int lastIndexOf(Object var1) {
      return this.innerList.lastIndexOf(var1);
   }
}
