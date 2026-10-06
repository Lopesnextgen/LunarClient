package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockBossMigration implements ConfigMigration {
   public SkyblockBossMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Skyblock) {
         JsonObject json4 = json3.has("options") ? json3.getAsJsonObject("options") : null;
         if (json4 != null) {
            this.method2(json3, json4, "slayerBossTimer", false, "SKYBLOCK_BOSS_TIMER");
            this.method2(json3, json4, "slayerMiniBossAlert", false, "SKYBLOCK_MINI_BOSS_ALERT");
            this.method2(json3, json4, "inactiveEffigyWaypoints", false, "SKYBLOCK_INACTIVE_EFFIGY_DISPLAY");
            this.method2(json3, json4, "gravityWellWaypoints", false, "SKYBLOCK_GRAVITY_WELL_WAYPOINTS");
            this.method2(json3, json4, "skyblockHideHunger", true, "SKYBLOCK_HIDE_FOOD");
            this.method2(json3, json4, "skyblockHideArmor", true, "SKYBLOCK_HIDE_ARMOR");
            this.method2(json3, json4, "autoCopyRareDrops", false, "SKYBLOCK_AUTO_COPY_RARE_DROPS");
            this.method2(json3, json4, "showGiantHPAtFeet", false, "SKYBLOCK_GIANT_HP_AT_FEET");
            this.method2(json3, json4, "showProfessorGuardianHP", false, "SKYBLOCK_PROFESSOR_GUARDIAN_HP");
            this.method2(json3, json4, "showKuudraHealth", false, "SKYBLOCK_KUUDRA_HEALTH");
            this.method2(json3, json4, "skyBlockWishingCompass", false, "SKYBLOCK_WISHING_COMPASS");
            this.method2(json3, json4, "skyBlockMetalDetector", false, "SKYBLOCK_METAL_DETECTOR");
            this.method2(json3, json4, "skyBlockFinishedCommissions", false, "SKYBLOCK_COMPLETED_COMMISSIONS");
            this.method2(json3, json4, "skyBlockMiddleClickItems", false, "SKYBLOCK_MIDDLE_CLICK_GUI");
            this.method2(json3, json4, "skyBlockCreationDate", false, "SKYBLOCK_CREATION_DATE");
            this.method2(json3, json4, "skyBlockItemId", false, "SKYBLOCK_ITEM_ID_LORE");
            this.method2(json3, json4, "skyblockSecretsCollected", true, "SKYBLOCK_DUNGEON_FEEDBACK");
            this.method2(json3, json4, "skyblockFishingHidePlayers", false, "SKYBLOCK_FISHING_HIDE_PLAYERS");
            this.method2(json3, json4, "skyblockFishingHotspotLocator", false, "SKYBLOCK_FISHING_HOTSPOT_LOCATOR");
            this.method2(json3, json4, "highlightEndNodes", false, "SKYBLOCK_END_NODE_HIGHLIGHT");
            this.method2(json3, json4, "highlightGlowingMushrooms", false, "SKYBLOCK_GLOWING_MUSHROOM_HIGHLIGHT");
            this.method2(json3, json4, "sbMenuClickToCommand", true, "SKYBLOCK_CUSTOM_MENU_CLICK");
            this.method2(json3, json4, "skyblockDungeonQuality", false, "SKYBLOCK_DUNGEON_QUALITY");
            this.method2(json3, json4, "skyblockHideNonStarred", true, "SKYBLOCK_DUNGEON_NAMETAGS");
            this.method2(json3, json4, "skyblockTerminalSplitTimers", true, "SKYBLOCK_TERMINAL_SPLITS");
            this.method2(json3, json4, "skyblockHideExCoops", false, "SKYBLOCK_HIDE_EX_COOPS");
            this.method2(json3, json4, "skyblockHideMagicSoupMessages", false, "SKYBLOCK_HIDE_MAGIC_SOUP_MESSAGES");
            this.method2(json3, json4, "skyblockHidePlayersNearNPCs", false, "SKYBLOCK_HIDE_PLAYERS_NEAR_NPC");
            this.method2(json3, json4, "skyblockHideOthersGifts", true, "SKYBLOCK_HIDE_OTHERS_GIFTS");
            this.method2(json3, json4, "skyblockAdblock", true, "SKYBLOCK_WEBSITE_ADBLOCK");
            this.method2(json3, json4, "enableDungeonRoutes", false, "SKYBLOCK_DUNGEON_ROUTES");
            this.method2(json3, json4, "griffinBurrowEstimates", false, "SKYBLOCK_BURROW_LOCATING");
            this.method2(json3, json4, "hideFallingBlocks", false, "SKYBLOCK_HIDE_FALLING_BLOCKS");
            this.method2(json3, json4, "hideMidasStaff", false, "SKYBLOCK_HIDE_MIDAS_BLOCKS");
            this.method3(json3, json4, "defaultSecretRoutesEnabled", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesNamesAtStart", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesNameColor", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesAllowPearling", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableHelpText", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesPathType", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableSuperboom", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableBlock", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableLever", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableEtherwarp", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableSecret", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisableMisc", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "secretRoutesDisablePearls", "SKYBLOCK_DUNGEON_ROUTES");
            this.method3(json3, json4, "burrowEstimateColor", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowEstimateLine", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowEstimateBeam", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "ritualClosestWarpKeybind", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowEstimatesPrioritizePlayerWaypoints", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowWarpToNearestAlert", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "ritualClosestWarpAllowed", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowMobColor", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowTreasureColor", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowStartColor", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowUnknownColor", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "burrowBeam", "SKYBLOCK_BURROW_LOCATING");
            this.method3(json3, json4, "skyBlockMetalDetectorLine", "SKYBLOCK_METAL_DETECTOR");
            json4.remove("skyBlockMetalDetectorChime");
            boolean flag5 = this.method7(json4, "replaceLavaWithWaterCrimson", false);
            boolean flag6 = this.method7(json4, "replaceLavaWithWaterEverywhere", false);
            JsonObject json7 = this.method5(json3, "SKYBLOCK_REPLACE_LAVA_TEXTURE");
            json7.addProperty("enabled", flag5 || flag6);
            JsonObject json8 = this.method6(json7);
            json8.addProperty("replaceLavaWithWaterEverywhere", flag6);
            json4.remove("replaceLavaWithWaterCrimson");
            json4.remove("replaceLavaWithWaterEverywhere");
            boolean flag9 = this.method7(json4, "vampireSteakDisplay", false);
            boolean flag10 = this.method7(json4, "vampireIchorDisplay", false);
            JsonObject json11 = this.method5(json3, "SKYBLOCK_VAMPIRE_SLAYER");
            json11.addProperty("enabled", flag9 || flag10);
            JsonObject json12 = this.method6(json11);
            json12.addProperty("vampireSteakDisplay", flag9);
            json12.addProperty("vampireIchorDisplay", flag10);
            json4.remove("vampireSteakDisplay");
            json4.remove("vampireIchorDisplay");
         }
      }
   }

   private void method2(JsonObject json1, JsonObject json2, String text3, boolean flag, String text) {
      boolean flag6 = this.method7(json2, text3, flag);
      this.method4(json1, text, flag6);
      json2.remove(text3);
   }

   private void method3(JsonObject json1, JsonObject json2, String text3, String text) {
      if (json2.has(text3)) {
         this.method6(this.method5(json1, text)).add(text3, json2.get(text3));
         json2.remove(text3);
      }
   }

   private void method4(JsonObject json1, String text2, boolean flag3) {
      this.method5(json1, text2).addProperty("enabled", flag3);
   }

   private JsonObject method5(JsonObject json1, String text2) {
      if (json1.has(text2) && json1.get(text2).isJsonObject()) {
         return json1.getAsJsonObject(text2);
      }

      JsonObject json3 = new JsonObject();
      json1.add(text2, json3);
      return json3;
   }

   private JsonObject method6(JsonObject json1) {
      if (json1.has("options") && json1.get("options").isJsonObject()) {
         return json1.getAsJsonObject("options");
      }

      JsonObject json2 = new JsonObject();
      json1.add("options", json2);
      return json2;
   }

   private boolean method7(JsonObject json1, String text2, boolean flag3) {
      return json1.has(text2) && !json1.get(text2).isJsonNull() ? json1.get(text2).getAsBoolean() : flag3;
   }
}
