package com.moonsworth.lunar.client;

import com.lunarclient.apollo.module.autotexthotkey.AutoTextHotkeyModule;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Collection;
import java.util.List;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator10 extends ApolloModuleHandler {
   public Highlight3Iterator10() {
      super("auto_text_hotkey", "Auto Text Hotkey");
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(AutoTextHotkeyModule.BLOCK_TEXT_INPUTS, AutoTextHotkeyModule.BLOCKED_TEXT_INPUTS, AutoTextHotkeyModule.BLOCK_CHAT_MESSAGE_TEXT_INPUTS);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
   }
}
