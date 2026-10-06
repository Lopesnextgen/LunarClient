package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import lombok.Generated;

public abstract class RewindHandler extends AbstractFeature {
   protected final ValueHolder<ReplayContext> field8;

   public RewindHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(true);
      this.field8 = threadmoduledump61;
      ModLifecycle framework10extension2 = (ModLifecycle)((ReplayContext)threadmoduledump61.get()).method6().method64(ModTraits.field12, arg0 -> ModLifecycle.method13());
      if (framework10extension2 != null) {
         framework10extension2.method4(this);
      }
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public ValueHolder<ReplayContext> method13() {
      return this.field8;
   }
}
