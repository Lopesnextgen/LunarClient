package com.moonsworth.lunar.client.driver;

import com.google.common.annotations.Beta;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.driver.component.ComponentCharCallback;
import com.moonsworth.lunar.client.driver.component.ComponentFocusCallback;
import com.moonsworth.lunar.client.driver.component.ComponentDropCallback;
import com.moonsworth.lunar.client.driver.component.ComponentKeyCallback;
import com.moonsworth.lunar.client.driver.component.ComponentCursorCallback;
import com.moonsworth.lunar.client.driver.component.ComponentScrollCallback;
import com.moonsworth.lunar.client.driver.component.ComponentResizeCallback;
import com.moonsworth.lunar.client.driver.component.ComponentMouseButtonCallback;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

@Beta
public abstract class DriverComponent<T extends DriverComponent<T>> {
   protected DriverViewport field1;
   protected DriverComponent<?> field2;
   protected float x;
   protected float y;
   protected int width;
   protected int height;
   private float field3;
   private final List<DriverComponent<?>> field4 = Collections.synchronizedList(new ArrayList<>());
   private List<DriverComponent<?>> field5;
   private Consumer<T> field6;
   private Consumer<T> field7;
   private ComponentResizeCallback<T> field8;
   private ComponentResizeCallback<T> field9;
   private ComponentFocusCallback<T> field10;
   private ComponentKeyCallback<T> field11;
   private ComponentCharCallback<T> field12;
   private ComponentCursorCallback<T> field13;
   private ComponentMouseButtonCallback<T> field14;
   private ComponentScrollCallback<T> field15;
   private ComponentDropCallback<T> field16;
   private int field17;
   private float field18;
   private float field19;
   protected com.moonsworth.lunar.client.driver.component.PositionQuad field20;
   protected com.moonsworth.lunar.client.driver.component.PositionQuad field21;
   private boolean field22;
   private boolean field23;

   public void method1(int number1, int number2) {
      this.width = number1;
      this.height = number2;
   }

   public void method2(int number1, int number2) {
      this.x = number1;
      this.y = number2;
   }

   public void method3(MixinHelper_4 mixinhelper_41, MarkerModel.Data5 data52) {
      for (DriverComponent markers_24 : this.method23()) {
         markers_24.method3(mixinhelper_41, data52);
      }
   }

   public <K extends DriverComponent<K>> void method4(int number1, int number2) {
      this.getChildren().forEach(arg2x -> arg2x.method1(number1, number2));
      this.method1(number1, number2);
   }

   public void method5() {
      this.method7(this.field20, this.field21, this.field18, this.field19);
   }

   public void method6(float value1, float value2, float value3, float value4) {
      this.method7(
         new com.moonsworth.lunar.client.driver.component.PositionQuad(value1, 0.0F, 0.0F, 0.0F),
         new com.moonsworth.lunar.client.driver.component.PositionQuad(value2, 0.0F, 0.0F, 0.0F),
         value3,
         value4
      );
   }

   public void method7(
      com.moonsworth.lunar.client.driver.component.PositionQuad nameplate21, com.moonsworth.lunar.client.driver.component.PositionQuad nameplate22, float value3, float value4
   ) {
      this.field20 = nameplate21;
      this.field21 = nameplate22;
      this.field18 = value3;
      this.field19 = value4;
   }

   public T method8() {
      assert this.field1 != null : "Cannot add component, screen is null";
      if (this.field2 != null) {
         this.field2.method9(this);
      } else {
         this.field1.method20().add(this);
      }

      this.method4(this.width, this.height);
      if (this.field5 != null) {
         for (DriverComponent markers_22 : this.field5) {
            markers_22.field1 = this.field1;
            markers_22.field2 = this;
            markers_22.method8();
         }
      }

      this.method11();
      this.field5 = null;
      return (T)this;
   }

   public void method9(DriverComponent<?> markers_21) {
      this.field4.add(markers_21);
   }

   public boolean method10() {
      boolean flag1;
      if (this.field2 != null) {
         flag1 = this.field2.getChildren().remove(this);
      } else {
         flag1 = this.field1.method20().remove(this);
      }

      return flag1;
   }

   protected void method11() {
   }

   public void method12() {
      if (this.field6 != null) {
         this.field6.accept((T)this);
      }

      for (DriverComponent markers_22 : this.method23()) {
         markers_22.method12();
      }
   }

   public void onClose() {
      if (this.field7 != null) {
         this.field7.accept((T)this);
      }

      for (DriverComponent markers_22 : this.getChildren()) {
         markers_22.onClose();
      }
   }

   public void method13(int number1, int number2) {
      if (this.field8 != null) {
         this.field8.accept((T)this, number1, number2);
      }

      for (DriverComponent markers_24 : this.method23()) {
         markers_24.method13(number1, number2);
      }
   }

   public void method14(int number1, int number2) {
      this.width = number1;
      this.height = number2;
      if (this.field9 != null) {
         this.field9.accept((T)this, number1, number2);
      }

      for (DriverComponent markers_24 : this.method23()) {
         markers_24.method14(number1, number2);
      }
   }

