package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.EarlyInUnknown;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import org.joml.Vector2i;

public class HologramsType26 implements Holograms_2 {
   private final Vector2i field1;

   public HologramsType26(Vector2i var1) {
      this.field1 = var1;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder().setEarlyInUnknown(EarlyInUnknown.newBuilder().setPosition(ThreadModuleDump66.method17(this.field1)).build()).build();
   }

   @Override
   public void method2(Holograms2_5 var1) {
      if (var1.field24 == null) {
         Holograms4Iterator var2 = var1.method13(this.field1.x, this.field1.y);
         if (var2 == null) {
            Holograms4Iterator var3 = new Holograms4Iterator(var1, HologramsType5.UNKNOWN);
            var3.method10(HologramsType2.ADJACENT);
            var3.method5(Nameplate4.method1(this.field1.x, this.field1.y, var1));
         }
      }
   }

   public static HologramsType26 method3(DungeonUpdate dungeonUpdate) {
      if (!dungeonUpdate.hasEarlyInUnknown()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      } else {
         return new HologramsType26(ThreadModuleDump66.method18(dungeonUpdate.getEarlyInUnknown().getPosition()));
      }
   }

   public Vector2i method4() {
      return this.field1;
   }
}
