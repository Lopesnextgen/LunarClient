package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.MetalDetectorLocationsDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.HighlightIterator;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import org.joml.Vector3ic;

public class SkyblockMetalDetectorDataGen extends AbstractFeature {
   private final ScoreboardListener field8 = (ScoreboardListener)this.method63(ScoreboardListener.class);
   public static final Gson field9 = new GsonBuilder().registerTypeAdapter(MetalDetectorTreasureMap.class, new MetalDetectorLocationsDeserializer()).create();
   private final Map<MetalDetectorTreasureType, BridgeExtension> field10 = new EnumMap<>(MetalDetectorTreasureType.class);
   private final Set<Vec3iBridge> field11 = new LinkedHashSet<>();
   private MetalDetectorTreasureMap field12;

   public SkyblockMetalDetectorDataGen(SkyblockDebugMod skyblockdebugmod1, ToggleOption lightingextension4432) {
      super(true);
      this.method4(ModTraits.field16, ChildModBinding.method4(false, skyblockdebugmod1));
      this.method4(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(HighlightIterator.class, this::method6);
      this.handle(EventWorldChange.class, this::method5);
      this.handle(BlockUpdate.class, this::method4);
      this.handle(HudRenderLegacyEventAlt.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventCommand.class, this::method2);
      this.handle(EventEntitySpawn.class, this::method7);
      new Thread(() -> {
         Path path1x = LunarConstants.field13.resolve("metal-detector-locations.json");
         if (path1x.toFile().exists()) {
            try {
               String text2x = Files.readString(path1x);
               this.field12 = (MetalDetectorTreasureMap)field9.fromJson(text2x, MetalDetectorTreasureMap.class);
            } catch (Exception exception3) {
               CrashReporter.method5(exception3, "Load Metal Detector Locations");
            }
         }
      }).start();
   }

   public String getId() {
      return "SKYBLOCK_METAL_DETECTOR_DATA_GEN";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventCommand highlightimpl41) {
      if (highlightimpl41.getCommand().equals("/sbdm metalDetector generate")) {
         highlightimpl41.cancel();
         ClipboardUtils.method2(LunarConstants.field22.toJson(this.method13()));
         SkyBlockChat.method1("metal detector relative positions saved to clipboard");
      } else if (highlightimpl41.getCommand().equals("/sbdm metalDetector addLookingAt")) {
         highlightimpl41.cancel();
         if (this.field10.size() != 4) {
            SkyBlockChat.method1("Haven't cached all of the npc's yet");
         } else {
            WorldBridgeExtension itemcounter6extension2 = Ref.method8();
            if (itemcounter6extension2 != null) {
               MovingObjectPositionBridge horsestats213 = Ref.method3().bridge$getObjectMouseOver();
               if (horsestats213 != null) {
                  if (horsestats213.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK)) {
                     Horsestats20Extension2 horsestats20extension24 = horsestats213.bridge$getBlockPosition();
                     if (itemcounter6extension2.method4(horsestats20extension24) != Bridge.method34().method23()) {
                        SkyBlockChat.method1("You are not looking at a chest, try again");
                     } else {
                        for (Entry entry6 : this.field10.entrySet()) {
                           MetalDetectorTreasureType fishingtype27 = (MetalDetectorTreasureType)entry6.getKey();
                           BridgeExtension bridgeextension8 = (BridgeExtension)entry6.getValue();
                           Horsestats20Extension2 horsestats20extension29 = bridgeextension8.bridge$getBlockPos();
                           int number10 = horsestats20extension24.bridge$getX() - horsestats20extension29.bridge$getX();
                           int number11 = horsestats20extension24.bridge$getY() - horsestats20extension29.bridge$getY();
                           int number12 = horsestats20extension24.bridge$getZ() - horsestats20extension29.bridge$getZ();
                           SkyBlockChat.method1("Relative to " + fishingtype27.getId() + ": " + number10 + ", " + number11 + ", " + number12);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method3(HudRenderLegacyEventAlt highlightimpl41) {
      AbstractRenderContext bridgeextension_92 = highlightimpl41.method3();
      bridgeextension_92.push();
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());

      for (Vec3iBridge horsestats205 : this.field11) {
         WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_92, horsestats205.bridge$toJoml(), 570490624, true);
      }

      for (BridgeExtension bridgeextension8 : Ref.method8().bridge$getEntities()) {
         if (bridgeextension8 instanceof Bridge5Extension2 bridge5extension26) {
            WorldRenderUtils.drawComponent(
               bridgeextension_92,
               Component.text(bridge5extension26.bridge$getLocationSkin().bridge$getPath()),
               bridge5extension26.method4(highlightimpl41.method5()),
               bridge5extension26.method19(highlightimpl41.method5()) + 1.0,
               bridge5extension26.method10(highlightimpl41.method5()),
               true,
               1.0F,
               false
            );
         }
      }

      bridgeextension_92.pop();
   }

   private void method4(BlockUpdate data1) {
      ImmutableList list2 = this.field8.method6();
      boolean flag3 = false;

      for (String text5 : list2) {
         if (text5.contains("Divan")) {
            flag3 = true;
            break;
         }
      }

      if (flag3) {
         if (data1.getBlock() == Bridge.method34().method23() && !this.field11.contains(data1.method1())) {
            SkyBlockChat.method1("New chest, please report to dulkir");
            this.field11.add(data1.method1());
            JsonObject json6 = this.method13();
            if (json6 == null) {
               SkyBlockChat.method1("couldn't generate new data, likely hasn't loaded all entities yet");
            }

            ClipboardUtils.method2(LunarConstants.field22.toJson(json6));
            SkyBlockChat.method1("A new version of the json has been automatically been saved to your clipboard for ease of use");
         }
      }
   }

   private void method5(EventWorldChange data31) {
      this.field10.clear();
      this.field11.clear();
   }

   private void method6(HighlightIterator highlightiterator1) {
      if (IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS) {
         for (MetalDetectorTreasureType fishingtype25 : MetalDetectorTreasureType.values()) {
            if (highlightiterator1.getSkinLocation().bridge$getPath().equals(fishingtype25.getSkinPath())) {
               System.out.println("Found: " + fishingtype25.getId());
               BridgeExtension bridgeextension6 = highlightiterator1.method1();
               if (bridgeextension6 != null) {
                  this.field10.put(fishingtype25, bridgeextension6);
                  if (this.field12 != null) {
                     for (Vector3ic vector3ic8 : (Set)this.field12.method1().get(fishingtype25)) {
                        this.field11.add(bridgeextension6.bridge$getBlockPos().bridge$add(vector3ic8));
                     }
                  }
               }
            }
         }
      }
   }

   private void method7(EventEntitySpawn highlightimpl6_21) {
      if (highlightimpl6_21.field1 instanceof Bridge5Extension2 bridge5extension22) {
         String text10 = bridge5extension22.bridge$getLocationSkin().bridge$getPath();

         for (MetalDetectorTreasureType fishingtype27 : MetalDetectorTreasureType.values()) {
            if (text10.equals(fishingtype27.getSkinPath())) {
               System.out.println("onSpawn Found: " + fishingtype27.getId());
               this.field10.put(fishingtype27, bridge5extension22);
               if (this.field12 != null) {
                  for (Vector3ic vector3ic9 : (Set)this.field12.method1().get(fishingtype27)) {
                     this.field11.add(bridge5extension22.bridge$getBlockPos().bridge$add(vector3ic9));
                  }
               }
            }
         }
      }
   }

   private JsonObject method13() {
      if (this.field10.size() != 4) {
         return null;
      }

      JsonObject json1 = new JsonObject();

      for (Entry entry3 : this.field10.entrySet()) {
         MetalDetectorTreasureType fishingtype24 = (MetalDetectorTreasureType)entry3.getKey();
         BridgeExtension bridgeextension5 = (BridgeExtension)entry3.getValue();
         Horsestats20Extension2 horsestats20extension26 = bridgeextension5.bridge$getBlockPos();
         JsonArray array7 = new JsonArray();

         for (Vec3iBridge horsestats209 : this.field11) {
            JsonObject json10 = new JsonObject();
            json10.addProperty("x", horsestats209.bridge$getX() - horsestats20extension26.bridge$getX());
            json10.addProperty("y", horsestats209.bridge$getY() - horsestats20extension26.bridge$getY());
            json10.addProperty("z", horsestats209.bridge$getZ() - horsestats20extension26.bridge$getZ());
            array7.add(json10);
         }

         json1.add(fishingtype24.getId(), array7);
      }

      return json1;
   }
}
