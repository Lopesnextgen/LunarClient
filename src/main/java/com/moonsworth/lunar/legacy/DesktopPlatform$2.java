package com.moonsworth.lunar.legacy;

import java.net.URL;

enum DesktopPlatform$2 {
   ;
   DesktopPlatform$2(String text) {
   }

   public String[] getOpenUrlArguments(URL url1) {
      return new String[]{"open", url1.toString()};
   }
}
