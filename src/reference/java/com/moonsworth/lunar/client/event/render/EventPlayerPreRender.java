package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.util.click.Click12;
import lombok.Generated;
import lombok.NonNull;

public class EventPlayerPreRender extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   @NonNull
   private final EntityPlayerBridge field1;
   @NonNull
   private final EventPlayerPreRender.Type field2;
   private final double field3;
   private final double field4;
   private final double field5;
   private final float field6;
   private int field7;
   private Bridge5_16 field8;
   private BatchingBufferSourceBridge field9;
   private int field10;
   private Click12 field11;

   public static EventPlayerPreRender method1(Bridge6_10 var0, double var1, double var3, double var5, float var7) {
      EventPlayerPreRender var8 = ClientEventBus.method29()
         .method12(EventPlayerPreRender.class, () -> new EventPlayerPreRender(var0, EventPlayerPreRender.Type.TEST, var1, var3, var5, var7));
      if (var8 != null && var8.isCancelled()) {
         return var8;
      }

      ClientEventBus.method29().method12(EventPlayerPreRender.class, () -> new EventPlayerPreRender(var0, EventPlayerPreRender.Type.MONITOR, var1, var3, var5, var7));
      return var8;
   }

   public static EventPlayerPreRender method2(
      EntityPlayerBridge var0, double var1, double var3, double var5, float var7, int var8, Bridge5_16 var9, BatchingBufferSourceBridge var10, int var11
   ) {
      EventPlayerPreRender var12 = ClientEventBus.method29()
         .method12(EventPlayerPreRender.class, () -> new EventPlayerPreRender(var0, EventPlayerPreRender.Type.TEST, var1, var3, var5, var7, var8, var9, var10, var11, null));
      if (var12 != null && var12.isCancelled()) {
         return var12;
      }

      ClientEventBus.method29()
         .method12(EventPlayerPreRender.class, () -> new EventPlayerPreRender(var0, EventPlayerPreRender.Type.MONITOR, var1, var3, var5, var7, var8, var9, var10, var11, null));
      return var12;
   }

   @Override
   public void cancel() {
      if (this.field2 == EventPlayerPreRender.Type.MONITOR) {
         throw new IllegalStateException("Can only cancel this event in the TEST stage.");
      }

      super.cancel();
   }

   @Generated
   public EventPlayerPreRender(
      @NonNull EntityPlayerBridge var1,
      @NonNull EventPlayerPreRender.Type var2,
      double var3,
      double var5,
      double var7,
      float var9,
      int var10,
      Bridge5_16 var11,
      BatchingBufferSourceBridge var12,
      int value,
      Click12 click12
   ) {
      if (var1 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("phase is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var5;
      this.field5 = var7;
      this.field6 = var9;
      this.field7 = var10;
      this.field8 = var11;
      this.field9 = var12;
      this.field10 = value;
      this.field11 = click12;
   }

   @Generated
   public EventPlayerPreRender(@NonNull EntityPlayerBridge var1, @NonNull EventPlayerPreRender.Type var2, double var3, double var5, double var7, float var9) {
      if (var1 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("phase is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var5;
      this.field5 = var7;
      this.field6 = var9;
   }

   @NonNull
   @Generated
   public EntityPlayerBridge method3() {
      return this.field1;
   }

   @NonNull
   @Generated
   public EventPlayerPreRender.Type method4() {
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
   public BatchingBufferSourceBridge method8() {
      return this.field9;
   }

   @Generated
   public int method9() {
      return this.field10;
   }

   @Generated
   public Click12 method10() {
      return this.field11;
   }

   @Generated
   public void method11(Click12 var1) {
      this.field11 = var1;
   }

   public enum Type {
      TEST,
      MONITOR;
   }
}
