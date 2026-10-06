package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data30<K, V> extends MixinHelper$Data25<K, Collection<V>> {
   transient @Nullable Set<Entry<K, Collection<V>>> asMapEntrySet;
   transient @Nullable Collection<Collection<V>> asMapValues;
   private static final long field5 = 0L;

   MixinHelper$Data30(Map<K, Collection<V>> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   public Collection<V> get(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         Collection var3 = (Collection)super.get(var1);
         return var3 == null ? null : MixinHelper_8.access$400(var3, this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Set<Entry<K, Collection<V>>> entrySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.asMapEntrySet == null) {
            this.asMapEntrySet = new MixinHelper$Data27<>(this.delegate().entrySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.asMapEntrySet;
      }
   }

   @Override
   public Collection<Collection<V>> values() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.asMapValues == null) {
            this.asMapValues = new MixinHelper$Data31<>(this.delegate().values(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.asMapValues;
      }
   }

   @Override
   public boolean containsValue(Object var1) {
      return this.values().contains(var1);
   }
}
