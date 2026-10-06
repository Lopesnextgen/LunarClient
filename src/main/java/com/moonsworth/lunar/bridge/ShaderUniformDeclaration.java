package com.moonsworth.lunar.bridge;

public class ShaderUniformDeclaration {
   private final String field1;
   private final GlslUniformType field2;

   public ShaderUniformDeclaration(String text, GlslUniformType bridgetype_152) {
      this.field1 = text;
      this.field2 = bridgetype_152;
   }

   public String name() {
      return this.field1;
   }

   public GlslUniformType method1() {
      return this.field2;
   }
}
