package com.moonsworth.lunar.client.util.collection;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractQueueImpl<T> extends AbstractQueue<T> {
   private final LinkedList<T> field1 = new LinkedList<>();
   private final int capacity;

   public AbstractQueueImpl(int value) {
      this.capacity = value;
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      return new AbstractQueueImpl.Data();
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public boolean offer(T t) {
      if (this.field1.size() >= this.capacity) {
         this.field1.remove();
      }

      this.field1.add((T)t);
      return true;
   }

   @Override
   public T poll() {
      return this.field1.poll();
   }

   @Override
   public T peek() {
      return this.field1.peekFirst();
   }

   public T peekLast() {
      return this.field1.peekLast();
   }

   @Nullable
   public T get(int index1) {
      return this.field1.get(index1);
   }

   @Generated
   public int getCapacity() {
      return this.capacity;
   }

   private class Data implements Iterator<T> {
      private int cursor = 0;

      private Data() {
      }

      @Override
      public boolean hasNext() {
         return this.cursor < AbstractQueueImpl.this.size();
      }

      @Override
      public T next() {
         Object obj1 = AbstractQueueImpl.this.field1.get(this.cursor);
         this.cursor++;
         return (T)obj1;
      }
   }
}
