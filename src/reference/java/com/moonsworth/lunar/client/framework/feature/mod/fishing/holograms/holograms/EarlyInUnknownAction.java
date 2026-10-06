package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.EarlyInUnknown;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapRoomType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonUpdateAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import org.joml.Vector2i;

public class EarlyInUnknownAction implements DungeonUpdateAction {
   private final Vector2i field1;

   public EarlyInUnknownAction(Vector2i vector2i1) {
      this.field1 = vector2i1;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder().setEarlyInUnknown(EarlyInUnknown.newBuilder().setPosition(ProtoConverter.method17(this.field1)).build()).build();
   }

   @Override
   public void method2(DungeonStateTracker holograms2_51) {
      if (holograms2_51.field24 == null) {
         DungeonRoomTracker holograms4iterator2 = holograms2_51.method13(this.field1.x, this.field1.y);
         if (holograms4iterator2 == null) {
            DungeonRoomTracker holograms4iterator3 = new DungeonRoomTracker(holograms2_51, MapRoomType.UNKNOWN);
            holograms4iterator3.method10(RoomState.ADJACENT);
            holograms4iterator3.method5(WorldPosition.method1(this.field1.x, this.field1.y, holograms2_51));
         }
      }
   }

   public static EarlyInUnknownAction method3(DungeonUpdate dungeonupdate0) {
      if (!dungeonupdate0.hasEarlyInUnknown()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      } else {
         return new EarlyInUnknownAction(ProtoConverter.method18(dungeonupdate0.getEarlyInUnknown().getPosition()));
      }
   }

   public Vector2i method4() {
      return this.field1;
   }
}
