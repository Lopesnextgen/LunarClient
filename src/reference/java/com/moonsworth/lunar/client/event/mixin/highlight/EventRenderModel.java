package com.moonsworth.lunar.client.event.mixin.highlight;

import com.lunarclient.apollo.module.limb.BodyPart;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ModelBipedBridge;
import java.util.EnumSet;
import java.util.Set;
import lombok.Generated;

public abstract class EventRenderModel extends com.moonsworth.lunar.client.event.LunarEvent {
   private final EntityPlayerBridge field1;
   private final ModelBipedBridge field2;
   private final float field3;

   @Generated
   public EntityPlayerBridge method1() {
      return this.field1;
   }

   @Generated
   public ModelBipedBridge method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   private EventRenderModel(EntityPlayerBridge bridgeextension2221, ModelBipedBridge bridgeextension_102, float value3) {
      this.field1 = bridgeextension2221;
      this.field2 = bridgeextension_102;
      this.field3 = value3;
   }

   public static class EventRenderBipedModel extends EventRenderModel {
      private Set<BodyPart> field4 = EnumSet.noneOf(BodyPart.class);
      private Set<BodyPart> field5 = EnumSet.noneOf(BodyPart.class);

      public EventRenderBipedModel(EntityPlayerBridge bridgeextension2221, ModelBipedBridge bridgeextension_102, float value3) {
         super(bridgeextension2221, bridgeextension_102, value3);
      }

      @Generated
      public Set<BodyPart> method4() {
         return this.field4;
      }

      @Generated
      public Set<BodyPart> method5() {
         return this.field5;
      }
   }

   public static class EventRenderModelPlayer extends EventRenderModel {
      public EventRenderModelPlayer(EntityPlayerBridge bridgeextension2221, ModelBipedBridge bridgeextension_102, float value3) {
         super(bridgeextension2221, bridgeextension_102, value3);
      }
   }
}
