package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

enum Types$ClassOwnership$1 {
   ;
   Types$ClassOwnership$1() {
   }

   @Nullable Class<?> getOwnerType(Class<?> clazz1) {
      return clazz1.getEnclosingClass();
   }
}
