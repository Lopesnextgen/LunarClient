package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler25;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class HighlightBase2 extends Highlight implements Nameplate2 {
   private final Fishing3 field1;

   @Generated
   public Fishing3 method1() {
      return this.field1;
   }

   @Generated
   public HighlightBase2(Fishing3 var1) {
      this.field1 = var1;
   }

   @Annotation3(GuiRewindhandlersHandler25.class)
   public static class Data extends HighlightBase2 {
      public Data(Fishing3 var1) {
         super(var1);
      }
   }

   @Annotation3(GuiRewindhandlersHandler25.class)
   public static class Data2 extends HighlightBase2 {
      public Data2(Fishing3 var1) {
         super(var1);
      }
   }

   @Annotation3(GuiRewindhandlersHandler25.class)
   public static class Data3 extends HighlightBase2 {
      public Data3(Fishing3 var1) {
         super(var1);
      }
   }
}
