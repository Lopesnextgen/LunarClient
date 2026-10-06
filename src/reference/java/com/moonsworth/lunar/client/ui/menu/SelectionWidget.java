package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import lombok.Generated;

public abstract class SelectionWidget<T> extends GuiWidget {
   protected final T field16;

   public SelectionWidget(GuiWidget handler, T t) {
      super(handler);
      this.field16 = (T)t;
   }

   @Generated
   public T getValue() {
      return this.field16;
   }
}
