package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Tiertagger_2 {
   @NotNull
   private final String field1;
   @NotNull
   private final String field2;
   private final Optional<String> field3;
   private final int field4;
   public static Tiertagger_2 field5 = new Tiertagger_2("none", "None", Optional.empty(), 0);

   public Tiertagger_2(@NotNull String var1, @NotNull String var2, Optional<String> option, int value) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = option;
      this.field4 = value;
   }

   @Override
   public String toString() {
      return this.field1;
   }

   @Override
   public boolean equals(Object var1) {
      return var1 == this || var1 instanceof Tiertagger_2 && Objects.equals(((Tiertagger_2)var1).field1, this.field1);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1);
   }

   @NotNull
   public String apiName() {
      return this.field1;
   }

   @NotNull
   public String niceName() {
      return this.field2;
   }

   public Optional<String> method1() {
      return this.field3;
   }

   public int method2() {
      return this.field4;
   }
}
