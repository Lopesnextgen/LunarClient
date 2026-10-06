package com.moonsworth.lunar.client.driver;

import com.google.common.annotations.Beta;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.driver.nameplate.mixin.Nameplate;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentFocusCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentDropCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentKeyCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentCursorCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentScrollCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentResizeCallbackLegacy;
import com.moonsworth.lunar.client.driver.nameplate.mixin.ComponentMouseButtonCallbackLegacy;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

@Beta
public abstract class DriverComponentLegacy<T extends DriverComponentLegacy<T>> {
   protected DriverViewportLegacy field1;
   protected DriverComponentLegacy<?> field2;
   protected float x;
   protected float y;
   protected int width;
   protected int height;
   private float field3;
   private final List<DriverComponentLegacy<?>> field4 = Collections.synchronizedList(new ArrayList<>());
   private List<DriverComponentLegacy<?>> field5;
   private Consumer<T> field6;
   private Consumer<T> field7;
   private ComponentResizeCallbackLegacy<T> field8;
   private ComponentResizeCallbackLegacy<T> field9;
   private ComponentFocusCallbackLegacy<T> field10;
   private ComponentKeyCallbackLegacy<T> field11;
   private Nameplate<T> field12;
   private ComponentCursorCallbackLegacy<T> field13;
   private ComponentMouseButtonCallbackLegacy<T> field14;
   private ComponentScrollCallbackLegacy<T> field15;
   private ComponentDropCallbackLegacy<T> field16;
   private int field17;
   private float field18;
   private float field19;
   protected com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy field20;
   protected com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy field21;
   private boolean field22;
   private boolean field23;

   public void method1(int var1, int var2) {
      this.width = var1;
      this.height = var2;
   }

   public void method2(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public void method3(MixinHelper_4 var1, MarkerModel.Data5 var2) {
      for (DriverComponentLegacy var4 : this.method23()) {
         var4.method3(var1, var2);
      }
   }

   public <K extends DriverComponentLegacy<K>> void method4(int var1, int var2) {
      this.getChildren().forEach(var2x -> var2x.method1(var1, var2));
      this.method1(var1, var2);
   }

   public void method5() {
      this.method7(this.field20, this.field21, this.field18, this.field19);
   }

   public void method6(float var1, float var2, float var3, float var4) {
      this.method7(
         new com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy(var1, 0.0F, 0.0F, 0.0F),
         new com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy(var2, 0.0F, 0.0F, 0.0F),
         var3,
         var4
      );
   }

   public void method7(
      com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy var1, com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy var2, float var3, float var4
   ) {
      this.field20 = var1;
      this.field21 = var2;
      this.field18 = var3;
      this.field19 = var4;
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
         for (DriverComponentLegacy var2 : this.field5) {
            var2.field1 = this.field1;
            var2.field2 = this;
            var2.method8();
         }
      }

      this.method11();
      this.field5 = null;
      return (T)this;
   }

   public void method9(DriverComponentLegacy<?> var1) {
      this.field4.add(var1);
   }

   public boolean method10() {
      boolean var1;
      if (this.field2 != null) {
         var1 = this.field2.getChildren().remove(this);
      } else {
         var1 = this.field1.method20().remove(this);
      }

      return var1;
   }

   protected void method11() {
   }

   public void method12() {
      if (this.field6 != null) {
         this.field6.accept((T)this);
      }

      for (DriverComponentLegacy var2 : this.method23()) {
         var2.method12();
      }
   }

   public void onClose() {
      if (this.field7 != null) {
         this.field7.accept((T)this);
      }

      for (DriverComponentLegacy var2 : this.getChildren()) {
         var2.onClose();
      }
   }

   public void method13(int var1, int var2) {
      if (this.field8 != null) {
         this.field8.accept((T)this, var1, var2);
      }

      for (DriverComponentLegacy var4 : this.method23()) {
         var4.method13(var1, var2);
      }
   }

   public void method14(int var1, int var2) {
      this.width = var1;
      this.height = var2;
      if (this.field9 != null) {
         this.field9.accept((T)this, var1, var2);
      }

      for (DriverComponentLegacy var4 : this.method23()) {
         var4.method14(var1, var2);
      }
   }

