package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.highlight.Highlight;
import java.io.File;
import lombok.Generated;

public class EventRunDirectory extends Highlight {
   private final String field1;
   private final File field2;

   @Generated
   public EventRunDirectory(String text, File file) {
      this.field1 = text;
      this.field2 = file;
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
