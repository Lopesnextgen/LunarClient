package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WailaIterator implements WailaComponent {
   private final List<WailaComponent> field1;
   private int width = -1;
   private int height = -1;

   public WailaIterator(WailaComponent... items1) {
      if (items1 != null && items1.length != 0) {
         this.field1 = Arrays.asList(items1);
      } else {
         this.field1 = new ArrayList<>();
      }
   }

   public void method1(WailaComponent... items1) {
      Collections.addAll(this.field1, items1);
   }

   @Override
   public int getWidth() {
      if (this.width == -1) {
         this.width = 0;

         for (WailaComponent waila2 : this.field1) {
            this.width = this.width + waila2.getWidth();
         }
      }

      return this.width;
   }

   @Override
   public int getHeight() {
      if (this.height == -1) {
         this.height = 0;

         for (WailaComponent waila2 : this.field1) {
            this.height = Math.max(this.height, waila2.getHeight());
         }
      }

      return this.height;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int value, int value2) {
      value2 += this.getHeight() / 2;

      for (WailaComponent waila6 : this.field1) {
         waila6.method1(mixinhelper_41, waila2, value, value2 - waila6.getHeight() / 2);
         value += waila6.getWidth();
      }
   }
}
