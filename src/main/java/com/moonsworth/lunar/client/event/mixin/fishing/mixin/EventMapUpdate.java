package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.world.MapDecorationBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import java.util.Collection;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventMapUpdate extends LunarEvent {
   private final int field1;
   private final MapDataBridge field2;
   private final boolean field3;
   @Nullable
   private final Collection<MapDecorationBridge> field4;

   @Generated
   public int getId() {
      return this.field1;
   }

   @Generated
   public MapDataBridge method1() {
      return this.field2;
   }

   @Generated
   public boolean method2() {
      return this.field3;
   }

   @Nullable
   @Generated
   public Collection<MapDecorationBridge> method3() {
      return this.field4;
   }

   @Generated
   public EventMapUpdate(int value, MapDataBridge itemcounter2_32, boolean flag, @Nullable Collection<MapDecorationBridge> list4) {
      this.field1 = value;
      this.field2 = itemcounter2_32;
      this.field3 = flag;
      this.field4 = list4;
   }
}
