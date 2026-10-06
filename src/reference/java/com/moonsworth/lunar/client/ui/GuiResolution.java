package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class GuiResolution {
   private final int field1;
   private final int field2;
   private final double field3;
   private final double field4;
   private int field5;
   private int field6;
   private int field7;

   public GuiResolution(MinecraftBridge bridge5_121) {
      this(bridge5_121, bridge5_121.bridge$displayWidth(), bridge5_121.bridge$displayHeight());
   }

   public GuiResolution(int value, int number2, int number3, double value2, double value3) {
      this.field1 = number2;
      this.field2 = number3;
      this.field5 = MathUtils.method8(value2);
      this.field6 = MathUtils.method8(value3);
      this.field7 = value;
      this.field3 = value2;
      this.field4 = value3;
   }

   public GuiResolution(MinecraftBridge bridge5_121, int number2, int number3) {
      this.field1 = number2;
      this.field2 = number3;
      if (Ref.MC_VERSION >= 6) {
         this.field7 = Math.max(1, bridge5_121.bridge$getGuiScale());
         this.field5 = number2;
         this.field6 = number3;
      } else {
         this.field5 = (int)(number2 / LcuiScreen.method20());
         this.field6 = (int)(number3 / LcuiScreen.method20());
         this.field7 = 1;
         boolean flag4 = bridge5_121.bridge$unicode();
         int number5 = bridge5_121.bridge$getGameSettings().bridge$getGuiScale();
         if (number5 == 0) {
            number5 = 1000;
         }

         while (this.field7 < number5 && this.field5 / (this.field7 + 1) >= 320 && this.field6 / (this.field7 + 1) >= 240) {
            this.field7++;
         }

         if (flag4 && this.field7 % 2 != 0 && this.field7 != 1) {
            this.field7--;
         }
      }

      this.field3 = (double)this.field5 / this.field7;
      this.field4 = (double)this.field6 / this.field7;
      this.field5 = MathUtils.method8(this.field3);
      this.field6 = MathUtils.method8(this.field4);
   }

   public double method1() {
      return this.field3;
   }

   public double method2() {
      return this.field4;
   }

   @Generated
   public int getWidth() {
      return this.field1;
   }

   @Generated
   public int getHeight() {
      return this.field2;
   }

   @Generated
   public int getScaledWidth() {
      return this.field5;
   }

   @Generated
   public int getScaledHeight() {
      return this.field6;
   }

   @Generated
   public int method3() {
      return this.field7;
   }
}
