package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

enum Types$ClassOwnership$2 {
   ;
   Types$ClassOwnership$2() {
   }

   @Nullable Class<?> getOwnerType(Class<?> clazz1) {
      return clazz1.isLocalClass() ? null : clazz1.getEnclosingClass();
   }
}
