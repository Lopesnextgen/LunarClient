package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler212;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class Rewindhandlers {
   @Annotation3(GuiRewindhandlersHandler212.class)
   public static class Data13 extends Highlight implements Nameplate2 {
      private final String field1;

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public Data13(String var1) {
         this.field1 = var1;
      }
   }

   @Annotation3(GuiRewindhandlersHandler22.class)
   public static class Data14 extends Highlight implements Nameplate2 {
   }

   @Annotation3(GuiRewindhandlersHandler212.class)
   public static class Data15 extends Highlight implements Nameplate2 {
      private final String field1;
      private final String field2;

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public String method2() {
         return this.field2;
      }

      @Generated
      public Data15(String var1, String text) {
         this.field1 = var1;
         this.field2 = text;
      }
   }
}
