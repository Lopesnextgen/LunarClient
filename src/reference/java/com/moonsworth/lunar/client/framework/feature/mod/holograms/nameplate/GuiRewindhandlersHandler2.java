package com.moonsworth.lunar.client.framework.feature.mod.holograms.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler28;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.mixin.Rewindhandlers;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakingProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPlacement;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler28 field7 = (GuiRewindhandlersHandler28)this.method3(GuiRewindhandlersHandler28.class);
   private final GuiRewindhandlersHandler29 field8 = (GuiRewindhandlersHandler29)this.method3(GuiRewindhandlersHandler29.class);
   @Nullable
   private HighlightType3 field9;

   public GuiRewindhandlersHandler2() {
      this.handle(EventBlockPlacement.class, this::method1);
      this.handle(EventBlockBreakingProgress.class, this::method2);
   }

   private void method1(EventBlockPlacement var1) {
      Bridge3_23 var2 = var1.getBlock();

      for (HighlightType3 var6 : HighlightType3.values()) {
         if (var6.getIsCrop().test(var2, this.field7)) {
            this.field9 = var6;
            ClientEventBus.method29().method12(Rewindhandlers.class, () -> new Rewindhandlers(var6));
            return;
         }
      }
   }

   private void method2(EventBlockBreakingProgress var1) {
      Bridge3_23 var2 = ThreadModuleDump63.method8().method4(var1.method2());
      if (var2 == Bridge.method34().method58()) {
         if (this.field8.getId().startsWith("CACTUS_KNIFE")) {
            this.field9 = HighlightType3.CACTUS;
         }
      }
   }

   @Nullable
   @Generated
   public HighlightType3 method5() {
      return this.field9;
   }
}
