package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.EarlyInDoor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import org.joml.Vector2i;

public class HologramsType22 implements Holograms_2 {
   private final HologramsType5 field1;
   private final Vector2i field2;
   private final Vector2i field3;

   public HologramsType22(HologramsType5 var1, Vector2i vector2i, Vector2i vector2i2) {
      this.field1 = var1;
      this.field2 = vector2i;
      this.field3 = vector2i2;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setEarlyInDoor(
            EarlyInDoor.newBuilder()
               .setType(this.field1.toProto())
               .setPosition1(ThreadModuleDump66.method17(this.field2))
               .setPosition2(ThreadModuleDump66.method17(this.field3))
               .build()
         )
         .build();
   }

   @Override
   public void method2(Holograms2_5 var1) {
      if (var1.field24 == null) {
         var1.method22(
            new Rewindhandlers(
               var1, HologramsType5.CLEAR, Nameplate4.method1(this.field2.x, this.field2.y, var1), Nameplate4.method1(this.field3.x, this.field3.y, var1)
            )
         );
      }
   }

   public static HologramsType22 method3(DungeonUpdate dungeonUpdate) {
      if (!dungeonUpdate.hasEarlyInDoor()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      EarlyInDoor var1 = dungeonUpdate.getEarlyInDoor();
      return new HologramsType22(
         HologramsType5.fromProto(var1.getType()), ThreadModuleDump66.method18(var1.getPosition1()), ThreadModuleDump66.method18(var1.getPosition2())
      );
   }

   public HologramsType5 method4() {
      return this.field1;
   }

   public Vector2i method5() {
      return this.field2;
   }

   public Vector2i method6() {
      return this.field3;
   }
}
