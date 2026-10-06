package com.moonsworth.lunar.client.event.mixin.gui;

import lombok.Generated;
import net.kyori.adventure.text.Component;

public class TitleEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Component title;
   private final TitleEvent.Type field1;
   private boolean field2;

   @Generated
   public TitleEvent(Component var1, TitleEvent.Type var2, boolean flag) {
      this.title = var1;
      this.field1 = var2;
      this.field2 = flag;
   }

   @Generated
   public TitleEvent(Component var1, TitleEvent.Type var2) {
      this.title = var1;
      this.field1 = var2;
   }

   @Generated
   public Component getTitle() {
      return this.title;
   }

   @Generated
   public TitleEvent.Type method2() {
      return this.field1;
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }

   public enum Type {
      SERVER,
      APOLLO;
   }
}
