package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;

@VersionGate(min = 33)
final class HighlightConfigMigrations {
   private static final List<HighlightMigrationStep> field1 = List.of(new HighlightHandler());
   static final int field2 = field1.size();

   HighlightConfigMigrations() {
   }

   static void method1(JsonObject json0, int number1) {
      Data2 data22 = MarkerModel.method1().OCRCCHICRRIROCIHCOROROHCIRCICO();
      double value3 = HighlightButton.method6();
      HighlightMigrationContext highlight35 = new HighlightMigrationContext(json0, (float)(data22.HHHCHORHIHRCOHIOICICICHCRRICCI() * value3), (float)(data22.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() * value3));

      for (int index6 = number1; index6 < field2; index6++) {
         field1.get(index6).method1(highlight35);
      }
   }
}
