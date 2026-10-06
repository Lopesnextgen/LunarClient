package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.render.SelectionHighlightHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;

class SelectedElement {
   private final RewindHandlers field1;
   private final RewindIterator<?> field2;
   private final SelectionHighlightHandler field3;
   private final String field4;

   SelectedElement(RewindHandlers rewindhandlers1, RewindIterator<?> iterator, SelectionHighlightHandler handler, String text) {
      this.field1 = rewindhandlers1;
      this.field2 = iterator;
      this.field3 = handler;
      this.field4 = text;
   }

   public RewindHandlers method1() {
      return this.field1;
   }

   public RewindIterator<?> method2() {
      return this.field2;
   }

   public SelectionHighlightHandler method3() {
      return this.field3;
   }

   public String method4() {
      return this.field4;
   }
}
