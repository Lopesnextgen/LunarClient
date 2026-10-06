package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

@GwtCompatible(emulated = true)
abstract class AbstractCollectionBase3<E> extends AbstractCollectionBase<E> implements Serializable {
   private transient Map<E, SerializableImpl> backingMap;
   private transient long size;
   @Annotation3
   private static final long field1 = -2250766705698539974L;

   protected AbstractCollectionBase3(Map<E, SerializableImpl> var1) {
      Preconditions.checkArgument(var1.isEmpty());
      this.backingMap = var1;
   }

   void setBackingMap(Map<E, SerializableImpl> var1) {
      this.backingMap = var1;
   }

   @Override
   public Set<Multiset.Extension<E>> entrySet() {
      return super.entrySet();
   }

   @Override
   Iterator<E> elementIterator() {
      final Iterator var1 = this.backingMap.entrySet().iterator();
      return new Iterator<E>() {
         @Nullable Entry<E, SerializableImpl> toRemove;

         @Override
         public boolean hasNext() {
            return var1.hasNext();
         }

         @Override
         public E next() {
            Entry var1x = (Entry)var1.next();
            this.toRemove = var1x;
            return (E)var1x.getKey();
         }

         @Override
         public void remove() {
            MixinHelper18_3.checkRemove(this.toRemove != null);
            AbstractCollectionBase3.this.size = AbstractCollectionBase3.this.size - this.toRemove.getValue().getAndSet(0);
            var1.remove();
            this.toRemove = null;
         }
      };
   }

   @Override
   Iterator<Multiset.Extension<E>> entryIterator() {
      final Iterator var1 = this.backingMap.entrySet().iterator();
      return new Iterator<Multiset.Extension<E>>() {
         @Nullable Entry<E, SerializableImpl> toRemove;

         @Override
         public boolean hasNext() {
            return var1.hasNext();
         }

         public Multiset.Extension<E> method1() {
            final Entry var1x = (Entry)var1.next();
            this.toRemove = var1x;
            return new MixinHelper33$Data3<E>() {
               @Override
               public E getElement() {
                  return (E)var1x.getKey();
               }

               @Override
               public int getCount() {
                  SerializableImpl var1xx = (SerializableImpl)var1x.getValue();
                  if (var1xx == null || var1xx.get() == 0) {
                     SerializableImpl var2 = AbstractCollectionBase3.this.backingMap.get(this.getElement());
                     if (var2 != null) {
                        return var2.get();
                     }
                  }

                  return var1xx == null ? 0 : var1xx.get();
               }
            };
         }

         @Override
         public void remove() {
            MixinHelper18_3.checkRemove(this.toRemove != null);
            AbstractCollectionBase3.this.size = AbstractCollectionBase3.this.size - this.toRemove.getValue().getAndSet(0);
            var1.remove();
            this.toRemove = null;
         }
      };
   }

   @Override
   public void forEachEntry(ObjIntConsumer<? super E> var1) {
      Preconditions.checkNotNull(var1);
      this.backingMap.forEach((var1x, var2) -> var1.accept(var1x, var2.get()));
   }

   @Override
   public void clear() {
      for (SerializableImpl var2 : this.backingMap.values()) {
         var2.set(0);
      }

      this.backingMap.clear();
      this.size = 0L;
   }

   @Override
   int distinctElements() {
      return this.backingMap.size();
   }

   @Override
   public int size() {
      return MixinHelper122.saturatedCast(this.size);
   }

   @Override
   public Iterator<E> iterator() {
      return new AbstractCollectionBase3.Data();
   }

   @Override
   public int count(@Nullable Object var1) {
      SerializableImpl var2 = Maps.safeGet(this.backingMap, var1);
      return var2 == null ? 0 : var2.get();
   }

   @CanIgnoreReturnValue
   @Override
   public int add(@Nullable E var1, int var2) {
      if (var2 == 0) {
         return this.count(var1);
      }

      Preconditions.checkArgument(var2 > 0, "occurrences cannot be negative: %s", var2);
      SerializableImpl var3 = this.backingMap.get(var1);
      int var4;
      if (var3 == null) {
         var4 = 0;
         this.backingMap.put((E)var1, new SerializableImpl(var2));
      } else {
         var4 = var3.get();
         long var5 = (long)var4 + var2;
         Preconditions.checkArgument(var5 <= 2147483647L, "too many occurrences: %s", var5);
         var3.add(var2);
      }

      this.size += var2;
      return var4;
   }

   @CanIgnoreReturnValue
   @Override
   public int remove(@Nullable Object var1, int var2) {
      if (var2 == 0) {
         return this.count(var1);
      }

      Preconditions.checkArgument(var2 > 0, "occurrences cannot be negative: %s", var2);
      SerializableImpl var3 = this.backingMap.get(var1);
      if (var3 == null) {
         return 0;
      }

      int var4 = var3.get();
      int var5;
      if (var4 > var2) {
         var5 = var2;
      } else {
         var5 = var4;
         this.backingMap.remove(var1);
      }

      var3.add(-var5);
      this.size -= var5;
      return var4;
   }

   @CanIgnoreReturnValue
   @Override
   public int setCount(@Nullable E var1, int var2) {
      MixinHelper18_3.checkNonnegative(var2, "count");
      int var4;
      if (var2 == 0) {
         SerializableImpl var3 = this.backingMap.remove(var1);
         var4 = method1(var3, var2);
      } else {
         SerializableImpl var5 = this.backingMap.get(var1);
         var4 = method1(var5, var2);
         if (var5 == null) {
            this.backingMap.put((E)var1, new SerializableImpl(var2));
         }
      }

      this.size += var2 - var4;
      return var4;
   }

   private static int method1(@Nullable SerializableImpl var0, int var1) {
      return var0 == null ? 0 : var0.getAndSet(var1);
   }

   @Annotation3
   private void readObjectNoData() {
      throw new InvalidObjectException("Stream data required");
   }

   private class Data implements Iterator<E> {
      final Iterator<Entry<E, SerializableImpl>> field1 = AbstractCollectionBase3.this.backingMap.entrySet().iterator();
      @Nullable Entry<E, SerializableImpl> currentEntry;
      int occurrencesLeft;
      boolean canRemove;

      Data() {
      }

      @Override
      public boolean hasNext() {
         return this.occurrencesLeft > 0 || this.field1.hasNext();
      }

      @Override
      public E next() {
         if (this.occurrencesLeft == 0) {
            this.currentEntry = this.field1.next();
            this.occurrencesLeft = this.currentEntry.getValue().get();
         }

         this.occurrencesLeft--;
         this.canRemove = true;
         return this.currentEntry.getKey();
      }

      @Override
      public void remove() {
         MixinHelper18_3.checkRemove(this.canRemove);
         int var1 = this.currentEntry.getValue().get();
         if (var1 <= 0) {
            throw new ConcurrentModificationException();
         }

         if (this.currentEntry.getValue().addAndGet(-1) == 0) {
            this.field1.remove();
         }

         AbstractCollectionBase3.this.size--;
         this.canRemove = false;
      }
   }
}
