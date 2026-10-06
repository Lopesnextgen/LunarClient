package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ObjectArrays;

@Annotation3
class AbstractSetLoader2<E> extends AbstractSetLoader<E> {
   private static final int field3 = -2;
   private transient int @Nullable [] predecessor;
   private transient int @Nullable [] successor;
   private transient int firstEntry;
   private transient int lastEntry;

   public static <E> AbstractSetLoader2<E> method2() {
      return new AbstractSetLoader2<>();
   }

   public static <E> AbstractSetLoader2<E> method3(Collection<? extends E> var0) {
      AbstractSetLoader2 var1 = method5(var0.size());
      var1.addAll(var0);
      return var1;
   }

   @SafeVarargs
   public static <E> AbstractSetLoader2<E> method4(E... var0) {
      AbstractSetLoader2 var1 = method5(var0.length);
      Collections.addAll(var1, (E[])var0);
      return var1;
   }

   public static <E> AbstractSetLoader2<E> method5(int var0) {
      return new AbstractSetLoader2<>(var0);
   }

   AbstractSetLoader2() {
   }

   AbstractSetLoader2(int var1) {
      super(var1);
   }

   @Override
   void init(int var1) {
      super.init(var1);
      this.firstEntry = -2;
      this.lastEntry = -2;
   }

   @Override
   int allocArrays() {
      int var1 = super.allocArrays();
      this.predecessor = new int[var1];
      this.successor = new int[var1];
      return var1;
   }

   @CanIgnoreReturnValue
   @Override
   Set<E> convertToHashFloodingResistantImplementation() {
      Set var1 = super.convertToHashFloodingResistantImplementation();
      this.predecessor = null;
      this.successor = null;
      return var1;
   }

   private int getPredecessor(int var1) {
      return this.predecessor[var1] - 1;
   }

   @Override
   int getSuccessor(int var1) {
      return this.successor[var1] - 1;
   }

   private void setSuccessor(int var1, int var2) {
      this.successor[var1] = var2 + 1;
   }

   private void setPredecessor(int var1, int var2) {
      this.predecessor[var1] = var2 + 1;
   }

   private void setSucceeds(int var1, int var2) {
      if (var1 == -2) {
         this.firstEntry = var2;
      } else {
         this.setSuccessor(var1, var2);
      }

      if (var2 == -2) {
         this.lastEntry = var1;
      } else {
         this.setPredecessor(var2, var1);
      }
   }

   @Override
   void insertEntry(int var1, @Nullable E var2, int var3, int var4) {
      super.insertEntry(var1, (E)var2, var3, var4);
      this.setSucceeds(this.lastEntry, var1);
      this.setSucceeds(var1, -2);
   }

   @Override
   void moveLastEntry(int var1, int var2) {
      int var3 = this.size() - 1;
      super.moveLastEntry(var1, var2);
      this.setSucceeds(this.getPredecessor(var1), this.getSuccessor(var1));
      if (var1 < var3) {
         this.setSucceeds(this.getPredecessor(var3), var1);
         this.setSucceeds(var1, this.getSuccessor(var3));
      }

      this.predecessor[var3] = 0;
      this.successor[var3] = 0;
   }

   @Override
   void resizeEntries(int var1) {
      super.resizeEntries(var1);
      this.predecessor = Arrays.copyOf(this.predecessor, var1);
      this.successor = Arrays.copyOf(this.successor, var1);
   }

   @Override
   int firstEntryIndex() {
      return this.firstEntry;
   }

   @Override
   int adjustAfterRemove(int var1, int var2) {
      return var1 >= this.size() ? var2 : var1;
   }

   @Override
   public Object[] toArray() {
      return ObjectArrays.toArrayImpl(this);
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      return (T[])ObjectArrays.toArrayImpl(this, var1);
   }

   @Override
   public Spliterator<E> spliterator() {
      return Spliterators.spliterator(this, 17);
   }

   @Override
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
