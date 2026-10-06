package com.moonsworth.lunar.client.mod.skyblock.chestprofit;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemValueResponse;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemValueResponse.Type;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.mixin.ChestType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.text.TextUtils;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockChestProfit extends AbstractFeature {
   private final ScreenTitleListener field8 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final GuiRewindhandlersHandler2 field9 = (GuiRewindhandlersHandler2)this.method63(GuiRewindhandlersHandler2.class);
   private static final Pattern field10 = Pattern.compile("^(Master )?Catacombs - (Floor (\\w+))$");
   private static final Pattern field11 = Pattern.compile("^Kuudra - \\w+$");
   private static final Pattern field12 = Pattern.compile("^(?<price>[\\d,]+) Coins$");
   private static final NumberFormat field13 = NumberFormat.getNumberInstance(Locale.ROOT);
   private static final SkyblockChestProfit.ProfitEntry field14;
   private final ToggleOption field15 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockHighlightProfittableChest"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockAdvancedProfitBreakdown"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final SkyblockChestProfit.Data field17 = new SkyblockChestProfit.Data();

   public SkyblockChestProfit(Skyblock skyblock1) {
      super(true);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method4(ModTraits.field17, ModCategories.method3(new SettingsPage[]{SettingsPage.DUNGEONS, SettingsPage.CRIMSON_ISLE}));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method4(ModTraits.field1, HudVisibilityWrapper.method4(new NameplateComponent(this, this::method13)));
      this.method51(this.field17::clear);
      this.handle(EventTick.class, this::method2);
      this.handle(EventRenderContainerSlotPost.class, this::method8);
      this.handle(EventRenderSlot.class, this::method13);
      this.handle(EventRenderHologramItem.class, this::method14);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field15, this.field16})
      );
   }

   public String getId() {
      return "SKYBLOCK_CHEST_PROFIT";
   }

   private void method2(EventTick highlightimpl21) {
      if (!IslandUtils.isOnIsland()) {
         this.field17.clear();
      } else {
         String text2 = this.field8.method5();
         if (text2 == null) {
            this.field17.clear();
         } else if (!field10.matcher(text2).matches() && !field11.matcher(text2).matches()) {
            ChestType chesttype5 = ChestType.fromString(text2);
            if (chesttype5 != null) {
               this.field17.field3 = chesttype5;
               if (this.field17.field1.get(this.field17.field3) == null) {
                  this.method4(31, this.field17.field3);
               }
            } else {
               this.field17.clear();
            }
         } else {
            if (this.field17.field3 != null) {
               this.field17.clear();
            }

            GuiContainerBridge bridge5extension_33 = this.field8.method6();
            if (bridge5extension_33.bridge$inventorySlots().size() >= 18) {
               for (int index4 = 10; index4 < 18; index4++) {
                  this.method3((SlotBridge)bridge5extension_33.bridge$inventorySlots().get(index4));
               }
            }
         }
      }
   }

   private void method3(SlotBridge bridge3_181) {
      ItemStackBridge bridgeextension_42 = bridge3_181.bridge$getItemStack();
      if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty() && bridgeextension_42.bridge$hasCustomLore()) {
         ChestType chesttype3 = ChestType.fromString(bridgeextension_42.bridge$getRawDisplayName());
         if (chesttype3 != null && this.field17.field1.get(chesttype3) == null) {
            this.method5(bridge3_181, bridgeextension_42, chesttype3);
         }
      }
   }

   private void method4(int index1, ChestType chesttype2) {
      SlotBridge bridge3_183 = (SlotBridge)this.field8.method6().bridge$inventorySlots().get(index1);
      ItemStackBridge bridgeextension_44 = bridge3_183.bridge$getItemStack();
      if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty() && bridgeextension_44.bridge$hasCustomLore()) {
         this.method5(bridge3_183, bridgeextension_44, chesttype2);
      }
   }

   private void method5(SlotBridge bridge3_181, ItemStackBridge bridgeextension_42, ChestType chesttype3) {
      List list4 = SkyblockItemUtil.method15(bridgeextension_42);
      SkyblockChestProfit.ProfitEntry data25 = this.method6(list4);
      boolean flag6 = true;

      for (Entry entry8 : this.field17.field1.entrySet()) {
         if (!((SkyblockChestProfit.ProfitEntry)entry8.getValue()).method3() && data25.method1() < ((SkyblockChestProfit.ProfitEntry)entry8.getValue()).method1()) {
            flag6 = false;
            break;
         }
      }

      if (flag6 && !data25.method3() && data25.method1() > 0) {
         this.field17.field4 = bridge3_181;
      }

      this.field17.field1.put(chesttype3, data25);
   }

   private SkyblockChestProfit.ProfitEntry method6(List<String> list1) {
      boolean flag2 = false;
      boolean flag3 = false;
      boolean flag4 = false;
      int number5 = 0;
      int number6 = 0;
      HashMap map7 = new HashMap();

      for (String text9 : list1) {
         if (text9.equals("Contents")) {
            flag2 = true;
         } else if (text9.equals("Cost")) {
            flag3 = true;
         } else {
            if (text9.equals("Already opened!")) {
               flag4 = true;
               break;
            }

            if (text9.isEmpty()) {
               flag2 = false;
               flag3 = false;
            } else if (flag3) {
               Matcher matcher10 = field12.matcher(text9);
               if (matcher10.matches()) {
                  number5 += Integer.parseInt(matcher10.group("price").replaceAll(",", ""));
               } else if (!text9.equals("FREE")) {
                  number5 += this.method7(text9);
               }
            } else if (flag2) {
               int number11 = this.method7(text9);
               map7.put(text9, number11);
               number6 += number11;
            }
         }
      }

      return new SkyblockChestProfit.ProfitEntry(number5, number6, flag4, map7);
   }

   private int method7(String text1) {
      ItemValueResponse gui2 = this.field9.method1(text1);
      if (gui2.isSuccess()) {
         return gui2.getValue();
      }

      this.field17.field2.put(text1, gui2.method4());
      return 0;
   }

   private void method8(EventRenderContainerSlotPost data31) {
      ArrayList list2 = new ArrayList();
      NumberFormat numberformat3 = NumberFormat.getNumberInstance(Locale.ROOT);
      numberformat3.setMaximumFractionDigits(0);
      ChestType chesttype4 = this.field17.field3;
      if (chesttype4 != null) {
         SkyblockChestProfit.ProfitEntry data25 = this.field17.field1.get(chesttype4);
         if ((Boolean)this.field16.get()) {
            this.method11(chesttype4, list2, numberformat3, data25);
         } else {
            this.method10(chesttype4, list2, numberformat3, data25);
         }
      } else {
         for (ChestType chesttype8 : ChestType.values()) {
            SkyblockChestProfit.ProfitEntry data29 = this.field17.field1.get(chesttype8);
            if (data29 != null && !data29.field3) {
               this.method10(chesttype8, list2, numberformat3, data29);
            }
         }
      }

      if (!this.field17.field2.isEmpty()) {
         list2.add(Component.text("Some items missing price data!", NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));

         for (Entry entry12 : this.field17.field2.entrySet()) {
            list2.add(Component.text("- " + (String)entry12.getKey() + " " + ((Type)entry12.getValue()).getDebugString(), NamedTextColor.GRAY));
         }
      }

      WorldRenderUtils.renderTooltip(data31, list2, (MixinCore9Extension)this.method7(ModTraits.field1));
   }

   private List<TextComponent> method13() {
      ArrayList list1 = new ArrayList();
      if ((Boolean)this.field16.get()) {
         this.method11(ChestType.BEDROCK, list1, field13, field14);
      } else {
         this.method10(ChestType.BEDROCK, list1, field13, field14);
      }

      return list1;
   }

   private void method10(ChestType chesttype1, List<TextComponent> list2, NumberFormat numberformat3, SkyblockChestProfit.ProfitEntry data24) {
      int number5 = data24.method1();
      list2.add(
         (TextComponent)((TextComponent)Component.text(TextUtils.toCamelCase(chesttype1.name(), true), chesttype1.getColor())
               .append(Component.text(" Chest:", NamedTextColor.WHITE)))
            .append(Component.text(" " + numberformat3.format(number5) + " coins", this.method12(number5)))
      );
      list2.add(Component.empty());
   }

   private void method11(ChestType chesttype1, List<TextComponent> list2, NumberFormat numberformat3, SkyblockChestProfit.ProfitEntry data24) {
      if (data24 != null && !data24.field3) {
         list2.add(
            (TextComponent)Component.text(TextUtils.toCamelCase(chesttype1.name(), true), chesttype1.getColor())
               .append(Component.text(" Chest:", NamedTextColor.WHITE))
         );
         list2.add(
            TextComponentFactory.builder().method2("Value").method5(NamedTextColor.YELLOW).method4(numberformat3.format(data24.field2) + " coins").method7(NamedTextColor.GOLD).build()
         );

         for (Entry entry6 : data24.field4.entrySet()) {
            list2.add(
               TextComponentFactory.builder()
                  .method2(" - " + (String)entry6.getKey())
                  .method5(NamedTextColor.GRAY)
                  .method4(numberformat3.format(entry6.getValue()) + " coins")
                  .method7(NamedTextColor.GOLD)
                  .build()
            );
         }

         list2.add(
            TextComponentFactory.builder().method2("Cost").method5(NamedTextColor.YELLOW).method4(numberformat3.format(data24.field1) + " coins").method7(NamedTextColor.GOLD).build()
         );
         int number7 = data24.method1();
         list2.add(TextComponentFactory.builder().method2("Profit").method5(NamedTextColor.YELLOW).method4(numberformat3.format(number7) + " coins").method7(this.method12(number7)).build());
         list2.add(Component.text(""));
      }
   }

   private NamedTextColor method12(int number1) {
      if (number1 > 0) {
         return NamedTextColor.GREEN;
      } else {
         return number1 < 0 ? NamedTextColor.RED : NamedTextColor.GRAY;
      }
   }

   private void method13(EventRenderSlot highlightimpl51) {
      ChestType chesttype2 = this.field17.field3;
      if (chesttype2 != null) {
         SlotBridge bridge3_183 = highlightimpl51.method5();
         if (bridge3_183 != null) {
            ItemStackBridge bridgeextension_44 = bridge3_183.bridge$getItemStack();
            if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty()) {
               String text5 = bridgeextension_44.bridge$getRawDisplayName();
               if (text5.equals("Reroll Chest")) {
                  this.field17.clear();
               }
            }
         }
      }
   }

   private void method14(EventRenderHologramItem data51) {
      if ((Boolean)this.field15.get()) {
         if (this.field17.field3 == null) {
            if (data51.method3().equals(this.field17.field4)) {
               data51.method1(-16711936);
            }
         }
      }
   }

   static {
      field13.setMaximumFractionDigits(0);
      LinkedHashMap map0 = new LinkedHashMap();
      map0.put("Necron's Handle", 450000000);
      map0.put("Fuming Potato Book", 1100000);
      map0.put("Enchanted Book (Ultimate Wise II)", 200000);
      int number1 = map0.values().stream().mapToInt(Integer::intValue).sum();
      field14 = new SkyblockChestProfit.ProfitEntry(100000000, number1, false, map0);
   }

   private static class Data {
      private final EnumMap<ChestType, SkyblockChestProfit.ProfitEntry> field1 = new EnumMap<>(ChestType.class);
      private final Map<String, Type> field2 = new HashMap<>();
      @Nullable
      private ChestType field3;
      private SlotBridge field4;

      private Data() {
      }

      private void clear() {
         this.field1.clear();
         this.field2.clear();
         this.field3 = null;
         this.field4 = null;
      }
   }

   private class ProfitEntry {
      private final int field1;
      private final int field2;
      private final boolean field3;
      private final Map<String, Integer> field4;

      private ProfitEntry(int number1, int number2, boolean flag3, Map<String, Integer> map4) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = flag3;
         this.field4 = map4;
      }

      private int method1() {
         return this.field2 - this.field1;
      }

      public int method2() {
         return this.field1;
      }

      public int value() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }

      public Map<String, Integer> method4() {
         return this.field4;
      }
   }
}
