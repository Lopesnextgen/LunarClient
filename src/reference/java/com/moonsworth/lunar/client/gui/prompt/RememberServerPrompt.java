package com.moonsworth.lunar.client.gui.prompt;

import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2_2;
import com.moonsworth.lunar.client.mod.misc.servercommandconfig.ServerCommandConfig;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.ui.prompt.PromptAction;

public class RememberServerPrompt extends PromptAction {
   @Override
   public void method1(boolean var1) {
      ServerCommandConfig var2 = ThreadModuleDump63.method4().method62().method11();
      GeneralSettings var3 = ThreadModuleDump63.method4().method41().method6();
      String var4 = this.getServerIp();
      if (var4 != null) {
         if (var1) {
            Fishing2_2.method1(var2.method9("yes", new Object[0]));
            var3.method83().add(var4);
         } else {
            Fishing2_2.method1(var2.method9("no", new Object[0]));
            var3.method84().add(var4);
         }
      }
   }

   @Override
   public Component method2() {
      ServerCommandConfig var1 = ThreadModuleDump63.method4().method62().method11();
      String var2 = this.getServerIp();
      return var2 == null ? null : Component.text(var1.method9("rememberChoice", new Object[0]));
   }

   private String getServerIp() {
      Bridge3_19 var1 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
      return var1 == null ? null : var1.bridge$serverIP();
   }
}
