package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.PanelPosition;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import lombok.Generated;

public class ConfigPanelPosition implements PanelPosition, JsonConfigurable {
   private float field1;
   private float field2;
   private int field3;
   private boolean reset;

   public ConfigPanelPosition() {
   }

   public void load(JsonObject json1) {
      JsonElement element2 = json1.get("panelIndex");
      if (element2 != null && !element2.isJsonNull()) {
         this.method6(element2.getAsInt());
      } else {
         this.method6(0);
      }
   }

   public void method1(JsonObject json1) {
      int number2 = this.method5();
      if (number2 != 0) {
         json1.addProperty("panelIndex", number2);
      }
   }

   @Generated
   @Override
   public float method1() {
      return this.field1;
   }

   @Generated
   @Override
   public float method3() {
      return this.field2;
   }

   @Generated
   @Override
   public int method5() {
      return this.field3;
   }

   @Generated
   @Override
   public boolean isReset() {
      return this.reset;
   }

   @Generated
   @Override
   public void method2(float value1) {
      this.field1 = value1;
   }

   @Generated
   @Override
   public void method4(float value1) {
      this.field2 = value1;
   }

   @Generated
   @Override
   public void method6(int value) {
      this.field3 = value;
   }

   @Generated
   @Override
   public void setReset(boolean flag) {
      this.reset = flag;
   }
}
