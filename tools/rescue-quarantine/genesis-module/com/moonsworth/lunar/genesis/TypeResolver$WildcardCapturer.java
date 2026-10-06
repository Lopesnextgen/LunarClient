package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class TypeResolver$WildcardCapturer {
   static final TypeResolver$WildcardCapturer field1 = new TypeResolver$WildcardCapturer();
   private final AtomicInteger field2;

   private TypeResolver$WildcardCapturer() {
      this(new AtomicInteger());
   }

   private TypeResolver$WildcardCapturer(AtomicInteger number1) {
      this.field2 = number1;
   }

   final Type method1(Type type1) {
      Preconditions.checkNotNull(type1);
      if (type1 instanceof Class) {
         return type1;
      }

      if (type1 instanceof TypeVariable) {
         return type1;
      }

      if (type1 instanceof GenericArrayType) {
         GenericArrayType genericarraytype8 = (GenericArrayType)type1;
         return MixinHelper3_2.newArrayType(this.method3().method1(genericarraytype8.getGenericComponentType()));
      }

      if (!(type1 instanceof ParameterizedType)) {
         if (type1 instanceof WildcardType) {
            WildcardType wildcardtype7 = (WildcardType)type1;
            Type[] items9 = wildcardtype7.getLowerBounds();
            return items9.length == 0 ? this.captureAsTypeVariable(wildcardtype7.getUpperBounds()) : type1;
         } else {
            throw new AssertionError("must have been one of the known types");
         }
      } else {
         ParameterizedType parameterizedtype2 = (ParameterizedType)type1;
         Class clazz3 = (Class)parameterizedtype2.getRawType();
         TypeVariable[] items4 = clazz3.getTypeParameters();
         Type[] items5 = parameterizedtype2.getActualTypeArguments();

         for (int index6 = 0; index6 < items5.length; index6++) {
            items5[index6] = this.method2(items4[index6]).method1(items5[index6]);
         }

         return MixinHelper3_2.newParameterizedTypeWithOwner(this.method3().captureNullable(parameterizedtype2.getOwnerType()), clazz3, items5);
      }
   }

   TypeVariable<?> captureAsTypeVariable(Type[] items1) {
      String text2 = "capture#" + this.field2.incrementAndGet() + "-of ? extends " + MixinHelper16_2.method2('&').method11(items1);
      return MixinHelper3_2.newArtificialTypeVariable(TypeResolver$WildcardCapturer.class, text2, items1);
   }

   private TypeResolver$WildcardCapturer method2(TypeVariable<?> typevariable1) {
      return new Data27$1(this, this.field2, typevariable1);
   }

   private TypeResolver$WildcardCapturer method3() {
      return new TypeResolver$WildcardCapturer(this.field2);
   }

   private Type captureNullable(@Nullable Type type1) {
      return type1 == null ? null : this.method1(type1);
   }
}
