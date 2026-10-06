package com.moonsworth.lunar.client.mod.skyblock.dungeonbreaker;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonBreaker extends AbstractFeature {
   private final EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private static final Pattern field9 = Pattern.compile("^Charges: (?<charges>\\d+)/20⸕$");
   private int field10;
   private boolean field11;

   public SkyblockDungeonBreaker(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockDungeonBreaker.Data()));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(EventTick.class, this::method1);
   }

   private void method1(EventTick highlightimpl21) {
      this.field11 = this.method13();
   }

   private boolean method13() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return false;
      }

      if (!this.field8.method9().equals("DUNGEONBREAKER")) {
         return false;
      }

      ItemStackBridge bridgeextension_42 = this.field8.method8();
      List list3 = SkyblockItemUtil.method15(bridgeextension_42);
      int number4 = list3.size();
      byte index5 = 9;
      if (number4 > index5) {
         String text6 = (String)list3.get(index5);
         if (this.method3(text6)) {
            return true;
         }
      }

      for (int index7 = 0; index7 < number4; index7++) {
         if (index7 != index5 && this.method3((String)list3.get(index7))) {
            return true;
         }
      }

      return false;
   }

   private boolean method3(String text1) {
      Matcher matcher2 = field9.matcher(text1);
      if (matcher2.matches()) {
         this.field10 = Integer.parseInt(matcher2.group("charges"));
         return true;
      } else {
         return false;
      }
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_BREAKER";
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 15, 20, 50, 140, 200);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         return flag1 ? this.method3(20) : this.method3(SkyblockDungeonBreaker.this.field10);
      }

      private HudLine method3(int number1) {
         return new HudLine(
            Bridge.method28().method40(),
            TextComponentFactory.builder()
               .method1(Component.text(SkyblockDungeonBreaker.this.method1("pickaxeCharges", new Object[0])))
               .method3(Component.text(number1))
               .method5(NamedTextColor.RED)
               .method9(NamedTextColor.GRAY)
               .method7(this.method4(number1))
               .build()
         );
      }

      private TextColor method4(int number1) {
         if (number1 >= 15) {
            return NamedTextColor.GREEN;
         } else {
            return number1 > 5 ? NamedTextColor.YELLOW : NamedTextColor.RED;
         }
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : flag1 || SkyblockDungeonBreaker.this.field11;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method20() {
         return false;
      }
   }
}
