package com.moonsworth.lunar.client.framework.loading;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;
import com.moonsworth.lunar.client.gui.LoadingScreen;
import java.util.Map;
import lombok.Generated;

public abstract class ItemMapHandler<T, V> implements LoadableHandler {
   private final Map<T, V> field1 = this.method3();

   public ItemMapHandler() {
   }

   protected abstract Map<T, V> method3();

   public void clear() {
      this.field1.clear();
   }

   @Override
   public void init() {
      if (this instanceof LoadingStage) {
         LoadingScreen.method14().method2((LoadingStage)this);
         ((LoadingStage)this).method1().forEach(arg0 -> {
            arg0.isLoaded();
            arg0.method3();
         });
      }

      LunarLogger.method1(this.getClass().getSimpleName() + " loaded " + this.field1.size() + " items.", new Object[0]);
   }

   @Override
   public void close() {
   }

   @Generated
   public Map<T, V> method2() {
      return this.field1;
   }
}
