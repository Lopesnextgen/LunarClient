package com.moonsworth.lunar.client.fishing;

import javax.annotation.Nullable;

public class Fishing_2 {
   public static final String field1 = "de.keksuccino.fancymenu.FancyMenu";
   @Nullable
   public static Class<?> field2;

   public static boolean method1() {
      return field2 != null;
   }

   static {
      try {
         field2 = Class.forName("de.keksuccino.fancymenu.FancyMenu");
      } catch (Throwable var1) {
      }
   }
}
