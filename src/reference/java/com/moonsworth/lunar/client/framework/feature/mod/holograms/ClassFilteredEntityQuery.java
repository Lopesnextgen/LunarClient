package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;

class ClassFilteredEntityQuery<ParentT extends MovementInputMarker, T extends ParentT> extends ParentTHandler<T, ParentT> implements EntityChangeListener<ParentT> {
   private final Class<T> field8;

   public ClassFilteredEntityQuery(IterableExtension<ParentT> iterableextension1, Class<T> clazz2) {
      super(iterableextension1);
      this.field8 = clazz2;
   }

   @Override
   public void method1(ParentT parentt1) {
      if (this.isEnabled() && this.field8.isInstance(parentt1)) {
         this.OCOCRCHOHOIICRHIRCCHCIHICHRCHH(parentt1);
      }
   }

   @Override
   public void method2(ParentT parentt1) {
      if (this.isEnabled() && this.field8.isInstance(parentt1)) {
         this.ORCCOORHRRCOHOHRRHIHRRIOCOHCIC(parentt1);
      }
   }

   @Override
   public void method3(ParentT parentt1) {
      if (this.isEnabled() && this.field8.isInstance(parentt1)) {
         this.HIIOIOORCIICIRROCCRIOHRHOOIRCR(parentt1);
      }
   }
}
