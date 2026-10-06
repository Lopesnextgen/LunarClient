package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

public abstract class InventoryScreenRenderEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   public static final int field1 = 20;
   private final MixinHelper_4 field2;
   private final int field3;
   private final int field4;

   @Generated
   public MixinHelper_4 method1() {
      return this.field2;
   }

   @Generated
   public int getX() {
      return this.field3;
   }

   @Generated
   public int getY() {
      return this.field4;
   }

   @Generated
   public InventoryScreenRenderEvent(MixinHelper_4 var1, int var2, int var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   public static class HotbarPostEvent extends InventoryScreenRenderEvent {
      public HotbarPostEvent(MixinHelper_4 var1, int var2, int var3) {
         super(var1, var2, var3);
      }
   }

   public static class HotbarItemsEvent extends InventoryScreenRenderEvent {
      public HotbarItemsEvent(MixinHelper_4 var1, int var2, int var3) {
         super(var1, var2, var3);
      }
   }

   public static class HotbarPreEvent extends InventoryScreenRenderEvent {
      public HotbarPreEvent(MixinHelper_4 var1, int var2, int var3) {
         super(var1, var2, var3);
      }
   }
}
