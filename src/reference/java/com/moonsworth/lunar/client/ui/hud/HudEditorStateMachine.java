package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.ui.widget.AdvancedOptionWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.hud.HudEditorState;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl3;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;

public final class HudEditorStateMachine implements HudEditorState {
   private final HudElementRegistryImpl field1;

   @Override
   public void method1(String var1) {
      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.isRecording()) {
         this.field1.method3(var1).ifPresent(var1x -> {
            MixinCore9Extension var2x = (MixinCore9Extension)var1x.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1);

            try {
               var2.method34().method25().put(var1x.getId(), new Nameplate2Impl3(var1x.getId(), var2x.method26(), var2x.getX(), var2x.getY()));
            } catch (Exception var4) {
               throw new RuntimeException(var4);
            }
         });
      }
   }

   @Override
   public void method2(String var1) {
      ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @Override
   public boolean method3(String var1) {
      Framework7Extension var2 = this.field1.method3(var1).orElse(null);
      if (var2 == null) {
         return false;
      }

      if (((MixinCore9Extension)var2.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1)).method32()) {
         return true;
      }

      FeatureSettingsScreen var3 = new FeatureSettingsScreen(ThreadModuleDump63.method3().bridge$getCurrentScreen());
      Framework4 var4 = (Framework4)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field16);
      if (var4 != null) {
         FeatureSettingsWidget var5 = new FeatureSettingsWidget(var3.method10(), var4.method1());
         var3.method10().method2(var5);

         for (AdvancedOptionWidget var7 : var5.method11(AdvancedOptionWidget.class)) {
            Framework7Extension var8 = var7.method14();
            if (var8.method2(Framework.field1) && var8 == var2) {
               if (var7.getOption() instanceof ToggleOption var9) {
                  var9.method3(true);
               }

               var5.method19(var7);
               break;
            }
         }
      } else {
         var3.method10().method2(new FeatureSettingsWidget(var3.method10(), var2));
      }

      if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new MainMenuButton(var3)));
      } else {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var3));
      }

      var3.method2(0);
      return true;
   }

   @Override
   public void method4(String var1) {
      this.field1.method3(var1).flatMap(var0 -> var0.method3(Framework.field6)).ifPresent(var0 -> {
         var0.setEnabled(false);
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   @Generated
   public HudEditorStateMachine(HudElementRegistryImpl var1) {
      this.field1 = var1;
   }
}
