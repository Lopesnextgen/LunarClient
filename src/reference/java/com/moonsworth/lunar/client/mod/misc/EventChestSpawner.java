package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public abstract class EventChestSpawner implements EventChestDefinition, EventRegistrar {
   private final Map<Class<Highlight>, Consumer<Highlight>> field1 = new HashMap<>();
   private boolean field2 = false;

   public <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.field1.put(var1, var2);
   }

   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int var3) {
      this.handle(var1, var2);
   }

   private void method4() {
      for (Entry var2 : this.field1.entrySet()) {
         ClientEventBus.method29().method2((Class)var2.getKey(), (Consumer)var2.getValue());
      }
   }

   private void method5() {
      for (Entry var2 : this.field1.entrySet()) {
         ClientEventBus.method29().method6((Class)var2.getKey(), (Consumer)var2.getValue());
      }
   }

   @Override
   public void method2() {
      this.method4();
      this.field2 = true;
   }

   @Override
   public void method3() {
      if (this.field2) {
         this.method5();
      }
   }
}
