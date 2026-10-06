package com.moonsworth.lunar.genesis;
import com.google.common.base.Supplier;

final class AbstractIdleService$ThreadNameSupplier implements Supplier<String> {
   private AbstractIdleService$ThreadNameSupplier(MixinHelper233 mixinhelper2331) {
      this.field1 = mixinhelper2331;
   }

   public String get() {
      return this.field1.serviceName() + " " + this.field1.method2();
   }
}
