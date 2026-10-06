package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.framework.Ref;

public class CompetitiveDisconnectScreen extends ConfirmScreen {
   public CompetitiveDisconnectScreen(GuiScreenBridge guiScreenBridge, Runnable runnable2) {
      super("gui.apollo.competitiveDisconnect", arg2x -> {
         if (arg2x) {
            runnable2.run();
         } else {
            Ref.method3().bridge$displayScreen(guiScreenBridge);
         }
      });
   }
}
