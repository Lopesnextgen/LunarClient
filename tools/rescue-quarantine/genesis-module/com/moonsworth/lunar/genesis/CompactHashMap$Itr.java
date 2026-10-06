package com.moonsworth.lunar.genesis;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class CompactHashMap$Itr<T> implements Iterator<T> {
   int expectedMetadata;
   int currentIndex;
   int indexToRemove;

   private CompactHashMap$Itr(AbstractMapLoader2 abstractmaploader21) {
      this.field1 = abstractmaploader21;
      this.expectedMetadata = AbstractMapLoader2.method3(this.field1);
      this.currentIndex = this.field1.firstEntryIndex();
      this.indexToRemove = -1;
   }

   @Override
   public boolean hasNext() {
      return this.currentIndex >= 0;
   }

   abstract T getOutput(int number1);

   @Override
   public T next() {
      this.checkForConcurrentModification();
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      }

      this.indexToRemove = this.currentIndex;
      Object obj1 = this.getOutput(this.currentIndex);
      this.currentIndex = this.field1.getSuccessor(this.currentIndex);
      return (T)obj1;
   }

   @Override
   public void remove() {
      this.checkForConcurrentModification();
      CollectPreconditions.checkRemove(this.indexToRemove >= 0);
      this.incrementExpectedModCount();
      this.field1.remove(this.field1.keys[this.indexToRemove]);
      this.currentIndex = this.field1.adjustAfterRemove(this.currentIndex, this.indexToRemove);
      this.indexToRemove = -1;
   }

   void incrementExpectedModCount() {
      this.expectedMetadata += 32;
   }

   private void checkForConcurrentModification() {
      if (AbstractMapLoader2.method3(this.field1) != this.expectedMetadata) {
         throw new ConcurrentModificationException();
      }
   }
}
