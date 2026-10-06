package com.moonsworth.lunar.legacy;

import java.net.URL;

enum DesktopPlatform$1 {
   ;
   DesktopPlatform$1(String text) {
   }

   public String[] getOpenUrlArguments(URL url1) {
      return new String[]{"rundll32", "url.dll,FileProtocolHandler", url1.toString()};
   }
}
