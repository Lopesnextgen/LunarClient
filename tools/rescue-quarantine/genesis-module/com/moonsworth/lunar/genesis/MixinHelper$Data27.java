package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Maps;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

class MixinHelper$Data27<K, V> extends MixinHelper$Data37<Entry<K, Collection<V>>> {
   private static final long field6 = 0L;

   MixinHelper$Data27(Set<Entry<K, Collection<V>>> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   @Override
   public Iterator<Entry<K, Collection<V>>> iterator() {
      return new MixinHelperIterator2<Entry<K, Collection<V>>, Entry<K, Collection<V>>>(super.iterator()) {
         Entry<K, Collection<V>> transform(final Entry<K, Collection<V>> var1) {
            return new MixinHelper315<K, Collection<V>>() {
               @Override
               protected Entry<K, Collection<V>> delegate() {
                  return var1;
               }

               public Collection<V> getValue() {
                  return MixinHelper_8.access$400((Collection)var1.getValue(), MixinHelper$Data27.this.RRCCHORICIIHRICRICOROHRHOCHRIC);
               }
            };
         }
      };
   }

   @Override
   public Object[] toArray() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return ObjectArrays.toArrayImpl(this.delegate());
      }
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return (T[])ObjectArrays.toArrayImpl(this.delegate(), var1);
      }
   }

   @Override
   public boolean contains(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return Maps.containsEntryImpl(this.delegate(), var1);
      }
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper39.containsAllImpl(this.delegate(), var1);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return Sets.equalsImpl(this.delegate(), var1);
      }
   }

   @Override
   public boolean remove(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return Maps.removeEntryImpl(this.delegate(), var1);
      }
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return Iterators.removeAll(this.delegate().iterator(), var1);
      }
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return Iterators.retainAll(this.delegate().iterator(), var1);
      }
   }
}
