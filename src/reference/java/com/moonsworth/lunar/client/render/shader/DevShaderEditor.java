package com.moonsworth.lunar.client.render.shader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.render.jit.JitResource;
import com.moonsworth.lunar.client.render.jit.JitShaderResource;
import com.moonsworth.lunar.client.render.jit.JitEmoteResource;
import com.moonsworth.lunar.client.cosmetics.gecko.VertexBuilder;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.render.shader.ShaderPackHelper;
import com.moonsworth.lunar.client.render.texture.NativeImageBuilder;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod.Data;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump94;
import com.moonsworth.lunar.client.util.alert.Alert6;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class DevShaderEditor implements LoadableHandler {
   private static final Map<String, GlslBuiltin> field1 = new HashMap<>(
      Arrays.stream(GlslBuiltin.values()).collect(Collectors.toMap(GlslBuiltin::getVarName, var0 -> (GlslBuiltin)var0))
   );
   private final Map<ResourceLocationBridge, String> field2 = new HashMap<>();

   public static GlslBuiltin method1(String var0) {
      return field1.get(var0);
   }

   public String method2(Alert6 var1) {
      return this.method4(var1.getFragmentShader(), JitShaderResource.Type.FRAGMENT, var1);
   }

   public String method3(Alert6 var1) {
      return this.method4(var1.getVertexShader(), JitShaderResource.Type.VERTEX, var1);
   }

   private String method4(ShaderKey var1, JitShaderResource.Type var2, Alert6 var3) {
      if (var1 instanceof ShaderKey.Data var4) {
         JitShaderResource var8 = ThreadModuleDump63.method4().method96().method2(var4.getLocation(), var2x -> new JitShaderResource(var2x, var2, var3));
         return var8 == null ? null : (String)var8.method1().orElse(null);
      } else if (var1 instanceof ShaderKey.Data2 var5) {
         String var7 = this.field2.computeIfAbsent(var5.getLocation(), var3x -> this.method5(var3x, var3, var2));
         return var7.isEmpty() ? null : var7;
      } else {
         return var1 instanceof ShaderKey.Data3 var6 ? ShaderCatalog.method3(var6, var2, var3) : null;
      }
   }

   private String method5(ResourceLocationBridge var1, Alert6 var2, JitShaderResource.Type var3) {
      IResourceBridge var4 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var1);
      if (var4 == null) {
         Slayer.method5("Unable to find dev shader '%s'!", new Object[]{var1});
         return "";
      } else {
         BufferedReader var5 = new BufferedReader(new InputStreamReader(var4.bridge$getInputStream()));
         String var6 = var5.lines().collect(Collectors.joining("\n"));
         return ThreadModuleDump94.method4(var6, var2, var3);
      }
   }

   public void reload() {
      this.field2.clear();
   }

   public void method6(ResourceLocationBridge var1) {
      this.field2.remove(var1);
   }

   public static Alert6 method7(ResourceLocationBridge var0, JsonObject var1, boolean var2) {
      if (var1.has("fragment") && var1.has("vertex")) {
         JsonObject var3 = var1.getAsJsonObject("fragment");
         JsonObject var4 = var1.getAsJsonObject("vertex");
         String var5 = var3.get("type").getAsString();
         String var6 = var3.get("value").getAsString();
         String var7 = var4.get("type").getAsString();
         String var8 = var4.get("value").getAsString();
         boolean var9 = var1.has("renderOnTick") && var1.get("renderOnTick").getAsBoolean();
         int var10 = var1.get("renderWidth").getAsInt();
         int var11 = var1.get("renderHeight").getAsInt();
         ArrayList var12 = new ArrayList();
         if (var1.has("uniforms")) {
            for (JsonElement var14 : var1.getAsJsonArray("uniforms")) {
               String var15 = var14.getAsString();
               GlslBuiltin var16 = method1(var15);
               if (var16 != null) {
                  var12.add(var16);
               }
            }
         }

         ArrayList var17 = new ArrayList();
         if (var1.has("samplers")) {
            var1.getAsJsonObject("samplers").asMap().forEach((var1x, var2x) -> {
               String var3x = var2x.getAsString();
               var17.add(new ShaderResource(var1x, ResourceLocationBridge.create(var3x)));
            });
         }

         return method8(var0, var5, var6, var7, var8, var9, var10, var11, var12, var17, var2);
      } else {
         return null;
      }
   }

   public static Alert6 method8(
      ResourceLocationBridge var0,
      String var1,
      String var2,
      String var3,
      String var4,
      boolean var5,
      int var6,
      int var7,
      List<GlslBuiltin> var8,
      List<ShaderResource> var9,
      boolean var10
   ) {
      ShaderKeyFactory var11 = ShaderKeyFactory.fromId(var1);
      ShaderKeyFactory var12 = ShaderKeyFactory.fromId(var3);
      if (var11 != null && var12 != null) {
         ShaderKey var13 = var11.createKey(var2, var10);
         ShaderKey var14 = var12.createKey(var4, var10);
         if (var13 != null && var14 != null) {
            return new Alert6(var0, var13, var14, var8, var9, var5, var6, var7);
         }

         if (var10) {
            method10("Something went wrong while creating keys! Check console for more info.");
            Slayer.method6("Shader Editor", "Unable to properly create keys! FSH: '" + var13 + "' VSH: '" + var14 + "'", new Object[0]);
         }

         return null;
      } else {
         if (var10) {
            if (var12 == null) {
               method10("Invalid VSH type '" + var3 + "'!");
            }

            if (var11 == null) {
               method10("Invalid FSH type '" + var3 + "'!");
            }
         }

         return null;
      }
   }

   public static void method9(String var0) {
      ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
      if (var1.isValid()) {
         Shaderdebugmod var2 = var1.method26();
         if (var2 != null) {
            Data var3 = var2.method43();
            if (var3 != null) {
               var3.method4(var0, Shaderdebugmod.method33(var0));
            }
         }
      }
   }

   public static void method10(String var0) {
      if (ThreadModuleDump63.method4() != null) {
         ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
         if (var1.isValid()) {
            Shaderdebugmod var2 = var1.method26();
            if (var2 != null) {
               Data var3 = var2.method43();
               if (var3 != null) {
                  var3.method2(var0, Shaderdebugmod.method33(var0));
               }
            }
         }
      }
   }

   public static void method11(ResourceLocationBridge var0, boolean var1, String var2) {
      if (ShaderInjectRegistry.method14(var0)) {
         var2 = ShaderPackHelper.method3(var2, method12(var1 ? JitShaderResource.Type.VERTEX : JitShaderResource.Type.FRAGMENT));
         method10("Unable to compile " + (var1 ? "VSH" : "FSH") + " shader! " + var2);
      }
   }

   private static int method12(JitShaderResource.Type var0) {
      ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
      if (var1.isValid() && var1.method26() != null) {
         Shaderdebugmod var2 = var1.method26();
         if (var2.method39()) {
            return switch (var0) {
               case FRAGMENT -> var2.method43().method7();
               case VERTEX -> var2.method43().method6();
            };
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   public ShaderCloakRenderer method13(UUID var1, @Nullable ResourceLocationBridge var2) {
      if (ThreadModuleDump63.method7() != null && ThreadModuleDump63.method7().bridge$getUniqueID().equals(var1)) {
         ShaderDebugMod var3 = ThreadModuleDump63.method4().method40().method74();
         if (var3.isValid() && var3.method24() != null) {
            return var3.method24();
         }
      }

      if (var2 == null) {
         CosmeticManager var6 = ThreadModuleDump63.method4().method53();
         CosmeticMetadata var4 = var6.getProvider(var1, CosmeticCategoryType.CLOAK);
         if (var4 == null) {
            return null;
         }

         var2 = var4.method4().method6(var1);
      }

      if (var2 == null) {
         return null;
      }

      Optional var7 = ThreadModuleDump63.method4().method96().method1(var2);
      if (var7.isEmpty()) {
         return null;
      }

      JitResource var8 = (JitResource)var7.get();
      return var8 instanceof JitEmoteResource var5 ? var5.method12() : null;
   }

   public void method14(NativeImageBuilder.Extension var1, Consumer<VertexBuilder> var2, UUID var3) {
      if (var3 != null && ShaderCloakRenderer.method34()) {
         CosmeticMetadata var4 = ThreadModuleDump63.method4().method53().method15(var3, CosmeticCategoryType.CLOAK);
         if (var4 != null) {
            ResourceLocationBridge var5 = var4.method4().method6(var3);
            this.method15(var1, var2, var3, var5);
         }
      }
   }

   public void method15(NativeImageBuilder.Extension var1, Consumer<VertexBuilder> var2, UUID var3, ResourceLocationBridge var4) {
      if (var3 != null && ShaderCloakRenderer.method34()) {
         ShaderCloakRenderer var5 = this.method13(var3, var4);
         if (var5 != null && var5.method36()) {
            if (var5.method26()) {
               var5.method14(var2);
            }

            var5.method17(var1);
         }
      }
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
   }
}
