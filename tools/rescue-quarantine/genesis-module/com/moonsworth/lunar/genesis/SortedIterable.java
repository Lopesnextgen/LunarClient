package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface SortedIterable<T> extends Iterable<T> {
   Comparator<? super T> comparator();

   @Override
   Iterator<T> iterator();
}
