package com.moonsworth.lunar.client.replay.modsettings;

import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.TextOption;

public interface Highlight {
   default ClientOption method1(Object var1, String var2) {
      ClientOption var3;
      if (var1 instanceof Boolean) {
         var3 = ((ToggleOption.ToggleOptionBuilder)OptionFactory.method7(var2).OOOIROIIOCOOHICRIRHHHRROHHHHIO((Boolean)var1)).method31();
      } else if (var1 instanceof Integer var4) {
         var3 = ((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(var2).ORCRHOICOIHCRRIOHIHIROHOCRRIOO(var4))
               .method7(1700, 1900))
            .method31();
      } else if (var1 instanceof Double var5) {
         var3 = ((DoubleOption.Data)((DoubleOption.Data)OptionFactory.method1(var2).OIRHOOIICOCIOOHICRRRICORIHHIHC(var5))
               .method8(0.0, 100.0))
            .method31();
      } else {
         if (!(var1 instanceof String)) {
            throw new IllegalArgumentException("Default type " + var1.getClass() + " not supported.");
         }

         var3 = ((TextOption.Data)OptionFactory.method12(var2).method2((String)var1)).method31();
      }

      return var3;
   }

   ClientOption getOption();
}
