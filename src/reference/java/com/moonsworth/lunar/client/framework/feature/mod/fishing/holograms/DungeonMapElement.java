package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.LineBatchRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
public class DungeonMapElement extends MapElement {
   private final MapRoomRenderer field7;
   private final RoomHistoryRenderer field8;
   public DungeonTrackable field9 = null;

   public DungeonMapElement(DungeonMapOverlay holograms3_21) {
      super("Dungeon", holograms3_21);
      this.field7 = new MapRoomRenderer(holograms3_21, this);
      this.field8 = new RoomHistoryRenderer(holograms3_21);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      DungeonPlayerTracker holograms4updater7 = holograms2_53.method29();
      float value8 = holograms4updater7.method8().method12(holograms_92) + holograms_92.method35();
      float value9 = holograms4updater7.method8().method13(holograms_92) + holograms_92.method35();
      this.method5(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers6, value8, value9, (arg4x, arg5x, arg6x) -> {
         for (DungeonRoomTracker holograms4iterator8x : holograms2_53.method25()) {
            this.field7.method1(mixinhelper_41, holograms_92, holograms2_53, holograms4iterator8x, arg4x, arg5x, arg6x);
         }

         this.method3(mixinhelper_41, holograms_92, holograms2_53, arg4x, arg5x, arg6x);
         if (this.field9 != null) {
            this.IHCCORIRRIIICCIOORCORRIHHROHOC.method26(this.field9.method1());
         }
      });
   }

   @Override
   public void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      LineBatchRenderer.method6(mixinhelper_41);
      float value7 = holograms_92.method35();
      if (holograms_92.method34() || holograms_92.method33()) {
         this.IHCCORIRRIIICCIOORCORRIHHROHOC.method13(mixinhelper_41, holograms_92, value4 + value7, value5 + value7, 100.0F - value7 * 2.0F, 100.0F - value7 * 2.0F);
      }

      DungeonPlayerTracker holograms4updater8 = holograms2_53.method29();
      float value9 = holograms4updater8.method8().method12(holograms_92) + value7;
      float value10 = holograms4updater8.method8().method13(holograms_92) + value7;
      this.method5(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers6, value9, value10, (arg4x, arg5x, arg6x) -> {
         LineBatchRenderer.method5(mixinhelper_41);
         this.field9 = null;

         for (RoomStateHistory rewindhandlers8x : holograms2_53.method26()) {
            this.field8.method1(mixinhelper_41, holograms_92, holograms2_53, rewindhandlers8x, arg4x, arg5x, arg6x);
         }

         for (DungeonRoomTracker holograms4iterator10x : holograms2_53.method25()) {
            this.field7.method2(mixinhelper_41, holograms_92, holograms2_53, holograms4iterator10x, arg4x, arg5x, arg6x);
         }

         LineBatchRenderer.method6(mixinhelper_41);
      });
      if (holograms_92.method34() || holograms_92.method33()) {
         this.IHCCORIRRIIICCIOORCORRIHHROHOC.method14(mixinhelper_41, holograms_92);
      }

      LineBatchRenderer.method5(mixinhelper_41);
   }

   private void method3(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      float value7 = Float.POSITIVE_INFINITY;
      this.IHCCORIRRIIICCIOORCORRIHHROHOC.method28(null);
      float value8 = holograms_92.method38();

      for (DungeonPlayerTracker holograms4updater10 : holograms2_53.getPlayers()) {
         if (!holograms4updater10.method19()) {
            float value11 = value4 + holograms4updater10.method8().method12(holograms_92) + value8;
            float value12 = value5 + holograms4updater10.method8().method13(holograms_92) + value8;
            float value13 = (float)(Math.pow(value11 - markers6.method10(), 2.0) + Math.pow(value12 - markers6.method11(), 2.0));
            if (value13 < value7 && holograms_92.method53()) {
               value7 = value13;
               this.IHCCORIRRIIICCIOORCORRIHHROHOC.method28(holograms4updater10);
            }
         }
      }

      for (DungeonPlayerTracker holograms4updater17 : holograms2_53.getPlayers()) {
         float value19 = value4 + holograms4updater17.method8().method12(holograms_92) + value8;
         float value21 = value5 + holograms4updater17.method8().method13(holograms_92) + value8;
         boolean flag22 = this.method5(holograms_92, holograms2_53, value4, value5, value19, value21);
         holograms4updater17.method10(mixinhelper_41, holograms_92, value19, value21, this.IHCCORIRRIIICCIOORCORRIHHROHOC, flag22);
         float value14 = (float)(Math.pow(value19 - markers6.method10(), 2.0) + Math.pow(value21 - markers6.method11(), 2.0));
         if (value14 < holograms4updater17.method17()) {
            this.field9 = holograms4updater17;
         }
      }

      DungeonPlayerTracker holograms4updater16 = this.IHCCORIRRIIICCIOORCORRIHHROHOC.method27();
      if (holograms4updater16 != null) {
         float value18 = value4 + holograms4updater16.method8().method12(holograms_92) + value8;
         float value20 = value5 + holograms4updater16.method8().method13(holograms_92) + value8;
         holograms4updater16.method11(
            mixinhelper_41, holograms_92, value18, value20, this.IHCCORIRRIIICCIOORCORRIHHROHOC, value18 > value4 && value18 < value4 + 100.0F && value20 > value5 && value20 < value5 + 100.0F, true
         );
      }
   }

   @Override
   public boolean method3(BettermapSettings holograms_91, DungeonStateTracker holograms2_52) {
      return true;
   }
}
