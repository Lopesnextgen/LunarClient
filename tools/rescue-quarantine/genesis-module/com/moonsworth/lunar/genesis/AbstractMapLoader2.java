package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ObjectArrays;
import com.google.common.base.Preconditions;

@Annotation3
class AbstractMapLoader2<K, V> extends AbstractMap<K, V> implements Serializable {
   private static final Object field1 = new Object();
   @Annotation4
   static final double field2 = 0.001;
   private static final int field3 = 9;
   private transient @Nullable Object table;
   @Annotation4
   transient int @Nullable [] entries;
   @Annotation4
   transient Object @Nullable [] keys;
   @Annotation4
   transient Object @Nullable [] values;
   private transient int metadata;
   private transient int size;
   private transient @Nullable Set<K> keySetView;
   private transient @Nullable Set<Entry<K, V>> entrySetView;
   private transient @Nullable Collection<V> valuesView;

   public static <K, V> AbstractMapLoader2<K, V> method1() {
      return new AbstractMapLoader2<>();
   }

   public static <K, V> AbstractMapLoader2<K, V> method2(int var0) {
      return new AbstractMapLoader2<>(var0);
   }

   AbstractMapLoader2() {
      this.init(3);
   }

   AbstractMapLoader2(int var1) {
      this.init(var1);
   }

   void init(int var1) {
      Preconditions.checkArgument(var1 >= 0, "Expected size must be >= 0");
      this.metadata = MixinHelper122.constrainToRange(var1, 1, 1073741823);
   }

   @Annotation4
   boolean needsAllocArrays() {
      return this.table == null;
   }

   @CanIgnoreReturnValue
   int allocArrays() {
      Preconditions.checkState(this.needsAllocArrays(), "Arrays already allocated");
      int var1 = this.metadata;
      int var2 = MixinHelper41.tableSize(var1);
      this.table = MixinHelper41.createTable(var2);
      this.setHashTableMask(var2 - 1);
      this.entries = new int[var1];
      this.keys = new Object[var1];
      this.values = new Object[var1];
      return var1;
   }

   @Annotation4
   @Nullable Map<K, V> delegateOrNull() {
      return this.table instanceof Map ? (Map)this.table : null;
   }

   Map<K, V> createHashFloodingResistantDelegate(int var1) {
      return new LinkedHashMap<>(var1, 1.0F);
   }

   @Annotation4
   @CanIgnoreReturnValue
   Map<K, V> convertToHashFloodingResistantImplementation() {
      Map var1 = this.createHashFloodingResistantDelegate(this.hashTableMask() + 1);

      for (int var2 = this.firstEntryIndex(); var2 >= 0; var2 = this.getSuccessor(var2)) {
         var1.put(this.keys[var2], this.values[var2]);
      }

      this.table = var1;
      this.entries = null;
      this.keys = null;
      this.values = null;
      this.incrementModCount();
      return var1;
   }

   private void setHashTableMask(int var1) {
      int var2 = 32 - Integer.numberOfLeadingZeros(var1);
      this.metadata = MixinHelper41.maskCombine(this.metadata, var2, 31);
   }

   private int hashTableMask() {
      return (1 << (this.metadata & 31)) - 1;
   }

   void incrementModCount() {
      this.metadata += 32;
   }

   void accessEntry(int var1) {
   }

