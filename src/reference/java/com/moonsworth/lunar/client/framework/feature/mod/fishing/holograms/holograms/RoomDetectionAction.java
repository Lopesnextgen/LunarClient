package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonUpdate;
import com.lunarclient.websocket.skyblock.v1.RoomDetection;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomRotation;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonUpdateAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomTemplateDetector;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class RoomDetectionAction implements DungeonUpdateAction {
   private final Vector2i field1;
   private final String field2;
   private final Vector3i field3;
   private final RoomRotation field4;

   public RoomDetectionAction(Vector2i vector2i1, String text2, Vector3i vector3i3, RoomRotation hologramstype_34) {
      this.field1 = vector2i1;
      this.field2 = text2;
      this.field3 = vector3i3;
      this.field4 = hologramstype_34;
   }

   @Override
   public DungeonUpdate method1() {
      return DungeonUpdate.newBuilder()
         .setRoomDetection(
            RoomTemplateDetector.newBuilder()
               .setPosition(ProtoConverter.method17(this.field1))
               .setHash(this.field2)
               .setOrigin(ProtoConverter.method19(this.field3))
               .setRotation(this.field4.toProto())
               .build()
         )
         .build();
   }

   @Override
   public void method2(DungeonStateTracker holograms2_51) {
      DungeonRoomTracker holograms4iterator2 = holograms2_51.method13(this.field1.x, this.field1.y);
      if (holograms4iterator2 != null && (!holograms4iterator2.method23().isPresent() || holograms4iterator2.method23().get().method26() == null)) {
         DungeonRoom holograms3 = RoomTemplateDetector.method4(this.field2);
         holograms4iterator2.method1(new RoomInstance(holograms3, this.field2, Bridge.method8().method4(this.field3.x, this.field3.y, this.field3.z), this.field4));
         if (holograms3 != null) {
            holograms4iterator2.method16(holograms3.roomType());
            if (holograms2_51.field24 == null && holograms4iterator2.method12() == DetectedRoomShape.ONE_BY_ONE && !holograms4iterator2.method29().isEmpty()) {
               holograms4iterator2.method29().get(0).method1(holograms3.roomType());
            }
         }
      }
   }

   public static RoomDetectionAction method3(DungeonUpdate dungeonupdate0) {
      if (!dungeonupdate0.hasRoomDetection()) {
         throw new IllegalStateException("Invalid DungeonUpdate object");
      }

      RoomTemplateDetector roomdetection1 = dungeonupdate0.getRoomDetection();
      return new RoomDetectionAction(
         ProtoConverter.method18(roomdetection1.getPosition()),
         roomdetection1.getHash(),
         ProtoConverter.method20(roomdetection1.getOrigin()),
         RoomRotation.fromProto(roomdetection1.getRotation())
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

   public RoomRotation method6() {
      return this.field4;
   }
}
