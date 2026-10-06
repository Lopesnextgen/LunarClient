package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapRoomType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomStateChange;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class RoomStateHistory {
   private final WorldPosition field1;
   private final WorldPosition field2;
   private final DungeonStateTracker field3;
   private MapRoomType field4;
   private List<RoomStateChange> field5 = new ArrayList<>();
   private int field6 = -1;

   public RoomStateHistory(DungeonStateTracker holograms2_51, MapRoomType map, WorldPosition nameplate43, WorldPosition nameplate44) {
      this.field3 = holograms2_51;
      this.field4 = null;
      this.field1 = nameplate43;
      this.field2 = nameplate44;
      this.method1(map);
   }

   public void method1(MapRoomType hologramstype51) {
      if (this.field4 != hologramstype51) {
         this.field5.add(new RoomStateChange(this.field4, hologramstype51));
         this.field4 = hologramstype51;
         if (this.field4 != MapRoomType.BLOOD && this.field4 != MapRoomType.WITHER_DOOR) {
            this.field3.method18(this);
         } else {
            this.field3.method17(this);
         }
      }
   }

   public void method2(MapRoomType hologramstype51) {
      this.field4 = hologramstype51;
   }

   public void method3(List<RoomStateChange> list) {
      this.field5 = list;
   }

   public void method4(long number1) {
      while (this.method5(number1)) {
      }
   }

   private boolean method5(long number1) {
      if (this.field6 >= 0) {
         RoomStateChange holograms_53 = this.field5.get(this.field6);
         if (holograms_53.getTimestamp() > number1) {
            this.field6--;
            holograms_53.method2(this);
            return true;
         }
      }

      if (this.field5.size() <= this.field6 + 1) {
         return false;
      } else {
         RoomStateChange holograms_54 = this.field5.get(this.field6 + 1);
         if (holograms_54.getTimestamp() < number1) {
            this.field6++;
            holograms_54.method1(this);
            return true;
         } else {
            return false;
         }
      }
   }

   public List<RoomStateChange> getEvents() {
      return this.field5;
   }

   @Generated
   public WorldPosition method6() {
      return this.field1;
   }

   @Generated
   public WorldPosition method7() {
      return this.field2;
   }

   @Generated
   public MapRoomType method8() {
      return this.field4;
   }
}
