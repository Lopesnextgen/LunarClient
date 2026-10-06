package com.moonsworth.lunar.client.framework;

import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.List;

public class DevFeatureList {
   public DevFeatureList() {
   }

   public static List<String> method1() {
      return List.of();
   }

   public static void method2() {
      if (LunarBuildData.field4) {
         ;
      }
   }
}
