package com.moonsworth.lunar.client.util.gui;

import java.util.List;
import java.util.function.Consumer;

public interface ListExtension<T> extends List<T> {
   void method1(Consumer<ListExtension2<T>> var1);
}
