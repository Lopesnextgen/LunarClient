package com.moonsworth.lunar.client.driver.holograms;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class CosmeticHologramLegacy extends com.moonsworth.lunar.client.driver.core.holograms.Holograms2<CosmeticHologramLegacy> {
   @SerializedName("cosmeticId")
   private int field22;
   @SerializedName("metadata")
   private JsonObject field23;
   @SerializedName("spin")
   private boolean field24;
   private transient CosmeticMetadata field25;
   private transient AxisAlignedBBBridge field26;
   private transient @Nullable AxisAlignedBBBridge field27;
   private transient int field28;
   private transient int field29;
   private transient int field30;

   @Override
   public void init() {
      if (!this.initialized) {
         if (Client.method109().method76().field8) {
            this.method4();
         }

         super.init();
      }
   }

   public void method4() {
      this.field25 = Client.method109().method53().method48(this.field22, this.field23 == null ? new JsonObject() : this.field23);
   }

   public void method2(CosmeticHologramLegacy var1) {
      super.method8(var1);
      int var2 = this.field22;
      this.field22 = var1.field22;
      this.field23 = var1.field23;
      if (var2 != this.field22) {
         this.method4();
      }
   }

   @Generated
   public int getCosmeticId() {
      return this.field22;
   }

   @Generated
   public JsonObject method5() {
      return this.field23;
   }

   @Generated
   public boolean method6() {
      return this.field24;
   }

   @Generated
   public CosmeticMetadata method8() {
      return this.field25;
   }

   @Generated
   public AxisAlignedBBBridge method9() {
      return this.field26;
   }

   @Generated
   public @Nullable AxisAlignedBBBridge method10() {
      return this.field27;
   }

   @Generated
   public int method22() {
      return this.field28;
   }

   @Generated
   public int method23() {
      return this.field29;
   }

   @Generated
   public int method31() {
      return this.field30;
   }

   @Generated
   public void method11(AxisAlignedBBBridge var1) {
      this.field26 = var1;
   }

   @Generated
   public void method12(@Nullable AxisAlignedBBBridge var1) {
      this.field27 = var1;
   }

   @Generated
   public void method13(int var1) {
      this.field28 = var1;
   }

   @Generated
   public void method14(int var1) {
      this.field29 = var1;
   }

   @Generated
   public void method15(int var1) {
      this.field30 = var1;
   }
}
