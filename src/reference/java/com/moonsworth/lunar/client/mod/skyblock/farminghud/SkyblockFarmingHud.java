package com.moonsworth.lunar.client.mod.skyblock.farminghud;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockGardenUtil;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.SkillXpSource;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.CropType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.nameplate.CropTracker;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileLoadEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.mixin.EventCropPlaced;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockFarmingHud extends AbstractFeature {
   private final SkyblockScoreboardParser field8 = (SkyblockScoreboardParser)this.method63(SkyblockScoreboardParser.class);
   private final ProfileIdListener field9 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private final CropTracker field10 = (CropTracker)this.method63(CropTracker.class);
   private static final long field11 = 10000L;
   private static final NumberFormat field12 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final NumberFormat field13 = NumberFormat.getNumberInstance(Locale.ROOT);
   private static final Pattern field14 = Pattern.compile("^ {2}GARDEN MILESTONE (?<crop>\\w+) [IVXL]+➜(?<level>[IVXL]+)$");
   private static final Pattern field15 = Pattern.compile("^ (?<crop>[A-Za-z ]+) (?<level>\\d{1,2}): (?<progress>[\\d.]+)%$");
   private static final Predicate<SkyblockFarmingHud.FarmingValueSample> field16 = arg0 -> arg0.method2() < Ref.method3().bridge$getSystemTime() - 10000L;
   private static final LongPredicate field17 = arg0 -> arg0 < Ref.method3().bridge$getSystemTime() - 10000L;
   private final ToggleOption field18 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowCropsPerHour"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowFarmingXPPerHour"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowBlocksPerSecond"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowNextMilestone"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "skyblockShowMaxMilestone"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ArrayList<SkyblockFarmingHud.FarmingValueSample> field23 = new ArrayList<>();
   private final ArrayList<SkyblockFarmingHud.FarmingValueSample> field24 = new ArrayList<>();
   private final LongList field25 = new LongArrayList();
   private final HashMap<CropType, Integer> field26 = new HashMap<>();
   private final HashMap<CropType, Long> field27 = new HashMap<>();
   private TextComponent field28;
   private TextComponent field29;

   public SkyblockFarmingHud(Skyblock skyblock1) {
      super(false);
      this.method24(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method24(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockFarmingHud.Data()));
      this.method24(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method24(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.GARDEN));
      this.method5(this::onDisable);
      this.handle(EventSlotUpdate.class, this::method1);
      this.handle(SkillXpUpdateEvent.class, this::method2);
      this.handle(EventCropPlaced.class, this::method3);
      this.handle(EventTabListUpdate.class, this::method4);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond.class, this::method5);
      this.handle(EventTick.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method7);
      this.handle(SkyblockProfileLoadEvent.class, this::method8);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange.class, this::method9);
      field13.setMinimumFractionDigits(2);
      field13.setMaximumFractionDigits(2);
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      if ((Boolean)this.field18.get()) {
         if (this.mc.bridge$getCurrentScreen() == null) {
            long number2 = this.method10(highlightimpl1.method2());
            long number4 = this.method10(highlightimpl1.method3());
            if (number2 != -1L && number4 != -1L) {
               long number6 = number4 >= number2 ? number4 - number2 : number4;
               this.field23.add(new SkyblockFarmingHud.FarmingValueSample(number6, Ref.method3().bridge$getSystemTime()));
               CropType highlighttype38 = this.field10.method5();
               if (highlighttype38 != null) {
                  if (this.field27.containsKey(highlighttype38)) {
                     this.field27.compute(highlighttype38, (arg2x, arg3) -> arg3 + number6);
                  }
               }
            }
         }
      }
   }

   private void method2(SkillXpUpdateEvent highlightimpl41) {
      if (highlightimpl41.method2() == SkillXpSource.ACTION_BAR) {
         if (highlightimpl41.method1() == CoordinatesType.FARMING) {
            this.field24.add(new SkyblockFarmingHud.FarmingValueSample(highlightimpl41.method4(), Ref.method3().bridge$getSystemTime()));
         }
      }
   }

   private void method3(EventCropPlaced rewindhandlers1) {
      if ((Boolean)this.field20.get()) {
         this.field25.add(Ref.method3().bridge$getSystemTime());
      }
   }

   private void method4(EventTabListUpdate highlightimpl31) {
      ScoreboardSection lotusfish32 = (ScoreboardSection)this.field8.method6().get("crop_milestones");
      if (lotusfish32 != null) {
         for (String text4 : lotusfish32.method3()) {
            Matcher matcher5 = field15.matcher(text4);
            if (matcher5.matches()) {
               CropType highlighttype36 = CropType.fromTab(matcher5.group("crop"));
               int index7 = Integer.parseInt(matcher5.group("level"));
               double value8 = Double.parseDouble(matcher5.group("progress")) / 100.0;
               if (!this.field26.containsKey(highlighttype36) || index7 > this.field26.get(highlighttype36)) {
                  this.field26.put(highlighttype36, index7);
               }

               if (index7 >= 46) {
                  break;
               }

               int[] items10 = this.method14().get(highlighttype36);
               if (items10 != null) {
                  long number11 = (long)(items10[index7] * value8);
                  if (!this.field27.containsKey(highlighttype36) || number11 > this.field27.get(highlighttype36)) {
                     this.field27.put(highlighttype36, number11);
                  }
                  break;
               }
            }
         }
      }
   }

   private void method5(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond highlightimpl41) {
      CropType highlighttype32 = this.field10.method5();
      double value3 = this.method14(this.field23);
      long number5 = this.method17(highlighttype32);
      String text7 = null;
      if (highlighttype32 == null) {
         text7 = this.method18("startFarming", new Object[0]);
      } else if (number5 == -1L) {
         text7 = this.method18("loading", new Object[0]);
      } else if (number5 > 0L) {
         if (value3 > 0.0) {
            long number8 = (long)(number5 / value3);
            text7 = TimeFormatting.method1(number8 * 1000L);
         } else {
            text7 = this.method18("startFarming", new Object[0]);
         }
      }

      MixinCore9Extension mixincore9extension15 = (MixinCore9Extension)this.method7(ModTraits.field1);
      if (mixincore9extension15 instanceof HudVisibilityWrapper nameplate9) {
         mixincore9extension15 = nameplate9.method5();
      }

      boolean flag16 = true;
      if (mixincore9extension15 instanceof TypedHudRenderer mixincore810) {
         flag16 = mixincore810.method37() == null || (Boolean)mixincore810.method37().get();
      }

      this.field28 = text7 == null
         ? null
         : TextComponentFactory.builder()
            .method2(this.method18("timeToNext", new Object[0]))
            .method4(text7)
            .method5(NamedTextColor.GOLD)
            .method7(text7.equals(this.method18("startFarming", new Object[0])) ? NamedTextColor.RED : NamedTextColor.GREEN)
            .method12(flag16)
            .build();
      long number17 = this.method18(highlighttype32);
      String text12 = null;
      if (highlighttype32 == null) {
         text12 = this.method18("startFarming", new Object[0]);
      } else if (number17 == -1L) {
         text12 = this.method18("loading", new Object[0]);
      } else if (number17 > 0L) {
         if (value3 > 0.0) {
            long number13 = (long)(number17 / value3);
            text12 = TimeFormatting.method1(number13 * 1000L);
         } else {
            text12 = this.method18("startFarming", new Object[0]);
         }
      }

      this.field29 = text12 == null
         ? null
         : TextComponentFactory.builder()
            .method2(this.method18("timeToMax", new Object[0]))
            .method4(text12)
            .method5(NamedTextColor.GOLD)
            .method7(text12.equals(this.method18("startFarming", new Object[0])) ? NamedTextColor.RED : NamedTextColor.GREEN)
            .method12(flag16)
            .build();
   }

   private void method6(EventTick highlightimpl21) {
      this.field23.removeIf(field16);
      this.field24.removeIf(field16);
      this.field25.removeIf(field17);
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      Matcher matcher2 = field14.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (matcher2.matches()) {
         CropType highlighttype33 = CropType.fromTab(matcher2.group("crop"));
         if (highlighttype33 != null) {
            int number4 = RomanNumeralParser.romanToInt(matcher2.group("level"));
            this.field26.put(highlighttype33, number4);
            this.field27.put(highlighttype33, 0L);
         }
      }
   }

   private void method8(SkyblockProfileLoadEvent data141) {
      this.method13();
   }

   private void onDisable() {
      this.field23.clear();
      this.field24.clear();
      this.field25.clear();
   }

   private void method9(com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange data31) {
      this.field23.clear();
      this.field24.clear();
      this.field25.clear();
   }

   private long method10(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 == null) {
         return -1L;
      } else {
         CompoundTagComponent mixinhelper_102 = (CompoundTagComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field1);
         if (mixinhelper_102 == null) {
            return -1L;
         } else {
            CompoundTagBridge bridge_573 = mixinhelper_102.bridge$getData();
            if (bridge_573 == null) {
               return -1L;
            } else if (bridge_573.bridge$contains("farmed_cultivating", 3)) {
               return bridge_573.bridge$getInteger("farmed_cultivating");
            } else {
               return bridge_573.bridge$contains("levelable_exp", 6) ? (long)bridge_573.bridge$getDouble("levelable_exp") : -1L;
            }
         }
      }
   }

   private void method13() {
      String text1 = this.field9.method5();
      if (text1 != null) {
         SkyBlockGardenUtil.getGardenAsync(text1).thenAcceptAsync(arg1x -> {
            if (arg1x != null) {
               arg1x.garden().resourcesCollected().forEach((arg1xx, arg2) -> {
                  CropType highlighttype33 = CropType.fromApi(arg1xx);
                  if (highlighttype33 != null && arg2 != null) {
                     int[] items4 = this.method14().get(highlighttype33);
                     if (items4 != null) {
                        SkyblockFarmingHud.FarmingLevelProgress data25 = this.method16(arg2, items4);
                        this.field26.put(highlighttype33, data25.level());
                        this.field27.put(highlighttype33, data25.method1());
                     }
                  }
               });
            }
         }, BackgroundExecutor.method8());
      }
   }

   public static HashMap<CropType, int[]> method12(JsonObject json0) {
      if (json0 == null) {
         return null;
      }

      HashMap map1 = new HashMap();
      JsonObject json2 = json0.getAsJsonObject("milestones");

      for (String text4 : json2.keySet()) {
         CropType highlighttype35 = CropType.fromLocal(text4);
         if (highlighttype35 != null) {
            JsonArray array6 = json2.getAsJsonArray(text4);
            int[] items7 = new int[array6.size()];

            for (int index8 = 0; index8 < array6.size(); index8++) {
               items7[index8] = array6.get(index8).getAsInt();
            }

            map1.put(highlighttype35, items7);
         }
      }

      return map1;
   }

   private HashMap<CropType, int[]> method14() {
      return Ref.method4().method40().method82().method15().method18();
   }

   private double method14(ArrayList<SkyblockFarmingHud.FarmingValueSample> list1) {
      double value2 = 0.0;

      for (SkyblockFarmingHud.FarmingValueSample data35 : list1) {
         value2 += data35.method1();
      }

      return value2 / 10.0;
   }

   private double method15(LongList longlist1) {
      return longlist1.size() / 10.0;
   }

   private SkyblockFarmingHud.FarmingLevelProgress method16(long number1, int[] items3) {
      int number4 = 0;

      for (int index5 = 0; index5 < items3.length; index5++) {
         number4 += items3[index5];
         if (number1 < number4) {
            return new SkyblockFarmingHud.FarmingLevelProgress(index5, number1 - (number4 - items3[index5]));
         }
      }

      return new SkyblockFarmingHud.FarmingLevelProgress(items3.length, number1 - number4);
   }

   private long method17(CropType highlighttype31) {
      if (!this.field26.containsKey(highlighttype31)) {
         return -1L;
      }

      int index2 = this.field26.get(highlighttype31);
      if (index2 >= 46) {
         return 0L;
      }

      int[] items3 = this.method14().get(highlighttype31);
      if (items3 == null) {
         return -1L;
      }

      int number4 = items3[index2];
      return number4 - this.field27.get(highlighttype31);
   }

   private long method18(CropType highlighttype31) {
      if (!this.field26.containsKey(highlighttype31)) {
         return -1L;
      }

      int number2 = this.field26.get(highlighttype31);
      if (number2 >= 46) {
         return 0L;
      }

      int number3 = 0;
      long number4 = 0L;
      int[] items6 = this.method14().get(highlighttype31);
      if (items6 == null) {
         return -1L;
      }

      for (int index7 = 0; index7 < items6.length; index7++) {
         number3 += items6[index7];
         if (index7 <= number2) {
            number4 += items6[index7];
         }
      }

      number4 += this.field27.get(highlighttype31);
      return number3 - number4;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field18, this.field19, this.field20, this.field21, this.field22})
      );
   }

   public String getId() {
      return "SKYBLOCK_FARMING_HUD";
   }

   public void method3(boolean flag1) {
      if (flag1 && ((ChildModBinding)this.method7(ModTraits.field16)).method1().isEnabled() && this.field26.isEmpty()) {
         this.method13();
      }
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 66, 100, 100, 140, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? this.method3(
               3800000.0,
               420000.0,
               19.5,
               this.method4(SkyblockFarmingHud.this.method18("timeToNext", new Object[0]), "6m 45s"),
               this.method4(SkyblockFarmingHud.this.method18("timeToMax", new Object[0]), "1d 20h 15m 30s")
            )
            : this.method3(
               SkyblockFarmingHud.this.method14(SkyblockFarmingHud.this.field23) * 3600.0,
               SkyblockFarmingHud.this.method14(SkyblockFarmingHud.this.field24) * 3600.0,
               Math.min(SkyblockFarmingHud.this.method15(SkyblockFarmingHud.this.field25), 20.0),
               SkyblockFarmingHud.this.field28,
               SkyblockFarmingHud.this.field29
            );
      }

      private List<HudLine> method3(double value1, double value3, double value5, @Nullable TextComponent text7, @Nullable TextComponent text8) {
         ArrayList list9 = new ArrayList();
         if ((Boolean)SkyblockFarmingHud.this.field18.get()) {
            String text10 = SkyblockFarmingHud.field12.format(value1);
            list9.add(
               new HudLine(
                  Bridge.method28().method44(),
                  TextComponentFactory.builder()
                     .method2(SkyblockFarmingHud.this.method18("cropsPerHour", new Object[0]))
                     .method4(text10)
                     .method5(NamedTextColor.GOLD)
                     .method7(text10.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFarmingHud.this.field19.get()) {
            String text12 = SkyblockFarmingHud.field12.format(value3);
            list9.add(
               new HudLine(
                  Bridge.method28().method45(),
                  TextComponentFactory.builder()
                     .method2(SkyblockFarmingHud.this.method18("farmingXpPerHour", new Object[0]))
                     .method4(text12)
                     .method5(NamedTextColor.GOLD)
                     .method7(text12.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFarmingHud.this.field20.get()) {
            double value13 = value5;
            list9.add(
               new HudLine(
                  Bridge.method28().method46(),
                  TextComponentFactory.builder()
                     .method2(SkyblockFarmingHud.this.method18("blocksPerSecond", new Object[0]))
                     .method4(SkyblockFarmingHud.field13.format(value13))
                     .method5(NamedTextColor.GOLD)
                     .method7(value13 == 0.0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFarmingHud.this.field21.get() && text7 != null) {
            list9.add(new HudLine(Bridge.method28().method47(), text7));
         }

         if ((Boolean)SkyblockFarmingHud.this.field22.get() && text8 != null) {
            list9.add(new HudLine(Bridge.method28().method48(), text8));
         }

         return list9;
      }

      private TextComponent method4(String text1, String text2) {
         return TextComponentFactory.builder()
            .method2(text1)
            .method4(text2)
            .method5(NamedTextColor.GOLD)
            .method7(NamedTextColor.GREEN)
            .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
            .build();
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }

   private class FarmingLevelProgress {
      private final int field1;
      private final long field2;

      private FarmingLevelProgress(int number1, long number2) {
         this.field1 = number1;
         this.field2 = number2;
      }

      public int level() {
         return this.field1;
      }

      public long method1() {
         return this.field2;
      }
   }

   private class FarmingValueSample {
      private final double field1;
      private final long field2;

      private FarmingValueSample(double value1, long number3) {
         this.field1 = value1;
         this.field2 = number3;
      }

      public double method1() {
         return this.field1;
      }

      public long method2() {
         return this.field2;
      }
   }
}
