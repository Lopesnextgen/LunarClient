package com.moonsworth.lunar.client.util;

import java.util.function.Supplier;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public interface ThreadModuleDump87 {
   default <S> S initAndGet() {
      this.init();
      return (S)this;
   }

   @OverridingMethodsMustInvokeSuper
   void init();

   static <S extends ThreadModuleDump87> S create(Supplier<S> supplier) {
      ThreadModuleDump87 var1 = (ThreadModuleDump87)supplier.get();
      var1.init();
      return (S)var1;
   }
}
