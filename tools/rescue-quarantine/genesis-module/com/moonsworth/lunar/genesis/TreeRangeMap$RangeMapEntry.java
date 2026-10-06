package com.moonsworth.lunar.genesis;
import com.google.common.collect.Range;

final class TreeRangeMap$RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
   private final Range<K> field1;
   private final V field2;

   TreeRangeMap$RangeMapEntry(SerializableLoader<K> serializableloader1, SerializableLoader<K> serializableloader2, V value3) {
      this(Range.method4(serializableloader1, serializableloader2), (V)value3);
   }

   TreeRangeMap$RangeMapEntry(Range<K> serializablebase21, V value2) {
      this.field1 = serializablebase21;
      this.field2 = (V)value2;
   }

   public Range<K> method1() {
      return this.field1;
   }

   public V getValue() {
      return this.field2;
   }

   public boolean contains(K value1) {
      return this.field1.contains(value1);
   }

   SerializableLoader<K> method2() {
      return this.field1.field2;
   }

   SerializableLoader<K> method3() {
      return this.field1.field3;
   }
}
