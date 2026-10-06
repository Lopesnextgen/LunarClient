package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.driver.nameplate.mixin.Nameplate;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentFocusCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentDropCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentKeyCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentCursorCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentScrollCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentResizeCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentMouseButtonCallbackLegacy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;

public abstract class ComponentStyleDataLegacy<T extends DriverComponentLegacy<T>, C extends DriverComponentLegacy<T>, B extends ComponentStyleDataLegacy<T, C, B>> {
   @Generated
   private DriverViewportLegacy field1;
   @Generated
   private DriverComponentLegacy<?> field2;
   @Generated
   private float x;
   @Generated
   private float y;
   @Generated
   private int width;
   @Generated
   private int height;
   @Generated
   private float field3;
   @Generated
   private List<DriverComponentLegacy<?>> field4 = new ArrayList<>();
   @Generated
   private Consumer<T> field5;
   @Generated
   private Consumer<T> field6;
   @Generated
   private ComponentResizeCallbackLegacy<T> field7;
   @Generated
   private ComponentResizeCallbackLegacy<T> field8;
   @Generated
   private ComponentFocusCallbackLegacy<T> field9;
   @Generated
   private ComponentKeyCallbackLegacy<T> field10;
   @Generated
   private Nameplate<T> field11;
   @Generated
   private ComponentCursorCallbackLegacy<T> field12;
   @Generated
   private ComponentMouseButtonCallbackLegacy<T> field13;
   @Generated
   private ComponentScrollCallbackLegacy<T> field14;
   @Generated
   private ComponentDropCallbackLegacy<T> field15;
   @Generated
   private int field16;
   @Generated
   private float field17;
   @Generated
   private float field18;
   @Generated
   private com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy field19;
   @Generated
   private com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy field20;
   @Generated
   private boolean field21;
   @Generated
   private boolean field22;

   public B method1(DriverComponentLegacy<?>... var1) {
      this.field4.addAll(Arrays.asList(var1));
      return this.method32();
   }

   public B method2(int var1, int var2) {
      this.width = var1;
      this.height = var2;
      return this.method32();
   }

   public B method3(int var1, int var2) {
      this.x = var1;
      this.y = var2;
      return this.method32();
   }

   B method4(DriverViewportLegacy var1) {
      this.field1 = var1;
      return this.method32();
   }

   B method5(DriverComponentLegacy<?> var1) {
      this.field2 = var1;
      return this.method32();
   }

   private B method6(List<DriverComponentLegacy<?>> var1) {
      return this.method32();
   }

   private B method7(float var1) {
      return this.method32();
   }

   private B method8(float var1) {
      return this.method32();
   }

   private B method9(boolean var1) {
      return this.method32();
   }

   private B method10(float var1) {
      return this.method32();
   }

   private B method11(float var1) {
      return this.method32();
   }

   private B method12(int var1) {
      return this.method32();
   }

   private B method13(boolean var1) {
      return this.method32();
   }

   private B method14(boolean var1) {
      return this.method32();
   }

   private B method15(com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy var1) {
      return this.method32();
   }

   private B method16(com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy var1) {
      return this.method32();
   }

   @Generated
   public B method17(int var1) {
      this.width = var1;
      return this.method32();
   }

   @Generated
   public B method18(int var1) {
      this.height = var1;
      return this.method32();
   }

   @Generated
   public B method19(float var1) {
      this.field3 = var1;
      return this.method32();
   }

   @Generated
   public B method20(Consumer<T> var1) {
      this.field5 = var1;
      return this.method32();
   }

   @Generated
   public B method21(Consumer<T> var1) {
      this.field6 = var1;
      return this.method32();
   }

   @Generated
   public B method22(ComponentResizeCallbackLegacy<T> var1) {
      this.field7 = var1;
      return this.method32();
   }

   @Generated
   public B method23(ComponentResizeCallbackLegacy<T> var1) {
      this.field8 = var1;
      return this.method32();
   }

   @Generated
   public B method24(ComponentFocusCallbackLegacy<T> var1) {
      this.field9 = var1;
      return this.method32();
   }

   @Generated
   public B method25(ComponentKeyCallbackLegacy<T> var1) {
      this.field10 = var1;
      return this.method32();
   }

   @Generated
   public B method26(Nameplate<T> var1) {
      this.field11 = var1;
      return this.method32();
   }

   @Generated
   public B method27(ComponentCursorCallbackLegacy<T> var1) {
      this.field12 = var1;
      return this.method32();
   }

   @Generated
   public B method28(ComponentMouseButtonCallbackLegacy<T> var1) {
      this.field13 = var1;
      return this.method32();
   }

   @Generated
   public B method29(ComponentScrollCallbackLegacy<T> var1) {
      this.field14 = var1;
      return this.method32();
   }

   @Generated
   public B method30(ComponentDropCallbackLegacy<T> var1) {
      this.field15 = var1;
      return this.method32();
   }

   @Generated
   public B method31(boolean var1) {
      this.field21 = var1;
      return this.method32();
   }

   @Generated
   protected abstract B method32();

   @Generated
   public abstract C method33();

   @Generated
   @Override
   public String toString() {
      return "LComponent.LComponentBuilder(screen="
         + this.field1
         + ", parent="
         + this.field2
         + ", x="
         + this.x
         + ", y="
         + this.y
         + ", width="
         + this.width
         + ", height="
         + this.height
         + ", fit="
         + this.field3
         + ", future="
         + this.field4
         + ", onTick="
         + this.field5
         + ", onClose="
         + this.field6
         + ", onFramebufferSize="
         + this.field7
         + ", onWindowSize="
         + this.field8
         + ", onWindowFocus="
         + this.field9
         + ", onKey="
         + this.field10
         + ", onChar="
         + this.field11
         + ", onCursorPos="
         + this.field12
         + ", onMouseButton="
         + this.field13
         + ", onScroll="
         + this.field14
         + ", onDrop="
         + this.field15
         + ", activeStyleId="
         + this.field16
         + ", maxWidth="
         + this.field17
         + ", maxHeight="
         + this.field18
         + ", xQuad="
         + this.field19
         + ", yQuad="
         + this.field20
         + ", growing="
         + this.field21
         + ", childrenPainterResized="
         + this.field22
         + ")";
   }
}
