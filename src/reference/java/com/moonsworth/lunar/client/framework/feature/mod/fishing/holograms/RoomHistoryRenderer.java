package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
public class RoomHistoryRenderer {
   private final DungeonMapOverlay field1;

   public RoomHistoryRenderer(DungeonMapOverlay holograms3_21) {
      this.field1 = holograms3_21;
   }

   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, RoomStateHistory rewindhandlers4, float value, float value2, MarkerModel<?> markers7) {
      if (rewindhandlers4.method8() != null) {
         RewindhandlersExtension rewindhandlersextension8 = holograms_92.method20(rewindhandlers4.method8(), true);
         boolean flag9 = rewindhandlers4.method6().method9() == rewindhandlers4.method7().method9();
         float value10 = holograms_92.method35();
         float value11 = rewindhandlers4.method6().method12(holograms_92);
         float value12 = rewindhandlers4.method6().method13(holograms_92);
         float value13 = holograms_92.method17(holograms2_53);
         float value14;
         float value15;
         float value16;
         float value17;
         if (flag9) {
            value16 = holograms_92.method19(holograms2_53);
            value17 = holograms_92.method18(holograms2_53);
            value14 = value + value10 + value11 + value13;
            value15 = value2 + value10 + value12 + value13 / 2.0F - value17 / 2.0F;
         } else {
            value16 = holograms_92.method18(holograms2_53);
            value17 = holograms_92.method19(holograms2_53);
            value14 = value + value10 + value11 + value13 / 2.0F - value16 / 2.0F;
            value15 = value2 + value10 + value12 + value13;
         }

         float value18 = holograms_92.method19(holograms2_53);
         this.field1.method17(mixinhelper_41, value14 + value18 / 2.0F, value15 + value18 / 2.0F, value16, value17, rewindhandlersextension8);
      }
   }
}
