package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler25;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class Rewindhandlers2 {
   @Annotation3(GuiRewindhandlersHandler25.class)
   public static class Data extends Highlight implements Nameplate2 {
      private final HighlightType field1;

      @Generated
      public HighlightType method1() {
         return this.field1;
      }

      @Generated
      public Data(HighlightType var1) {
         this.field1 = var1;
      }
   }

   @Annotation3(GuiRewindhandlersHandler25.class)
   public static class Data2 extends Highlight implements Nameplate2 {
      private final long field1;
      private final int field2;

      @Generated
      public long getTimestamp() {
         return this.field1;
      }

      @Generated
      public int method1() {
         return this.field2;
      }

      @Generated
      public Data2(long var1, int var3) {
         this.field1 = var1;
         this.field2 = var3;
      }
   }

   @Annotation3(GuiRewindhandlersHandler25.class)
   public static class Data3 extends com.moonsworth.lunar.client.highlight.HighlightImpl implements Nameplate2 {
      private final Rewindhandlers2.Data3.Type field1;
      private final String field2;
      private final boolean field3;

      @Generated
      public Rewindhandlers2.Data3.Type method1() {
         return this.field1;
      }

      @Generated
      public String method2() {
         return this.field2;
      }

      @Generated
      public boolean method3() {
         return this.field3;
      }

      @Generated
      public Data3(Rewindhandlers2.Data3.Type var1, String text, boolean var3) {
         this.field1 = var1;
         this.field2 = text;
         this.field3 = var3;
      }

      public enum Type {
         TERMINAL,
         DEVICE,
         LEVER,
         GATE;
      }
   }
}
