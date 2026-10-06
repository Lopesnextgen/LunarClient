package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ObjectArrays;

@GwtIncompatible
class CompactLinkedHashSet<E> extends CompactHashSet<E> {
   private static final int field3 = -2;
   private transient int @Nullable [] predecessor;
   private transient int @Nullable [] successor;
   private transient int firstEntry;
   private transient int lastEntry;

   public static <E> CompactLinkedHashSet<E> method2() {
      return new CompactLinkedHashSet<>();
   }

   public static <E> CompactLinkedHashSet<E> method3(Collection<? extends E> list0) {
      CompactLinkedHashSet abstractsetloader21 = method5(list0.size());
      abstractsetloader21.addAll(list0);
      return abstractsetloader21;
   }

   @SafeVarargs
   public static <E> CompactLinkedHashSet<E> method4(E... items0) {
      CompactLinkedHashSet abstractsetloader21 = method5(items0.length);
      Collections.addAll(abstractsetloader21, items0);
      return abstractsetloader21;
   }

   public static <E> CompactLinkedHashSet<E> method5(int number0) {
      return new CompactLinkedHashSet<>(number0);
   }

   CompactLinkedHashSet() {
   }

   CompactLinkedHashSet(int number1) {
      super(number1);
   }

   void init(int number1) {
      super.init(number1);
      this.firstEntry = -2;
      this.lastEntry = -2;
   }

   int allocArrays() {
      int index1 = super.allocArrays();
      this.predecessor = new int[index1];
      this.successor = new int[index1];
      return index1;
   }

   @CanIgnoreReturnValue
   Set<E> convertToHashFloodingResistantImplementation() {
      Set set1 = super.convertToHashFloodingResistantImplementation();
      this.predecessor = null;
      this.successor = null;
      return set1;
   }

   private int getPredecessor(int index1) {
      return this.predecessor[index1] - 1;
   }

   int getSuccessor(int index1) {
      return this.successor[index1] - 1;
   }

   private void setSuccessor(int index1, int number2) {
      this.successor[index1] = number2 + 1;
   }

   private void setPredecessor(int index1, int number2) {
      this.predecessor[index1] = number2 + 1;
   }

   private void setSucceeds(int number1, int number2) {
      if (number1 == -2) {
         this.firstEntry = number2;
      } else {
         this.setSuccessor(number1, number2);
      }

      if (number2 == -2) {
         this.lastEntry = number1;
      } else {
         this.setPredecessor(number2, number1);
      }
   }

   void insertEntry(int number1, @Nullable E value2, int number3, int number4) {
      super.insertEntry(number1, value2, number3, number4);
      this.setSucceeds(this.lastEntry, number1);
      this.setSucceeds(number1, -2);
   }

   void moveLastEntry(int number1, int number2) {
      int index3 = this.size() - 1;
      super.moveLastEntry(number1, number2);
      this.setSucceeds(this.getPredecessor(number1), this.getSuccessor(number1));
      if (number1 < index3) {
         this.setSucceeds(this.getPredecessor(index3), number1);
         this.setSucceeds(number1, this.getSuccessor(index3));
      }

      this.predecessor[index3] = 0;
      this.successor[index3] = 0;
   }

   void resizeEntries(int number1) {
      super.resizeEntries(number1);
      this.predecessor = Arrays.copyOf(this.predecessor, number1);
      this.successor = Arrays.copyOf(this.successor, number1);
   }

   int firstEntryIndex() {
      return this.firstEntry;
   }

   int adjustAfterRemove(int number1, int number2) {
      return number1 >= this.size() ? number2 : number1;
   }

   public Object[] toArray() {
      return ObjectArrays.toArrayImpl(this);
   }

   public <T> T[] toArray(T[] items1) {
      return (T[])ObjectArrays.toArrayImpl(this, items1);
   }

   public Spliterator<E> spliterator() {
      return Spliterators.spliterator(this, 17);
   }

   public void clear() {
      if (!this.needsAllocArrays()) {
         this.firstEntry = -2;
         this.lastEntry = -2;
         if (this.predecessor != null) {
            Arrays.fill(this.predecessor, 0, this.size(), 0);
            Arrays.fill(this.successor, 0, this.size(), 0);
         }

         super.clear();
      }
   }
}
