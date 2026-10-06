package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.Iterator;
import java.util.Set;
import org.jspecify.annotations.Nullable;

public class DevOptionBaker extends OptionBaker {
   public static final String field2 = "test";
   private static final int field3 = 20;
   private static final int field4 = 30;
   private int field5 = 0;

   public DevOptionBaker(@Nullable String text1) {
      super(text1);
      if (!"test".equals(text1) && LunarBuildData.field4) {
         throw new IllegalAccessError("DevOptionBaker can only be used in a development environment!");
      }
   }

   @Override
   protected void method4(SettingsNode lightinghandler21, Set<SettingsNode> set2) {
      Iterator iterator3 = lightinghandler21.getChildren().iterator();

      while (iterator3.hasNext()) {
         SettingsNode lightinghandler24 = (SettingsNode)iterator3.next();
         if (lightinghandler24.method4() || this.method1(lightinghandler24, set2)) {
            iterator3.remove();
            set2.add(lightinghandler24);
         }

         if (this.field5 > 30) {
            throw new StackOverflowError("Found option with hierarchy deeper than 30 for id: " + this.getId());
         }

         if (this.field5 > 20) {
            System.out.println("Depth detection: " + lightinghandler24.method3().getId());
         }

         this.field5++;
         this.method4(lightinghandler24, set2);
         this.field5--;
      }
   }
}
