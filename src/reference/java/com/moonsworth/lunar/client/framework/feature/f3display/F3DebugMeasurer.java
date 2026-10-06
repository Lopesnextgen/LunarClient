package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;

public class F3DebugMeasurer implements F3DebugWriter {
   private float width;
   private float height;
   private final boolean field1;

   public F3DebugMeasurer(boolean flag1) {
      this.field1 = flag1;
   }

   @Override
   public F3DebugWriter method3(boolean flag1, String... items2) {
      for (String text6 : items2) {
         if (text6 == null) {
            return this;
         }
      }

      String text7 = String.join("", items2);
      Bridge10_2 bridge10_28 = Ref.method10();
      this.method6(bridge10_28.bridge$getStringWidth(text7), bridge10_28.method19());
      return this;
   }

   @Override
   public F3DebugWriter method4(boolean flag1, List<ProfilerResultBridge> list) {
      this.method6(150.0F, 150.0F);
      return this;
   }

   @Override
   public F3DebugWriter method5(com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart f3display1) {
      this.method6(242.0F, 52.0F);
      return this;
   }

   @Override
   public F3DebugWriter method6(String text, String text2) {
      return this;
   }

   @Override
   public boolean method7() {
      return this.field1;
   }

   private void method6(float value, float value2) {
      this.width = Math.max(this.width, value);
      this.height += value2;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }
}
