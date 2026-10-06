package com.moonsworth.lunar.client.mod.render;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.transform.ComponentTransform;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.lunarclient.adventure.transform.transformation.TransformationAction;
import com.lunarclient.adventure.transform.transformation.TransformationFunctor;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.waypoints.Gui2Loader_2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader;
import com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader2;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.rewindhandlers.ConfigureWaypointPropsLegacy;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.util.internal.ConcurrentSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WaypointStore extends ItemSetHandler<GuiHandler2> implements JsonFileConfig, GuiIterator.Extension, EventRegistrar {
   private static final String field2 = "waypoint-patterns.json";
   private static final String field3 = "/lc_waypoint_chat";
   private final List<Gui2Loader> decoders = ImmutableList.of(
      new com.moonsworth.lunar.client.framework.feature.waypoints.Gui2Loader("Badlion Client", "logo/badlion-logo-32x32.png"),
      new Gui2Loader_2("JourneyMap"),
      new com.moonsworth.lunar.client.framework.feature.waypoints.mixin.rewindhandlers.Gui2Loader("VoxelMap"),
      new com.moonsworth.lunar.client.framework.feature.waypoints.mixin.holograms.Gui2Loader("Xaero's Minimap"),
      new com.moonsworth.lunar.client.framework.feature.waypoints.mixin.nameplate.Gui2Loader("Skytils", "logo/skytils-logo-32x32.png"),
      new Gui2Loader2("Coleweight")
   );
   private int field4 = 0;
   private static final ComponentTransform field5 = ComponentTransform.builder()
      .textTransformation(
         new TransformationFunctor(
            TransformationAction.MODIFY,
            (var0, var1) -> var0 != null
               ? (Builder)((Builder)var1.hoverEvent(
                     HoverEvent.showText(Component.text(ThreadModuleDump63.method4().method67().method2("features.WAYPOINTS.info", "clickToCreate")))
                  ))
                  .clickEvent(ClickEvent.runCommand("/lc_waypoint_chat [" + var0.group(1) + ", " + var0.group(2) + ", " + var0.group(3) + "]"))
               : var1
         )
      )
      .build();
   private final GuiIterator field6 = new GuiIterator();
   private final List<ComponentTransformer> field7 = new ArrayList<>();
   private final List<Pattern> field8 = new ArrayList<>();
   private final BiMap<String, Integer> field9 = HashBiMap.create(4);
   private final AtomicInteger field10 = new AtomicInteger(1);
   private final Set<GuiHandler> field11 = new ConcurrentSet();
   private String field12;

   @Override
   protected Set<GuiHandler2> method3() {
      return new ConcurrentSet();
   }

   @Override
   public String method5() {
      return "waypoints.json";
   }

   @Override
   public void init() {
      super.init();
      this.HHIHOOIHCOHOIRORCHICOCHCORROCR();
      this.handle(KeybindEvent.class, var0 -> {
         if (ThreadModuleDump63.method8() != null && var0.method1(ThreadModuleDump63.method4().method41().method8().method16())) {
            com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field17);
         }
      });
      this.handle(EventRunDirectory.class, var1 -> {
         this.field12 = var1.method1();
         this.method10();
      });
      this.handle(ServerJoinEvent.class, var1 -> this.method10());
      this.handle(DisconnectEvent.class, var1 -> this.method10());
      this.handle(ServerPingEvent.class, var1 -> this.method10());
      this.handle(EventCommandLegacy.class, this::method12);
      this.handle(EventCommandLegacy.Data.class, this::method11);
      this.handle(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data.class, var1 -> {
         Waypoints var2 = ThreadModuleDump63.method4().method40().method20();
         if (var2.isEnabled() && var2.method26().get()) {
            Component var3 = var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI();

            for (ComponentTransformer var5 : this.field7) {
               var3 = var5.transform(var3);
            }

            var1.OHROCHICOIOICHOCRROORRCIIICIHO(var3);
         }
      });
      this.method11();
   }

   private void method10() {
      this.field9.clear();
      this.field9.putIfAbsent("minecraft:overworld", 0);
      this.field9.putIfAbsent("minecraft:the_nether", -1);
      this.field9.putIfAbsent("minecraft:the_end", 1);
      if (ThreadModuleDump63.method8() != null) {
         int var1 = ThreadModuleDump63.method8().bridge$getDimensionId();
         if (var1 < -1 || var1 > 1) {
            this.field9.putIfAbsent(ThreadModuleDump63.method8().bridge$getDimensionKey(), var1);
         }
      }

      this.field6.method3("server", method19());
      this.field6.method3("loadedWorld", this.field12);
      JsonObject var4 = new JsonObject();

      for (Entry var3 : this.field9.entrySet()) {
         var4.addProperty((String)var3.getKey(), (Number)var3.getValue());
      }

      this.field6.method3("knownDimensions", var4);
   }

   private void method11() {
      JsonObject var1 = this.method22("waypoint-patterns.json", JsonObject.class);
      if (var1 != null) {
         for (Entry var3 : var1.entrySet()) {
            Pattern var4 = Pattern.compile(((JsonElement)var3.getValue()).getAsString(), 2);
            this.field8.add(var4);
            this.field7
               .add(ComponentTransformer.of(ComponentPattern.builder().componentPredicate(var0 -> var0.clickEvent() == null).pattern(var4).build(), field5));
         }
      }
   }

   @Override
   public void close() {
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public boolean method5(GuiHandler2 var1, boolean var2) {
      for (GuiHandler2 var4 : this.method13()) {
         if (var1.getName().equals(var4.getName()) && var1.getServer().equals(var4.getServer()) && var1.method1(var4.getWorld())) {
            return false;
         }
      }

      this.method13().add(var1);
      if (var1.method39() != null && !var1.method39().isEmpty()) {
         this.field9.putIfAbsent(var1.method39(), var1.getDimension());
      }

      if (var1.method41()) {
         if (var2) {
            this.method7(var1);
         }
      } else {
         this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      }

      this.method20();
      return true;
   }

   public boolean method6(GuiHandler2 var1) {
      return this.method5(var1, true);
   }

   public void method7(GuiHandler2 var1) {
   }

   public void method8(GuiHandler2 var1) {
   }

   public boolean removeIf(Predicate<GuiHandler2> var1) {
      if (this.method13().removeIf(var1)) {
         this.method20();
         return true;
      } else {
         return false;
      }
   }

   public boolean method9(@NotNull GuiHandler2 var1) {
      if (var1.method40()) {
         return false;
      }

      if (var1.method41()) {
         this.method8(var1);
      }

      return this.removeIf(var1x -> var1x.equals(var1));
   }

   public Optional<WaypointStore.Data> method10(String var1) {
      for (Pattern var3 : this.field8) {
         Matcher var4 = var3.matcher(var1);
         if (var4.matches()) {
            return Optional.of(new WaypointStore.Data(var4.group(1), var4.group(2), var4.group(3)));
         }
      }

      return Optional.empty();
   }

   private void method11(EventCommandLegacy.Data var1) {
      if (var1.method1("/lc_waypoint_chat")) {
         var1.method2();
      }
   }

   private void method12(EventCommandLegacy var1) {
      if (var1.method1("/lc_waypoint_chat")) {
         var1.cancel();

         try {
            double var2;
            double var4;
            double var6;
            if (var1.get(0).contains("[")) {
               var2 = Double.parseDouble(var1.get(0).substring(1, var1.get(0).length() - 1));
               var4 = Double.parseDouble(var1.get(1).substring(0, var1.get(1).length() - 1));
               var6 = Double.parseDouble(var1.get(2).substring(0, var1.get(2).length() - 1));
            } else {
               var2 = Double.parseDouble(var1.get(1));
               var4 = Double.parseDouble(var1.get(3));
               var6 = Double.parseDouble(var1.get(5));
            }

            com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50()
               .method17(DriverRouteRegistryLegacy.field18, ConfigureWaypointPropsLegacy.method2().method4(var2).method5(var4).method6(var6).method7());
         } catch (Exception var8) {
         }
      }
   }

   @Override
   public void load(JsonObject var1) {
      this.method13().clear();
      this.field11.clear();
      boolean var2 = ConfigMigrator.method3(this, var1) && this.HOHCCHOCHIRIRORCHCROIHHOHRIHIR();
      if (var1.has("sortIndex")) {
         this.method23(var1.get("sortIndex").getAsInt());
      }

      if (var1.has("totalDeaths")) {
         this.field10.set(var1.get("totalDeaths").getAsInt());
      }

      if (var1.has("groups")) {
         this.method14(var1.getAsJsonObject("groups"));
      }

      if (var1.has("waypoints")) {
         for (GuiHandler2 var4 : this.method13(var1)) {
            this.method6(var4);
         }

         if (var2) {
            this.method7(true);
         }

         this.method20();
      }
   }

   public List<GuiHandler2> method13(JsonObject var1) {
      ArrayList var2 = new ArrayList();

      try {
         for (Entry var4 : var1.get("waypoints").getAsJsonObject().entrySet()) {
            String var5 = (String)var4.getKey();
            JsonObject var6 = ((JsonElement)var4.getValue()).getAsJsonObject();

            for (Entry var8 : var6.entrySet()) {
               String var9 = (String)var8.getKey();
               JsonObject var10 = ((JsonElement)var8.getValue()).getAsJsonObject();

               for (Entry var12 : var10.entrySet()) {
                  JsonObject var13 = ((JsonElement)var12.getValue()).getAsJsonObject();
                  JsonObject var14 = var13.getAsJsonObject("location");
                  JsonElement var15 = var13.get("addedAtMs");
                  JsonElement var16 = var13.get("isSkyBlockWaypoint");
                  JsonElement var17 = var13.get("isDeathWaypoint");
                  JsonElement var18 = var13.get("skyBlockLocation");
                  JsonElement var19 = var13.get("sortIndex");
                  JsonElement var20 = var13.get("groupIndex");
                  JsonElement var21 = var13.get("groupId");
                  GuiLoader var22 = new GuiLoader();
                  var22.load(var13.get("renderConfig").getAsJsonObject());
                  GuiHandler2 var23 = GuiHandler2.method18()
                     .method14(var15 == null ? 0L : var15.getAsLong())
                     .method2((String)var12.getKey())
                     .method3(Vec3Bridge.method2(var14.get("x").getAsDouble(), var14.get("y").getAsDouble(), var14.get("z").getAsDouble()))
                     .method4(var9)
                     .method5(var13.get("dimension").getAsInt())
                     .method7(var16 != null && var16.getAsBoolean())
                     .method8(var17 != null && var17.getAsBoolean())
                     .method6(var18 != null ? Gui2Extension3.getByMapValue(var13.get("skyBlockLocation").getAsString()) : Gui2Extension3.NONE)
                     .method15(var19 == null ? -1 : var19.getAsInt())
                     .method10(var13.get("visible").getAsBoolean())
                     .method12(var5)
                     .method13(false)
                     .method18(var22)
                     .method19();
                  if (var21 != null) {
                     this.method27(UUID.fromString(var21.getAsString())).ifPresent(var23::method32);
                  }

                  if (var20 != null) {
                     var23.method33(var20.getAsInt());
                  }

                  if (var13.has("dimensionKey")) {
                     var23.method26(var13.get("dimensionKey").getAsString());
                  }

                  var2.add(var23);
               }
            }
         }
      } catch (Exception var24) {
         Slayer.method7("Error while loading waypoints: " + var24);
         var24.printStackTrace();
      }

      return var2;
   }

   public void method14(JsonObject var1) {
      try {
         for (Entry var3 : var1.entrySet()) {
            String var4 = (String)var3.getKey();
            JsonObject var5 = ((JsonElement)var3.getValue()).getAsJsonObject();
            GuiHandler var6 = GuiHandler.method3(var4, var5);
            this.field11.add(var6);
         }
      } catch (Exception var7) {
         Slayer.method7("Error while loading waypoint groups: " + var7);
         var7.printStackTrace();
      }
   }

   public void getProvider(JsonObject var1) {
      JsonObject var2 = new JsonObject();

      for (GuiHandler var4 : this.field11) {
         JsonObject var5 = new JsonObject();
         var4.method2(var5);
         var2.add(var4.getId().toString(), var5);
      }

      var1.add("groups", var2);
   }

   @Override
   public void method1(JsonObject var1) {
      var1.addProperty("version", ConfigMigrator.field2);
      var1.addProperty("totalDeaths", this.field10.get());
      var1.addProperty("sortIndex", this.field4);
      this.getProvider(var1);
      this.method17(var1, this.method13());
   }

   public void method17(JsonObject var1, Collection<GuiHandler2> var2) {
      JsonObject var3 = new JsonObject();

      for (GuiHandler2 var5 : var2) {
         this.method18(var3, var5);
      }

      var1.add("waypoints", var3);
   }

   public void method18(JsonObject var1, GuiHandler2 var2) {
      try {
         if (var2.method41()) {
            return;
         }

         JsonObject var3;
         if (!var1.has(var2.getServer())) {
            var3 = new JsonObject();
            var1.add(var2.getServer(), var3);
         } else {
            var3 = var1.get(var2.getServer()).getAsJsonObject();
         }

         JsonObject var4;
         if (!var3.has(var2.getWorld())) {
            var4 = new JsonObject();
            var3.add(var2.getWorld(), var4);
         } else {
            var4 = var3.get(var2.getWorld()).getAsJsonObject();
         }

         JsonObject var5 = new JsonObject();
         JsonObject var6 = new JsonObject();
         var6.addProperty("x", var2.method35().bridge$xCoord());
         var6.addProperty("y", var2.method35().bridge$yCoord());
         var6.addProperty("z", var2.method35().bridge$zCoord());
         var5.add("location", var6);
         var5.addProperty("icon", var2.method46().getIcon().name());
         var5.addProperty("addedAtMs", var2.method42());
         var5.addProperty("isDeathWaypoint", var2.method38());
         var5.addProperty("visible", var2.isVisible());
         var5.addProperty("dimension", var2.getDimension());
         var5.addProperty("isSkyBlockWaypoint", var2.method37());
         var5.addProperty("skyBlockLocation", var2.method36().getMapValue());
         var5.addProperty("sortIndex", var2.method43());
         if (var2.method45() != -1) {
            var5.addProperty("groupIndex", var2.method45());
         }

         if (var2.method39() != null && !var2.method39().isEmpty()) {
            var5.addProperty("dimensionKey", var2.method39());
         }

         JsonObject var7 = new JsonObject();
         GuiLoader var8 = var2.method46();
         var8.method3(var7);
         var5.add("renderConfig", var7);
         if (var2.method44() != null) {
            var5.addProperty("groupId", var2.method44().getId().toString());
         }

         var4.add(var2.getName(), var5);
      } catch (Exception var9) {
      }
   }

   public static String method19() {
      Bridge5_12 var0 = ThreadModuleDump63.method3();
      Bridge3_19 var1 = var0.bridge$getCurrentServerData();
      String var2 = "";
      if (var0.bridge$getWorld() != null && var1 == null) {
         var2 = var2 + "sp:" + ThreadModuleDump63.method4().method48().field12;
      } else if (var0.bridge$isConnectedToRealms()) {
         var2 = var2 + "realms";
         if (var1 != null) {
            var2 = var2 + ": " + var1.bridge$getServerName();
         }
      } else if (var0.bridge$getWorld() != null && ThreadModuleDump63.method4().method81().method26()) {
         var2 = var2 + "hostedworld:" + var0.bridge$getWorld().bridge$getWorldId();
      } else if (var1 != null) {
         var2 = var2 + "mp:" + var1.bridge$serverIP();
      }

      Waypoints var3 = ThreadModuleDump63.method4().method40().method20();
      return var3.method33().method1("server", var2);
   }

   public void method20() {
      this.field6.method3("waypoints", this.method21(this.method13()));
      JsonArray var1 = new JsonArray();

      for (GuiHandler var3 : this.field11) {
         var1.add(var3.method128());
      }

      this.field6.method3("groups", var1);
   }

   public JsonObject method21(Collection<GuiHandler2> var1) {
      JsonObject var2 = new JsonObject();

      for (GuiHandler2 var4 : var1) {
         try {
            JsonObject var5;
            if (!var2.has(var4.getServer())) {
               var5 = new JsonObject();
               var2.add(var4.getServer(), var5);
            } else {
               var5 = var2.get(var4.getServer()).getAsJsonObject();
            }

            JsonObject var6;
            if (!var5.has(var4.getWorld())) {
               var6 = new JsonObject();
               var5.add(var4.getWorld(), var6);
            } else {
               var6 = var5.get(var4.getWorld()).getAsJsonObject();
            }

            var6.add(var4.getName(), var4.method128());
         } catch (Exception var7) {
         }
      }

      return var2;
   }

   @Nullable
   private <T> T method22(String var1, Class<T> var2) {
      Path var3 = ThreadModuleDump48.field12.resolve(var1);
      if (!var3.toFile().exists()) {
         return null;
      }

      try {
         String var4 = Files.readString(var3);
         return (T)ThreadModuleDump48.field22.fromJson(var4, var2);
      } catch (Exception var5) {
         Inventorymod2.method5(var5, "Loading " + var1);
         return null;
      }
   }

   public void method23(int var1) {
      this.field4 = var1;
      this.field6.method3("activeSortIndex", var1);
   }

   public Optional<GuiHandler2> method24(String var1, String var2, String var3) {
      return this.method13()
         .stream()
         .filter(var3x -> var3x.getName().equals(var3) && var3x.getServer().equals(var1) && var3x.getWorld().equals(var2))
         .findFirst();
   }

   public boolean method25(GuiHandler var1) {
      if (this.field11.stream().anyMatch(var1x -> var1x.getId().equals(var1.getId()))) {
         return false;
      }

      this.field11.add(var1);
      this.method20();
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      return true;
   }

   public boolean method26(UUID var1, boolean var2) {
      GuiHandler var3 = this.field11.stream().filter(var1x -> var1x.getId().equals(var1)).findFirst().orElse(null);
      if (var3 == null) {
         return false;
      }

      if (var2) {
         this.method13().removeIf(var1x -> var1x.method44() != null && var1.equals(var1x.method44().getId()));
      } else {
         this.method13()
            .stream()
            .filter(var1x -> var1x.method44() != null && var1.equals(var1x.method44().getId()))
            .forEach(var0 -> var0.method32(null));
      }

      this.field11.remove(var3);
      this.method20();
      return true;
   }

   public Optional<GuiHandler> method27(UUID var1) {
      return this.field11.stream().filter(var1x -> var1x.getId().equals(var1)).findFirst();
   }

   @Generated
   public List<Gui2Loader> getDecoders() {
      return this.decoders;
   }

   @Generated
   @Override
   public GuiIterator getProvider() {
      return this.field6;
   }

   @Generated
   public BiMap<String, Integer> method30() {
      return this.field9;
   }

   @Generated
   public AtomicInteger method31() {
      return this.field10;
   }

   @Generated
   public Set<GuiHandler> method32() {
      return this.field11;
   }

   public class Data {
      private final String field1;
      private final String field2;
      private final String field3;

      public Data(String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public String method1() {
         return this.field1;
      }

      public String method2() {
         return this.field2;
      }

      public String method3() {
         return this.field3;
      }
   }
}
