package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.Annotation7;

@Annotation7
public class GuiImpl extends Gui_2<Rewind_2> {
   public GuiImpl(Nameplate2 nameplate2, HashMapImpl map) {
      super(nameplate2, map);
   }

   @Override
   public String type() {
      return "effect";
   }
}
