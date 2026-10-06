package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

class Multimaps$CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
   transient Supplier<? extends List<V>> field4;
   @GwtIncompatible
   private static final long field5 = 0L;

   Multimaps$CustomListMultimap(Map<K, Collection<V>> map1, Supplier<? extends List<V>> supplierextension2) {
      super(map1);
      this.field4 = (Supplier<? extends List<V>>)Preconditions.checkNotNull(supplierextension2);
   }

   Set<K> createKeySet() {
      return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
   }

   Map<K, Collection<V>> createAsMap() {
      return this.CRIOHHHIOHHCCIOHHCIIROHRIIHCII();
   }

   protected List<V> createCollection() {
      return (List<V>)this.field4.get();
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream objectoutputstream1) {
      objectoutputstream1.defaultWriteObject();
      objectoutputstream1.writeObject(this.field4);
      objectoutputstream1.writeObject(this.backingMap());
   }

   @GwtIncompatible
   private void readObject(ObjectInputStream objectinputstream1) {
      objectinputstream1.defaultReadObject();
      this.field4 = (Supplier<? extends List<V>>)objectinputstream1.readObject();
      Map map2 = (Map)objectinputstream1.readObject();
      this.HOCCRCCRCHROIICOOHOHRIICRHCOHR(map2);
   }
}
