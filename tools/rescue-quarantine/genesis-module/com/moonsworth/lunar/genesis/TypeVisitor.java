package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;
import com.google.common.collect.Sets;

abstract class TypeVisitor {
   private final Set<Type> field1 = Sets.newHashSet();

   TypeVisitor() {
   }

   public final void method1(Type... items1) {
      for (Type type5 : items1) {
         if (type5 != null && this.field1.add(type5)) {
            boolean flag6 = false;

            try {
               if (type5 instanceof TypeVariable) {
                  this.visitTypeVariable((TypeVariable<?>)type5);
               } else if (type5 instanceof WildcardType) {
                  this.visitWildcardType((WildcardType)type5);
               } else if (type5 instanceof ParameterizedType) {
                  this.visitParameterizedType((ParameterizedType)type5);
               } else if (type5 instanceof Class) {
                  this.visitClass((Class<?>)type5);
               } else {
                  if (!(type5 instanceof GenericArrayType)) {
                     throw new AssertionError("Unknown type: " + type5);
                  }

                  this.visitGenericArrayType((GenericArrayType)type5);
               }

               flag6 = true;
            } finally {
               if (!flag6) {
                  this.field1.remove(type5);
               }
            }
         }
      }
   }

   void visitClass(Class<?> clazz1) {
   }

   void visitGenericArrayType(GenericArrayType genericarraytype1) {
   }

   void visitParameterizedType(ParameterizedType parameterizedtype1) {
   }

   void visitTypeVariable(TypeVariable<?> typevariable1) {
   }

   void visitWildcardType(WildcardType wildcardtype1) {
   }
}
