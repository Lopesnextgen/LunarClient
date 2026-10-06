package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
abstract class AbstractCollectionBase5<E> extends AbstractCollectionBase<E> implements IterableExtension2<E> {
   @Annotation_3
   final java.util.Comparator<? super E> field1;
   private transient @Nullable IterableExtension2<E> field2;

   AbstractCollectionBase5() {
      this(Ordering.method1());
   }

   AbstractCollectionBase5(java.util.Comparator<? super E> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public NavigableSet<E> elementSet() {
      return (NavigableSet<E>)super.elementSet();
   }

   NavigableSet<E> createElementSet() {
      return new MixinHelper30.Data2<>(this);
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      return this.field1;
   }

   @Override
   public Multiset.Extension<E> method3() {
      Iterator var1 = this.entryIterator();
      return var1.hasNext() ? (Multiset.Extension)var1.next() : null;
   }

   @Override
   public Multiset.Extension<E> method4() {
      Iterator var1 = this.descendingEntryIterator();
      return var1.hasNext() ? (Multiset.Extension)var1.next() : null;
   }

   @Override
   public Multiset.Extension<E> method5() {
      Iterator var1 = this.entryIterator();
      if (var1.hasNext()) {
         Multiset.Extension var2 = (Multiset.Extension)var1.next();
         var2 = Multisets.method4(var2.getElement(), var2.getCount());
         var1.remove();
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public Multiset.Extension<E> method6() {
      Iterator var1 = this.descendingEntryIterator();
      if (var1.hasNext()) {
         Multiset.Extension var2 = (Multiset.Extension)var1.next();
         var2 = Multisets.method4(var2.getElement(), var2.getCount());
         var1.remove();
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public IterableExtension2<E> method7(@Nullable E var1, MixinHelperType_3 var2, @Nullable E var3, MixinHelperType_3 var4) {
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var4);
      return (IterableExtension2<E>)this.HRICOROOOCCOCOROCRHHCRRIRCOICO(var1, var2).method6(var3, var4);
   }

   abstract Iterator<Multiset.Extension<E>> descendingEntryIterator();

   Iterator<E> descendingIterator() {
      return Multisets.method22(this.method7());
   }

   @Override
   public IterableExtension2<E> method7() {
      IterableExtension2 var1 = this.field2;
      return var1 == null ? (this.field2 = this.method8()) : var1;
   }

   IterableExtension2<E> method8() {
      class Data extends MixinHelper31622<E> {
         @Override
         IterableExtension2<E> method2() {
            return AbstractCollectionBase5.this;
         }

         @Override
         Iterator<Multiset.Extension<E>> entryIterator() {
            return AbstractCollectionBase5.this.descendingEntryIterator();
         }

         @Override
         public Iterator<E> iterator() {
            return AbstractCollectionBase5.this.descendingIterator();
         }
      }

      return new Data();
   }
}
