package com.moonsworth.lunar.client.driver.core.holograms;

import com.eliotlash.molang.utils.MathUtils;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.webosr.input.Mouse.Button;
import lombok.Generated;

public abstract class Holograms2<T extends Holograms2<?>> {
   private static Holograms2<?> field1 = null;
   @SerializedName("id")
   private String id;
   @SerializedName("type")
   private HologramSubjectLegacy field2;
   @SerializedName("group")
   private HologramElementLegacy field3;
   @SerializedName("position")
   private HologramBoundsLegacy field4;
   @SerializedName("zoom")
   private float field5;
   @SerializedName("canDragModel")
   private boolean field6;
   @SerializedName("canRotateHorizontal")
   private boolean field7;
   @SerializedName("canRotateVertical")
   private boolean field8;
   @SerializedName("canManuallyZoom")
   private boolean field9;
   @SerializedName("ignoreDisplayZoomOffsets")
   private boolean field10;
   private transient int field11;
   private transient int field12;
   private transient double posX;
   private transient double posY;
   private transient double field13;
   private transient double field14;
   private transient float field15;
   private transient float field16;
   private transient float field17;
   private transient Button field18 = null;
   protected transient boolean initialized;
   private transient HologramRendererLegacy field19;
   protected transient int field20;
   private Fishing field21;

   public void init() {
      if (this.field21 == null) {
         this.field21 = new Fishing(this.getId(), true, this.method11());
      }

      this.initialized = true;
   }

   public float method1() {
      return MathUtils.wrapDegrees(this.field15);
   }

   public float method2() {
      return MathUtils.wrapDegrees(this.field16);
   }

   public double getX() {
      return -this.posX;
   }

   public double getY() {
      return this.posY;
   }

   public float getZoom() {
      return this.field5 + this.field17;
   }

   public float method3() {
      return this.field5;
   }

   public boolean method4(MarkerModel.Data5 var1) {
      return (this.field3 == null || this.field3.method1() == null || this.field3.method1().method1(var1)) && this.field4.method1(var1);
   }

   public void method5(int var1, int var2, MarkerModel.Data5 var3) {
      boolean var4 = var1 == 0 && var2 == 1;
      boolean var5 = var1 == 1 && var2 == 1;
      boolean var6 = var1 == 2 && var2 == 1;
      if (var6) {
         this.reset();
         if (field1 == this) {
            this.field18 = null;
            field1 = null;
         }
      } else {
         if (this.field18 == null) {
            if (this.field6 && var5) {
               this.field18 = Button.RIGHT;
               field1 = this;
               this.field13 = var3.HHHCHORHIHRCOHIOICICICHCRRICCI() + this.posX;
               this.field14 = var3.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() + this.posY;
            } else if ((this.field7 || this.field8) && var4) {
               this.field18 = Button.LEFT;
               field1 = this;
               this.field13 = var3.HHHCHORHIHRCOHIOICICICHCRRICCI() - this.field15;
               this.field14 = var3.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - this.field16;
            }
         }
      }
   }

   public void method6(MarkerModel.Data5 var1) {
      if (!HologramRendererLegacy.method12()) {
         if (this.field18 != null && field1 == this) {
            if (!Bridge.method20().method1(this.field18 == Button.LEFT ? 0 : 1)) {
               this.field18 = null;
               field1 = null;
            } else {
               if (this.field18 == Button.RIGHT) {
                  this.posX = this.field13 - var1.HHHCHORHIHRCOHIOICICICHCRRICCI();
                  this.posY = this.field14 - var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO();
               } else if (this.field18 == Button.LEFT) {
                  if (this.field7) {
                     this.field15 = -((float)(this.field13 - var1.HHHCHORHIHRCOHIOICICICHCRRICCI()));
                  }

                  if (this.field8) {
                     this.field16 = -((float)(this.field14 - var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO()));
                  }
               }
            }
         }
      }
   }

   public boolean method7() {
      return field1 == this && this.field18 != null;
   }

   private void reset() {
      this.posX = 0.0;
      this.posY = 0.0;
      this.field15 = 0.0F;
      this.field16 = 0.0F;
      this.field17 = 0.0F;
   }

