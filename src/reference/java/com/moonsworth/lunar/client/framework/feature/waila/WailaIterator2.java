package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.ArrayList;
import java.util.List;

public class WailaIterator2 implements Waila {
   private int width = -1;
   private int height = -1;
   private final List<Waila> field1 = new ArrayList<>();

   public WailaIterator2(Waila... var1) {
      for (Waila var5 : var1) {
         if (var5 != null) {
            this.field1.add(var5);
         }
      }
   }

   @Override
   public int getWidth() {
      if (this.width == -1) {
         this.width = 0;

         for (Waila var2 : this.field1) {
            this.width = Math.max(this.width, var2.getWidth());
         }
      }

      return this.width;
   }

   @Override
   public int getHeight() {
      if (this.height == -1) {
         this.height = 0;

         for (Waila var2 : this.field1) {
            this.height = this.height + var2.getHeight() + 1;
         }
      }

      return this.height;
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int value, int value2) {
      value2++;

      for (Waila var6 : this.field1) {
         var6.method1(var1, var2, value, value2);
         value2 += var6.getHeight() + 1;
      }
   }
}
