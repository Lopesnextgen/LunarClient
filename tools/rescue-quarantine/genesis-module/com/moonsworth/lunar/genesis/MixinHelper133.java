package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;

@GwtCompatible
public interface MixinHelper133<K, V> extends Multimap<K, V> {
   List<V> get(@Nullable K var1);

   @CanIgnoreReturnValue
   List<V> removeAll(@Nullable Object var1);

   @CanIgnoreReturnValue
   List<V> replaceValues(K var1, Iterable<? extends V> var2);

   @Override
   Map<K, Collection<V>> asMap();

   @Override
   boolean equals(@Nullable Object var1);
}
