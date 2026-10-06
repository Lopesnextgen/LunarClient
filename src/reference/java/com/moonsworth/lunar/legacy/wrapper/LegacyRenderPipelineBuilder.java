package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge$Extension;
import com.moonsworth.lunar.bridge.Bridge2$Extension2;
import com.moonsworth.lunar.bridge.Bridge2Factory;
import com.moonsworth.lunar.bridge.Bridge_45;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;

public class LegacyRenderPipelineBuilder extends RenderPipelineBuilder {
   @Override
   protected Bridge_45 method45() {
      return new RenderPipelineBridgeAdapter(
         this.field1,
         this.field2,
         this.field3,
         this.field4,
         this.field5,
         this.field6 == null ? Bridge2Factory.method1().bridge$build() : this.field6.bridge$build(),
         this.field7,
         this.field8,
         this.field9,
         this.field10,
         this.field11 == null || this.field11,
         this.field12 == null || this.field12,
         this.field13 == null || this.field13,
         this.field14 == null || this.field14,
         this.field15,
         this.field19,
         this.field16,
         this.field17,
         this.field18
      );
   }

   @Override
   public Bridge$Extension method43() {
      return new BridgeHandler$Data(
         this.field2,
         this.field3,
         this.field4,
         this.field5,
         this.field6 == null ? null : this.field6.bridge$build(),
         this.field7,
         this.field8,
         this.field9,
         this.field10,
         this.field11,
         this.field12,
         this.field13,
         this.field14,
         this.field15
      );
   }

   @Override
   protected Bridge2$Extension2 method46() {
      return Bridge2Factory.method1();
   }
}
