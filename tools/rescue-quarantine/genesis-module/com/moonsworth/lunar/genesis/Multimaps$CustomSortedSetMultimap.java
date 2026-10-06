package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

class Multimaps$CustomSortedSetMultimap<K, V> extends MixinHelper134222<K, V> {
   transient Supplier<? extends SortedSet<V>> field5;
   transient java.util.Comparator<? super V> valueComparator;
   @GwtIncompatible
   private static final long field6 = 0L;

   Multimaps$CustomSortedSetMultimap(Map<K, Collection<V>> map1, Supplier<? extends SortedSet<V>> supplierextension2) {
      super(map1);
      this.field5 = (Supplier<? extends SortedSet<V>>)Preconditions.checkNotNull(supplierextension2);
      this.valueComparator = ((SortedSet)supplierextension2.get()).comparator();
   }

   Set<K> createKeySet() {
      return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
   }

   Map<K, Collection<V>> createAsMap() {
      return this.CRIOHHHIOHHCCIOHHCIIROHRIIHCII();
   }

   protected SortedSet<V> createCollection() {
      return (SortedSet<V>)this.field5.get();
   }

   public java.util.Comparator<? super V> valueComparator() {
      return this.valueComparator;
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream objectoutputstream1) {
      objectoutputstream1.defaultWriteObject();
      objectoutputstream1.writeObject(this.field5);
      objectoutputstream1.writeObject(this.backingMap());
   }

   @GwtIncompatible
   private void readObject(ObjectInputStream objectinputstream1) {
      objectinputstream1.defaultReadObject();
      this.field5 = (Supplier<? extends SortedSet<V>>)objectinputstream1.readObject();
      this.valueComparator = ((SortedSet)this.field5.get()).comparator();
      Map map2 = (Map)objectinputstream1.readObject();
      this.HOCCRCCRCHROIICOOHOHRIICRHCOHR(map2);
   }
}
