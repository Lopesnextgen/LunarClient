package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.client.render.jit.JitShaderResource;
import com.moonsworth.lunar.client.util.ThreadModuleDump94;
import com.moonsworth.lunar.client.util.alert.Alert6;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShaderCatalog {
   private static final Map<ShaderKey.Data3, String> field1 = new HashMap<>();
   private static final Map<ShaderKey.Data3, String> field2 = new HashMap<>();
   public static final ShaderKey field3 = method1("simple", ShaderSource.field1);
   public static final ShaderKey field4 = method1("positioned", ShaderSource.field2);
   public static final ShaderKey field5 = method1("positioned_proj", ShaderSource.field2);
   public static final ShaderKey field6 = method2("debug", ShaderSource.field3);
   public static final ShaderKey field7 = method2("template", ShaderSource.field4);

   private static ShaderKey method1(String var0, String var1) {
      ShaderKey.Data3 var2 = ShaderKey.method1(var0);
      field1.put(var2, var1);
      return var2;
   }

   private static ShaderKey method2(String var0, String var1) {
      ShaderKey.Data3 var2 = ShaderKey.method1(var0);
      field2.put(var2, var1);
      return var2;
   }

   public static String method3(ShaderKey.Data3 var0, JitShaderResource.Type var1, Alert6 var2) {
      String var3 = switch (var1) {
         case VERTEX -> (String)field1.get(var0);
         case FRAGMENT -> (String)field2.get(var0);
      };
      return var3 == null ? null : ThreadModuleDump94.method4(var3, var2, var1);
   }

   public static Set<ShaderKey.Data3> method4(JitShaderResource.Type var0) {
      return switch (var0) {
         case VERTEX -> field1.keySet();
         case FRAGMENT -> field2.keySet();
      };
   }

   private ShaderCatalog() {
   }
}
