package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.RoomSecrets;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import org.joml.Vector2i;

public class HologramsType23 implements Holograms_2 {
   private final Vector2i field1;
   private final int field2;
   private final int field3;

   public HologramsType23(Vector2i var1, int var2, int value) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = value;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setRoomSecrets(RoomSecrets.newBuilder().setPosition(ThreadModuleDump66.method17(this.field1)).setCurrent(this.field2).setMax(this.field3).build())
         .build();
   }

   @Override
   public void method2(Holograms2_5 var1) {
      Holograms4Iterator var2 = var1.method13(this.field1.x, this.field1.y);
      if (var2 != null) {
         var2.method13(this.field2);
         var2.method15(this.field3);
      }
   }

   public static HologramsType23 method3(DungeonUpdate dungeonUpdate) {
      if (!dungeonUpdate.hasRoomSecrets()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      RoomSecrets var1 = dungeonUpdate.getRoomSecrets();
      return new HologramsType23(ThreadModuleDump66.method18(var1.getPosition()), var1.getCurrent(), var1.getMax());
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
