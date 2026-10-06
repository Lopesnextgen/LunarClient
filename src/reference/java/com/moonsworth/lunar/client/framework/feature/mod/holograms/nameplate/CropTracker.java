package com.moonsworth.lunar.client.framework.feature.mod.holograms.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.CropType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockTimeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.mixin.EventCropPlaced;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPlace;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class CropTracker extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final SkyblockTimeListener field7 = (SkyblockTimeListener)this.method3(SkyblockTimeListener.class);
   private final EquippedItemListener field8 = (EquippedItemListener)this.method3(EquippedItemListener.class);
   @Nullable
   private CropType field9;

   public CropTracker() {
      this.handle(EventBlockPlace.class, this::method1);
      this.handle(EventBlockBreakProgress.class, this::method2);
   }

   private void method1(EventBlockPlace highlightimpl81) {
      Bridge3_23 bridge3_232 = highlightimpl81.getBlock();

      for (CropType highlighttype36 : CropType.values()) {
         if (highlighttype36.getIsCrop().test(bridge3_232, this.field7)) {
            this.field9 = highlighttype36;
            LunarEventBus.method29().method12(EventCropPlaced.class, () -> new EventCropPlaced(highlighttype36));
            return;
         }
      }
   }

   private void method2(EventBlockBreakProgress highlightimpl51) {
      Bridge3_23 bridge3_232 = Ref.method8().RHIRRICCRHHHIIHHIHHOHRCHIOORCC(highlightimpl51.method2());
      if (bridge3_232 == Bridge.method34().method58()) {
         if (this.field8.getId().startsWith("CACTUS_KNIFE")) {
            this.field9 = CropType.CACTUS;
         }
      }
   }

   @Nullable
   @Generated
   public CropType method5() {
      return this.field9;
   }
}
