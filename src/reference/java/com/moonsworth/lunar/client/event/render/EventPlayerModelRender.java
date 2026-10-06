package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.client.highlight.Highlight;
import javax.vecmath.Matrix4f;
import lombok.Generated;
import lombok.NonNull;

public class EventPlayerModelRender extends Highlight {
   @NonNull
   private final EntityPlayerBridge field1;
   @NonNull
   private final Matrix4f field2;
   @NonNull
   private final BridgeExtension2_7 field3;
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
   public BridgeExtension2_7 method3() {
      return this.field3;
   }

   @Generated
   public float method4() {
      return this.field4;
   }

   @Generated
   public EventPlayerModelRender(@NonNull EntityPlayerBridge var1, @NonNull Matrix4f var2, @NonNull BridgeExtension2_7 var3, float value) {
      if (var1 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("modelMatrix is marked non-null but is null");
      }

      if (var3 == null) {
         throw new NullPointerException("model is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = value;
   }
}
