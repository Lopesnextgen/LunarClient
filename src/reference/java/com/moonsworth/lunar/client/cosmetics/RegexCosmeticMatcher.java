package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;
import java.util.regex.Pattern;

public class RegexCosmeticMatcher implements CosmeticMatcher {
   private final Pattern field1;

   public RegexCosmeticMatcher(Pattern var1) {
      this.field1 = var1;
   }

   @Override
   public boolean method1(CosmeticIndexEntry var1) {
      return this.field1.matcher(var1.getName()).matches();
   }

   public Pattern method3() {
      return this.field1;
   }
}
