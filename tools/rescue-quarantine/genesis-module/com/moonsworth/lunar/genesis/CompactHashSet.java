package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.collect.ObjectArrays;
import com.google.common.eventbus.Subscribe;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

@GwtIncompatible
class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
   @Subscribe
   static final double field1 = 0.001;
   private static final int field2 = 9;
   private transient @Nullable Object table;
   private transient int @Nullable [] entries;
   @Subscribe
   transient Object @Nullable [] elements;
   private transient int metadata;
   private transient int size;

   public static <E> CompactHashSet<E> method1() {
      return new CompactHashSet<>();
   }

   public static <E> CompactHashSet<E> method2(Collection<? extends E> list0) {
      CompactHashSet abstractsetloader1 = method4(list0.size());
      abstractsetloader1.addAll(list0);
      return abstractsetloader1;
   }

   @SafeVarargs
   public static <E> CompactHashSet<E> method3(E... items0) {
      CompactHashSet abstractsetloader1 = method4(items0.length);
      Collections.addAll(abstractsetloader1, (E[])items0);
      return abstractsetloader1;
   }

   public static <E> CompactHashSet<E> method4(int number0) {
      return new CompactHashSet<>(number0);
   }

   CompactHashSet() {
      this.init(3);
   }

   CompactHashSet(int number1) {
      this.init(number1);
   }

   void init(int number1) {
      Preconditions.checkArgument(number1 >= 0, "Expected size must be >= 0");
      this.metadata = MixinHelper122.constrainToRange(number1, 1, 1073741823);
   }

   @Subscribe
   boolean needsAllocArrays() {
      return this.table == null;
   }

   @CanIgnoreReturnValue
   int allocArrays() {
      Preconditions.checkState(this.needsAllocArrays(), "Arrays already allocated");
      int index1 = this.metadata;
      int number2 = CompactHashing.tableSize(index1);
      this.table = CompactHashing.createTable(number2);
      this.setHashTableMask(number2 - 1);
      this.entries = new int[index1];
      this.elements = new Object[index1];
      return index1;
   }

   @Subscribe
   @Nullable Set<E> delegateOrNull() {
      return this.table instanceof Set ? (Set)this.table : null;
   }

   private Set<E> createHashFloodingResistantDelegate(int number1) {
      return new LinkedHashSet<>(number1, 1.0F);
   }

   @Subscribe
   @CanIgnoreReturnValue
   Set<E> convertToHashFloodingResistantImplementation() {
      Set set1 = this.createHashFloodingResistantDelegate(this.hashTableMask() + 1);

      for (int index2 = this.firstEntryIndex(); index2 >= 0; index2 = this.getSuccessor(index2)) {
         set1.add(this.elements[index2]);
      }

      this.table = set1;
      this.entries = null;
      this.elements = null;
      this.incrementModCount();
      return set1;
   }

   @Subscribe
   boolean isUsingHashFloodingResistance() {
      return this.delegateOrNull() != null;
   }

   private void setHashTableMask(int number1) {
      int number2 = 32 - Integer.numberOfLeadingZeros(number1);
      this.metadata = CompactHashing.maskCombine(this.metadata, number2, 31);
   }

   private int hashTableMask() {
      return (1 << (this.metadata & 31)) - 1;
   }

   void incrementModCount() {
      this.metadata += 32;
   }

   @CanIgnoreReturnValue
   @Override
   public boolean add(E value1) {
      if (this.needsAllocArrays()) {
         this.allocArrays();
      }

      Set set2 = this.delegateOrNull();
      if (set2 != null) {
         return set2.add(value1);
      }

      int[] items3 = this.entries;
      Object[] items4 = this.elements;
      int number5 = this.size;
      int number6 = number5 + 1;
      int number7 = Hashing.smearedHash(value1);
      int number8 = this.hashTableMask();
      int number9 = number7 & number8;
      int number10 = CompactHashing.tableGet(this.table, number9);
      if (number10 == 0) {
         if (number6 > number8) {
            number8 = this.resizeTable(number8, CompactHashing.newCapacity(number8), number7, number5);
         } else {
            CompactHashing.tableSet(this.table, number9, number5 + 1);
         }
      } else {
         int number13 = CompactHashing.getHashPrefix(number7, number8);
         int index14 = 0;

         int index11;
         int number12;
         do {
            index11 = number10 - 1;
            number12 = items3[index11];
            if (CompactHashing.getHashPrefix(number12, number8) == number13 && Objects.equal(value1, items4[index11])) {
               return false;
            }

            number10 = CompactHashing.getNext(number12, number8);
            index14++;
         } while (number10 != 0);

         if (index14 >= 9) {
            return this.convertToHashFloodingResistantImplementation().add((E)value1);
         }

         if (number6 > number8) {
            number8 = this.resizeTable(number8, CompactHashing.newCapacity(number8), number7, number5);
         } else {
            items3[index11] = CompactHashing.maskCombine(number12, number5 + 1, number8);
         }
      }

      this.resizeMeMaybe(number6);
      this.insertEntry(number5, (E)value1, number7, number8);
      this.size = number6;
      this.incrementModCount();
      return true;
   }

   void insertEntry(int index1, @Nullable E value2, int number3, int number4) {
      this.entries[index1] = CompactHashing.maskCombine(number3, 0, number4);
      this.elements[index1] = value2;
   }

   private void resizeMeMaybe(int number1) {
      int number2 = this.entries.length;
      if (number1 > number2) {
         int number3 = Math.min(1073741823, number2 + Math.max(1, number2 >>> 1) | 1);
         if (number3 != number2) {
            this.resizeEntries(number3);
         }
      }
   }

   void resizeEntries(int number1) {
      this.entries = Arrays.copyOf(this.entries, number1);
      this.elements = Arrays.copyOf(this.elements, number1);
   }

   @CanIgnoreReturnValue
   private int resizeTable(int number1, int number2, int number3, int number4) {
      Object obj5 = CompactHashing.createTable(number2);
      int number6 = number2 - 1;
      if (number4 != 0) {
         CompactHashing.tableSet(obj5, number3 & number6, number4 + 1);
      }

      Object obj7 = this.table;
      int[] items8 = this.entries;

      for (int index9 = 0; index9 <= number1; index9++) {
         int number10 = CompactHashing.tableGet(obj7, index9);

         while (number10 != 0) {
            int index11 = number10 - 1;
            int number12 = items8[index11];
            int number13 = CompactHashing.getHashPrefix(number12, number1) | index9;
            int number14 = number13 & number6;
            int number15 = CompactHashing.tableGet(obj5, number14);
            CompactHashing.tableSet(obj5, number14, number10);
            items8[index11] = CompactHashing.maskCombine(number13, number15, number6);
            number10 = CompactHashing.getNext(number12, number1);
         }
      }

      this.table = obj5;
      this.setHashTableMask(number6);
      return number6;
   }

   @Override
   public boolean contains(Object obj1) {
      if (this.needsAllocArrays()) {
         return false;
      }

      Set set2 = this.delegateOrNull();
      if (set2 != null) {
         return set2.contains(obj1);
      }

      int number3 = Hashing.smearedHash(obj1);
      int number4 = this.hashTableMask();
      int number5 = CompactHashing.tableGet(this.table, number3 & number4);
      if (number5 == 0) {
         return false;
      }

      int number6 = CompactHashing.getHashPrefix(number3, number4);

      do {
         int index7 = number5 - 1;
         int number8 = this.entries[index7];
         if (CompactHashing.getHashPrefix(number8, number4) == number6 && Objects.equal(obj1, this.elements[index7])) {
            return true;
         }

         number5 = CompactHashing.getNext(number8, number4);
      } while (number5 != 0);

      return false;
   }

   @CanIgnoreReturnValue
   @Override
   public boolean remove(Object obj1) {
      if (this.needsAllocArrays()) {
         return false;
      }

      Set set2 = this.delegateOrNull();
      if (set2 != null) {
         return set2.remove(obj1);
      }

      int index3 = this.hashTableMask();
      int number4 = CompactHashing.remove(obj1, null, index3, this.table, this.entries, this.elements, null);
      if (number4 == -1) {
         return false;
      }

      this.moveLastEntry(number4, index3);
      this.size--;
      this.incrementModCount();
      return true;
   }

   void moveLastEntry(int index1, int number2) {
      int index3 = this.size() - 1;
      if (index1 < index3) {
         Object obj4 = this.elements[index3];
         this.elements[index1] = obj4;
         this.elements[index3] = null;
         this.entries[index1] = this.entries[index3];
         this.entries[index3] = 0;
         int number5 = Hashing.smearedHash(obj4) & number2;
         int number6 = CompactHashing.tableGet(this.table, number5);
         int number7 = index3 + 1;
         if (number6 == number7) {
            CompactHashing.tableSet(this.table, number5, index1 + 1);
         } else {
            int index8;
            int number9;
            do {
               index8 = number6 - 1;
               number9 = this.entries[index8];
               number6 = CompactHashing.getNext(number9, number2);
            } while (number6 != number7);

            this.entries[index8] = CompactHashing.maskCombine(number9, index1 + 1, number2);
         }
      } else {
         this.elements[index1] = null;
         this.entries[index1] = 0;
      }
   }

   int firstEntryIndex() {
      return this.isEmpty() ? -1 : 0;
   }

   int getSuccessor(int number1) {
      return number1 + 1 < this.size ? number1 + 1 : -1;
   }

   int adjustAfterRemove(int number1, int number2) {
      return number1 - 1;
   }

   @Override
   public Iterator<E> iterator() {
      Set set1 = this.delegateOrNull();
      return (Iterator<E>)(set1 != null ? set1.iterator() : new AbstractSetLoader$1(this));
   }

   @Override
   public Spliterator<E> spliterator() {
      if (this.needsAllocArrays()) {
         return Spliterators.spliterator(new Object[0], 17);
      }

      Set set1 = this.delegateOrNull();
      return set1 != null ? set1.spliterator() : Spliterators.spliterator(this.elements, 0, this.size, 17);
   }

   @Override
   public void forEach(Consumer<? super E> consumer1) {
      Preconditions.checkNotNull(consumer1);
      Set set2 = this.delegateOrNull();
      if (set2 != null) {
         set2.forEach(consumer1);
      } else {
         for (int index3 = this.firstEntryIndex(); index3 >= 0; index3 = this.getSuccessor(index3)) {
            consumer1.accept(this.elements[index3]);
         }
      }
   }

   @Override
   public int size() {
      Set set1 = this.delegateOrNull();
      return set1 != null ? set1.size() : this.size;
   }

   @Override
   public boolean isEmpty() {
      return this.size() == 0;
   }

   @Override
   public Object[] toArray() {
      if (this.needsAllocArrays()) {
         return new Object[0];
      }

      Set set1 = this.delegateOrNull();
      return set1 != null ? set1.toArray() : Arrays.copyOf(this.elements, this.size);
   }

   @CanIgnoreReturnValue
   @Override
   public <T> T[] toArray(T[] items1) {
      if (this.needsAllocArrays()) {
         if (items1.length > 0) {
            items1[0] = null;
         }

         return (T[])items1;
      } else {
         Set set2 = this.delegateOrNull();
         return (T[])(set2 != null ? set2.toArray(items1) : ObjectArrays.toArrayImpl(this.elements, 0, this.size, items1));
      }
   }

   public void trimToSize() {
      if (!this.needsAllocArrays()) {
         Set set1 = this.delegateOrNull();
         if (set1 != null) {
            Set set5 = this.createHashFloodingResistantDelegate(this.size());
            set5.addAll(set1);
            this.table = set5;
         } else {
            int number2 = this.size;
            if (number2 < this.entries.length) {
               this.resizeEntries(number2);
            }

            int number3 = CompactHashing.tableSize(number2);
            int number4 = this.hashTableMask();
            if (number3 < number4) {
               this.resizeTable(number4, number3, 0, 0);
            }
         }
      }
   }

   @Override
   public void clear() {
      if (!this.needsAllocArrays()) {
         this.incrementModCount();
         Set set1 = this.delegateOrNull();
         if (set1 != null) {
            this.metadata = MixinHelper122.constrainToRange(this.size(), 3, 1073741823);
            set1.clear();
            this.table = null;
            this.size = 0;
         } else {
            Arrays.fill(this.elements, 0, this.size, null);
            CompactHashing.tableClear(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
            this.size = 0;
         }
      }
   }

   private void writeObject(ObjectOutputStream objectoutputstream1) {
      objectoutputstream1.defaultWriteObject();
      objectoutputstream1.writeInt(this.size());

      for (Object obj3 : this) {
         objectoutputstream1.writeObject(obj3);
      }
   }

   private void readObject(ObjectInputStream objectinputstream1) {
      objectinputstream1.defaultReadObject();
      int number2 = objectinputstream1.readInt();
      if (number2 < 0) {
         throw new InvalidObjectException("Invalid size: " + number2);
      }

      this.init(number2);

      for (int index3 = 0; index3 < number2; index3++) {
         Object obj4 = objectinputstream1.readObject();
         this.add((E)obj4);
      }
   }
}
