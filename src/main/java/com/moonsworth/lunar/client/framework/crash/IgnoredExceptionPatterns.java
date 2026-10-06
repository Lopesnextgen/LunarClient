package com.moonsworth.lunar.client.framework.crash;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class IgnoredExceptionPatterns {
   private static final Map<String, Pattern> field1 = new HashMap<>();

   public IgnoredExceptionPatterns() {
   }

   public static void method1(String text, Pattern pattern1) {
      field1.put(text.toLowerCase(), pattern1);
   }

   public static Collection<Pattern> method2() {
      return field1.values();
   }
}
