package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.RoomDetection;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class HologramsType25 implements Holograms_2 {
   private final Vector2i field1;
   private final String field2;
   private final Vector3i field3;
   private final HologramsType_3 field4;

   public HologramsType25(Vector2i var1, String var2, Vector3i var3, HologramsType_3 var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setRoomDetection(
            RoomDetection.newBuilder()
               .setPosition(ThreadModuleDump66.method17(this.field1))
               .setHash(this.field2)
               .setOrigin(ThreadModuleDump66.method19(this.field3))
               .setRotation(this.field4.toProto())
               .build()
         )
         .build();
   }

   @Override
   public void method2(Holograms2_5 var1) {
      Holograms4Iterator var2 = var1.method13(this.field1.x, this.field1.y);
      if (var2 != null && (!var2.method23().isPresent() || var2.method23().get().method26() == null)) {
         Holograms var3 = Holograms_4.method4(this.field2);
         var2.method1(new Holograms3(var3, this.field2, Bridge.method8().method4(this.field3.x, this.field3.y, this.field3.z), this.field4));
         if (var3 != null) {
            var2.method16(var3.roomType());
            if (var1.field24 == null && var2.method12() == HologramsType.ONE_BY_ONE && !var2.method29().isEmpty()) {
               var2.method29().get(0).method1(var3.roomType());
            }
         }
      }
   }

   public static HologramsType25 method3(DungeonUpdate var0) {
      if (!var0.hasRoomDetection()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      RoomDetection var1 = var0.getRoomDetection();
      return new HologramsType25(
         ThreadModuleDump66.method18(var1.getPosition()),
         var1.getHash(),
         ThreadModuleDump66.method20(var1.getOrigin()),
         HologramsType_3.fromProto(var1.getRotation())
      );
   }

   public Vector2i method4() {
      return this.field1;
   }

   public String hash() {
      return this.field2;
   }

   public Vector3i method5() {
      return this.field3;
   }

   public HologramsType_3 method6() {
      return this.field4;
   }
}
