package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.function.Predicate;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@GwtCompatible
public interface PredicateExtension<T> extends Predicate<T> {
   @CanIgnoreReturnValue
   boolean apply(@Nullable T var1);

   @Override
   boolean equals(@Nullable Object var1);

   @Override
   default boolean test(@Nullable T var1) {
      return this.apply((T)var1);
   }
}
