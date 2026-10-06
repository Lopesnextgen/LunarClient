package com.moonsworth.lunar.client.event.mixin.highlight;

import com.lunarclient.apollo.module.limb.BodyPart;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BipedModelBridge;
import java.util.EnumSet;
import java.util.Set;
import lombok.Generated;

public abstract class ModelRenderEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final EntityPlayerBridge field1;
   private final BipedModelBridge field2;
   private final float field3;

   @Generated
   public EntityPlayerBridge method1() {
      return this.field1;
   }

   @Generated
   public BipedModelBridge method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   private ModelRenderEvent(EntityPlayerBridge var1, BipedModelBridge var2, float var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public static class BipedModelRenderEvent extends ModelRenderEvent {
      private Set<BodyPart> field4 = EnumSet.noneOf(BodyPart.class);
      private Set<BodyPart> field5 = EnumSet.noneOf(BodyPart.class);

      public BipedModelRenderEvent(EntityPlayerBridge var1, BipedModelBridge var2, float var3) {
         super(var1, var2, var3);
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

   public static class ModelPlayerRenderEvent extends ModelRenderEvent {
      public ModelPlayerRenderEvent(EntityPlayerBridge var1, BipedModelBridge var2, float var3) {
         super(var1, var2, var3);
      }
   }
}
