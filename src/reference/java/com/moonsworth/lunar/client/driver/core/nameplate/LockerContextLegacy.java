package com.moonsworth.lunar.client.driver.core.nameplate;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import javax.annotation.Nullable;
import lombok.Generated;

public class LockerContextLegacy extends DriverContextLegacy {
   private final LockerSectionLegacy field1;
   @Nullable
   private final String field2;
   private final boolean field3;

   @Override
   protected void method2(JsonObject var1) {
      var1.addProperty("type", this.field1.toString());
      if (this.field2 != null) {
         var1.addProperty("subType", this.field2);
      }

      var1.addProperty("hideSidebar", this.field3);
   }

   @Generated
   public LockerContextLegacy(LockerSectionLegacy var1, @Nullable String var2, boolean flag) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = flag;
   }
}
