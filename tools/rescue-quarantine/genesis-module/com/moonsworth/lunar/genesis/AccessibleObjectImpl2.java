package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

@Annotation2
public abstract class AccessibleObjectImpl2<T, R> extends AccessibleObjectImpl implements GenericDeclaration {
   <M extends AccessibleObject & Member> AccessibleObjectImpl2(M var1) {
      super((M)var1);
   }

   public static AccessibleObjectImpl2<?, Object> method1(Method var0) {
      return new AccessibleObjectImpl2.Data(var0);
   }

   public static <T> AccessibleObjectImpl2<T, T> method2(Constructor<T> var0) {
      return new AccessibleObjectImpl2.Data2<>(var0);
   }

   public abstract boolean isOverridable();

   public abstract boolean isVarArgs();

   @CanIgnoreReturnValue
   public final R method3(@Nullable T var1, Object... var2) {
      return (R)this.invokeInternal(var1, Preconditions.checkNotNull(var2));
   }

   public final TypeToken<? extends R> method13() {
      return (TypeToken<? extends R>)TypeToken.method2(this.getGenericReturnType());
   }

   public final ImmutableList<Parameter> method14() {
      Type[] var1 = this.getGenericParameterTypes();
      java.lang.annotation.Annotation[][] var2 = this.getParameterAnnotations();
      AnnotatedType[] var3 = this.getAnnotatedParameterTypes();
      ImmutableList.Data2 var4 = ImmutableList.method30();

      for (int var5 = 0; var5 < var1.length; var5++) {
         var4.method2(new Parameter(this, var5, TypeToken.method2(var1[var5]), var2[var5], var3[var5]));
      }

      return var4.method6();
   }

   public final ImmutableList<TypeToken<? extends Throwable>> method15() {
      ImmutableList.Data2 var1 = ImmutableList.method30();

      for (Type var5 : this.getGenericExceptionTypes()) {
         TypeToken var6 = TypeToken.method2(var5);
         var1.method2(var6);
      }

      return var1.method6();
   }

   public final <R1 extends R> AccessibleObjectImpl2<T, R1> method7(Class<R1> var1) {
      return this.method8(TypeToken.method1(var1));
   }

   public final <R1 extends R> AccessibleObjectImpl2<T, R1> method8(TypeToken<R1> var1) {
      if (!var1.method16(this.method13())) {
         throw new IllegalArgumentException("Invokable is known to return " + this.method13() + ", not " + var1);
      } else {
         return this;
      }
   }

   @Override
   public final Class<? super T> getDeclaringClass() {
      return (Class<? super T>)super.getDeclaringClass();
   }

   @Override
   public TypeToken<T> method1() {
      return TypeToken.method1((Class<T>)this.getDeclaringClass());
   }

   abstract Object invokeInternal(@Nullable Object var1, Object[] var2);

   abstract Type[] getGenericParameterTypes();

   abstract AnnotatedType[] getAnnotatedParameterTypes();

   abstract Type[] getGenericExceptionTypes();

   abstract java.lang.annotation.Annotation[][] getParameterAnnotations();

   abstract Type getGenericReturnType();

   public abstract AnnotatedType getAnnotatedReturnType();

   static class Data<T> extends AccessibleObjectImpl2<T, Object> {
      final Method field3;

      Data(Method var1) {
         super(var1);
         this.field3 = var1;
      }

      @Override
      final Object invokeInternal(@Nullable Object var1, Object[] var2) {
         return this.field3.invoke(var1, var2);
      }

      @Override
      Type getGenericReturnType() {
         return this.field3.getGenericReturnType();
      }

      @Override
      Type[] getGenericParameterTypes() {
         return this.field3.getGenericParameterTypes();
      }

      @Override
      AnnotatedType[] getAnnotatedParameterTypes() {
         return this.field3.getAnnotatedParameterTypes();
      }

      @Override
      public AnnotatedType getAnnotatedReturnType() {
         return this.field3.getAnnotatedReturnType();
      }

      @Override
      Type[] getGenericExceptionTypes() {
         return this.field3.getGenericExceptionTypes();
      }

      @Override
      final java.lang.annotation.Annotation[][] getParameterAnnotations() {
         return this.field3.getParameterAnnotations();
      }

      @Override
      public final TypeVariable<?>[] getTypeParameters() {
         return this.field3.getTypeParameters();
      }

      @Override
      public final boolean isOverridable() {
         return !this.CROOIRCRHRRIOHCIICRRIORHRCORIR()
            && !this.IHIOCIOHCRHHHCHIHHOCOHIORHICRI()
            && !this.OHOOIHRRORRCOCRHIIOOIHHROCOHHI()
            && !Modifier.isFinal(this.getDeclaringClass().getModifiers());
      }

      @Override
      public final boolean isVarArgs() {
         return this.field3.isVarArgs();
      }
   }

   static class Data2<T> extends AccessibleObjectImpl2<T, T> {
      final Constructor<?> field3;

      Data2(Constructor<?> var1) {
         super(var1);
         this.field3 = var1;
      }

      @Override
      final Object invokeInternal(@Nullable Object var1, Object[] var2) {
         try {
            return this.field3.newInstance(var2);
         } catch (InstantiationException var4) {
            throw new RuntimeException(this.field3 + " failed.", var4);
         }
      }

      @Override
      Type getGenericReturnType() {
         Class var1 = this.getDeclaringClass();
         TypeVariable[] var2 = var1.getTypeParameters();
         return var2.length > 0 ? MixinHelper3_2.newParameterizedType(var1, var2) : var1;
      }

      @Override
      Type[] getGenericParameterTypes() {
         Type[] var1 = this.field3.getGenericParameterTypes();
         if (var1.length > 0 && this.mayNeedHiddenThis()) {
            Class[] var2 = this.field3.getParameterTypes();
            if (var1.length == var2.length && var2[0] == this.getDeclaringClass().getEnclosingClass()) {
               return Arrays.copyOfRange(var1, 1, var1.length);
            }
         }

         return var1;
      }

      @Override
      AnnotatedType[] getAnnotatedParameterTypes() {
         return this.field3.getAnnotatedParameterTypes();
      }

      @Override
      public AnnotatedType getAnnotatedReturnType() {
         return this.field3.getAnnotatedReturnType();
      }

      @Override
      Type[] getGenericExceptionTypes() {
         return this.field3.getGenericExceptionTypes();
      }

      @Override
      final java.lang.annotation.Annotation[][] getParameterAnnotations() {
         return this.field3.getParameterAnnotations();
      }

      @Override
      public final TypeVariable<?>[] getTypeParameters() {
         TypeVariable[] var1 = this.getDeclaringClass().getTypeParameters();
         TypeVariable[] var2 = this.field3.getTypeParameters();
         TypeVariable[] var3 = new TypeVariable[var1.length + var2.length];
         System.arraycopy(var1, 0, var3, 0, var1.length);
         System.arraycopy(var2, 0, var3, var1.length, var2.length);
         return var3;
      }

      @Override
      public final boolean isOverridable() {
         return false;
      }

      @Override
      public final boolean isVarArgs() {
         return this.field3.isVarArgs();
      }

      private boolean mayNeedHiddenThis() {
         Class var1 = this.field3.getDeclaringClass();
         if (var1.getEnclosingConstructor() != null) {
            return true;
         }

         Method var2 = var1.getEnclosingMethod();
         return var2 != null ? !Modifier.isStatic(var2.getModifiers()) : var1.getEnclosingClass() != null && !Modifier.isStatic(var1.getModifiers());
      }
   }
}
