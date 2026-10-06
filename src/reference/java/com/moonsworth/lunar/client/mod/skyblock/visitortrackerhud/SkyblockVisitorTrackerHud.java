package com.moonsworth.lunar.client.mod.skyblock.visitortrackerhud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.nameplate.VisitorTrackerDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockVisitorTrackerHud extends AbstractFeature {
   private final ProfileIdListener field8 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private static final Gson field9 = new GsonBuilder().registerTypeAdapter(SkyblockVisitorTrackerHud.Data.class, new VisitorTrackerDeserializer()).create();
   private static final File field10 = new File(LunarConstants.field25 + File.separator + "skyblock_visitors.json");
   private static final Pattern field11 = Pattern.compile("^OFFER ACCEPTED with ([\\w ]+) \\((?<rarity>[A-Z]+)\\)$");
   private static final Pattern field12 = Pattern.compile("^ {4}(\\+(?<amount>[\\d.k]+) )?(?<drop>[\\w ]+)$");
   private static final NumberFormat field13 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final ResourceLocationBridge FANCY_CROSS_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/dungeon/map_icon/fancy_cross.png");
   private static final ResourceLocationBridge FANCY_GREEN_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/dungeon/map_icon/fancy_green.png");
   private static final ResourceLocationBridge FLOWERING_BOUQUET_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/flowering_bouquet.png");
   private static final ResourceLocationBridge GREEN_BANDANA_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/green_bandana.png");
   private static final ResourceLocationBridge OVERGROWN_GRASS_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/overgrown_grass.png");
   private static final ResourceLocationBridge MUSIC_RUNE_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/music_rune.png");
   private static final ResourceLocationBridge COPPER_DYE_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/copper_dye.png");
   private final ToggleOption skyblockShowVisitorStats = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowVisitorStats"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowGardenXP = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowGardenXP"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowFarmingXP = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowFarmingXP"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowCopper = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowCopper"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowBits = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowBits"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowFloweringBouquet = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowFloweringBouquet"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowGreenBandana = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowGreenBandana"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowOvergrownGrass = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowOvergrownGrass"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowDedicationFour = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowDedicationFour"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowMusicRune = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowMusicRune"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowCopperDye = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowCopperDye"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockShowSpaceHelmet = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowSpaceHelmet"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ItemStackBridge spaceHelmetIcon;
   private SkyblockVisitorTrackerHud.Data visitorData = new SkyblockVisitorTrackerHud.Data();
   private boolean resetConfirmPending;

   public SkyblockVisitorTrackerHud(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockVisitorTrackerHud.VisitorTrackerHudElement()));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method53(() -> this.method3(new SkyblockProfileChangeEvent(null, this.field8.method5())));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
      this.handle(EventRenderSlot.class, this::method2);
      this.handle(SkyblockProfileChangeEvent.class, this::method3);
      ItemBridge bridge6_42 = (ItemBridge)Bridge.method28().method94().get(Ref.MC_VERSION >= 33 ? 14 : 0);
      this.spaceHelmetIcon = Bridge.method8().method38(bridge6_42);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         Matcher matcher3 = field11.matcher(text2);
         if (matcher3.matches()) {
            String text8 = matcher3.group("rarity");
            this.visitorData.method1(text8, true);
            this.method13();
         } else {
            Matcher matcher4 = field12.matcher(text2);
            if (matcher4.matches()) {
               String text6 = matcher4.group("amount");
               String text7 = matcher4.group("drop");
               int number5;
               if (text6 == null) {
                  number5 = 1;
               } else if (text6.endsWith("k")) {
                  number5 = (int)(Double.parseDouble(text6.replaceAll("k", "")) * 1000.0);
               } else {
                  number5 = Integer.parseInt(text6);
               }

               this.visitorData.method2(number5, text7);
               this.method13();
            }
         }
      }
   }

   private void method2(EventRenderSlot highlightimpl51) {
      if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         SlotBridge bridge3_182 = highlightimpl51.method5();
         if (bridge3_182 != null && bridge3_182.bridge$getIndex() == 33) {
            ItemStackBridge bridgeextension_43 = bridge3_182.bridge$getItemStack();
            if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
               if (ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_43.bridge$getDisplayName()).equals("Refuse Offer")) {
                  ItemStackBridge bridgeextension_44 = ((SlotBridge)highlightimpl51.method4().bridge$inventorySlots().get(13)).bridge$getItemStack();
                  if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty()) {
                     List list5 = SkyblockItemUtil.method14(bridgeextension_44);
                     String text6 = ChatFormatting.getTextWithoutFormattingCodes((String)list5.get(0));
                     this.visitorData.method1(text6, false);
                     this.method13();
                  }
               }
            }
         }
      }
   }

   private void method3(SkyblockProfileChangeEvent data151) {
      this.visitorData = new SkyblockVisitorTrackerHud.Data();
      if (field10.exists()) {
         try {
            String text2 = data151.method2();
            JsonElement element3 = JsonParser.parseReader(new FileReader(field10));
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

            this.visitorData = (SkyblockVisitorTrackerHud.Data)field9.fromJson(json6.getAsJsonObject(text2), SkyblockVisitorTrackerHud.Data.class);
         } catch (IOException exception7) {
            CrashReporter.method5(exception7, "Loading SkyBlock Garden Visitors");
         }
      }
   }

   private void method13() {
      String text1 = this.field8.method5();
      if (text1 != null) {
         try {
            Object obj2;
            if (field10.exists()) {
               try {
                  obj2 = JsonParser.parseReader(new FileReader(field10));
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

            JsonObject json6 = field9.toJsonTree(this.visitorData).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field10)) {
               field9.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Garden Visitors");
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method2(
            new OptionProvider[]{
               this.skyblockShowVisitorStats,
               this.skyblockShowGardenXP,
               this.skyblockShowFarmingXP,
               this.skyblockShowCopper,
               this.skyblockShowBits,
               this.skyblockShowFloweringBouquet,
               this.skyblockShowGreenBandana,
               this.skyblockShowOvergrownGrass,
               this.skyblockShowDedicationFour,
               this.skyblockShowMusicRune,
               this.skyblockShowCopperDye,
               this.skyblockShowSpaceHelmet,
               OptionFactory.method14("reset").method4(() -> this.resetConfirmPending = true).method17(() -> this.resetConfirmPending),
               OptionFactory.method14("confirm").method4(() -> {
                  this.visitorData = new SkyblockVisitorTrackerHud.Data();
                  this.method13();
                  this.resetConfirmPending = false;
               }).method17(() -> !this.resetConfirmPending)
            }
         )
      );
   }

   public String getId() {
      return "SKYBLOCK_VISITOR_TRACKER_HUD";
   }

   private static SkyblockVisitorTrackerHud.Data method14() {
      SkyblockVisitorTrackerHud.Data data0 = new SkyblockVisitorTrackerHud.Data();
      data0.method4().put("UNCOMMON", 214);
      data0.method4().put("RARE", 148);
      data0.method4().put("LEGENDARY", 53);
      data0.method4().put("MYTHIC", 11);
      data0.method4().put("SPECIAL", 2);
      data0.method18(428);
      data0.method19(391);
      data0.method20(37);
      data0.method21(1284500L);
      data0.method22(3675000L);
      data0.method23(48250);
      data0.method24(12400);
      data0.method25(3);
      data0.method26(1);
      data0.method27(2);
      data0.method28(1);
      data0.method29(2);
      data0.method30(4);
      data0.method31(1);
      return data0;
   }

   public static class Data {
      @SerializedName("total")
      private int total;
      @SerializedName("rarities")
      private final HashMap<String, Integer> rarities = new HashMap<>();
      @SerializedName("accepted")
      private int acceptedCount;
      @SerializedName("denied")
      private int deniedCount;
      @SerializedName("gardenXP")
      private long gardenXp;
      @SerializedName("farmingXP")
      private long farmingXp;
      @SerializedName("copper")
      private int copper;
      @SerializedName("bits")
      private int bits;
      @SerializedName("floweringBouquet")
      private int floweringBouquet;
      @SerializedName("greenBandana")
      private int field8;
      @SerializedName("overgrownGrass")
      private int field9;
      @SerializedName("dedicationFour")
      private int field10;
      @SerializedName("musicRune")
      private int field11;
      @SerializedName("copperDye")
      private int field12;
      @SerializedName("spaceHelmet")
      private int field13;

      public Data() {
         this.rarities.put("UNCOMMON", 0);
         this.rarities.put("RARE", 0);
         this.rarities.put("LEGENDARY", 0);
         this.rarities.put("MYTHIC", 0);
         this.rarities.put("SPECIAL", 0);
      }

      public void method1(String text1, boolean flag2) {
         this.total++;
         this.rarities.put(text1, this.rarities.get(text1) + 1);
         if (flag2) {
            this.acceptedCount++;
         } else {
            this.deniedCount++;
         }
      }

      public void method2(int number1, String text2) {
         switch (text2) {
            case "Garden Experience":
               this.gardenXp += number1;
               break;
            case "Farming XP":
               this.farmingXp += number1;
               break;
            case "Copper":
               this.copper += number1;
               break;
            case "Bits":
               this.bits += number1;
               break;
            case "Flowering Bouquet":
               this.floweringBouquet += number1;
               break;
            case "Green Bandana":
               this.field8 += number1;
               break;
            case "Overgrown Grass":
               this.field9 += number1;
               break;
            case "Dedication IV":
               this.field10 += number1;
               break;
            case "◆ Music Rune I":
               this.field11 += number1;
               break;
            case "Copper Dye":
               this.field12 += number1;
               break;
            case "Space Helmet":
               this.field13 += number1;
         }
      }

      public String get(String text1) {
         return SkyblockVisitorTrackerHud.field13.format(this.rarities.get(text1));
      }

      @Generated
      public int method3() {
         return this.total;
      }

      @Generated
      public HashMap<String, Integer> method4() {
         return this.rarities;
      }

      @Generated
      public int method5() {
         return this.acceptedCount;
      }

      @Generated
      public int method6() {
         return this.deniedCount;
      }

      @Generated
      public long method7() {
         return this.gardenXp;
      }

      @Generated
      public long method8() {
         return this.farmingXp;
      }

      @Generated
      public int method9() {
         return this.copper;
      }

      @Generated
      public int method10() {
         return this.bits;
      }

      @Generated
      public int method11() {
         return this.floweringBouquet;
      }

      @Generated
      public int method12() {
         return this.field8;
      }

      @Generated
      public int method13() {
         return this.field9;
      }

      @Generated
      public int method14() {
         return this.field10;
      }

      @Generated
      public int method15() {
         return this.field11;
      }

      @Generated
      public int method16() {
         return this.field12;
      }

      @Generated
      public int method17() {
         return this.field13;
      }

      @Generated
      public void method18(int number1) {
         this.total = number1;
      }

      @Generated
      public void method19(int number1) {
         this.acceptedCount = number1;
      }

      @Generated
      public void method20(int number1) {
         this.deniedCount = number1;
      }

      @Generated
      public void method21(long number1) {
         this.gardenXp = number1;
      }

      @Generated
      public void method22(long number1) {
         this.farmingXp = number1;
      }

      @Generated
      public void method23(int number1) {
         this.copper = number1;
      }

      @Generated
      public void method24(int number1) {
         this.bits = number1;
      }

      @Generated
      public void method25(int number1) {
         this.floweringBouquet = number1;
      }

      @Generated
      public void method26(int number1) {
         this.field8 = number1;
      }

      @Generated
      public void method27(int number1) {
         this.field9 = number1;
      }

      @Generated
      public void method28(int number1) {
         this.field10 = number1;
      }

      @Generated
      public void method29(int number1) {
         this.field11 = number1;
      }

      @Generated
      public void method30(int number1) {
         this.field12 = number1;
      }

      @Generated
      public void method31(int number1) {
         this.field13 = number1;
      }
   }

   private class VisitorTrackerHudElement extends TypedHudRenderer<List<HudLine>> {
      public VisitorTrackerHudElement() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 160, 320, 100, 200, 500);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         SkyblockVisitorTrackerHud.Data data2 = flag1 ? SkyblockVisitorTrackerHud.method14() : SkyblockVisitorTrackerHud.this.visitorData;
         ArrayList list3 = new ArrayList();
         list3.add(new HudLine(Component.text("Visitor Tracker", NamedTextColor.AQUA, new TextDecoration[]{TextDecoration.BOLD})));
         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowVisitorStats.get()) {
            list3.add(
               new HudLine(
                  TextComponentFactory.builder()
                     .method2(SkyblockVisitorTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("total", new Object[0]))
                     .method4(SkyblockVisitorTrackerHud.field13.format(data2.method3()))
                     .build()
               )
            );
            list3.add(
               new HudLine(
                  ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(
                                             data2.get("UNCOMMON"), NamedTextColor.GREEN
                                          )
                                          .append(Component.text("-", NamedTextColor.GRAY)))
                                       .append(Component.text(data2.get("RARE"), NamedTextColor.BLUE)))
                                    .append(Component.text("-", NamedTextColor.GRAY)))
                                 .append(Component.text(data2.get("LEGENDARY"), NamedTextColor.GOLD)))
                              .append(Component.text("-", NamedTextColor.GRAY)))
                           .append(Component.text(data2.get("MYTHIC"), NamedTextColor.LIGHT_PURPLE)))
                        .append(Component.text("-", NamedTextColor.GRAY)))
                     .append(Component.text(data2.get("SPECIAL"), NamedTextColor.RED))
               )
            );
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.FANCY_GREEN_TEXTURE,
                  TextComponentFactory.builder()
                     .method2(SkyblockVisitorTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("accepted", new Object[0]))
                     .method4(SkyblockVisitorTrackerHud.field13.format(data2.method5()))
                     .method5(NamedTextColor.GREEN)
                     .method7(data2.method5() == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.FANCY_CROSS_TEXTURE,
                  TextComponentFactory.builder()
                     .method2(SkyblockVisitorTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("denied", new Object[0]))
                     .method4(SkyblockVisitorTrackerHud.field13.format(data2.method6()))
                     .method5(NamedTextColor.RED)
                     .method7(data2.method6() == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowGardenXP.get()) {
            String text4 = SkyblockVisitorTrackerHud.field13.format(data2.method7());
            list3.add(
               new HudLine(
                  Bridge.method28().method45(),
                  TextComponentFactory.builder()
                     .method2("Garden XP")
                     .method4(text4)
                     .method5(NamedTextColor.GOLD)
                     .method7(text4.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowFarmingXP.get()) {
            String text5 = SkyblockVisitorTrackerHud.field13.format(data2.method8());
            list3.add(
               new HudLine(
                  Bridge.method28().method48(),
                  TextComponentFactory.builder()
                     .method2("Farming XP")
                     .method4(text5)
                     .method5(NamedTextColor.GOLD)
                     .method7(text5.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowCopper.get()) {
            String text6 = SkyblockVisitorTrackerHud.field13.format(data2.method9());
            list3.add(
               new HudLine(
                  Bridge.method28().method57(),
                  TextComponentFactory.builder()
                     .method2("Copper")
                     .method4(text6)
                     .method5(NamedTextColor.RED)
                     .method7(text6.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowBits.get()) {
            String text7 = SkyblockVisitorTrackerHud.field13.format(data2.method10());
            list3.add(
               new HudLine(
                  Bridge.method28().method28(),
                  TextComponentFactory.builder()
                     .method2("Bits")
                     .method4(text7)
                     .method5(NamedTextColor.AQUA)
                     .method7(text7.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowFloweringBouquet.get()) {
            String text8 = SkyblockVisitorTrackerHud.field13.format(data2.method11());
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.FLOWERING_BOUQUET_TEXTURE,
                  TextComponentFactory.builder()
                     .method2("Flowering Bouquet")
                     .method4(text8)
                     .method5(NamedTextColor.BLUE)
                     .method7(text8.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowGreenBandana.get()) {
            String text9 = SkyblockVisitorTrackerHud.field13.format(data2.method12());
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.GREEN_BANDANA_TEXTURE,
                  TextComponentFactory.builder()
                     .method2("Green Bandana")
                     .method4(text9)
                     .method5(NamedTextColor.DARK_GREEN)
                     .method7(text9.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowOvergrownGrass.get()) {
            String text10 = SkyblockVisitorTrackerHud.field13.format(data2.method13());
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.OVERGROWN_GRASS_TEXTURE,
                  TextComponentFactory.builder()
                     .method2("Overgrown Grass")
                     .method4(text10)
                     .method5(NamedTextColor.BLUE)
                     .method7(text10.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowDedicationFour.get()) {
            String text11 = SkyblockVisitorTrackerHud.field13.format(data2.method14());
            list3.add(
               new HudLine(
                  Bridge.method28().method24(),
                  TextComponentFactory.builder()
                     .method2("Dedication IV")
                     .method4(text11)
                     .method5(NamedTextColor.GOLD)
                     .method7(text11.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowMusicRune.get()) {
            String text12 = SkyblockVisitorTrackerHud.field13.format(data2.method15());
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.MUSIC_RUNE_TEXTURE,
                  TextComponentFactory.builder()
                     .method2("Music Rune")
                     .method4(text12)
                     .method5(NamedTextColor.AQUA)
                     .method7(text12.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowCopperDye.get()) {
            String text13 = SkyblockVisitorTrackerHud.field13.format(data2.method16());
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.COPPER_DYE_TEXTURE,
                  TextComponentFactory.builder()
                     .method2("Copper Dye")
                     .method4(text13)
                     .method5(NamedTextColor.GRAY)
                     .method7(text13.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockVisitorTrackerHud.this.skyblockShowSpaceHelmet.get()) {
            String text14 = SkyblockVisitorTrackerHud.field13.format(data2.method17());
            list3.add(
               new HudLine(
                  SkyblockVisitorTrackerHud.this.spaceHelmetIcon,
                  TextComponentFactory.builder()
                     .method2("Space Helmet")
                     .method4(text14)
                     .method5(NamedTextColor.RED)
                     .method7(text14.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list3;
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : IslandUtils.getIsland() == SkyblockIsland.GARDEN;
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : IslandUtils.getIsland() == SkyblockIsland.GARDEN;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
