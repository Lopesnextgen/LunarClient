package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.lunarclient.websocket.skyblock.v1.BroadcastDungeonUpdateRequest;
import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.DungeonUpdatePush;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType22;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType23;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType24;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType25;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType26;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.UUID;

public class Holograms_3 {
   private final Holograms2_5 field1;

   public Holograms_3(Holograms2_5 var1) {
      this.field1 = var1;
   }

   public void method1(DungeonUpdatePush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getSenderUuid());
      if (var2 != null && !var2.equals(this.field1.method29().getUuid()) && !this.field1.getPlayer(var2).isEmpty()) {
         DungeonUpdate var3 = var1.getUpdate();

         try {
            switch (var3.getContentsCase()) {
               case EARLY_IN_DOOR:
                  HologramsType22.method3(var3).method2(this.field1);
                  break;
               case EARLY_IN_ROOM:
                  HologramsType24.method3(var3).method2(this.field1);
                  break;
               case EARLY_IN_UNKNOWN:
                  HologramsType26.method3(var3).method2(this.field1);
                  break;
               case ROOM_DETECTION:
                  HologramsType25.method3(var3).method2(this.field1);
                  break;
               case ROOM_SECRETS:
                  HologramsType23.method3(var3).method2(this.field1);
            }
         } catch (Exception var5) {
            Inventorymod2.method5(var5, "DungeonSyncing");
         }
      }
   }

   public void method2(Holograms_2 var1) {
      ThreadModuleDump63.method5()
         .ifPresent(
            var1x -> var1x.method106().broadcastDungeonUpdate(null, BroadcastDungeonUpdateRequest.newBuilder().setUpdate(var1.method1()).build(), var0x -> {})
         );
   }
}
