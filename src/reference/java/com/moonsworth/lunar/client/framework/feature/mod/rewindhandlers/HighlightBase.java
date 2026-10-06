package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.gui.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class HighlightBase extends Highlight implements Nameplate2 {
   @Annotation3(GuiRewindhandlersHandler2.class)
   public static class Data extends HighlightBase {
   }

   @Annotation3(GuiRewindhandlersHandler2.class)
   public static class Data2 extends HighlightBase {
      private final boolean field1;

      @Generated
      public Data2(boolean flag) {
         this.field1 = flag;
      }

      @Generated
      public boolean method1() {
         return this.field1;
      }
   }
}
