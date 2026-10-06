package com.moonsworth.lunar.genesis;

import java.util.NavigableSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

@GwtCompatible(emulated = true)
final class MixinHelper48<E> extends MixinHelper33$Data9<E> implements IterableExtension2<E> {
   private transient @Nullable MixinHelper48<E> field3;
   private static final long field4 = 0L;

   MixinHelper48(IterableExtension2<E> var1) {
      super(var1);
   }

   protected IterableExtension2<E> method2() {
      return (IterableExtension2<E>)super.method1();
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      return this.method2().comparator();
   }

   NavigableSet<E> createElementSet() {
      return Sets.unmodifiableNavigableSet(this.method2().elementSet());
   }

   @Override
   public NavigableSet<E> elementSet() {
      return (NavigableSet<E>)super.elementSet();
   }

   @Override
   public IterableExtension2<E> method7() {
      MixinHelper48 var1 = this.field3;
      if (var1 == null) {
         var1 = new MixinHelper48<>(this.method2().method7());
         var1.field3 = this;
         return this.field3 = var1;
      } else {
         return var1;
      }
   }

   @Override
   public Multiset.Extension<E> method3() {
      return this.method2().method3();
   }

   @Override
   public Multiset.Extension<E> method4() {
      return this.method2().method4();
   }

   @Override
   public Multiset.Extension<E> method5() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Multiset.Extension<E> method6() {
      throw new UnsupportedOperationException();
   }

   @Override
   public IterableExtension2<E> method6(E var1, MixinHelperType_3 var2) {
      return Multisets.method3(this.method2().method6((E)var1, var2));
   }

   @Override
   public IterableExtension2<E> method7(E var1, MixinHelperType_3 var2, E var3, MixinHelperType_3 var4) {
      return Multisets.method3(this.method2().method7((E)var1, var2, (E)var3, var4));
   }

   @Override
   public IterableExtension2<E> method8(E var1, MixinHelperType_3 var2) {
      return Multisets.method3(this.method2().method8((E)var1, var2));
   }
}
