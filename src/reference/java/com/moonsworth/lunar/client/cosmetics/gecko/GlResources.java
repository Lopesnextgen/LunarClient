package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.GlObjectBridge;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

final class GlResources {
   final GlObjectBridge field1;
   final Bridge2_5 field2;
   final GlObjectBridge field3;
   final int field4;
   final int field5;
   @Nullable
   final Bridge2_5 field6;
   final float[] field7;
   final int[] field8;

   void delete() {
      Bridge.method42().method91(this.field1);
      this.field2.bridge$close();
      if (this.field6 != null) {
         this.field6.bridge$close();
      }

      Bridge.method42().method91(this.field3);
   }

   @Generated
   public GlResources(GlObjectBridge bridge_351, Bridge2_5 bridge2_52, GlObjectBridge bridge_353, int number4, int number5, @Nullable Bridge2_5 bridge2_56, float[] items7, int[] items8) {
      this.field1 = bridge_351;
      this.field2 = bridge2_52;
      this.field3 = bridge_353;
      this.field4 = number4;
      this.field5 = number5;
      this.field6 = bridge2_56;
      this.field7 = items7;
      this.field8 = items8;
   }
}
