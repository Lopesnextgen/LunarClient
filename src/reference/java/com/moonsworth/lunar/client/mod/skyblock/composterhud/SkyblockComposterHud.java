package com.moonsworth.lunar.client.mod.skyblock.composterhud;

import com.google.common.collect.UnmodifiableIterator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
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
import com.moonsworth.lunar.client.framework.listener.TabListListener;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockComposterHud extends AbstractFeature {
   private final TabListListener field8 = (TabListListener)this.method63(TabListListener.class);
   private static final Pattern field9 = Pattern.compile("Stored Compost: (.+)");
   private static final Pattern field10 = Pattern.compile("Time Left: (.+)");
   private static final Pattern field11 = Pattern.compile("Organic Matter: (.+)");
   private static final Pattern field12 = Pattern.compile("Fuel: (.+)");
   private static final ResourceLocationBridge field13 = ResourceLocationBridge.create("lunar", "skyblock/hud/compost.png");
   private static final ResourceLocationBridge field14 = ResourceLocationBridge.create("lunar", "skyblock/hud/oil_barrel.png");
   private static final ResourceLocationBridge field15 = ResourceLocationBridge.create("lunar", "skyblock/hud/box_of_seeds.png");
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("fuelAmount").method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("organicMatter").method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("composterTimeRemaining").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("storedCompostAmount").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private String field20 = "";
   private String field21 = "";
   private String field22 = "";
   private String field23 = "";

   public SkyblockComposterHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockComposterHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTabListUpdate.class, this::method1);
   }

   private void method1(EventTabListUpdate highlightimpl31) {
      UnmodifiableIterator unmodifiableiterator2 = this.field8.method6().iterator();

      while (unmodifiableiterator2.hasNext()) {
         String text3 = (String)unmodifiableiterator2.next();
         Matcher matcher4 = field9.matcher(text3);
         if (matcher4.find()) {
            this.field20 = matcher4.group(1);
         } else {
            matcher4 = field10.matcher(text3);
            if (matcher4.find()) {
               this.field21 = matcher4.group(1);
            } else {
               matcher4 = field12.matcher(text3);
               if (matcher4.find()) {
                  this.field22 = matcher4.group(1);
               } else {
                  matcher4 = field11.matcher(text3);
                  if (matcher4.find()) {
                     this.field23 = matcher4.group(1);
                  }
               }
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_COMPOSTER_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field18, this.field19, this.field16, this.field17})
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 30, 60, 50, 150, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            List list2 = this.method3("3h 22m", "1,204", "38,500", "64,200");
            return list2 != null ? list2 : List.of(new HudLine(Component.text("Composter Info")));
         } else {
            return IslandUtils.getIsland() != SkyblockIsland.GARDEN
               ? null
               : this.method3(
                  SkyblockComposterHud.this.field21, SkyblockComposterHud.this.field20, SkyblockComposterHud.this.field22, SkyblockComposterHud.this.field23
               );
         }
      }

      @Nullable
      private List<HudLine> method3(String text1, String text2, String text3, String text4) {
         ArrayList list5 = new ArrayList();
         if ((Boolean)SkyblockComposterHud.this.field18.get()) {
            list5.add(
               new HudLine(
                  Bridge.method28().method20(),
                  TextComponentFactory.builder()
                     .method2("Time Left")
                     .method4(text1)
                     .method5(NamedTextColor.GOLD)
                     .method7(text1.equals("INACTIVE") ? NamedTextColor.RED : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockComposterHud.this.field19.get()) {
            list5.add(
               new HudLine(
                  SkyblockComposterHud.field13,
                  TextComponentFactory.builder()
                     .method2("Stored")
                     .method4(text2)
                     .method5(NamedTextColor.GOLD)
                     .method7(text2.equals("0") ? NamedTextColor.GRAY : NamedTextColor.DARK_GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockComposterHud.this.field16.get()) {
            list5.add(
               new HudLine(
                  SkyblockComposterHud.field14,
                  TextComponentFactory.builder()
                     .method2("Fuel")
                     .method4(text3)
                     .method5(NamedTextColor.GOLD)
                     .method7(text3.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockComposterHud.this.field17.get()) {
            list5.add(
               new HudLine(
                  SkyblockComposterHud.field15,
                  TextComponentFactory.builder()
                     .method2("Organic Matter")
                     .method4(text4)
                     .method5(NamedTextColor.GOLD)
                     .method7(text4.equals("0") ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if (list5.isEmpty()) {
            return null;
         }

         list5.add(0, new HudLine(((TextComponent)Component.text("Composter Info").color(NamedTextColor.AQUA)).decorate(TextDecoration.BOLD)));
         return list5;
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : IslandUtils.getIsland() == SkyblockIsland.GARDEN;
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : IslandUtils.getIsland() == SkyblockIsland.GARDEN;
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
}
