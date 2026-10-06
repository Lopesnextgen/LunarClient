package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface CosmeticMatcher {
   boolean method1(CosmeticIndexEntry var1);

   default Set<CosmeticIndexEntry> method2() {
      return ThreadModuleDump63.method4().method53().method60().values().stream().filter(this::method1).collect(Collectors.toSet());
   }

   static CosmeticMatcher method3(String text) {
      String[] var1 = text.split(":", 2);
      if (var1.length != 2) {
         throw new IllegalStateException("Invalid cosmetic matcher syntax: " + text);
      }

      return switch (var1[0]) {
         case "type" -> new TypeCosmeticMatcher(CosmeticCategoryType.from(var1[1]).orElseThrow(() -> new IllegalStateException(var1[1] + " is not a valid cosmetic type")));
         case "cosmetic" -> new IdCosmeticMatcher(var1[1]);
         case "regex" -> new RegexCosmeticMatcher(Pattern.compile(var1[1]));
         default -> throw new IllegalStateException("Unexpected cosmetic matcher type: " + var1[0]);
      };
   }
}
