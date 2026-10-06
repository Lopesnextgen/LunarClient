package com.moonsworth.lunar.client.mod.skyblock.trophyfishexchangerate;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
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
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.SackCountListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockTrophyFishExchangeRate extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "skyblock/hud/magmafish.png");
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "skyblock/hud/lotus.png");
   private static final Pattern field11 = Pattern.compile("^Stored: (?<amount>[\\d,]+)/.+$");
   private final SackCountListener field12 = (SackCountListener)this.method8(SackCountListener.class);
   private final ToggleOption field13 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showHud"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showMagmafish"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showLotus"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showTotal"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showInTooltip"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field18 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field19 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "amountColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private int field20;
   private int field21;
   private int field22;
   private int field23;

   public SkyblockTrophyFishExchangeRate(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockTrophyFishExchangeRate.ExchangeRateHud()));
      this.method5(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSecond.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method2);
   }

   private void method1(EventSecond highlightimpl41) {
      this.field20 = 0;
      this.field21 = 0;
      this.field22 = 0;
      this.field23 = 0;
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         for (ItemStackBridge bridgeextension_44 : bridge5extension_52.bridge$getInventory().bridge$getMainInventory()) {
            SkyblockTrophyFishExchangeRate.Data data5 = this.method3(bridgeextension_44);
            if (data5 != null) {
               switch (data5.method1()) {
                  case FISH:
                     this.field20 = this.field20 + data5.amount();
                     break;
                  case FROG:
                     this.field22 = this.field22 + data5.amount();
               }
            }
         }

         ObjectIterator objectiterator6 = this.field12.method6().object2IntEntrySet().iterator();

         while (objectiterator6.hasNext()) {
            Entry entry7 = (Entry)objectiterator6.next();
            SkyblockTrophyFishExchangeRate.Data data8 = this.method4((String)entry7.getKey(), entry7.getIntValue());
            if (data8 != null) {
               switch (data8.method1()) {
                  case FISH:
                     this.field21 = this.field21 + data8.amount();
                     break;
                  case FROG:
                     this.field23 = this.field23 + data8.amount();
               }
            }
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      if ((Boolean)this.field17.get()) {
         SkyblockTrophyFishExchangeRate.Data data2 = this.method3((ItemStackBridge)data21.method1().orElse(null));
         if (data2 != null && data2.amount() > 0) {
            List list3 = data21.method3();
            list3.add(
               TextComponentFactory.clickable(
                  TextComponentFactory.builder()
                     .method2(data2.method1().getItem())
                     .method6(this.field18.method14(0.0F))
                     .method4(field8.format(data2.amount()))
                     .method8(this.field19.method14(0.0F))
                     .build()
               )
            );
            data21.method2(list3);
         }
      }
   }

   @Nullable
   private SkyblockTrophyFishExchangeRate.Data method3(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty()) {
         for (SkyblockTrophyFishExchangeRate.Type type5 : SkyblockTrophyFishExchangeRate.Type.values()) {
            Object2IntOpenHashMap object2intopenhashmap6 = this.method5(type5);
            if (object2intopenhashmap6 != null) {
               String text7 = SkyblockItemUtil.method2(bridgeextension_41);
               int number8 = object2intopenhashmap6.getOrDefault(text7, -1);
               if (number8 >= 0) {
                  int number9 = bridgeextension_41.bridge$getStackSize();

                  for (String text11 : SkyblockItemUtil.method15(bridgeextension_41)) {
                     Matcher matcher12 = field11.matcher(text11);
                     if (matcher12.matches()) {
                        number9 = NumberUtils.method3(matcher12.group("amount").replace(",", ""));
                        break;
                     }
                  }

                  return new SkyblockTrophyFishExchangeRate.Data(number8 * number9, type5);
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Nullable
   private SkyblockTrophyFishExchangeRate.Data method4(String text1, int number2) {
      for (SkyblockTrophyFishExchangeRate.Type type6 : SkyblockTrophyFishExchangeRate.Type.values()) {
         Object2IntOpenHashMap object2intopenhashmap7 = this.method5(type6);
         if (object2intopenhashmap7 != null) {
            int number8 = object2intopenhashmap7.getOrDefault(text1, -1);
            if (number8 >= 0) {
               return new SkyblockTrophyFishExchangeRate.Data(number8 * number2, type6);
            }
         }
      }

      return null;
   }

   private Object2IntOpenHashMap<String> method5(SkyblockTrophyFishExchangeRate.Type type1) {
      Module module2 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15();

      return switch (type1) {
         case FISH -> module2.method35();
         case FROG -> module2.method36();
      };
   }

   public String getId() {
      return "SKYBLOCK_TROPHY_FISH_EXCHANGE_RATE";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.HUD,
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14, this.field15, this.field16})
         )
      );
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field17}));
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field18, this.field19})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data {
      private final int field1;
      private final SkyblockTrophyFishExchangeRate.Type field2;

      private Data(int number1, SkyblockTrophyFishExchangeRate.Type type2) {
         this.field1 = number1;
         this.field2 = type2;
      }

      public int amount() {
         return this.field1;
      }

      public SkyblockTrophyFishExchangeRate.Type method1() {
         return this.field2;
      }
   }

   private class ExchangeRateHud extends TypedHudRenderer<List<HudLine>> {
      public ExchangeRateHud() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(30, 60, 120, 100, 150, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? this.method3(1284, 9375, 6, 41)
            : this.method3(
               SkyblockTrophyFishExchangeRate.this.field20,
               SkyblockTrophyFishExchangeRate.this.field21,
               SkyblockTrophyFishExchangeRate.this.field22,
               SkyblockTrophyFishExchangeRate.this.field23
            );
      }

      private List<HudLine> method3(int number1, int number2, int number3, int number4) {
         ArrayList list5 = new ArrayList();
         if ((Boolean)SkyblockTrophyFishExchangeRate.this.field14.get()) {
            if (number1 > 0) {
               list5.add(
                  new HudLine(
                     SkyblockTrophyFishExchangeRate.field9,
                     TextComponentFactory.builder()
                        .method2(SkyblockTrophyFishExchangeRate.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("inventory", new Object[0]))
                        .method6(SkyblockTrophyFishExchangeRate.this.field18.method14(0.0F))
                        .method4(SkyblockTrophyFishExchangeRate.field8.format(number1))
                        .method8(SkyblockTrophyFishExchangeRate.this.field19.method14(0.0F))
                        .build()
                  )
               );
            }

            if (number2 > 0) {
               list5.add(
                  new HudLine(
                     SkyblockTrophyFishExchangeRate.field9,
                     TextComponentFactory.builder()
                        .method2(SkyblockTrophyFishExchangeRate.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("sacks", new Object[0]))
                        .method6(SkyblockTrophyFishExchangeRate.this.field18.method14(0.0F))
                        .method4(SkyblockTrophyFishExchangeRate.field8.format(number2))
                        .method8(SkyblockTrophyFishExchangeRate.this.field19.method14(0.0F))
                        .build()
                  )
               );
            }

            if ((Boolean)SkyblockTrophyFishExchangeRate.this.field16.get() && number1 > 0 && number2 > 0) {
               list5.add(
                  new HudLine(
                     SkyblockTrophyFishExchangeRate.field9,
                     TextComponentFactory.builder()
                        .method2(SkyblockTrophyFishExchangeRate.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("total", new Object[0]))
                        .method6(SkyblockTrophyFishExchangeRate.this.field18.method14(0.0F))
                        .method4(SkyblockTrophyFishExchangeRate.field8.format(number1 + number2))
                        .method8(SkyblockTrophyFishExchangeRate.this.field19.method14(0.0F))
                        .build()
                  )
               );
            }
         }

         if ((Boolean)SkyblockTrophyFishExchangeRate.this.field15.get()) {
            if (number3 > 0) {
               list5.add(
                  new HudLine(
                     SkyblockTrophyFishExchangeRate.field10,
                     TextComponentFactory.builder()
                        .method2(SkyblockTrophyFishExchangeRate.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("inventory", new Object[0]))
                        .method6(SkyblockTrophyFishExchangeRate.this.field18.method14(0.0F))
                        .method4(SkyblockTrophyFishExchangeRate.field8.format(number3))
                        .method8(SkyblockTrophyFishExchangeRate.this.field19.method14(0.0F))
                        .build()
                  )
               );
            }

            if (number4 > 0) {
               list5.add(
                  new HudLine(
                     SkyblockTrophyFishExchangeRate.field10,
                     TextComponentFactory.builder()
                        .method2(SkyblockTrophyFishExchangeRate.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("sacks", new Object[0]))
                        .method6(SkyblockTrophyFishExchangeRate.this.field18.method14(0.0F))
                        .method4(SkyblockTrophyFishExchangeRate.field8.format(number4))
                        .method8(SkyblockTrophyFishExchangeRate.this.field19.method14(0.0F))
                        .build()
                  )
               );
            }

            if ((Boolean)SkyblockTrophyFishExchangeRate.this.field16.get() && number3 > 0 && number2 > 0) {
               list5.add(
                  new HudLine(
                     SkyblockTrophyFishExchangeRate.field10,
                     TextComponentFactory.builder()
                        .method2(SkyblockTrophyFishExchangeRate.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("total", new Object[0]))
                        .method6(SkyblockTrophyFishExchangeRate.this.field18.method14(0.0F))
                        .method4(SkyblockTrophyFishExchangeRate.field8.format(number3 + number4))
                        .method8(SkyblockTrophyFishExchangeRate.this.field19.method14(0.0F))
                        .build()
                  )
               );
            }
         }

         return list5.isEmpty() ? null : list5;
      }

      public boolean method4(boolean flag1) {
         return (Boolean)SkyblockTrophyFishExchangeRate.this.field13.get() && super.method4(flag1);
      }

      public boolean method30() {
         return (Boolean)SkyblockTrophyFishExchangeRate.this.field13.get();
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
   }

   private enum Type {
      FISH("Magmafish"),
      FROG("Lotus");

      private final String item;

      @Generated
      Type(String text3) {
         this.item = text3;
      }

      @Generated
      public String getItem() {
         return this.item;
      }
   }
}
