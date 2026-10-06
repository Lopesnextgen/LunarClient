package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class StackedPlayersTooltip {
   private final List<DungeonPlayerTracker> field1;
   private final DungeonRoomTracker field2;

   public TextComponent method1(DungeonPlayerTracker holograms4updater1) {
      String text2 = this.field2.method11(false);
      NamedTextColor namedtextcolor3 = this.field2.method30().method6().color();
      String text4 = text2 + " (" + this.field2.method30().method6() + ")";
      if (!this.method2()) {
         StringBuilder builder5 = new StringBuilder(" Stacked with ");
         boolean flag6 = true;

         for (DungeonPlayerTracker holograms4updater8 : this.field1) {
            if (holograms4updater8 != holograms4updater1) {
               if (!flag6) {
                  builder5.append(", ");
               }

               builder5.append(holograms4updater8.method20(true));
               flag6 = false;
            }
         }

         text4 = text4 + builder5.toString();
      }

      return Component.text(text4, namedtextcolor3);
   }

   public boolean method2() {
      return this.field1.size() == 1;
   }

   @Generated
   public StackedPlayersTooltip(List<DungeonPlayerTracker> list, DungeonRoomTracker dungeonRoomTracker) {
      this.field1 = list;
      this.field2 = dungeonRoomTracker;
   }
}
