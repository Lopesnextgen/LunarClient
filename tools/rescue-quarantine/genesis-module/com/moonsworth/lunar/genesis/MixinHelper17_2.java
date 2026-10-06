package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@GwtCompatible
public interface MixinHelper17_2<I, O> {
   ListenableFuture<O> apply(@Nullable I var1);
}
