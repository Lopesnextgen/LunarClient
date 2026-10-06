package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler23.class)
public class HighlightImpl3 extends Highlight implements Nameplate2 {
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.Rewindhandlers field1;
   private boolean field2;

   public int getCount() {
      return this.field2 ? 2 : 1;
   }

   @Generated
   public HighlightImpl3(com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.Rewindhandlers rewindhandlers, boolean flag) {
      this.field1 = rewindhandlers;
      this.field2 = flag;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.Rewindhandlers method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }
}
