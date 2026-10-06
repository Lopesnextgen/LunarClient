package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import com.google.common.collect.Multiset;

@Annotation3
interface CollectionExtension2<E> extends Multiset<E> {
   SortedSet<E> elementSet();
}
