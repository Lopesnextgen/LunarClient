package com.moonsworth.lunar.client.network.apollo;

import com.lunarclient.apollo.module.autotexthotkey.AutoTextHotkeyModule;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Collection;
import java.util.List;

public class AutoTextHotkeyApolloHandler extends ApolloModuleHandler {
   public AutoTextHotkeyApolloHandler() {
      super("auto_text_hotkey", "Auto Text Hotkey");
   }

   public Collection<Option<?, ?, ?>> method1() {
      return List.of(AutoTextHotkeyModule.BLOCK_TEXT_INPUTS, AutoTextHotkeyModule.BLOCKED_TEXT_INPUTS, AutoTextHotkeyModule.BLOCK_CHAT_MESSAGE_TEXT_INPUTS);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
   }
}
