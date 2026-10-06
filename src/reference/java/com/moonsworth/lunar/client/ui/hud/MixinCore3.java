package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class MixinCore3 {
   private final @Nullable Bridge6_4 field1;
   private final @Nullable ItemStackBridge field2;
   private final @Nullable ResourceLocationBridge field3;
   private final @Nullable Consumer<MixinHelper_4> field4;
   @NotNull
   private final Component field5;

   public MixinCore3(@Nullable Bridge6_4 var1, @NotNull Component var2) {
      this.field1 = var1;
      this.field2 = var1 == null ? null : Bridge.method8().method38(var1);
      this.field3 = null;
      this.field4 = null;
      this.field5 = var2;
   }

   public MixinCore3(@Nullable ItemStackBridge var1, @NotNull Component var2) {
      this.field1 = var1 == null ? null : var1.bridge$getItem();
      this.field2 = var1;
      this.field3 = null;
      this.field4 = null;
      this.field5 = var2;
   }

   public MixinCore3(@Nullable Consumer<MixinHelper_4> var1, @NotNull Component var2) {
      this.field1 = null;
      this.field2 = null;
      this.field3 = null;
      this.field4 = var1;
      this.field5 = var2;
   }

   public MixinCore3(@NotNull Component var1) {
      this.field1 = null;
      this.field2 = null;
      this.field3 = null;
      this.field4 = null;
      this.field5 = var1;
   }

   public MixinCore3(@Nullable ResourceLocationBridge var1, @NotNull Component var2) {
      this.field1 = null;
      this.field2 = null;
      this.field3 = var1;
      this.field5 = var2;
      this.field4 = null;
   }

   public MixinCore3(@NotNull MixinCore3 var1, @NotNull Component var2) {
      this.field1 = var1.getItem();
      this.field2 = var1.method3();
      this.field3 = var1.getIcon();
      this.field4 = var1.method4();
      this.field5 = var2;
   }

   public boolean shouldRender() {
      return this.hasIcon() || this.field5 != Component.empty();
   }

   public boolean hasIcon() {
      return this.field1 != null || this.field3 != null;
   }

   public float method1(boolean var1, float var2) {
      float var3 = ThreadModuleDump63.method10().bridge$getStringWidth(this.field5);
      return var1 && this.hasIcon() ? var3 + var2 * 1.5F : var3;
   }

   public static MixinCore3 method2() {
      return new MixinCore3((Bridge6_4)null, Component.empty());
   }

   @Generated
   public @Nullable Bridge6_4 getItem() {
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
