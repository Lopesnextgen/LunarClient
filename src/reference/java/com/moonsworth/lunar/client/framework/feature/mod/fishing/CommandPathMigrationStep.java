package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.Set;

final class CommandPathMigrationStep implements SkyBlockCommandMigrationStep {
   CommandPathMigrationStep() {
   }

   @Override
   public void method1(SkyBlockCommandMigration fishing51) {
      for (SkyBlockCommand fishing_23 : fishing51.method5().method1()) {
         this.method2(fishing51, fishing_23);
      }
   }

   private void method2(SkyBlockCommandMigration fishing51, SkyBlockCommand fishing_22) {
      if (fishing_22.method7() != null) {
         String text3 = fishing_22.getPrettyName();
         String text4 = fishing_22.key();
         if (!text3.equals(text4)) {
            fishing51.method3(text3, text4);
            if (fishing51.method1().remove(text3)) {
               fishing51.method1().add(text4);
            }

            Set set5 = fishing51.method2().remove(text3);
            if (set5 != null) {
               fishing51.method2().put(text4, set5);

               for (String text7 : set5) {
                  fishing51.method3(text3 + ":" + text7, text4 + ":" + text7);
               }
            }
         }
      }

      if (fishing_22.method3() && !fishing_22.method4()) {
         for (SkyBlockCommand fishing_29 : fishing_22.method5()) {
            this.method2(fishing51, fishing_29);
         }
      }
   }
}
