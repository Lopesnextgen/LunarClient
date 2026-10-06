package com.moonsworth.lunar.client.tps;

import com.moonsworth.lunar.bridge.Bridge3_10;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.emote.ModelRenderConfig;

public interface Tps {
   void draw();

   static Tps method1(ModelRenderConfig var0) {
      return new Tps.Data(var0);
   }

   class Data implements Tps {
      public final ModelRenderConfig field1;
      private final Bridge3_10 field2;

      private Data(ModelRenderConfig var1) {
         this.field1 = var1;
         if (var1.method7().method38()) {
            this.field2 = var1.method7().method30().method51().bridge$last().bridge$copy();
         } else {
            this.field2 = null;
         }
      }

      @Override
      public void draw() {
         this.field1.method7().method5(var1 -> {
            Bridge5_16 var2 = var1.method51();
            var2.bridge$pushPose();
            var2.bridge$setPose(this.field2);
         });
         PlayerModelPartMap.method1(this.field1);
         this.field1.method7().method5(var0 -> {
            Bridge5_16 var1 = var0.method51();
            var1.bridge$popPose();
         });
      }
   }
}
