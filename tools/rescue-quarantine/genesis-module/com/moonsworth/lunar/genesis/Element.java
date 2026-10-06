package com.moonsworth.lunar.genesis;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.reflect.TypeToken;
import com.google.common.base.Preconditions;

class Element extends AccessibleObject implements Member {
   private final AccessibleObject field1;
   private final Member field2;

   <M extends AccessibleObject & Member> Element(M m1) {
      Preconditions.checkNotNull(m1);
      this.field1 = m1;
      this.field2 = (Member)m1;
   }

   public TypeToken<?> method1() {
      return TypeToken.method1(this.getDeclaringClass());
   }

   @Override
   public final boolean isAnnotationPresent(Class<? extends Annotation> clazz1) {
      return this.field1.isAnnotationPresent(clazz1);
   }

   @Override
   public final <A extends Annotation> A getAnnotation(Class<A> clazz1) {
      return this.field1.getAnnotation(clazz1);
   }

   @Override
   public final Annotation[] getAnnotations() {
      return this.field1.getAnnotations();
   }

   @Override
   public final Annotation[] getDeclaredAnnotations() {
      return this.field1.getDeclaredAnnotations();
   }

   @Override
   public final void setAccessible(boolean flag1) {
      this.field1.setAccessible(flag1);
   }

   @Override
   public final boolean isAccessible() {
      return this.field1.isAccessible();
   }

   @Override
   public Class<?> getDeclaringClass() {
      return this.field2.getDeclaringClass();
   }

   @Override
   public final String getName() {
      return this.field2.getName();
   }

   @Override
   public final int getModifiers() {
      return this.field2.getModifiers();
   }

   @Override
   public final boolean isSynthetic() {
      return this.field2.isSynthetic();
   }

   public final boolean method2() {
      return Modifier.isPublic(this.getModifiers());
   }

   public final boolean method3() {
      return Modifier.isProtected(this.getModifiers());
   }

   public final boolean method4() {
      return !this.method5() && !this.method2() && !this.method3();
   }

   public final boolean method5() {
      return Modifier.isPrivate(this.getModifiers());
   }

   public final boolean method6() {
      return Modifier.isStatic(this.getModifiers());
   }

   public final boolean method7() {
      return Modifier.isFinal(this.getModifiers());
   }

   public final boolean method8() {
      return Modifier.isAbstract(this.getModifiers());
   }

   public final boolean method9() {
      return Modifier.isNative(this.getModifiers());
   }

   public final boolean method10() {
      return Modifier.isSynchronized(this.getModifiers());
   }

   final boolean method11() {
      return Modifier.isVolatile(this.getModifiers());
   }

   final boolean method12() {
      return Modifier.isTransient(this.getModifiers());
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Element)) {
         return false;
      }

      Element accessibleobjectimpl2 = (Element)obj1;
      return this.method1().equals(accessibleobjectimpl2.method1()) && this.field2.equals(accessibleobjectimpl2.field2);
   }

   @Override
   public int hashCode() {
      return this.field2.hashCode();
   }

   @Override
   public String toString() {
      return this.field2.toString();
   }
}
