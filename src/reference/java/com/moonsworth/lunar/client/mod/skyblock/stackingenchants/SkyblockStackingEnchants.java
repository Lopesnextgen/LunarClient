package com.moonsworth.lunar.client.mod.skyblock.stackingenchants;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockStackingEnchants extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final List<SkyblockStackingEnchants.Data> field9 = List.of(
      new SkyblockStackingEnchants.Data("Expertise", "expertise_kills", SkyblockStackingEnchants.Type.INTEGER),
      new SkyblockStackingEnchants.Data("Compact", "compact_blocks", SkyblockStackingEnchants.Type.INTEGER),
      new SkyblockStackingEnchants.Data("Cultivating", "farmed_cultivating", SkyblockStackingEnchants.Type.LONG),
      new SkyblockStackingEnchants.Data("Champion", "champion_combat_xp", SkyblockStackingEnchants.Type.DOUBLE),
      new SkyblockStackingEnchants.Data("Hecatomb", "hecatomb_s_runs", SkyblockStackingEnchants.Type.INTEGER),
      new SkyblockStackingEnchants.Data("Toxophilite", "toxophilite_combat_xp", SkyblockStackingEnchants.Type.DOUBLE),
      new SkyblockStackingEnchants.Data("Absorb", "absorb_logs_chopped", SkyblockStackingEnchants.Type.INTEGER)
   );
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "progressColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();

   public SkyblockStackingEnchants(Skyblock skyblock1) {
      super(true);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderTooltipPre.class, this::method1);
   }

   private void method1(EventRenderTooltipPre data21) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.method1().orElse(null);
      CompoundTagBridge bridge_573 = SkyblockItemUtil.method1(bridgeextension_42);
      if (bridge_573 != null) {
         List list4 = data21.method3();

         for (SkyblockStackingEnchants.Data data6 : field9) {
            if (data6.isEnabled()) {
               long number7 = this.method2(bridge_573, data6);
               if (number7 != -1L) {
                  TextComponent text9 = Component.text(data6.getName() + ": ", TextColor.color(this.field10.method14(0.0F)));
                  text9 = (TextComponent)text9.append(Component.text(field8.format(number7), TextColor.color(this.field11.method14(0.0F))));
                  int number10 = this.method3(number7, data6);
                  if (number10 != -1) {
                     text9 = (TextComponent)text9.append(Component.text(" / " + NumberUtils.method10(number10), TextColor.color(this.field10.method14(0.0F))));
                  }

                  list4.add(TextComponentFactory.clickable(text9));
                  data21.method2(list4);
                  return;
               }
            }
         }
      }
   }

   private long method2(CompoundTagBridge bridge_571, SkyblockStackingEnchants.Data data2) {
      String text3 = data2.getTag();
      switch (data2.method2()) {
         case INTEGER:
            return bridge_571.bridge$contains(text3, 3) ? bridge_571.bridge$getInteger(text3) : -1L;
         case LONG:
            return bridge_571.bridge$contains(text3, 4) ? bridge_571.bridge$getLong(text3) : -1L;
         case DOUBLE:
            return bridge_571.bridge$contains(text3, 6) ? (long)bridge_571.bridge$getDouble(text3) : -1L;
         default:
            return -1L;
      }
   }

   private int method3(long number1, SkyblockStackingEnchants.Data data3) {
      Object2ObjectOpenHashMap object2objectopenhashmap4 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method34();
      if (object2objectopenhashmap4 == null) {
         return -1;
      }

      IntArrayList intarraylist5 = (IntArrayList)object2objectopenhashmap4.get(data3.getId());
      IntListIterator intlistiterator6 = intarraylist5.iterator();

      while (intlistiterator6.hasNext()) {
         int number7 = (Integer)intlistiterator6.next();
         if (number7 > number1) {
            return number7;
         }
      }

      return -1;
   }

   public String getId() {
      return "SKYBLOCK_STACKING_ENCHANTS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg0 -> {
            for (SkyblockStackingEnchants.Data data2 : field9) {
               String text3 = data2.method1();
               ToggleOption lightingextension4434 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(text3)
                     .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
                  .method31();
               data2.method4(lightingextension4434::get);
               arg0.method9(new ClientOption[]{lightingextension4434});
            }
         }
      );
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field10, this.field11})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private static class Data {
      private final String field1;
      private final String field2;
      private final SkyblockStackingEnchants.Type field3;
      private Supplier<Boolean> field4 = () -> true;

      public boolean isEnabled() {
         return this.field4.get();
      }

      public String getId() {
         return this.getName().toUpperCase(Locale.ROOT).replace(' ', '_');
      }

      public String method1() {
         return this.getName().toLowerCase(Locale.ROOT) + "Progress";
      }

      @Generated
      public Data(String text1, String text2, SkyblockStackingEnchants.Type type3) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = type3;
      }

      @Generated
      public String getName() {
         return this.field1;
      }

      @Generated
      public String getTag() {
         return this.field2;
      }

      @Generated
      public SkyblockStackingEnchants.Type method2() {
         return this.field3;
      }

      @Generated
      public Supplier<Boolean> method3() {
         return this.field4;
      }

      @Generated
      public void method4(Supplier<Boolean> supplier1) {
         this.field4 = supplier1;
      }
   }

   private enum Type {
      INTEGER,
      LONG,
      DOUBLE;

      Type() {
      }
   }
}
