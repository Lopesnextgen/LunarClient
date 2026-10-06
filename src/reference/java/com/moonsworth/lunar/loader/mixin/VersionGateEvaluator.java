package com.moonsworth.lunar.loader.mixin;

import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.AnnotationNode;

public class VersionGateEvaluator {
   public VersionGateEvaluator() {
   }

   public static boolean method1(AnnotationNode annotation0, Config config1) {
      List list2 = annotation0.values;
      Config config3 = null;
      Config config4 = null;
      ArrayList list5 = null;
      boolean flag6 = false;

      for (byte index7 = 0; index7 < list2.size(); index7 += 2) {
         String text8 = list2.get(index7).toString();
         Object obj9 = list2.get(index7 + 1);
         if ("max".equals(text8)) {
            Integer number18 = (Integer)obj9;
            if (number18 != -1) {
               config4 = (Config)Config.method2(number18).orElse(null);
            }
         } else if ("min".equals(text8)) {
            Integer number17 = (Integer)obj9;
            if (number17 != -1) {
               config3 = (Config)Config.method2(number17).orElse(null);
            }
         } else if ("value".equals(text8) && obj9 instanceof List list10) {
            list5 = new ArrayList(list2.size());

            for (Object obj12 : list10) {
               if (obj12 instanceof Integer number13) {
                  Config.method2(number13).ifPresent(list5::add);
               } else if (obj12 instanceof String text14) {
                  Config.get(text14).ifPresent(list5::add);
               }
            }
         } else if ("inverted".equals(text8)) {
            flag6 = (Boolean)obj9;
         } else if ("onReturn".equals(text8) && (Boolean)obj9) {
            return true;
         }
      }

      if (list5 != null) {
         return flag6 != list5.contains(config1);
      }

      if (flag6) {
         boolean flag16 = false;
         if (config3 != null) {
            flag16 |= config1.method5(config3);
         }

         if (config4 != null) {
            flag16 |= config1.method3(config4);
         }

         return flag16;
      } else {
         boolean flag15 = true;
         if (config3 != null) {
            flag15 &= config1.method4(config3);
         }

         if (config4 != null) {
            flag15 &= config4.method4(config1);
         }

         return flag15;
      }
   }
}
