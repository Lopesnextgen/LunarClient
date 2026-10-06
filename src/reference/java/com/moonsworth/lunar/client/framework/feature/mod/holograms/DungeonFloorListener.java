package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonFloorDetectedEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonExitEvent;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DungeonFloorListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ \ue067 The Catacombs \\(([FME1-7]+)\\)$");
   private final ScoreboardListener field8 = (ScoreboardListener)this.method3(ScoreboardListener.class);
   private final TrackedValue<DungeonFloor> field9 = TrackedValue.method1(this, DungeonFloor.NONE);

   public DungeonFloorListener() {
      this.handle(EventWorldChange.class, this::method1);
      this.method4(EventScoreboardUpdate.class, this::method2, 101);
   }

   private void method1(EventWorldChange data31) {
      if (this.field9.get() != DungeonFloor.NONE) {
         LunarEventBus.method29().method12(DungeonExitEvent.class, DungeonExitEvent::new);
         this.field9.set(DungeonFloor.NONE);
      }
   }

   private void method2(EventScoreboardUpdate highlightimpl21) {
      this.method5();
   }

   private void method5() {
      SkyblockIsland gui2extension31 = IslandUtils.getIsland();
      if (gui2extension31 != SkyblockIsland.NONE) {
         DungeonFloor highlighttype2 = (DungeonFloor)this.field9.get();
         this.field9.set(this.method4(this.field8.method6(), gui2extension31));
         if (highlighttype2 == DungeonFloor.NONE && this.field9.get() != DungeonFloor.NONE) {
            LunarEventBus.method29().method12(DungeonFloorDetectedEvent.class, () -> new DungeonFloorDetectedEvent((DungeonFloor)this.field9.get()));
         }
      }
   }

   private DungeonFloor method4(List<String> list1, SkyblockIsland gui2extension32) {
      for (String text4 : list1) {
         Matcher matcher5 = field7.matcher(text4);
         if (matcher5.find()) {
            try {
               return DungeonFloor.valueOf(matcher5.group(1));
            } catch (IllegalArgumentException illegalargumentexception7) {
               System.err.println("Could not find current dungeon floor.");
               return DungeonFloor.NONE;
            }
         }
      }

      return gui2extension32 == SkyblockIsland.DUNGEON ? (DungeonFloor)this.field9.get() : DungeonFloor.NONE;
   }

   protected void onEnable() {
      this.method5();
   }

   public DungeonFloor method6() {
      return (DungeonFloor)this.field9.get();
   }
}
