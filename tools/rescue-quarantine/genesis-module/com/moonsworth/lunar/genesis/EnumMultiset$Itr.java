package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class EnumMultiset$Itr<T> implements Iterator<T> {
   int index;
   int toRemove;

   EnumMultiset$Itr(AbstractCollectionBase2 abstractcollectionbase21) {
      this.field1 = abstractcollectionbase21;
      this.index = 0;
      this.toRemove = -1;
   }

   abstract T output(int number1);

   @Override
   public boolean hasNext() {
      while (this.index < AbstractCollectionBase2.method4(this.field1).length) {
         if (AbstractCollectionBase2.method5(this.field1)[this.index] > 0) {
            return true;
         }

         this.index++;
      }

      return false;
   }

   @Override
   public T next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      }

      Object obj1 = this.output(this.index);
      this.toRemove = this.index++;
      return (T)obj1;
   }

   @Override
   public void remove() {
      CollectPreconditions.checkRemove(this.toRemove >= 0);
      if (AbstractCollectionBase2.method5(this.field1)[this.toRemove] > 0) {
         AbstractCollectionBase2.method6(this.field1);
         AbstractCollectionBase2.method8(
            this.field1, AbstractCollectionBase2.method7(this.field1) - AbstractCollectionBase2.method5(this.field1)[this.toRemove]
         );
         AbstractCollectionBase2.method5(this.field1)[this.toRemove] = 0;
      }

      this.toRemove = -1;
   }
}
