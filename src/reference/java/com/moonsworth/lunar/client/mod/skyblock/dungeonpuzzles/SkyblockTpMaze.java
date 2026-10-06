package com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.PuzzleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventTeleportBase.EventTeleportPre;
import com.moonsworth.lunar.client.event.mixin.gui.EventTeleportBase.EventTeleportPost;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.math.LineMath;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.hud.Corner;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class SkyblockTpMaze extends AbstractFeature {
   private final List<Line2D> field8 = new ArrayList<>();
   private final Set<AxisAlignedBBBridge> field9 = new HashSet<>();
   private Vec3iBridge field10;
   private EventTeleportPre field11 = new EventTeleportPre(0.0, 0.0, 0.0, 0.0F, 0.0F);
   private EventTeleportPost field12 = new EventTeleportPost(0.0, 0.0, 0.0, 0.0F, 0.0F);

   public SkyblockTpMaze(SkyblockDungeonPuzzles skyblockdungeonpuzzles1, ToggleOption lightingextension4432) {
      super(true);
      this.method5(ModTraits.field16, ChildModBinding.method4(false, skyblockdungeonpuzzles1));
      this.method5(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method51(this::clear);
      this.handle(EventTeleportPre.class, this::method3);
      this.handle(HudRenderLegacyEvent.class, this::method6);
      this.handle(EventTeleportPost.class, this::method4);
      this.handle(EventWorldChange.class, arg1x -> this.clear());
   }

   public String getId() {
      return "SKYBLOCK_TP_MAZE";
   }

   protected void method1(boolean flag1) {
   }

   public void method3(boolean flag1) {
      if (flag1) {
         SkyblockDungeonPuzzles skyblockdungeonpuzzles2 = (SkyblockDungeonPuzzles)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         skyblockdungeonpuzzles2.method13();
      }
   }

   public void clear() {
      this.field10 = null;
      this.field8.clear();
      this.field9.clear();
   }

   private void method3(EventTeleportPre data151) {
      SkyblockDungeonPuzzles skyblockdungeonpuzzles2 = (SkyblockDungeonPuzzles)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      DungeonRoomTracker holograms4iterator3 = skyblockdungeonpuzzles2.method47();
      if (holograms4iterator3 != null && holograms4iterator3.method25() == PuzzleType.TP_MAZE) {
         Bridge5Extension_5 bridge5extension_54 = Ref.method7();
         WorldBridgeExtension itemcounter6extension5 = Ref.method8();
         if (bridge5extension_54 != null && itemcounter6extension5 != null) {
            if (!data151.equals(this.field11)) {
               this.field11 = data151;
               Horsestats20Extension2 horsestats20extension26 = Bridge.method8()
                  .method4((int)Math.floor(bridge5extension_54.bridge$getPosX()), (int)Math.floor(bridge5extension_54.bridge$getPosY()), (int)Math.floor(bridge5extension_54.bridge$getPosZ()));
               Horsestats20Extension2 horsestats20extension27 = Bridge.method8().method4((int)Math.floor(data151.getX()), (int)Math.floor(data151.getY()), (int)Math.floor(data151.getZ()));
               Bridge3_23 bridge3_238 = itemcounter6extension5.method4(horsestats20extension26);
               Bridge3_23 bridge3_239 = itemcounter6extension5.method4(horsestats20extension27);
               BlocksBridge bridge_5610 = Bridge.method34();
               if (bridge3_239 == bridge_5610.method35() && bridge3_238 == bridge_5610.method36()) {
                  this.field9.add(bridge3_238.bridge$getAABB(itemcounter6extension5, horsestats20extension26).method5(horsestats20extension26).method11(0.01F));

                  for (Corner threadmoduledumptype414 : Corner.values()) {
                     Horsestats20Extension2 horsestats20extension215 = Bridge.method8()
                        .method4(horsestats20extension27.bridge$getX() + threadmoduledumptype414.offsetX(), horsestats20extension27.bridge$getY(), horsestats20extension27.bridge$getZ() + threadmoduledumptype414.offsetY());
                     Bridge3_23 bridge3_2316 = itemcounter6extension5.method4(horsestats20extension215);
                     if (bridge3_2316 == bridge_5610.method36()) {
                        this.field9.add(bridge3_2316.bridge$getAABB(itemcounter6extension5, horsestats20extension215).method5(horsestats20extension215).method11(0.01F));
                        break;
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventTeleportPost data161) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (bridge5extension_52 != null && itemcounter6extension3 != null) {
         SkyblockDungeonPuzzles skyblockdungeonpuzzles4 = (SkyblockDungeonPuzzles)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         DungeonRoomTracker holograms4iterator5 = skyblockdungeonpuzzles4.method47();
         if (holograms4iterator5 != null && holograms4iterator5.method25() == PuzzleType.TP_MAZE) {
            if (!data161.equals(this.field12)) {
               this.method5(bridge5extension_52, itemcounter6extension3);
            }

            this.field12 = data161;
         }
      }
   }

   private void method5(@NotNull Bridge6_10 bridge6_101, @NotNull Itemcounter6 itemcounter62) {
      Vector3d vector3d3 = bridge6_101.bridge$getEyePosition().method6();
      Vector3d vector3d4 = bridge6_101.method19().method6();
      double value5 = 64.0;
      Vector3d vector3d7 = new Vector3d(vector3d3.x() + vector3d4.x() * value5, vector3d3.y() + vector3d4.y() * value5, vector3d3.z() + vector3d4.z() * value5);
      this.field8.add(0, new Double(vector3d3.x(), vector3d3.z(), vector3d7.x(), vector3d7.z()));
      if (this.field10 == null && this.field8.size() >= 2) {
         Line2D line2d8 = null;

         for (Line2D line2d10 : this.field8) {
            if (line2d8 == null) {
               line2d8 = line2d10;
            } else {
               Optional optional11 = LineMath.method3(line2d10, line2d8);
               if (!optional11.isEmpty()) {
                  Point2D point2d12 = (Point2D)optional11.get();
                  Vector3i vector3i13 = new Vector3i((int)Math.floor(point2d12.getX()), 69, (int)Math.floor(point2d12.getY()));
                  BlocksBridge bridge_5614 = Bridge.method34();

                  for (Corner threadmoduledumptype418 : Corner.values()) {
                     Horsestats20Extension2 horsestats20extension219 = Bridge.method8().method4(vector3i13.x() + threadmoduledumptype418.offsetX(), vector3i13.y(), vector3i13.z() + threadmoduledumptype418.offsetY());
                     if (itemcounter62.method4(horsestats20extension219) == bridge_5614.method36()) {
                        this.field10 = horsestats20extension219;
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(HudRenderLegacyEvent highlightimpl21) {
      SkyblockDungeonPuzzles skyblockdungeonpuzzles2 = (SkyblockDungeonPuzzles)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      DungeonRoomTracker holograms4iterator3 = skyblockdungeonpuzzles2.method47();
      if (holograms4iterator3 != null && holograms4iterator3.method25() == PuzzleType.TP_MAZE) {
         WorldBridgeExtension itemcounter6extension4 = Ref.method8();
         if (itemcounter6extension4 != null) {
            AbstractRenderContext bridgeextension_95 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_436 = Ref.method13();
            bridgeextension_95.push();
            bridgeextension_95.translate(-bridge2_436.bridge$renderPosX(), -bridge2_436.bridge$renderPosY(), -bridge2_436.bridge$renderPosZ());
            DrawBufferBridge bridge2_327 = bridgeextension_95.method10(LunarRenderTypes.field15);
            bridge2_327.method1();

            for (AxisAlignedBBBridge horsestats129 : this.field9) {
               WorldRenderUtils.fillBox(
                  bridge2_327,
                  horsestats129.bridge$getMinX(),
                  horsestats129.bridge$getMinY(),
                  horsestats129.bridge$getMinZ(),
                  horsestats129.bridge$getMaxX(),
                  horsestats129.bridge$getMaxY(),
                  horsestats129.bridge$getMaxZ(),
                  587137024
               );
            }

            bridge2_327.method17(BufferMode.BATCHED);
            BufferBuilderBridge bridge_2811 = bridgeextension_95.method11((Float)Ref.method4().method40().method82().method19().get());

            for (AxisAlignedBBBridge horsestats1210 : this.field9) {
               WorldRenderUtils.drawBoxOutline(
                  bridge_2811,
                  horsestats1210.bridge$getMinX(),
                  horsestats1210.bridge$getMinY(),
                  horsestats1210.bridge$getMinZ(),
                  horsestats1210.bridge$getMaxX(),
                  horsestats1210.bridge$getMaxY(),
                  horsestats1210.bridge$getMaxZ(),
                  -65536
               );
            }

            bridge_2811.end();
            if (this.field10 != null) {
               AxisAlignedBBBridge horsestats1213 = itemcounter6extension4.method4(this.field10).bridge$getAABB(itemcounter6extension4, this.field10);
               if (horsestats1213 == null) {
                  return;
               }

               horsestats1213 = horsestats1213.method5(this.field10).method11(0.01F);
               WorldRenderUtils.drawFancyBox(bridgeextension_95, horsestats1213, 570490624, true);
            }

            bridgeextension_95.pop();
         }
      }
   }
}
