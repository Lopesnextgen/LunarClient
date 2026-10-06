package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6Task;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import java.util.Map;
import java.util.Set;

class Fishing5 {
   private final Fishing4 field1;
   private final JsonDeserializerIterator$Data field2;

   Fishing5(Fishing4 var1, JsonDeserializerIterator$Data var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   Set<String> method1() {
      return this.field1.method16();
   }

   Map<String, Set<String>> method2() {
      return this.field1.method18();
   }

   void method3(String var1, String var2) {
      Map var3 = this.field1.method15();
      ModifierKeybindOption var4 = (ModifierKeybindOption)var3.get(var1);
      if (var4 != null) {
         ModifierKeybindOption var5 = (ModifierKeybindOption)var3.get(var2);
         if (var5 == null || var5.get().method8() == KeyCode.KEY_NONE) {
            this.field1.method7(var2).method8(var4.get());
            var3.remove(var1);
            var4.remove();
            GuiRewindhandlers6Task.method2(var4);
         }
      }
   }

   public Fishing4 method4() {
      return this.field1;
   }

   public JsonDeserializerIterator$Data method5() {
      return this.field2;
   }
}
