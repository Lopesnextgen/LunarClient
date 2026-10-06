package com.moonsworth.lunar.genesis;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.reflect.Reflection;
import com.google.common.base.Predicates;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

final class MixinHelper3_2 {
   private static final MixinHelper24_2<Type, String> field1 = new MixinHelper24_2<Type, String>() {
      public String apply(Type var1) {
         return MixinHelper3$Type10.CURRENT.typeName(var1);
      }
   };
   private static final MixinHelper16_2 field2 = MixinHelper16_2.method1(", ").method13("null");

   static Type newArrayType(Type var0) {
      if (var0 instanceof WildcardType) {
         WildcardType var1 = (WildcardType)var0;
         Type[] var2 = var1.getLowerBounds();
         Preconditions.checkArgument(var2.length <= 1, "Wildcard cannot have more than one lower bounds.");
         if (var2.length == 1) {
            return supertypeOf(newArrayType(var2[0]));
         }

         Type[] var3 = var1.getUpperBounds();
         Preconditions.checkArgument(var3.length == 1, "Wildcard should have only one upper bound.");
         return subtypeOf(newArrayType(var3[0]));
      } else {
         return MixinHelper3$Type10.CURRENT.newArrayType(var0);
      }
   }

   static ParameterizedType newParameterizedTypeWithOwner(@Nullable Type var0, Class<?> var1, Type... var2) {
      if (var0 == null) {
         return newParameterizedType(var1, var2);
      }

      Preconditions.checkNotNull(var2);
      Preconditions.checkArgument(var1.getEnclosingClass() != null, "Owner type for unenclosed %s", var1);
      return new MixinHelper3$Data45(var0, var1, var2);
   }

   static ParameterizedType newParameterizedType(Class<?> var0, Type... var1) {
      return new MixinHelper3$Data45(MixinHelper3$Type9.JVM_BEHAVIOR.getOwnerType(var0), var0, var1);
   }

   static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(D var0, String var1, Type... var2) {
      return newTypeVariableImpl((D)var0, var1, var2.length == 0 ? new Type[]{Object.class} : var2);
   }

   @Annotation4
   static WildcardType subtypeOf(Type var0) {
      return new MixinHelper3$Data44(new Type[0], new Type[]{var0});
   }

   @Annotation4
   static WildcardType supertypeOf(Type var0) {
      return new MixinHelper3$Data44(new Type[]{var0}, new Type[]{Object.class});
   }

   static String toString(Type var0) {
      return var0 instanceof Class ? ((Class)var0).getName() : var0.toString();
   }

   static @Nullable Type getComponentType(Type var0) {
      Preconditions.checkNotNull(var0);
      final AtomicReference var1 = new AtomicReference();
      (new MixinHelper7_2() {
         @Override
         void visitTypeVariable(TypeVariable<?> var1x) {
            var1.set(MixinHelper3_2.subtypeOfComponentType(var1x.getBounds()));
         }

         @Override
         void visitWildcardType(WildcardType var1x) {
            var1.set(MixinHelper3_2.subtypeOfComponentType(var1x.getUpperBounds()));
         }

         @Override
         void visitGenericArrayType(GenericArrayType var1x) {
            var1.set(var1x.getGenericComponentType());
         }

         @Override
         void visitClass(Class<?> var1x) {
            var1.set(var1x.getComponentType());
         }
      }).method1(new Type[]{var0});
      return (Type)var1.get();
   }

   private static @Nullable Type subtypeOfComponentType(Type[] var0) {
      for (Type var4 : var0) {
         Type var5 = getComponentType(var4);
         if (var5 != null) {
            if (var5 instanceof Class) {
               Class var6 = (Class)var5;
               if (var6.isPrimitive()) {
                  return var6;
               }
            }

            return subtypeOf(var5);
         }
      }

      return null;
   }

   private static <D extends GenericDeclaration> TypeVariable<D> newTypeVariableImpl(D var0, String var1, Type[] var2) {
      MixinHelper3$Data42 var3 = new MixinHelper3$Data42<>(var0, var1, var2);
      return Reflection.newProxy(TypeVariable.class, new MixinHelper3$Data43(var3));
   }

   private static Type[] toArray(Collection<Type> var0) {
      return var0.toArray(new Type[0]);
   }

   private static Iterable<Type> filterUpperBounds(Iterable<Type> var0) {
      return Iterables.method4(var0, Predicates.method5(Predicates.method12(Object.class)));
   }

   private static void disallowPrimitiveType(Type[] var0, String var1) {
      for (Type var5 : var0) {
         if (var5 instanceof Class) {
            Class var6 = (Class)var5;
            Preconditions.checkArgument(!var6.isPrimitive(), "Primitive type '%s' used as %s", var6, var1);
         }
      }
   }

   static Class<?> getArrayClass(Class<?> var0) {
      return Array.newInstance(var0, 0).getClass();
   }

   private MixinHelper3_2() {
   }
}
