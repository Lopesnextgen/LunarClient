package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.AutoCloseableIterator;
import com.moonsworth.lunar.ichor.ClassTransformContext;
import com.moonsworth.lunar.ichor.IchorInjection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ClassNameRegexFilter implements IchorInjection {
   private static final Map<String, Pattern> field1 = new ConcurrentHashMap<>();
   private final Pattern field2;

   public ClassNameRegexFilter(String text) {
      this.field2 = AutoCloseableIterator.method5(text);
   }

   public boolean method1(ClassTransformContext autocloseableiterator2$data31) {
      return this.field2.matcher(autocloseableiterator2$data31.className()).matches();
   }
}
