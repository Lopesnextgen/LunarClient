package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.event.LunarEvent;
import java.io.File;
import lombok.Generated;

public class EventGameDirectory extends LunarEvent {
   private final String field1;
   private final File field2;

   @Generated
   public EventGameDirectory(String text, File file2) {
      this.field1 = text;
      this.field2 = file2;
   }

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public File method2() {
      return this.field2;
   }
}
