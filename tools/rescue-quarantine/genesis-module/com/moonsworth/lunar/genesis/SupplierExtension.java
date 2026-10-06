package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.function.Supplier;
import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@GwtCompatible
public interface SupplierExtension<T> extends Supplier<T> {
   @CanIgnoreReturnValue
   @Override
   T get();
}
