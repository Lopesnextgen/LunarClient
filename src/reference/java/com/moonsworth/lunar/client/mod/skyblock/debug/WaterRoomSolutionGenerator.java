package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.WaterBoardSolutions.WaterBoardMaterial;
import com.moonsworth.lunar.client.event.mixin.EventCommand;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3i;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles.SkyblockWaterRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles.SkyblockDungeonPuzzles;

public class WaterRoomSolutionGenerator extends AbstractFeature {
   private HudTimer field8;
   private JsonArray field9;
   private String field10;
   private final Map<WaterBoardMaterial, Vector3i> field11 = new HashMap<>();

   public WaterRoomSolutionGenerator(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.handle(BlockUpdate.class, this::method2);
      this.handle(EventCommand.class, this::method1);
   }

   private void method1(EventCommand highlightimpl41) {
      if (highlightimpl41.getCommand().startsWith("/sbdm-water")) {
         highlightimpl41.cancel();
         if (highlightimpl41.get(0).equals("help")) {
            SkyBlockChat.method1("Waterboard solution Generator:");
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - /sbdm-water <start/stop> ").color(NamedTextColor.WHITE))
                  .append(Component.text(": records user inputs and spits out a json when done").color(NamedTextColor.GRAY))
            );
         }

         if (highlightimpl41.get(0).equals("start")) {
            this.start();
         }

         if (highlightimpl41.get(0).equals("stop")) {
            this.stop();
         }
      }
   }

   private void start() {
      Skyblock skyblock1 = Ref.method4().method40().method82();
      SkyblockDungeonPuzzles skyblockdungeonpuzzles2 = skyblock1.method109();
      ToggleOption lightingextension4433 = skyblockdungeonpuzzles2.method30();
      ToggleOption lightingextension4434 = skyblockdungeonpuzzles2.method45();
      if (skyblock1.isEnabled() && skyblockdungeonpuzzles2.isEnabled() && (Boolean)lightingextension4433.get() && (Boolean)lightingextension4434.get()) {
         SkyblockWaterRoom skyblockwaterroom5 = skyblockdungeonpuzzles2.method21();
         int number6 = skyblockwaterroom5.method19();
         int number7 = skyblockwaterroom5.method21();
         if (number6 > 0 && number7 > 0) {
            for (Entry entry9 : skyblockwaterroom5.method17().entrySet()) {
               Vector3i vector3i10 = (Vector3i)entry9.getValue();
               if (Ref.method8().method5(vector3i10).bridge$isFlippedLever(vector3i10.x, vector3i10.y, vector3i10.z)) {
                  SkyBlockChat.method1("Please unflip all levers before starting the recording.");
                  return;
               }
            }

            this.field10 = number6 + "-" + number7;
            this.field9 = new JsonArray();
            this.field11.putAll(skyblockwaterroom5.method17());
            this.field8 = new com.moonsworth.lunar.client.framework.hud.HudTimer.Data().method3().method5(0L).method6(true).method7();
            SkyBlockChat.method1("Recording Inputs!");
         } else {
            SkyBlockChat.method1("You're either not in waterboard, or the puzzle is not in a valid starting position. Please try again.");
         }
      } else {
         SkyBlockChat.method1("Please turn on waterboard solver and make sure that the fast solutions are on as well.");
      }
   }

   private void stop() {
      JsonObject json1 = new JsonObject();
      json1.add(this.field10, this.field9);
      ClipboardUtils.method2(LunarConstants.field22.toJson(json1));
      this.clear();
      SkyBlockChat.method1("Results copied!");
   }

   private void clear() {
      this.field8 = null;
      this.field11.clear();
      this.field10 = null;
      this.field9 = null;
   }

   private void method2(BlockUpdate data1) {
      if (this.field8 != null) {
         Vector3i vector3i2 = data1.IIRHOIHIOOOCIIICROHOROCIOHHORC().bridge$toJoml();

         for (WaterBoardMaterial type26 : WaterBoardMaterial.values()) {
            Vector3i vector3i7 = this.field11.get(type26);
            if (vector3i7 != null && vector3i7.equals(vector3i2)) {
               JsonObject json8 = new JsonObject();
               json8.addProperty("a", type26.getId());
               json8.addProperty("b", this.field8.get());
               this.field9.add(json8);
               if (type26 == WaterBoardMaterial.WATER && this.field8.isPaused()) {
                  this.field8.method2();
               }
            }
         }
      }
   }

   @ConstantName
   public String getId() {
      return "WATER_ROOM_SOLUTION_GENERATOR";
   }
}
