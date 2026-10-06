package com.moonsworth.lunar.genesis;

import java.util.concurrent.ScheduledFuture;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;

@Annotation2
@GwtCompatible
public interface FutureExtension3<V> extends ListenableFuture<V>, ScheduledFuture<V> {
}
