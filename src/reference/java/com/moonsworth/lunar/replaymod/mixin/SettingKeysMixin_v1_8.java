package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.replay.modsettings.Highlight;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.core.SettingsRegistry.SettingKeys;
import org.intellij.lang.annotations.Subst;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SettingKeys.class)
public class SettingKeysMixin_v1_8<T> implements Highlight {
   @Final
   @Shadow
   private T defaultValue;
   @Final
   @Shadow
   @Subst("optionId")
   private String key;
   private ClientOption option;
   private boolean wasPreInit = false;

   public ClientOption getOption() {
      if (this.option == null) {
         if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
            Framework5 var1 = (Framework5)ThreadModuleDump63.method4().method40().method64().method7(Framework.field14);
            this.option = var1.method2().stream().filter(var1x -> var1x.getId().startsWith(this.key)).findFirst().orElseGet(() -> {
               ClientOption var2 = this.createOption(false);
               var1.method1().add(var2);
               return var2;
            });
         } else {
            this.option = this.createOption(true);
         }
      } else if (this.wasPreInit && ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         this.option = EventRegistrationsHandler.field7.getOrDefault(this.option.getId(), this.option);
         this.wasPreInit = false;
      }

      return this.option;
   }

   private ClientOption createOption(boolean var1) {
      ClientOption var2 = this.method1(this.defaultValue, this.key);
      if (var1) {
         this.wasPreInit = true;
         EventRegistrationsHandler.field7.put(var2.getId(), var2);
      }

      return var2;
   }
}
