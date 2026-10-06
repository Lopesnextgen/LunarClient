package com.moonsworth.lunar.client.driver.bridge;

import com.google.common.collect.Iterables;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;

public class ColorBridge implements DriverGuiExtension, GuiIterator.Extension {
   public ColorBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method49().method11();
   }

   @CallbackJS("add")
   public static void method2(JsonObject json0) {
      if (Client.method109().method49().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size() >= 12) {
         Client.method109()
            .method49()
            .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
            .remove(Iterables.getFirst(Client.method109().method49().IIORHHIRHIORHRCCCOICCRCHRRCCRH(), null));
      }

      Client.method109().method49().method4(method4(json0));
      Client.method109().method49().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      Client.method109().method49().method10();
   }

   @CallbackJS("remove")
   public static void method3(JsonObject json0) {
      Client.method109().method49().IIORHHIRHIORHRCCCOICCRCHRRCCRH().removeIf(arg1 -> arg1.method2(method4(json0)));
      Client.method109().method49().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      Client.method109().method49().method10();
   }

   private static ColorOption method4(JsonObject json0) {
      ColorOption lightingextension42221 = (ColorOption)((Data)OptionFactory.method8("color").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1)).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      if (json0.has("chroma")) {
         JsonObject json2 = json0.getAsJsonObject("chroma");
         if (json2.has("chroma")) {
            lightingextension42221.method19().HCHHRHHCRIIORRRICOOCCOCHIRRRRR(json2.get("chroma")).ifPresent(arg1x -> lightingextension42221.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(arg1x));
         }

         if (json2.has("chromaType")) {
            lightingextension42221.method23().HCHHRHHCRIIORRRICOOCCOCHIRRRRR(json2.get("chromaType")).ifPresent(arg1x -> lightingextension42221.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC(arg1x));
         }

         if (json2.has("chromaSpeed")) {
            lightingextension42221.method21().HCHHRHHCRIIORRRICOOCCOCHIRRRRR(json2.get("chromaSpeed")).ifPresent(arg1x -> lightingextension42221.method21().method1(arg1x));
         }
      }

      if (json0.has("hue")) {
         lightingextension42221.CCOIHIIHIIOOIHCIORRCHCIRHIRCOR(json0.get("hue").getAsFloat());
      }

      if (json0.has("brightness")) {
         lightingextension42221.HCRCHORRHRRIHICOIIHCHRRHCICCRI(json0.get("brightness").getAsFloat());
      }

      if (json0.has("saturation")) {
         lightingextension42221.OROIROOOOOORHCOHCOHCCRHOOOCOIC(json0.get("saturation").getAsFloat());
      }

      if (json0.has("opacity")) {
         lightingextension42221.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(json0.get("opacity").getAsFloat());
      }

      if (json0.has("hex")) {
         lightingextension42221.method1(ColorOption.method24(json0.get("hex").getAsString()));
      }

      return lightingextension42221;
   }
}
