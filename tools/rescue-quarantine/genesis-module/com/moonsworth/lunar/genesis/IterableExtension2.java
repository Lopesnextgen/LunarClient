package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;

@GwtCompatible(emulated = true)
public interface IterableExtension2<E> extends IterableExtension<E>, CollectionExtension2<E> {
   @Override
   java.util.Comparator<? super E> comparator();

   Multiset.Extension<E> method3();

   Multiset.Extension<E> method4();

   Multiset.Extension<E> method5();

   Multiset.Extension<E> method6();

   NavigableSet<E> elementSet();

   @Override
   Set<Multiset.Extension<E>> entrySet();

   @Override
   Iterator<E> iterator();

   IterableExtension2<E> method7();

   IterableExtension2<E> method6(E var1, MixinHelperType_3 var2);

   IterableExtension2<E> method7(E var1, MixinHelperType_3 var2, E var3, MixinHelperType_3 var4);

   IterableExtension2<E> method8(E var1, MixinHelperType_3 var2);
}
