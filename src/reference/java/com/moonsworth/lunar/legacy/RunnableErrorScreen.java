package com.moonsworth.lunar.legacy;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiErrorScreen;

class RunnableErrorScreen extends GuiErrorScreen {
   RunnableErrorScreen(Legacy2 legacy21, String text2, String text3, Runnable runnable4) {
      super(text2, text3);
      this.field2 = legacy21;
      this.field1 = runnable4;
   }

   public void actionPerformed(GuiButton guibutton1) {
      this.field1.run();
   }
}
