package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.EarlyInRoom;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapRoomType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonUpdateAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.Arrays;
import org.joml.Vector2i;

public class EarlyInRoomAction implements DungeonUpdateAction {
   private final Vector2i[] field1;
   private final MapRoomType field2;

   public EarlyInRoomAction(Vector2i[] items1, MapRoomType hologramstype52) {
      this.field1 = items1;
      this.field2 = hologramstype52;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setEarlyInRoom(
            EarlyInRoom.newBuilder()
               .addAllComponents(Arrays.stream(this.field1).map(ProtoConverter::method17).toList())
               .setType(this.field2.toProto())
               .build()
         )
         .build();
   }

   @Override
   public void method2(DungeonStateTracker holograms2_51) {
      DungeonRoomTracker holograms4iterator2 = new DungeonRoomTracker(holograms2_51, this.field2);

      for (Vector2i vector2i6 : this.field1) {
         DungeonRoomTracker holograms4iterator7 = holograms2_51.method13(vector2i6.x, vector2i6.y);
         if (holograms4iterator7 != null) {
            holograms4iterator2 = holograms4iterator7;
            holograms4iterator2.method17();
            holograms4iterator2.method16(this.field2);
            break;
         }
      }

      holograms4iterator2.method10(RoomState.ADJACENT);

      for (Vector2i vector2i11 : this.field1) {
         holograms4iterator2.method5(WorldPosition.method1(vector2i11.x, vector2i11.y, holograms2_51));
      }
   }

   public static EarlyInRoomAction method3(DungeonUpdate dungeonupdate0) {
      if (!dungeonupdate0.hasEarlyInRoom()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      EarlyInRoom earlyinroom1 = dungeonupdate0.getEarlyInRoom();
      return new EarlyInRoomAction(
         earlyinroom1.getComponentsList().stream().map(ProtoConverter::method18).toArray(Vector2i[]::new), MapRoomType.fromProto(earlyinroom1.getType())
      );
   }

   public Vector2i[] method4() {
      return this.field1;
   }

   public MapRoomType method5() {
      return this.field2;
   }
}