   @CanIgnoreReturnValue
   @Override
   public V put(K var1, V var2) {
      if (this.needsAllocArrays()) {
         this.allocArrays();
      }

      Map var3 = this.delegateOrNull();
      if (var3 != null) {
         return (V)var3.put(var1, var2);
      }

      int[] var4 = this.entries;
      Object[] var5 = this.keys;
      Object[] var6 = this.values;
      int var7 = this.size;
      int var8 = var7 + 1;
      int var9 = MixinHelper36_2.smearedHash(var1);
      int var10 = this.hashTableMask();
      int var11 = var9 & var10;
      int var12 = MixinHelper41.tableGet(this.table, var11);
      if (var12 == 0) {
         if (var8 > var10) {
            var10 = this.resizeTable(var10, MixinHelper41.newCapacity(var10), var9, var7);
         } else {
            MixinHelper41.tableSet(this.table, var11, var7 + 1);
         }
      } else {
         int var15 = MixinHelper41.getHashPrefix(var9, var10);
         int var16 = 0;

         int var13;
         int var14;
         do {
            var13 = var12 - 1;
            var14 = var4[var13];
            if (MixinHelper41.getHashPrefix(var14, var10) == var15 && MixinHelper72.equal(var1, var5[var13])) {
               Object var17 = var6[var13];
               var6[var13] = var2;
               this.accessEntry(var13);
               return (V)var17;
            }

            var12 = MixinHelper41.getNext(var14, var10);
            var16++;
         } while (var12 != 0);

         if (var16 >= 9) {
            return this.convertToHashFloodingResistantImplementation().put((K)var1, (V)var2);
         }

         if (var8 > var10) {
            var10 = this.resizeTable(var10, MixinHelper41.newCapacity(var10), var9, var7);
         } else {
            var4[var13] = MixinHelper41.maskCombine(var14, var7 + 1, var10);
         }
      }

      this.resizeMeMaybe(var8);
      this.insertEntry(var7, (K)var1, (V)var2, var9, var10);
      this.size = var8;
      this.incrementModCount();
      return null;
   }

   void insertEntry(int var1, @Nullable K var2, @Nullable V var3, int var4, int var5) {
      this.entries[var1] = MixinHelper41.maskCombine(var4, 0, var5);
      this.keys[var1] = var2;
      this.values[var1] = var3;
   }

   private void resizeMeMaybe(int var1) {
      int var2 = this.entries.length;
      if (var1 > var2) {
         int var3 = Math.min(1073741823, var2 + Math.max(1, var2 >>> 1) | 1);
         if (var3 != var2) {
            this.resizeEntries(var3);
         }
      }
   }

   void resizeEntries(int var1) {
      this.entries = Arrays.copyOf(this.entries, var1);
      this.keys = Arrays.copyOf(this.keys, var1);
      this.values = Arrays.copyOf(this.values, var1);
   }

   @CanIgnoreReturnValue
   private int resizeTable(int var1, int var2, int var3, int var4) {
      Object var5 = MixinHelper41.createTable(var2);
      int var6 = var2 - 1;
      if (var4 != 0) {
         MixinHelper41.tableSet(var5, var3 & var6, var4 + 1);
      }

      Object var7 = this.table;
      int[] var8 = this.entries;

      for (int var9 = 0; var9 <= var1; var9++) {
         int var10 = MixinHelper41.tableGet(var7, var9);

         while (var10 != 0) {
            int var11 = var10 - 1;
            int var12 = var8[var11];
            int var13 = MixinHelper41.getHashPrefix(var12, var1) | var9;
            int var14 = var13 & var6;
            int var15 = MixinHelper41.tableGet(var5, var14);
            MixinHelper41.tableSet(var5, var14, var10);
            var8[var11] = MixinHelper41.maskCombine(var13, var15, var6);
            var10 = MixinHelper41.getNext(var12, var1);
         }
      }

      this.table = var5;
      this.setHashTableMask(var6);
      return var6;
   }

   private int indexOf(@Nullable Object var1) {
      if (this.needsAllocArrays()) {
         return -1;
      }

      int var2 = MixinHelper36_2.smearedHash(var1);
      int var3 = this.hashTableMask();
      int var4 = MixinHelper41.tableGet(this.table, var2 & var3);
      if (var4 == 0) {
         return -1;
      }

      int var5 = MixinHelper41.getHashPrefix(var2, var3);

      do {
         int var6 = var4 - 1;
         int var7 = this.entries[var6];
         if (MixinHelper41.getHashPrefix(var7, var3) == var5 && MixinHelper72.equal(var1, this.keys[var6])) {
            return var6;
         }

         var4 = MixinHelper41.getNext(var7, var3);
      } while (var4 != 0);

      return -1;
   }

   @Override
   public boolean containsKey(Object var1) {
      Map var2 = this.delegateOrNull();
      return var2 != null ? var2.containsKey(var1) : this.indexOf(var1) != -1;
   }

