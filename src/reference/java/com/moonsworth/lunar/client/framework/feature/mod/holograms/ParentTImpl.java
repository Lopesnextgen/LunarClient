package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;
import java.util.function.Predicate;

class ParentTImpl<T extends MovementInputMarker> extends ParentTHandler<T, T> implements EntityChangeListener<T> {
   private final Predicate<T> field8;

   public ParentTImpl(IterableExtension<T> iterableextension1, Predicate<T> predicate2) {
      super(iterableextension1);
      this.field8 = predicate2;
   }

   @Override
   public void method1(T value1) {
      if (this.isEnabled() && this.field8.test((T)value1)) {
         this.OCOCRCHOHOIICRHIRCCHCIHICHRCHH(value1);
      }
   }

   @Override
   public void method2(T value1) {
      if (this.isEnabled()) {
         this.ORCCOORHRRCOHOHRRHIHRRIOCOHCIC(value1);
      }
   }

   @Override
   public void method3(T value1) {
      if (this.isEnabled()) {
         if (this.field8.test((T)value1)) {
            this.HIIOIOORCIICIRROCCRIOHRHOOIRCR(value1);
         } else {
            this.ORCCOORHRRCOHOHRRHIHRRIOCOHCIC(value1);
         }
      }
   }
}
