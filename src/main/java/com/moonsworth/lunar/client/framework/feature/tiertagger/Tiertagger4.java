package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import org.jetbrains.annotations.NotNull;

public class Tiertagger4 {
   private final Optional<TiertaggerType> field1;
   private final OptionalInt field2;
   private final OptionalInt field3;
   @NotNull
   private final Map<Tiertagger_2, Tiertagger3_2> field4;

   public Tiertagger4(Optional<TiertaggerType> option, OptionalInt option2, OptionalInt option3, @NotNull Map<Tiertagger_2, Tiertagger3_2> var4) {
      this.field1 = option;
      this.field2 = option2;
      this.field3 = option3;
      this.field4 = var4;
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
   public Map<Tiertagger_2, Tiertagger3_2> method4() {
      return this.field4;
   }
}
