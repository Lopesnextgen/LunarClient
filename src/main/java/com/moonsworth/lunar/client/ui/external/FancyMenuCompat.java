package com.moonsworth.lunar.client.ui.external;

import javax.annotation.Nullable;

public class FancyMenuCompat {
   public static final String field1 = "de.keksuccino.fancymenu.FancyMenu";
   @Nullable
   public static Class<?> field2;

   public FancyMenuCompat() {
   }

   public static boolean method1() {
      return field2 != null;
   }

   static {
      try {
         field2 = Class.forName("de.keksuccino.fancymenu.FancyMenu");
      } catch (Throwable exception1) {
      }
   }
}
