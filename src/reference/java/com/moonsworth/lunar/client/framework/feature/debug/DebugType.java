package com.moonsworth.lunar.client.framework.feature.debug;

import com.moonsworth.lunar.client.util.text.TextUtils;

public enum DebugType implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ALL,
   MOVEMENT_UI,
   ASSET_SERVER,
   IPC;

   DebugType() {
   }

   public String id() {
      return TextUtils.toCamelCase(this.name(), false);
   }

   public String getLanguagePath() {
      return "debug";
   }
}
