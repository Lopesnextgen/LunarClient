package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.RoomSecrets;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonUpdateAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import org.joml.Vector2i;

public class RoomSecretsAction implements DungeonUpdateAction {
   private final Vector2i field1;
   private final int field2;
   private final int field3;

   public RoomSecretsAction(Vector2i vector2i1, int value, int value2) {
      this.field1 = vector2i1;
      this.field2 = value;
      this.field3 = value2;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setRoomSecrets(RoomSecrets.newBuilder().setPosition(ProtoConverter.method17(this.field1)).setCurrent(this.field2).setMax(this.field3).build())
         .build();
   }

   @Override
   public void method2(DungeonStateTracker holograms2_51) {
      DungeonRoomTracker holograms4iterator2 = holograms2_51.method13(this.field1.x, this.field1.y);
      if (holograms4iterator2 != null) {
         holograms4iterator2.method13(this.field2);
         holograms4iterator2.method15(this.field3);
      }
   }

   public static RoomSecretsAction method3(DungeonUpdate dungeonupdate0) {
      if (!dungeonupdate0.hasRoomSecrets()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      RoomSecrets roomsecrets1 = dungeonupdate0.getRoomSecrets();
      return new RoomSecretsAction(ProtoConverter.method18(roomsecrets1.getPosition()), roomsecrets1.getCurrent(), roomsecrets1.getMax());
   }

   public Vector2i method4() {
      return this.field1;
   }

   public int method5() {
      return this.field2;
   }

   public int max() {
      return this.field3;
   }
}
