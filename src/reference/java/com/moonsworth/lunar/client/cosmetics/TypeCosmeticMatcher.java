package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;

public class TypeCosmeticMatcher implements CosmeticMatcher {
   private final CosmeticCategoryType field1;

   public TypeCosmeticMatcher(CosmeticCategoryType var1) {
      this.field1 = var1;
   }

   @Override
   public boolean method1(CosmeticIndexEntry var1) {
      return var1.method2().map(var1x -> var1x.equals(this.field1)).orElse(false);
   }

   public CosmeticCategoryType method3() {
      return this.field1;
   }
}