   public void method15(boolean flag1) {
      if (this.field10 != null) {
         this.field10.accept((T)this, flag1);
      }

      for (DriverComponent markers_23 : this.method23()) {
         markers_23.method15(flag1);
      }
   }

   public void method16(KeyCode bridgetype_81, int number2, int number3, int number4, int number5) {
      if (this.field11 != null) {
         this.field11.accept((T)this, bridgetype_81, number2, number3, number4, number5);
      }

      for (DriverComponent markers_27 : this.method23()) {
         markers_27.method16(bridgetype_81, number2, number3, number4, number5);
      }
   }

   public void method17(int number1) {
      if (this.field12 != null) {
         this.field12.accept((T)this, number1);
      }

      for (DriverComponent markers_23 : this.method23()) {
         markers_23.method17(number1);
      }
   }

   public void method18(MarkerModel.Data5 data51) {
      if (this.field13 != null) {
         this.field13.accept((T)this, data51);
      }

      for (DriverComponent markers_23 : this.method23()) {
         markers_23.method18(data51);
      }
   }

   public void method19(int number1, int number2, int number3, MarkerModel.Data5 data54) {
      if (this.field14 != null) {
         this.field14.accept((T)this, number1, number2, number3, data54);
      }

      for (DriverComponent markers_26 : this.method23()) {
         markers_26.method19(number1, number2, number3, data54);
      }
   }

   public void method20(double value1, double value3) {
      if (this.field15 != null) {
         this.field15.accept((T)this, value1, value3);
      }

      for (DriverComponent markers_26 : this.method23()) {
         markers_26.method20(value1, value3);
      }
   }

   public void method21(List<Path> list1) {
      if (this.field16 != null) {
         this.field16.accept((T)this, list1);
      }

      for (DriverComponent markers_23 : this.method23()) {
         markers_23.method21(list1);
      }
   }

   public void method22(int number1, int number2, int number3, int number4) {
      this.width = number1;
      this.height = number2;

      for (DriverComponent markers_26 : this.method23()) {
         markers_26.method22(number1, number2, number3, number4);
      }
   }

   public List<DriverComponent<?>> method23() {
      return this.field4;
   }

   protected static <T extends DriverComponent<T>, C extends DriverComponent<T>, B extends ComponentStyleData<T, C, B>, D extends ComponentStyleData<T, ?, ?>> D method24(
      D value0, DriverViewport highlight3iterator1, DriverComponent<?> markers_22
   ) {
      return (D)value0.method4(highlight3iterator1).method5(markers_22);
   }

   @Generated
   protected DriverComponent(ComponentStyleData<T, ?, ?> markers$data1) {
      this.field1 = markers$data1.field1;
      this.field2 = markers$data1.field2;
      this.x = markers$data1.x;
      this.y = markers$data1.y;
      this.width = markers$data1.width;
      this.height = markers$data1.height;
      this.field3 = markers$data1.field3;
      this.field5 = markers$data1.field4;
      this.field6 = markers$data1.field5;
      this.field7 = markers$data1.field6;
      this.field8 = markers$data1.field7;
      this.field9 = markers$data1.field8;
      this.field10 = markers$data1.field9;
      this.field11 = markers$data1.field10;
      this.field12 = markers$data1.field11;
      this.field13 = markers$data1.field12;
      this.field14 = markers$data1.field13;
      this.field15 = markers$data1.field14;
      this.field16 = markers$data1.field15;
      this.field17 = markers$data1.field16;
      this.field18 = markers$data1.field17;
      this.field19 = markers$data1.field18;
      this.field20 = markers$data1.field19;
      this.field21 = markers$data1.field20;
      this.field22 = markers$data1.field21;
      this.field23 = markers$data1.field22;
   }

   @Generated
   public DriverViewport method25() {
      return this.field1;
   }

   @Generated
   public DriverComponent<?> method26() {
      return this.field2;
   }

   @Generated
   public float getX() {
      return this.x;
   }

   @Generated
   public float getY() {
      return this.y;
   }

   @Generated
   public int getWidth() {
      return this.width;
   }

   @Generated
   public int getHeight() {
      return this.height;
   }

   @Generated
   public DriverComponent<T> method27(float value1) {
      this.field3 = value1;
      return this;
   }

   @Generated
   public List<DriverComponent<?>> getChildren() {
      return this.field4;
   }

   @Generated
   public int method28() {
      return this.field17;
   }

   @Generated
   public float method29() {
      return this.field18;
   }

   @Generated
   public float method30() {
      return this.field19;
   }

   @Generated
   public com.moonsworth.lunar.client.driver.component.PositionQuad method31() {
      return this.field20;
   }

   @Generated
   public com.moonsworth.lunar.client.driver.component.PositionQuad method32() {
      return this.field21;
   }

   @Generated
   public boolean method33() {
      return this.field22;
   }
}
