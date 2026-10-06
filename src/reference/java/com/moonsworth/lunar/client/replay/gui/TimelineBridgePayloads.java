package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import java.util.UUID;

public class TimelineBridgePayloads {
   public TimelineBridgePayloads() {
   }

   public static class KeyframeTarget {
      @SerializedName("layerId")
      public UUID field1;
      @SerializedName("keyframeType")
      public String field2;
      @SerializedName("keyframeId")
      public String field3;
      @SerializedName("frame")
      public int frame;

      public KeyframeTarget() {
      }
   }

   public static class KeyframeMove {
      @SerializedName("layerId")
      public UUID field1;
      @SerializedName("parentType")
      public String field2;
      @SerializedName("keyframeType")
      public String field3;
      @SerializedName("keyframeId")
      public String field4;
      @SerializedName("currentFrame")
      public int field5;
      @SerializedName("newFrame")
      public int field6;

      public KeyframeMove() {
      }
   }

   public static class LayerMove {
      @SerializedName("layerId")
      public UUID field1;
      @SerializedName("trackId")
      public UUID field2;
      @SerializedName("frame")
      public int frame;

      public LayerMove() {
      }
   }

   public class PendingKeyframeMove {
      private final KeyframeProperty<?, ?> field1;
      private final int field2;
      private final KeyframeProperty.Keyframe field3;

      public PendingKeyframeMove(KeyframeProperty<?, ?> keyframeProperty, int value, KeyframeProperty.Keyframe keyframe) {
         this.field1 = keyframeProperty;
         this.field2 = value;
         this.field3 = keyframe;
      }

      public KeyframeProperty<?, ?> method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public KeyframeProperty.Keyframe method3() {
         return this.field3;
      }
   }
}
