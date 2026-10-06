package com.moonsworth.lunar.client.config.option.trait;

import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.TraitRegistry;

public interface Lightoverlay {
   int method1();

   String method2(int number1);

   TraitType<?> method3(int number1);

   @Nullable
   Map<LightoverlayType, int[]> method4(int number1);

   static Lightoverlay method5(int value, String[] items1, TraitType<?>[] items2, Map<LightoverlayType, int[]>[] items3) {
      return new LightoverlayHandler(value, items1, items2, items3);
   }

   static Lightoverlay method6(TraitRegistry lightoverlay60) {
      int number1 = lightoverlay60.size() - 1;
      String[] items2 = new String[lightoverlay60.size()];
      TraitType[] items3 = new TraitType[lightoverlay60.size()];
      Map[] items4 = new Map[lightoverlay60.size()];

      for (Entry entry6 : lightoverlay60.entrySet()) {
         int index7 = ((TraitType)entry6.getValue()).getId();
         items2[index7] = (String)entry6.getKey();
         items3[index7] = (TraitType)entry6.getValue();
         items4[index7] = (Map)lightoverlay60.method5().get(index7);
      }

      return new LightoverlayHandler(number1, items2, items3, items4);
   }
}
