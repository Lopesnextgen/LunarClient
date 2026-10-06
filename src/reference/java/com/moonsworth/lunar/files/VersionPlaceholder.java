package com.moonsworth.lunar.files;

import com.moonsworth.lunar.files.mixin.VersionInfo;

public class VersionPlaceholder {
   private final String field1;
   private final String field2;
   public static final String field3 = "mcVer";
   public static final String field4 = "mcId";
   public static final String field5 = "mcpVer";
   public static final String field6 = "parchmentVer";
   public static final String field7 = "parchmentMcVer";
   public static final String field8 = "computeFrames";

   public VersionPlaceholder(String text, String text2) {
      this.field1 = text;
      this.field2 = text2;
   }

   public static VersionPlaceholder method1(VersionInfo files30) {
      return new VersionPlaceholder("mcVer", files30.getId());
   }

   public String key() {
      return this.field1;
   }

   public String value() {
      return this.field2;
   }
}