   public void method8(T var1) {
      this.id = var1.getId();
      this.field2 = var1.method12();
      this.field3 = var1.method13();
      this.field4 = var1.method14();
      this.field5 = var1.getZoom();
      this.field6 = var1.method15();
      this.field7 = var1.method16();
      this.field8 = var1.method17();
   }

   public void remove() {
      if (this.field21 != null) {
         this.field21.delete();
      }

      com.moonsworth.lunar.client.driver.core.DriverViewLegacy.method21().method14().method15().remove(this.id, this);
   }

   public boolean method9(AbstractRenderContext var1) {
      return this.field21.method2(var1, (int)this.field4.getWidth(), (int)this.field4.getHeight());
   }

   public void method10(AbstractRenderContext var1) {
      this.field21.method3(var1);
   }

   public int method11() {
      return 30;
   }

   public void tick() {
      this.field20++;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public HologramSubjectLegacy method12() {
      return this.field2;
   }

   @Generated
   public HologramElementLegacy method13() {
      return this.field3;
   }

   @Generated
   public HologramBoundsLegacy method14() {
      return this.field4;
   }

   @Generated
   public boolean method15() {
      return this.field6;
   }

   @Generated
   public boolean method16() {
      return this.field7;
   }

   @Generated
   public boolean method17() {
      return this.field8;
   }

   @Generated
   public boolean method18() {
      return this.field9;
   }

   @Generated
   public boolean method19() {
      return this.field10;
   }

   @Generated
   public int method20() {
      return this.field11;
   }

   @Generated
   public int method21() {
      return this.field12;
   }

   @Generated
   public double getPosX() {
      return this.posX;
   }

   @Generated
   public double getPosY() {
      return this.posY;
   }

   @Generated
   public double method24() {
      return this.field13;
   }

   @Generated
   public double method25() {
      return this.field14;
   }

   @Generated
   public float method26() {
      return this.field17;
   }

   @Generated
   public Button method27() {
      return this.field18;
   }

   @Generated
   public boolean isInitialized() {
      return this.initialized;
   }

   @Generated
   public HologramRendererLegacy method28() {
      return this.field19;
   }

   @Generated
   public int method29() {
      return this.field20;
   }

   @Generated
   public Fishing method30() {
      return this.field21;
   }

   @Generated
   public void setId(String var1) {
      this.id = var1;
   }

   @Generated
   public void method31(HologramSubjectLegacy var1) {
      this.field2 = var1;
   }

   @Generated
   public void method32(HologramElementLegacy var1) {
      this.field3 = var1;
   }

   @Generated
   public void method33(HologramBoundsLegacy var1) {
      this.field4 = var1;
   }

   @Generated
   public void method34(float var1) {
      this.field5 = var1;
   }

   @Generated
   public void method35(boolean var1) {
      this.field6 = var1;
   }

   @Generated
   public void method36(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public void method37(boolean var1) {
      this.field8 = var1;
   }

   @Generated
   public void method38(boolean var1) {
      this.field9 = var1;
   }

   @Generated
   public void method39(boolean var1) {
      this.field10 = var1;
   }

   @Generated
   public void method40(int var1) {
      this.field11 = var1;
   }

   @Generated
   public void method41(int var1) {
      this.field12 = var1;
   }

   @Generated
   public void setPosX(double var1) {
      this.posX = var1;
   }

   @Generated
   public void setPosY(double var1) {
      this.posY = var1;
   }

   @Generated
   public void method44(double var1) {
      this.field13 = var1;
   }

   @Generated
   public void method45(double var1) {
      this.field14 = var1;
   }

   @Generated
   public void method46(float var1) {
      this.field15 = var1;
   }

   @Generated
   public void method47(float var1) {
      this.field16 = var1;
   }

   @Generated
   public void method48(float var1) {
      this.field17 = var1;
   }

   @Generated
   public void method49(Button var1) {
      this.field18 = var1;
   }

   @Generated
   public void setInitialized(boolean var1) {
      this.initialized = var1;
   }

   @Generated
   public void method51(int var1) {
      this.field20 = var1;
   }

   @Generated
   public void method52(Fishing var1) {
      this.field21 = var1;
   }

   @Generated
   public void method53(HologramRendererLegacy var1) {
      this.field19 = var1;
   }
}
