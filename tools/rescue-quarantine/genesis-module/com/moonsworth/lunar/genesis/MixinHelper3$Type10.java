package com.moonsworth.lunar.genesis;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map.Entry;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;

enum MixinHelper3$Type10 {
   JAVA6 {
      GenericArrayType newArrayType(Type var1) {
         return new MixinHelper3$Data40(var1);
      }

      @Override
      Type usedInGenericType(Type var1) {
         Preconditions.checkNotNull(var1);
         if (var1 instanceof Class) {
            Class var2 = (Class)var1;
            if (var2.isArray()) {
               return new MixinHelper3$Data40(var2.getComponentType());
            }
         }

         return var1;
      }
   },
   JAVA7 {
      @Override
      Type newArrayType(Type var1) {
         return var1 instanceof Class ? MixinHelper3_2.getArrayClass((Class<?>)var1) : new MixinHelper3$Data40(var1);
      }

      @Override
      Type usedInGenericType(Type var1) {
         return Preconditions.checkNotNull(var1);
      }
   },
   JAVA8 {
      @Override
      Type newArrayType(Type var1) {
         return JAVA7.newArrayType(var1);
      }

      @Override
      Type usedInGenericType(Type var1) {
         return JAVA7.usedInGenericType(var1);
      }

      @Override
      String typeName(Type var1) {
         try {
            Method var2 = Type.class.getMethod("getTypeName");
            return (String)var2.invoke(var1);
         } catch (NoSuchMethodException var3) {
            throw new AssertionError("Type.getTypeName should be available in Java 8");
         } catch (InvocationTargetException var4) {
            throw new RuntimeException(var4);
         } catch (IllegalAccessException var5) {
            throw new RuntimeException(var5);
         }
      }
   },
   JAVA9 {
      @Override
      Type newArrayType(Type var1) {
         return JAVA8.newArrayType(var1);
      }

      @Override
      Type usedInGenericType(Type var1) {
         return JAVA8.usedInGenericType(var1);
      }

      @Override
      String typeName(Type var1) {
         return JAVA8.typeName(var1);
      }

      @Override
      boolean jdkTypeDuplicatesOwnerName() {
         return false;
      }
   };

   static final MixinHelper3$Type10 CURRENT;

   MixinHelper3$Type10() {
   }

   abstract Type newArrayType(Type var1);

   abstract Type usedInGenericType(Type var1);

   final ImmutableList<Type> usedInGenericType(Type[] var1) {
      ImmutableList.Data2 var2 = ImmutableList.method30();

      for (Type var6 : var1) {
         var2.method2(this.usedInGenericType(var6));
      }

      return var2.method6();
   }

   String typeName(Type var1) {
      return MixinHelper3_2.toString(var1);
   }

   boolean jdkTypeDuplicatesOwnerName() {
      return true;
   }

   static {
      if (AnnotatedElement.class.isAssignableFrom(TypeVariable.class)) {
         if ((new MixinHelper2<Entry<String, int[][]>>() {}).method1().toString().contains("java.util.Map.java.util.Map")) {
            CURRENT = JAVA8;
         } else {
            CURRENT = JAVA9;
         }
      } else if ((new MixinHelper2<int[]>() {}).method1() instanceof Class) {
         CURRENT = JAVA7;
      } else {
         CURRENT = JAVA6;
      }
   }
}
