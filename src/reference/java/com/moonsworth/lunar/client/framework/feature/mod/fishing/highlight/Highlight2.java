package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;

@Annotation2(min = 33)
final class Highlight2 {
   private static final List<Highlight> field1 = List.of(new HighlightHandler());
   static final int field2 = field1.size();

   static void method1(JsonObject var0, int var1) {
      Data2 var2 = MarkerModel.method1().method5();
      double var3 = Highlight5.method6();
      Highlight3 var5 = new Highlight3(var0, (float)(var2.HHHCHORHIHRCOHIOICICICHCRRICCI() * var3), (float)(var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() * var3));

      for (int var6 = var1; var6 < field2; var6++) {
         field1.get(var6).method1(var5);
      }
   }
}
