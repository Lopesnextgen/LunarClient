package com.moonsworth.lunar.genesis;

final class FuturesGetChecked$GetCheckedTypeValidatorHolder$ClassValueValidator$1 extends ClassValue<Boolean> {
   FuturesGetChecked$GetCheckedTypeValidatorHolder$ClassValueValidator$1() {
   }

   protected Boolean computeValue(Class<?> clazz1) {
      MixinHelper_3.checkExceptionClassValidity(clazz1.asSubclass(Exception.class));
      return true;
   }
}
