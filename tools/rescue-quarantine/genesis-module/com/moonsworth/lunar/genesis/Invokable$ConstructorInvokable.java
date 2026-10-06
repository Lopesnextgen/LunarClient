package com.moonsworth.lunar.genesis;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;

class Invokable$ConstructorInvokable<T> extends AccessibleObjectImpl2<T, T> {
   final Constructor<?> field3;

   Invokable$ConstructorInvokable(Constructor<?> constructor1) {
      super(constructor1);
      this.field3 = constructor1;
   }

   final Object invokeInternal(@Nullable Object obj1, Object[] items2) {
      try {
         return this.field3.newInstance(items2);
      } catch (InstantiationException instantiationexception4) {
         throw new RuntimeException(this.field3 + " failed.", instantiationexception4);
      }
   }

   Type getGenericReturnType() {
      Class clazz1 = this.getDeclaringClass();
      TypeVariable[] items2 = clazz1.getTypeParameters();
      return items2.length > 0 ? MixinHelper3_2.newParameterizedType(clazz1, items2) : clazz1;
   }

   Type[] getGenericParameterTypes() {
      Type[] items1 = this.field3.getGenericParameterTypes();
      if (items1.length > 0 && this.mayNeedHiddenThis()) {
         Class[] items2 = this.field3.getParameterTypes();
         if (items1.length == items2.length && items2[0] == this.getDeclaringClass().getEnclosingClass()) {
            return Arrays.copyOfRange(items1, 1, items1.length);
         }
      }

      return items1;
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
      TypeVariable[] items1 = this.getDeclaringClass().getTypeParameters();
      TypeVariable[] items2 = this.field3.getTypeParameters();
      TypeVariable[] items3 = new TypeVariable[items1.length + items2.length];
      System.arraycopy(items1, 0, items3, 0, items1.length);
      System.arraycopy(items2, 0, items3, items1.length, items2.length);
      return items3;
   }

   public final boolean isOverridable() {
      return false;
   }

   public final boolean isVarArgs() {
      return this.field3.isVarArgs();
   }

   private boolean mayNeedHiddenThis() {
      Class clazz1 = this.field3.getDeclaringClass();
      if (clazz1.getEnclosingConstructor() != null) {
         return true;
      }

      Method method2 = clazz1.getEnclosingMethod();
      return method2 != null ? !Modifier.isStatic(method2.getModifiers()) : clazz1.getEnclosingClass() != null && !Modifier.isStatic(clazz1.getModifiers());
   }
}
