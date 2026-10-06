package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ShaderUniform {
   public static ShaderUniform field1 = method4().method1(GlslUniformType.VEC3, "Position").method2(GlslUniformType.VEC2, "uv").method3();
   public static ShaderUniform field2 = method4()
      .method1(GlslUniformType.VEC3, "Position")
      .method1(GlslUniformType.VEC3, "Normal")
      .method1(GlslUniformType.VEC2, "UV0")
      .method2(GlslUniformType.VEC2, "uv")
      .method2(GlslUniformType.VEC3, "worldPos")
      .method2(GlslUniformType.VEC3, "normal")
      .method3();
   public static ShaderUniform field3 = method4()
      .method1(GlslUniformType.VEC3, "Position")
      .method1(GlslUniformType.VEC3, "Normal")
      .method1(GlslUniformType.VEC2, "UV0")
      .method2(GlslUniformType.VEC2, "uv")
      .method2(GlslUniformType.VEC3, "worldPos")
      .method2(GlslUniformType.VEC3, "normal")
      .method3();
   private final List<ShaderUniform.Data> field4;
   private final List<ShaderUniform.Data> field5;

   private ShaderUniform(List<ShaderUniform.Data> var1, List<ShaderUniform.Data> var2) {
      this.field4 = var1;
      this.field5 = var2;
   }

   private ShaderUniform() {
      this(List.of(), List.of());
   }

   public void method1(BiConsumer<GlslUniformType, String> var1) {
      this.field4.forEach(var1x -> var1.accept(var1x.field1, var1x.field2));
   }

   public void method2(BiConsumer<GlslUniformType, String> var1) {
      this.field5.forEach(var1x -> var1.accept(var1x.field1, var1x.field2));
   }

   public boolean method3() {
      return this == field2 || this == field3;
   }

   public static ShaderUniform.Data2 method4() {
      return new ShaderUniform.Data2();
   }

   private class Data {
      private final GlslUniformType field1;
      private final String field2;

      private Data(GlslUniformType var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public GlslUniformType method1() {
         return this.field1;
      }

      public String name() {
         return this.field2;
      }
   }

   public static class Data2 {
      private final List<ShaderUniform.Data> field1 = new ArrayList<>();
      private final List<ShaderUniform.Data> field2 = new ArrayList<>();

      private Data2() {
      }

      public ShaderUniform.Data2 method1(GlslUniformType var1, String var2) {
         this.field1.add(new ShaderUniform.Data(var1, var2));
         return this;
      }

      public ShaderUniform.Data2 method2(GlslUniformType var1, String var2) {
         this.field2.add(new ShaderUniform.Data(var1, var2));
         return this;
      }

      public ShaderUniform method3() {
         ShaderUniform var1 = new ShaderUniform(List.copyOf(this.field1), List.copyOf(this.field2));
         this.field1.clear();
         this.field2.clear();
         return var1;
      }
   }
}
