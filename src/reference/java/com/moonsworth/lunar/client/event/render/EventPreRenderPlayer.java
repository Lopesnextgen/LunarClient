package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.MultiBufferSourceBridge;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.render.pipeline.RenderPipeline;
import lombok.Generated;
import lombok.NonNull;

public class EventPreRenderPlayer extends com.moonsworth.lunar.client.event.CancellableEvent {
   @NonNull
   private final EntityPlayerBridge field1;
   @NonNull
   private final EventPreRenderPlayer.Phase field2;
   private final double field3;
   private final double field4;
   private final double field5;
   private final float field6;
   private int field7;
   private Bridge5_16 field8;
   private MultiBufferSourceBridge field9;
   private int field10;
   private RenderPipeline field11;

   public static EventPreRenderPlayer method1(Bridge6_10 bridge6_100, double value1, double value3, double value5, float value7) {
      EventPreRenderPlayer highlightimpl98 = LunarEventBus.method29()
         .method12(EventPreRenderPlayer.class, () -> new EventPreRenderPlayer(bridge6_100, EventPreRenderPlayer.Phase.TEST, value1, value3, value5, value7));
      if (highlightimpl98 != null && highlightimpl98.isCancelled()) {
         return highlightimpl98;
      }

      LunarEventBus.method29().method12(EventPreRenderPlayer.class, () -> new EventPreRenderPlayer(bridge6_100, EventPreRenderPlayer.Phase.MONITOR, value1, value3, value5, value7));
      return highlightimpl98;
   }

   public static EventPreRenderPlayer method2(
      EntityPlayerBridge entity, double value1, double value3, double value5, float value7, int value, Bridge5_16 bridge5_169, MultiBufferSourceBridge bridge1710, int value2
   ) {
      EventPreRenderPlayer highlightimpl912 = LunarEventBus.method29()
         .method12(EventPreRenderPlayer.class, () -> new EventPreRenderPlayer(entity, EventPreRenderPlayer.Phase.TEST, value1, value3, value5, value7, value, bridge5_169, bridge1710, value2, null));
      if (highlightimpl912 != null && highlightimpl912.isCancelled()) {
         return highlightimpl912;
      }

      LunarEventBus.method29()
         .method12(EventPreRenderPlayer.class, () -> new EventPreRenderPlayer(entity, EventPreRenderPlayer.Phase.MONITOR, value1, value3, value5, value7, value, bridge5_169, bridge1710, value2, null));
      return highlightimpl912;
   }

   @Override
   public void cancel() {
      if (this.field2 == EventPreRenderPlayer.Phase.MONITOR) {
         throw new IllegalStateException("Can only cancel this event in the TEST stage.");
      }

      super.cancel();
   }

   @Generated
   public EventPreRenderPlayer(
      @NonNull EntityPlayerBridge bridgeextension2221,
      @NonNull EventPreRenderPlayer.Phase type2,
      double value3,
      double value5,
      double value7,
      float value9,
      int value,
      Bridge5_16 bridge5_1611,
      MultiBufferSourceBridge bridge1712,
      int value2,
      RenderPipeline click1214
   ) {
      if (bridgeextension2221 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (type2 == null) {
         throw new NullPointerException("phase is marked non-null but is null");
      }

      this.field1 = bridgeextension2221;
      this.field2 = type2;
      this.field3 = value3;
      this.field4 = value5;
      this.field5 = value7;
      this.field6 = value9;
      this.field7 = value;
      this.field8 = bridge5_1611;
      this.field9 = bridge1712;
      this.field10 = value2;
      this.field11 = click1214;
   }

   @Generated
   public EventPreRenderPlayer(@NonNull EntityPlayerBridge bridgeextension2221, @NonNull EventPreRenderPlayer.Phase type2, double value3, double value5, double value7, float value9) {
      if (bridgeextension2221 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (type2 == null) {
         throw new NullPointerException("phase is marked non-null but is null");
      }

      this.field1 = bridgeextension2221;
      this.field2 = type2;
      this.field3 = value3;
      this.field4 = value5;
      this.field5 = value7;
      this.field6 = value9;
   }

   @NonNull
   @Generated
   public EntityPlayerBridge method3() {
      return this.field1;
   }

   @NonNull
   @Generated
   public EventPreRenderPlayer.Phase method4() {
      return this.field2;
   }

   @Generated
   public double getX() {
      return this.field3;
   }

   @Generated
   public double getY() {
      return this.field4;
   }

   @Generated
   public double getZ() {
      return this.field5;
   }

   @Generated
   public float method5() {
      return this.field6;
   }

   @Generated
   public int method6() {
      return this.field7;
   }

   @Generated
   public Bridge5_16 method7() {
      return this.field8;
   }

   @Generated
   public MultiBufferSourceBridge method8() {
      return this.field9;
   }

   @Generated
   public int method9() {
      return this.field10;
   }

   @Generated
   public RenderPipeline method10() {
      return this.field11;
   }

   @Generated
   public void method11(RenderPipeline click121) {
      this.field11 = click121;
   }

   public enum Phase {
      TEST,
      MONITOR;

      Phase() {
      }
   }
}
