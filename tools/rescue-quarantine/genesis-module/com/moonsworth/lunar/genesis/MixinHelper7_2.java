package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;
import com.google.common.collect.Sets;

abstract class MixinHelper7_2 {
   private final Set<Type> field1 = Sets.newHashSet();

   public final void method1(Type... var1) {
      for (Type var5 : var1) {
         if (var5 != null && this.field1.add(var5)) {
            boolean var6 = false;

            try {
               if (var5 instanceof TypeVariable) {
                  this.visitTypeVariable((TypeVariable<?>)var5);
               } else if (var5 instanceof WildcardType) {
                  this.visitWildcardType((WildcardType)var5);
               } else if (var5 instanceof ParameterizedType) {
                  this.visitParameterizedType((ParameterizedType)var5);
               } else if (var5 instanceof Class) {
                  this.visitClass((Class<?>)var5);
               } else {
                  if (!(var5 instanceof GenericArrayType)) {
                     throw new AssertionError("Unknown type: " + var5);
                  }

                  this.visitGenericArrayType((GenericArrayType)var5);
               }

               var6 = true;
            } finally {
               if (!var6) {
                  this.field1.remove(var5);
               }
            }
         }
      }
   }

   void visitClass(Class<?> var1) {
   }

   void visitGenericArrayType(GenericArrayType var1) {
   }

   void visitParameterizedType(ParameterizedType var1) {
   }

   void visitTypeVariable(TypeVariable<?> var1) {
   }

   void visitWildcardType(WildcardType var1) {
   }
}
