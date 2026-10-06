package com.moonsworth.lunar.client.chat.translation;

import com.moonsworth.lunar.client.framework.Client;

public interface Translatable {
   String getLanguagePath();

   default String method1(String text1, Object... items2) {
      return Client.method109().method67().method3(this, text1, items2);
   }
}
