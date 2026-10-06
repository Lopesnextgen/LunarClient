package com.moonsworth.lunar.client.framework.feature.mod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.GlaciteTunnelGraph;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.FairySoulLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.GlaciteTunnelsDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommandConfig;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommandDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.chest.NpcLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.MiddleClickGuiRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Vec3iDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation.MinionXpData;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemShopPrices;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemShopPricesDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItems;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.QuizKey;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonSplits;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.WaterBoardSolutions;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.QuizKeyDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin.KuudraWaypoints;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreatureRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.CropType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.GameDataLoadEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.skyblock.farminghud.SkyblockFarmingHud;
import com.moonsworth.lunar.client.mod.skyblock.middleclickgui.SkyblockMiddleClickGui;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class Module {
   public static final Gson field1 = new GsonBuilder()
      .registerTypeAdapter(QuizKey.class, new QuizKeyDeserializer())
      .registerTypeAdapter(GlaciteTunnelGraph.class, new GlaciteTunnelsDeserializer())
      .registerTypeAdapter(SkyBlockCommandConfig.class, new SkyBlockCommandDeserializer())
      .registerTypeAdapter(Vec3iBridge.class, new Vec3iDeserializer())
      .registerTypeAdapter(
         com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap.class,
         new com.moonsworth.lunar.client.framework.feature.mod.fishing.MetalDetectorLocationsDeserializer()
      )
      .registerTypeAdapter(DungeonSplits.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonSplitsDeserializer())
      .registerTypeAdapter(ChocolateEggLocations.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocationsDeserializer())
      .registerTypeAdapter(KuudraWaypoints.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin.KuudraWaypointsDeserializer())
      .registerTypeAdapter(
         Dungeonwaypoints.class, new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.LegacyDungeonWaypointDeserializer()
      )
      .registerTypeAdapter(WaterBoardSolutions.class, new WaterBoardSolutions.Data())
      .registerTypeAdapter(ImportantItems.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ImportantItemsDeserializer())
      .registerTypeAdapter(ItemShopPrices.class, new ItemShopPricesDeserializer())
      .registerTypeAdapter(NpcLocations.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.chest.ImportantNpcLocationsDeserializer())
      .registerTypeAdapter(MaxLevels.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.MaxLevelsDeserializer())
      .registerTypeAdapter(SeaCreatureRegistry.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreatureRegistryDeserializer())
      .registerTypeAdapter(FairySoulLocations.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.FairySoulLocationsDeserializer())
      .registerTypeAdapter(MinionXpData.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation.MinionDataDeserializer())
      .registerTypeAdapter(VanillaItemRegistry.class, new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.JsonDeserializerIterator2())
      .setPrettyPrinting()
      .create();
   private static final Path field2 = LunarConstants.field13.resolve("local");
   private boolean field3;
   private volatile QuizKey field4;
   private volatile GlaciteTunnelGraph field5;
   private volatile SkyBlockCommandConfig field6;
   private volatile DungeonRoom[] field7;
   private volatile com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap field8;
   private volatile DungeonSplits field9;
   private volatile HashMap<String, Long> field10;
   private volatile ChocolateEggLocations field11;
   private volatile WaterBoardSolutions field12;
   private volatile HashMap<CropType, int[]> field13;
   private volatile KuudraWaypoints field14;
   private volatile MiddleClickGuiRegistry field15;
   private volatile HashSet<String> field16;
   private volatile HashMap<String, IntArrayList> field17;
   private volatile ImportantItems field18;
   private volatile MaxLevels field19;
   private volatile Map<String, Gui> field20;
   private volatile Map<String, Gui> field21;
   private volatile Map<String, Gui3> field22;
   private volatile List<String> field23;
   private volatile ItemShopPrices field24;
   private volatile Map<String, String> field25;
   private volatile Map<String, List<Vector3ic>> field26;
   private volatile NpcLocations field27;
   private volatile List<String> field28;
   private volatile Object2ObjectOpenHashMap<String, IntArrayList> field29;
   private volatile Object2IntOpenHashMap<String> field30;
   private volatile Object2IntOpenHashMap<String> field31;
   private volatile Map<String, String> field32;
   private volatile Map<String, String> field33;
   private volatile SeaCreatureRegistry field34;
   private volatile Set<String> field35;
   private volatile FairySoulLocations field36;
   private volatile MinionXpData field37;
   private volatile VanillaItemRegistry field38;

   public Module() {
   }

   public void method1() {
      this.field3 = true;
      this.field4 = this.method3("dungeon/quiz-key.json", QuizKey.class);
      this.field5 = this.method3("glacite-tunnels.json", GlaciteTunnelGraph.class);
      this.field6 = this.method3("commands.json", SkyBlockCommandConfig.class);
      this.field7 = this.method3("dungeon" + File.separator + "rooms.json", DungeonRoom[].class);
      this.field8 = this.method3("metal-detector-locations.json", com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap.class);
      this.field9 = this.method3("splits.json", DungeonSplits.class);
      this.field10 = this.method2("chocolate-factory-prices.json", (new TypeToken<HashMap<String, Long>>() {}).getType());
      this.field11 = this.method3("hoppity-eggs.json", ChocolateEggLocations.class);
      this.field12 = this.method3("dungeon/waterboard-solutions.json", WaterBoardSolutions.class);
      this.field13 = SkyblockFarmingHud.method12(this.method3("garden.json", JsonObject.class));
      this.field14 = this.method3("kuudra-waypoints.json", KuudraWaypoints.class);
      this.field15 = SkyblockMiddleClickGui.method4(this.method3("middle-click.json", JsonArray.class));
      this.field16 = this.method2("dungeon/trash-items.json", (new TypeToken<HashSet<String>>() {}).getType());
      this.field17 = this.method2("skill-xp.json", (new TypeToken<HashMap<String, IntArrayList>>() {}).getType());
      this.field18 = this.method3("important-items.json", ImportantItems.class);
      this.field19 = this.method3("max-levels.json", MaxLevels.class);
      this.field22 = this.method2("vendor-items.json", (new TypeToken<Map<String, Gui3>>() {}).getType());
      Gui[] items1 = this.method3("enchants.json", Gui[].class);
      this.field20 = new HashMap<>();
      this.field21 = new HashMap<>();

      for (Gui gui5 : items1) {
         this.field20.put(gui5.displayName(), gui5);
         this.field21.put(gui5.name(), gui5);
      }

      this.field23 = this.method2("autocomplete-warps.json", (new TypeToken<List<String>>() {}).getType());
      this.field24 = this.method3("item-shop-prices.json", ItemShopPrices.class);
      this.field25 = this.method2("sphinx-key.json", (new TypeToken<HashMap<String, String>>() {}).getType());
      this.field26 = method7(this.method2("mineshaft-corpse-waypoints.json", (new TypeToken<Map<String, List<List<Integer>>>>() {}).getType()));
      this.field27 = this.method3("important-npc-locations.json", NpcLocations.class);
      this.field28 = this.method2("adblock-websites.json", (new TypeToken<List<String>>() {}).getType());
      this.field29 = this.method2("stacking-enchants.json", (new TypeToken<Object2ObjectOpenHashMap<String, IntArrayList>>() {}).getType());
      this.field30 = this.method2("fishing/trophy-fish-fillet.json", (new TypeToken<Object2IntOpenHashMap<String>>() {}).getType());
      this.field31 = this.method2("fishing/trophy-frog-fillet.json", (new TypeToken<Object2IntOpenHashMap<String>>() {}).getType());
      this.field32 = this.method2("api-items-override.json", (new TypeToken<HashMap<String, String>>() {}).getType());
      this.field33 = this.method2("api-materials-override.json", (new TypeToken<HashMap<String, String>>() {}).getType());
      this.field34 = this.method3("fishing/sea-creatures.json", SeaCreatureRegistry.class);
      this.field35 = this.method2("fishing/baits.json", (new TypeToken<HashSet<String>>() {}).getType());
      this.field36 = this.method3("fairy-souls.json", FairySoulLocations.class);
      this.field37 = this.method3("minions.json", MinionXpData.class);
      this.field38 = this.method3("skyblock-vanilla-items.json", VanillaItemRegistry.class);
      if (this.field38 != null && this.field38.method3() > 0) {
         CrashReporter.method5(new JsonParseException(this.field38.method3() + " malformed entries dropped from vanilla-items.json"), "SkyBlockGameData");
      }

      Ref.method3().bridge$submit(() -> LunarEventBus.method29().method12(GameDataLoadEvent.class, GameDataLoadEvent::new));
   }

   private <T> T method2(String text1, Type type2) {
      Path path3 = LunarConstants.field13.resolve(text1);
      if (!path3.toFile().exists()) {
         return null;
      }

      try {
         String text4 = Files.readString(path3);
         return (T)field1.fromJson(text4, type2);
      } catch (Exception exception5) {
         CrashReporter.method5(exception5, "Loading " + text1);
         return null;
      }
   }

   private <T> T method3(String text1, Class<T> clazz2) {
      return this.method2(text1, clazz2);
   }

   public <T> T method4(String text1, Class<T> clazz2) {
      return this.method6(text1, clazz2);
   }

   public void method5(String text1, Object obj2) {
      Path path3 = field2.resolve(text1);

      try {
         Files.createDirectories(field2);
         Files.writeString(path3, field1.toJson(obj2));
      } catch (Exception exception5) {
         CrashReporter.method5(exception5, "Saving " + text1);
      }
   }

   public <T> T method6(String text1, Type type2) {
      Path path3 = field2.resolve(text1);
      if (!path3.toFile().exists()) {
         return null;
      }

      try {
         String text4 = Files.readString(path3);
         return (T)field1.fromJson(text4, type2);
      } catch (Exception exception5) {
         CrashReporter.method5(exception5, "Loading " + text1);
         return null;
      }
   }

   private static Map<String, List<Vector3ic>> method7(Map<String, List<List<Integer>>> map0) {
      HashMap map1 = new HashMap<String, List<Vector3ic>>() {};
      if (map0 == null) {
         return map1;
      }

      for (Entry entry3 : map0.entrySet()) {
         ArrayList list4 = new ArrayList();

         for (List list6 : (List)entry3.getValue()) {
            list4.add(new Vector3i((Integer)list6.get(0), (Integer)list6.get(1), (Integer)list6.get(2)));
         }

         map1.put((String)entry3.getKey(), list4);
      }

      return map1;
   }

   @Generated
   public boolean method8() {
      return this.field3;
   }

   @Generated
   public QuizKey method9() {
      return this.field4;
   }

   @Generated
   public GlaciteTunnelGraph method10() {
      return this.field5;
   }

   @Generated
   public SkyBlockCommandConfig method11() {
      return this.field6;
   }

   @Generated
   public DungeonRoom[] method12() {
      return this.field7;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap method13() {
      return this.field8;
   }

   @Generated
   public DungeonSplits method14() {
      return this.field9;
   }

   @Generated
   public HashMap<String, Long> method15() {
      return this.field10;
   }

   @Generated
   public ChocolateEggLocations method16() {
      return this.field11;
   }

   @Generated
   public WaterBoardSolutions method17() {
      return this.field12;
   }

   @Generated
   public HashMap<CropType, int[]> method18() {
      return this.field13;
   }

   @Generated
   public KuudraWaypoints method19() {
      return this.field14;
   }

   @Generated
   public MiddleClickGuiRegistry method20() {
      return this.field15;
   }

   @Generated
   public HashSet<String> method21() {
      return this.field16;
   }

   @Generated
   public HashMap<String, IntArrayList> method22() {
      return this.field17;
   }

   @Generated
   public ImportantItems method23() {
      return this.field18;
   }

   @Generated
   public MaxLevels method24() {
      return this.field19;
   }

   @Generated
   public Map<String, Gui> method25() {
      return this.field20;
   }

   @Generated
   public Map<String, Gui> method26() {
      return this.field21;
   }

   @Generated
   public Map<String, Gui3> method27() {
      return this.field22;
   }

   @Generated
   public List<String> method28() {
      return this.field23;
   }

   @Generated
   public ItemShopPrices method29() {
      return this.field24;
   }

   @Generated
   public Map<String, String> method30() {
      return this.field25;
   }

   @Generated
   public Map<String, List<Vector3ic>> method31() {
      return this.field26;
   }

   @Generated
   public NpcLocations method32() {
      return this.field27;
   }

   @Generated
   public List<String> method33() {
      return this.field28;
   }

   @Generated
   public Object2ObjectOpenHashMap<String, IntArrayList> method34() {
      return this.field29;
   }

   @Generated
   public Object2IntOpenHashMap<String> method35() {
      return this.field30;
   }

   @Generated
   public Object2IntOpenHashMap<String> method36() {
      return this.field31;
   }

   @Generated
   public Map<String, String> method37() {
      return this.field32;
   }

   @Generated
   public Map<String, String> method38() {
      return this.field33;
   }

   @Generated
   public SeaCreatureRegistry method39() {
      return this.field34;
   }

   @Generated
   public Set<String> method40() {
      return this.field35;
   }

   @Generated
   public FairySoulLocations method41() {
      return this.field36;
   }

   @Generated
   public MinionXpData method42() {
      return this.field37;
   }

   @Generated
   public VanillaItemRegistry method43() {
      return this.field38;
   }
}
