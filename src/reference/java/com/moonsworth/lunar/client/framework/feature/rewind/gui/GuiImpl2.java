package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator22;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.Annotation7;

@Annotation7
public class GuiImpl2 extends Gui_2<RewindIterator22> {
   public GuiImpl2(Nameplate2 nameplate2, HashMapImpl map) {
      super(nameplate2, map);
   }

   @Override
   public String type() {
      return "audio";
   }
}
