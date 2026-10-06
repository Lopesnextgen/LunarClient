package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
class HighlightMigrationContext {
   private final JsonObject field1;
   private final float field2;
   private final float field3;

   HighlightMigrationContext(JsonObject json1, float value, float value2) {
      this.field1 = json1;
      this.field2 = value;
      this.field3 = value2;
   }

   public JsonObject method1() {
      return this.field1;
   }

   public float method2() {
      return this.field2;
   }

   public float method3() {
      return this.field3;
   }
}
