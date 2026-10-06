package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.RabbitCollectionStore;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.RabbitCollection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateRabbit;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.chocolatefactory.SkyblockRabbitCollectionOverlay;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockChocolateFactory extends AbstractFeature {
   private final ProfileIdListener field8 = (ProfileIdListener)this.method70(ProfileIdListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method70(ScreenTitleListener.class);
   private final HighlightTypeListener field10 = (HighlightTypeListener)this.method70(HighlightTypeListener.class);
   public static final long[] field11 = new long[]{150000000L, 1000000000L, 4000000000L, 10000000000L, 30000000000L};
   private final Pattern field12 = Pattern.compile("^Your Barn: (?<rabbits>\\d{1,3})/(?<barnSize>\\d{1,3}) Rabbits$");
   private final Pattern field13 = Pattern.compile("^Status: ACTIVE ((?<hour>\\d)h)?((?<minute>\\d{1,2})m)?((?<second>\\d{1,2})s)?$");
   private final Pattern field14 = Pattern.compile("You bought (?<item>.*?)( x(?<amount>\\d+))?!");
   private final RabbitCollectionStore field15 = new RabbitCollectionStore(this);
   private final SkyblockAffordableRabbit field16;
   private final SkyblockBestRabbit field17;
   private final SkyblockChocolateStats field18;
   private final SkyblockExtraPurchaseStats field19;
   private final SkyblockGoldenRabbit field20;
   private final SkyblockRabbitCollectionOverlay field21;
   private final SkyblockRabbitLevels field22;
   private final SkyblockStrayRabbit field23;
   private final SkyblockChocolateShopHelper field24;
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryShowStats")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryHighlightBest")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryHighlightAffordable")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryShowExtraStats")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryHighlightStrays")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryGoldenAlert")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field31 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryShowRabbitLevels")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryShowRabbitCollection")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field33 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockChocolateFactoryChocolateShopHelper")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field34 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showInTooltip").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field35 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showBestItemsList").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field36 = (ColorOption)((Data)OptionFactory.method8("titleColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field37 = (ColorOption)((Data)OptionFactory.method8("itemNameColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field38 = (ColorOption)((Data)OptionFactory.method8("coinsPerChocolateColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final ColorOption field39 = (ColorOption)((Data)OptionFactory.method8("skyblockChocolateFactoryAffordableColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12799933))
      .method31();
   private final ColorOption field40 = (ColorOption)((Data)OptionFactory.method8("skyblockChocolateFactoryStrayColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ColorOption field41 = (ColorOption)((Data)OptionFactory.method8("skyblockChocolateFactoryGoldenColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final EnumOption<NamedColorOption> field42 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockChocolateFactoryTitleColor", NamedColorOption.GOLD
      )
      .method31();
   private final EnumOption<NamedColorOption> field43 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockChocolateFactoryTypeColor", NamedColorOption.YELLOW
      )
      .method31();
   private final EnumOption<NamedColorOption> field44 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockChocolateFactoryValueColor", NamedColorOption.GOLD
      )
      .method31();
   private final EnumOption<NamedColorOption> field45 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockChocolateFactoryTimeColor", NamedColorOption.AQUA
      )
      .method31();
   private RabbitCollection field46 = new RabbitCollection();
   private final List<ChocolateRabbit> field47 = new ArrayList<>();
   private long field48;
   private long field49;
   private long field50;
   private long field51;
   private double field52;
   private double field53;
   private double field54 = 1.0;
   private int field55;
   private int field56;
   private int field57;
   private int field58;
   private int field59;
   private int field60;
   private int field61;
   private boolean field62;
   private int field63;
   private Instant field64;
   private long field65;

   public SkyblockChocolateFactory(Skyblock skyblock1) {
      super(false);
      this.field16 = new SkyblockAffordableRabbit(this, this.field27);
      this.field17 = new SkyblockBestRabbit(this, this.field26);
      this.field18 = new SkyblockChocolateStats(this, this.field25);
      this.field19 = new SkyblockExtraPurchaseStats(this, this.field28);
      this.field20 = new SkyblockGoldenRabbit(this, this.field30);
      this.field21 = new SkyblockRabbitCollectionOverlay(this, this.field32);
      this.field22 = new SkyblockRabbitLevels(this, this.field31);
      this.field23 = new SkyblockStrayRabbit(this, this.field29);
      this.field24 = new SkyblockChocolateShopHelper(this, this.field33);
      this.method66(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method66(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method66(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method53(() -> this.field15.method1(new SkyblockProfileChangeEvent(null, this.field8.method5())));
      this.handle(EventScreenInitPost.class, this::method2);
      this.handle(EventSlotUpdate.class, this::method3);
      this.handle(EventSecond.class, this::method4);
      this.handle(SkyblockProfileChangeEvent.class, this.field15::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method5);
   }

   public List<Framework7Extension> method9() {
      return List.of(this.field16, this.field17, this.field18, this.field19, this.field20, this.field21, this.field22, this.field23, this.field24);
   }

   private void method2(EventScreenInitPost data51) {
      if (this.field46.isDirty()) {
         this.field15.method2(this.field8.method5());
      }

      if (this.isInGui()) {
         this.field65 = 0L;
      }
   }

   private void method3(EventSlotUpdate highlightimpl1) {
      if (this.isInGui()) {
         if (highlightimpl1.getSlot() < 54) {
            GuiContainerBridge bridge5extension_32 = this.field9.method6();
            this.field47.clear();
            List list3 = bridge5extension_32.bridge$inventorySlots();
            ItemStackBridge bridgeextension_44 = ((SlotBridge)list3.get(13)).bridge$getItemStack();
            if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty()) {
               String text5 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_44.bridge$getDisplayName());
               this.field48 = NumberUtils.method1(text5.replaceAll("\\D", ""));

               for (String text7 : SkyblockItemUtil.method14(bridgeextension_44)) {
                  String text8 = ChatFormatting.getTextWithoutFormattingCodes(text7);
                  if (text8.startsWith("All-time Chocolate: ")) {
                     String text9 = text8.replaceAll("\\D", "");
                     this.field50 = NumberUtils.method1(text9);
                     break;
                  }
               }
            }

            ItemStackBridge bridgeextension_436 = ((SlotBridge)list3.get(45)).bridge$getItemStack();
            List list37 = SkyblockItemUtil.method14(bridgeextension_436);
            if (!list37.isEmpty()) {
               String text38 = ChatFormatting.getTextWithoutFormattingCodes((String)list37.get(0));
               this.field52 = NumberUtils.method2(text38.replaceAll("[^\\d.]", ""));

               for (String text42 : list37) {
                  String text10 = ChatFormatting.getTextWithoutFormattingCodes(text42);
                  if (text10.startsWith("Total Multiplier: ")) {
                     this.field54 = Math.max(NumberUtils.method2(text10.replaceAll("[^\\d.]", "")), 1.0);
                     this.field53 = this.field52 / this.field54;
                     break;
                  }
               }
            } else if (bridgeextension_436 != null && !bridgeextension_436.bridge$isEmpty()) {
               this.field52 = 0.0;
               this.field54 = 1.0;
               this.field53 = 0.0;
            }

            SlotBridge bridge3_1839 = (SlotBridge)list3.get(27);
            ItemStackBridge bridgeextension_441 = bridge3_1839.bridge$getItemStack();
            if (bridgeextension_441 != null && !bridgeextension_441.bridge$isEmpty()) {
               for (String text46 : SkyblockItemUtil.method14(bridgeextension_441)) {
                  String text11 = ChatFormatting.getTextWithoutFormattingCodes(text46);
                  if (text11.startsWith("Chocolate this Prestige: ")) {
                     String text12 = text11.replaceAll("\\D", "");
                     this.field49 = NumberUtils.method1(text12);
                     break;
                  }
               }

               String text44 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_441.bridge$getDisplayName());
               String text47 = text44.substring(text44.lastIndexOf(" ") + 1);
               if (RomanNumeralParser.isRomanNumeral(text47)) {
                  this.field55 = RomanNumeralParser.romanToInt(text47);
                  if (this.field55 <= field11.length) {
                     this.field51 = field11[this.field55 - 1];
                     long number50 = this.field51 - this.field49 + this.field48;
                     this.field47.add(new ChocolateRabbit(bridge3_1839, bridgeextension_441, number50, 0.0));
                  }
               }
            }

            ItemsBridge bridge2_2145 = Bridge.method28();

            for (int index48 = 28; index48 <= 34; index48++) {
               SlotBridge bridge3_1851 = (SlotBridge)list3.get(index48);
               ItemStackBridge bridgeextension_453 = bridge3_1851.bridge$getItemStack();
               if (bridgeextension_453 != null && !bridgeextension_453.bridge$isEmpty() && bridgeextension_453.bridge$getItem() == bridge2_2145.method5()) {
                  long number13 = this.method6(bridgeextension_453);
                  if (number13 >= 0L) {
                     this.field47.add(new ChocolateRabbit(bridge3_1851, bridgeextension_453, number13, (index48 - 27) * this.field54));
                  }
               }
            }

            SlotBridge bridge3_1849 = (SlotBridge)list3.get(35);
            ItemStackBridge bridgeextension_452 = bridge3_1849.bridge$getItemStack();
            long number54 = this.method6(bridgeextension_452);
            if (number54 >= 0L) {
               this.field47.add(new ChocolateRabbit(bridge3_1849, bridgeextension_452, number54, 0.0));
            }

            for (String text15 : SkyblockItemUtil.method14(bridgeextension_452)) {
               String text16 = ChatFormatting.getTextWithoutFormattingCodes(text15);
               if (text16.startsWith("Your Barn: ")) {
                  Matcher matcher17 = this.field12.matcher(text16);
                  if (matcher17.matches()) {
                     this.field56 = NumberUtils.method3(matcher17.group("rabbits"));
                     this.field57 = NumberUtils.method3(matcher17.group("barnSize"));
                  }
                  break;
               }
            }

            SlotBridge bridge3_1855 = (SlotBridge)list3.get(37);
            ItemStackBridge bridgeextension_456 = bridge3_1855.bridge$getItemStack();
            long number57 = this.method6(bridgeextension_456);
            if (number57 >= 0L) {
               this.field47.add(new ChocolateRabbit(bridge3_1855, bridgeextension_456, number57, 0.0));
            }

            SlotBridge bridge3_1818 = (SlotBridge)list3.get(38);
            ItemStackBridge bridgeextension_419 = bridge3_1818.bridge$getItemStack();
            if (bridgeextension_419 != null && bridgeextension_419.bridge$getItem() == bridge2_2145.method20()) {
               for (String text21 : SkyblockItemUtil.method14(bridgeextension_419)) {
                  String text22 = ChatFormatting.getTextWithoutFormattingCodes(text21);
                  if (text22.startsWith("Status: ACTIVE ")) {
                     this.field62 = true;
                     Matcher matcher23 = this.field13.matcher(text22);
                     if (matcher23.matches()) {
                        String text24 = matcher23.group("hour");
                        long number25 = text24 == null ? 0L : NumberUtils.method1(text24);
                        String text27 = matcher23.group("minute");
                        long number28 = text27 == null ? 0L : NumberUtils.method1(text27);
                        String text30 = matcher23.group("second");
                        long number31 = text30 == null ? 0L : NumberUtils.method1(text30);
                        this.field64 = Instant.now().plusSeconds(number25 * 60L * 60L + number28 * 60L + number31);
                     }
                  } else if (text22.equals("Status: INACTIVE")) {
                     this.field62 = false;
                     this.field64 = null;
                  } else if (text22.startsWith("Charges: ")) {
                     String text61 = text22.substring(text22.indexOf(" ") + 1, text22.indexOf("/"));
                     this.field63 = NumberUtils.method3(text61);
                  } else if (text22.endsWith("x Chocolate per second")) {
                     long number62 = this.method6(bridgeextension_419);
                     if (number62 >= 0L) {
                        double value64;
                        if (this.field62) {
                           double value66 = NumberUtils.method2(text22.replaceAll("[^\\d.]", ""));
                           double value29 = this.field54 - (value66 - 0.1);
                           double value74 = value29 + 0.1;
                           value64 = this.field53 * value74 - this.field53 * value29;
                        } else {
                           double value67 = this.field54 + 0.1;
                           value64 = this.field53 * value67 - this.field52;
                        }

                        this.field47.add(new ChocolateRabbit(bridge3_1818, bridgeextension_419, number62, value64 / 8.0));
                     }
                  }
               }
            }

            SlotBridge bridge3_1858 = (SlotBridge)list3.get(39);
            ItemStackBridge bridgeextension_459 = bridge3_1858.bridge$getItemStack();
            long number60 = this.method6(bridgeextension_459);
            if (number60 >= 0L) {
               this.field47.add(new ChocolateRabbit(bridge3_1858, bridgeextension_459, number60, 0.0));
            }

            SlotBridge bridge3_1863 = (SlotBridge)list3.get(41);
            ItemStackBridge bridgeextension_465 = bridge3_1863.bridge$getItemStack();
            long number26 = this.method6(bridgeextension_465);
            if (number26 >= 0L) {
               double value68 = this.field54 + 0.01;
               double value71 = this.field53 * value68 - this.field52;
               this.field47.add(new ChocolateRabbit(bridge3_1863, bridgeextension_465, number26, value71));
            }

            ItemStackBridge bridgeextension_469 = ((SlotBridge)list3.get(50)).bridge$getItemStack();
            List list70 = SkyblockItemUtil.method14(bridgeextension_469);

            for (int index72 = 0; index72 < list70.size() - 1; index72++) {
               String text75 = ChatFormatting.getTextWithoutFormattingCodes((String)list70.get(index72));
               if (text75.startsWith("Rabbits Found: ")) {
                  String text32 = ChatFormatting.getTextWithoutFormattingCodes((String)list70.get(index72 + 1));
                  String text33 = text32.substring(text32.indexOf("/") + 1).replaceAll("\\D", "");
                  this.field60 = NumberUtils.method3(text33);
                  break;
               }
            }

            ItemStackBridge bridgeextension_473 = ((SlotBridge)list3.get(51)).bridge$getItemStack();

            for (String text78 : SkyblockItemUtil.method14(bridgeextension_473)) {
               String text80 = ChatFormatting.getTextWithoutFormattingCodes(text78);
               if (text80.startsWith("Available eggs: ")) {
                  this.field58 = NumberUtils.method3(text80.replaceAll("\\D", ""));
               } else if (text80.startsWith("Purchased slots: ")) {
                  String text34 = text80.substring(text80.indexOf(":") + 2, text80.indexOf("/"));
                  this.field59 = NumberUtils.method3(text34);
               }
            }

            ItemStackBridge bridgeextension_477 = ((SlotBridge)list3.get(52)).bridge$getItemStack();

            for (String text81 : SkyblockItemUtil.method14(bridgeextension_477)) {
               String text82 = ChatFormatting.getTextWithoutFormattingCodes(text81);
               if (text82.startsWith("You are #")) {
                  String text35 = text82.replaceAll("\\D", "");
                  this.field61 = NumberUtils.method3(text35);
               }
            }

            this.field47.sort(Comparator.comparing(ChocolateRabbit::method1));
         }
      }
   }

   private void method4(EventSecond highlightimpl41) {
      if (!this.isInGui()) {
         if (this.field52 != 0.0) {
            this.field65 = this.field65 + (long)this.field52;
         }
      }
   }

   private void method5(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (text2.equals("Hitman slot purchased!")) {
         this.field59++;
      } else {
         HashMap map3 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method15();
         if (map3 == null) {
            return;
         }

         Matcher matcher4 = this.field14.matcher(text2);
         if (!matcher4.matches()) {
            return;
         }

         String text5 = matcher4.group("item");
         String text6 = matcher4.group("amount");
         int number7 = text6 == null ? 1 : NumberUtils.method3(text6);
         this.field65 = this.field65 - map3.getOrDefault(text5, 0L) * number7;
      }
   }

   private long method6(ItemStackBridge bridgeextension_41) {
      List list2 = SkyblockItemUtil.method14(bridgeextension_41);

      for (int index3 = 0; index3 < list2.size() - 1; index3++) {
         String text4 = ChatFormatting.getTextWithoutFormattingCodes((String)list2.get(index3));
         if (text4.equals("Cost")) {
            String text5 = ChatFormatting.getTextWithoutFormattingCodes((String)list2.get(index3 + 1));
            if (text5.endsWith(" Chocolate")) {
               String text6 = text5.replaceAll("\\D", "");
               return NumberUtils.method1(text6);
            }
         }
      }

      return -1L;
   }

   private boolean isInGui() {
      return this.field10.method7() == SkyblockMenuType.CHOCOLATE_FACTORY;
   }

   public String getId() {
      return "SKYBLOCK_CHOCOLATE_FACTORY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         this.field25,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
               this.field42,
               this.field43,
               this.field44,
               this.field45,
               ((MixinCore9Extension)this.field18.method7(ModTraits.field1)).method9()
            }
         )
      );
      lightingextension231.method9(new ClientOption[]{this.field26});
      lightingextension231.method7(this.field27, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field39}));
      lightingextension231.method9(new ClientOption[]{this.field39, this.field28});
      lightingextension231.method7(this.field29, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field40}));
      lightingextension231.method7(this.field30, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field41}));
      lightingextension231.method9(new ClientOption[]{this.field31});
      lightingextension231.method7(
         this.field32,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{((MixinCore9Extension)this.field21.method7(ModTraits.field1)).method9()}
         )
      );
      lightingextension231.method7(
         this.field33,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
               this.field34,
               this.field35,
               this.field36,
               this.field37,
               this.field38,
               ((MixinCore9Extension)this.field24.method7(ModTraits.field1)).method9()
            }
         )
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   @Generated
   public ProfileIdListener method13() {
      return this.field8;
   }

   @Generated
   public ScreenTitleListener method14() {
      return this.field9;
   }

   @Generated
   public HighlightTypeListener method15() {
      return this.field10;
   }

   @Generated
   public Pattern method16() {
      return this.field12;
   }

   @Generated
   public Pattern method17() {
      return this.field13;
   }

   @Generated
   public Pattern method19() {
      return this.field14;
   }

   @Generated
   public RabbitCollectionStore method21() {
      return this.field15;
   }

   @Generated
   public SkyblockAffordableRabbit method22() {
      return this.field16;
   }

   @Generated
   public SkyblockBestRabbit method23() {
      return this.field17;
   }

   @Generated
   public SkyblockChocolateStats method24() {
      return this.field18;
   }

   @Generated
   public SkyblockExtraPurchaseStats method25() {
      return this.field19;
   }

   @Generated
   public SkyblockGoldenRabbit method26() {
      return this.field20;
   }

   @Generated
   public SkyblockRabbitCollectionOverlay method27() {
      return this.field21;
   }

   @Generated
   public SkyblockRabbitLevels method28() {
      return this.field22;
   }

   @Generated
   public SkyblockStrayRabbit method29() {
      return this.field23;
   }

   @Generated
   public SkyblockChocolateShopHelper method30() {
      return this.field24;
   }

   @Generated
   public ToggleOption method34() {
      return this.field25;
   }

   @Generated
   public ToggleOption method35() {
      return this.field26;
   }

   @Generated
   public ToggleOption method36() {
      return this.field27;
   }

   @Generated
   public ToggleOption method37() {
      return this.field28;
   }

   @Generated
   public ToggleOption method38() {
      return this.field29;
   }

   @Generated
   public ToggleOption method39() {
      return this.field30;
   }

   @Generated
   public ToggleOption method40() {
      return this.field31;
   }

   @Generated
   public ToggleOption method41() {
      return this.field32;
   }

   @Generated
   public ToggleOption method42() {
      return this.field33;
   }

   @Generated
   public ToggleOption method43() {
      return this.field34;
   }

   @Generated
   public ToggleOption method44() {
      return this.field35;
   }

   @Generated
   public ColorOption method45() {
      return this.field36;
   }

   @Generated
   public ColorOption method46() {
      return this.field37;
   }

   @Generated
   public ColorOption method47() {
      return this.field38;
   }

   @Generated
   public ColorOption method48() {
      return this.field39;
   }

   @Generated
   public ColorOption method49() {
      return this.field40;
   }

   @Generated
   public ColorOption method50() {
      return this.field41;
   }

   @Generated
   public EnumOption<NamedColorOption> method51() {
      return this.field42;
   }

   @Generated
   public EnumOption<NamedColorOption> method52() {
      return this.field43;
   }

   @Generated
   public EnumOption<NamedColorOption> method53() {
      return this.field44;
   }

   @Generated
   public EnumOption<NamedColorOption> method54() {
      return this.field45;
   }

   @Generated
   public RabbitCollection method55() {
      return this.field46;
   }

   @Generated
   public List<ChocolateRabbit> method56() {
      return this.field47;
   }

   @Generated
   public long method57() {
      return this.field48;
   }

   @Generated
   public long method58() {
      return this.field49;
   }

   @Generated
   public long method59() {
      return this.field50;
   }

   @Generated
   public long method60() {
      return this.field51;
   }

   @Generated
   public double method61() {
      return this.field52;
   }

   @Generated
   public double method62() {
      return this.field53;
   }

   @Generated
   public double method63() {
      return this.field54;
   }

   @Generated
   public int method64() {
      return this.field55;
   }

   @Generated
   public int method65() {
      return this.field56;
   }

   @Generated
   public int method66() {
      return this.field57;
   }

   @Generated
   public int method67() {
      return this.field58;
   }

   @Generated
   public int method68() {
      return this.field59;
   }

   @Generated
   public int method69() {
      return this.field60;
   }

   @Generated
   public int method70() {
      return this.field61;
   }

   @Generated
   public boolean method71() {
      return this.field62;
   }

   @Generated
   public int method72() {
      return this.field63;
   }

   @Generated
   public Instant method73() {
      return this.field64;
   }

   @Generated
   public long method74() {
      return this.field65;
   }

   @Generated
   public void method66(RabbitCollection fishing41) {
      this.field46 = fishing41;
   }
}
