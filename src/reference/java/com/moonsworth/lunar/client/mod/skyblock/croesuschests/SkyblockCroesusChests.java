package com.moonsworth.lunar.client.mod.skyblock.croesuschests;

import com.moonsworth.lunar.bridge.Bridge;
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
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.HashSet;
import java.util.Locale;

public class SkyblockCroesusChests extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method8(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method8(ScreenTitleListener.class);
   private static final ItemStackBridge field10 = Bridge.method8().method41();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "skyblockHideOpenedCroesusChests"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("skyblockHideCroesusChestKeyChests")
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "skyblockColorChestKeyChests"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("checkmarkRerolledChests")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockChestKeyCroesusChestColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2771446))
      .method31();
   private final EnumOption<NamedColorOption> field16 = (EnumOption<NamedColorOption>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "checkmarkColor", NamedColorOption.GREEN
      )
      .method31();
   private final HashSet<SlotBridge> field17 = new HashSet<>();
   private final HashSet<SlotBridge> field18 = new HashSet<>();
   private final HashSet<ItemStackBridge> field19 = new HashSet<>();

   public SkyblockCroesusChests(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, this::method13));
      this.handle(EventRenderHologramItem.class, this::method3);
      this.handle(EventSlotUpdate.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize.class, this::method4);
      this.handle(EventScreenOpen.class, this::method1);
   }

   private void method1(EventScreenOpen highlightimpl91) {
      this.field17.clear();
      this.field18.clear();
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      SkyblockMenuType highlighttype2 = this.field8.method7();
      if (highlighttype2 != null && highlighttype2.isChestClaimGui()) {
         int index3 = highlightimpl1.getSlot();
         if (highlightimpl1.getSlot() >= 0 && highlightimpl1.getSlot() <= 53) {
            GuiContainerBridge bridge5extension_34 = this.field9.method6();
            if (bridge5extension_34 != null) {
               SlotBridge bridge3_185 = (SlotBridge)bridge5extension_34.bridge$inventorySlots().get(index3);
               ItemStackBridge bridgeextension_46 = highlightimpl1.method3();

               for (String text8 : SkyblockItemUtil.method15(bridgeextension_46)) {
                  if ((Boolean)this.field11.get() && text8.toLowerCase(Locale.ROOT).equals("no more chests to open!")) {
                     this.field17.add(bridge3_185);
                     return;
                  }

                  if (text8.startsWith("Opened Chest: ")) {
                     if ((Boolean)this.field12.get()) {
                        this.field17.add(bridge3_185);
                     } else if ((Boolean)this.field13.get()) {
                        this.field18.add(bridge3_185);
                     }

                     return;
                  }
               }
            }
         }
      }
   }

   private void method3(EventRenderHologramItem data51) {
      SlotBridge bridge3_182 = data51.method3();
      if (this.field17.contains(bridge3_182)) {
         data51.method2(field10);
      } else if (this.field18.contains(bridge3_182)) {
         data51.method1(this.field15.method14(0.0F));
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize data1) {
      if ((Boolean)this.field14.get()) {
         SkyblockMenuType highlighttype2 = this.field8.method7();
         if (highlighttype2 == SkyblockMenuType.CROESUS) {
            for (String text4 : SkyblockItemUtil.method14(data1.getItem())) {
               if (text4.endsWith(ChatFormatting.STRIKETHROUGH + "Kismet Feather")) {
                  data1.setText(((NamedColorOption)this.field16.get()).getColor().toString() + "✔");
                  return;
               }
            }
         }
      }
   }

   private boolean method13() {
      SkyblockIsland gui2extension31 = IslandUtils.getIsland();
      return gui2extension31 == SkyblockIsland.DUNGEON_HUB || gui2extension31 == SkyblockIsland.CRIMSON_ISLES;
   }

   public String getId() {
      return "SKYBLOCK_CROESUS_CHESTS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11, this.field12});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}));
      });
      lightingextension231.method9(new ClientOption[]{this.field11, this.field12});
      lightingextension231.method7(this.field13, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15}));
   }
}
