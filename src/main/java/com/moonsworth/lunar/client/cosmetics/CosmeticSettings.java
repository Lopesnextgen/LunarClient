package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonObject;
import lombok.Generated;

public class CosmeticSettings {
   private boolean field1;
   private boolean field2 = true;
   private boolean showOverChestplate = true;
   private boolean showOverLeggings = true;
   private boolean showOverBoots = true;
   private boolean field3 = true;
   private boolean field4;
   private float field5;

   public void method1(JsonObject json1) {
      this.field1 = json1.has("clothCloak") && json1.get("clothCloak").getAsBoolean();
      this.field5 = json1.has("hatHeightOffset") ? json1.get("hatHeightOffset").getAsFloat() : 0.0F;
      this.field4 = json1.has("flipShoulder") && json1.get("flipShoulder").getAsBoolean();
      this.field2 = !json1.has("showHatsOverHelmet") || json1.get("showHatsOverHelmet").getAsBoolean();
      this.showOverChestplate = !json1.has("showOverChestplate") || json1.get("showOverChestplate").getAsBoolean();
      this.showOverLeggings = !json1.has("showOverLeggings") || json1.get("showOverLeggings").getAsBoolean();
      this.showOverBoots = !json1.has("showOverBoots") || json1.get("showOverBoots").getAsBoolean();
      this.field3 = !json1.has("showHatsOverSkinlayer") || json1.get("showHatsOverSkinlayer").getAsBoolean();
   }

   public void method2(JsonObject json1) {
      if (this.field1) {
         json1.addProperty("clothCloak", true);
      } else {
         json1.remove("clothCloak");
      }

      if (this.field4) {
         json1.addProperty("flipShoulder", true);
      } else if (json1.has("flipShoulder")) {
         json1.remove("flipShoulder");
      }

      if (this.field5 != 0.0F) {
         json1.addProperty("hatHeightOffset", this.field5);
      } else {
         json1.remove("hatHeightOffset");
      }

      if (!this.field2) {
         json1.addProperty("showHatsOverHelmet", true);
      } else {
         json1.remove("showHatsOverHelmet");
      }

      if (!this.showOverChestplate) {
         json1.addProperty("showOverChestplate", true);
      } else {
         json1.remove("showOverChestplate");
      }

      if (!this.showOverLeggings) {
         json1.addProperty("showOverLeggings", true);
      } else {
         json1.remove("showOverLeggings");
      }

      if (!this.showOverBoots) {
         json1.addProperty("showOverBoots", true);
      } else {
         json1.remove("showOverBoots");
      }

      if (!this.field3) {
         json1.addProperty("showHatsOverSkinlayer", true);
      } else {
         json1.remove("showHatsOverSkinlayer");
      }
   }

   @Generated
   public CosmeticSettings() {
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
   public void method7(boolean flag1) {
      this.field1 = flag1;
   }

   @Generated
   public void method8(boolean flag1) {
      this.field2 = flag1;
   }

   @Generated
   public void setShowOverChestplate(boolean flag1) {
      this.showOverChestplate = flag1;
   }

   @Generated
   public void setShowOverLeggings(boolean flag1) {
      this.showOverLeggings = flag1;
   }

   @Generated
   public void setShowOverBoots(boolean flag1) {
      this.showOverBoots = flag1;
   }

   @Generated
   public void method12(boolean flag1) {
      this.field3 = flag1;
   }

   @Generated
   public void method13(boolean flag1) {
      this.field4 = flag1;
   }

   @Generated
   public void method14(float value) {
      this.field5 = value;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof CosmeticSettings module22)) {
         return false;
      } else if (!module22.canEqual(this)) {
         return false;
      } else if (this.method3() != module22.method3()) {
         return false;
      } else if (this.method4() != module22.method4()) {
         return false;
      } else if (this.isShowOverChestplate() != module22.isShowOverChestplate()) {
         return false;
      } else if (this.isShowOverLeggings() != module22.isShowOverLeggings()) {
         return false;
      } else if (this.isShowOverBoots() != module22.isShowOverBoots()) {
         return false;
      } else if (this.method5() != module22.method5()) {
         return false;
      } else {
         return this.method6() != module22.method6() ? false : Float.compare(this.getHatHeightOffset(), module22.getHatHeightOffset()) == 0;
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof CosmeticSettings;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method3() ? 79 : 97);
      number2 = number2 * 59 + (this.method4() ? 79 : 97);
      number2 = number2 * 59 + (this.isShowOverChestplate() ? 79 : 97);
      number2 = number2 * 59 + (this.isShowOverLeggings() ? 79 : 97);
      number2 = number2 * 59 + (this.isShowOverBoots() ? 79 : 97);
      number2 = number2 * 59 + (this.method5() ? 79 : 97);
      number2 = number2 * 59 + (this.method6() ? 79 : 97);
      return number2 * 59 + Float.floatToIntBits(this.getHatHeightOffset());
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
