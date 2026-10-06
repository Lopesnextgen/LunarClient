package com.moonsworth.lunar.ichor.api;

import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.IchorService;
import java.util.Optional;
import java.util.ServiceLoader;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@IchorService
public class IchorAPI {
   private static boolean canCacheClasses = Boolean.parseBoolean(System.getProperty("ichor.prebakeClasses", "true"));
   private static IchorStage classCacheLevel = null;

   public IchorAPI() {
   }

   public static Optional<IchorPipeline> getPipeline(@Nullable Object obj0) {
      IchorPipeline ichor71 = null;
      if (obj0 instanceof ClassLoader classloader2) {
         ichor71 = findPipeline(classloader2);
      }

      if (ichor71 == null && obj0 != null) {
         if (obj0 instanceof Class clazz3) {
            ichor71 = findPipeline(clazz3.getClassLoader());
         } else {
            ichor71 = findPipeline(obj0.getClass().getClassLoader());
         }
      }

      if (ichor71 == null) {
         ichor71 = findPipeline(Thread.currentThread().getContextClassLoader());
      }

      if (ichor71 == null) {
         ichor71 = findPipeline(IchorAPI.class.getClassLoader());
      }

      return Optional.ofNullable(ichor71);
   }

   private static IchorPipeline findPipeline(ClassLoader classloader0) {
      while (classloader0 != null) {
         if (classloader0 instanceof IchorClassLoader ichorapi27) {
            return ichorapi27.method1();
         }

         Class[] items1 = classloader0.getClass().getInterfaces();

         for (Class clazz5 : items1) {
            if (clazz5.getName().equals(IchorClassLoader.class.getName())) {
               IchorClassLoader ichorapi26 = (IchorClassLoader)classloader0;
               return ichorapi26.method1();
            }
         }

         classloader0 = classloader0.getParent();
      }

      return null;
   }

   public static boolean canCacheClasses() {
      return canCacheClasses;
   }

   public static void doNotCacheClasses() {
      canCacheClasses = false;
   }

   public static Class<?> forName(String text0, Class<?> clazz1) {
      try {
         if (clazz1.getClassLoader() instanceof URLClassLoader urlclassloader5) {
            return urlclassloader5.loadClass(text0);
         } else {
            Optional optional2 = getPipeline(clazz1);
            if (optional2.isPresent()) {
               ClassLoader classloader6 = ((IchorPipeline)optional2.get()).method29();
               return Class.forName(text0.replace('/', '.'), true, classloader6);
            } else {
               return Class.forName(text0);
            }
         }
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   public static ServiceLoader<?> loadService(Class<?> clazz0, Class<?> clazz1) {
      if (clazz1.getClassLoader() instanceof URLClassLoader urlclassloader4) {
         return ServiceLoader.load(clazz0, urlclassloader4);
      } else {
         Optional optional2 = getPipeline(null);
         if (optional2.isPresent()) {
            ClassLoader classloader5 = ((IchorPipeline)optional2.get()).method29();
            return ServiceLoader.load(clazz0, classloader5);
         } else {
            return ServiceLoader.load(clazz0);
         }
      }
   }

   @Generated
   public static IchorStage getClassCacheLevel() {
      return classCacheLevel;
   }

   @Generated
   public static void setClassCacheLevel(IchorStage ichor40) {
      classCacheLevel = ichor40;
   }
}
