package com.moonsworth.lunar.client.replay.export;

import lombok.Generated;

public enum VideoResolution implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
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
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   VideoResolution(String text3, int number4, int number5) {
      this.id = text3;
      this.width = number4;
      this.height = number5;
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
