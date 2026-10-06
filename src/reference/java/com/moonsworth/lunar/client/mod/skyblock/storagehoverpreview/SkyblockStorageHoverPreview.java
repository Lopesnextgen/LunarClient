package com.moonsworth.lunar.client.mod.skyblock.storagehoverpreview;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.StorageOverlayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.Storageoverlay;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkyblockStorageHoverPreview extends AbstractFeature {
   private static final ItemStackBridge field8 = Bridge.method8()
      .method38((ItemBridge)Bridge.method28().method93().get(Ref.MC_VERSION >= 33 ? 15 : 0));
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create("minecraft", "textures/gui/container/generic_54.png");
   private final StorageOverlayListener field10 = (StorageOverlayListener)this.method63(StorageOverlayListener.class);
   private final HighlightTypeListener field11 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ToggleOption field12 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "personalCompactor"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "personalDeletor"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "backpack"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "enderChest"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "cakeBag"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "buildersWand"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "buildersRuler"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "basketOfSeeds"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "netherWartPouch"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field21 = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("storagePreviewKeybind")
      .method5(KeyCode.KEY_LCONTROL)
      .method31();
   private final ToggleOption field22 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("holdForPreview")
      .method31();
   private final ColorOption field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "storageColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "useBackpackColor"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final List<SkyblockStorageHoverPreview.StoragePreviewRule> field25 = List.of(
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_COMPACTOR_4000", this.field12, 1, this.method5("compact"), 4),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_COMPACTOR_5000", this.field12, 1, this.method5("compact"), 3, 4, 5),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_COMPACTOR_6000", this.field12, 1, this.method5("compact"), 1, 2, 3, 4, 5, 6, 7),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_COMPACTOR_7000", this.field12, 2, this.method5("compact"), 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_DELETOR_4000", this.field13, 1, this.method5("deletor"), 4),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_DELETOR_5000", this.field13, 1, this.method5("deletor"), 3, 4, 5),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_DELETOR_6000", this.field13, 1, this.method5("deletor"), 1, 2, 3, 4, 5, 6, 7),
      new SkyblockStorageHoverPreview.StoragePreviewRule("PERSONAL_DELETOR_7000", this.field13, 2, this.method5("deletor"), 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15),
      new SkyblockStorageHoverPreview.StoragePreviewRule("Backpack Slot ", this.field14, this.method7(arg0 -> !arg0.isEnderChestPage())),
      new SkyblockStorageHoverPreview.StoragePreviewRule("Ender Chest Page ", this.field15, this.method7(Storageoverlay::isEnderChestPage)),
      new SkyblockStorageHoverPreview.StoragePreviewRule("NEW_YEAR_CAKE_BAG", this.field16, 6, this.method6("new_year_cake_bag_data")),
      new SkyblockStorageHoverPreview.StoragePreviewRule("BUILDERS_WAND", this.field17, 6, this.method6("builder's_wand_data")),
      new SkyblockStorageHoverPreview.StoragePreviewRule("BUILDERS_RULER", this.field18, 6, this.method6("builder's_ruler_data")),
      new SkyblockStorageHoverPreview.StoragePreviewRule("BASKET_OF_SEEDS", this.field19, 6, this.method6("basket_of_seeds_data")),
      new SkyblockStorageHoverPreview.StoragePreviewRule("NETHER_WART_POUCH", this.field20, 6, this.method6("nether_wart_pouch_data"))
   );
   private final LoadingCache<ItemStackBridge, Optional<SkyblockStorageHoverPreview.Data>> field26 = CacheBuilder.newBuilder()
      .expireAfterAccess(1L, TimeUnit.MINUTES)
      .weakKeys()
      .maximumSize(40L)
      .build(new CacheLoader<ItemStackBridge, Optional<SkyblockStorageHoverPreview.Data>>() {
         public Optional<SkyblockStorageHoverPreview.Data> method1(@NotNull ItemStackBridge bridgeextension_41) {
            return SkyblockStorageHoverPreview.this.method4(bridgeextension_41);
         }
      });

   public SkyblockStorageHoverPreview(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method1, 200);
   }

   private void method1(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      SkyblockMenuType highlighttype2 = this.field11.method7();
      if (highlighttype2 == null || !highlighttype2.isAuctionGui()) {
         if (this.field21.isKeyDown() == (Boolean)this.field22.get()) {
            ItemStackBridge bridgeextension_43 = (ItemStackBridge)data21.method1().orElse(null);
            if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
               SkyblockStorageHoverPreview.Data data4;
               try {
                  data4 = (SkyblockStorageHoverPreview.Data)((Optional)this.field26.get(bridgeextension_43)).orElse(null);
               } catch (ExecutionException executionexception7) {
                  CrashReporter.method5(executionexception7, "SkyBlockStorageHoverPreview");
                  return;
               }

               if (data4 != null) {
                  int number5 = this.field23.method14(0.0F);
                  if ((Boolean)this.field24.get() && data4.method1().method3() == this.field14) {
                     number5 = this.method8(bridgeextension_43).orElse(this.field23.method14(0.0F));
                  }

                  ItemStackBridge[] items6 = data4.method2();
                  this.method2(data21.method2(), data21.getX() + 8, data21.getY() - 8, number5, data4.method1().method2(items6), bridgeextension_43);
                  this.method3(data21.method2(), items6, data21.getX() + 8, data21.getY() - 8);
                  data21.cancel();
               }
            }
         }
      }
   }

   private void method2(MixinHelper_4 mixinhelper_41, int number2, int number3, int number4, int number5, ItemStackBridge bridgeextension_46) {
      int number7 = number5 * 18 + 17;
      LcuiScreen.method37(mixinhelper_41, field9, number2, number3, 256.0F, 256.0F, 0.0F, 0.0F, 256.0F, number7, number4);
      LcuiScreen.method37(mixinhelper_41, field9, number2, number3 + number7, 256.0F, 256.0F, 0.0F, 215.0F, 256.0F, 221.0F, number4);
      mixinhelper_41.push();
      mixinhelper_41.method10(
         Ref.method10(), TextBridge.asAdventure(bridgeextension_46.bridge$getDisplayName()).color(NamedTextColor.DARK_GRAY), number2 + 8, number3 + 6, -1, false
      );
      mixinhelper_41.pop();
   }

   private void method3(MixinHelper_4 mixinhelper_41, ItemStackBridge[] items2, int number3, int number4) {
      mixinhelper_41.push();
      mixinhelper_41.method44(arg0 -> arg0.method29().method33());

      for (int index5 = 0; index5 < items2.length; index5++) {
         ItemStackBridge bridgeextension_46 = items2[index5];
         if (bridgeextension_46 != null) {
            int number7 = number3 + 8 + index5 % 9 * 18;
            int number8 = number4 + 20 + index5 / 9 * 18 - 2;
            mixinhelper_41.method35(bridgeextension_46, number7, number8, true);
         }
      }

      mixinhelper_41.method44(arg0 -> {
         AbstractRenderContext bridgeextension_91x = arg0.method29();
         bridgeextension_91x.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
         bridgeextension_91x.method33();
      });
      mixinhelper_41.pop();
   }

   private Optional<SkyblockStorageHoverPreview.Data> method4(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = SkyblockItemUtil.method2(bridgeextension_41);
      String text3 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_41.bridge$getDisplayName());

      for (SkyblockStorageHoverPreview.StoragePreviewRule data25 : this.field25) {
         if (data25.isEnabled()) {
            String text6 = data25.getItem();
            if (text2.equals(text6) || text3.startsWith(text6)) {
               ItemStackBridge[] items7 = data25.method1(bridgeextension_41);
               return items7 == null ? Optional.empty() : Optional.of(new SkyblockStorageHoverPreview.Data(data25, items7));
            }
         }
      }

      return Optional.empty();
   }

   private Function<ItemStackBridge, ItemStackBridge[]> method5(String text1) {
      return arg1x -> {
         CompoundTagBridge bridge_572 = SkyblockItemUtil.method1(arg1x);
         if (bridge_572 == null) {
            return null;
         }

         ItemStackBridge[] items3 = new ItemStackBridge[12];

         for (int index4 = 0; index4 <= 11; index4++) {
            String text5 = bridge_572.bridge$getString("personal_" + text1 + "_" + index4);
            if (!text5.isEmpty()) {
               items3[index4] = SkyblockItemRegistry.method3(text5);
            }
         }

         return items3;
      };
   }

   private Function<ItemStackBridge, ItemStackBridge[]> method6(String text1) {
      return arg1x -> {
         CompoundTagBridge bridge_572 = SkyblockItemUtil.method1(arg1x);
         if (bridge_572 == null) {
            return null;
         }

         ItemStackBridge[] items3 = new ItemStackBridge[54];
         byte[] items4 = bridge_572.bridge$getByteArray(text1);

         NBTTagListBridge bridge3_65;
         try {
            bridge3_65 = SkyblockItemUtil.method24(items4);
         } catch (IOException exception8) {
            return null;
         }

         for (int index6 = 0; index6 < bridge3_65.bridge$size(); index6++) {
            CompoundTagBridge bridge_577 = bridge3_65.bridge$getCompoundAt(index6);
            items3[index6] = SkyblockItemUtil.method27(bridge_577);
         }

         return items3;
      };
   }

   private Function<ItemStackBridge, ItemStackBridge[]> method7(Predicate<Storageoverlay> predicate1) {
      return arg2 -> {
         int number3 = NumberUtils.method3(ChatFormatting.getTextWithoutFormattingCodes(arg2.bridge$getDisplayName()).replaceAll("\\D", ""));
         if (number3 == 0) {
            return null;
         }

         for (Storageoverlay storageoverlay5 : this.field10.method6()) {
            if (storageoverlay5 != null && predicate1.test(storageoverlay5) && storageoverlay5.getMenuIndex() == number3) {
               return storageoverlay5.method7() ? storageoverlay5.method1().toArray(new ItemStackBridge[0]) : null;
            }
         }

         return null;
      };
   }

   private Optional<Integer> method8(ItemStackBridge bridgeextension_41) {
      CompoundTagBridge bridge_572 = SkyblockItemUtil.method1(bridgeextension_41);
      if (bridge_572 == null) {
         return Optional.empty();
      }

      String text3 = bridge_572.bridge$getString("backpack_color");
      if (text3.isEmpty()) {
         return Optional.empty();
      }

      return Optional.ofNullable(switch (text3) {
         case "WHITE" -> -393218;
         case "ORANGE" -> -425955;
         case "MAGENTA" -> -3715395;
         case "LIGHT_BLUE" -> -12930086;
         case "YELLOW" -> -75715;
         case "LIME" -> -8337633;
         case "PINK" -> -816214;
         case "GREY" -> -12103854;
         case "LIGHT_GREY" -> -6447721;
         case "CYAN" -> -15295332;
         case "PURPLE" -> -7785800;
         case "BLUE" -> -12827478;
         case "BROWN" -> -8170446;
         case "GREEN" -> -10585066;
         case "RED" -> -5231066;
         case "BLACK" -> -14869215;
         default -> null;
      });
   }

   public String getId() {
      return "SKYBLOCK_STORAGE_HOVER_PREVIEW";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.CONTROLS, arg1x -> arg1x.method9(new ClientOption[]{this.field21, this.field22})
      );
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{
               this.field12, this.field13, this.field14, this.field15, this.field16, this.field17, this.field18, this.field19, this.field20
            }
         )
      );
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field23, this.field24})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data {
      private final SkyblockStorageHoverPreview.StoragePreviewRule field1;
      private final ItemStackBridge[] field2;

      private Data(SkyblockStorageHoverPreview.StoragePreviewRule data21, ItemStackBridge[] items2) {
         this.field1 = data21;
         this.field2 = items2;
      }

      public SkyblockStorageHoverPreview.StoragePreviewRule method1() {
         return this.field1;
      }

      public ItemStackBridge[] method2() {
         return this.field2;
      }
   }

   private static class StoragePreviewRule {
      private final String field1;
      private final ToggleOption field2;
      private final int field3;
      private final boolean[] field4;
      private final Function<ItemStackBridge, ItemStackBridge[]> field5;

      public StoragePreviewRule(String text1, ToggleOption lightingextension4432, int index3, Function<ItemStackBridge, ItemStackBridge[]> function4, int... items5) {
         this.field1 = text1;
         this.field2 = lightingextension4432;
         this.field3 = index3;
         this.field4 = new boolean[index3 * 9];

         for (int index9 : items5) {
            this.field4[index9] = true;
         }

         this.field5 = function4;
      }

      public StoragePreviewRule(String text1, ToggleOption lightingextension4432, int number3, Function<ItemStackBridge, ItemStackBridge[]> function4) {
         this(text1, lightingextension4432, number3, function4);
         Arrays.fill(this.field4, true);
      }

      public StoragePreviewRule(String text1, ToggleOption lightingextension4432, Function<ItemStackBridge, ItemStackBridge[]> function3) {
         this(text1, lightingextension4432, 0, function3);
      }

      public boolean isEnabled() {
         return (Boolean)this.field2.get();
      }

      @Nullable
      public ItemStackBridge[] method1(ItemStackBridge bridgeextension_41) {
         ItemStackBridge[] items2 = this.field5.apply(bridgeextension_41);
         if (items2 == null) {
            return null;
         }

         int index3 = this.method2(items2) * 9;
         ItemStackBridge[] items4 = new ItemStackBridge[index3];
         int index5 = 0;

         for (int index6 = 0; index6 < index3; index6++) {
            if (index6 < this.field4.length && !this.field4[index6]) {
               items4[index6] = SkyblockStorageHoverPreview.field8;
            } else if (index5 < items2.length) {
               items4[index6] = items2[index5++];
            }
         }

         return items4;
      }

      public int method2(ItemStackBridge[] items1) {
         return this.field3 != 0 ? this.field3 : (int)Math.ceil(items1.length / 9.0);
      }

      @Generated
      public String getItem() {
         return this.field1;
      }

      @Generated
      public ToggleOption method3() {
         return this.field2;
      }
   }
}
