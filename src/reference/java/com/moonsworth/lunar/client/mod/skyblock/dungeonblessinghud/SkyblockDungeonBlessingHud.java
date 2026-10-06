package com.moonsworth.lunar.client.mod.skyblock.dungeonblessinghud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockDungeonBlessingHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^Blessing of (?<type>Life|Power|Stone|Wisdom|Time) (?<tier>[IVX]+)$");
   private final ColorOption field9 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lifeBlessingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16733696))
      .method31();
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "powerBlessingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5636096))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "stoneBlessingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "wisdomBlessingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "timeBlessingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final HashMap<String, Integer> field14 = new HashMap<>();

   public SkyblockDungeonBlessingHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockDungeonBlessingHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method1(this::onDisable);
      this.handle(EventSecond.class, this::method1);
      this.handle(EventWorldChange.class, this::method2);
   }

   private void onDisable() {
      this.field14.clear();
   }

   private void method1(EventSecond highlightimpl41) {
      GuiIngameBridge bridge5extension92 = Ref.method3().bridge$getGuiIngame();
      if (bridge5extension92 != null) {
         Bridge2_42 bridge2_423 = Ref.method3().bridge$getGuiIngame().bridge$getTabList().bridge$getFooter();
         String text4 = TextBridge.getTextContent(TextBridge.asAdventure(bridge2_423));

         for (String text8 : text4.split("\n")) {
            Matcher matcher9 = field8.matcher(text8);
            if (matcher9.matches()) {
               String text10 = matcher9.group("type");
               int number11 = RomanNumeralParser.romanToInt(matcher9.group("tier"));
               this.field14.put(text10, number11);
            }
         }
      }
   }

   private void method2(EventWorldChange data31) {
      this.field14.clear();
   }

   private TextColor method3(String text1) {
      return TextColor.color(switch (text1) {
         case "Life" -> this.field9.method14(0.0F);
         case "Power" -> this.field10.method14(0.0F);
         case "Stone" -> this.field11.method14(0.0F);
         case "Wisdom" -> this.field12.method14(0.0F);
         case "Time" -> this.field13.method14(0.0F);
         default -> -1;
      });
   }

   private ItemBridge method4(String text1) {
      ItemsBridge bridge2_212 = Bridge.method28();

      return switch (text1) {
         case "Life" -> bridge2_212.method65();
         case "Power" -> bridge2_212.method16();
         case "Stone" -> bridge2_212.method13();
         case "Wisdom" -> bridge2_212.method36();
         case "Time" -> bridge2_212.method20();
         default -> null;
      };
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_BLESSING_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> arg1x.method9(new ClientOption[]{this.field9, this.field10, this.field11, this.field12, this.field13})
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 100, 200, 50, 120, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            LinkedHashMap map2 = new LinkedHashMap();
            map2.put("Power", 15);
            map2.put("Life", 10);
            map2.put("Wisdom", 6);
            map2.put("Time", 5);
            map2.put("Stone", 5);
            return this.method3(map2);
         } else {
            return this.method3(SkyblockDungeonBlessingHud.this.field14);
         }
      }

      private List<HudLine> method3(Map<String, Integer> map1) {
         ArrayList list2 = new ArrayList();

         for (Entry entry4 : map1.entrySet()) {
            String text5 = (String)entry4.getKey();
            int number6 = (Integer)entry4.getValue();
            TextColor textcolor7 = SkyblockDungeonBlessingHud.this.method3(text5);
            ItemBridge bridge6_48 = SkyblockDungeonBlessingHud.this.method4(text5);
            list2.add(
               new HudLine(
                  bridge6_48,
                  TextComponentFactory.builder().method2(text5).method4(number6 + "").method5(textcolor7).method7(number6 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN).build()
               )
            );
         }

         return list2;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
