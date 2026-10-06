package com.moonsworth.lunar.client.util;

import org.intellij.lang.annotations.RegExp;

public class ThreadModuleDump12 {
   @RegExp
   public static final String CONSTANT_NAME_PATTERN = "[A-Z0-9_]{2,}";
   @RegExp
   public static final String IDENTIFIER_PATTERN = "^([a-z]+\\.)?[a-z0-9]+(([A-Z]{1,4}[a-z0-9]+)*[A-Z]{0,3}|([a-z0-9]+[A-Z]{1,3})*|[A-Z]{1,3})$";
   @RegExp
   public static final String CLASS_NAME_PATTERN = "^([A-Z][a-z0-9]+)((\\d)|([A-Z0-9][a-z0-9]+))*([A-Z])?$";
}
