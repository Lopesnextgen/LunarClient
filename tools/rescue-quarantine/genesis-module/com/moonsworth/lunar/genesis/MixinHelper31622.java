package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.ForwardingMultiset;
import com.google.common.collect.Multiset;

@GwtCompatible(emulated = true)
abstract class MixinHelper31622<E> extends ForwardingMultiset<E> implements IterableExtension2<E> {
   private transient @Nullable Ordering<? super E> comparator;
   private transient @Nullable NavigableSet<E> elementSet;
   private transient @Nullable Set<Multiset.Extension<E>> entrySet;

   abstract IterableExtension2<E> method2();

   @Override
   public java.util.Comparator<? super E> comparator() {
      java.util.Comparator var1 = this.comparator;
      return var1 == null ? (this.comparator = Ordering.method2(this.method2().comparator()).method9()) : var1;
   }

   @Override
   public NavigableSet<E> elementSet() {
      NavigableSet var1 = this.elementSet;
      return var1 == null ? (this.elementSet = new MixinHelper30.Data2<>(this)) : var1;
   }

   @Override
   public Multiset.Extension<E> method5() {
      return this.method2().method6();
   }

   @Override
   public Multiset.Extension<E> method6() {
      return this.method2().method5();
   }

   @Override
   public IterableExtension2<E> method6(E var1, MixinHelperType_3 var2) {
      return this.method2().method8((E)var1, var2).method7();
   }

   @Override
   public IterableExtension2<E> method7(E var1, MixinHelperType_3 var2, E var3, MixinHelperType_3 var4) {
      return this.method2().method7((E)var3, var4, (E)var1, var2).method7();
   }

   @Override
   public IterableExtension2<E> method8(E var1, MixinHelperType_3 var2) {
      return this.method2().method6((E)var1, var2).method7();
   }

   @Override
   protected Multiset<E> method1() {
      return this.method2();
   }

   @Override
   public IterableExtension2<E> method7() {
      return this.method2();
   }

   @Override
   public Multiset.Extension<E> method3() {
      return this.method2().method4();
   }

   @Override
   public Multiset.Extension<E> method4() {
      return this.method2().method3();
   }

   abstract Iterator<Multiset.Extension<E>> entryIterator();

   @Override
   public Set<Multiset.Extension<E>> entrySet() {
      Set var1 = this.entrySet;
      return var1 == null ? (this.entrySet = this.createEntrySet()) : var1;
   }

   Set<Multiset.Extension<E>> createEntrySet() {
      class Data extends MixinHelper33$Data6<E> {
         @Override
         Multiset<E> method1() {
            return MixinHelper31622.this;
         }

         @Override
         public Iterator<Multiset.Extension<E>> iterator() {
            return MixinHelper31622.this.entryIterator();
         }

         @Override
         public int size() {
            return MixinHelper31622.this.method2().entrySet().size();
         }
      }

      return new Data();
   }

   @Override
   public Iterator<E> iterator() {
      return Multisets.method22(this);
   }

   @Override
   public Object[] toArray() {
      return this.standardToArray();
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      return (T[])this.standardToArray(var1);
   }

   @Override
   public String toString() {
      return this.entrySet().toString();
   }
}
