package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.bridge.Bridge3_10;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;

public interface RenderSubmission {
   void draw();

   static RenderSubmission method1(ModelRenderConfig fov80) {
      return new RenderSubmission.DeferredDraw(fov80);
   }

   class DeferredDraw implements RenderSubmission {
      public final ModelRenderConfig field1;
      private final Bridge3_10 field2;

      private DeferredDraw(ModelRenderConfig fov81) {
         this.field1 = fov81;
         if (fov81.method7().method38()) {
            this.field2 = fov81.method7().method30().method51().bridge$last().bridge$copy();
         } else {
            this.field2 = null;
         }
      }

      @Override
      public void draw() {
         this.field1.method7().method5(arg1 -> {
            Bridge5_16 bridge5_162 = arg1.method51();
            bridge5_162.bridge$pushPose();
            bridge5_162.bridge$setPose(this.field2);
         });
         PlayerModelPartMap.method1(this.field1);
         this.field1.method7().method5(arg0 -> {
            Bridge5_16 bridge5_161 = arg0.method51();
            bridge5_161.bridge$popPose();
         });
      }
   }
}
