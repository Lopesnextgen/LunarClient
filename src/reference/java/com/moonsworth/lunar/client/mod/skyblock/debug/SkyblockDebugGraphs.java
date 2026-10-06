package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.GlaciteTunnelGraph;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import com.moonsworth.lunar.client.event.mixin.EventCommand;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.collection.UnorderedPair;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import java.nio.file.Files;
import java.nio.file.Path;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.joml.Vector3d;

public class SkyblockDebugGraphs extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPathEdges").method4(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPathVertices").method4(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showGlacite").method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showUmber").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTungsten").method4(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAquamarine").method4(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCitrine").method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPeridot").method4(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showOnyx").method4(true))
      .method31();
   private GlaciteTunnelGraph field17 = null;
   private Fishing2 field18 = null;
   private Fishing2 field19 = null;
   private boolean field20 = true;

   public SkyblockDebugGraphs(SkyblockDebugMod skyblockdebugmod1) {
      super(true);
      this.method7(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.handle(EventCommand.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt.class, this::method2);
   }

   public void method3(boolean flag1) {
      if (flag1 && !this.field20) {
         new Thread(() -> {
            Path path1x = LunarConstants.field13.resolve("glacite-tunnels.json");
            if (path1x.toFile().exists()) {
               try {
                  String text2 = Files.readString(path1x);
                  this.field17 = (GlaciteTunnelGraph)SkyblockMetalDetectorDataGen.field9.fromJson(text2, GlaciteTunnelGraph.class);
               } catch (Exception exception3) {
                  CrashReporter.method5(exception3, "Load Metal Detector Locations");
               }
            }
         }).start();
         this.field20 = true;
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt highlightimpl41) {
      if (this.field17 != null && this.field17.method4() != null) {
         AbstractRenderContext bridgeextension_92 = highlightimpl41.method3();
         EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
         bridgeextension_92.push();
         bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
         if (this.field18 != null) {
            WorldRenderUtils.drawComponent(
               bridgeextension_92,
               Component.text("a").color(NamedTextColor.BLUE),
               this.field18.method4().bridge$getX() + 0.5F,
               this.field18.method4().bridge$getY() + 2.0F,
               this.field18.method4().bridge$getZ() + 0.5F,
               true
            );
         }

         if (this.field19 != null) {
            WorldRenderUtils.drawComponent(
               bridgeextension_92,
               Component.text("b").color(NamedTextColor.RED),
               this.field19.method4().bridge$getX() + 0.5F,
               this.field19.method4().bridge$getY() + 2.0F,
               this.field19.method4().bridge$getZ() + 0.5F,
               true
            );
         }

         for (Fishing2 fishing25 : this.field17.method4().getNodes()) {
            if (this.method9(fishing25)) {
               String text6 = fishing25.method5() == null ? "path" : fishing25.method5().getId();
               int number7 = fishing25.method5() == null ? -1 : fishing25.method5().getColor();
               int number8 = number7 & 16777215 | 570425344;
               WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_92, fishing25.method4().bridge$toJoml(), number8);
               WorldRenderUtils.drawComponent(
                  bridgeextension_92,
                  Component.text(text6).color(TextColor.color(number7)),
                  fishing25.method4().bridge$getX() + 0.5F,
                  fishing25.method4().bridge$getY() + 1.5F,
                  fishing25.method4().bridge$getZ() + 0.5F,
                  true
               );
            }
         }

         for (UnorderedPair threadmoduledump510 : this.field17.method3()) {
            if (this.method8(threadmoduledump510)) {
               Vector3d vector3d11 = new Vector3d(
                  ((Fishing2)threadmoduledump510.method1()).method4().bridge$getX() + 0.5,
                  ((Fishing2)threadmoduledump510.method1()).method4().bridge$getY() + 0.5,
                  ((Fishing2)threadmoduledump510.method1()).method4().bridge$getZ() + 0.5
               );
               Vector3d vector3d12 = new Vector3d(
                  ((Fishing2)threadmoduledump510.method2()).method4().bridge$getX() + 0.5,
                  ((Fishing2)threadmoduledump510.method2()).method4().bridge$getY() + 0.5,
                  ((Fishing2)threadmoduledump510.method2()).method4().bridge$getZ() + 0.5
               );
               WorldRenderUtils.renderDebugLine(bridgeextension_92, vector3d11, vector3d12, -16711936);
            }
         }

         bridgeextension_92.pop();
      }
   }

   private void method3(EventCommand highlightimpl41) {
      if (highlightimpl41.getCommand().startsWith("/sbdm-graph")) {
         highlightimpl41.cancel();
         if (highlightimpl41.get(0).equals("add")) {
            if (highlightimpl41.get(1).equals("path")) {
               Fishing2 fishing27 = new Fishing2(Ref.method7().bridge$getBlockPos().bridge$above(), null);
               this.field17.method4().add(fishing27);
               SkyBlockChat.method1("adding new path node");
               return;
            }

            for (FishingType fishingtype5 : FishingType.values()) {
               if (highlightimpl41.get(1).equals(fishingtype5.getId())) {
                  Fishing2 fishing26 = new Fishing2(Ref.method7().bridge$getBlockPos().bridge$above(), fishingtype5);
                  this.method4(fishing26);
                  SkyBlockChat.method1("adding new " + fishingtype5.getId() + " node");
                  return;
               }
            }
         } else if (highlightimpl41.get(0).equals("connect")) {
            if (this.field18 == null || this.field19 == null) {
               SkyBlockChat.method1("please set A node and B node");
               return;
            }

            this.field17.method4().connect(this.field18, this.field19);
            SkyBlockChat.method1("connected nodes A and B");
         } else if (highlightimpl41.get(0).equals("disconnect")) {
            if (this.field18 == null || this.field19 == null) {
               SkyBlockChat.method1("please set A node and B node");
               return;
            }

            this.field17.method4().disconnect(this.field18, this.field19);
         } else if (highlightimpl41.get(0).equals("remove")) {
            this.field17.method4().remove(this.method14());
            SkyBlockChat.method1("remove completed");
         } else if (highlightimpl41.get(0).equals("build")) {
            ClipboardUtils.method2(LunarConstants.field22.toJson(this.method13()));
            SkyBlockChat.method1("Graph built and saved to your clipboard");
         } else if (highlightimpl41.get(0).equals("load")) {
            this.load();
            SkyBlockChat.method1("Graph loaded from your clipboard");
         } else if (highlightimpl41.get(0).equals("a")) {
            this.field18 = this.method14();
            SkyBlockChat.method1("Set A node");
         } else if (highlightimpl41.get(0).equals("b")) {
            this.field19 = this.method14();
            SkyBlockChat.method1("Set B node");
         } else if (highlightimpl41.get(0).equals("help")) {
            SkyBlockChat.method1("Graph building tool help:");
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - /sbdm graph build ").color(NamedTextColor.WHITE))
                  .append(Component.text(": saves a json representation of the current graph to the user's clipboard").color(NamedTextColor.GRAY))
            );
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - /sbdm graph add <path or resource type> ").color(NamedTextColor.WHITE))
                  .append(Component.text(": adds a path or resource type at the current location").color(NamedTextColor.GRAY))
            );
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - sbdm graph <a or b> ").color(NamedTextColor.WHITE))
                  .append(Component.text(": saves the current node as either a or b").color(NamedTextColor.GRAY))
            );
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - sbdm graph connect ").color(NamedTextColor.WHITE))
                  .append(Component.text(": connect to the current a and b nodes").color(NamedTextColor.GRAY))
            );
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - sbdm graph remove ").color(NamedTextColor.WHITE))
                  .append(Component.text(": removes nearest node").color(NamedTextColor.GRAY))
            );
            SkyBlockChat.sendMessage(
               ((TextComponent)Component.text(" - sbdm graph disconnect ").color(NamedTextColor.WHITE)).append(Component.text(": disconnects node a and b"))
            );
         }
      }
   }

   public void method4(Fishing2 fishing21) {
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (itemcounter6extension2 != null) {
         if (fishing21.method5() == null) {
            throw new IllegalArgumentException("path node provided to non path node method");
         }

         this.field17.method4().add(fishing21);

         for (Fishing2 fishing24 : this.field17.method4().getNodes()) {
            if (fishing21.method5() == null || fishing24.method5() == null) {
               Ray sextension5 = Ray.method9(Raycaster.field4)
                  .method6(fishing21.method4(), fishing24.method4())
                  .method14((arg0, arg1x) -> !arg1x.bridge$isAir())
                  .method18();
               ((MissResult)sextension5.method8(Ref.method8())).method7(arg0 -> {}, () -> this.field17.method4().connect(fishing21, fishing24));
            }
         }
      }
   }

   private JsonObject method13() {
      JsonObject json1 = new JsonObject();
      JsonObject json2 = new JsonObject();
      JsonObject json3 = new JsonObject();

      for (Fishing2 fishing25 : this.field17.method4().getNodes()) {
         JsonArray array6 = new JsonArray();

         for (Fishing2 fishing28 : this.field17.method4().getConnectedNodesFor(fishing25)) {
            array6.add(fishing28.getUuid());
         }

         json2.add(fishing25.getUuid(), fishing25.method2());
         json3.add(fishing25.getUuid(), array6);
      }

      json1.add("nodes", json2);
      json1.add("links", json3);
      return json1;
   }

   private void load() {
      String text1 = ClipboardUtils.method1();
      this.field17 = (GlaciteTunnelGraph)Module.field1.fromJson(text1, GlaciteTunnelGraph.class);
   }

   private Fishing2 method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return null;
      }

      double value2 = Double.MAX_VALUE;
      Fishing2 fishing24 = null;

      for (Fishing2 fishing26 : this.field17.method4().getNodes()) {
         double value7 = bridge5extension_51.method15(fishing26.method4().bridge$getX(), fishing26.method4().bridge$getY(), fishing26.method4().bridge$getZ());
         if (value7 < value2) {
            value2 = value7;
            fishing24 = fishing26;
         }
      }

      return fishing24;
   }

   private ToggleOption method7(FishingType fishingtype1) {
      return switch (fishingtype1) {
         case GLACITE -> this.field10;
         case ONYX -> this.field16;
         case UMBER -> this.field11;
         case CITRINE -> this.field14;
         case PERIDOT -> this.field15;
         case TUNGSTEN -> this.field12;
         case AQUAMARINE -> this.field13;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   private boolean method8(UnorderedPair<Fishing2> threadmoduledump51) {
      return ((Fishing2)threadmoduledump51.method1()).method5() == null && ((Fishing2)threadmoduledump51.method2()).method5() == null
         ? (Boolean)this.field8.get()
         : this.method9((Fishing2)threadmoduledump51.method1()) && this.method9((Fishing2)threadmoduledump51.method2());
   }

   private boolean method9(Fishing2 fishing21) {
      return fishing21.method5() == null ? (Boolean)this.field9.get() : (Boolean)this.method7(fishing21.method5()).get();
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_GRAPHS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(
         new ClientOption[]{this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16}
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method8().method11(this);
   }
}
