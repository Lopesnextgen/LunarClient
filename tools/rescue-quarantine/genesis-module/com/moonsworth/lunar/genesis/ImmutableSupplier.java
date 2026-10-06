package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import com.google.common.base.Supplier;

@Immutable
interface ImmutableSupplier<T> extends Supplier<T> {
}
