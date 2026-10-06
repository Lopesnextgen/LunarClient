package com.moonsworth.lunar.genesis;

import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Annotation4
class MixinHelper$Data48 {
   static final String field1 = MixinHelper$Data48.class.getName() + "$ClassValueValidator";
   static final MixinHelper$Extension5 field2 = method1();

   static MixinHelper$Extension5 method1() {
      try {
         Class var0 = Class.forName(field1);
         return (MixinHelper$Extension5)var0.getEnumConstants()[0];
      } catch (Throwable var1) {
         return MixinHelper_3.method3();
      }
   }

   enum Type implements MixinHelper$Extension5 {
      INSTANCE;

      private static final Set<WeakReference<Class<? extends Exception>>> validClasses = new CopyOnWriteArraySet<>();

      @Override
      public void validateClass(Class<? extends Exception> var1) {
         for (WeakReference var3 : validClasses) {
            if (var1.equals(var3.get())) {
               return;
            }
         }

         MixinHelper_3.checkExceptionClassValidity(var1);
         if (validClasses.size() > 1000) {
            validClasses.clear();
         }

         validClasses.add(new WeakReference<>(var1));
      }
   }

   @Annotation_2
   enum Type2 implements MixinHelper$Extension5 {
      INSTANCE;

      private static final ClassValue<Boolean> isValidClass = new ClassValue<Boolean>() {
         protected Boolean computeValue(Class<?> var1) {
            MixinHelper_3.checkExceptionClassValidity(var1.asSubclass(Exception.class));
            return true;
         }
      };

      @Override
      public void validateClass(Class<? extends Exception> var1) {
         isValidClass.get(var1);
      }
   }
}
