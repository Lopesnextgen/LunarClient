package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.function.Function;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@GwtCompatible
public interface MixinHelper24_2<F, T> extends Function<F, T> {
   @CanIgnoreReturnValue
   @Override
   @Nullable T apply(@Nullable F var1);

   @Override
   boolean equals(@Nullable Object var1);
}
