package com.moonsworth.lunar.client.event;

import java.util.function.Consumer;
import com.moonsworth.lunar.client.highlight.Highlight;

public interface EventRegistrar {
   default <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      ClientEventBus.method29().method2(var1, var2);
   }

   default <T extends Highlight> void method1(Class<T> var1, Runnable var2) {
      this.handle(var1, var1x -> var2.run());
   }

   default <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int var3) {
      ClientEventBus.method29().method4(var1, var2, var3);
   }

   default <T extends Highlight> void method3(Class<T> var1, Runnable var2, int var3) {
      this.method2(var1, var1x -> var2.run(), var3);
   }
}
