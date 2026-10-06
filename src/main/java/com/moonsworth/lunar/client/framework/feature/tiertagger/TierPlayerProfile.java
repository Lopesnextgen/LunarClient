package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import org.jetbrains.annotations.NotNull;

public class TierPlayerProfile {
   private final Optional<TiertaggerType> field1;
   private final OptionalInt field2;
   private final OptionalInt field3;
   @NotNull
   private final Map<TierGameMode, TierRanking> field4;

   public TierPlayerProfile(Optional<TiertaggerType> optional1, OptionalInt optionalint2, OptionalInt optionalint3, @NotNull Map<TierGameMode, TierRanking> map4) {
      this.field1 = optional1;
      this.field2 = optionalint2;
      this.field3 = optionalint3;
      this.field4 = map4;
   }

   public Optional<TiertaggerType> method1() {
      return this.field1;
   }

   public OptionalInt method2() {
      return this.field2;
   }

   public OptionalInt method3() {
      return this.field3;
   }

   @NotNull
   public Map<TierGameMode, TierRanking> method4() {
      return this.field4;
   }
}