   @Override
   public V get(Object var1) {
      Map var2 = this.delegateOrNull();
      if (var2 != null) {
         return (V)var2.get(var1);
      }

      int var3 = this.indexOf(var1);
      if (var3 == -1) {
         return null;
      }

      this.accessEntry(var3);
      return (V)this.values[var3];
   }

   @CanIgnoreReturnValue
   @Override
   public V remove(Object var1) {
      Map var2 = this.delegateOrNull();
      if (var2 != null) {
         return (V)var2.remove(var1);
      }

      Object var3 = this.removeHelper(var1);
      return (V)(var3 == field1 ? null : var3);
   }

   private Object removeHelper(Object var1) {
      if (this.needsAllocArrays()) {
         return field1;
      }

      int var2 = this.hashTableMask();
      int var3 = MixinHelper41.remove(var1, null, var2, this.table, this.entries, this.keys, null);
      if (var3 == -1) {
         return field1;
      }

      Object var4 = this.values[var3];
      this.moveLastEntry(var3, var2);
      this.size--;
      this.incrementModCount();
      return var4;
   }

   void moveLastEntry(int var1, int var2) {
      int var3 = this.size() - 1;
      if (var1 < var3) {
         Object var4 = this.keys[var3];
         this.keys[var1] = var4;
         this.values[var1] = this.values[var3];
         this.keys[var3] = null;
         this.values[var3] = null;
         this.entries[var1] = this.entries[var3];
         this.entries[var3] = 0;
         int var5 = MixinHelper36_2.smearedHash(var4) & var2;
         int var6 = MixinHelper41.tableGet(this.table, var5);
         int var7 = var3 + 1;
         if (var6 == var7) {
            MixinHelper41.tableSet(this.table, var5, var1 + 1);
         } else {
            int var8;
            int var9;
            do {
               var8 = var6 - 1;
               var9 = this.entries[var8];
               var6 = MixinHelper41.getNext(var9, var2);
            } while (var6 != var7);

            this.entries[var8] = MixinHelper41.maskCombine(var9, var1 + 1, var2);
         }
      } else {
         this.keys[var1] = null;
         this.values[var1] = null;
         this.entries[var1] = 0;
      }
   }

   int firstEntryIndex() {
      return this.isEmpty() ? -1 : 0;
   }

   int getSuccessor(int var1) {
      return var1 + 1 < this.size ? var1 + 1 : -1;
   }

   int adjustAfterRemove(int var1, int var2) {
      return var1 - 1;
   }

