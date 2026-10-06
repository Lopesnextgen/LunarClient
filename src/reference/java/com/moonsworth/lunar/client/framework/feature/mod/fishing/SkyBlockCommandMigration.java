package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.listener.KeybindOptionListener;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import java.util.Map;
import java.util.Set;

class SkyBlockCommandMigration {
   private final SkyBlockCommandKeybinds field1;
   private final SkyBlockCommandConfig field2;

   SkyBlockCommandMigration(SkyBlockCommandKeybinds fishing41, SkyBlockCommandConfig jsondeserializeriterator$data2) {
      this.field1 = fishing41;
      this.field2 = jsondeserializeriterator$data2;
   }

   Set<String> method1() {
      return this.field1.method16();
   }

   Map<String, Set<String>> method2() {
      return this.field1.method18();
   }

   void method3(String text1, String text2) {
      Map map3 = this.field1.method15();
      ModifierKeybindOption lightingextension491334 = (ModifierKeybindOption)map3.get(text1);
      if (lightingextension491334 != null) {
         ModifierKeybindOption lightingextension491335 = (ModifierKeybindOption)map3.get(text2);
         if (lightingextension491335 == null || ((KeyBind)lightingextension491335.get()).method8() == KeyCode.KEY_NONE) {
            this.field1.method7(text2).method8((KeyBind)lightingextension491334.get());
            map3.remove(text1);
            lightingextension491334.remove();
            KeybindOptionListener.method2(lightingextension491334);
         }
      }
   }

   public SkyBlockCommandKeybinds method4() {
      return this.field1;
   }

   public SkyBlockCommandConfig method5() {
      return this.field2;
   }
}
