package com.moonsworth.lunar.genesis;

import java.lang.reflect.ParameterizedType;
import org.checkerframework.checker.nullness.qual.Nullable;

enum MixinHelper3$Type9 {
   OWNED_BY_ENCLOSING_CLASS {
      @Override
      @Nullable Class<?> getOwnerType(Class<?> var1) {
         return var1.getEnclosingClass();
      }
   },
   LOCAL_CLASS_HAS_NO_OWNER {
      @Override
      @Nullable Class<?> getOwnerType(Class<?> var1) {
         return var1.isLocalClass() ? null : var1.getEnclosingClass();
      }
   };

   static final MixinHelper3$Type9 JVM_BEHAVIOR = detectJvmBehavior();

   MixinHelper3$Type9() {
   }

   abstract @Nullable Class<?> getOwnerType(Class<?> var1);

   private static MixinHelper3$Type9 detectJvmBehavior() {
      Class var0 = (new Data<String>() {}).getClass();
      ParameterizedType var1 = (ParameterizedType)var0.getGenericSuperclass();

      for (MixinHelper3$Type9 var5 : values()) {
         class Data<T> {
         }

         if (var5.getOwnerType(Data.class) == var1.getOwnerType()) {
            return var5;
         }
      }

      throw new AssertionError();
   }
}
