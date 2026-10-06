package com.moonsworth.lunar.client.framework.loading;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;
import com.moonsworth.lunar.client.gui.LoadingScreen;
import java.util.Set;
import lombok.Generated;

public abstract class ItemSetHandler<T> implements LoadableHandler {
   protected final Set<T> field1;

   public ItemSetHandler() {
      LunarLogger.method3("Loading " + this.getClass().getSimpleName() + "...", new Object[0]);
      this.field1 = this.method3();
   }

   protected abstract Set<T> method3();

   @Override
   public void init() {
      if (this instanceof LoadingStage) {
         LoadingScreen.method14().method2((LoadingStage)this);
         ((LoadingStage)this).method1().forEach(arg0 -> {
            try {
               arg0.isLoaded();
               arg0.method3();
            } catch (Exception exception2) {
               exception2.printStackTrace();
            }
         });
         ((LoadingStage)this).method2();
      }

      LunarLogger.method1(this.getClass().getSimpleName() + " loaded " + this.field1.size() + " items.", new Object[0]);
   }

   @Override
   public void close() {
   }

   @Generated
   public Set<T> method13() {
      return this.field1;
   }
}
