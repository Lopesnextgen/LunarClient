package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.concurrent.atomic.AtomicReference;

final class Types$2 extends TypeVisitor {
   Types$2(AtomicReference atomicreference1) {
      this.field2 = atomicreference1;
   }

   @Override
   void visitTypeVariable(TypeVariable<?> typevariable1) {
      this.field2.set(MixinHelper3_2.access$100(typevariable1.getBounds()));
   }

   @Override
   void visitWildcardType(WildcardType wildcardtype1) {
      this.field2.set(MixinHelper3_2.access$100(wildcardtype1.getUpperBounds()));
   }

   @Override
   void visitGenericArrayType(GenericArrayType genericarraytype1) {
      this.field2.set(genericarraytype1.getGenericComponentType());
   }

   @Override
   void visitClass(Class<?> clazz1) {
      this.field2.set(clazz1.getComponentType());
   }
}
