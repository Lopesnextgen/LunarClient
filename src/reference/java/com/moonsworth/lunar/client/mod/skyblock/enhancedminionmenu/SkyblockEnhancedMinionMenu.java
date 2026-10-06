package com.moonsworth.lunar.client.mod.skyblock.enhancedminionmenu;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation.MinionXpData;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemValueResponse;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.text.WordUtils;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockEnhancedMinionMenu extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final SkyblockScoreboardParser field10 = (SkyblockScoreboardParser)this.method63(SkyblockScoreboardParser.class);
   private final GuiRewindhandlersHandler2 field11 = (GuiRewindhandlersHandler2)this.method63(GuiRewindhandlersHandler2.class);
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCookieBuff").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showActivePet").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showWisdomBoost").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showExp").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showProfit").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field17 = (ColorOption)((Data)OptionFactory.method8("subjectColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private final List<TextComponent> field18 = new ArrayList<>();

   public SkyblockEnhancedMinionMenu(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.ISLAND || IslandUtils.getIsland() == SkyblockIsland.HUB));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new NameplateComponent(this, this::method13)));
      this.method1(this.field18::clear);
      this.handle(EventSecond.class, this::method1);
      this.handle(EventRenderContainerSlotPost.class, this::method2);
   }

   private void method1(EventSecond highlightimpl41) {
      this.field18.clear();
      String text2 = this.field9.method5();
      if (text2 != null && text2.contains(" Minion ")) {
         GuiContainerBridge bridge5extension_33 = this.field9.method6();
         if (bridge5extension_33 != null) {
            List list4 = bridge5extension_33.bridge$inventorySlots();
            ItemStackBridge bridgeextension_45 = ((SlotBridge)bridge5extension_33.bridge$inventorySlots().get(4)).bridge$getItemStack();
            if (bridgeextension_45 != null && !bridgeextension_45.bridge$isEmpty()) {
               String text6 = SkyblockItemUtil.method2(bridgeextension_45);
               if (text6.contains("_GENERATOR_")) {
                  this.method3(text6, list4);
               }
            }
         }
      }
   }

   private void method2(EventRenderContainerSlotPost data31) {
      if (!this.field18.isEmpty()) {
         String text2 = this.field9.method5();
         if (text2 != null && text2.contains(" Minion ")) {
            WorldRenderUtils.renderTooltip(data31, this.field18, (MixinCore9Extension)this.method7(ModTraits.field1));
         }
      }
   }

   private void method3(String text1, List<SlotBridge> list2) {
      MinionXpData colorsaturation3 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method42();
      if (colorsaturation3 != null) {
         String[] items4 = text1.split("_");
         if (items4.length >= 3) {
            String text5 = items4[0];
            if (items4.length > 3) {
               text5 = text5 + "_" + items4[1];
            }

            String text6 = (String)colorsaturation3.getCategories().get(text5);
            if (text6 != null) {
               int number7 = this.method6(text6);
               double value8 = this.method8(list2, colorsaturation3);
               double value10 = value8 * (1.0 + number7 / 100.0);
               double value12 = this.method9(list2);
               if ((Boolean)this.field12.get()) {
                  this.field18.add(this.method11(this.method12("cookieBuff", new Object[0]), this.method14()));
               }

               if ((Boolean)this.field13.get()) {
                  this.field18.add(this.method12(this.method12("activePet", new Object[0]), this.method15()));
               }

               if ((Boolean)this.field14.get()) {
                  if (number7 < 0) {
                     TextComponent text14 = Component.text(
                        this.method12("enableWisdomWidget", new Object[]{WordUtils.capitalizeFully(text6)}), NamedTextColor.RED
                     );
                     this.field18.add(this.method12(this.method12("wisdomBoost", new Object[0]), text14));
                  } else {
                     this.field18.add(this.method13(this.method12("wisdomBoost", new Object[0]), number7));
                  }
               }

               if ((Boolean)this.field15.get()) {
                  this.field18.add(this.method13(this.method12("itemsExp", new Object[0]), value8));
                  this.field18.add(this.method13(this.method12("boostedExp", new Object[0]), value10));
               }

               if ((Boolean)this.field16.get()) {
                  this.field18.add(this.method13(this.method12("profit", new Object[0]), value12));
               }
            }
         }
      }
   }

   private List<TextComponent> method13() {
      ArrayList list1 = new ArrayList();
      double value2 = 15420.0;
      byte number4 = 45;
      double value5 = value2 * (1.0 + number4 / 100.0);
      double value7 = 22359.0;
      if ((Boolean)this.field12.get()) {
         list1.add(this.method11(this.method12("cookieBuff", new Object[0]), true));
      }

      if ((Boolean)this.field13.get()) {
         list1.add(this.method12(this.method12("activePet", new Object[0]), Component.text(" [Lvl 100] Rabbit", NamedTextColor.GOLD)));
      }

      if ((Boolean)this.field14.get()) {
         list1.add(this.method13(this.method12("wisdomBoost", new Object[0]), number4));
      }

      if ((Boolean)this.field15.get()) {
         list1.add(this.method13(this.method12("itemsExp", new Object[0]), value2));
         list1.add(this.method13(this.method12("boostedExp", new Object[0]), value5));
      }

      if ((Boolean)this.field16.get()) {
         list1.add(this.method13(this.method12("profit", new Object[0]), value7));
      }

      return list1;
   }

   private boolean method14() {
      ScoreboardSection lotusfish31 = (ScoreboardSection)this.field10.method6().get("effects");
      if (lotusfish31 != null) {
         for (String text3 : lotusfish31.method3()) {
            if (text3.startsWith(" Cookie Buff")) {
               return !text3.endsWith("INACTIVE");
            }
         }
      }

      String text4 = this.method16();
      if (text4.isEmpty()) {
         return false;
      }

      String[] items5 = text4.split("Cookie Buff");
      return items5.length > 1 ? !items5[1].toLowerCase(Locale.ROOT).contains("not active!") : false;
   }

   private int method6(String text1) {
      ScoreboardSection lotusfish32 = (ScoreboardSection)this.field10.method6().get("stats");
      if (lotusfish32 == null) {
         return -1;
      }

      for (String text4 : lotusfish32.method3()) {
         String text5 = text4.toUpperCase(Locale.ROOT);
         if (text5.startsWith(" " + text1 + " WISDOM")) {
            return NumberUtils.method3(text5.replaceAll("\\D", ""));
         }
      }

      return -1;
   }

   private Component method15() {
      ScoreboardSection lotusfish31 = (ScoreboardSection)this.field10.method6().get("pet");
      if (lotusfish31 == null) {
         return Component.text(this.method12("enablePetsWidget", new Object[0]), NamedTextColor.RED);
      }

      List list2 = lotusfish31.method3();

      for (int index3 = 0; index3 < list2.size(); index3++) {
         String text4 = (String)list2.get(index3);
         if (text4.startsWith(" [Lvl")) {
            return (Component)lotusfish31.method2().get(index3);
         }
      }

      return null;
   }

   private double method8(List<SlotBridge> list1, MinionXpData colorsaturation2) {
      double value3 = 0.0;

      for (int index5 = 0; index5 < 3; index5++) {
         for (int index6 = 0; index6 < 5; index6++) {
            int index7 = 21 + index5 * 9 + index6;
            ItemStackBridge bridgeextension_48 = ((SlotBridge)list1.get(index7)).bridge$getItemStack();
            if (bridgeextension_48 != null && !bridgeextension_48.bridge$isEmpty()) {
               String text9 = SkyblockItemUtil.method2(bridgeextension_48);
               Double value10 = (Double)colorsaturation2.getXp().get(text9);
               if (value10 != null) {
                  value3 += value10 * bridgeextension_48.bridge$getStackSize();
               }
            }
         }
      }

      return value3;
   }

   private double method9(List<SlotBridge> list1) {
      double value2 = 0.0;

      for (int index4 = 0; index4 < 3; index4++) {
         for (int index5 = 0; index5 < 5; index5++) {
            int index6 = 21 + index4 * 9 + index5;
            ItemStackBridge bridgeextension_47 = ((SlotBridge)list1.get(index6)).bridge$getItemStack();
            if (bridgeextension_47 != null && !bridgeextension_47.bridge$isEmpty()) {
               String text8 = SkyblockItemUtil.method3(bridgeextension_47);
               ItemValueResponse gui9 = this.field11.method2(text8);
               if (gui9.isSuccess()) {
                  value2 += (double)gui9.getValue() * bridgeextension_47.bridge$getStackSize();
               }
            }
         }
      }

      for (SlotBridge bridge3_1812 : list1) {
         ItemStackBridge bridgeextension_413 = bridge3_1812.bridge$getItemStack();
         if (bridgeextension_413 != null && !bridgeextension_413.bridge$isEmpty()) {
            for (String text15 : SkyblockItemUtil.method15(bridgeextension_413)) {
               if (text15.startsWith("Held Coins: ")) {
                  try {
                     value2 += Double.parseDouble(text15.split(": ")[1].replace(",", ""));
                  } catch (NumberFormatException numberformatexception10) {
                  }
               }
            }
         }
      }

      return value2;
   }

   private String method16() {
      Bridge2_42 bridge2_421 = Ref.method3().bridge$getGuiIngame().bridge$getTabList().bridge$getFooter();
      return TextBridge.getTextContent(TextBridge.asAdventure(bridge2_421));
   }

   private TextComponent method11(String text1, boolean flag2) {
      return (TextComponent)Component.text(text1 + ": ", TextColor.color(this.field17.method14(0.0F)))
         .append(flag2 ? Component.text('✔', NamedTextColor.GREEN) : Component.text('✖', NamedTextColor.RED));
   }

   private TextComponent method12(String text1, Component component2) {
      Object obj3 = component2 != null ? component2 : Component.text(" ✖", NamedTextColor.RED);
      return (TextComponent)Component.text(text1 + ":", TextColor.color(this.field17.method14(0.0F))).append((Component)obj3);
   }

   private TextComponent method13(String text1, double value2) {
      return (TextComponent)Component.text(text1 + ": ", TextColor.color(this.field17.method14(0.0F)))
         .append(Component.text(field8.format(value2), value2 > 0.0 ? NamedTextColor.GREEN : NamedTextColor.RED));
   }

   public String getId() {
      return "SKYBLOCK_ENHANCED_MINION_MENU";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field12, this.field13, this.field14, this.field15, this.field16})
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field17}));
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
