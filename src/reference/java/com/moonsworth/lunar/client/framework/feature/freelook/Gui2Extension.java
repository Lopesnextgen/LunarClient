package com.moonsworth.lunar.client.framework.feature.freelook;

import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   THIRD("thirdPerson", 1),
   FORWARD("forward", 2),
   FIRST("firstPerson", 0);

   private String id;
   private int perspective;

   public String id() {
      return WordUtils.capitalize(this.method1(this.id, new Object[0]));
   }

   @Generated
   Gui2Extension(String text, int value) {
      this.id = text;
      this.perspective = value;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public int getPerspective() {
      return this.perspective;
   }
}
