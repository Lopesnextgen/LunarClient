package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

class TypeToken$3 extends TypeVisitor {
   TypeToken$3(MixinHelper23_2 mixinhelper23_21) {
      this.field2 = mixinhelper23_21;
   }

   void visitTypeVariable(TypeVariable<?> typevariable1) {
      throw new IllegalArgumentException(MixinHelper23_2.method41(this.field2) + "contains a type variable and is not safe for the operation");
   }

   void visitWildcardType(WildcardType wildcardtype1) {
      this.method1(wildcardtype1.getLowerBounds());
      this.method1(wildcardtype1.getUpperBounds());
   }

   void visitParameterizedType(ParameterizedType parameterizedtype1) {
      this.method1(parameterizedtype1.getActualTypeArguments());
      this.method1(new Type[]{parameterizedtype1.getOwnerType()});
   }

   void visitGenericArrayType(GenericArrayType genericarraytype1) {
      this.method1(new Type[]{genericarraytype1.getGenericComponentType()});
   }
}
