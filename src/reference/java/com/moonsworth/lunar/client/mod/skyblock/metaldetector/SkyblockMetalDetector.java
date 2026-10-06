package com.moonsworth.lunar.client.mod.skyblock.metaldetector;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.HighlightIterator;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joml.Vector3d;
import org.joml.Vector3ic;
import toxi.geom.Vec3D;

public class SkyblockMetalDetector extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("skyBlockMetalDetectorLine")
      .method31();
   private final Map<MetalDetectorTreasureType, BridgeExtension> field9 = new EnumMap<>(MetalDetectorTreasureType.class);
   private final Set<Vector3ic> field10 = new LinkedHashSet<>();
   private final Pattern field11 = Pattern.compile("TREASURE: ([\\d.]+)m");
   private final List<SkyblockMetalDetector.Data> field12 = new ArrayList<>();
   private int field13;
   private long field14;

   public SkyblockMetalDetector(Skyblock skyblock1) {
      super(false);
      this.method12(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method12(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
      this.method7(this::onDisable);
      this.handle(HighlightIterator.class, this::method9);
      this.handle(EventEntitySpawn.class, this::method10);
      this.handle(EventWorldChange.class, this::method8);
      this.handle(HudRenderLegacyEventAlt.class, this::method7);
      this.handle(EventActionBarMessage.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method3);
      this.handle(EventTick.class, this::method4);
   }

   private void onDisable() {
      this.method8(null);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_METAL_DETECTOR";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.field13 = EventTick.field1;
      } else {
         this.method13();
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = TextBridge.getTextContent(data1.OHCICHOROROOORHCRICORHRRCRCCHO());
      if (text2.startsWith("You found") && text2.endsWith("with your Metal Detector!")) {
         this.method13();
      }
   }

   private void method4(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         if (bridge5extension_52.bridge$getPosX() != bridge5extension_52.bridge$lastTickX()
            || bridge5extension_52.bridge$getPosY() != bridge5extension_52.bridge$lastTickY()
            || bridge5extension_52.bridge$getPosZ() != bridge5extension_52.bridge$lastTickZ()) {
            this.field13 = EventTick.field1;
         }
      }
   }

   private void method5(EventActionBarMessage data21) {
      if (EventTick.field1 - this.field13 >= 10) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            String text3 = TextBridge.getTextContent(data21.OHCICHOROROOORHCRICORHRRCRCCHO());
            Matcher matcher4 = this.field11.matcher(text3);
            if (matcher4.find()) {
               if (this.field9.isEmpty()) {
                  if (Ref.method3().bridge$getSystemTime() - this.field14 > 60000L) {
                     Skyblock skyblock14 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
                     SkyBlockChat.method1(skyblock14.method226("metalDetectorNoNPC", new Object[0]));
                     this.field14 = Ref.method3().bridge$getSystemTime();
                  }

                  return;
               }

               double value5 = Double.parseDouble(matcher4.group(1));
               int number7 = this.field12.size();
               this.method13();
               List list8 = this.field10.stream().filter(arg3x -> this.method11(arg3x, value5)).toList();
               int index9 = 0;

               for (Vector3ic vector3ic11 : list8) {
                  index9++;
                  String text12 = "Estimate";
                  if (list8.size() > 1) {
                     text12 = "Estimate " + index9 + " (" + 100 / list8.size() + "%)";
                  }

                  Waypoint guihandler213 = Waypoint.method18()
                     .method2(text12)
                     .method3(Vec3Bridge.method2(vector3ic11.x() + 0.5, vector3ic11.y() + 1.5, vector3ic11.z() + 0.5))
                     .method4(Client.method109().getWorld())
                     .method5(Ref.method8().bridge$getDimensionId())
                     .method12(WaypointStore.method19())
                     .method13(true)
                     .method19();
                  Ref.method4().method48().method6(guihandler213);
                  this.field12.add(new SkyblockMetalDetector.Data(guihandler213, vector3ic11));
               }

               if (this.field12.size() > number7) {
                  IslandUtils.playSound();
               }
            }
         }
      }
   }

   private void method13() {
      for (SkyblockMetalDetector.Data data2 : this.field12) {
         Ref.method4().method48().method9(data2.method1());
      }

      this.field12.clear();
   }

   private void method7(HudRenderLegacyEventAlt highlightimpl41) {
      EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
      AbstractRenderContext bridgeextension_93 = highlightimpl41.method3();
      bridgeextension_93.push();
      bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());

      for (SkyblockMetalDetector.Data data5 : this.field12) {
         WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_93, data5.method2());
      }

      bridgeextension_93.pop();
      if ((Boolean)this.field8.get()) {
         for (SkyblockMetalDetector.Data data7 : this.field12) {
            WorldRenderUtils.drawLineFromCamera(new Vec3D(data7.field2.x() + 0.5F, data7.field2.y() + 1, data7.field2.z() + 0.5F), bridgeextension_93, -16711936, 1.0F);
         }
      }
   }

   private void method8(EventWorldChange data31) {
      this.field9.clear();
      this.field10.clear();
      this.method13();
   }

   private void method9(HighlightIterator highlightiterator1) {
      if (IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS) {
         MetalDetectorTreasureMap fishing2 = Ref.method4().method40().method82().method15().method13();
         if (fishing2 != null) {
            for (MetalDetectorTreasureType fishingtype26 : MetalDetectorTreasureType.values()) {
               if (highlightiterator1.getSkinLocation().bridge$getPath().equals(fishingtype26.getSkinPath())) {
                  BridgeExtension bridgeextension7 = highlightiterator1.method1();
                  if (bridgeextension7 != null) {
                     if (this.field9.containsKey(fishingtype26)) {
                        return;
                     }

                     this.field9.put(fishingtype26, bridgeextension7);
                     if (this.field10.isEmpty()) {
                        for (Vector3ic vector3ic9 : (Set)fishing2.method1().get(fishingtype26)) {
                           this.field10.add(bridgeextension7.bridge$getBlockPos().bridge$toJoml().add(vector3ic9));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method10(EventEntitySpawn highlightimpl6_21) {
      MetalDetectorTreasureMap fishing2 = Ref.method4().method40().method82().method15().method13();
      if (fishing2 != null) {
         if (highlightimpl6_21.field1 instanceof Bridge5Extension2 bridge5extension23) {
            String text11 = bridge5extension23.bridge$getLocationSkin().bridge$getPath();

            for (MetalDetectorTreasureType fishingtype28 : MetalDetectorTreasureType.values()) {
               if (!text11.equals(fishingtype28.getSkinPath())) {
                  return;
               }

               if (this.field9.containsKey(fishingtype28)) {
                  return;
               }

               this.field9.put(fishingtype28, bridge5extension23);
               if (this.field10.isEmpty()) {
                  for (Vector3ic vector3ic10 : (Set)fishing2.method1().get(fishingtype28)) {
                     this.field10.add(bridge5extension23.bridge$getBlockPos().bridge$toJoml().add(vector3ic10));
                  }
               }
            }
         }
      }
   }

   public boolean method11(Vector3ic vector3ic1, double value2) {
      Vector3d vector3d4 = Ref.method7().bridge$getPosition();
      double value5 = vector3d4.distance(vector3ic1.x(), vector3ic1.y() + 1, vector3ic1.z());
      return value5 > value2 - 0.05 && value5 < value2 + 0.05;
   }

   private class Data {
      private final Waypoint field1;
      private final Vector3ic field2;

      private Data(Waypoint guihandler21, Vector3ic vector3ic2) {
         this.field1 = guihandler21;
         this.field2 = vector3ic2;
      }

      public Waypoint method1() {
         return this.field1;
      }

      public Vector3ic method2() {
         return this.field2;
      }
   }
}
