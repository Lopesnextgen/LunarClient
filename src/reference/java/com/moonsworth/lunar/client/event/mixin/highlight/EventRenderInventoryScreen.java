package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

public abstract class EventRenderInventoryScreen extends com.moonsworth.lunar.client.event.LunarEvent {
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
   public EventRenderInventoryScreen(MixinHelper_4 mixinhelper_41, int number2, int number3) {
      this.field2 = mixinhelper_41;
      this.field3 = number2;
      this.field4 = number3;
   }

   public static class EventRenderHotbarPost extends EventRenderInventoryScreen {
      public EventRenderHotbarPost(MixinHelper_4 mixinhelper_41, int number2, int number3) {
         super(mixinhelper_41, number2, number3);
      }
   }

   public static class EventRenderHotbarItems extends EventRenderInventoryScreen {
      public EventRenderHotbarItems(MixinHelper_4 mixinhelper_41, int number2, int number3) {
         super(mixinhelper_41, number2, number3);
      }
   }

   public static class EventRenderHotbarPre extends EventRenderInventoryScreen {
      public EventRenderHotbarPre(MixinHelper_4 mixinhelper_41, int number2, int number3) {
         super(mixinhelper_41, number2, number3);
      }
   }
}
