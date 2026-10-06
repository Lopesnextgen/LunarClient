package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Gui3 {
   @SerializedName("coins")
   private final int field1;
   @SerializedName("barbarian")
   private final Map<String, Integer> field2;
   @SerializedName("mage")
   private final Map<String, Integer> field3;
   @SerializedName("common")
   private final Map<String, Integer> field4;

   public Gui3(int var1, Map<String, Integer> var2, Map<String, Integer> var3, Map<String, Integer> var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
   }

   @SerializedName("coins")
   public int method1() {
      return this.field1;
   }

   @SerializedName("barbarian")
   public Map<String, Integer> method2() {
      return this.field2;
   }

   @SerializedName("mage")
   public Map<String, Integer> method3() {
      return this.field3;
   }

   @SerializedName("common")
   public Map<String, Integer> method4() {
      return this.field4;
   }
}