   @Override
   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> var1) {
      Preconditions.checkNotNull(var1);
      Map var2 = this.delegateOrNull();
      if (var2 != null) {
         var2.replaceAll(var1);
      } else {
         for (int var3 = 0; var3 < this.size; var3++) {
            this.values[var3] = var1.apply(this.keys[var3], this.values[var3]);
         }
      }
   }

   @Override
   public Set<K> keySet() {
      return this.keySetView == null ? (this.keySetView = this.createKeySet()) : this.keySetView;
   }

   Set<K> createKeySet() {
      return new AbstractMapLoader2.Data3();
   }

   Iterator<K> keySetIterator() {
      Map var1 = this.delegateOrNull();
      return var1 != null ? var1.keySet().iterator() : new AbstractMapLoader2<K, V>.Data<K>() {
         @Override
         K getOutput(int var1) {
            return (K)AbstractMapLoader2.this.keys[var1];
         }
      };
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);
      Map var2 = this.delegateOrNull();
      if (var2 != null) {
         var2.forEach(var1);
      } else {
         for (int var3 = this.firstEntryIndex(); var3 >= 0; var3 = this.getSuccessor(var3)) {
            var1.accept(this.keys[var3], this.values[var3]);
         }
      }
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      return this.entrySetView == null ? (this.entrySetView = this.createEntrySet()) : this.entrySetView;
   }

   Set<Entry<K, V>> createEntrySet() {
      return new AbstractMapLoader2.Data2();
   }

   Iterator<Entry<K, V>> entrySetIterator() {
      Map var1 = this.delegateOrNull();
      return var1 != null ? var1.entrySet().iterator() : new AbstractMapLoader2<K, V>.Data<Entry<K, V>>() {
         Entry<K, V> getOutput(int var1) {
            return AbstractMapLoader2.this.new Data4(var1);
         }
      };
   }

   @Override
   public int size() {
      Map var1 = this.delegateOrNull();
      return var1 != null ? var1.size() : this.size;
   }

   @Override
   public boolean isEmpty() {
      return this.size() == 0;
   }

   @Override
   public boolean containsValue(Object var1) {
      Map var2 = this.delegateOrNull();
      if (var2 != null) {
         return var2.containsValue(var1);
      }

      for (int var3 = 0; var3 < this.size; var3++) {
         if (MixinHelper72.equal(var1, this.values[var3])) {
            return true;
         }
      }

      return false;
   }

   @Override
   public Collection<V> values() {
      return this.valuesView == null ? (this.valuesView = this.createValues()) : this.valuesView;
   }

   Collection<V> createValues() {
      return new AbstractMapLoader2.Data5();
   }

   Iterator<V> valuesIterator() {
      Map var1 = this.delegateOrNull();
      return var1 != null ? var1.values().iterator() : new AbstractMapLoader2<K, V>.Data<V>() {
         @Override
         V getOutput(int var1) {
            return (V)AbstractMapLoader2.this.values[var1];
         }
      };
   }

   public void trimToSize() {
      if (!this.needsAllocArrays()) {
         Map var1 = this.delegateOrNull();
         if (var1 != null) {
            Map var5 = this.createHashFloodingResistantDelegate(this.size());
            var5.putAll(var1);
            this.table = var5;
         } else {
            int var2 = this.size;
            if (var2 < this.entries.length) {
               this.resizeEntries(var2);
            }

            int var3 = MixinHelper41.tableSize(var2);
            int var4 = this.hashTableMask();
            if (var3 < var4) {
               this.resizeTable(var4, var3, 0, 0);
            }
         }
      }
   }

   @Override
   public void clear() {
      if (!this.needsAllocArrays()) {
         this.incrementModCount();
         Map var1 = this.delegateOrNull();
         if (var1 != null) {
            this.metadata = MixinHelper122.constrainToRange(this.size(), 3, 1073741823);
            var1.clear();
            this.table = null;
            this.size = 0;
         } else {
            Arrays.fill(this.keys, 0, this.size, null);
            Arrays.fill(this.values, 0, this.size, null);
            MixinHelper41.tableClear(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
            this.size = 0;
         }
      }
   }

   private void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      var1.writeInt(this.size());
      Iterator var2 = this.entrySetIterator();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         var1.writeObject(var3.getKey());
         var1.writeObject(var3.getValue());
      }
   }

   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      int var2 = var1.readInt();
      if (var2 < 0) {
         throw new InvalidObjectException("Invalid size: " + var2);
      }

      this.init(var2);

      for (int var3 = 0; var3 < var2; var3++) {
         Object var4 = var1.readObject();
         Object var5 = var1.readObject();
         this.put((K)var4, (V)var5);
      }
   }

   private abstract class Data<T> implements Iterator<T> {
      int expectedMetadata = AbstractMapLoader2.this.metadata;
      int currentIndex = AbstractMapLoader2.this.firstEntryIndex();
      int indexToRemove = -1;

      private Data() {
      }

      @Override
      public boolean hasNext() {
         return this.currentIndex >= 0;
      }

      abstract T getOutput(int var1);

      @Override
      public T next() {
         this.checkForConcurrentModification();
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         }

         this.indexToRemove = this.currentIndex;
         Object var1 = this.getOutput(this.currentIndex);
         this.currentIndex = AbstractMapLoader2.this.getSuccessor(this.currentIndex);
         return (T)var1;
      }

      @Override
      public void remove() {
         this.checkForConcurrentModification();
         MixinHelper18_3.checkRemove(this.indexToRemove >= 0);
         this.incrementExpectedModCount();
         AbstractMapLoader2.this.remove(AbstractMapLoader2.this.keys[this.indexToRemove]);
         this.currentIndex = AbstractMapLoader2.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
         this.indexToRemove = -1;
      }

      void incrementExpectedModCount() {
         this.expectedMetadata += 32;
      }

      private void checkForConcurrentModification() {
         if (AbstractMapLoader2.this.metadata != this.expectedMetadata) {
            throw new ConcurrentModificationException();
         }
      }
   }

   class Data2 extends MixinHelper19$Data32<K, V> {
      @Override
      Map<K, V> map() {
         return AbstractMapLoader2.this;
      }

      @Override
      public Iterator<Entry<K, V>> iterator() {
         return AbstractMapLoader2.this.entrySetIterator();
      }

      @Override
      public Spliterator<Entry<K, V>> spliterator() {
         Map var1 = AbstractMapLoader2.this.delegateOrNull();
         return var1 != null
            ? var1.entrySet().spliterator()
            : MixinHelper3_5.indexed(AbstractMapLoader2.this.size, 17, var1x -> AbstractMapLoader2.this.new Data4(var1x));
      }

      @Override
      public boolean contains(Object var1) {
         Map var2 = AbstractMapLoader2.this.delegateOrNull();
         if (var2 != null) {
            return var2.entrySet().contains(var1);
         }

         if (!(var1 instanceof Entry)) {
            return false;
         }

         Entry var3 = (Entry)var1;
         int var4 = AbstractMapLoader2.this.indexOf(var3.getKey());
         return var4 != -1 && MixinHelper72.equal(AbstractMapLoader2.this.values[var4], var3.getValue());
      }

      @Override
      public boolean remove(Object var1) {
         Map var2 = AbstractMapLoader2.this.delegateOrNull();
         if (var2 != null) {
            return var2.entrySet().remove(var1);
         }

         if (var1 instanceof Entry) {
            Entry var3 = (Entry)var1;
            if (AbstractMapLoader2.this.needsAllocArrays()) {
               return false;
            }

            int var4 = AbstractMapLoader2.this.hashTableMask();
            int var5 = MixinHelper41.remove(
               var3.getKey(),
               var3.getValue(),
               var4,
               AbstractMapLoader2.this.table,
               AbstractMapLoader2.this.entries,
               AbstractMapLoader2.this.keys,
               AbstractMapLoader2.this.values
            );
            if (var5 == -1) {
               return false;
            }

            AbstractMapLoader2.this.moveLastEntry(var5, var4);
            AbstractMapLoader2.this.size--;
            AbstractMapLoader2.this.incrementModCount();
            return true;
         } else {
            return false;
         }
      }
   }

   class Data3 extends MixinHelper19$Data25<K, V> {
      Data3() {
         super(AbstractMapLoader2.this);
      }

      @Override
      public Object[] toArray() {
         if (AbstractMapLoader2.this.needsAllocArrays()) {
            return new Object[0];
         }

         Map var1 = AbstractMapLoader2.this.delegateOrNull();
         return var1 != null ? var1.keySet().toArray() : ObjectArrays.copyAsObjectArray(AbstractMapLoader2.this.keys, 0, AbstractMapLoader2.this.size);
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         if (AbstractMapLoader2.this.needsAllocArrays()) {
            if (var1.length > 0) {
               var1[0] = null;
            }

            return (T[])var1;
         } else {
            Map var2 = AbstractMapLoader2.this.delegateOrNull();
            return (T[])(var2 != null
               ? var2.keySet().toArray(var1)
               : ObjectArrays.toArrayImpl(AbstractMapLoader2.this.keys, 0, AbstractMapLoader2.this.size, var1));
         }
      }

      @Override
      public boolean remove(Object var1) {
         Map var2 = AbstractMapLoader2.this.delegateOrNull();
         return var2 != null ? var2.keySet().remove(var1) : AbstractMapLoader2.this.removeHelper(var1) != AbstractMapLoader2.field1;
      }

      @Override
      public Iterator<K> iterator() {
         return AbstractMapLoader2.this.keySetIterator();
      }

      @Override
      public Spliterator<K> spliterator() {
         if (AbstractMapLoader2.this.needsAllocArrays()) {
            return Spliterators.spliterator(new Object[0], 17);
         }

         Map var1 = AbstractMapLoader2.this.delegateOrNull();
         return var1 != null ? var1.keySet().spliterator() : Spliterators.spliterator(AbstractMapLoader2.this.keys, 0, AbstractMapLoader2.this.size, 17);
      }

      @Override
      public void forEach(Consumer<? super K> var1) {
         Preconditions.checkNotNull(var1);
         Map var2 = AbstractMapLoader2.this.delegateOrNull();
         if (var2 != null) {
            var2.keySet().forEach(var1);
         } else {
            for (int var3 = AbstractMapLoader2.this.firstEntryIndex(); var3 >= 0; var3 = AbstractMapLoader2.this.getSuccessor(var3)) {
               var1.accept(AbstractMapLoader2.this.keys[var3]);
            }
         }
      }
   }

   final class Data4 extends MixinHelper32<K, V> {
      private final @Nullable Object field1;
      private int lastKnownIndex;

      Data4(int var2) {
         this.field1 = AbstractMapLoader2.this.keys[var2];
         this.lastKnownIndex = var2;
      }

      @Override
      public @Nullable K getKey() {
         return (K)this.field1;
      }

      private void updateLastKnownIndex() {
         if (this.lastKnownIndex == -1
            || this.lastKnownIndex >= AbstractMapLoader2.this.size()
            || !MixinHelper72.equal(this.field1, AbstractMapLoader2.this.keys[this.lastKnownIndex])) {
            this.lastKnownIndex = AbstractMapLoader2.this.indexOf(this.field1);
         }
      }

      @Override
      public V getValue() {
         Map var1 = AbstractMapLoader2.this.delegateOrNull();
         if (var1 != null) {
            return (V)var1.get(this.field1);
         }

         this.updateLastKnownIndex();
         return (V)(this.lastKnownIndex == -1 ? null : AbstractMapLoader2.this.values[this.lastKnownIndex]);
      }

      @Override
      public V setValue(V var1) {
         Map var2 = AbstractMapLoader2.this.delegateOrNull();
         if (var2 != null) {
            return (V)var2.put(this.field1, var1);
         } else {
            this.updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
               AbstractMapLoader2.this.put((K)this.field1, (V)var1);
               return null;
            } else {
               Object var3 = AbstractMapLoader2.this.values[this.lastKnownIndex];
               AbstractMapLoader2.this.values[this.lastKnownIndex] = var1;
               return (V)var3;
            }
         }
      }
   }

   class Data5 extends MixinHelper19$Data35<K, V> {
      Data5() {
         super(AbstractMapLoader2.this);
      }

      @Override
      public Iterator<V> iterator() {
         return AbstractMapLoader2.this.valuesIterator();
      }

      @Override
      public void forEach(Consumer<? super V> var1) {
         Preconditions.checkNotNull(var1);
         Map var2 = AbstractMapLoader2.this.delegateOrNull();
         if (var2 != null) {
            var2.values().forEach(var1);
         } else {
            for (int var3 = AbstractMapLoader2.this.firstEntryIndex(); var3 >= 0; var3 = AbstractMapLoader2.this.getSuccessor(var3)) {
               var1.accept(AbstractMapLoader2.this.values[var3]);
            }
         }
      }

      @Override
      public Spliterator<V> spliterator() {
         if (AbstractMapLoader2.this.needsAllocArrays()) {
            return Spliterators.spliterator(new Object[0], 16);
         }

         Map var1 = AbstractMapLoader2.this.delegateOrNull();
         return var1 != null ? var1.values().spliterator() : Spliterators.spliterator(AbstractMapLoader2.this.values, 0, AbstractMapLoader2.this.size, 16);
      }

      @Override
      public Object[] toArray() {
         if (AbstractMapLoader2.this.needsAllocArrays()) {
            return new Object[0];
         }

         Map var1 = AbstractMapLoader2.this.delegateOrNull();
         return var1 != null ? var1.values().toArray() : ObjectArrays.copyAsObjectArray(AbstractMapLoader2.this.values, 0, AbstractMapLoader2.this.size);
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         if (AbstractMapLoader2.this.needsAllocArrays()) {
            if (var1.length > 0) {
               var1[0] = null;
            }

            return (T[])var1;
         } else {
            Map var2 = AbstractMapLoader2.this.delegateOrNull();
            return (T[])(var2 != null
               ? var2.values().toArray(var1)
               : ObjectArrays.toArrayImpl(AbstractMapLoader2.this.values, 0, AbstractMapLoader2.this.size, var1));
         }
      }
   }
}
