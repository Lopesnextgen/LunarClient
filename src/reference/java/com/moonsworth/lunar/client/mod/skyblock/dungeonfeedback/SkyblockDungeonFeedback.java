package com.moonsworth.lunar.client.mod.skyblock.dungeonfeedback;

import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.StackedPlayersTooltip;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonFeedback extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final DungeonMapListener field9 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private static final Pattern field10 = Pattern.compile("^ +Team Score:.*$");

   public SkyblockDungeonFeedback(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(TypedChatMessage.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_FEEDBACK";
   }

   private void method1(TypedChatMessage data1) {
      if (this.field8.method6() != DungeonFloor.NONE) {
         String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
         if (field10.matcher(text2).matches()) {
            DungeonStateTracker holograms2_53 = (DungeonStateTracker)this.field9.method5().orElse(null);
            if (holograms2_53 != null) {
               new Thread(() -> {
                  try {
                     Thread.sleep(2000L);

                     for (DungeonPlayerTracker holograms4updater3x : holograms2_53.getPlayers()) {
                        Ref.method3().bridge$schedule(() -> SkyBlockChat.sendMessage(this.method2(holograms4updater3x)));
                     }
                  } catch (InterruptedException interruptedexception4) {
                  }
               }).start();
            }
         }
      }
   }

   private Component method2(DungeonPlayerTracker holograms4updater1) {
      Component component2 = ((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(holograms4updater1.method20(true), NamedTextColor.DARK_GREEN)
                     .append(Component.text(" cleared ", NamedTextColor.GRAY)))
                  .append(Component.text(holograms4updater1.method35() + "-" + holograms4updater1.method36(), NamedTextColor.GOLD)))
               .append(Component.text(" rooms | ", NamedTextColor.GRAY)))
            .append(Component.text(holograms4updater1.method4(), NamedTextColor.GOLD)))
         .append(Component.text(" secrets", NamedTextColor.GRAY));
      if (holograms4updater1.getDeaths() > 0) {
         component2 = component2.append(Component.text(" | ", NamedTextColor.GRAY))
            .append(Component.text(holograms4updater1.getDeaths(), NamedTextColor.RED))
            .append(Component.text(" death", NamedTextColor.GRAY));
         if (holograms4updater1.getDeaths() > 1) {
            component2 = component2.append(Component.text("s", NamedTextColor.GRAY));
         }
      }

      Object obj3 = Component.empty();
      boolean flag4 = true;

      for (StackedPlayersTooltip holograms2_46 : holograms4updater1.method29()) {
         if (!flag4) {
            obj3 = obj3.append(Component.text("\n"));
         }

         obj3 = obj3.append(holograms2_46.method1(holograms4updater1));
         flag4 = false;
      }

      return component2.style(Style.style().hoverEvent(HoverEvent.showText((Component)obj3)));
   }
}
