package com.moonsworth.lunar.genesis;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.checkerframework.checker.nullness.qual.Nullable;

class Invokable$MethodInvokable<T> extends AccessibleObjectImpl2<T, Object> {
   final Method field3;

   Invokable$MethodInvokable(Method method1) {
      super(method1);
      this.field3 = method1;
   }

   final Object invokeInternal(@Nullable Object obj1, Object[] items2) {
      return this.field3.invoke(obj1, items2);
   }

   Type getGenericReturnType() {
      return this.field3.getGenericReturnType();
   }

   Type[] getGenericParameterTypes() {
      return this.field3.getGenericParameterTypes();
   }

   AnnotatedType[] getAnnotatedParameterTypes() {
      return this.field3.getAnnotatedParameterTypes();
   }

   public AnnotatedType getAnnotatedReturnType() {
      return this.field3.getAnnotatedReturnType();
   }

   Type[] getGenericExceptionTypes() {
      return this.field3.getGenericExceptionTypes();
   }

   final Annotation[][] getParameterAnnotations() {
      return this.field3.getParameterAnnotations();
   }

   public final TypeVariable<?>[] getTypeParameters() {
      return this.field3.getTypeParameters();
   }

   public final boolean isOverridable() {
      return !this.CROOIRCRHRRIOHCIICRRIORHRCORIR()
         && !this.IHIOCIOHCRHHHCHIHHOCOHIORHICRI()
         && !this.OHOOIHRRORRCOCRHIIOOIHHROCOHHI()
         && !Modifier.isFinal(this.getDeclaringClass().getModifiers());
   }

   public final boolean isVarArgs() {
      return this.field3.isVarArgs();
   }
}
