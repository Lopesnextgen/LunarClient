package com.moonsworth.lunar.bridge;

import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public class Bridge2Handler2 implements Bridge2_13, Component {
   private final Bridge2_42 field1;

   @NotNull
   public @Unmodifiable List<Component> children() {
      return List.of();
   }

   @NotNull
   public Component children(@NotNull List<? extends ComponentLike> var1) {
      return this;
   }

   @NotNull
   public Style style() {
      return Style.empty();
   }

   @NotNull
   public Component style(@NotNull Style var1) {
      return this;
   }

   @Override
   public Bridge2_42 moonBridge$asBridgeComponent() {
      return this.field1;
   }

   @Generated
   public Bridge2Handler2(Bridge2_42 var1) {
      this.field1 = var1;
   }
}
