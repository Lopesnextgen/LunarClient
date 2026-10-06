package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemShopPrices;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ShopItem;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ShopPriceLine;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.RequiredItem;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemSkin;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.text.WordUtils;

public class SkyblockChocolateShopHelper extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final float field9 = 18.0F;
   private final HighlightTypeListener field10 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final GuiRewindhandlersHandler2 field11 = (GuiRewindhandlersHandler2)this.method63(GuiRewindhandlersHandler2.class);
   private final List<ShopPriceLine> field12 = new ArrayList<>();
   private float field13 = 0.0F;
   private float field14 = 0.0F;

   public SkyblockChocolateShopHelper(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method5(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method5(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method5(ModTraits.field1, HudVisibilityWrapper.method4(new NameplateComponent(this, this::method13)));
      this.handle(EventScreenInitPost.class, this::method1);
      this.handle(EventRenderContainerSlotPost.class, this::method2);
      this.handle(EventRenderTooltipPre.class, this::method3);
   }

   private void method1(EventScreenInitPost data51) {
      SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockchocolatefactory2.method44().get()) {
         this.field12.clear();
         SkyblockMenuType highlighttype3 = this.field10.method7();
         if (highlighttype3 == SkyblockMenuType.CHOCOLATE_SHOP) {
            ItemShopPrices gui2_24 = Ref.method4().method40().method82().method15().method29();
            if (gui2_24 != null) {
               for (ShopItem gui3_27 : gui2_24.method7()
                  .values()
                  .stream()
                  .filter(arg1x -> this.method5(arg1x) > 0.0)
                  .sorted((arg1x, arg2x) -> Double.compare(this.method5(arg2x), this.method5(arg1x)))
                  .limit(12L)
                  .toList()) {
                  String text8 = gui3_27.method2();
                  String text9 = gui3_27.displayName();
                  if (text9 == null) {
                     text9 = SkyblockItemRegistry.field4.getOrDefault(text8, WordUtils.capitalizeFully(text8.replace('_', ' ')));
                  }

                  TextComponent text10 = TextBridge.asAdventure(text9 + ": ");
                  TextComponent text11 = Component.text(field8.format(this.method5(gui3_27)));
                  ItemStackBridge bridgeextension_412;
                  if (gui3_27.method5() != null) {
                     String[] items13 = gui3_27.method5().split(":");
                     ItemBridge bridge6_414 = Bridge.method28().method22(items13[0]);
                     bridgeextension_412 = Bridge.method8().method38(bridge6_414);
                     if (items13.length > 1) {
                        bridgeextension_412.bridge$setItemDamage(Integer.parseInt(items13[1]));
                     }
                  } else if (gui3_27.method6() != null) {
                     ItemSkin gui_315 = gui3_27.method6();
                     bridgeextension_412 = SkyblockItemUtil.method12(gui_315.id(), gui_315.method1(), gui_315.signature());
                  } else {
                     bridgeextension_412 = SkyblockItemRegistry.method3(text8);
                  }

                  if (bridgeextension_412 != null) {
                     bridgeextension_412.bridge$setStackDisplayName(TextBridge.asAdventure(text9));
                  }

                  this.field12.add(new ShopPriceLine(text10, text11, bridgeextension_412));
                  float value16 = Ref.method10().bridge$getStringWidth(text10);
                  float value17 = value16 + Ref.method10().bridge$getStringWidth(text11) + 18.0F;
                  if (value17 > this.field14) {
                     this.field13 = value16;
                     this.field14 = value17;
                  }
               }
            }
         }
      }
   }

   private void method2(EventRenderContainerSlotPost data31) {
      SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockchocolatefactory2.method44().get()) {
         if (!this.field12.isEmpty()) {
            MixinHelper_4 mixinhelper_43 = data31.method5();
            MixinCore9Extension mixincore9extension4 = (MixinCore9Extension)this.method7(ModTraits.field1);
            float value5 = (1 + this.field12.size()) * 18.0F;
            mixincore9extension4.method16(this.field14, value5);
            float value6 = Math.abs(18.0F - Ref.method10().method19()) / 2.0F;
            WorldRenderUtils.prepareComponentScale(mixinhelper_43, mixincore9extension4);
            mixinhelper_43.method10(
               Ref.method10(),
               Component.text(this.method8("title", new Object[0])).decorate(TextDecoration.BOLD),
               0,
               0,
               skyblockchocolatefactory2.method45().method14(0.0F),
               true
            );
            mixinhelper_43.method39(0.0F, 18.0F);

            for (ShopPriceLine gui4_28 : this.field12) {
               if (gui4_28.method3() != null) {
                  mixinhelper_43.method34(gui4_28.method3(), 0, 0, Ref.method3());
               }

               mixinhelper_43.push();
               mixinhelper_43.method39(18.0F, value6);
               mixinhelper_43.method10(Ref.method10(), gui4_28.method1(), 0, 0, skyblockchocolatefactory2.method46().method14(0.0F), true);
               mixinhelper_43.method39(this.field13, 0.0F);
               mixinhelper_43.method10(Ref.method10(), gui4_28.method2(), 0, 0, skyblockchocolatefactory2.method47().method14(0.0F), true);
               mixinhelper_43.pop();
               mixinhelper_43.method39(0.0F, 18.0F);
            }

            mixinhelper_43.pop();
         }
      }
   }

   private void method3(EventRenderTooltipPre data21) {
      SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockchocolatefactory2.method43().get()) {
         SkyblockMenuType highlighttype3 = this.field10.method7();
         if (highlighttype3 == SkyblockMenuType.CHOCOLATE_SHOP) {
            ItemShopPrices gui2_24 = Ref.method4().method40().method82().method15().method29();
            if (gui2_24 != null) {
               ItemStackBridge bridgeextension_45 = (ItemStackBridge)data21.method1().orElse(null);
               String text6 = SkyblockItemUtil.method3(bridgeextension_45);
               ShopItem gui3_27 = (ShopItem)gui2_24.method7().get(text6);
               if (gui3_27 != null) {
                  double value8 = this.method5(gui3_27);
                  if (!(value8 <= 0.0)) {
                     TextComponent text10 = TextComponentFactory.builder().method2(this.method8("coinsPerChocolate", new Object[0])).method4(field8.format(value8)).build();
                     List list11 = data21.method3();
                     list11.add((ClickableText)Bridge.method8().method89(text10));
                     data21.method2(list11);
                  }
               }
            }
         }
      }
   }

   private List<TextComponent> method13() {
      SkyblockChocolateFactory skyblockchocolatefactory1 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      return !skyblockchocolatefactory1.method44().get()
         ? List.of()
         : WidgetFactory.createLore(
            this.method8("title", new Object[0]),
            skyblockchocolatefactory1.method45().method14(0.0F),
            skyblockchocolatefactory1.method46().method14(0.0F),
            skyblockchocolatefactory1.method47().method14(0.0F),
            List.of(
               ValuePair.method1("Egglocator", "2,667"),
               ValuePair.method1("Nibble Chocolate Stick", "1,200"),
               ValuePair.method1("Rabbit Mafioso Shard", "443"),
               ValuePair.method1("Rabbit Cat Shard", "268"),
               ValuePair.method1("Smooth Chocolate Bar", "170"),
               ValuePair.method1("Hot Chocolate Mixin", "160"),
               ValuePair.method1("Rabbit Neighbor Shard", "146"),
               ValuePair.method1("Chocolate Factory Barn Skin", "143"),
               ValuePair.method1("Ganache Chocolate Slab", "115"),
               ValuePair.method1("Rich Chocolate Chunk", "90"),
               ValuePair.method1("Prestige Chocolate Realm", "68"),
               ValuePair.method1("Chocolate Dye", "61")
            )
         );
   }

   private double method5(ShopItem gui3_21) {
      double value2 = this.field11.method3(gui3_21.method1(), true).getValue();
      if (value2 <= 0.0) {
         return -1.0;
      }

      for (RequiredItem gui55 : gui3_21.method7()) {
         value2 -= this.field11.method2(gui55.id()).getValue() * gui55.amount();
      }

      return value2 / (gui3_21.getCost() / 1000000.0);
   }

   public String getId() {
      return "SKYBLOCK_CHOCOLATE_SHOP_HELPER";
   }

   protected void method1(boolean flag1) {
   }
}
