package com.moonsworth.lunar.client.mod.skyblock.harvestfeasttrackerhud;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.CropType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockElectionListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.nameplate.CropTracker;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockHarvestFeastTrackerHud extends AbstractFeature {
   private static final File field8 = new File(LunarConstants.field25 + File.separator + "skyblock_harvest_feast_drops.json");
   private static final Pattern field9 = Pattern.compile("^RARE CROP! (?<item>[\\w ]+)(?: \\(.+\\))*$");
   private static final NumberFormat field10 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private final ProfileIdListener field11 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private final SkyblockElectionListener field12 = (SkyblockElectionListener)this.method63(SkyblockElectionListener.class);
   private final CropTracker field13 = (CropTracker)this.method63(CropTracker.class);
   private final ToggleOption field14 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "onlyShowDuringHarvestFeast"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("onlyShowFarmedDrop").method31();
   private final MultiSelectOption field16 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "dropsToShow"
         )
         .method3(SkyblockHarvestFeastTrackerHud.Type.getItemNames())
         .method2(SkyblockHarvestFeastTrackerHud.Type.getItemNames()))
      .method31();
   private SkyblockHarvestFeastTrackerHud.Data field17 = new SkyblockHarvestFeastTrackerHud.Data();
   private boolean field18;

   public SkyblockHarvestFeastTrackerHud(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockHarvestFeastTrackerHud.HarvestFeastHudElement()));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.GARDEN));
      this.method53(() -> this.method3(new SkyblockProfileChangeEvent(null, this.field11.method5())));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
      this.handle(SkyblockProfileChangeEvent.class, this::method3);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (this.method13()) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         Matcher matcher3 = field9.matcher(text2);
         if (matcher3.matches()) {
            this.field17.addDrop(matcher3.group("item"));
            this.method14();
         }
      }
   }

   private boolean method13() {
      return this.field12.method2("Grand Feast") || SkyblockCalendar.isAutumn();
   }

   private void method3(SkyblockProfileChangeEvent data151) {
      this.field17 = new SkyblockHarvestFeastTrackerHud.Data();
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

            this.field17 = (SkyblockHarvestFeastTrackerHud.Data)LunarConstants.field22
               .fromJson(json6.getAsJsonObject(text2), SkyblockHarvestFeastTrackerHud.Data.class);
         } catch (IOException exception7) {
            CrashReporter.method5(exception7, "Loading SkyBlock Harvest Feast Drops");
         }
      }
   }

   private void method14() {
      String text1 = this.field11.method5();
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

            JsonObject json6 = LunarConstants.field22.toJsonTree(this.field17).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field8)) {
               LunarConstants.field22.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Harvest Feast Drops");
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_HARVEST_FEAST_TRACKER_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.HUD, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14, this.field15});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}).method3(this.field15::get);
      });
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new OptionProvider[]{
               OptionFactory.method14("reset").method4(() -> this.field18 = true).method17(() -> this.field18),
               OptionFactory.method14("confirm").method4(() -> {
                  this.field17 = new SkyblockHarvestFeastTrackerHud.Data();
                  this.method14();
                  this.field18 = false;
               }).method17(() -> !this.field18)
            }
         )
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   @KeepName
   private static class Data {
      private final transient Map<String, SkyblockHarvestFeastTrackerHud.Type> itemToDrop = new HashMap<>();
      private final transient Map<CropType, SkyblockHarvestFeastTrackerHud.Type> cropToDrop = new HashMap<>();
      private final Map<SkyblockHarvestFeastTrackerHud.Type, Integer> drops;

      public Data() {
         for (SkyblockHarvestFeastTrackerHud.Type type4 : SkyblockHarvestFeastTrackerHud.Type.values()) {
            this.itemToDrop.put(type4.getItemName(), type4);
            if (type4.getCrop() != null) {
               this.cropToDrop.put(type4.getCrop(), type4);
            }
         }

         this.drops = new EnumMap<>(SkyblockHarvestFeastTrackerHud.Type.class);
      }

      public void addDrop(String text1) {
         SkyblockHarvestFeastTrackerHud.Type type2 = this.itemToDrop.get(text1);
         if (type2 != null) {
            this.drops.put(type2, this.get(type2) + 1);
         }
      }

      public int get(SkyblockHarvestFeastTrackerHud.Type type1) {
         return this.drops.getOrDefault(type1, 0);
      }

      static SkyblockHarvestFeastTrackerHud.Data preview() {
         SkyblockHarvestFeastTrackerHud.Data data0 = new SkyblockHarvestFeastTrackerHud.Data();
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.SEASONING, 128);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.CORNUCOPIA, 12);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.CARROT_ZEST, 9);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.DEEPFRIES, 7);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.AGGOURDIAN, 4);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.CANE_KNOT, 6);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.MELON_JUICE, 8);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.CACTUS_FLOWER, 5);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.DESIGNER_COFFEE_BEANS, 3);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.FEASTFUNGUS, 2);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.BOTROOT, 4);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.SALTED_SUNFLOWER_SEEDS, 1);
         data0.drops.put(SkyblockHarvestFeastTrackerHud.Type.CRYSTALIZED_MOONLIGHT, 1);
         return data0;
      }

      @Generated
      public Map<String, SkyblockHarvestFeastTrackerHud.Type> getItemToDrop() {
         return this.itemToDrop;
      }

      @Generated
      public Map<CropType, SkyblockHarvestFeastTrackerHud.Type> getCropToDrop() {
         return this.cropToDrop;
      }

      @Generated
      public Map<SkyblockHarvestFeastTrackerHud.Type, Integer> getDrops() {
         return this.drops;
      }
   }

   private class HarvestFeastHudElement extends TypedHudRenderer<List<HudLine>> {
      public HarvestFeastHudElement() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(40, 260, 500, 100, 200, 500);
      }

      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? this.method3(SkyblockHarvestFeastTrackerHud.Data.preview(), CropType.WHEAT)
            : this.method3(SkyblockHarvestFeastTrackerHud.this.field17, SkyblockHarvestFeastTrackerHud.this.field13.method5());
      }

      private List<HudLine> method3(SkyblockHarvestFeastTrackerHud.Data data1, CropType highlighttype32) {
         ArrayList list3 = new ArrayList();
         list3.add(
            new HudLine(
               Component.text(
                  SkyblockHarvestFeastTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("cropTracker", new Object[0]),
                  NamedTextColor.YELLOW,
                  new TextDecoration[]{TextDecoration.BOLD}
               )
            )
         );
         if ((Boolean)SkyblockHarvestFeastTrackerHud.this.field15.get()) {
            int number4 = data1.get(SkyblockHarvestFeastTrackerHud.Type.SEASONING);
            list3.add(
               new HudLine(
                  SkyblockHarvestFeastTrackerHud.Type.SEASONING.getItem(),
                  TextComponentFactory.builder()
                     .method2(SkyblockHarvestFeastTrackerHud.Type.SEASONING.getItemName())
                     .method5(NamedTextColor.GREEN)
                     .method4(SkyblockHarvestFeastTrackerHud.field10.format(number4))
                     .method7(number4 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
            SkyblockHarvestFeastTrackerHud.Type type5 = data1.getCropToDrop().get(highlighttype32);
            if (type5 != null) {
               int number6 = data1.get(type5);
               list3.add(
                  new HudLine(
                     type5.getItem(),
                     TextComponentFactory.builder()
                        .method2(type5.getItemName())
                        .method5(NamedTextColor.GREEN)
                        .method4(SkyblockHarvestFeastTrackerHud.field10.format(number6))
                        .method7(number6 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                        .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                        .build()
                  )
               );
            }
         } else {
            for (SkyblockHarvestFeastTrackerHud.Type type7 : SkyblockHarvestFeastTrackerHud.Type.values()) {
               if (SkyblockHarvestFeastTrackerHud.this.field16.contains(type7.getItemName())) {
                  int number8 = data1.get(type7);
                  list3.add(
                     new HudLine(
                        type7.getItem(),
                        TextComponentFactory.builder()
                           .method2(type7.getItemName())
                           .method5(NamedTextColor.GREEN)
                           .method4(SkyblockHarvestFeastTrackerHud.field10.format(number8))
                           .method7(number8 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                           .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                           .build()
                     )
                  );
               }
            }
         }

         return list3;
      }

      public boolean method4(boolean flag1) {
         if (!super.method4(flag1)) {
            return false;
         } else {
            return flag1 ? true : !(Boolean)SkyblockHarvestFeastTrackerHud.this.field14.get() || SkyblockHarvestFeastTrackerHud.this.method13();
         }
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5().method1(false).method2(false).method8();
      }
   }

   private enum Type {
      SEASONING("Seasoning", Ref.MC_VERSION >= 33 ? Bridge.method28().method66() : null, null),
      CORNUCOPIA("Cornucopia", Ref.MC_VERSION >= 33 ? Bridge.method28().method67() : null, CropType.WHEAT),
      CARROT_ZEST("Carrot Zest", Ref.MC_VERSION >= 33 ? Bridge.method28().method68() : null, CropType.CARROT),
      DEEPFRIES("Deepfries", Ref.MC_VERSION >= 33 ? Bridge.method28().method69() : null, CropType.POTATO),
      AGGOURDIAN("Aggourdian", Ref.MC_VERSION >= 33 ? Bridge.method28().method70() : null, CropType.PUMPKIN),
      CANE_KNOT("Cane Knot", Ref.MC_VERSION >= 33 ? Bridge.method28().method71() : null, CropType.SUGAR_CANE),
      MELON_JUICE("Melon Juice", Ref.MC_VERSION >= 33 ? Bridge.method28().method72() : null, CropType.MELON),
      CACTUS_FLOWER("Cactus Flower", Ref.MC_VERSION >= 33 ? Bridge.method28().method73() : null, CropType.CACTUS),
      DESIGNER_COFFEE_BEANS("Designer Coffee Beans", Ref.MC_VERSION >= 33 ? Bridge.method28().method74() : null, CropType.COCOA_BEANS),
      FEASTFUNGUS("Feastfungus", Ref.MC_VERSION >= 33 ? Bridge.method28().method75() : null, CropType.MUSHROOM),
      BOTROOT("Botroot", Ref.MC_VERSION >= 33 ? Bridge.method28().method76() : null, CropType.NETHER_WART),
      SALTED_SUNFLOWER_SEEDS("Salted Sunflower Seeds", Ref.MC_VERSION >= 33 ? Bridge.method28().method77() : null, CropType.SUNFLOWER),
      CRYSTALIZED_MOONLIGHT("Crystalized Moonlight", Ref.MC_VERSION >= 33 ? Bridge.method28().method78() : null, CropType.MOONFLOWER),
      FLORAL_GELATIN("Floral Gelatin", Ref.MC_VERSION >= 33 ? Bridge.method28().method79() : null, CropType.WILD_ROSE);

      private final String itemName;
      private final ItemBridge item;
      private final CropType crop;

      public static Set<String> getItemNames() {
         return Arrays.stream(values()).map(SkyblockHarvestFeastTrackerHud.Type::getItemName).collect(Collectors.toSet());
      }

      @Generated
      Type(String text3, ItemBridge bridge6_44, CropType highlighttype35) {
         this.itemName = text3;
         this.item = bridge6_44;
         this.crop = highlighttype35;
      }

      @Generated
      public String getItemName() {
         return this.itemName;
      }

      @Generated
      public ItemBridge getItem() {
         return this.item;
      }

      @Generated
      public CropType getCrop() {
         return this.crop;
      }
   }
}
