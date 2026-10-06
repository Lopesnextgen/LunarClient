package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.util.collection.CopyOnWriteIfNeededArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

public class ListenerSet<T> implements OptionUpdateListeners<T> {
   private static final Map<Runnable, Consumer<?>> field1 = new WeakHashMap<>();
   private final List<Consumer<? super T>> field2 = new CopyOnWriteIfNeededArrayList(1);

   public ListenerSet() {
   }

   @Override
   public void forEach(Consumer<Consumer<? super T>> consumer1) {
      this.field2.forEach(consumer1);
   }

   @Override
   public void method1(Consumer<? super T> consumer1) {
      this.field2.add(consumer1);
   }

   @Override
   public void method2(Consumer<? super T> consumer1) {
      this.field2.remove(consumer1);
   }

   @Override
   public void method3(Runnable runnable1) {
      Consumer consumer2 = arg1x -> runnable1.run();
      field1.put(runnable1, consumer2);
      this.field2.add(consumer2);
   }

   @Override
   public void method4(Runnable runnable1) {
      this.field2.remove(field1.remove(runnable1));
   }
}
