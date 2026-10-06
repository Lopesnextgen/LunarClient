package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockCave;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class SkyblockLocationListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("[^a-z A-Z:0-9_/'.!]");
   private static final Set<Character> field8 = Sets.newHashSet(new Character[]{'⏣', 'ф', '\ue067', '\ue020'});
   private final com.moonsworth.lunar.client.framework.listener.ScoreboardListener field9 = (com.moonsworth.lunar.client.framework.listener.ScoreboardListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.ScoreboardListener.class
   );
   private final TrackedValue<SkyblockCave> field10 = TrackedValue.method1(this, null);

   public SkyblockLocationListener() {
      this.handle(EventScoreboardUpdate.class, this::method1);
   }

   private void method1(EventScoreboardUpdate highlightimpl21) {
      if (IslandUtils.isOnIsland() && highlightimpl21.method1() != null) {
         this.update();
      }
   }

   protected void onEnable() {
      this.update();
   }

   private void update() {
      ImmutableList list1 = this.field9.method6();
      String text2 = null;

      for (String text4 : list1) {
         if (field8.stream().anyMatch(arg1x -> text4.contains(String.valueOf(arg1x)))) {
            String text5 = field7.matcher(text4).replaceAll("");
            text2 = text5.trim();
            if (IslandUtils.getIsland() == SkyblockIsland.RIFT) {
               text2 = "Rift " + text2;
            }
            break;
         }
      }

      if (text2 == null) {
         this.field10.set(null);
      } else {
         this.field10.set(SkyblockCave.fromScoreboard(text2).orElse(null));
      }
   }

   public Optional<SkyblockCave> method5() {
      return !IslandUtils.isOnIsland() ? Optional.empty() : Optional.ofNullable((SkyblockCave)this.field10.get());
   }
}
