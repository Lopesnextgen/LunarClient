package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   p720("720p", 1280, 720),
   p1080("1080p", 1920, 1080),
   p1440("1440p", 2560, 1440),
   p2160("4k", 3840, 2160),
   CUSTOM("custom", 0, 0);

   private final String id;
   private final int width;
   private final int height;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension(String text, int value, int value2) {
      this.id = text;
      this.width = value;
      this.height = value2;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public int getWidth() {
      return this.width;
   }

   @Generated
   public int getHeight() {
      return this.height;
   }
}
