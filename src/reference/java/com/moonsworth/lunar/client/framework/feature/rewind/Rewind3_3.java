package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class Rewind3_3 {
   private final RewindHandlers field1;
   private final Highlight_3 field2;
   private final Nameplate2 field3;
   private final Gui_2<? extends RewindIterator<?>> field4;
   private final Entry<Range<Integer>, RewindIterator<?>> field5;

   public Rewind3_3(
      RewindHandlers handler, Highlight_3 highlight_3, Nameplate2 nameplate2, Gui_2<? extends RewindIterator<?>> gui_2, Entry<Range<Integer>, RewindIterator<?>> entry
   ) {
      this.field1 = handler;
      this.field2 = highlight_3;
      this.field3 = nameplate2;
      this.field4 = gui_2;
      this.field5 = entry;
   }

   public RewindHandlers method1() {
      return this.field1;
   }

   public Highlight_3 method2() {
      return this.field2;
   }

   public Nameplate2 method3() {
      return this.field3;
   }

   public Gui_2<? extends RewindIterator<?>> method4() {
      return this.field4;
   }

   public Entry<Range<Integer>, RewindIterator<?>> method5() {
      return this.field5;
   }
}
