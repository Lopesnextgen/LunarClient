package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class WailaHandler7 implements Waila {
   private final int field1;
   private final int spacing;
   @NotNull
   private final ResourceLocationBridge field2;

   @Override
   public int getWidth() {
      return this.field1;
   }

   @Override
   public int getHeight() {
      return this.field1;
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int value) {
      LcuiScreen.method31(
         var1, this.field2, var3 + this.spacing + 1, value + this.spacing + 1, this.field1 - this.spacing * 2 - 1, this.field1 - this.spacing * 2 - 1, -1
      );
   }

   @Generated
   public WailaHandler7(int var1, int var2, @NotNull ResourceLocationBridge var3) {
      if (var3 == null) {
         throw new NullPointerException("resourceLocation is marked non-null but is null");
      }

      this.field1 = var1;
      this.spacing = var2;
      this.field2 = var3;
   }
}
