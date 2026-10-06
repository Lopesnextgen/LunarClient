package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.replay.modsettings.Highlight;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.core.SettingsRegistry.SettingKeys;
import org.intellij.lang.annotations.Subst;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SettingKeys.class)
public class SettingKeysV1_8Mixin<T> implements Highlight {
   @Final
   @Shadow
   private T defaultValue;
   @Final
   @Shadow
   @Subst("optionId")
   private String key;
   private ClientOption option;
   private boolean wasPreInit = false;

   public SettingKeysV1_8Mixin() {
   }

   public ClientOption getOption() {
      if (this.option == null) {
         if (Ref.method4() != null && Ref.method4().method40() != null) {
            OptionContainer framework51 = (OptionContainer)Ref.method4().method40().method64().RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field14);
            this.option = framework51.method2().stream().filter(arg1x -> arg1x.getId().startsWith(this.key)).findFirst().orElseGet(() -> {
               ClientOption lightingextension2 = this.createOption(false);
               framework51.method1().add(lightingextension2);
               return lightingextension2;
            });
         } else {
            this.option = this.createOption(true);
         }
      } else if (this.wasPreInit && Ref.method4() != null && Ref.method4().method40() != null) {
         this.option = EventRegistrationsHandler.field7.getOrDefault(this.option.getId(), this.option);
         this.wasPreInit = false;
      }

      return this.option;
   }

   private ClientOption createOption(boolean flag1) {
      ClientOption lightingextension2 = this.HRICOROOOCCOCOROCRHHCRRIRCOICO(this.defaultValue, this.key);
      if (flag1) {
         this.wasPreInit = true;
         EventRegistrationsHandler.field7.put(lightingextension2.getId(), lightingextension2);
      }

      return lightingextension2;
   }
}
