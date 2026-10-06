package com.moonsworth.lunar.client.mod.misc.servercommandconfig;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;

public class ServerCommandConfig extends AbstractFeature {
   public ServerCommandConfig() {
      super(true);
      this.method4(ModTraits.field18, arg0 -> arg0.RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(30));
   }

   @ConstantName
   public String getId() {
      return "SERVER_COMMAND_CONFIG";
   }

   protected void method1(boolean flag1) {
   }

   public void method2(RootSettingsBuilder lightingextension231) {
   }

   protected ModDetails method20() {
      return super.method20();
   }
}
