package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class HighlightBase3 extends Highlight implements Nameplate2 {
   private final String field1;
   private final long field2;

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public long getTimestamp() {
      return this.field2;
   }

   @Generated
   public HighlightBase3(String var1, long var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Annotation3(GuiRewindhandlersHandler2.class)
   public static class Data extends HighlightBase3 {
      public Data(String var1, long var2) {
         super(var1, var2);
      }
   }

   @Annotation3(GuiRewindhandlersHandler2.class)
   public static class Data2 extends HighlightBase3 {
      public Data2(String var1, long var2) {
         super(var1, var2);
      }
   }

   @Annotation3(GuiRewindhandlersHandler2.class)
   public static class Data3 extends HighlightBase3 {
      public Data3(String var1, long var2) {
         super(var1, var2);
      }
   }
}
