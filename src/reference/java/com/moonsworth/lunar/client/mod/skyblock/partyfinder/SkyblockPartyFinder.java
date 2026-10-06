package com.moonsworth.lunar.client.mod.skyblock.partyfinder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.AccessoryBagStorage;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.Dungeons;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.Inventory;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.PetsData;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.dungeons.PlayerClasses;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.dungeons.dungeontypes.Catacombs;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.dungeons.dungeontypes.catacombs.FloorStatNumber;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.netherislandplayerdata.KuudraCompletedTiers;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.petsdata.Pet;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.playerdata.Experience;
import com.lunarclient.minecraft.MinecraftUUID;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockProfilesUtil;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.SkillLevelCalculator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItems;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.framework.listener.HypixelPartyTracker;
import com.moonsworth.lunar.client.framework.listener.PartyState;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockPartyFinder extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final HypixelPartyTracker field10 = (HypixelPartyTracker)this.method63(HypixelPartyTracker.class);
   private static final Pattern field11 = Pattern.compile("^Currently Selected: (?<class>Healer|Archer|Tank|Mage|Berserk)$");
   private static final Pattern field12 = Pattern.compile("^ \\w+: (?<class>Healer|Archer|Tank|Mage|Berserk) \\(\\d{1,2}\\)$");
   private static final Pattern field13 = Pattern.compile("^Party Finder > (?<player>\\w+) joined the dungeon group! \\(\\w+ Level \\d+\\)$");
   private static final Pattern field14 = Pattern.compile("^Party Finder > (?<player>\\w+) joined the group! \\(Combat Level \\d+\\)$");
   private static final TextComponent field15 = Component.text(
      "-----------------------------", NamedTextColor.AQUA, new TextDecoration[]{TextDecoration.STRIKETHROUGH, TextDecoration.BOLD}
   );
   private static final NumberFormat field16 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final DecimalFormat field17 = new DecimalFormat("0.00");
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highlightJoinableParty").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highlightMissingClassParty")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field20 = (ColorOption)((Data)OptionFactory.method8("highlightColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showMissingClassesInLore").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPlayerStatsOnJoin").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPlayerStatsAsLeader").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSkillLevels").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCompletions").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAccessoryBag").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showArmorAndEquipment").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showImportantWeapons").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showImportantItems").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showImportantPets").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field31 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showBestTimes").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSecrets").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private DungeonClass field33 = null;
   private final HashSet<SlotBridge> field34 = new HashSet<>();

   public SkyblockPartyFinder(Skyblock skyblock1) {
      super(false);
      this.method12(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method12(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSlotUpdate.class, arg1x -> {
         this.method1(arg1x);
         this.method2(arg1x);
      });
      this.handle(EventRenderHologramItem.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method4);
      this.handle(EventScreenOpen.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method8);
      this.method1(this::onDisable);
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      if (this.field8.method7() == SkyblockMenuType.CATACOMBS_GATE) {
         ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
         if (bridgeextension_42 != null) {
            if (bridgeextension_42.bridge$getDisplayName().endsWith("Dungeon Classes")) {
               for (String text4 : SkyblockItemUtil.method15(bridgeextension_42)) {
                  Matcher matcher5 = field11.matcher(text4);
                  if (matcher5.matches()) {
                     this.field33 = DungeonClass.fromDisplayName(matcher5.group("class"));
                     return;
                  }
               }
            }
         }
      }
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      if ((Boolean)this.field18.get()) {
         if (this.field8.method7() == SkyblockMenuType.PARTY_FINDER) {
            int index2 = highlightimpl1.getSlot();
            if (highlightimpl1.getSlot() >= 0 && highlightimpl1.getSlot() <= 53) {
               SlotBridge bridge3_183 = (SlotBridge)this.field9.method6().bridge$inventorySlots().get(index2);
               ItemStackBridge bridgeextension_44 = highlightimpl1.method3();
               List list5 = SkyblockItemUtil.method15(bridgeextension_44);
               if (this.method5(bridgeextension_44, list5)) {
                  boolean flag6 = true;

                  for (String text8 : list5) {
                     if (text8.startsWith("Requires") || text8.startsWith("✗ ") || text8.equals("Complete previous floor first!")) {
                        flag6 = false;
                        break;
                     }
                  }

                  if ((Boolean)this.field19.get() && (this.field33 == null || this.method7(list5).contains(this.field33))) {
                     flag6 = false;
                  }

                  if (flag6) {
                     this.field34.add(bridge3_183);
                  }
               }
            }
         }
      }
   }

   private void method3(EventRenderHologramItem data51) {
      if ((Boolean)this.field18.get()) {
         if (this.field8.method7() == SkyblockMenuType.PARTY_FINDER) {
            if (this.field34.contains(data51.method3())) {
               data51.method1(this.field20.method14(0.0F));
            }
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      if ((Boolean)this.field21.get()) {
         if (this.field33 != null) {
            if (this.field8.method7() == SkyblockMenuType.PARTY_FINDER) {
               Optional optional2 = data21.method1();
               if (!optional2.isEmpty()) {
                  ItemStackBridge bridgeextension_43 = (ItemStackBridge)optional2.get();
                  List list4 = SkyblockItemUtil.method15(bridgeextension_43);
                  if (this.method5(bridgeextension_43, list4)) {
                     HashSet set5 = new HashSet<>(Arrays.asList(DungeonClass.values()));
                     set5.removeAll(this.method7(list4));
                     if (!set5.isEmpty()) {
                        TextComponent text6 = (TextComponent)Component.empty()
                           .append(Component.text(this.method16("missing", new Object[0]) + ": ", NamedTextColor.RED));
                        boolean flag7 = true;

                        for (DungeonClass hologramstype2_29 : set5) {
                           if (!flag7) {
                              text6 = (TextComponent)text6.append(Component.text(", ", NamedTextColor.GRAY));
                           }

                           NamedTextColor namedtextcolor10 = hologramstype2_29 == this.field33 ? NamedTextColor.GREEN : NamedTextColor.GRAY;
                           text6 = (TextComponent)text6.append(Component.text(hologramstype2_29.getChatDisplayName(), namedtextcolor10));
                           flag7 = false;
                        }

                        List list11 = data21.method3();
                        list11.add((ClickableText)Bridge.method8().method89(text6));
                        data21.method2(list11);
                     }
                  }
               }
            }
         }
      }
   }

   private boolean method5(ItemStackBridge bridgeextension_41, List<String> list2) {
      return !bridgeextension_41.bridge$getDisplayName().endsWith("'s Party") ? false : list2 != null && !list2.isEmpty() && ((String)list2.get(0)).startsWith("Dungeon:");
   }

   private void method6(EventScreenOpen highlightimpl91) {
      this.field34.clear();
   }

   private void onDisable() {
      this.field34.clear();
   }

   private HashSet<DungeonClass> method7(List<String> list1) {
      HashSet set2 = new HashSet();

      for (String text4 : list1) {
         Matcher matcher5 = field12.matcher(text4);
         if (matcher5.matches()) {
            set2.add(DungeonClass.fromDisplayName(matcher5.group("class")));
         }
      }

      return set2;
   }

   private void method8(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.isOnIsland()) {
         if ((Boolean)this.field23.get()) {
            Optional optional2 = this.field10.method7();
            if (optional2.isEmpty() || !Ref.method7().bridge$getName().equals(((PartyState)optional2.get()).method2())) {
               return;
            }
         }

         String text7 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         Runnable runnable3 = null;
         Matcher matcher4 = field13.matcher(text7);
         if (matcher4.matches()) {
            String text5 = matcher4.group("player");
            runnable3 = () -> this.method9(text5);
         } else {
            Matcher matcher8 = field14.matcher(text7);
            if (matcher8.matches()) {
               String text6 = matcher8.group("player");
               runnable3 = () -> this.method10(text6);
            }
         }

         if (runnable3 != null) {
            BackgroundExecutor.method6().execute(runnable3);
         }
      }
   }

   private void method9(String text1) {
      ArrayList list2 = new ArrayList();
      list2.add(field15);
      TextComponent text3 = Component.text("[" + this.method16("hover", new Object[0]) + "]", NamedTextColor.GOLD, new TextDecoration[]{TextDecoration.BOLD});
      Member member4 = this.method11(text1);
      if (member4 == null) {
         Ref.method4().method69().method3(NotificationManager.method15("partyFinderLoadStatsFailed", new Object[]{text1}));
      } else {
         Dungeons dungeons5 = member4.dungeons();
         Catacombs catacombs6 = dungeons5.dungeonTypes().catacombs();
         Catacombs catacombs7 = dungeons5.dungeonTypes().masterCatacombs();
         list2.add(Component.text(this.method16("dungeonStats", new Object[]{text1}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));
         list2.add(Component.empty());
         if ((Boolean)this.field24.get()) {
            double value8 = SkillLevelCalculator.getCatacombsLevel(catacombs6.experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Catacombs"}))
                  .method4(field17.format(value8))
                  .method5(NamedTextColor.RED)
                  .method7(value8 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            PlayerClasses playerclasses10 = dungeons5.playerClasses();
            double value11 = SkillLevelCalculator.getCatacombsLevel(playerclasses10.healer().experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Healer"}))
                  .method4(field17.format(value11))
                  .method5(NamedTextColor.LIGHT_PURPLE)
                  .method7(value11 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            double value13 = SkillLevelCalculator.getCatacombsLevel(playerclasses10.archer().experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Archer"}))
                  .method4(field17.format(value13))
                  .method5(NamedTextColor.GREEN)
                  .method7(value13 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            double value15 = SkillLevelCalculator.getCatacombsLevel(playerclasses10.tank().experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Tank"}))
                  .method4(field17.format(value15))
                  .method5(NamedTextColor.GRAY)
                  .method7(value15 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            double value17 = SkillLevelCalculator.getCatacombsLevel(playerclasses10.mage().experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Mage"}))
                  .method4(field17.format(value17))
                  .method5(NamedTextColor.AQUA)
                  .method7(value17 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            double value19 = SkillLevelCalculator.getCatacombsLevel(playerclasses10.berserk().experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Berserk"}))
                  .method4(field17.format(value19))
                  .method5(NamedTextColor.RED)
                  .method7(value19 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            list2.add(Component.empty());
         }

         if ((Boolean)this.field25.get()) {
            FloorStatNumber floorstatnumber22 = catacombs6.tierCompletions();
            TextComponent text9 = Component.empty();

            for (int index27 = 0; index27 <= 7; index27++) {
               String text31 = Integer.toString(index27);
               double value12 = floorstatnumber22.elm().get(text31).asDouble().orElse(0.0);
               String text14 = index27 == 0 ? "Entrance" : this.method16("floor", new Object[]{text31});
               text9 = (TextComponent)text9.append(
                  TextComponentFactory.builder().method2(text14).method4(field16.format(value12)).method5(NamedTextColor.GOLD).method7(NamedTextColor.WHITE).build()
               );
               if (index27 != 7) {
                  text9 = (TextComponent)text9.appendNewline();
               }
            }

            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("floorCompletions", new Object[0]))
                  .method3(text3)
                  .method5(NamedTextColor.GREEN)
                  .method16(HoverEvent.showText(text9))
                  .build()
            );
            FloorStatNumber floorstatnumber28 = catacombs7.tierCompletions();
            TextComponent text32 = Component.empty();

            for (int index35 = 1; index35 <= 7; index35++) {
               String text38 = Integer.toString(index35);
               double value41 = floorstatnumber28.elm().get(text38).asDouble().orElse(0.0);
               text32 = (TextComponent)text32.append(
                  TextComponentFactory.builder()
                     .method2(this.method16("masterFloor", new Object[]{text38}))
                     .method4(field16.format(value41))
                     .method5(NamedTextColor.GOLD)
                     .method7(NamedTextColor.WHITE)
                     .build()
               );
               if (index35 != 7) {
                  text32 = (TextComponent)text32.appendNewline();
               }
            }

            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("masterFloorCompletions", new Object[0]))
                  .method3(text3)
                  .method5(NamedTextColor.RED)
                  .method16(HoverEvent.showText(text32))
                  .build()
            );
            list2.add(Component.empty());
         }

         if ((Boolean)this.field31.get()) {
            FloorStatNumber floorstatnumber23 = catacombs6.fastestTimeSPlus();
            TextComponent text25 = Component.empty();

            for (int index29 = 1; index29 <= 7; index29++) {
               String text33 = Integer.toString(index29);
               Optional optional36 = floorstatnumber23.elm().get(text33).asDouble();
               TextComponent text39 = optional36.isEmpty()
                  ? Component.text(this.method16("notApplicable", new Object[0]), NamedTextColor.RED)
                  : Component.text(TimeFormatting.method1(((Double)optional36.get()).longValue()), NamedTextColor.WHITE);
               text25 = (TextComponent)text25.append(
                  TextComponentFactory.builder().method2(this.method16("floor", new Object[]{text33})).method3(text39).method5(NamedTextColor.GOLD).build()
               );
               if (index29 != 7) {
                  text25 = (TextComponent)text25.appendNewline();
               }
            }

            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("floorSPlusPersonalBests", new Object[0]))
                  .method3(text3)
                  .method5(NamedTextColor.GREEN)
                  .method16(HoverEvent.showText(text25))
                  .build()
            );
            FloorStatNumber floorstatnumber30 = catacombs7.fastestTimeSPlus();
            TextComponent text34 = Component.empty();

            for (int index37 = 1; index37 <= 7; index37++) {
               String text40 = Integer.toString(index37);
               Optional optional42 = floorstatnumber30.elm().get(text40).asDouble();
               TextComponent text43 = optional42.isEmpty()
                  ? Component.text(this.method16("notApplicable", new Object[0]), NamedTextColor.RED)
                  : Component.text(TimeFormatting.method1(((Double)optional42.get()).longValue()), NamedTextColor.WHITE);
               text34 = (TextComponent)text34.append(
                  TextComponentFactory.builder().method2(this.method16("masterFloor", new Object[]{text40})).method3(text43).method5(NamedTextColor.GOLD).build()
               );
               if (index37 != 7) {
                  text34 = (TextComponent)text34.appendNewline();
               }
            }

            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("masterFloorSPlusPersonalBests", new Object[0]))
                  .method3(text3)
                  .method5(NamedTextColor.GREEN)
                  .method16(HoverEvent.showText(text34))
                  .build()
            );
            list2.add(Component.empty());
         }

         if ((Boolean)this.field32.get()) {
            int number24 = (int)(catacombs6.tierCompletions().total().orElse(0.0) + catacombs7.tierCompletions().total().orElse(0.0));
            double value26 = dungeons5.secrets().orElse(0.0);
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("secretCount", new Object[0]))
                  .method4(field16.format(value26))
                  .method5(NamedTextColor.AQUA)
                  .method7(NamedTextColor.WHITE)
                  .build()
            );
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("secretsPerRun", new Object[0]))
                  .method4(field17.format(value26 / number24))
                  .method5(NamedTextColor.AQUA)
                  .method7(NamedTextColor.WHITE)
                  .build()
            );
            list2.add(Component.empty());
         }

         try {
            if ((Boolean)this.field26.get()) {
               list2.addAll(this.method16(member4));
               list2.add(Component.empty());
            }

            if ((Boolean)this.field27.get()) {
               list2.add(Component.text(this.method16("armor", new Object[0]), NamedTextColor.YELLOW, new TextDecoration[]{TextDecoration.BOLD}));
               list2.addAll(this.method12(member4));
               list2.add(Component.empty());
            }

            if ((Boolean)this.field28.get()) {
               list2.add(Component.text(this.method16("importantWeapons", new Object[0]), NamedTextColor.YELLOW, new TextDecoration[]{TextDecoration.BOLD}));
               list2.addAll(this.method13(member4, true));
               list2.add(Component.empty());
            }

            if ((Boolean)this.field29.get()) {
               list2.add(
                  TextComponentFactory.builder()
                     .method2(this.method16("importantItems", new Object[0]))
                     .method3(text3)
                     .method5(NamedTextColor.RED)
                     .method16(HoverEvent.showText(this.method14(member4, true)))
                     .build()
               );
            }

            if ((Boolean)this.field30.get()) {
               list2.add(
                  TextComponentFactory.builder()
                     .method2(this.method16("importantPets", new Object[0]))
                     .method3(text3)
                     .method5(NamedTextColor.GREEN)
                     .method16(HoverEvent.showText(this.method15(member4, true)))
                     .build()
               );
            }
         } catch (IOException exception21) {
            CrashReporter.method5(exception21, "SkyBlock Party Finder Stats");
         }

         list2.add(field15);
         Ref.method3().bridge$schedule(() -> {
            for (TextComponent text2x : list2) {
               SkyBlockChat.method3(text2x);
            }
         });
      }
   }

   private void method10(String text1) {
      ArrayList list2 = new ArrayList();
      list2.add(field15);
      TextComponent text3 = Component.text("[" + this.method16("hover", new Object[0]) + "]", NamedTextColor.GOLD, new TextDecoration[]{TextDecoration.BOLD});
      Member member4 = this.method11(text1);
      if (member4 == null) {
         Ref.method4().method69().method3(NotificationManager.method15("partyFinderLoadStatsFailed", new Object[]{text1}));
      } else {
         list2.add(Component.text(this.method16("kuudraStats", new Object[]{text1}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));
         list2.add(Component.empty());
         if ((Boolean)this.field24.get()) {
            Experience experience5 = member4.playerData().experience();
            if (!experience5.elm().exists()) {
               list2.add(
                  TextComponentFactory.builder()
                     .method2(this.method16("level", new Object[]{"Catacombs"}))
                     .method4(this.method16("apiOff", new Object[]{"Skills"}))
                     .method5(NamedTextColor.RED)
                     .method7(NamedTextColor.RED)
                     .method14(new TextDecoration[]{TextDecoration.BOLD})
                     .build()
               );
            } else {
               int number6 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1())
                  .method15()
                  .method24()
                  .method1()
                  .getInt(CoordinatesType.COMBAT);
               int number7 = SkillLevelCalculator.getLevel(experience5.skillCombat().orElse(0.0), CoordinatesType.COMBAT);
               list2.add(
                  TextComponentFactory.builder()
                     .method2(this.method16("level", new Object[]{"Combat"}))
                     .method4(field16.format(number7))
                     .method5(NamedTextColor.RED)
                     .method7(number7 >= number6 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                     .build()
               );
            }

            Catacombs catacombs15 = member4.dungeons().dungeonTypes().catacombs();
            double value17 = SkillLevelCalculator.getCatacombsLevel(catacombs15.experience().orElse(-1.0));
            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("level", new Object[]{"Catacombs"}))
                  .method4(field17.format(value17))
                  .method5(NamedTextColor.RED)
                  .method7(value17 >= 50.0 ? NamedTextColor.GOLD : NamedTextColor.WHITE)
                  .build()
            );
            list2.add(Component.empty());
         }

         if ((Boolean)this.field25.get()) {
            KuudraCompletedTiers kuudracompletedtiers14 = member4.netherIslandPlayerData().kuudraCompletedTiers();
            TextComponent text16 = Component.empty();

            for (SkyblockPartyFinder.Type type10 : SkyblockPartyFinder.Type.values()) {
               String text11 = type10.getApiName();
               int number12 = kuudracompletedtiers14.elm().get(text11).asInt().orElse(0);
               if (text16 != Component.empty()) {
                  text16 = (TextComponent)text16.appendNewline();
               }

               text16 = (TextComponent)text16.append(
                  TextComponentFactory.builder()
                     .method2(type10.getDisplayName())
                     .method4(field16.format(number12))
                     .method5(NamedTextColor.GOLD)
                     .method7(NamedTextColor.WHITE)
                     .build()
               );
            }

            list2.add(
               TextComponentFactory.builder()
                  .method2(this.method16("kuudraCompletions", new Object[0]))
                  .method3(text3)
                  .method5(NamedTextColor.GREEN)
                  .method16(HoverEvent.showText(text16))
                  .build()
            );
            list2.add(Component.empty());
         }

         try {
            if ((Boolean)this.field26.get()) {
               list2.addAll(this.method16(member4));
               list2.add(Component.empty());
            }

            if ((Boolean)this.field27.get()) {
               list2.add(Component.text(this.method16("armor", new Object[0]), NamedTextColor.YELLOW, new TextDecoration[]{TextDecoration.BOLD}));
               list2.addAll(this.method12(member4));
               list2.add(Component.empty());
            }

            if ((Boolean)this.field28.get()) {
               list2.add(Component.text(this.method16("importantWeapons", new Object[0]), NamedTextColor.YELLOW, new TextDecoration[]{TextDecoration.BOLD}));
               list2.addAll(this.method13(member4, false));
               list2.add(Component.empty());
            }

            if ((Boolean)this.field29.get()) {
               list2.add(
                  TextComponentFactory.builder()
                     .method2(this.method16("importantItems", new Object[0]))
                     .method3(text3)
                     .method5(NamedTextColor.RED)
                     .method16(HoverEvent.showText(this.method14(member4, false)))
                     .build()
               );
            }

            if ((Boolean)this.field30.get()) {
               list2.add(
                  TextComponentFactory.builder()
                     .method2(this.method16("importantPets", new Object[0]))
                     .method3(text3)
                     .method5(NamedTextColor.GREEN)
                     .method16(HoverEvent.showText(this.method15(member4, false)))
                     .build()
               );
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "SkyBlock Party Finder Stats");
         }

         list2.add(field15);
         Ref.method3().bridge$schedule(() -> {
            for (TextComponent text2x : list2) {
               SkyBlockChat.method3(text2x);
            }
         });
      }
   }

   private Member method11(String text1) {
      String text2 = MinecraftUUID.getSync(text1);
      return text2 == null ? null : SkyBlockProfilesUtil.getSelectedProfileMemberSync(text2);
   }

   private ArrayList<TextComponent> method12(Member member1) {
      ArrayList list2 = new ArrayList();
      Inventory inventory3 = member1.inventory();
      if (!inventory3.elm().exists()) {
         list2.add(Component.text(this.method16("apiOff", new Object[]{"Inventory"}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));
         return list2;
      }

      String text4 = (String)inventory3.invArmor().data().orElse(null);
      if (text4 != null) {
         NBTTagListBridge bridge3_65 = SkyblockItemUtil.method22(text4);

         for (int index6 = bridge3_65.bridge$size() - 1; index6 >= 0; index6--) {
            CompoundTagBridge bridge_577 = bridge3_65.bridge$getCompoundAt(index6);
            list2.add(SkyblockItemUtil.method25(bridge_577));
         }
      }

      String text9 = (String)inventory3.equipmentContents().data().orElse(null);
      if (text9 != null) {
         NBTTagListBridge bridge3_610 = SkyblockItemUtil.method22(text9);

         for (int index11 = 0; index11 < bridge3_610.bridge$size(); index11++) {
            CompoundTagBridge bridge_578 = bridge3_610.bridge$getCompoundAt(index11);
            list2.add(SkyblockItemUtil.method25(bridge_578));
         }
      }

      return list2;
   }

   private ArrayList<TextComponent> method13(Member member1, boolean flag2) {
      ArrayList list3 = new ArrayList();
      Inventory inventory4 = member1.inventory();
      if (!inventory4.elm().exists()) {
         list3.add(Component.text(this.method16("apiOff", new Object[]{"Inventory"}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));
         return list3;
      }

      ImportantItems gui55 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method23();
      HashSet set6 = flag2 ? gui55.method1().method1() : gui55.method2().method1();
      String text7 = (String)inventory4.invContents().data().orElse(null);
      if (text7 == null) {
         return list3;
      }

      NBTTagListBridge bridge3_68 = SkyblockItemUtil.method22(text7);

      for (int index9 = 0; index9 < bridge3_68.bridge$size(); index9++) {
         CompoundTagBridge bridge_5710 = bridge3_68.bridge$getCompoundAt(index9);
         String text11 = bridge_5710.bridge$getCompoundTag("tag").bridge$getCompoundTag("ExtraAttributes").bridge$getString("id");
         if (set6.contains(text11)) {
            list3.add(SkyblockItemUtil.method25(bridge_5710));
         }
      }

      return list3;
   }

   private TextComponent method14(Member member1, boolean flag2) {
      Inventory inventory3 = member1.inventory();
      if (!inventory3.elm().exists()) {
         return Component.text(this.method16("apiOff", new Object[]{"Inventory"}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD});
      }

      TextComponent text4 = Component.empty();
      ImportantItems gui55 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method23();
      HashSet set6 = flag2 ? gui55.method1().method2() : gui55.method2().method2();
      String text7 = (String)inventory3.invContents().data().orElse(null);
      if (text7 == null) {
         return text4;
      }

      NBTTagListBridge bridge3_68 = SkyblockItemUtil.method22(text7);

      for (int index9 = 0; index9 < bridge3_68.bridge$size(); index9++) {
         CompoundTagBridge bridge_5710 = bridge3_68.bridge$getCompoundAt(index9);
         CompoundTagBridge bridge_5711 = bridge_5710.bridge$getCompoundTag("tag");
         String text12 = bridge_5711.bridge$getCompoundTag("ExtraAttributes").bridge$getString("id");
         if (set6.contains(text12)) {
            TextComponent text13 = TextBridge.asAdventure(bridge_5711.bridge$getCompoundTag("display").bridge$getString("Name"));
            if (text4 != Component.empty()) {
               text4 = (TextComponent)text4.appendNewline();
            }

            text4 = (TextComponent)text4.append(text13);
         }
      }

      return text4;
   }

   private TextComponent method15(Member member1, boolean flag2) {
      PetsData petsdata3 = member1.petsData();
      if (!petsdata3.elm().exists()) {
         return Component.text(this.method16("apiOff", new Object[]{"Pets"}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD});
      }

      TextComponent text4 = Component.empty();
      Module module5 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15();
      ImportantItems gui56 = module5.method23();
      HashSet set7 = flag2 ? gui56.method1().method3() : gui56.method2().method3();
      Object2IntOpenHashMap object2intopenhashmap8 = module5.method24().method2();

      for (Pet pet10 : petsdata3.pets()) {
         String text11 = (String)pet10.type().orElse(null);
         if (text11 != null && set7.contains(text11)) {
            ItemRarity guitype312 = ItemRarity.fromRarity((String)pet10.tier().orElse(null));
            if (guitype312 != null) {
               TextComponent text13 = Component.text(this.method17(text11), guitype312.getNamedTextColor());
               double value14 = pet10.exp().orElse(0.0);
               int number16 = object2intopenhashmap8.containsKey(text11) ? SkillLevelCalculator.getPetLevel200(value14, guitype312) : SkillLevelCalculator.getPetLevel(value14, guitype312);
               TextComponent text17 = Component.text("[Lvl " + number16 + "] ", NamedTextColor.GRAY);
               TextComponent text18 = Component.empty();
               String text19 = (String)pet10.heldItem().orElse(null);
               if (text19 != null) {
                  TextComponent text20 = TextBridge.asAdventure(SkyblockItemRegistry.field4.getOrDefault(text19, this.method17(text19)));
                  text18 = (TextComponent)((TextComponent)Component.text(" (", NamedTextColor.GRAY).append(text20))
                     .append(Component.text(")", NamedTextColor.GRAY));
               }

               TextComponent text21 = (TextComponent)((TextComponent)text17.append(text13)).append(text18);
               if (text4 != Component.empty()) {
                  text4 = (TextComponent)text4.appendNewline();
               }

               text4 = (TextComponent)text4.append(text21);
            }
         }
      }

      return text4;
   }

   private ArrayList<TextComponent> method16(Member member1) {
      ArrayList list2 = new ArrayList();
      AccessoryBagStorage accessorybagstorage3 = member1.accessoryBagStorage();
      if (!accessorybagstorage3.elm().exists()) {
         list2.add(Component.text(this.method16("apiOff", new Object[]{"Accessory Bag"}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));
         return list2;
      }

      list2.add(
         TextComponentFactory.builder()
            .method2("Magical Power")
            .method4(field16.format(accessorybagstorage3.highestMagicalPower().orElse(0.0)))
            .method5(NamedTextColor.GOLD)
            .method7(NamedTextColor.WHITE)
            .build()
      );
      String text4 = (String)accessorybagstorage3.selectedPower().orElse(null);
      TextComponent text5 = text4 == null
         ? Component.text(this.method16("none", new Object[0]), NamedTextColor.RED)
         : Component.text(this.method17(text4), NamedTextColor.WHITE);
      list2.add(TextComponentFactory.builder().method2(this.method16("selectedPower", new Object[0])).method3(text5).method5(NamedTextColor.RED).build());
      TextComponent text6 = Component.text(this.method16("none", new Object[0]), NamedTextColor.RED);
      JsonObject json7 = accessorybagstorage3.tuning().elm().asJsonObject().orElse(new JsonObject());
      if (!json7.isEmpty() && json7.has("slot_0")) {
         StringBuilder builder8 = new StringBuilder();

         for (Entry entry10 : json7.getAsJsonObject("slot_0").entrySet()) {
            int number11 = ((JsonElement)entry10.getValue()).getAsInt();
            if (number11 > 0) {
               if (!builder8.isEmpty()) {
                  builder8.append(", ");
               }

               builder8.append(number11).append(" ").append(this.method17((String)entry10.getKey()));
            }
         }

         if (!builder8.isEmpty()) {
            text6 = Component.text(builder8.toString(), NamedTextColor.WHITE);
         }
      }

      list2.add(TextComponentFactory.builder().method2("Tuning").method3(text6).method5(NamedTextColor.LIGHT_PURPLE).build());
      TextComponent text18 = Component.text(this.method16("apiOff", new Object[]{"Inventory"}), NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD});
      Inventory inventory19 = member1.inventory();
      if (inventory19.elm().exists()) {
         text18 = Component.text(this.method16("none", new Object[0]), NamedTextColor.RED);
         String text20 = (String)inventory19.bagContents().talismanBag().data().orElse(null);
         NBTTagListBridge bridge3_621 = text20 == null ? null : SkyblockItemUtil.method22(text20);
         HashMap map12 = new HashMap();

         for (int index13 = 0; bridge3_621 != null && index13 < bridge3_621.bridge$size(); index13++) {
            CompoundTagBridge bridge_5714 = bridge3_621.bridge$getCompoundAt(index13);
            String text15 = bridge_5714.bridge$getCompoundTag("tag").bridge$getCompoundTag("ExtraAttributes").bridge$getString("talisman_enrichment");
            if (!text15.isEmpty()) {
               map12.put(text15, map12.getOrDefault(text15, 0) + 1);
            }
         }

         if (!map12.isEmpty()) {
            StringBuilder builder22 = new StringBuilder();

            for (Entry entry24 : map12.entrySet()) {
               String text16 = this.method17((String)entry24.getKey());
               Integer number17 = (Integer)entry24.getValue();
               if (!builder22.isEmpty()) {
                  builder22.append(", ");
               }

               builder22.append(number17).append(" ").append(text16);
            }

            text18 = Component.text(builder22.toString(), NamedTextColor.WHITE);
         }
      }

      list2.add(TextComponentFactory.builder().method2("Enrichments").method3(text18).method5(NamedTextColor.GRAY).build());
      return list2;
   }

   private String method17(@NotNull String text1) {
      return WordUtils.capitalizeFully(text1.replace('_', ' '));
   }

   public String getId() {
      return "SKYBLOCK_PARTY_FINDER";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field18, arg1xx -> arg1xx.method9(new ClientOption[]{this.field19}));
            arg1x.method9(new ClientOption[]{this.field21});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field22,
               arg1xx -> arg1xx.method9(
                  new ClientOption[]{
                     this.field23, this.field24, this.field25, this.field26, this.field27, this.field28, this.field29, this.field30, this.field31, this.field32
                  }
               )
            );
         }
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field20}));
   }

   private enum Type {
      NONE("none", "Basic"),
      HOT("hot", "Hot"),
      BURNING("burning", "Burning"),
      FIERY("fiery", "Fiery"),
      INFERNAL("infernal", "Infernal");

      private final String apiName;
      private final String displayName;

      @Generated
      Type(String text3, String text4) {
         this.apiName = text3;
         this.displayName = text4;
      }

      @Generated
      public String getApiName() {
         return this.apiName;
      }

      @Generated
      public String getDisplayName() {
         return this.displayName;
      }
   }
}
