package com.moonsworth.lunar.client.mod.skyblock.terminalsplits;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonTerminalListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalPhaseEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalActivateEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalActivateEvent.Type;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.TimeFormatting.TimeFormat;
import java.util.Arrays;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockTerminalSplits extends AbstractFeature {
   private final DungeonTerminalListener field8 = (DungeonTerminalListener)this.method63(DungeonTerminalListener.class);
   private final long[] field9 = new long[5];

   public SkyblockTerminalSplits(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(TerminalPhaseEvent.class, this::method2);
      this.handle(TerminalActivateEvent.class, this::method1);
   }

   private void method1(TerminalActivateEvent data31) {
      if (data31.method1() == Type.DEVICE) {
         int number2 = this.field8.method15();
         if (number2 == 1 && !data31.method3()) {
            SkyBlockChat.method1("Simon Says: " + TimeFormat.STOPWATCH.format(Ref.method3().bridge$getSystemTime() - this.field8.method13()) + "!");
         }
      }
   }

   private void method2(TerminalPhaseEvent data21) {
      int number2 = data21.method1();
      int index3 = number2 - 1;
      if (index3 >= 0 && index3 < this.field9.length) {
         this.field9[index3] = data21.getTimestamp();
         if (data21.method1() > 4) {
            this.method13();
         }
      }
   }

   private void method13() {
      SkyBlockChat.sendMessage(
         ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(
                                       "Terminal splits: "
                                    )
                                    .color(NamedTextColor.GOLD))
                                 .append(Component.text("1 - ").color(NamedTextColor.GRAY)))
                              .append(Component.text(TimeFormat.STOPWATCH.format(this.field9[1] - this.field9[0])).color(NamedTextColor.WHITE)))
                           .append(Component.text(", 2 - ").color(NamedTextColor.GRAY)))
                        .append(Component.text(TimeFormat.STOPWATCH.format(this.field9[2] - this.field9[1])).color(NamedTextColor.WHITE)))
                     .append(Component.text(", 3 - ").color(NamedTextColor.GRAY)))
                  .append(Component.text(TimeFormat.STOPWATCH.format(this.field9[3] - this.field9[2])).color(NamedTextColor.WHITE)))
               .append(Component.text(", 4 - ").color(NamedTextColor.GRAY)))
            .append(Component.text(TimeFormat.STOPWATCH.format(this.field9[4] - this.field9[3])).color(NamedTextColor.WHITE))
      );
      Arrays.fill(this.field9, 0L);
   }

   public String getId() {
      return "SKYBLOCK_TERMINAL_SPLITS";
   }
}
