package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class TierRanking {
   @NotNull
   private final TierGameMode field1;
   @NotNull
   private final TierPlacement field2;
   private final Optional<TierPlacement> field3;
   private final Optional<Boolean> field4;

   public TierRanking(@NotNull TierGameMode tiertagger_21, @NotNull TierPlacement tiertagger52, Optional<TierPlacement> optional3, Optional<Boolean> optional4) {
      this.field1 = tiertagger_21;
      this.field2 = tiertagger52;
      this.field3 = optional3;
      this.field4 = optional4;
   }

   @NotNull
   public TierGameMode method1() {
      return this.field1;
   }

   @NotNull
   public TierPlacement method2() {
      return this.field2;
   }

   public Optional<TierPlacement> method3() {
      return this.field3;
   }

   public Optional<Boolean> method4() {
      return this.field4;
   }
}
