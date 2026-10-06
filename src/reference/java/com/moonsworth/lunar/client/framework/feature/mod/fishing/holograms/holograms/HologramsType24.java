package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.EarlyInRoom;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.Arrays;
import org.joml.Vector2i;

public class HologramsType24 implements Holograms_2 {
   private final Vector2i[] field1;
   private final HologramsType5 field2;

   public HologramsType24(Vector2i[] var1, HologramsType5 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setEarlyInRoom(
            EarlyInRoom.newBuilder()
               .addAllComponents(Arrays.stream(this.field1).map(ThreadModuleDump66::method17).toList())
               .setType(this.field2.toProto())
               .build()
         )
         .build();
   }

   @Override
   public void method2(Holograms2_5 var1) {
      Holograms4Iterator var2 = new Holograms4Iterator(var1, this.field2);

      for (Vector2i var6 : this.field1) {
         Holograms4Iterator var7 = var1.method13(var6.x, var6.y);
         if (var7 != null) {
            var2 = var7;
            var2.method17();
            var2.method16(this.field2);
            break;
         }
      }

      var2.method10(HologramsType2.ADJACENT);

      for (Vector2i var11 : this.field1) {
         var2.method5(Nameplate4.method1(var11.x, var11.y, var1));
      }
   }

   public static HologramsType24 method3(DungeonUpdate dungeonUpdate) {
      if (!dungeonUpdate.hasEarlyInRoom()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      EarlyInRoom var1 = dungeonUpdate.getEarlyInRoom();
      return new HologramsType24(
         var1.getComponentsList().stream().map(ThreadModuleDump66::method18).toArray(Vector2i[]::new), HologramsType5.fromProto(var1.getType())
      );
   }

   public Vector2i[] method4() {
      return this.field1;
   }

   public HologramsType5 method5() {
      return this.field2;
   }
}
