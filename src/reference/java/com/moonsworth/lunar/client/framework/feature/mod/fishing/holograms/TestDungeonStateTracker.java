package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;

final class TestDungeonStateTracker extends DungeonStateTracker {
   private final boolean field31;

   private TestDungeonStateTracker(boolean flag) {
      super(DungeonFloor.F7, false, null, null, null);
      this.field31 = flag;
   }

   @Override
   public boolean method39() {
      return this.field31;
   }
}
