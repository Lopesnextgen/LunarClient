package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler29.class)
public class HighlightImpl extends Highlight implements Nameplate2 {
   private final ItemStackBridge field1;
   private final String field2;
   private final String field3;

   @Generated
   public ItemStackBridge method1() {
      return this.field1;
   }

   @Generated
   public String getId() {
      return this.field2;
   }

   @Generated
   public String method2() {
      return this.field3;
   }

   @Generated
   public HighlightImpl(ItemStackBridge itemStackBridge, String text, String text2) {
      this.field1 = itemStackBridge;
      this.field2 = text;
      this.field3 = text2;
   }
}
