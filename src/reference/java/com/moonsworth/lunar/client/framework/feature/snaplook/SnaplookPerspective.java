package com.moonsworth.lunar.client.framework.feature.snaplook;

import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum SnaplookPerspective implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   THIRD("thirdPerson", 1),
   FORWARD("forward", 2);

   private String id;
   private int perspective;

   public String id() {
      return WordUtils.capitalize(this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]));
   }

   @Generated
   SnaplookPerspective(String text3, int number4) {
      this.id = text3;
      this.perspective = number4;
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
