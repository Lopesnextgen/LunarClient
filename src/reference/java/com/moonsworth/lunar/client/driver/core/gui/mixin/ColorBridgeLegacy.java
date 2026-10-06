package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.common.collect.Iterables;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;

public class ColorBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method49().method11();
   }

   @CallbackJS("add")
   public static void method2(JsonObject var0) {
      if (Client.method109().method49().method3().size() >= 12) {
         Client.method109()
            .method49()
            .method3()
            .remove(Iterables.getFirst(Client.method109().method49().method3(), null));
      }

      Client.method109().method49().method4(method4(var0));
      Client.method109().method49().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      Client.method109().method49().method10();
   }

   @CallbackJS("remove")
   public static void method3(JsonObject var0) {
      Client.method109().method49().method3().removeIf(var1 -> var1.method2(method4(var0)));
      Client.method109().method49().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      Client.method109().method49().method10();
   }

   private static ColorOption method4(JsonObject var0) {
      ColorOption var1 = (ColorOption)((Data)OptionFactory.method8("color").method4(-1)).method31();
      if (var0.has("chroma")) {
         JsonObject var2 = var0.getAsJsonObject("chroma");
         if (var2.has("chroma")) {
            var1.method19().method14(var2.get("chroma")).ifPresent(var1x -> var1.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(var1x));
         }

         if (var2.has("chromaType")) {
            var1.method23().method14(var2.get("chromaType")).ifPresent(var1x -> var1.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC(var1x));
         }

         if (var2.has("chromaSpeed")) {
            var1.method21().method14(var2.get("chromaSpeed")).ifPresent(var1x -> var1.method21().method1(var1x));
         }
      }

      if (var0.has("hue")) {
         var1.CCOIHIIHIIOOIHCIORRCHCIRHIRCOR(var0.get("hue").getAsFloat());
      }

      if (var0.has("brightness")) {
         var1.HCRCHORRHRRIHICOIIHCHRRHCICCRI(var0.get("brightness").getAsFloat());
      }

      if (var0.has("saturation")) {
         var1.OROIROOOOOORHCOHCOHCCRHOOOCOIC(var0.get("saturation").getAsFloat());
      }

      if (var0.has("opacity")) {
         var1.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(var0.get("opacity").getAsFloat());
      }

      if (var0.has("hex")) {
         var1.method1(ColorOption.method24(var0.get("hex").getAsString()));
      }

      return var1;
   }
}
