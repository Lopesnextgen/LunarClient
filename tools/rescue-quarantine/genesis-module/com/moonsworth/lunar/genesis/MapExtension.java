package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public interface MapExtension<K, V> extends Map<K, V> {
   @CanIgnoreReturnValue
   @Override
   @Nullable V put(@Nullable K var1, @Nullable V var2);

   @CanIgnoreReturnValue
   @Nullable V forcePut(@Nullable K var1, @Nullable V var2);

   @Override
   void putAll(Map<? extends K, ? extends V> var1);

   Set<V> values();

   MapExtension<V, K> method2();
}
