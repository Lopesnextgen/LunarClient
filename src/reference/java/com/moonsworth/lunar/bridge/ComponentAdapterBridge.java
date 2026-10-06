package com.moonsworth.lunar.bridge;

import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public class ComponentAdapterBridge implements AdventureComponentBridge, Component {
   private final Bridge2_42 field1;

   @NotNull
   public @Unmodifiable List<Component> children() {
      return List.of();
   }

   @NotNull
   public Component children(@NotNull List<? extends ComponentLike> list1) {
      return this;
   }

   @NotNull
   public Style style() {
      return Style.empty();
   }

   @NotNull
   public Component style(@NotNull Style style1) {
      return this;
   }

   public Bridge2_42 moonBridge$asBridgeComponent() {
      return this.field1;
   }

   @Generated
   public ComponentAdapterBridge(Bridge2_42 bridge2_421) {
      this.field1 = bridge2_421;
   }
}
