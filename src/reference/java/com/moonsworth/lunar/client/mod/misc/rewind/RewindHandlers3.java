package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import lombok.Generated;

public abstract class RewindHandlers3 extends AbstractFeature {
   protected final ThreadModuleDump6<Nameplate4> field8;

   public RewindHandlers3(ThreadModuleDump6<Nameplate4> var1) {
      super(true);
      this.field8 = var1;
      Framework10Extension var2 = (Framework10Extension)((Nameplate4)var1.get()).method6().method64(Framework.field12, var0 -> Framework10Extension.method13());
      if (var2 != null) {
         var2.method4(this);
      }
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public ThreadModuleDump6<Nameplate4> method13() {
      return this.field8;
   }
}
