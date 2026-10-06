package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public abstract class ShaderKey {
   private final String field1;

   public static ShaderKey.Data3 method1(String var0) {
      return new ShaderKey.Data3(var0);
   }

   public static ShaderKey method2(String var0) {
      int var1 = var0.indexOf(":");
      return var1 != -1 && var1 + 1 < var0.length()
         ? method3(ResourceLocationBridge.create("lunar-jit", var0.substring(var1 + 1)))
         : method3(ResourceLocationBridge.create("lunar-jit", var0));
   }

   public static ShaderKey method3(ResourceLocationBridge var0) {
      return new ShaderKey.Data(var0);
   }

   public static ShaderKey method4(String var0) {
      return method6(ResourceLocationBridge.method1(var0, "lunar"));
   }

   public abstract String method5();

   public static ShaderKey method6(ResourceLocationBridge var0) {
      return new ShaderKey.Data2(var0);
   }

   public boolean method7() {
      return false;
   }

   public boolean method8() {
      return false;
   }

   @Override
   public String toString() {
      return "CloakShaderKey(" + this.field1 + ")";
   }

   public static ShaderKey method9(String var0) {
      int var1 = var0.indexOf("_");
      if (var1 == -1) {
         throw new RuntimeException("Invalid Cloak Shader key: '" + var0 + "'!");
      }

      String var2 = var0.substring(0, var1);
      String var3 = var0.substring(var1 + 1);

      return switch (var2) {
         case "static" -> method1(var3);
         case "dev" -> method4(var3);
         case "jit" -> method2(var3);
         default -> throw new RuntimeException("Unknown Cloak Shader key type: '" + var2 + "'!");
      };
   }

   @Generated
   private ShaderKey(String var1) {
      this.field1 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ShaderKey var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         String var3 = this.getKey();
         String var4 = var2.getKey();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof ShaderKey;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      String var3 = this.getKey();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   public String getKey() {
      return this.field1;
   }

   public static class Data extends ShaderKey {
      private final ResourceLocationBridge field2;

      private Data(ResourceLocationBridge var1) {
         super("jit_" + var1.bridge$getPath());
         this.field2 = var1;
      }

      @Override
      public String method5() {
         return this.field2.bridge$getPath();
      }

      @Override
      public boolean method7() {
         return true;
      }

      @Generated
      public ResourceLocationBridge getLocation() {
         return this.field2;
      }
   }

   public static class Data2 extends ShaderKey {
      private final ResourceLocationBridge field2;

      private Data2(ResourceLocationBridge var1) {
         super("dev_" + var1.bridge$getPath());
         this.field2 = var1;
      }

      @Override
      public String method5() {
         return this.field2.bridge$getPath();
      }

      @Override
      public boolean method8() {
         return true;
      }

      @Generated
      public ResourceLocationBridge getLocation() {
         return this.field2;
      }
   }

   public static class Data3 extends ShaderKey {
      private final String field2;

      private Data3(String var1) {
         super("static_" + var1);
         this.field2 = var1;
      }

      @Override
      public String method5() {
         return this.field2;
      }

      @Generated
      public String method2() {
         return this.field2;
      }
   }
}
