package com.moonsworth.lunar.client.mod.skyblock.raritybackground;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lunarclient.adventure.matcher.ComponentMatcher;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ChatComponentStyleBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.LoreComponent;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarItems;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.experimentsolvers.SkyblockSuperpairs;
import com.moonsworth.lunar.client.mod.skyblock.experimentsolvers.SkyblockExperimentSolvers;

public class SkyblockRarityBackground extends AbstractFeature {
   private final FloatOption field8 = (FloatOption)((Data)((Data)OptionFactory.method2("skyBlockRarityOpacity")
            .method4(0.4F))
         .method8(0.0F, 1.0F))
      .method31();
   private final LoadingCache<ItemStackBridge, ItemRarity> field9 = CacheBuilder.newBuilder()
      .expireAfterAccess(1L, TimeUnit.SECONDS)
      .softValues()
      .build(new CacheLoader<ItemStackBridge, ItemRarity>() {
         public ItemRarity method1(@NotNull ItemStackBridge bridgeextension_41) {
            return SkyblockRarityBackground.this.method7(bridgeextension_41);
         }
      });

   public SkyblockRarityBackground(Skyblock skyblock1) {
      super(true);
      this.method10(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method10(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method10(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender.class, this::method3);
      this.handle(EventRenderHotbarItems.class, this::method4);
   }

   public String getId() {
      return "SKYBLOCK_RARITY_BACKGROUND";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private void method3(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender data1) {
      if (IslandUtils.isOnIsland() && !this.shouldCancel()) {
         if (data1.method3() instanceof GuiContainerBridge bridge5extension_32) {
            for (SlotBridge bridge3_184 : bridge5extension_32.bridge$inventorySlots()) {
               ItemStackBridge bridgeextension_45 = bridge3_184.bridge$getItemStack();
               if (bridgeextension_45 != null && !bridgeextension_45.bridge$isEmpty()) {
                  this.method6(
                     data1.method5(),
                     bridgeextension_45,
                     bridge5extension_32.bridge$getGuiLeft() + bridge3_184.bridge$getXDisplayPosition(),
                     bridge5extension_32.bridge$getGuiTop() + bridge3_184.bridge$getYDisplayPosition()
                  );
               }
            }
         }
      }
   }

   private void method4(EventRenderInventoryScreen highlightbase21) {
      if (IslandUtils.isOnIsland() && !this.shouldCancel()) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            List list3 = bridge5extension_52.bridge$getInventory().bridge$getMainInventory();

            for (int index4 = 0; index4 < 9; index4++) {
               ItemStackBridge bridgeextension_45 = (ItemStackBridge)list3.get(index4);
               if (bridgeextension_45 != null && !bridgeextension_45.bridge$isEmpty()) {
                  int number6 = highlightbase21.getX() + 3 + index4 * 20;
                  int number7 = highlightbase21.getY() + 3;
                  this.method6(highlightbase21.method1(), bridgeextension_45, number6, number7);
               }
            }

            if (bridge5extension_52.bridge$getOffHandItemRenderState() instanceof ItemStackBridge bridgeextension_48) {
               if (!bridgeextension_48.bridge$isEmpty()) {
                  this.method6(highlightbase21.method1(), bridgeextension_48, highlightbase21.getX() - 20, highlightbase21.getY() + 3);
               }
            }
         }
      }
   }

   public @Nullable Integer method5(ItemStackBridge bridgeextension_41) {
      ItemRarity guitype32;
      try {
         guitype32 = (ItemRarity)this.field9.get(bridgeextension_41);
      } catch (ExecutionException executionexception5) {
         CrashReporter.method5(executionexception5, "SkyBlockItemRarityBackground");
         return null;
      }

      if (guitype32 == ItemRarity.NONE) {
         return null;
      }

      int number3 = guitype32.getNamedTextColor().value();
      int number4 = Math.round((Float)this.field8.get() * 255.0F);
      return ColorUtils.method22(number3, number4);
   }

   private void method6(MixinHelper_4 mixinhelper_41, ItemStackBridge bridgeextension_42, int number3, int number4) {
      Integer number5 = this.method5(bridgeextension_42);
      if (number5 != null) {
         mixinhelper_41.method45(arg3x -> arg3x.method1(number3, number4, number3 + 16, number4 + 16, number5));
      }
   }

   private ItemRarity method7(ItemStackBridge bridgeextension_41) {
      LoreComponent mixinhelper7_52 = (LoreComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field8);
      if (mixinhelper7_52 == null) {
         return ItemRarity.NONE;
      }

      List list3 = mixinhelper7_52.bridge$getLines();

      for (int index4 = list3.size() - 1; index4 >= 0; index4--) {
         Component component5 = ((ChatComponentStyleBridge)list3.get(index4)).moonBridge$asAdventureComponent();
         Optional optional6 = this.method8(component5);
         if (optional6.isPresent()) {
            return (ItemRarity)optional6.get();
         }
      }

      CompoundTagComponent mixinhelper_107 = (CompoundTagComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field1);
      if (mixinhelper_107 == null) {
         return ItemRarity.NONE;
      }

      CompoundTagBridge bridge_578 = mixinhelper_107.bridge$getData();
      if (bridge_578 == null) {
         return ItemRarity.NONE;
      }

      String text9 = bridge_578.bridge$getString("petInfo");
      return text9 == null ? ItemRarity.NONE : this.method9(text9);
   }

   private Optional<ItemRarity> method8(Component component1) {
      String text2 = TextBridge.getTextContent(component1);
      text2 = text2.replaceFirst("^Rarity: ", "");

      for (ItemRarity guitype36 : ItemRarity.values()) {
         if (guitype36 != ItemRarity.NONE) {
            ComponentMatcher componentmatcher7 = guitype36.getMatcher();
            if (guitype36.getStartsWithAnalyzer().matcher(text2).find() && componentmatcher7.matches(component1)) {
               return Optional.of(guitype36);
            }
         }
      }

      return Optional.empty();
   }

   private ItemRarity method9(@NotNull String text1) {
      try {
         JsonElement element2 = JsonParser.parseString(text1);
         JsonElement element3 = element2.getAsJsonObject().get("tier");
         if (element3 == null) {
            return ItemRarity.NONE;
         }

         String text4 = element3.getAsString();

         for (ItemRarity guitype38 : ItemRarity.values()) {
            if (text4.contains(guitype38.getTag())) {
               return guitype38;
            }
         }
      } catch (Exception exception9) {
         return ItemRarity.NONE;
      }

      return ItemRarity.NONE;
   }

   private boolean shouldCancel() {
      Skyblock skyblock1 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      SkyblockExperimentSolvers skyblockexperimentsolvers2 = skyblock1.method120();
      if (!skyblockexperimentsolvers2.isEnabled()) {
         return false;
      }

      SkyblockSuperpairs skyblocksuperpairs3 = skyblockexperimentsolvers2.method24();
      return skyblocksuperpairs3.isEnabled() && skyblocksuperpairs3.isInGui();
   }
}
