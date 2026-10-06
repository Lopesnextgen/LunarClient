package com.moonsworth.lunar.client.mod.skyblock.dungeonmilestonehud;

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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockDungeonMilestoneHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile(
      "^(Healer|Tank|Mage|Archer|Berserk) Milestone (?<tier>[❶-❿]): You have [a-zA-Z\\s]+ (?<amount>[\\d,]+) .+ so far! .+$"
   );
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showMilestoneCondition").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "milestoneColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private int field11;
   private String field12 = "0";

   public SkyblockDungeonMilestoneHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockDungeonMilestoneHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method51(this::onDisable);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
      this.handle(EventWorldChange.class, this::method2);
   }

   private void onDisable() {
      this.field11 = 0;
      this.field12 = "0";
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      Matcher matcher2 = field8.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (matcher2.matches()) {
         this.field11 = matcher2.group("tier").charAt(0) - 10101;
         this.field12 = matcher2.group("amount");
      }
   }

   private void method2(EventWorldChange data31) {
      this.field11 = 0;
      this.field12 = "0";
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_MILESTONE_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field9}));
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field10}));
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 40, 80, 50, 120, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1 ? this.method3(4, "12,500,000") : this.method3(SkyblockDungeonMilestoneHud.this.field11, SkyblockDungeonMilestoneHud.this.field12);
      }

      private List<HudLine> method3(int number1, String text2) {
         ArrayList list3 = new ArrayList();
         TextColor textcolor4 = TextColor.color(SkyblockDungeonMilestoneHud.this.field10.method14(0.0F));
         list3.add(new HudLine(Bridge.method28().method18(), Component.text("Milestone " + number1, textcolor4)));
         if ((Boolean)SkyblockDungeonMilestoneHud.this.field9.get()) {
            list3.add(new HudLine(Bridge.method28().method27(), Component.text(text2 + " Total", textcolor4)));
         }

         return list3;
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : IslandUtils.getIsland() == SkyblockIsland.DUNGEON;
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : IslandUtils.getIsland() == SkyblockIsland.DUNGEON;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
