package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.profile.importer.ExternalProfileLocator;

public class MigrationContext extends DriverContext {
   private final boolean field1;

   public MigrationContext() {
      this(false);
   }

   public MigrationContext(boolean flag) {
      this.field1 = flag;
   }

   @Override
   protected void method2(JsonObject json1) {
      super.method2(json1);
      json1.addProperty("migration", this.field1);
      json1.addProperty("featherDetected", ExternalProfileLocator.isFeatherDetected());
      json1.addProperty("badlionDetected", ExternalProfileLocator.isBadlionDetected());
   }
}
