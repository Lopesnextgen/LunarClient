package com.moonsworth.lunar.client.framework;

import java.util.function.Supplier;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public interface Initializable {
   default <S> S method1() {
      this.method2();
      return (S)this;
   }

   @OverridingMethodsMustInvokeSuper
   void method2();

   static <S extends Initializable> S method3(Supplier<S> supplier0) {
      Initializable threadmoduledump871 = (Initializable)supplier0.get();
      threadmoduledump871.method2();
      return (S)threadmoduledump871;
   }
}
