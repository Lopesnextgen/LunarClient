package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.framework.Ref;

public class FogScaleMigration implements ConfigMigration {
   public FogScaleMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof GeneralSettings && (Ref.field1 || LcuiScreen.method19() > 1.0)) {
         json3.remove("useMinecraftScale");
      }
   }
}
