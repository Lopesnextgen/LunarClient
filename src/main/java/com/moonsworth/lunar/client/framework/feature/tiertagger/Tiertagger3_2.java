package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Tiertagger3_2 {
   @NotNull
   private final Tiertagger_2 field1;
   @NotNull
   private final Tiertagger5 field2;
   private final Optional<Tiertagger5> field3;
   private final Optional<Boolean> field4;

   public Tiertagger3_2(@NotNull Tiertagger_2 var1, @NotNull Tiertagger5 var2, Optional<Tiertagger5> option, Optional<Boolean> option2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = option;
      this.field4 = option2;
   }

   @NotNull
   public Tiertagger_2 method1() {
      return this.field1;
   }

   @NotNull
   public Tiertagger5 method2() {
      return this.field2;
   }

   public Optional<Tiertagger5> method3() {
      return this.field3;
   }

   public Optional<Boolean> method4() {
      return this.field4;
   }
}
