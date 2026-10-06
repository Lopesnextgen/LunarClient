package com.moonsworth.lunar.client.util.text;

import org.intellij.lang.annotations.RegExp;

public class RegexPatterns {
   @RegExp
   public static final String field1 = "[A-Z0-9_]{2,}";
   @RegExp
   public static final String field2 = "^([a-z]+\\.)?[a-z0-9]+(([A-Z]{1,4}[a-z0-9]+)*[A-Z]{0,3}|([a-z0-9]+[A-Z]{1,3})*|[A-Z]{1,3})$";
   @RegExp
   public static final String field3 = "^([A-Z][a-z0-9]+)((\\d)|([A-Z0-9][a-z0-9]+))*([A-Z])?$";

   public RegexPatterns() {
   }
}
