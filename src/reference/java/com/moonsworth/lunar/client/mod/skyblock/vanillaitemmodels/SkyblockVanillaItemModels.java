package com.moonsworth.lunar.client.mod.skyblock.vanillaitemmodels;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.widget.MultiSelectWidget;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.command.StringArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemIdCodec;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.GameDataLoadEvent;
import com.moonsworth.lunar.client.command.CommandSuggestionBuilder;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkyblockVanillaItemModels extends AbstractFeature {
   private final ListOption<String> field8 = (ListOption<String>)((com.moonsworth.lunar.client.config.option.ListOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method31(
            "skyBlockVanillaModelOverrides", Codec.STRING.listOf()
         )
         .method7((arg1x, arg2) -> new MultiSelectWidget(arg1x, arg2, 12.0F, false, this::method5)))
      .method31();
   private volatile Map<String, String> field9 = Map.of();
   private final ListOption<String> field10 = (ListOption<String>)((com.moonsworth.lunar.client.config.option.ListOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method31(
            "skyBlockVanillaModelWhitelist", Codec.STRING.listOf()
         )
         .method7((arg1x, arg2) -> new MultiSelectWidget(arg1x, arg2, 12.0F, false, this::method6)))
      .method31();
   private final Map<SkyblockVanillaItemModels.VanillaModelCacheKey, ItemStackBridge> field11 = new ConcurrentHashMap<>();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("onlyModelOverrides")
      .method31();
   private final Set<String> field13 = ConcurrentHashMap.newKeySet();
   private final LoadingCache<ItemStackBridge, Optional<SkyblockVanillaItemModels.Extension>> field14 = CacheBuilder.newBuilder()
      .expireAfterAccess(1L, TimeUnit.SECONDS)
      .softValues()
      .build(new CacheLoader<ItemStackBridge, Optional<SkyblockVanillaItemModels.Extension>>() {
         public Optional<SkyblockVanillaItemModels.Extension> method1(@NotNull ItemStackBridge bridgeextension_41) {
            return SkyblockVanillaItemModels.this.method11(bridgeextension_41);
         }
      });

   public SkyblockVanillaItemModels(Skyblock skyblock1) {
      super(false);
      this.method14(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method14(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method14(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method31(new SkyblockVanillaItemModels.Data());
      this.handle(GameDataLoadEvent.class, arg1x -> this.method13());
      this.field8.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.method14());
      this.field10.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.method13());
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.method13());
   }

   public String getId() {
      return "SKYBLOCK_VANILLA_ITEM_MODELS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      ((SettingsSectionImpl)lightingextension231.method12("skyBlockVanillaModelOverridesHelp")).method4(true);
      lightingextension231.method9(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)lightingextension231.method12("skyBlockVanillaModelWhitelistHelp")).method4(true);
      ((SettingsSectionImpl)lightingextension231.method12("skyBlockVanillaModelWhitelistHelp2")).method4(true);
      lightingextension231.method9(new ClientOption[]{this.field10, this.field12});
   }

   private void method13() {
      this.field14.invalidateAll();
      this.field11.clear();
      this.field13.clear();
   }

   private void method14() {
      this.field9 = ItemIdCodec.method6((List)this.field8.get());
      this.method13();
   }

   @Nullable
   private String method5(String text1) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.ItemIdCodec.ItemIdPair data22 = ItemIdCodec.method5(text1);
      if (data22 == null) {
         this.method7(this.method31("invalidOverride", new Object[0]));
         return null;
      } else if (!ItemIdCodec.method2(data22.itemId())) {
         this.method7(this.method31("unknownItemOnVersion", new Object[]{data22.itemId()}));
         return null;
      } else {
         ItemIdCodec.method7(this.field8, data22.method1());
         return data22.encode();
      }
   }

   @Nullable
   private String method6(String text1) {
      String text2 = ItemIdCodec.method3(text1);
      if (text2 == null) {
         this.method7(this.method31("invalidSkyBlockId", new Object[0]));
      }

      return text2;
   }

   private void method7(String text1) {
      Ref.method4().method69().method6(NotificationType.ERROR, this.method18(), text1);
   }

   @Nullable
   public ResourceLocationBridge method8(@Nullable ItemStackBridge bridgeextension_41) {
      return this.method10(bridgeextension_41) instanceof SkyblockVanillaItemModels$HHRROIIHRRICIIHIIHICRHHRHOHHOO$Data2 skyblockvanillaitemmodels$hhrroiihrriciihiihicrhhrhohhoo$data22 ? skyblockvanillaitemmodels$hhrroiihrriciihiihicrhhrhohhoo$data22.method1() : null;
   }

   @Nullable
   public ItemStackBridge method9(@Nullable ItemStackBridge bridgeextension_41) {
      return this.method10(bridgeextension_41) instanceof SkyblockVanillaItemModels$HHRROIIHRRICIIHIIHICRHHRHOHHOO$Data skyblockvanillaitemmodels$hhrroiihrriciihiihicrhhrhohhoo$data2 ? skyblockvanillaitemmodels$hhrroiihrriciihiihicrhhrhohhoo$data2.method1() : null;
   }

   @Nullable
   private SkyblockVanillaItemModels.Extension method10(@Nullable ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty() && IslandUtils.isOnIsland()) {
         try {
            return (SkyblockVanillaItemModels.Extension)((Optional)this.field14.get(bridgeextension_41)).orElse(null);
         } catch (ExecutionException executionexception3) {
            if (this.field13.add("substitution-cache")) {
               CrashReporter.method5(executionexception3, "SkyBlockVanillaItemModels");
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private Optional<SkyblockVanillaItemModels.Extension> method11(ItemStackBridge bridgeextension_41) {
      if (!bridgeextension_41.bridge$hasCustomModel()) {
         return Optional.empty();
      }

      String text2 = SkyblockItemUtil.method2(bridgeextension_41);
      if (text2.isEmpty()) {
         return Optional.empty();
      }

      if (this.field10.contains(text2)) {
         return Optional.empty();
      }

      String text4 = this.field9.get(text2);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data data3;
      if (text4 != null) {
         data3 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data(text4, null, null);
      } else {
         if ((Boolean)this.field12.get()) {
            return Optional.empty();
         }

         Skyblock skyblock5 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry gui46 = skyblock5.method15().method43();
         data3 = gui46 == null ? null : gui46.method1(text2);
      }

      if (data3 == null) {
         String text8 = bridgeextension_41.bridge$getItem().bridge$getRegistryName();
         return "minecraft:paper".equals(text8) ? Optional.empty() : this.method16(text8);
      } else if (data3.method2() != null) {
         return bridgeextension_41.bridge$getItem() == Bridge.method28().method5()
            ? this.method16(data3.item())
            : this.method12(text2, data3, bridgeextension_41.bridge$hasFoil(), () -> this.method13(data3, bridgeextension_41.bridge$hasFoil()));
      } else {
         ItemBridge bridge6_47 = Bridge.method28().method22(data3.item());
         if (bridge6_47 == null || bridge6_47 == Bridge.method28().method25()) {
            return Optional.empty();
         } else {
            return bridge6_47 != bridgeextension_41.bridge$getItem() && data3.method1() != null
               ? this.method12(text2, data3, bridgeextension_41.bridge$hasFoil(), () -> this.method14(bridge6_47, data3.method1(), bridgeextension_41.bridge$hasFoil()))
               : this.method16(data3.item());
         }
      }
   }

   private Optional<SkyblockVanillaItemModels.Extension> method12(
      String text1, com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data data2, boolean flag3, Supplier<ItemStackBridge> supplier4
   ) {
      try {
         ItemStackBridge bridgeextension_45 = this.field11.computeIfAbsent(new SkyblockVanillaItemModels.VanillaModelCacheKey(data2, flag3), arg1x -> (ItemStackBridge)supplier4.get());
         return Optional.of(new SkyblockVanillaItemModels$HHRROIIHRRICIIHIIHICRHHRHOHHOO$Data(bridgeextension_45));
      } catch (Exception exception6) {
         if (this.field13.add(text1)) {
            CrashReporter.method5(exception6, "SkyBlockVanillaItemModels " + text1);
         }

         return Optional.empty();
      }
   }

   private ItemStackBridge method13(com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data data1, boolean flag2) {
      return this.method15(SkyblockItemUtil.method13(data1.method2()), flag2);
   }

   private ItemStackBridge method14(ItemBridge bridge6_41, int number2, boolean flag3) {
      ItemStackBridge bridgeextension_44 = Bridge.method8().method38(bridge6_41);
      bridgeextension_44.bridge$setDyedColor(number2);
      return this.method15(bridgeextension_44, flag3);
   }

   private ItemStackBridge method15(ItemStackBridge bridgeextension_41, boolean flag2) {
      if (flag2) {
         bridgeextension_41.bridge$setFoil(true);
      }

      return bridgeextension_41;
   }

   private Optional<SkyblockVanillaItemModels.Extension> method16(String text1) {
      return Optional.of(new SkyblockVanillaItemModels$HHRROIIHRRICIIHIIHICRHHRHOHHOO$Data2(ResourceLocationBridge.create(text1)));
   }

   private void method17() {
      SkyBlockChat.method1(this.method31("usage", new Object[0]));
   }

   private void method18(String text1) {
      String text2 = ItemIdCodec.method1(text1);
      if (text2 != null && ItemIdCodec.method2(text2)) {
         String text3 = this.method22();
         if (text3 == null) {
            SkyBlockChat.method1(this.method31("holdToOverride", new Object[0]));
         } else {
            ItemIdCodec.method7(this.field8, text3);
            if (!this.field8.add(ItemIdCodec.method4(text3, text2))) {
               SkyBlockChat.method1(this.method31("saveFailed", new Object[]{text3}));
            } else {
               SkyBlockChat.method1(this.method31("overrideSet", new Object[]{text3, text2}));
               this.field10.remove(text3);
            }
         }
      } else {
         SkyBlockChat.method1(this.method31("unknownItem", new Object[]{text1}));
      }
   }

   private void method19() {
      String text1 = this.method22();
      if (text1 == null) {
         SkyBlockChat.method1(this.method31("holdToWhitelist", new Object[0]));
      } else if (this.field10.remove(text1)) {
         SkyBlockChat.method1(this.method31("whitelistRemoved", new Object[]{text1}));
      } else if (!this.field10.add(text1)) {
         SkyBlockChat.method1(this.method31("saveFailed", new Object[]{text1}));
      } else {
         SkyBlockChat.method1(this.method31("whitelisted", new Object[]{text1}));
         ItemIdCodec.method7(this.field8, text1);
      }
   }

   private void method21() {
      String text1 = this.method22();
      if (text1 == null) {
         SkyBlockChat.method1(this.method31("holdToRemove", new Object[0]));
      } else {
         if (ItemIdCodec.method7(this.field8, text1)) {
            SkyBlockChat.method1(this.method31("overrideRemoved", new Object[]{text1}));
         } else if (this.field10.remove(text1)) {
            SkyBlockChat.method1(this.method31("whitelistRemoved", new Object[]{text1}));
         } else {
            SkyBlockChat.method1(this.method31("noOverride", new Object[]{text1}));
         }
      }
   }

   @Nullable
   private String method22() {
      if (Ref.method7() == null) {
         return null;
      }

      String text1 = SkyblockItemUtil.method2(Ref.method7().bridge$getCurrentEquippedItem());
      return text1.isEmpty() ? null : text1;
   }

   private void method22(CommandSuggestionBuilder nameplate3_21) {
      String text2 = nameplate3_21.method1().toLowerCase(Locale.ROOT);

      for (ItemBridge bridge6_44 : Bridge.method28().method99()) {
         String text5 = bridge6_44.bridge$getRegistryName();
         if (text5.startsWith("minecraft:")) {
            text5 = text5.substring("minecraft:".length());
         }

         if (text5.startsWith(text2)) {
            nameplate3_21.method2(text5);
         }
      }
   }

   private final class Data extends ClientCommand {
      private Data() {
         super(
            LiteralCommandNode.method1("lcitem")
               .method3(arg1x -> SkyblockVanillaItemModels.this.method17())
               .method2(LiteralCommandNode.method1("remove").method3(arg1x -> SkyblockVanillaItemModels.this.method21()))
               .method2(LiteralCommandNode.method1("whitelist").method3(arg1x -> SkyblockVanillaItemModels.this.method19()))
               .method2(
                  ArgumentCommandNode.method1("item", StringArgumentParser.field1)
                     .method2((arg1x, arg2) -> SkyblockVanillaItemModels.this.method22(arg2))
                     .method8(arg1x -> SkyblockVanillaItemModels.this.method18(arg1x.getString("item")))
               )
         );
      }

      public boolean isEnabled() {
         return SkyblockVanillaItemModels.this.isEnabled() && IslandUtils.isOnIsland();
      }
   }

   private class VanillaModelCacheKey {
      private final com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data field1;
      private final boolean field2;

      private VanillaModelCacheKey(com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data data1, boolean flag2) {
         this.field1 = data1;
         this.field2 = flag2;
      }

      public com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.VanillaItemRegistry.Data method1() {
         return this.field1;
      }

      public boolean method2() {
         return this.field2;
      }
   }

   private sealed interface Extension
      permits SkyblockVanillaItemModels$HHRROIIHRRICIIHIIHICRHHRHOHHOO$Data2,
      SkyblockVanillaItemModels$HHRROIIHRRICIIHIIHICRHHRHOHHOO$Data {
   }
}
