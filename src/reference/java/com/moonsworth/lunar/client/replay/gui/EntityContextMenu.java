package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.replay.render.SelectionHighlightHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;

public class EntityContextMenu extends RewindPropertyProvider {
   public EntityContextMenu(List<RewindPropertyProvider> list1) {
      super(list1);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      SelectionHighlightHandler rewindhandlers3impl22 = rewindhandlers1.method50();
      this.method3("showContextMenu", rewindhandlers3impl22.method15());
      if (rewindhandlers3impl22.method14() != null) {
         this.method3(
            "nametag", rewindhandlers3impl22.method14() instanceof EntityLivingBridge bridgeextension2_53 && (Ref.MC_VERSION == 0 || bridgeextension2_53.bridge$shouldShowName())
         );
         this.method3("skin", rewindhandlers3impl22.method14() instanceof Bridge6_10);
      } else {
         this.method3("nametag", false);
         this.method3("skin", false);
      }
   }
}
