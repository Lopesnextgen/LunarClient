package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;

public class Ichor2Handler implements IchorInjection {
   private final String[] field1;

   public Ichor2Handler(String... var1) {
      this.field1 = var1;
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      String var2 = var1.className();

      for (String var6 : this.field1) {
         if (var2.startsWith(var6)) {
            return true;
         }
      }

      return false;
   }
}
