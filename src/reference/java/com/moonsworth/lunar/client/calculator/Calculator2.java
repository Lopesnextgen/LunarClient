package com.moonsworth.lunar.client.calculator;

import com.moonsworth.lunar.client.framework.Client;

public interface Calculator2 {
   String getLanguagePath();

   default String method1(String text, Object... items) {
      return Client.method109().method67().method3(this, text, items);
   }
}
