package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;

public class IdCosmeticMatcher implements CosmeticMatcher {
   private final String field1;

   public IdCosmeticMatcher(String var1) {
      this.field1 = var1;
   }

   @Override
   public boolean method1(CosmeticIndexEntry var1) {
      return var1.getName().equals(this.field1);
   }

   public String method3() {
      return this.field1;
   }
}
