package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.profile.importer.ExternalProfileLocator;

public class MigrationContextLegacy extends DriverContextLegacy {
   private final boolean field1;

   public MigrationContextLegacy() {
      this(false);
   }

   public MigrationContextLegacy(boolean var1) {
      this.field1 = var1;
   }

   @Override
   protected void method2(JsonObject var1) {
      super.method2(var1);
      var1.addProperty("migration", this.field1);
      var1.addProperty("featherDetected", ExternalProfileLocator.method4());
      var1.addProperty("badlionDetected", ExternalProfileLocator.method3());
   }
}
