package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import javax.vecmath.Matrix4f;
import lombok.Generated;
import lombok.NonNull;

public class EventRenderPlayerModel extends LunarEvent {
   @NonNull
   private final EntityPlayerBridge field1;
   @NonNull
   private final Matrix4f field2;
   @NonNull
   private final ModelPlayerBridge field3;
   private final float field4;

   @NonNull
   @Generated
   public EntityPlayerBridge method1() {
      return this.field1;
   }

   @NonNull
   @Generated
   public Matrix4f method2() {
      return this.field2;
   }

   @NonNull
   @Generated
   public ModelPlayerBridge method3() {
      return this.field3;
   }

   @Generated
   public float method4() {
      return this.field4;
   }

   @Generated
   public EventRenderPlayerModel(@NonNull EntityPlayerBridge bridgeextension2221, @NonNull Matrix4f matrix4f2, @NonNull ModelPlayerBridge bridgeextension2_73, float value) {
      if (bridgeextension2221 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (matrix4f2 == null) {
         throw new NullPointerException("modelMatrix is marked non-null but is null");
      }

      if (bridgeextension2_73 == null) {
         throw new NullPointerException("model is marked non-null but is null");
      }

      this.field1 = bridgeextension2221;
      this.field2 = matrix4f2;
      this.field3 = bridgeextension2_73;
      this.field4 = value;
   }
}
