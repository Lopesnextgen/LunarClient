package com.moonsworth.lunar.client.mod.skyblock.deathcounterhud;

import com.google.common.collect.UnmodifiableIterator;
import com.moonsworth.lunar.bridge.Bridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockElectionListener;
import com.moonsworth.lunar.client.framework.listener.TabListListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockDeathCounterHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^Team Deaths: (?<deaths>\\d+)$");
   private final DungeonFloorListener field9 = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final SkyblockElectionListener field10 = (SkyblockElectionListener)this.method63(SkyblockElectionListener.class);
   private final TabListListener field11 = (TabListListener)this.method63(TabListListener.class);
   private int deaths;

   public SkyblockDeathCounterHud(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockDeathCounterHud.Data()));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method1(this::onDisable);
      this.handle(EventSecond.class, this::method1);
      this.handle(EventWorldChange.class, this::method2);
   }

   private void method1(EventSecond highlightimpl41) {
      UnmodifiableIterator unmodifiableiterator2 = this.field11.method6().iterator();

      while (unmodifiableiterator2.hasNext()) {
         String text3 = (String)unmodifiableiterator2.next();
         Matcher matcher4 = field8.matcher(text3);
         if (matcher4.matches()) {
            this.deaths = Integer.parseInt(matcher4.group("deaths"));
            return;
         }
      }
   }

   private void method2(EventWorldChange data31) {
      this.deaths = 0;
   }

   private void onDisable() {
      this.deaths = 0;
   }

   public String getId() {
      return "SKYBLOCK_DEATH_COUNTER_HUD";
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 50, 80, 150);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (IslandUtils.getIsland() != SkyblockIsland.DUNGEON) {
            return null;
         }

         if (flag1) {
            return new HudLine(
               Bridge.method28().method6(),
               TextComponentFactory.builder()
                  .method2("Deaths")
                  .method4("2")
                  .method5(NamedTextColor.GRAY)
                  .method7(NamedTextColor.YELLOW)
                  .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                  .build()
            );
         }

         int number2 = SkyblockDeathCounterHud.this.field9.method6().getNumber() >= 6 ? 2 : 0;
         int number3 = SkyblockDeathCounterHud.this.field10.method2("EZPZ") ? 10 : 0;
         int number4 = 7 + number2 + number3;
         NamedTextColor namedtextcolor5;
         if (SkyblockDeathCounterHud.this.deaths <= 0) {
            namedtextcolor5 = NamedTextColor.GREEN;
         } else if (SkyblockDeathCounterHud.this.deaths <= number4 / 2) {
            namedtextcolor5 = NamedTextColor.YELLOW;
         } else {
            namedtextcolor5 = NamedTextColor.RED;
         }

         return new HudLine(
            Bridge.method28().method6(),
            TextComponentFactory.builder()
               .method2("Deaths")
               .method4(Integer.toString(SkyblockDeathCounterHud.this.deaths))
               .method5(NamedTextColor.GRAY)
               .method7(namedtextcolor5)
               .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
               .build()
         );
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      protected boolean method23() {
         return true;
      }
   }
}
