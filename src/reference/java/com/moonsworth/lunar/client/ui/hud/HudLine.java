package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class HudLine {
   private final @Nullable ItemBridge field1;
   private final @Nullable ItemStackBridge field2;
   private final @Nullable ResourceLocationBridge field3;
   private final @Nullable Consumer<MixinHelper_4> field4;
   @NotNull
   private final Component field5;

   public HudLine(@Nullable ItemBridge bridge6_41, @NotNull Component component2) {
      this.field1 = bridge6_41;
      this.field2 = bridge6_41 == null ? null : Bridge.method8().method38(bridge6_41);
      this.field3 = null;
      this.field4 = null;
      this.field5 = component2;
   }

   public HudLine(@Nullable ItemStackBridge bridgeextension_41, @NotNull Component component2) {
      this.field1 = bridgeextension_41 == null ? null : bridgeextension_41.bridge$getItem();
      this.field2 = bridgeextension_41;
      this.field3 = null;
      this.field4 = null;
      this.field5 = component2;
   }

   public HudLine(@Nullable Consumer<MixinHelper_4> consumer1, @NotNull Component component2) {
      this.field1 = null;
      this.field2 = null;
      this.field3 = null;
      this.field4 = consumer1;
      this.field5 = component2;
   }

   public HudLine(@NotNull Component component1) {
      this.field1 = null;
      this.field2 = null;
      this.field3 = null;
      this.field4 = null;
      this.field5 = component1;
   }

   public HudLine(@Nullable ResourceLocationBridge horsestats141, @NotNull Component component2) {
      this.field1 = null;
      this.field2 = null;
      this.field3 = horsestats141;
      this.field5 = component2;
      this.field4 = null;
   }

   public HudLine(@NotNull HudLine mixincore31, @NotNull Component component2) {
      this.field1 = mixincore31.getItem();
      this.field2 = mixincore31.method3();
      this.field3 = mixincore31.getIcon();
      this.field4 = mixincore31.method4();
      this.field5 = component2;
   }

   public boolean shouldRender() {
      return this.hasIcon() || this.field5 != Component.empty();
   }

   public boolean hasIcon() {
      return this.field1 != null || this.field3 != null;
   }

   public float method1(boolean flag1, float value2) {
      float value3 = Ref.method10().bridge$getStringWidth(this.field5);
      return flag1 && this.hasIcon() ? value3 + value2 * 1.5F : value3;
   }

   public static HudLine method2() {
      return new HudLine((ItemBridge)null, Component.empty());
   }

   @Generated
   public @Nullable ItemBridge getItem() {
      return this.field1;
   }

   @Generated
   public @Nullable ItemStackBridge method3() {
      return this.field2;
   }

   @Generated
   public @Nullable ResourceLocationBridge getIcon() {
      return this.field3;
   }

   @Generated
   public @Nullable Consumer<MixinHelper_4> method4() {
      return this.field4;
   }

   @NotNull
   @Generated
   public Component getComponent() {
      return this.field5;
   }
}
