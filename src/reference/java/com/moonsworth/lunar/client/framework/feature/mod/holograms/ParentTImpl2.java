package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;

class ParentTImpl2<ParentT extends BridgeExtension, T extends ParentT> extends ParentTHandler<T, ParentT> implements Holograms<ParentT> {
   private final Class<T> field8;

   public ParentTImpl2(IterableExtension<ParentT> var1, Class<T> var2) {
      super(var1);
      this.field8 = var2;
   }

   @Override
   public void method1(ParentT var1) {
      if (this.isEnabled() && this.field8.isInstance(var1)) {
         this.OCOCRCHOHOIICRHIRCCHCIHICHRCHH(var1);
      }
   }

   @Override
   public void method2(ParentT var1) {
      if (this.isEnabled() && this.field8.isInstance(var1)) {
         this.ORCCOORHRRCOHOHRRHIHRRIOCOHCIC(var1);
      }
   }

   @Override
   public void method3(ParentT var1) {
      if (this.isEnabled() && this.field8.isInstance(var1)) {
         this.HIIOIOORCIICIRROCCRIOHRHOOIRCR(var1);
      }
   }
}
