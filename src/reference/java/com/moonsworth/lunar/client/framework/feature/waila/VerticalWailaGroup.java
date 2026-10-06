package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.ArrayList;
import java.util.List;

public class VerticalWailaGroup implements WailaComponent {
   private int width = -1;
   private int height = -1;
   private final List<WailaComponent> field1 = new ArrayList<>();

   public VerticalWailaGroup(WailaComponent... items1) {
      for (WailaComponent waila5 : items1) {
         if (waila5 != null) {
            this.field1.add(waila5);
         }
      }
   }

   @Override
   public int getWidth() {
      if (this.width == -1) {
         this.width = 0;

         for (WailaComponent waila2 : this.field1) {
            this.width = Math.max(this.width, waila2.getWidth());
         }
      }

      return this.width;
   }

   @Override
   public int getHeight() {
      if (this.height == -1) {
         this.height = 0;

         for (WailaComponent waila2 : this.field1) {
            this.height = this.height + waila2.getHeight() + 1;
         }
      }

      return this.height;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int value, int index4) {
      index4++;

      for (WailaComponent waila6 : this.field1) {
         waila6.method1(mixinhelper_41, waila2, value, index4);
         index4 += waila6.getHeight() + 1;
      }
   }
}
