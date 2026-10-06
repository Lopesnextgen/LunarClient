package com.moonsworth.lunar.genesis;

import java.lang.reflect.Type;

enum Types$JavaVersion$4 {
   ;
   Types$JavaVersion$4() {
   }

   Type newArrayType(Type type1) {
      return JAVA8.newArrayType(type1);
   }

   Type usedInGenericType(Type type1) {
      return JAVA8.usedInGenericType(type1);
   }

   String typeName(Type type1) {
      return JAVA8.typeName(type1);
   }

   boolean jdkTypeDuplicatesOwnerName() {
      return false;
   }
}
