package com.moonsworth.lunar.client.config.option.trait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;

public class TraitDebugFormatter {
   public TraitDebugFormatter() {
   }

   public static String method1(TraitContainer lightoverlay2extension20) {
      if (lightoverlay2extension20 == null) {
         return "Container is null";
      }

      StringBuilder builder1 = new StringBuilder();
      builder1.append("TraitContainer [size=").append(lightoverlay2extension20.size()).append("]\n");
      lightoverlay2extension20.stream().sorted((arg0x, arg1x) -> Integer.compare(arg0x.type(), arg1x.type())).forEach(arg2 -> {
         String text3 = lightoverlay2extension20.method1().method2(arg2.type());
         builder1.append("  - ").append(text3).append(" (").append(arg2.type()).append("): ");
         builder1.append(arg2.value() != null ? arg2.value().toString() : "null").append("\n");
      });
      return builder1.toString();
   }

   public static String method2(Lightoverlay lightoverlay0, TraitType<?> lightoverlay91) {
      if (lightoverlay0 != null && lightoverlay91 != null) {
         StringBuilder builder2 = new StringBuilder();
         builder2.append("TraitType: ").append(lightoverlay91).append("\n");
         builder2.append("  - ID: ").append(lightoverlay91.getId()).append("\n");
         builder2.append("  - Name: ").append(lightoverlay0.method2(lightoverlay91.getId())).append("\n");
         Map map3 = lightoverlay0.method4(lightoverlay91.getId());
         if (map3 != null && !map3.isEmpty()) {
            builder2.append("  - Conditions:\n");

            for (Entry entry5 : map3.entrySet()) {
               builder2.append("    - ").append(entry5.getKey()).append(": ");
               List list6 = Arrays.stream((int[])entry5.getValue()).mapToObj(lightoverlay0::method2).collect(Collectors.toList());
               builder2.append(String.join(", ", list6)).append("\n");
            }
         } else {
            builder2.append("  - No conditions\n");
         }

         return builder2.toString();
      } else {
         return "Registry or TraitType is null";
      }
   }

   public static List<String> method3(TraitContainer lightoverlay2extension20) {
      ArrayList list1 = new ArrayList();
      Lightoverlay lightoverlay2 = lightoverlay2extension20.method1();

      for (Trait lightoverlay84 : lightoverlay2extension20) {
         int number5 = lightoverlay84.type();
         String text6 = lightoverlay2.method2(number5);
         Map map7 = lightoverlay2.method4(number5);
         if (map7 != null) {
            int[] items8 = map7.getOrDefault(LightoverlayType.REQUIRES, new int[0]);

            for (int index12 : items8) {
               TraitType lightoverlay913 = lightoverlay2.method3(index12);
               if (lightoverlay913 != null && !lightoverlay2extension20.method2(lightoverlay913)) {
                  String text14 = lightoverlay2.method2(index12);
                  list1.add(text6 + " requires " + text14 + " but it is not present");
               }
            }

            int[] items16 = map7.getOrDefault(LightoverlayType.CONFLICTS, new int[0]);

            for (int index20 : items16) {
               TraitType lightoverlay921 = lightoverlay2.method3(index20);
               if (lightoverlay921 != null && lightoverlay2extension20.method2(lightoverlay921)) {
                  String text15 = lightoverlay2.method2(index20);
                  list1.add(text6 + " conflicts with " + text15 + " but both are present");
               }
            }
         }
      }

      return list1;
   }

   public static String method4(Lightoverlay lightoverlay0, TraitType<?> lightoverlay91) {
      if (lightoverlay0 != null && lightoverlay91 != null) {
         int number2 = lightoverlay91.getId();
         String text3 = lightoverlay0.method2(number2);
         StringBuilder builder4 = new StringBuilder();
         builder4.append("Related conditions for ").append(text3).append(":\n");
         boolean flag5 = false;

         for (int index6 = 0; index6 <= lightoverlay0.method1(); index6++) {
            if (index6 != number2) {
               Map map7 = lightoverlay0.method4(index6);
               if (map7 != null) {
                  boolean flag8 = false;
                  StringBuilder builder9 = new StringBuilder();
                  String text10 = lightoverlay0.method2(index6);
                  builder9.append("  - ").append(text10).append(":\n");

                  for (Entry entry12 : map7.entrySet()) {
                     if (Arrays.stream((int[])entry12.getValue()).anyMatch(arg1x -> arg1x == number2)) {
                        builder9.append("    - ").append(entry12.getKey()).append(" ").append(text3).append("\n");
                        flag8 = true;
                        flag5 = true;
                     }
                  }

                  if (flag8) {
                     builder4.append(builder9);
                  }
               }
            }
         }

         if (!flag5) {
            builder4.append("  No other trait types have conditions related to this type\n");
         }

         return builder4.toString();
      } else {
         return "Registry or TraitType is null";
      }
   }

   public static String method5(TraitContainer lightoverlay2extension20, TraitContainer lightoverlay2extension21) {
      if (lightoverlay2extension20 != null && lightoverlay2extension21 != null) {
         StringBuilder builder2 = new StringBuilder();
         builder2.append("Comparing trait containers:\n");
         builder2.append("Traits only in first container:\n");
         boolean flag3 = false;

         for (Trait lightoverlay85 : lightoverlay2extension20) {
            TraitType lightoverlay96 = lightoverlay2extension20.method1().method3(lightoverlay85.type());
            if (lightoverlay96 != null && !lightoverlay2extension21.method2(lightoverlay96)) {
               String text7 = lightoverlay2extension20.method1().method2(lightoverlay85.type());
               builder2.append("  - ").append(text7).append(" (").append(lightoverlay85.type()).append(")\n");
               flag3 = true;
            }
         }

         if (!flag3) {
            builder2.append("  None\n");
         }

         builder2.append("Traits only in second container:\n");
         boolean flag12 = false;

         for (Trait lightoverlay815 : lightoverlay2extension21) {
            TraitType lightoverlay917 = lightoverlay2extension21.method1().method3(lightoverlay815.type());
            if (lightoverlay917 != null && !lightoverlay2extension20.method2(lightoverlay917)) {
               String text8 = lightoverlay2extension21.method1().method2(lightoverlay815.type());
               builder2.append("  - ").append(text8).append(" (").append(lightoverlay815.type()).append(")\n");
               flag12 = true;
            }
         }

         if (!flag12) {
            builder2.append("  None\n");
         }

         builder2.append("Traits with different values:\n");
         boolean flag14 = false;

         for (Trait lightoverlay818 : lightoverlay2extension20) {
            TraitType lightoverlay919 = lightoverlay2extension20.method1().method3(lightoverlay818.type());
            if (lightoverlay919 != null && lightoverlay2extension21.method2(lightoverlay919)) {
               Object obj9 = lightoverlay818.value();
               Object obj10 = lightoverlay2extension21.method1(lightoverlay919);
               if (obj9 == null && obj10 != null || obj9 != null && !obj9.equals(obj10)) {
                  String text11 = lightoverlay2extension20.method1().method2(lightoverlay818.type());
                  builder2.append("  - ").append(text11).append(" (").append(lightoverlay818.type()).append("): \n");
                  builder2.append("    First: ").append(obj9).append("\n");
                  builder2.append("    Second: ").append(obj10).append("\n");
                  flag14 = true;
               }
            }
         }

         if (!flag14) {
            builder2.append("  None\n");
         }

         return builder2.toString();
      } else {
         return "One or both containers are null";
      }
   }
}