   public void method15(boolean var1) {
      if (this.field10 != null) {
         this.field10.accept((T)this, var1);
      }

      for (DriverComponentLegacy var3 : this.method23()) {
         var3.method15(var1);
      }
   }

   public void method16(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (this.field11 != null) {
         this.field11.accept((T)this, var1, var2, var3, var4, var5);
      }

      for (DriverComponentLegacy var7 : this.method23()) {
         var7.method16(var1, var2, var3, var4, var5);
      }
   }

   public void method17(int var1) {
      if (this.field12 != null) {
         this.field12.accept((T)this, var1);
      }

      for (DriverComponentLegacy var3 : this.method23()) {
         var3.method17(var1);
      }
   }

   public void method18(MarkerModel.Data5 var1) {
      if (this.field13 != null) {
         this.field13.accept((T)this, var1);
      }

      for (DriverComponentLegacy var3 : this.method23()) {
         var3.method18(var1);
      }
   }

   public void method19(int var1, int var2, int var3, MarkerModel.Data5 var4) {
      if (this.field14 != null) {
         this.field14.accept((T)this, var1, var2, var3, var4);
      }

      for (DriverComponentLegacy var6 : this.method23()) {
         var6.method19(var1, var2, var3, var4);
      }
   }

   public void method20(double var1, double var3) {
      if (this.field15 != null) {
         this.field15.accept((T)this, var1, var3);
      }

      for (DriverComponentLegacy var6 : this.method23()) {
         var6.method20(var1, var3);
      }
   }

   public void method21(List<Path> var1) {
      if (this.field16 != null) {
         this.field16.accept((T)this, var1);
      }

      for (DriverComponentLegacy var3 : this.method23()) {
         var3.method21(var1);
      }
   }

   public void method22(int var1, int var2, int var3, int var4) {
      this.width = var1;
      this.height = var2;

      for (DriverComponentLegacy var6 : this.method23()) {
         var6.method22(var1, var2, var3, var4);
      }
   }

   public List<DriverComponentLegacy<?>> method23() {
      return this.field4;
   }

   protected static <T extends DriverComponentLegacy<T>, C extends DriverComponentLegacy<T>, B extends ComponentStyleDataLegacy<T, C, B>, D extends ComponentStyleDataLegacy<T, ?, ?>> D method24(
      D var0, DriverViewportLegacy var1, DriverComponentLegacy<?> var2
   ) {
      return (D)var0.method4(var1).method5(var2);
   }

   @Generated
   protected DriverComponentLegacy(ComponentStyleDataLegacy<T, ?, ?> var1) {
      this.field1 = var1.field1;
      this.field2 = var1.field2;
      this.x = var1.x;
      this.y = var1.y;
      this.width = var1.width;
      this.height = var1.height;
      this.field3 = var1.field3;
      this.field5 = var1.field4;
      this.field6 = var1.field5;
      this.field7 = var1.field6;
      this.field8 = var1.field7;
      this.field9 = var1.field8;
      this.field10 = var1.field9;
      this.field11 = var1.field10;
      this.field12 = var1.field11;
      this.field13 = var1.field12;
      this.field14 = var1.field13;
      this.field15 = var1.field14;
      this.field16 = var1.field15;
      this.field17 = var1.field16;
      this.field18 = var1.field17;
      this.field19 = var1.field18;
      this.field20 = var1.field19;
      this.field21 = var1.field20;
      this.field22 = var1.field21;
      this.field23 = var1.field22;
   }

   @Generated
   public DriverViewportLegacy method25() {
      return this.field1;
   }

   @Generated
   public DriverComponentLegacy<?> method26() {
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
   public DriverComponentLegacy<T> method27(float var1) {
      this.field3 = var1;
      return this;
   }

   @Generated
   public List<DriverComponentLegacy<?>> getChildren() {
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
   public com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy method31() {
      return this.field20;
   }

   @Generated
   public com.moonsworth.lunar.client.driver.nameplate.PositionQuadLegacy method32() {
      return this.field21;
   }

   @Generated
   public boolean method33() {
      return this.field22;
   }
}
