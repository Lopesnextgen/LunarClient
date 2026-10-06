package com.moonsworth.lunar.genesis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

enum Types$JavaVersion$3 {
   ;
   Types$JavaVersion$3() {
   }

   Type newArrayType(Type type1) {
      return JAVA7.newArrayType(type1);
   }

   Type usedInGenericType(Type type1) {
      return JAVA7.usedInGenericType(type1);
   }

   String typeName(Type type1) {
      try {
         Method method2 = Type.class.getMethod("getTypeName");
         return (String)method2.invoke(type1);
      } catch (NoSuchMethodException nosuchmethodexception3) {
         throw new AssertionError("Type.getTypeName should be available in Java 8");
      } catch (InvocationTargetException invocationtargetexception4) {
         throw new RuntimeException(invocationtargetexception4);
      } catch (IllegalAccessException illegalaccessexception5) {
         throw new RuntimeException(illegalaccessexception5);
      }
   }
}
