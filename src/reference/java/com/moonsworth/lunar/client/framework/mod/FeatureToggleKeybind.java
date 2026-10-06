package com.moonsworth.lunar.client.framework.mod;

import com.google.common.base.CaseFormat;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionRegistrant;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class FeatureToggleKeybind implements OptionRegistrant {
   private final Framework7Extension field1;
   private final ModifierKeybindOption field2;

   public FeatureToggleKeybind(Framework7Extension var1) {
      this.field1 = var1;
      String var2 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, var1.getId()) + "ToggleKeybind";
      this.field2 = (ModifierKeybindOption)((ModifierKeybindOption.Data)((ModifierKeybindOption.Data)OptionFactory.method18(var2)
               .method5(KeyCode.KEY_NONE)
               .method14(() -> this.method8("toggleKeybindName", this.method2())))
            .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
         .method31();
      this.field2.method3(this::method6);
   }

   @Override
   public void method1(RootSettingsAssembler var1) {
      var1.method11(new ClientOption[]{this.field2});
   }

   public boolean isSet() {
      return this.field2.get().method8() != KeyCode.KEY_NONE;
   }

   public void clear() {
      this.field2.method8(KeyCombo.method1(KeyCode.KEY_NONE));
   }

   private String method2() {
      return ((ModDetails)this.field1.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field13)).getName();
   }

   public String method3() {
      return this.isSet() ? this.field2.method17() : this.method8("toggleKeybindNone");
   }

   public String method4() {
      return ThreadModuleDump63.method27(this.field2.method9(), this.field2.getId());
   }

   public boolean method5() {
      return !this.field2.method9().isEmpty();
   }

   private void method6() {
      if (this.isSet()) {
         Alert2 var1 = (Alert2)this.field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
         if (var1 != null && var1.method3().isPresent()) {
            boolean var4 = var1.method2() == AlertType.SERVER;
            String var3 = var1.method3().get()
               ? (var4 ? "modpackAllowedModDescription" : "allowedModDescription")
               : (var4 ? "modpackDisallowedModDescription" : "disallowedModDescription");
            this.method7(this.method8(var3, this.method2()));
         } else {
            ClientOption var2 = this.field1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field6).flatMap(ModEnabledState::method1).orElse(null);
            if (var2 != null) {
               var2.method10(!(Boolean)var2.get());
               if (ThreadModuleDump63.method4().method41().method6().method51().get()) {
                  this.method7(NotificationManager.method15(var2.get() ? "modToggleEnabled" : "modToggleDisabled", new Object[]{this.method2()}));
               }
            }
         }
      }
   }

   private void method7(String var1) {
      Framework10 var2 = (Framework10)this.field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
      ResourceLocationBridge var3 = var2 != null ? var2.method2() : null;
      if (var3 != null) {
         ThreadModuleDump63.method4().method69().method4(var3, var1);
      } else {
         ThreadModuleDump63.method4().method69().method3(var1);
      }
   }

   private String method8(String var1, Object... var2) {
      return ThreadModuleDump63.method4() == null ? var1 : ThreadModuleDump63.method4().method67().method2("gui.components", var1, var2);
   }

   @Generated
   public ModifierKeybindOption method9() {
      return this.field2;
   }
}
