package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.ForwardingMultiset;
import com.google.common.collect.Multiset;

@Annotation2
@GwtCompatible(emulated = true)
public abstract class MixinHelper31623<E> extends ForwardingMultiset<E> implements IterableExtension2<E> {
   protected MixinHelper31623() {
   }

   protected abstract IterableExtension2<E> method2();

   @Override
   public NavigableSet<E> elementSet() {
      return this.method2().elementSet();
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      return this.method2().comparator();
   }

   @Override
   public IterableExtension2<E> method7() {
      return this.method2().method7();
   }

   @Override
   public Multiset.Extension<E> method3() {
      return this.method2().method3();
   }

   protected Multiset.Extension<E> method8() {
      Iterator var1 = this.entrySet().iterator();
      if (!var1.hasNext()) {
         return null;
      }

      Multiset.Extension var2 = (Multiset.Extension)var1.next();
      return Multisets.method4((E)var2.getElement(), var2.getCount());
   }

   @Override
   public Multiset.Extension<E> method4() {
      return this.method2().method4();
   }

   protected Multiset.Extension<E> method9() {
      Iterator var1 = this.method7().entrySet().iterator();
      if (!var1.hasNext()) {
         return null;
      }

      Multiset.Extension var2 = (Multiset.Extension)var1.next();
      return Multisets.method4((E)var2.getElement(), var2.getCount());
   }

   @Override
   public Multiset.Extension<E> method5() {
      return this.method2().method5();
   }

   protected Multiset.Extension<E> method10() {
      Iterator var1 = this.entrySet().iterator();
      if (!var1.hasNext()) {
         return null;
      }

      Multiset.Extension var2 = (Multiset.Extension)var1.next();
      var2 = Multisets.method4(var2.getElement(), var2.getCount());
      var1.remove();
      return var2;
   }

   @Override
   public Multiset.Extension<E> method6() {
      return this.method2().method6();
   }

   protected Multiset.Extension<E> method11() {
      Iterator var1 = this.method7().entrySet().iterator();
      if (!var1.hasNext()) {
         return null;
      }

      Multiset.Extension var2 = (Multiset.Extension)var1.next();
      var2 = Multisets.method4(var2.getElement(), var2.getCount());
      var1.remove();
      return var2;
   }

   @Override
   public IterableExtension2<E> method6(E var1, MixinHelperType_3 var2) {
      return this.method2().method6((E)var1, var2);
   }

   @Override
   public IterableExtension2<E> method7(E var1, MixinHelperType_3 var2, E var3, MixinHelperType_3 var4) {
      return this.method2().method7((E)var1, var2, (E)var3, var4);
   }

   protected IterableExtension2<E> method13(E var1, MixinHelperType_3 var2, E var3, MixinHelperType_3 var4) {
      return this.method8((E)var1, var2).method6((E)var3, var4);
   }

   @Override
   public IterableExtension2<E> method8(E var1, MixinHelperType_3 var2) {
      return this.method2().method8((E)var1, var2);
   }

   protected class Data extends MixinHelper30.Data2<E> {
      public Data() {
         super(MixinHelper31623.this);
      }
   }

   protected abstract class Data2 extends MixinHelper31622<E> {
      public Data2() {
      }

      @Override
      IterableExtension2<E> method2() {
         return MixinHelper31623.this;
      }
   }
}
