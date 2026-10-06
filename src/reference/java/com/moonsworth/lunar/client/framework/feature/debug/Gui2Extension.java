package com.moonsworth.lunar.client.framework.feature.debug;

import com.moonsworth.lunar.client.util.ThreadModuleDump46;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ALL,
   MOVEMENT_UI,
   ASSET_SERVER,
   IPC;

   public String id() {
      return ThreadModuleDump46.method6(this.name(), false);
   }

   public String getLanguagePath() {
      return "debug";
   }
}
