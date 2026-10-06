package com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.PuzzleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles.SkyblockDungeonPuzzles;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;
import toxi.geom.Line3D;
import toxi.geom.Vec3D;

public class SkyblockCreeperBeams extends AbstractFeature {
   private final Map<Integer, Integer> field8 = Map.of(0, 587137024, 1, 570490624, 2, 570425599, 3, 587202304);
   private final List<Vector3i> field9 = new ArrayList<>();
   private final SkyblockCreeperBeams.Data[] field10 = new SkyblockCreeperBeams.Data[4];

   public SkyblockCreeperBeams(SkyblockDungeonPuzzles skyblockdungeonpuzzles1, ToggleOption lightingextension4432) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method4(false, skyblockdungeonpuzzles1));
      this.method45(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(HudRenderLegacyEvent.class, this::method5);
   }

   public String getId() {
      return "SKYBLOCK_CREEPER_BEAMS";
   }

   protected void method1(boolean flag1) {
   }

   public void method3(boolean flag1) {
      if (flag1) {
         SkyblockDungeonPuzzles skyblockdungeonpuzzles2 = (SkyblockDungeonPuzzles)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         skyblockdungeonpuzzles2.method13();
      }
   }

   public void method3(DungeonRoomTracker holograms4iterator1) {
      this.field9.clear();
      Arrays.fill(this.field10, null);
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (itemcounter6extension2 != null) {
         BlocksBridge bridge_563 = Bridge.method34();
         int number4 = (int)((WorldPosition)holograms4iterator1.method28().get(0)).method2();
         int number5 = (int)((WorldPosition)holograms4iterator1.method28().get(0)).method3();

         for (int index6 = 69; index6 <= 85; index6++) {
            for (int index7 = 0; index7 <= 30; index7++) {
               for (int index8 = 0; index8 <= 30; index8++) {
                  Bridge3_23 bridge3_239 = itemcounter6extension2.bridge$getBlockAt(index7 + number4, index6, index8 + number5);
                  if (bridge3_239 == bridge_563.method10() || bridge3_239 == bridge_563.method37()) {
                     this.field9.add(new Vector3i(index7 + number4, index6, index8 + number5));
                  }
               }
            }
         }

         PriorityQueue priorityqueue10 = this.method4(holograms4iterator1);
         int index11 = 0;
         HashSet set12 = new HashSet();

         while (!priorityqueue10.isEmpty() && index11 < 4) {
            SkyblockCreeperBeams.Data data13 = (SkyblockCreeperBeams.Data)priorityqueue10.poll();
            if (!set12.contains(data13.field1) && !set12.contains(data13.field2)) {
               this.field10[index11] = data13;
               index11++;
               set12.add(data13.field1);
               set12.add(data13.field2);
            }
         }
      }
   }

   @NotNull
   private PriorityQueue<SkyblockCreeperBeams.Data> method4(DungeonRoomTracker holograms4iterator1) {
      PriorityQueue priorityqueue2 = new PriorityQueue();
      Vector3i vector3i3 = ((RoomInstance)holograms4iterator1.method23().get()).method4(new Vector3i(15, 76, 15));
      Vec3D vec3d4 = new Vec3D(vector3i3.x + 0.5F, vector3i3.y + 0.5F, vector3i3.z + 0.5F);

      for (int index5 = 0; index5 < this.field9.size(); index5++) {
         for (int index6 = 0; index6 < this.field9.size(); index6++) {
            if (index5 < index6) {
               Vector3i vector3i7 = this.field9.get(index5);
               Vector3i vector3i8 = this.field9.get(index6);
               Line3D line3d9 = new Line3D(
                  new Vec3D(vector3i7.x() + 0.5F, vector3i7.y() + 0.5F, vector3i7.z() + 0.5F), new Vec3D(vector3i8.x() + 0.5F, vector3i8.y() + 0.5F, vector3i8.z() + 0.5F)
               );
               Vec3D vec3d10 = line3d9.closestPointTo(vec3d4);
               float value11 = vec3d10.distanceTo(vec3d4);
               priorityqueue2.offer(new SkyblockCreeperBeams.Data(vector3i7, vector3i8, value11));
            }
         }
      }

      return priorityqueue2;
   }

   private void method5(HudRenderLegacyEvent highlightimpl21) {
      SkyblockDungeonPuzzles skyblockdungeonpuzzles2 = (SkyblockDungeonPuzzles)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      DungeonRoomTracker holograms4iterator3 = skyblockdungeonpuzzles2.method47();
      if (holograms4iterator3 != null && holograms4iterator3.method25() == PuzzleType.CREEPER_BEAMS) {
         EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
         AbstractRenderContext bridgeextension_95 = highlightimpl21.method3();
         bridgeextension_95.push();
         bridgeextension_95.translate(-bridge2_434.bridge$renderPosX(), -bridge2_434.bridge$renderPosY(), -bridge2_434.bridge$renderPosZ());

         for (int index6 = 0; index6 < this.field10.length; index6++) {
            SkyblockCreeperBeams.Data data7 = this.field10[index6];
            if (data7 != null) {
               WorldRenderUtils.highlightBlockAt(bridgeextension_95, data7.method2(), this.field8.get(index6));
               WorldRenderUtils.highlightBlockAt(bridgeextension_95, data7.method3(), this.field8.get(index6));
            }
         }

         bridgeextension_95.pop();
      }
   }

   private class Data implements Comparable<SkyblockCreeperBeams.Data> {
      private final Vector3i field1;
      private final Vector3i field2;
      private final float field3;

      private Data(Vector3i vector3i1, Vector3i vector3i2, float value3) {
         this.field1 = vector3i1;
         this.field2 = vector3i2;
         this.field3 = value3;
      }

      public int method1(SkyblockCreeperBeams.Data data1) {
         if (data1.field3 == this.field3) {
            return 0;
         } else {
            return this.field3 < data1.field3 ? -1 : 1;
         }
      }

      public Vector3i method2() {
         return this.field1;
      }

      public Vector3i method3() {
         return this.field2;
      }

      public float method4() {
         return this.field3;
      }
   }
}
