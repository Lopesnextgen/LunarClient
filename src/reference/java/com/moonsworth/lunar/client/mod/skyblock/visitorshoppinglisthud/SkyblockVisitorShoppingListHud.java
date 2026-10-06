package com.moonsworth.lunar.client.mod.skyblock.visitorshoppinglisthud;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockVisitorShoppingListHud extends AbstractFeature {
   private final ScreenTitleListener screenTitleListener = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private static final Pattern OFFER_ACCEPTED_PATTERN = Pattern.compile("^OFFER ACCEPTED with (?<name>[\\w ]+) \\([A-Z]+\\)$");
   private static final Pattern SHOPPING_ITEM_PATTERN = Pattern.compile("^ (?<item>[\\w' ]+?)( x(?<amount>[\\d,]+))?$");
   private static final Map<String, Integer> DEFAULT_SHOPPING_LIST = new LinkedHashMap<String, Integer>() {
      {
         this.put("Enchanted Wheat", 476);
         this.put("Compacted Moonflower", 3);
         this.put("Enchanted Baked Potato", 3);
         this.put("Enchanted Carrot", 725);
      }
   };
   private final HashMap<String, List<SkyblockVisitorShoppingListHud.ShoppingListItem>> offersByVisitor = new HashMap<>();
   private final HashMap<String, Integer> requiredItems = new HashMap<>();

   public SkyblockVisitorShoppingListHud(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockVisitorShoppingListHud.Data()));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSlotUpdate.class, this::onSlotUpdate);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(SkyblockProfileChangeEvent.class, this::method4);
   }

   private void onSlotUpdate(EventSlotUpdate highlightimpl1) {
      if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         if (highlightimpl1.getSlot() == 29) {
            String text2 = this.screenTitleListener.method5();
            if (text2 != null) {
               ItemStackBridge bridgeextension_43 = highlightimpl1.method3();
               if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
                  if (ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_43.bridge$getDisplayName()).equals("Accept Offer")) {
                     ArrayList list4 = new ArrayList();
                     List list5 = SkyblockItemUtil.method14(bridgeextension_43);

                     for (int index6 = 1; index6 < list5.size(); index6++) {
                        String text7 = ChatFormatting.getTextWithoutFormattingCodes((String)list5.get(index6));
                        Matcher matcher8 = field10.matcher(text7);
                        if (!matcher8.matches()) {
                           break;
                        }

                        String text9 = matcher8.group("item");
                        String text10 = matcher8.group("amount");
                        int number11 = text10 == null ? 1 : Integer.parseInt(text10.replaceAll(",", ""));
                        list4.add(new SkyblockVisitorShoppingListHud.ShoppingListItem(text9, number11));
                     }

                     this.offersByVisitor.put(text2, list4);
                     this.recomputeRequiredItems();
                  }
               }
            }
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         Matcher matcher3 = field9.matcher(text2);
         if (matcher3.matches()) {
            String text4 = matcher3.group("name");
            this.removeOffer(text4);
         }
      }
   }

   private void method3(EventRenderSlot highlightimpl51) {
      if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         if (highlightimpl51.method5() != null) {
            if (highlightimpl51.method5().bridge$getIndex() == 33) {
               String text2 = this.screenTitleListener.method5();
               if (text2 != null) {
                  this.removeOffer(text2);
               }
            }
         }
      }
   }

   private void method4(SkyblockProfileChangeEvent data151) {
      this.offersByVisitor.clear();
      this.requiredItems.clear();
   }

   private void recomputeRequiredItems() {
      this.requiredItems.clear();

      for (List list2 : this.offersByVisitor.values()) {
         for (SkyblockVisitorShoppingListHud.ShoppingListItem data24 : list2) {
            String text5 = data24.item();
            this.requiredItems.put(text5, this.requiredItems.getOrDefault(text5, 0) + data24.amount());
         }
      }
   }

   private void removeOffer(String text1) {
      this.offersByVisitor.remove(text1);
      this.recomputeRequiredItems();
   }

   public String getId() {
      return "SKYBLOCK_VISITOR_SHOPPING_LIST_HUD";
   }

   private class Data extends TypedHudRenderer<List<TextComponent>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 100, 200, 100, 200, 1000);
      }

      @Nullable
      public List<TextComponent> method2(boolean flag1) {
         if (flag1) {
            return this.method3(SkyblockVisitorShoppingListHud.DEFAULT_SHOPPING_LIST);
         } else {
            SkyblockIsland gui2extension32 = IslandUtils.getIsland();
            if (gui2extension32 != SkyblockIsland.GARDEN && gui2extension32 != SkyblockIsland.HUB) {
               return null;
            } else {
               return SkyblockVisitorShoppingListHud.this.requiredItems.isEmpty() ? null : this.method3(SkyblockVisitorShoppingListHud.this.requiredItems);
            }
         }
      }

      private List<TextComponent> method3(Map<String, Integer> map1) {
         ArrayList list2 = new ArrayList();
         list2.add(
            (TextComponent)((TextComponent)Component.text(SkyblockVisitorShoppingListHud.this.method4("shoppingList", new Object[0]))
                  .color(NamedTextColor.AQUA))
               .decorate(TextDecoration.BOLD)
         );

         for (String text4 : map1.keySet()) {
            int number5 = (Integer)map1.get(text4);
            list2.add(TextComponentFactory.itemLine(text4, number5));
         }

         return list2;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }
   }

   private class ShoppingListItem {
      private final String itemName;
      private final int requiredAmount;

      private ShoppingListItem(String text1, int number2) {
         this.itemName = text1;
         this.requiredAmount = number2;
      }

      public String item() {
         return this.itemName;
      }

      public int amount() {
         return this.requiredAmount;
      }
   }
}
