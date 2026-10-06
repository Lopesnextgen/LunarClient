package com.moonsworth.lunar.client.mod.skyblock.fairysouls;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.FairySoulLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import org.joml.Vector3i;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockFairySouls extends AbstractFeature {
   private static final File field8 = new File(LunarConstants.field25 + File.separator + "skyblock_fairy_souls.json");
   private static final Set<String> field9 = Sets.newHashSet(new String[]{"SOUL! You found a Fairy Soul!", "You have already found that Fairy Soul!"});
   private final ProfileIdListener field10 = (ProfileIdListener)this.method9(ProfileIdListener.class);
   private final IntegerOption field11 = (IntegerOption)((Data)((Data)OptionFactory.method4("fairySoulDistanceToShow")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 300))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "waypointColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2130744908))
      .method31();
   private FairySoulLocations field13 = new FairySoulLocations();

   public SkyblockFairySouls(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method53(() -> this.method3(new SkyblockProfileChangeEvent(null, this.field10.method5())));
      this.handle(HudRenderLegacyEventAlt.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(SkyblockProfileChangeEvent.class, this::method3);
   }

   private void method1(HudRenderLegacyEventAlt highlightimpl41) {
      SkyblockIsland gui2extension32 = IslandUtils.getIsland();
      Set set3 = this.method5(gui2extension32);
      if (!set3.isEmpty()) {
         Bridge5Extension_5 bridge5extension_54 = Ref.method7();
         Vector3i vector3i5 = new Vector3i(bridge5extension_54.bridge$getBlockX(), bridge5extension_54.bridge$getBlockY(), bridge5extension_54.bridge$getBlockZ());
         AbstractRenderContext bridgeextension_96 = highlightimpl41.method3();
         Set set7 = this.field13.method1(gui2extension32);

         for (Vector3i vector3i9 : set3) {
            if (!set7.contains(vector3i9) && ((Integer)this.field11.get() <= 0 || !(vector3i9.distance(vector3i5) > ((Integer)this.field11.get()).intValue()))) {
               WorldRenderUtils.drawBeaconBeam(bridgeextension_96, vector3i9.x() + 0.5, vector3i9.y(), 256.0, vector3i9.z() + 0.5, this.field12.method14(0.0F));
            }
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (field9.contains(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH())) {
         SkyblockIsland gui2extension32 = IslandUtils.getIsland();
         Set set3 = this.method5(gui2extension32);
         if (!set3.isEmpty()) {
            Bridge5Extension_5 bridge5extension_54 = Ref.method7();
            Vector3i vector3i5 = new Vector3i(bridge5extension_54.bridge$getBlockX(), bridge5extension_54.bridge$getBlockY(), bridge5extension_54.bridge$getBlockZ());
            Vector3i vector3i6 = null;
            double value7 = Double.MAX_VALUE;

            for (Vector3i vector3i10 : set3) {
               double value11 = vector3i10.distanceSquared(vector3i5);
               if (!(value11 > 25.0) && value11 < value7) {
                  vector3i6 = vector3i10;
                  value7 = value11;
               }
            }

            if (vector3i6 != null) {
               if (this.field13.method2(gui2extension32, vector3i6)) {
                  this.method13();
               }
            }
         }
      }
   }

   private void method3(SkyblockProfileChangeEvent data151) {
      this.field13 = new FairySoulLocations();
      if (field8.exists()) {
         try {
            String text2 = data151.method2();
            JsonElement element3 = JsonParser.parseReader(new FileReader(field8));
            if (!element3.isJsonObject()) {
               return;
            }

            JsonObject json4 = element3.getAsJsonObject();
            String text5 = this.mc.bridge$getSession().bridge$getProfile().getId().toString();
            if (!json4.has(text5)) {
               return;
            }

            JsonObject json6 = json4.getAsJsonObject(text5);
            if (!json6.has(text2)) {
               return;
            }

            JsonObject json7 = json6.getAsJsonObject(text2);
            if (!json7.has("fairySouls")) {
               return;
            }

            this.field13 = (FairySoulLocations)Module.field1.fromJson(json7.getAsJsonObject("fairySouls"), FairySoulLocations.class);
         } catch (IOException exception8) {
            CrashReporter.method5(exception8, "Loading SkyBlock Collected Fairy Souls");
         }
      }
   }

   private void method13() {
      String text1 = this.field10.method5();
      if (text1 != null) {
         try {
            Object obj2;
            if (field8.exists()) {
               try {
                  obj2 = JsonParser.parseReader(new FileReader(field8));
               } catch (JsonParseException jsonparseexception12) {
                  obj2 = new JsonObject();
               }
            } else {
               obj2 = new JsonObject();
            }

            if (!obj2.isJsonObject()) {
               return;
            }

            String text3 = this.mc.bridge$getSession().bridge$getProfile().getId().toString();
            JsonObject json4 = obj2.getAsJsonObject();
            JsonObject json5 = json4.getAsJsonObject(text3);
            if (json5 == null) {
               json5 = new JsonObject();
            }

            JsonObject json6 = LunarConstants.field22.toJsonTree(this.field13).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field8)) {
               LunarConstants.field22.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Collected Fairy Souls");
         }
      }
   }

   private Set<Vector3i> method5(SkyblockIsland gui2extension31) {
      return ((Skyblock)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1()).method15().method41().method1(gui2extension31);
   }

   public String getId() {
      return "SKYBLOCK_FAIRY_SOULS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{
               this.field11,
               OptionFactory.method14("collectIsland").method4(() -> {
                  SkyblockIsland gui2extension31xx = IslandUtils.getIsland();
                  if (this.field13.method3(gui2extension31xx, this.method5(gui2extension31xx))) {
                     this.method13();
                  }
               }).method31(),
               OptionFactory.method14("uncollectIsland").method4(() -> this.field13.method4().remove(IslandUtils.getIsland())).method31()
            }
         )
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field12}));
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
