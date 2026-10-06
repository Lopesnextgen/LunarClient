package com.moonsworth.lunar.client.mod.skyblock.tiersasstacksize;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class TiersAsStackSize extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final LoadingCache<ItemStackBridge, Optional<TiersAsStackSize.Data>> field9 = CacheBuilder.newBuilder()
      .expireAfterAccess(1L, TimeUnit.SECONDS)
      .softValues()
      .build(new CacheLoader<ItemStackBridge, Optional<TiersAsStackSize.Data>>() {
         public Optional<TiersAsStackSize.Data> method1(ItemStackBridge bridgeextension_41) {
            return TiersAsStackSize.this.method2(bridgeextension_41);
         }
      });
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("bookLevelAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("potionLevelAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("minionLevelAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("itemStarsAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("petCandyAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "heartOfTheMountainLevelAsStack"
         )
         .method4(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "heartOfTheForestLevelAsStack"
         )
         .method4(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("attributeLevelAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("skyblockLevelAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("farmingToolLevelAsStack")
         .method4(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("partyFinderSizeAsStack")
         .method4(true))
      .method31();
   private static final Pattern field21 = Pattern.compile("^.*?_GENERATOR_(?<tier>\\d{1,2})$");
   private static final Pattern field22 = Pattern.compile("^§7Level (\\d+)(§8/\\d+)?$");
   private static final Pattern field23 = Pattern.compile("^§7Your SkyBlock Level: §8\\[§(?<color>.)(?<level>\\d{1,3})§8]$");

   public TiersAsStackSize(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize.class, this::method1);
      this.handle(EventRenderItemStackSize.class, this::method1);
   }

   private void method1(EventRenderItemStackSize highlightimpl121) {
      ItemStackBridge bridgeextension_42 = highlightimpl121.getItem();
      if (bridgeextension_42 != null && !highlightimpl121.method1()) {
         if (this.field8.method7() == SkyblockMenuType.LOADOUTS) {
            List list3 = SkyblockItemUtil.method15(bridgeextension_42);
            if (!list3.isEmpty() && ((String)list3.get(list3.size() - 1)).startsWith("Right-click to edit")) {
               return;
            }
         }

         try {
            Optional optional12 = (Optional)this.field9.get(bridgeextension_42);
            if (optional12.isEmpty()) {
               return;
            }

            int number4 = ((TiersAsStackSize.Data)optional12.get()).method1();
            ChatFormatting horsestatstype85 = ((TiersAsStackSize.Data)optional12.get()).getColor();
            GuiScreenBridge bridge5extension66 = this.mc.bridge$getCurrentScreen();
            if (number4 >= 100
               && bridge5extension66 instanceof GuiContainerBridge
               && highlightimpl121 instanceof com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize data7) {
               float value8 = Ref.method10().bridge$getStringWidth(number4 + "");
               float value9 = ClampUtils.clamp(16.0F / value8, 0.5F, 1.0F);
               MixinHelper_4 mixinhelper_410 = data7.method2();
               mixinhelper_410.push();
               mixinhelper_410.method38(data7.getX() + 17 - value8 * value9, data7.getY() + 10, 301.0F);
               mixinhelper_410.method40(value9, value9);
               mixinhelper_410.method10(Ref.method10(), Component.text(number4, horsestatstype85.getAdventureColor()), 0, 0, -1, true);
               mixinhelper_410.pop();
            } else {
               highlightimpl121.setText(horsestatstype85.toString() + number4);
            }
         } catch (ExecutionException executionexception11) {
            CrashReporter.method5(executionexception11, "SkyblockTiersStackSize");
         }
      }
   }

   private Optional<TiersAsStackSize.Data> method2(ItemStackBridge bridgeextension_41) {
      SkyblockMenuType highlighttype2 = this.field8.method7();
      String text3 = bridgeextension_41.bridge$getDisplayName();
      List list4 = SkyblockItemUtil.method14(bridgeextension_41);
      Optional optional5 = this.method6(list4);
      if (optional5.isPresent()) {
         return optional5;
      }

      Optional optional6 = this.method7(list4);
      if (optional6.isPresent()) {
         return optional6;
      }

      if ((Boolean)this.field18.get() && text3.endsWith("SkyBlock Leveling")) {
         Matcher matcher14 = field23.matcher((CharSequence)list4.get(0));
         if (!matcher14.matches()) {
            return Optional.empty();
         }

         int number16 = NumberUtils.method3(matcher14.group("level"));
         ChatFormatting horsestatstype818 = ChatFormatting.getByCode(matcher14.group("color").charAt(0));
         return Optional.of(new TiersAsStackSize.Data(number16, horsestatstype818));
      } else {
         Optional optional7 = this.method9(text3, list4);
         if (optional7.isPresent()) {
            return optional7;
         }

         if ((Boolean)this.field20.get() && highlighttype2 == SkyblockMenuType.PARTY_FINDER && text3.endsWith("'s Party")) {
            int index15 = 0;

            for (String text19 : list4) {
               if (text19.equals("§8 Empty")) {
                  index15++;
               }
            }

            return Optional.of(new TiersAsStackSize.Data(5 - index15));
         } else {
            CompoundTagComponent mixinhelper_108 = (CompoundTagComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field1);
            if (mixinhelper_108 == null) {
               return Optional.empty();
            }

            CompoundTagBridge bridge_579 = mixinhelper_108.bridge$getData();
            if (bridge_579 == null) {
               return Optional.empty();
            }

            String text10 = bridge_579.bridge$getString("id");
            if ((Boolean)this.field10.get() && "ENCHANTED_BOOK".equals(text10)) {
               CompoundTagBridge bridge_5720 = bridge_579.bridge$getCompoundTag("enchantments");
               if (bridge_5720 == null) {
                  return Optional.empty();
               }

               Set set21 = bridge_5720.bridge$getAllKeys();
               if (set21.size() != 1) {
                  return Optional.empty();
               }

               int number22 = bridge_5720.bridge$getInteger((String)set21.iterator().next());
               return Optional.of(new TiersAsStackSize.Data(number22));
            } else {
               if ((Boolean)this.field11.get() && bridge_579.bridge$getInteger("potion_level") != 0) {
                  return Optional.of(new TiersAsStackSize.Data(bridge_579.bridge$getInteger("potion_level")));
               }

               int number11 = this.method5(text10);
               if (number11 != 0) {
                  return Optional.of(new TiersAsStackSize.Data(number11));
               }

               if (!(Boolean)this.field13.get() || bridge_579.bridge$getInteger("dungeon_item_level") == 0 && bridge_579.bridge$getInteger("upgrade_level") == 0) {
                  if ((Boolean)this.field14.get() && !bridge_579.bridge$getString("petInfo").isEmpty()) {
                     JsonObject json12 = (JsonObject)LunarConstants.field22.fromJson(bridge_579.bridge$getString("petInfo"), JsonObject.class);
                     if (!json12.has("candyUsed")) {
                        return Optional.empty();
                     }

                     int number13 = json12.get("candyUsed").getAsInt();
                     return number13 > 0 ? Optional.of(new TiersAsStackSize.Data(number13)) : Optional.empty();
                  } else {
                     return this.field19.get() && bridge_579.bridge$getInteger("levelable_lvl") != 0
                        ? Optional.of(new TiersAsStackSize.Data(bridge_579.bridge$getInteger("levelable_lvl")))
                        : Optional.empty();
                  }
               } else {
                  return bridge_579.bridge$getInteger("upgrade_level") != 0
                     ? Optional.of(new TiersAsStackSize.Data(bridge_579.bridge$getInteger("upgrade_level")))
                     : Optional.of(new TiersAsStackSize.Data(bridge_579.bridge$getInteger("dungeon_item_level")));
               }
            }
         }
      }
   }

   public String getId() {
      return "TIERS_AS_STACK_SIZE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      List list2 = List.of(
         this.field10,
         this.field11,
         this.field12,
         this.field13,
         this.field14,
         this.field15,
         this.field16,
         this.field17,
         this.field18,
         this.field19,
         this.field20
      );
      lightingextension231.method9(list2.toArray(new ToggleOption[0]));
      list2.forEach(arg1x -> arg1x.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0));
   }

   private int method5(String text1) {
      if (!(Boolean)this.field12.get()) {
         return 0;
      }

      if (text1 != null && !text1.isEmpty()) {
         Matcher matcher2 = field21.matcher(text1);
         if (matcher2.matches()) {
            try {
               return Integer.parseInt(matcher2.group("tier"));
            } catch (NumberFormatException numberformatexception4) {
            }
         }

         return 0;
      } else {
         return 0;
      }
   }

   private Optional<TiersAsStackSize.Data> method6(List<String> list1) {
      return this.method8(this.field15, SkyblockMenuType.HOTM, list1);
   }

   private Optional<TiersAsStackSize.Data> method7(List<String> list1) {
      return this.method8(this.field16, SkyblockMenuType.HOTF, list1);
   }

   private Optional<TiersAsStackSize.Data> method8(ToggleOption lightingextension4431, SkyblockMenuType highlighttype2, List<String> list3) {
      if (!(Boolean)lightingextension4431.get()) {
         return Optional.empty();
      }

      if (this.field8.method7() != highlighttype2) {
         return Optional.empty();
      }

      if (list3.isEmpty()) {
         return Optional.empty();
      }

      String text4 = (String)list3.get(0);
      Matcher matcher5 = field22.matcher(text4);
      if (!matcher5.matches()) {
         return Optional.empty();
      }

      for (String text7 : list3) {
         if (text7.endsWith("ENABLED") || text7.endsWith("UNLOCKED") || text7.endsWith("DISABLED")) {
            return Optional.of(new TiersAsStackSize.Data(Integer.parseInt(matcher5.group(1))));
         }
      }

      return Optional.empty();
   }

   private Optional<TiersAsStackSize.Data> method9(String text1, List<String> list2) {
      if (!(Boolean)this.field17.get()) {
         return Optional.empty();
      }

      if (this.field8.method7() != SkyblockMenuType.ATTRIBUTE_MENU) {
         return Optional.empty();
      }

      if (list2.isEmpty()) {
         return Optional.empty();
      }

      String text3 = (String)list2.get(list2.size() - 1);
      if (!text3.endsWith("Left-Click to open!") && !text3.endsWith("Right-Click to toggle!")) {
         return Optional.empty();
      }

      try {
         String text4 = text1.substring(text1.lastIndexOf(32) + 1);
         return Optional.of(new TiersAsStackSize.Data(RomanNumeralParser.romanToInt(text4)));
      } catch (IllegalArgumentException illegalargumentexception5) {
         return Optional.of(new TiersAsStackSize.Data(0));
      }
   }

   private static class Data {
      private final int field1;
      private final ChatFormatting field2;

      public Data(int number1) {
         this(number1, ChatFormatting.WHITE);
      }

      @Generated
      public Data(int number1, ChatFormatting horsestatstype82) {
         this.field1 = number1;
         this.field2 = horsestatstype82;
      }

      @Generated
      public int method1() {
         return this.field1;
      }

      @Generated
      public ChatFormatting getColor() {
         return this.field2;
      }
   }
}
