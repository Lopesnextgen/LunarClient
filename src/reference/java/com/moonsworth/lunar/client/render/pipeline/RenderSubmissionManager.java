package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class RenderSubmissionManager implements LoadableHandler, EventBusAccess {
   private RenderStage field1 = RenderStage.POST_RENDER;
   private final List<RenderSubmission> field2 = new ArrayList<>();
   private final List<RenderSubmission> field3 = new ArrayList<>();

   public RenderSubmissionManager() {
   }

   public void init() {
      this.method1(EventRenderTick.EventRenderTickStart.class, () -> this.method3(RenderStage.COLLECTING));
   }

   public void method1(RenderSubmission tps1) {
      if (this.field1.isAfter(RenderStage.COLLECTING)) {
         LunarLogger.error("Invalid Opaque Submit Call", new RenderStageException(this.field1, tps1));
      } else {
         this.field2.add(tps1);
      }
   }

   public void method2(RenderSubmission tps1) {
      if (this.field1.isAfter(RenderStage.COLLECTING)) {
         LunarLogger.error("Invalid Translucent Submit Call", new RenderStageException(this.field1, tps1));
      } else {
         this.field3.add(tps1);
      }
   }

   public void method3(RenderStage renderStage) {
      if (renderStage != this.field1) {
         if (renderStage == RenderStage.COLLECTING || !renderStage.isBefore(this.field1)) {
            this.field1 = renderStage;
            if (renderStage == RenderStage.RENDERING_OPAQUE) {
               this.method4();
            } else if (renderStage == RenderStage.RENDERING_TRANSLUCENT) {
               this.method5();
            } else if (renderStage == RenderStage.COLLECTING) {
               this.field2.clear();
               this.field3.clear();
            }
         }
      }
   }

   private void method4() {
      if (!this.field2.isEmpty()) {
         for (RenderSubmission tps2 : this.field2) {
            tps2.draw();
         }

         this.field2.clear();
      }
   }

   private void method5() {
      if (!this.field3.isEmpty()) {
         for (RenderSubmission tps2 : this.field3) {
            tps2.draw();
         }

         this.field3.clear();
      }
   }

   public boolean method6() {
      return this.field1 == RenderStage.COLLECTING;
   }

   public void close() {
   }

   @Generated
   public RenderStage method7() {
      return this.field1;
   }
}
