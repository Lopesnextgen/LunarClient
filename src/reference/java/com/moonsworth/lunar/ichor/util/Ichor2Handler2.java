package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.AutoCloseableIterator;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class Ichor2Handler2 implements IchorInjection {
   private static final Map<String, Pattern> field1 = new ConcurrentHashMap<>();
   private final Pattern field2;

   public Ichor2Handler2(String var1) {
      this.field2 = AutoCloseableIterator.method5(var1);
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      return this.field2.matcher(var1.className()).matches();
   }
}
