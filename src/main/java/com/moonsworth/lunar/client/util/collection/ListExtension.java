package com.moonsworth.lunar.client.util.collection;

import java.util.List;
import java.util.function.Consumer;

public interface ListExtension<T> extends List<T> {
   void method1(Consumer<ImmutableListView<T>> consumer1);
}
