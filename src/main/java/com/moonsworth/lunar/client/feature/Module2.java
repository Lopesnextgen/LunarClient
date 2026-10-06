package com.moonsworth.lunar.client.feature;

import com.google.gson.JsonObject;
import lombok.Generated;

public class Module2 {
   private boolean field1;
   private boolean field2 = true;
   private boolean showOverChestplate = true;
   private boolean showOverLeggings = true;
   private boolean showOverBoots = true;
   private boolean field3 = true;
   private boolean field4;
   private float field5;

   public void method1(JsonObject var1) {
      this.field1 = var1.has("clothCloak") && var1.get("clothCloak").getAsBoolean();
      this.field5 = var1.has("hatHeightOffset") ? var1.get("hatHeightOffset").getAsFloat() : 0.0F;
      this.field4 = var1.has("flipShoulder") && var1.get("flipShoulder").getAsBoolean();
      this.field2 = !var1.has("showHatsOverHelmet") || var1.get("showHatsOverHelmet").getAsBoolean();
      this.showOverChestplate = !var1.has("showOverChestplate") || var1.get("showOverChestplate").getAsBoolean();
      this.showOverLeggings = !var1.has("showOverLeggings") || var1.get("showOverLeggings").getAsBoolean();
      this.showOverBoots = !var1.has("showOverBoots") || var1.get("showOverBoots").getAsBoolean();
      this.field3 = !var1.has("showHatsOverSkinlayer") || var1.get("showHatsOverSkinlayer").getAsBoolean();
   }

   public void method2(JsonObject var1) {
      if (this.field1) {
         var1.addProperty("clothCloak", true);
      } else {
         var1.remove("clothCloak");
      }

      if (this.field4) {
         var1.addProperty("flipShoulder", true);
      } else if (var1.has("flipShoulder")) {
         var1.remove("flipShoulder");
      }

      if (this.field5 != 0.0F) {
         var1.addProperty("hatHeightOffset", this.field5);
      } else {
         var1.remove("hatHeightOffset");
      }

      if (!this.field2) {
         var1.addProperty("showHatsOverHelmet", true);
      } else {
         var1.remove("showHatsOverHelmet");
      }

      if (!this.showOverChestplate) {
         var1.addProperty("showOverChestplate", true);
      } else {
         var1.remove("showOverChestplate");
      }

      if (!this.showOverLeggings) {
         var1.addProperty("showOverLeggings", true);
      } else {
         var1.remove("showOverLeggings");
      }

      if (!this.showOverBoots) {
         var1.addProperty("showOverBoots", true);
      } else {
         var1.remove("showOverBoots");
      }

      if (!this.field3) {
         var1.addProperty("showHatsOverSkinlayer", true);
      } else {
         var1.remove("showHatsOverSkinlayer");
      }
   }

   @Generated
   public boolean method3() {
      return this.field1;
   }

   @Generated
   public boolean method4() {
      return this.field2;
   }

   @Generated
   public boolean isShowOverChestplate() {
      return this.showOverChestplate;
   }

   @Generated
   public boolean isShowOverLeggings() {
      return this.showOverLeggings;
   }

   @Generated
   public boolean isShowOverBoots() {
      return this.showOverBoots;
   }

   @Generated
   public boolean method5() {
      return this.field3;
   }

   @Generated
   public boolean method6() {
      return this.field4;
   }

   @Generated
   public float getHatHeightOffset() {
      return this.field5;
   }

   @Generated
   public void method7(boolean var1) {
      this.field1 = var1;
   }

   @Generated
   public void method8(boolean var1) {
      this.field2 = var1;
   }

   @Generated
   public void setShowOverChestplate(boolean var1) {
      this.showOverChestplate = var1;
   }

   @Generated
   public void setShowOverLeggings(boolean var1) {
      this.showOverLeggings = var1;
   }

   @Generated
   public void setShowOverBoots(boolean var1) {
      this.showOverBoots = var1;
   }

   @Generated
   public void method12(boolean var1) {
      this.field3 = var1;
   }

   @Generated
   public void method13(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   public void method14(float var1) {
      this.field5 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Module2 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (this.method3() != var2.method3()) {
         return false;
      } else if (this.method4() != var2.method4()) {
         return false;
      } else if (this.isShowOverChestplate() != var2.isShowOverChestplate()) {
         return false;
      } else if (this.isShowOverLeggings() != var2.isShowOverLeggings()) {
         return false;
      } else if (this.isShowOverBoots() != var2.isShowOverBoots()) {
         return false;
      } else if (this.method5() != var2.method5()) {
         return false;
      } else {
         return this.method6() != var2.method6() ? false : Float.compare(this.getHatHeightOffset(), var2.getHatHeightOffset()) == 0;
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Module2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.method3() ? 79 : 97);
      var2 = var2 * 59 + (this.method4() ? 79 : 97);
      var2 = var2 * 59 + (this.isShowOverChestplate() ? 79 : 97);
      var2 = var2 * 59 + (this.isShowOverLeggings() ? 79 : 97);
      var2 = var2 * 59 + (this.isShowOverBoots() ? 79 : 97);
      var2 = var2 * 59 + (this.method5() ? 79 : 97);
      var2 = var2 * 59 + (this.method6() ? 79 : 97);
      return var2 * 59 + Float.floatToIntBits(this.getHatHeightOffset());
   }

   @Generated
   @Override
   public String toString() {
      return "CosmeticSettings(useClothCloaks="
         + this.method3()
         + ", showHatsOverHelmet="
         + this.method4()
         + ", showOverChestplate="
         + this.isShowOverChestplate()
         + ", showOverLeggings="
         + this.isShowOverLeggings()
         + ", showOverBoots="
         + this.isShowOverBoots()
         + ", showHatsOverSkinlayer="
         + this.method5()
         + ", flipArm="
         + this.method6()
         + ", hatHeightOffset="
         + this.getHatHeightOffset()
         + ")";
   }
}
