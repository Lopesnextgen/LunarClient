package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multiset;

final class MixinHelper33$Data8<E> implements Iterator<E> {
   private final Multiset<E> field1;
   private final Iterator<Multiset.Extension<E>> field2;
   private Multiset.@Nullable Extension<E> field3;
   private int laterCount;
   private int totalCount;
   private boolean canRemove;

   MixinHelper33$Data8(Multiset<E> var1, Iterator<Multiset.Extension<E>> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public boolean hasNext() {
      return this.laterCount > 0 || this.field2.hasNext();
   }

   @Override
   public E next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      }

      if (this.laterCount == 0) {
         this.field3 = this.field2.next();
         this.totalCount = this.laterCount = this.field3.getCount();
      }

      this.laterCount--;
      this.canRemove = true;
      return this.field3.getElement();
   }

   @Override
   public void remove() {
      MixinHelper18_3.checkRemove(this.canRemove);
      if (this.totalCount == 1) {
         this.field2.remove();
      } else {
         this.field1.remove(this.field3.getElement());
      }

      this.totalCount--;
      this.canRemove = false;
   }
}
