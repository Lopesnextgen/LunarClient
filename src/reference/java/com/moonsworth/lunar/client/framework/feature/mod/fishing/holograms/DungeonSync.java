package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.lunarclient.websocket.skyblock.v1.BroadcastDungeonUpdateRequest;
import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.DungeonUpdatePush;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.EarlyInDoorAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomSecretsAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.EarlyInRoomAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomDetectionAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.EarlyInUnknownAction;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.UUID;

public class DungeonSync {
   private final DungeonStateTracker field1;

   public DungeonSync(DungeonStateTracker holograms2_51) {
      this.field1 = holograms2_51;
   }

   public void method1(DungeonUpdatePush dungeonupdatepush1) {
      UUID uuid2 = ProtoConverter.method1(dungeonupdatepush1.getSenderUuid());
      if (uuid2 != null && !uuid2.equals(this.field1.method29().getUuid()) && !this.field1.getPlayer(uuid2).isEmpty()) {
         DungeonUpdate dungeonupdate3 = dungeonupdatepush1.getUpdate();

         try {
            switch (dungeonupdate3.getContentsCase()) {
               case EARLY_IN_DOOR:
                  EarlyInDoorAction.method3(dungeonupdate3).method2(this.field1);
                  break;
               case EARLY_IN_ROOM:
                  EarlyInRoomAction.method3(dungeonupdate3).method2(this.field1);
                  break;
               case EARLY_IN_UNKNOWN:
                  EarlyInUnknownAction.method3(dungeonupdate3).method2(this.field1);
                  break;
               case ROOM_DETECTION:
                  RoomDetectionAction.method3(dungeonupdate3).method2(this.field1);
                  break;
               case ROOM_SECRETS:
                  RoomSecretsAction.method3(dungeonupdate3).method2(this.field1);
            }
         } catch (Exception exception5) {
            CrashReporter.method5(exception5, "DungeonSyncing");
         }
      }
   }

   public void method2(DungeonUpdateAction holograms_21) {
      Ref.method5()
         .ifPresent(
            arg1x -> arg1x.method106().broadcastDungeonUpdate(null, BroadcastDungeonUpdateRequest.newBuilder().setUpdate(holograms_21.method1()).build(), arg0x -> {})
         );
   }
}
