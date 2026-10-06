package com.moonsworth.lunar.client.render.pipeline;

public class RenderStageException extends RuntimeException {
   public RenderStageException(RenderStage renderStage, RenderSubmission tps2) {
      super("RenderManager received a submit after COLLECTING stage! Stage: " + renderStage + method1(tps2));
   }

   private static String method1(RenderSubmission tps0) {
      return tps0 instanceof RenderSubmission.DeferredDraw data1 ? " (Geckolib: " + data1.field1.getTexture() + ")" : "";
   }
}
