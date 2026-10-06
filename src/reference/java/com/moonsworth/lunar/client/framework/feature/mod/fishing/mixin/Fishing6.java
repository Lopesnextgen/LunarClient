package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType3;

public class Fishing6 {
   @SerializedName("rarity")
   private final GuiType3 field1;
   @SerializedName("collected")
   private final boolean field2;

   public Fishing6(GuiType3 var1, boolean var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @SerializedName("rarity")
   public GuiType3 method1() {
      return this.field1;
   }

   @SerializedName("collected")
   public boolean method2() {
      return this.field2;
   }
}
