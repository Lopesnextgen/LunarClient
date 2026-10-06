package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.ClassTransformContext;
import com.moonsworth.lunar.ichor.IchorInjection;

public class ClassPrefixFilter implements IchorInjection {
   private final String[] field1;

   public ClassPrefixFilter(String... items1) {
      this.field1 = items1;
   }

   public boolean method1(ClassTransformContext autocloseableiterator2$data31) {
      String text2 = autocloseableiterator2$data31.className();

      for (String text6 : this.field1) {
         if (text2.startsWith(text6)) {
            return true;
         }
      }

      return false;
   }
}
