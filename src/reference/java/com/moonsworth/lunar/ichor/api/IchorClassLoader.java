package com.moonsworth.lunar.ichor.api;

import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Optional;

public interface IchorClassLoader {
   IchorPipeline method1();

   default Optional<Class<?>> method2(String text1, byte[] items2) {
      return Optional.empty();
   }

   @KeepName
   Class<?> loadClass(String text1, boolean flag2);

   void method3(URLClassLoader urlclassloader1);

   URLClassLoader method4();
}
