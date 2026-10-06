package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.network.GameBlogPostInteractionEventData;
import com.moonsworth.lunar.network.NetworkIterator;
import com.moonsworth.lunar.network.GameBlogPostInteractionEventData.Type;
import com.moonsworth.webosr.javascript.CallbackJS;

public class BlogPostsBridge implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method65().method15();
   }

   @CallbackJS("open")
   public static void method2(String text, String text2) {
      Client.method109().method105().method2(new NetworkIterator().method4(new GameBlogPostInteractionEventData().method2(text).method4(Type.CLICK)));
      DriverViewportLegacy.method50().method56().method11(text2, Initiator.INITIATOR_BLOG);
   }
}
