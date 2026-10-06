package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class TextureWailaComponent implements WailaComponent {
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
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int value, int value2) {
      LcuiScreen.method31(
         mixinhelper_41, this.field2, value + this.spacing + 1, value2 + this.spacing + 1, this.field1 - this.spacing * 2 - 1, this.field1 - this.spacing * 2 - 1, -1
      );
   }

   @Generated
   public TextureWailaComponent(int value, int value2, @NotNull ResourceLocationBridge horsestats143) {
      if (horsestats143 == null) {
         throw new NullPointerException("resourceLocation is marked non-null but is null");
      }

      this.field1 = value;
      this.spacing = value2;
      this.field2 = horsestats143;
   }
}
