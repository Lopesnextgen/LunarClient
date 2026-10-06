package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class TierGameMode {
   @NotNull
   private final String field1;
   @NotNull
   private final String field2;
   private final Optional<String> field3;
   private final int field4;
   public static TierGameMode field5 = new TierGameMode("none", "None", Optional.empty(), 0);

   public TierGameMode(@NotNull String text1, @NotNull String text2, Optional<String> optional3, int value) {
      this.field1 = text1;
      this.field2 = text2;
      this.field3 = optional3;
      this.field4 = value;
   }

   @Override
   public String toString() {
      return this.field1;
   }

   @Override
   public boolean equals(Object object) {
      return object == this || object instanceof TierGameMode && Objects.equals(((TierGameMode)object).field1, this.field1);
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
