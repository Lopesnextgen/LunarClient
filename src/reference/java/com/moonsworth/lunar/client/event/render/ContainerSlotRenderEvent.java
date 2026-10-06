package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public abstract class ContainerSlotRenderEvent extends HighlightImpl {
   private final MarkerModel.Data4 field1;
   private final float field2;
   private final Bridge5Extension6 field3;
   private final AbstractRenderContext field4;
   private final MixinHelper_4 field5;

   @Generated
   private ContainerSlotRenderEvent(MarkerModel.Data4 var1, float var2, Bridge5Extension6 var3, AbstractRenderContext var4, MixinHelper_4 var5) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
   }

   @Generated
   public MarkerModel.Data4 method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public Bridge5Extension6 method3() {
      return this.field3;
   }

   @Generated
   public AbstractRenderContext method4() {
      return this.field4;
   }

   @Generated
   public MixinHelper_4 method5() {
      return this.field5;
   }

   public static class Data extends ContainerSlotRenderEvent {
      public Data(MarkerModel.Data4 var1, float var2, Bridge5Extension6 var3, AbstractRenderContext var4, MixinHelper_4 var5) {
         super(var1, var2, var3, var4, var5);
      }
   }

   public static class ContainerSlotAfterItemsEvent extends ContainerSlotRenderEvent {
      public ContainerSlotAfterItemsEvent(MarkerModel.Data4 var1, float var2, Bridge5Extension6 var3, AbstractRenderContext var4, MixinHelper_4 var5) {
         super(var1, var2, var3, var4, var5);
      }
   }

   public static class ContainerSlotPostEvent extends ContainerSlotRenderEvent {
      public ContainerSlotPostEvent(MarkerModel.Data4 var1, float var2, Bridge5Extension6 var3, AbstractRenderContext var4, MixinHelper_4 var5) {
         super(var1, var2, var3, var4, var5);
      }
   }

   public static class ContainerSlotPreEvent extends ContainerSlotRenderEvent {
      public ContainerSlotPreEvent(MarkerModel.Data4 var1, float var2, Bridge5Extension6 var3, AbstractRenderContext var4, MixinHelper_4 var5) {
         super(var1, var2, var3, var4, var5);
      }
   }
}
