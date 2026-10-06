package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.driver.component.ComponentCharCallback;
import com.moonsworth.lunar.client.driver.component.ComponentFocusCallback;
import com.moonsworth.lunar.client.driver.component.ComponentDropCallback;
import com.moonsworth.lunar.client.driver.component.ComponentKeyCallback;
import com.moonsworth.lunar.client.driver.component.ComponentCursorCallback;
import com.moonsworth.lunar.client.driver.component.ComponentScrollCallback;
import com.moonsworth.lunar.client.driver.component.ComponentResizeCallback;
import com.moonsworth.lunar.client.driver.component.ComponentMouseButtonCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;

public abstract class ComponentStyleData<T extends DriverComponent<T>, C extends DriverComponent<T>, B extends ComponentStyleData<T, C, B>> {
   @Generated
   private DriverViewport field1;
   @Generated
   private DriverComponent<?> field2;
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
   private List<DriverComponent<?>> field4 = new ArrayList<>();
   @Generated
   private Consumer<T> field5;
   @Generated
   private Consumer<T> field6;
   @Generated
   private ComponentResizeCallback<T> field7;
   @Generated
   private ComponentResizeCallback<T> field8;
   @Generated
   private ComponentFocusCallback<T> field9;
   @Generated
   private ComponentKeyCallback<T> field10;
   @Generated
   private ComponentCharCallback<T> field11;
   @Generated
   private ComponentCursorCallback<T> field12;
   @Generated
   private ComponentMouseButtonCallback<T> field13;
   @Generated
   private ComponentScrollCallback<T> field14;
   @Generated
   private ComponentDropCallback<T> field15;
   @Generated
   private int field16;
   @Generated
   private float field17;
   @Generated
   private float field18;
   @Generated
   private com.moonsworth.lunar.client.driver.component.PositionQuad field19;
   @Generated
   private com.moonsworth.lunar.client.driver.component.PositionQuad field20;
   @Generated
   private boolean field21;
   @Generated
   private boolean field22;

   public ComponentStyleData() {
   }

   public B method1(DriverComponent<?>... items1) {
      this.field4.addAll(Arrays.asList(items1));
      return this.method32();
   }

   public B method2(int number1, int number2) {
      this.width = number1;
      this.height = number2;
      return this.method32();
   }

   public B method3(int number1, int number2) {
      this.x = number1;
      this.y = number2;
      return this.method32();
   }

   B method4(DriverViewport driverViewport) {
      this.field1 = driverViewport;
      return this.method32();
   }

   B method5(DriverComponent<?> markers_21) {
      this.field2 = markers_21;
      return this.method32();
   }

   private B method6(List<DriverComponent<?>> list) {
      return this.method32();
   }

   private B method7(float value1) {
      return this.method32();
   }

   private B method8(float value1) {
      return this.method32();
   }

   private B method9(boolean flag1) {
      return this.method32();
   }

   private B method10(float value1) {
      return this.method32();
   }

   private B method11(float value1) {
      return this.method32();
   }

   private B method12(int number1) {
      return this.method32();
   }

   private B method13(boolean flag1) {
      return this.method32();
   }

   private B method14(boolean flag1) {
      return this.method32();
   }

   private B method15(com.moonsworth.lunar.client.driver.component.PositionQuad nameplate21) {
      return this.method32();
   }

   private B method16(com.moonsworth.lunar.client.driver.component.PositionQuad nameplate21) {
      return this.method32();
   }

   @Generated
   public B method17(int number1) {
      this.width = number1;
      return this.method32();
   }

   @Generated
   public B method18(int number1) {
      this.height = number1;
      return this.method32();
   }

   @Generated
   public B method19(float value1) {
      this.field3 = value1;
      return this.method32();
   }

   @Generated
   public B method20(Consumer<T> consumer1) {
      this.field5 = consumer1;
      return this.method32();
   }

   @Generated
   public B method21(Consumer<T> consumer1) {
      this.field6 = consumer1;
      return this.method32();
   }

   @Generated
   public B method22(ComponentResizeCallback<T> nameplate71) {
      this.field7 = nameplate71;
      return this.method32();
   }

   @Generated
   public B method23(ComponentResizeCallback<T> nameplate71) {
      this.field8 = nameplate71;
      return this.method32();
   }

   @Generated
   public B method24(ComponentFocusCallback<T> nameplate21) {
      this.field9 = nameplate21;
      return this.method32();
   }

   @Generated
   public B method25(ComponentKeyCallback<T> nameplate41) {
      this.field10 = nameplate41;
      return this.method32();
   }

   @Generated
   public B method26(ComponentCharCallback<T> nameplate1) {
      this.field11 = nameplate1;
      return this.method32();
   }

   @Generated
   public B method27(ComponentCursorCallback<T> nameplate51) {
      this.field12 = nameplate51;
      return this.method32();
   }

   @Generated
   public B method28(ComponentMouseButtonCallback<T> nameplate81) {
      this.field13 = nameplate81;
      return this.method32();
   }

   @Generated
   public B method29(ComponentScrollCallback<T> nameplate61) {
      this.field14 = nameplate61;
      return this.method32();
   }

   @Generated
   public B method30(ComponentDropCallback<T> nameplate31) {
      this.field15 = nameplate31;
      return this.method32();
   }

   @Generated
   public B method31(boolean flag1) {
      this.field21 = flag1;
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
