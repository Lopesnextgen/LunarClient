package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import com.google.common.collect.Multiset;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
interface SortedMultisetBridge<E> extends Multiset<E> {
   SortedSet<E> elementSet();
}
