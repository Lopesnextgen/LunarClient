package com.moonsworth.lunar.client.render.shader;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeImplementation.Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.render.shader.BundledShaderLoader;
import com.moonsworth.lunar.client.render.shader.BlitPostEffect;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class ShaderInjectRegistry implements LoadableHandler {
   public static final String field1 = "lunar-shaders";
   public static final String field2 = "lunar/shader-inject/";
   private static final String field3 = "#define LUNAR_HUD_SHADER_COMPAT";
   private static final String field4 = "#define MH_VERSION ";
   private final Map<String, ShaderInjectedPipeline> field5 = new HashMap<>();
   private Set<HudShaderTarget> field6 = EnumSet.noneOf(HudShaderTarget.class);
   private com.moonsworth.lunar.client.render.shader.LunarPostEffect field7 = null;

   @Nullable
   public ShaderInjectedPipeline method1(String var1, ShaderPipeline var2) {
      if (this.field5.containsKey(var1)) {
         return null;
      }

      ShaderInjectedPipeline var3 = ShaderInjectedPipeline.method9(var1, var2);
      this.field5.put(var1, var3);
      var3.method2();
      return var3;
   }

   public com.moonsworth.lunar.client.render.shader.LunarPostEffect method2(String var1, BundledShaderLoader var2) {
      ShaderInjectedPipeline var3 = this.method1(var1, var2);
      if (var3 == null) {
         throw new RuntimeException("Tried to register Post Effect but a shader inject under that ID already exists! (" + var1 + ")");
      } else {
         return new com.moonsworth.lunar.client.render.shader.LunarPostEffect(var1, var3);
      }
   }

   public void method3(String var1) {
      this.field5.remove(var1);
   }

   public ShaderInjectedPipeline method4(String var1) {
      return this.field5.get(var1);
   }

   public void method5(AbstractRenderContext var1, Bridge3_24 var2, Bridge3_24 var3) {
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         var2.method5(var3, false);
      } else {
         if (this.field7 == null) {
            this.field7 = this.method2("blit", new BlitPostEffect());
         }

         this.field7.method3(var1, var2, var3, var0 -> {});
      }
   }

   public boolean method6(HudShaderTarget var1) {
      return this.field6.contains(var1);
   }

   public void method7(String var1) {
      Set var2 = this.field6;
      this.field6 = this.method8(var1);

      for (HudShaderTarget var6 : HudShaderTarget.values()) {
         boolean var7 = this.field6.contains(var6);
         if (var2.contains(var6) != var7) {
            Framework7Extension var8 = var6.getFeature();
            if (var8 != null) {
               Alert2 var9 = (Alert2)var8.method1(Framework.field4);
               if (var9 != null) {
                  if (var7) {
                     var9.method4(var8, null);
                  } else {
                     var9.method5(var8);
                  }
               }
            }
         }
      }
   }

   private Set<HudShaderTarget> method8(String var1) {
      EnumSet var2 = EnumSet.noneOf(HudShaderTarget.class);
      if (var1.contains("#define MH_VERSION ")) {
         var2.add(HudShaderTarget.BOSSBAR_MOD);
      }

      int var3 = var1.indexOf("#define LUNAR_HUD_SHADER_COMPAT");

      while (var3 != -1) {
         int var4 = var3 + "#define LUNAR_HUD_SHADER_COMPAT".length();
         String var5 = this.method9(var1, var4);
         HudShaderTarget var6 = var5.isEmpty() ? null : HudShaderTarget.fromDefineSuffix(var5);
         if (var6 != null) {
            var2.add(var6);
         } else {
            var2.addAll(EnumSet.allOf(HudShaderTarget.class));
         }

         var3 = var1.indexOf("#define LUNAR_HUD_SHADER_COMPAT", var4);
      }

      return var2;
   }

   private String method9(String var1, int var2) {
      int var3;
      for (var3 = var2; var3 < var1.length(); var3++) {
         char var4 = var1.charAt(var3);
         if (var4 != '_' && !Character.isLetterOrDigit(var4)) {
            break;
         }
      }

      return var1.substring(var2, var3);
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
   }

   public String method10(ResourceLocationBridge var1) {
      if (!method14(var1)) {
         return null;
      }

      String var2 = method17(var1.bridge$getPath());
      if (var2 == null) {
         return null;
      }

      boolean var3 = var2.startsWith("vsh/");
      String var4 = var2.substring(4);
      ShaderInjectedPipeline var5 = this.method4(var4);
      return var5 == null ? null : var5.method7(var3);
   }

   public void method11(Extension var1) {
      for (ShaderInjectedPipeline var3 : this.field5.values()) {
         var3.method8(var1);
      }
   }

   public static boolean method12(ResourceLocationBridge var0) {
      return method13(var0.bridge$getPath());
   }

   public static boolean method13(String var0) {
      return var0.startsWith("lunar/shader-inject/");
   }

   public static boolean method14(ResourceLocationBridge var0) {
      return "lunar-shaders".equals(var0.bridge$getDomain());
   }

   public static JsonObject method15(ResourceLocationBridge var0, boolean var1) {
      String var2 = method17(var0.bridge$getPath());
      if (var2 == null) {
         return null;
      }

      boolean var3 = var2.endsWith(".json");
      if (!var3 && !var1) {
         return null;
      }

      String var4 = var2;
      if (var3) {
         var4 = var4.substring(0, var4.length() - 5);
      }

      ShaderInjectedPipeline var5 = ThreadModuleDump63.method4().method99().method4(var4);
      return var5 != null ? var5.method3() : null;
   }

   public static Supplier<InputStream> method16(ResourceLocationBridge var0) {
      return () -> {
         if (ThreadModuleDump63.method4() == null) {
            return InputStream.nullInputStream();
         }

         JsonObject var1 = method15(var0, false);
         if (var1 != null) {
            return new ByteArrayInputStream(ThreadModuleDump48.field22.toJson(var1).getBytes(StandardCharsets.UTF_8));
         }

         String var2 = var0.bridge$getPath();
         if (!var2.endsWith(".vsh") && !var2.endsWith(".fsh")) {
            return InputStream.nullInputStream();
         }

         boolean var3 = var2.endsWith(".vsh");
         String var4 = method17(var2);
         if (var4 == null) {
            return InputStream.nullInputStream();
         }

         var4 = var4.substring(4, var4.length() - 4);
         ShaderInjectedPipeline var5 = ThreadModuleDump63.method4().method99().method4(var4);
         return var5 != null ? new ByteArrayInputStream(var5.method7(var3).getBytes(StandardCharsets.UTF_8)) : InputStream.nullInputStream();
      };
   }

   private static String method17(String var0) {
      int var1 = var0.lastIndexOf("lunar/shader-inject/");
      return var1 == -1 ? null : var0.substring(var1 + "lunar/shader-inject/".length());
   }
}
