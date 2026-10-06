package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.Animation;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.mod.misc.EventChest;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.panoramamaker.PanoramaMaker;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ThreadLocalRandom;

public class AnimatedLogoWidget extends GuiWidget {
   private static final int field16 = 8;
   private static final float field17 = 0.15F;
   private static final ResourceLocationBridge field18 = EventChest.field2
      ? ResourceLocationBridge.create("lunar", "animatedlogo/128/logo-anniversary-hat.png")
      : ResourceLocationBridge.create("lunar", "animatedlogo/128/logo_128_no_stars.png");
   private static final ResourceLocationBridge[] field19 = new ResourceLocationBridge[8];
   private final AnimatedLogoWidget.Data[] field20 = new AnimatedLogoWidget.Data[8];
   private final float[] field21 = new float[8];
   private boolean field22 = true;
   private final boolean field23;
   private final boolean field24;

   public AnimatedLogoWidget(GuiWidget var1, boolean var2, boolean var3) {
      super(var1);

      for (int var4 = 1; var4 <= 8; var4++) {
         if (field19[var4 - 1] == null) {
            field19[var4 - 1] = ResourceLocationBridge.create("lunar", "animatedlogo/128/logo_128_star_" + var4 + ".png");
         }
      }

      this.method1();
      this.field24 = var3;
      this.field23 = var2;
   }

   private void method1() {
      for (int var1 = 1; var1 <= 8; var1++) {
         if (this.field20[var1 - 1] != null && !this.field20[var1 - 1].CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            this.field22 = false;
         }

         if (this.field20[var1 - 1] == null || !this.field20[var1 - 1].CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            long var2 = ThreadLocalRandom.current().nextLong(4000L, 12000L);
            if (this.field22) {
               this.field21[var1 - 1] = Math.max(ThreadLocalRandom.current().nextFloat(), 0.8F);
            }

            this.field20[var1 - 1] = new AnimatedLogoWidget.Data(var2);
         }
      }
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (!PanoramaMaker.field12) {
         if (this.field8.getAsBoolean()) {
            var1.push();
            if (this.field23) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method31(
                  var1, field18, this.x + 1.0F, this.y + 1.0F, this.width, this.height, 855638016
               );
            }

            int var4;
            if (!this.field24) {
               var4 = ThreadModuleDump63.method4().method41().method6().method66().method14(0.0F) | 0xFF000000;
            } else {
               var4 = -1;
            }

            com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, field18, this.x, this.y, this.width, this.height, var4);

            for (int var5 = 0; var5 < 8; var5++) {
               AnimatedLogoWidget.Data var6 = this.field20[var5];
               if (!var6.OHCHHRHOHOCCRCIHCIRORCOCCOHOHI()) {
                  var6.start();
               }

               var1.push();
               if (!var6.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
                  this.method1();
               }

               float var7 = var6.method3();
               if (var6.method2() && this.field22) {
                  this.field22 = false;
               }

               if (this.field22) {
                  var7 = Math.max(var7, this.field21[var5]);
               }

               if (this.field23) {
                  com.moonsworth.lunar.client.ui.LcuiScreen.method31(
                     var1, field19[var5], this.x + 1.0F, this.y + 1.0F, this.width, this.height, ThreadModuleDump23.method11(0.0F, 0.0F, 0.0F, var7 / 5.0F)
                  );
               }

               com.moonsworth.lunar.client.ui.LcuiScreen.method31(
                  var1, field19[var5], this.x, this.y, this.width, this.height, ThreadModuleDump23.method18(var4, var7)
               );
               var1.pop();
            }

            var1.pop();
         }
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   private static class Data extends Animation {
      private ColorAnimation field10;
      private ColorAnimation field11;

      private Data(long var1) {
         super((long)((float)var1 * 1.0F));
         this.field10 = new ColorAnimation(Math.min((long)Math.min((float)var1 * 0.2F, 3000.0F), 1500L));
         this.field11 = new ColorAnimation(Math.min((long)((float)var1 * 0.4F), 5000L));
      }

      @Override
      public void start() {
         super.start();
         if (!this.field10.method5()) {
            this.field10.start();
         }
      }

      boolean method2() {
         return this.field10.method6();
      }

      float method3() {
         if (!this.field10.method5()) {
            this.field10.start();
         }

         if (this.field10.method7()) {
            return Math.max(1.0F * this.field10.method9(), 0.15F);
         }

         if (this.OIOOHIHRHORHROIOICHCHOIRRRCRIR() <= this.field11.getDurationMs()) {
            if (!this.field11.method5()) {
               this.field11.start();
            }

            return 1.0F - 0.85F * this.field11.method9();
         } else {
            return 1.0F;
         }
      }
   }
}
