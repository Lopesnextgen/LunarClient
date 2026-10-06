package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;

@GwtCompatible(emulated = true)
final class MixinHelper30 {
   private MixinHelper30() {
   }

   private static <E> E method1(Multiset.Extension<E> var0) {
      if (var0 == null) {
         throw new NoSuchElementException();
      } else {
         return (E)var0.getElement();
      }
   }

   private static <E> E method2(Multiset.@Nullable Extension<E> var0) {
      return (E)(var0 == null ? null : var0.getElement());
   }

   @Annotation3
   static class Data2<E> extends MixinHelper30.Data3<E> implements NavigableSet<E> {
      Data2(IterableExtension2<E> var1) {
         super(var1);
      }

      @Override
      public E lower(E var1) {
         return MixinHelper30.method2((Multiset.Extension<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method6(var1, MixinHelperType_3.OPEN).method4());
      }

      @Override
      public E floor(E var1) {
         return MixinHelper30.method2((Multiset.Extension<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method6(var1, MixinHelperType_3.CLOSED).method4());
      }

      @Override
      public E ceiling(E var1) {
         return MixinHelper30.method2((Multiset.Extension<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method8(var1, MixinHelperType_3.CLOSED).method3());
      }

      @Override
      public E higher(E var1) {
         return MixinHelper30.method2((Multiset.Extension<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method8(var1, MixinHelperType_3.OPEN).method3());
      }

      @Override
      public NavigableSet<E> descendingSet() {
         return new MixinHelper30.Data2<>(this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method7());
      }

      @Override
      public Iterator<E> descendingIterator() {
         return this.descendingSet().iterator();
      }

      @Override
      public E pollFirst() {
         return MixinHelper30.method2(this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method5());
      }

      @Override
      public E pollLast() {
         return MixinHelper30.method2(this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method6());
      }

      @Override
      public NavigableSet<E> subSet(E var1, boolean var2, E var3, boolean var4) {
         return new MixinHelper30.Data2<>(
            (IterableExtension2<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC()
               .method7(var1, MixinHelperType_3.forBoolean(var2), var3, MixinHelperType_3.forBoolean(var4))
         );
      }

      @Override
      public NavigableSet<E> headSet(E var1, boolean var2) {
         return new MixinHelper30.Data2<>((IterableExtension2<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method6(var1, MixinHelperType_3.forBoolean(var2)));
      }

      @Override
      public NavigableSet<E> tailSet(E var1, boolean var2) {
         return new MixinHelper30.Data2<>((IterableExtension2<E>)this.HIHOHOHOOOIOIOICCCOCHCIHOHHHIC().method8(var1, MixinHelperType_3.forBoolean(var2)));
      }
   }

   static class Data3<E> extends MixinHelper33$Data4<E> implements SortedSet<E> {
      @Weak
      private final IterableExtension2<E> field1;

      Data3(IterableExtension2<E> var1) {
         this.field1 = var1;
      }

      final IterableExtension2<E> method2() {
         return this.field1;
      }

      @Override
      public Iterator<E> iterator() {
         return Multisets.elementIterator(this.method2().entrySet().iterator());
      }

      @Override
      public java.util.Comparator<? super E> comparator() {
         return this.method2().comparator();
      }

      @Override
      public SortedSet<E> subSet(E var1, E var2) {
         return this.method2().method7((E)var1, MixinHelperType_3.CLOSED, (E)var2, MixinHelperType_3.OPEN).elementSet();
      }

      @Override
      public SortedSet<E> headSet(E var1) {
         return this.method2().method6((E)var1, MixinHelperType_3.OPEN).elementSet();
      }

      @Override
      public SortedSet<E> tailSet(E var1) {
         return this.method2().method8((E)var1, MixinHelperType_3.CLOSED).elementSet();
      }

      @Override
      public E first() {
         return MixinHelper30.method1(this.method2().method3());
      }

      @Override
      public E last() {
         return MixinHelper30.method1(this.method2().method4());
      }
   }
}
