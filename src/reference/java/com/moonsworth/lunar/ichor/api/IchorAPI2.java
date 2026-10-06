package com.moonsworth.lunar.ichor.api;

import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.Optional;

public interface IchorAPI2 {
   IchorPipeline method1();

   default Optional<Class<?>> method2(String var1, byte[] var2) {
      return Optional.empty();
   }

   @Annotation2
   Class<?> loadClass(String var1, boolean var2);

   void method3(URLClassLoader var1);

   URLClassLoader method4();
}
