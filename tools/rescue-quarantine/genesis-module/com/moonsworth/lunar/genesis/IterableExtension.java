package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface IterableExtension<T> extends Iterable<T> {
   java.util.Comparator<? super T> comparator();

   @Override
   Iterator<T> iterator();
}
