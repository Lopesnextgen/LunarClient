package com.moonsworth.lunar.client.mod.skyblock.tabwidget;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.text.TextUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyBlockTabWidgetHud extends AbstractFeature {
   private final SkyblockScoreboardParser field8 = (SkyblockScoreboardParser)this.method63(SkyblockScoreboardParser.class);
   private final Lotusfish field9;
   private final MultiSelectOption field10 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "skyBlockTabWidgetHuds"
         )
         .method2(this.method13()))
      .method3(SkyblockIsland.ids())
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTitle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   private SkyBlockTabWidgetHud(Skyblock skyblock1, Lotusfish lotusfish2) {
      super(true);
      this.field9 = lotusfish2;
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyBlockTabWidgetHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.TAB_WIDGETS));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
   }

   public static SkyBlockTabWidgetHud method1(Skyblock skyblock0, final Lotusfish lotusfish1) {
      final String text2 = "SKYBLOCK_TAB_WIDGET_" + TextUtils.toUpperSnakeCase(lotusfish1.id()) + "_CHILD";
      return new SkyBlockTabWidgetHud(skyblock0, lotusfish1) {
         @Override
         protected Set<String> method13() {
            HashSet set1x = new HashSet();
            Set set2x = lotusfish1.exclusivelyOn();
            if (set2x != null && !set2x.isEmpty()) {
               for (SkyblockIsland gui2extension34 : set2x) {
                  set1x.add(gui2extension34.id());
               }
            } else {
               set1x.addAll(SkyblockIsland.ids());
            }

            return set1x;
         }

         @Override
         public String getId() {
            return text2;
         }
      };
   }

   public String getId() {
      throw new IllegalStateException("SkyBlockTabWidgetHud must be created using SkyBlockTabWidgetHud.create()!");
   }

   protected Set<String> method13() {
      throw new IllegalStateException("SkyBlockTabWidgetHud must be created using SkyBlockTabWidgetHud.create()!");
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field4}).method4(() -> this.field9.title() + " HUD").method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field10, this.field11})
      );
   }

   private class Data extends TypedHudRenderer<List<Component>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 100, 1000, 10, 100, 1000);
      }

      @Nullable
      public List<Component> method2(boolean flag1) {
         ScoreboardSection lotusfish32 = (ScoreboardSection)SkyBlockTabWidgetHud.this.field8.method6().get(SkyBlockTabWidgetHud.this.field9.id());
         if (flag1) {
            List list3 = lotusfish32 == null ? List.of() : lotusfish32.method2();
            return list3.isEmpty() ? List.of(this.method24()) : this.method3(list3);
         } else {
            return lotusfish32 == null
               ? List.of(
                  Component.text("No " + SkyBlockTabWidgetHud.this.field9.id() + " widget found!").color(NamedTextColor.RED),
                  Component.text("Fix this using /widgets!").color(NamedTextColor.GREEN)
               )
               : this.method3(lotusfish32.method2());
         }
      }

      private List<Component> method3(List<Component> list1) {
         return SkyBlockTabWidgetHud.this.field11.get() ? list1 : list1.subList(1, list1.size());
      }

      private Component method24() {
         return ((TextComponent)Component.text(SkyBlockTabWidgetHud.this.field9.title()).color(NamedTextColor.GREEN)).decorate(TextDecoration.BOLD);
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : ((Set)SkyBlockTabWidgetHud.this.field10.get()).contains(IslandUtils.getIsland().id());
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : ((Set)SkyBlockTabWidgetHud.this.field10.get()).contains(IslandUtils.getIsland().id());
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
   }
}
