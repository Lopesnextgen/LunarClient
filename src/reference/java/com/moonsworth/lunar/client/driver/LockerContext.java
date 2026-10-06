package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.DriverContext;
import javax.annotation.Nullable;
import lombok.Generated;

public class LockerContext extends DriverContext {
   private final LockerSection field1;
   @Nullable
   private final String field2;
   private final boolean field3;

   @Override
   protected void method2(JsonObject json1) {
      json1.addProperty("type", this.field1.toString());
      if (this.field2 != null) {
         json1.addProperty("subType", this.field2);
      }

      json1.addProperty("hideSidebar", this.field3);
   }

   @Generated
   public LockerContext(LockerSection lockerSection, @Nullable String text2, boolean flag) {
      this.field1 = lockerSection;
      this.field2 = text2;
      this.field3 = flag;
   }
}
