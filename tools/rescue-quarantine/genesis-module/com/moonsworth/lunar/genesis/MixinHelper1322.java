package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public interface MixinHelper1322<K, V> extends MixinHelper132_2<K, V> {
   SortedSet<V> get(@Nullable K var1);

   @CanIgnoreReturnValue
   SortedSet<V> removeAll(@Nullable Object var1);

   @CanIgnoreReturnValue
   SortedSet<V> replaceValues(K var1, Iterable<? extends V> var2);

   @Override
   Map<K, Collection<V>> asMap();

   java.util.Comparator<? super V> valueComparator();
}
