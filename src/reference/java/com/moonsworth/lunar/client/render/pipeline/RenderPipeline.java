package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.bridge.Bridge5_16;
import java.util.List;
import lombok.Generated;

public class RenderPipeline {
   private Bridge5_16 field1;
   private List<PipelinePass> passes;

   @Generated
   public RenderPipeline() {
   }

   @Generated
   public Bridge5_16 method1() {
      return this.field1;
   }

   @Generated
   public List<PipelinePass> getPasses() {
      return this.passes;
   }

   @Generated
   public void method3(Bridge5_16 bridge5_161) {
      this.field1 = bridge5_161;
   }

   @Generated
   public void setPasses(List<PipelinePass> list) {
      this.passes = list;
   }
}
