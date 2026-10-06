package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ShaderStage implements Bridge_23 {
   VERTEX("vertex", 35633, true, "vsh", "vert", "glsl"),
   FRAGMENT("fragment", 35632, true, "fsh", "frag", "glsl"),
   GEOMETRY("geometry", 36313, false, "gsh", "geom", "glsl"),
   CONTROL("control", 36488, false, "tesc", "glsl"),
   EVALUATION("evaluation", 36487, false, "tese", "glsl"),
   COMPUTE("compute", 37305, false, "csh", "comp", "glsl");

   private final String name;
   private final int glId;
   private final boolean supported;
   private final String[] extensions;

   ShaderStage(String text, int value, boolean flag, String... items) {
      this.name = text;
      this.glId = value;
      this.supported = flag;
      this.extensions = items;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   @Override
   public int getGlId() {
      return this.glId;
   }

   @Generated
   @Override
   public boolean isSupported() {
      return this.supported;
   }

   @Generated
   public String[] getExtensions() {
      return this.extensions;
   }
}
