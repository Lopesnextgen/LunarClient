package com.moonsworth.lunar.client.mod.skyblock.fishingbaithud;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.FishingHookTracker;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileLoadEvent;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

@VersionGate(min = 33)
public class SkyblockFishingBaitHud extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private final SkyblockProfileCache field9 = (SkyblockProfileCache)this.method10(SkyblockProfileCache.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener field10 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener)this.method10(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener.class
   );
   private final ScreenTitleListener field11 = (ScreenTitleListener)this.method10(ScreenTitleListener.class);
   private final FishingHookTracker field12 = (FishingHookTracker)this.method10(FishingHookTracker.class);
   private final ToggleOption field13 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "countInventoryBait"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "groupBait"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "amountColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final List<SkyblockFishingBaitHud.BaitStack> field16 = new ArrayList<>();
   private final List<SkyblockFishingBaitHud.BaitStack> field17 = new ArrayList<>();
   private boolean field18;

   public SkyblockFishingBaitHud(Skyblock skyblock1) {
      super(false);
      this.method11(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method11(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockFishingBaitHud.Data()));
      this.method11(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method11(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTick.class, arg1x -> {
         this.method13();
         this.method14();
      });
      this.handle(SkyblockProfileLoadEvent.class, arg1x -> this.method15());
   }

   private void method13() {
      boolean flag1 = this.field12.method12();
      if (!this.field18 && flag1 && !this.field16.isEmpty() && !this.field16.get(0).method1()) {
         this.field16.remove(0);
      }

      this.field18 = flag1;
   }

   private void method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         Set set2 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method40();
         if (set2 != null) {
            this.field17.clear();
            if ((Boolean)this.field13.get()) {
               for (ItemStackBridge bridgeextension_44 : bridge5extension_51.bridge$getInventory().bridge$getMainInventory()) {
                  String text5 = SkyblockItemUtil.method2(bridgeextension_44);
                  if (set2.contains(text5)) {
                     this.field17.add(new SkyblockFishingBaitHud.BaitStack(bridgeextension_44));
                  }
               }
            }

            if (this.field10.method7() == SkyblockMenuType.FISHING_BAG) {
               this.field16.clear();
               GuiContainerBridge bridge5extension_39 = this.field11.method6();
               List list10 = bridge5extension_39.bridge$inventorySlots();
               int number11 = bridge5extension_39.bridge$getLowerChestSizeInventory();

               for (int index6 = 0; index6 < list10.size() - number11; index6++) {
                  ItemStackBridge bridgeextension_47 = ((SlotBridge)list10.get(index6)).bridge$getItemStack();
                  String text8 = SkyblockItemUtil.method2(bridgeextension_47);
                  if (set2.contains(text8)) {
                     this.field16.add(new SkyblockFishingBaitHud.BaitStack(bridgeextension_47));
                  }
               }
            }
         }
      }
   }

   private void method15() {
      Member member1 = this.field9.method9();
      if (member1 != null) {
         List list2 = SkyblockItemUtil.method28((String)member1.inventory().bagContents().fishingBag().data().orElse(null));
         if (list2 != null) {
            this.field16.clear();

            for (ItemStackBridge bridgeextension_44 : list2) {
               if (!bridgeextension_44.bridge$isEmpty()) {
                  this.field16.add(new SkyblockFishingBaitHud.BaitStack(bridgeextension_44));
               }
            }
         }
      }
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.method14();
         this.method15();
      }
   }

   private List<SkyblockFishingBaitHud.BaitStack> method16() {
      List list1 = Stream.concat(this.field16.stream(), this.field17.stream()).toList();
      return !this.field14.get()
         ? this.method6(list1)
         : new ArrayList<>(
            list1.stream()
               .collect(
                  Collectors.toMap(
                     SkyblockFishingBaitHud.BaitStack::getId, arg0 -> (SkyblockFishingBaitHud.BaitStack)arg0, SkyblockFishingBaitHud.BaitStack::method2, LinkedHashMap::new
                  )
               )
               .values()
         );
   }

   private List<SkyblockFishingBaitHud.BaitStack> method6(List<SkyblockFishingBaitHud.BaitStack> list1) {
      if (list1.isEmpty()) {
         return new ArrayList<>();
      }

      ArrayList list2 = new ArrayList();

      for (SkyblockFishingBaitHud.BaitStack data24 : list1) {
         if (list2.isEmpty()) {
            list2.add(data24);
         } else {
            int index5 = list2.size() - 1;
            SkyblockFishingBaitHud.BaitStack data26 = (SkyblockFishingBaitHud.BaitStack)list2.get(index5);
            if (data26.getId().equals(data24.getId())) {
               list2.set(index5, data26.method2(data24));
            } else {
               list2.add(data24);
            }
         }
      }

      return list2;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field13}));
      lightingextension231.method7(SettingsPage.HUD, arg1x -> arg1x.method9(new ClientOption[]{this.field14}));
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field15}));
   }

   public String getId() {
      return "SKYBLOCK_FISHING_BAIT_HUD";
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(15, 120, 200, 60, 150, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method24();
         }

         ArrayList list2 = new ArrayList();

         for (SkyblockFishingBaitHud.BaitStack data24 : SkyblockFishingBaitHud.this.method16()) {
            list2.add(new HudLine(data24.getItem(), this.method5(data24.method3(), data24.getAmount())));
         }

         return list2.isEmpty() ? null : list2;
      }

      private List<HudLine> method24() {
         ArrayList list1 = new ArrayList();
         this.method4(list1, "FISH_BAIT", "Fish Bait", NamedTextColor.WHITE, 512);
         this.method4(list1, "SPOOKY_BAIT", "Spooky Bait", NamedTextColor.WHITE, 144);
         this.method4(list1, "WHALE_BAIT", "Whale Bait", NamedTextColor.BLUE, 32);
         if (list1.isEmpty()) {
            ModDetails framework82 = (ModDetails)SkyblockFishingBaitHud.this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field13);
            return framework82 == null ? null : List.of(new HudLine(Component.text(framework82.getName())));
         } else {
            return list1;
         }
      }

      private void method4(List<HudLine> list1, String text2, String text3, TextColor textcolor4, int number5) {
         ItemStackBridge bridgeextension_46 = SkyblockItemRegistry.method3(text2);
         if (bridgeextension_46 != null) {
            list1.add(new HudLine(bridgeextension_46, this.method5(Component.text(text3, textcolor4), number5)));
         }
      }

      private Component method5(TextComponent text1, int number2) {
         return TextComponentFactory.builder()
            .method1(text1)
            .method4(SkyblockFishingBaitHud.field8.format(number2))
            .method8(SkyblockFishingBaitHud.this.field15.method14(0.0F))
            .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
            .build();
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

      protected boolean method19() {
         return false;
      }

      protected boolean method23() {
         return true;
      }
   }

   private static class BaitStack {
      private final ItemStackBridge field1;
      private final String field2;
      private final TextComponent field3;
      private int amount;

      public BaitStack(ItemStackBridge bridgeextension_41) {
         this.field1 = bridgeextension_41;
         this.field2 = SkyblockItemUtil.method2(bridgeextension_41);
         this.field3 = TextBridge.asAdventure(bridgeextension_41.bridge$getDisplayName());
         this.amount = bridgeextension_41.bridge$getStackSize();
      }

      private boolean method1() {
         return --this.amount > 0;
      }

      private SkyblockFishingBaitHud.BaitStack method2(SkyblockFishingBaitHud.BaitStack data21) {
         return new SkyblockFishingBaitHud.BaitStack(this.field1, this.field2, this.field3, this.amount + data21.getAmount());
      }

      @Generated
      public BaitStack(ItemStackBridge bridgeextension_41, String text2, TextComponent text3, int number4) {
         this.field1 = bridgeextension_41;
         this.field2 = text2;
         this.field3 = text3;
         this.amount = number4;
      }

      @Generated
      public ItemStackBridge getItem() {
         return this.field1;
      }

      @Generated
      public String getId() {
         return this.field2;
      }

      @Generated
      public TextComponent method3() {
         return this.field3;
      }

      @Generated
      public int getAmount() {
         return this.amount;
      }
   }
}
