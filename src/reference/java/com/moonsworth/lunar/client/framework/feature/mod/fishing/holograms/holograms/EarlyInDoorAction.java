package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.EarlyInDoor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapRoomType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonUpdateAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import org.joml.Vector2i;

public class EarlyInDoorAction implements DungeonUpdateAction {
   private final MapRoomType field1;
   private final Vector2i field2;
   private final Vector2i field3;

   public EarlyInDoorAction(MapRoomType hologramstype51, Vector2i vector2i2, Vector2i vector2i3) {
      this.field1 = hologramstype51;
      this.field2 = vector2i2;
      this.field3 = vector2i3;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setEarlyInDoor(
            EarlyInDoor.newBuilder()
               .setType(this.field1.toProto())
               .setPosition1(ProtoConverter.method17(this.field2))
               .setPosition2(ProtoConverter.method17(this.field3))
               .build()
         )
         .build();
   }

   @Override
   public void method2(DungeonStateTracker holograms2_51) {
      if (holograms2_51.field24 == null) {
         holograms2_51.method22(
            new RoomStateHistory(
               holograms2_51, MapRoomType.CLEAR, WorldPosition.method1(this.field2.x, this.field2.y, holograms2_51), WorldPosition.method1(this.field3.x, this.field3.y, holograms2_51)
            )
         );
      }
   }

   public static EarlyInDoorAction method3(DungeonUpdate dungeonupdate0) {
      if (!dungeonupdate0.hasEarlyInDoor()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      EarlyInDoor earlyindoor1 = dungeonupdate0.getEarlyInDoor();
      return new EarlyInDoorAction(
         MapRoomType.fromProto(earlyindoor1.getType()), ProtoConverter.method18(earlyindoor1.getPosition1()), ProtoConverter.method18(earlyindoor1.getPosition2())
      );
   }

   public MapRoomType method4() {
      return this.field1;
   }

   public Vector2i method5() {
      return this.field2;
   }

   public Vector2i method6() {
      return this.field3;
   }
}
