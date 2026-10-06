package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.render.shader.ShaderCatalog;
import com.moonsworth.lunar.client.render.shader.ShaderKey;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.shader.ShaderKeyFactory;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.render.shader.DevShaderEditor;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.render.jit.JitShaderResource;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert6;
import com.moonsworth.lunar.files.Files6_2;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Shaderdebugmod {
   private static final Pattern field1 = Pattern.compile(
      "undefined variable \"(\\w+)\"|'(\\w+)'\\s*:\\s*undeclared identifier|Undeclared identifier\\s+(\\w+)|'(\\w+)'\\s+undeclared"
   );
   private final ResourceLocationBridge field2;
   private boolean field3;
   private long field4 = 0L;
   private boolean field5 = false;
   private Shaderdebugmod3 field6 = null;
   private Shaderdebugmod3 field7 = null;
   private final Map<String, Runnable> field8 = new HashMap<>();
   private Shaderdebugmod.Data field9 = null;
   private boolean field10 = false;
   private final Fishing field11;
   private boolean field12 = true;
   private boolean field13 = false;
   private boolean field14 = false;
   private boolean field15 = false;
   private boolean field16 = false;
   private boolean field17 = true;
   private int field18 = 176;
   private int field19 = 136;
   private final Set<GlslBuiltin> field20 = EnumSet.noneOf(GlslBuiltin.class);
   private final Set<GlslBuiltin> field21 = EnumSet.noneOf(GlslBuiltin.class);
   private final List<ShaderResource> field22 = new ArrayList<>();
   private boolean field23;
   private String field24 = "simple";
   private boolean field25;
   private String field26 = "debug";
   private List<Shaderdebugmod.Data2> field27 = new ArrayList<>();
   private List<Shaderdebugmod.Data2> field28 = new ArrayList<>();

   public Shaderdebugmod(ResourceLocationBridge var1) {
      this.field2 = var1;
      this.field11 = new Fishing("shader-fb-preview", false);
      Alert6 var2 = ThreadModuleDump63.method4().method53().method73().get(var1);
      this.field3 = var2 != null;
      if (var2 != null) {
         this.field18 = var2.getRenderWidth();
         this.field19 = var2.getRenderHeight();
         this.field23 = !(var2.getVertexShader() instanceof ShaderKey.Data3);
         this.field24 = var2.getVertexShader().method5();
         this.field25 = !(var2.getFragmentShader() instanceof ShaderKey.Data3);
         this.field26 = var2.getFragmentShader().method5();
         this.field20.addAll(var2.getUniforms());
         this.field22.addAll(var2.getSamplers());
         this.field12 = var2.isRenderOnTick();
         if (this.field23) {
            Path var3 = Shaderdebugmod2.method6(ThreadModuleDump48.field10, this.field24);
            if (Files.exists(var3)) {
               this.method37(var3.getParent());
            }
         }

         if (this.field25) {
            Path var4 = Shaderdebugmod2.method6(ThreadModuleDump48.field10, this.field26);
            if (Files.exists(var4)) {
               this.method38(var4.getParent());
            }
         }
      }
   }

   public boolean method1() {
      return this.method2() ? this.method3(false) : false;
   }

   public boolean method2() {
      if (!this.field5) {
         return false;
      }

      long var1 = System.currentTimeMillis() - this.field4;
      return var1 > 3000L;
   }

   public boolean method3(boolean var1) {
      if (!this.field5 && !var1) {
         return false;
      }

      this.field4 = System.currentTimeMillis();
      this.field5 = false;
      Path var2 = ThreadModuleDump48.field10.resolve("indexes").resolve("shader_textures.json");
      JsonObject var3;
      if (Files.exists(var2)) {
         try {
            String var4 = Files.readString(var2);
            var3 = JsonParser.parseString(var4).getAsJsonObject();
         } catch (Exception var6) {
            Slayer.method6("Shader Editor", "Unable to read Shader Definition Dev JSON file!");
            var6.printStackTrace();
            this.field5 = true;
            return false;
         }
      } else {
         var3 = new JsonObject();
      }

      var3.add(this.field2.toString(), this.method4(false, null));

      try {
         Files.writeString(var2, ThreadModuleDump48.field23.toJson(var3));
         return true;
      } catch (IOException var5) {
         Slayer.method6("Shader Editor", "Unable to save Shader Definition Dev JSON file!");
         var5.printStackTrace();
         this.field5 = true;
         return false;
      }
   }

   public JsonObject method4(boolean var1, String var2) {
      JsonObject var3 = new JsonObject();
      JsonObject var4 = new JsonObject();
      var4.addProperty("type", this.field25 ? "custom" : "static");
      var4.addProperty("value", Shaderdebugmod2.method8(var1, this.field25, false, this.field26, var2));
      JsonObject var5 = new JsonObject();
      var5.addProperty("type", this.field23 ? "custom" : "static");
      var5.addProperty("value", Shaderdebugmod2.method8(var1, this.field23, true, this.field24, var2));
      JsonArray var6 = new JsonArray();

      for (GlslBuiltin var8 : this.field20) {
         var6.add(var8.getVarName());
      }

      JsonObject var10 = new JsonObject();

      for (ShaderResource var9 : this.field22) {
         var10.addProperty(var9.method1(), Shaderdebugmod2.method7(var1, var9, var2));
      }

      var3.add("fragment", var4);
      var3.add("vertex", var5);
      var3.add("uniforms", var6);
      var3.add("samplers", var10);
      var3.addProperty("renderOnTick", this.field12);
      var3.addProperty("renderWidth", this.field18);
      var3.addProperty("renderHeight", this.field19);
      return var3;
   }

   public void method5() {
      this.field10 = true;
   }

   public boolean method6(ResourceLocationBridge var1) {
      return var1.equals(this.field2);
   }

   public void method7(GlslBuiltin var1, boolean var2) {
      if (var2) {
         this.field20.add(var1);
      } else {
         this.field20.remove(var1);
      }

      this.method19(Shaderdebugmod.Type2.UNIFORMS);
      this.method17();
   }

   public void method8(GlslBuiltin var1, boolean var2) {
      if (var2) {
         this.field21.add(var1);
      } else {
         this.field21.remove(var1);
      }

      this.method19(Shaderdebugmod.Type2.HIDDEN_UNIFORMS);
      ShaderDebugMod var3 = ThreadModuleDump63.method4().method40().method74();
      if (var2 && var3.isValid()) {
         ShaderCloakRenderer var4 = var3.method24();
         if (var4 != null) {
            var4.method19(var1);
         }
      }
   }

   public void method9(int var1, int var2) {
      if (var1 != -1) {
         this.field18 = var1;
      }

      if (var2 != -1) {
         this.field19 = var2;
      }

      this.field5 = true;
      this.method17();
   }

   public void method10(boolean var1, boolean var2) {
      if (var1) {
         this.field23 = var2;
         this.method19(Shaderdebugmod.Type2.VSH_TYPE);
         if (!var2) {
            this.method37(null);
         }
      } else {
         this.field25 = var2;
         this.method19(Shaderdebugmod.Type2.FSH_TYPE);
         if (!var2) {
            this.method38(null);
         }
      }

      this.method17();
   }

   public void method11(boolean var1, String var2) {
      if (var1) {
         this.field24 = var2;
         this.method19(Shaderdebugmod.Type2.VSH);
      } else {
         this.field26 = var2;
         this.method19(Shaderdebugmod.Type2.FSH);
      }

      this.method17();
   }

   private Files6_2<ResourceLocationBridge, Path> method12(String var1, String... var2) {
      File var3 = Gui4.method7(null, ThreadModuleDump48.field10.toFile(), var1, var2);
      if (var3 != null && var3.exists()) {
         Path var4 = ThreadModuleDump48.field10.toAbsolutePath().normalize();
         Path var5 = var3.toPath().toAbsolutePath().normalize();
         if (!var5.startsWith(var4)) {
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "File must be within the dev_cosmetics folder!");
            return null;
         } else {
            Path var6 = var4.relativize(var5);
            String var7 = this.method14(var6);
            return Files6_2.method1(ResourceLocationBridge.create(var7), var5);
         }
      } else {
         return null;
      }
   }

   public void method13(boolean var1) {
      Files6_2 var2;
      if (var1) {
         var2 = this.method12("Vertex Shader", "vsh");
      } else {
         var2 = this.method12("Fragment Shader", "fsh");
      }

      if (var2 != null) {
         if (var1) {
            this.field24 = var2.field1.toString();
            this.method37(((Path)var2.field2).getParent());
            this.method19(Shaderdebugmod.Type2.VSH);
         } else {
            this.field26 = var2.field1.toString();
            this.method38(((Path)var2.field2).getParent());
            this.method19(Shaderdebugmod.Type2.FSH);
         }

         this.method17();
      }
   }

   private String method14(Path var1) {
      String var2 = var1.toString().replace('\\', '/');
      return var2.startsWith("/") ? "lunar:dev_cosmetics" + var2 : "lunar:dev_cosmetics/" + var2;
   }

   public void method15(String var1, boolean var2) {
      try {
         Shaderdebugmod.Type2 var3 = Shaderdebugmod.Type2.valueOf(var1.toUpperCase());
         switch (var3) {
            case LIMIT_TICK:
               this.field12 = var2;
               break;
            case TEX_PREVIEW_SEPARATE:
               this.field13 = var2;
               break;
            case TEX_PREVIEW_LABEL:
               this.field14 = var2;
               break;
            case PLAYER_PREVIEW_CLOTH:
               this.field15 = var2;
               break;
            case PLAYER_PREVIEW_MOVE:
               this.field16 = var2;
               break;
            case AUTO_HOT_RELOAD:
               this.field17 = var2;
               break;
            default:
               return;
         }

         this.method19(var3);
         this.method17();
      } catch (Exception var4) {
      }
   }

   public void method16(String var1) {
      String[] var2 = var1.split(":", 2);
      String var3 = "";
      String var4 = "";
      if (var2.length == 2) {
         var3 = var2[0];
         var4 = var2[1];
      } else if (var2.length == 1) {
         var3 = var2[0];
      }

      Shaderdebugmod.Type var5 = Shaderdebugmod.Type.fromId(var3);
      if (var5 != null) {
         switch (var5) {
            case MISSING_UNIFORM:
               boolean var19 = false;

               for (String var10 : var4.split(",")) {
                  GlslBuiltin var11 = DevShaderEditor.method1(var10);
                  if (var11 != null) {
                     var19 = true;
                     this.field20.add(var11);
                  }
               }

               if (var19) {
                  this.method19(Shaderdebugmod.Type2.UNIFORMS);
                  this.method28();
               }
               break;
            case LIMIT_20FPS:
               this.field12 = Boolean.parseBoolean(var4);
               this.method19(Shaderdebugmod.Type2.LIMIT_TICK);
               this.method28();
               break;
            case BAD_RESOLUTION:
               if (this.field18 % 22 != 0) {
                  this.field18 = (int)(Math.ceil(this.field18 / 22.0) * 22.0);
               }

               if (this.field19 % 17 != 0) {
                  this.field19 = (int)(Math.ceil(this.field19 / 17.0) * 17.0);
               }

               int var18 = this.field18 / 22;
               int var22 = this.field19 / 17;
               if (var18 > var22) {
                  this.field19 = 17 * var18;
               } else {
                  this.field18 = 22 * var22;
               }

               this.method19(Shaderdebugmod.Type2.RESOLUTION);
               this.method28();
               break;
            case DUPLICATE_SAMPLER_NAME:
               if (!var4.isEmpty()) {
                  int var17;
                  try {
                     var17 = Integer.parseInt(var4);
                  } catch (Exception var13) {
                     return;
                  }

                  if (var17 < 0 || var17 >= this.field22.size()) {
                     return;
                  }

                  ShaderResource var21 = this.field22.get(var17);
                  this.field22.set(var17, new ShaderResource(this.method22(), var21.method2()));
                  this.method19(Shaderdebugmod.Type2.SAMPLERS);
                  this.method28();
               }
               break;
            case EXPORT:
               boolean var16 = "cdn".equals(var4);
               Shaderdebugmod2.method1(this, var16);
               break;
            case CREATE_SHADER_DATA:
               this.field3 = true;
               this.method19(Shaderdebugmod.Type2.HAS_SHADER_DATA);
               this.method28();
               break;
            case FIND_SHADER:
               boolean var15 = "vsh".equals(var4);
               if (var15 && !this.field23 || !var15 && !this.field25) {
                  return;
               }

               String var20 = var15 ? this.field24 : this.field26;
               Path var24 = Shaderdebugmod2.method6(ThreadModuleDump48.field10, var20);
               if (Files.exists(var24)) {
                  Shaderdebugmod2.method4(var24);
               }
               break;
            case FIND_SAMPLER:
               int var14 = -1;

               try {
                  var14 = Integer.parseInt(var4);
               } catch (Exception var12) {
               }

               if (var14 < 0 || var14 >= this.field22.size()) {
                  return;
               }

               ShaderResource var7 = this.field22.get(var14);
               if (!JitPaths.method1(var7.method2())) {
                  String var8 = var7.method2().bridge$getPath();
                  Path var9 = Shaderdebugmod2.method6(ThreadModuleDump48.field10, var8);
                  if (Files.exists(var9)) {
                     Shaderdebugmod2.method4(var9);
                  }
               }
               break;
            case CUSTOM:
               Runnable var6 = this.field8.get(var4);
               if (var6 != null) {
                  var6.run();
                  this.method28();
               }
         }
      }
   }

   public void method17() {
      if (this.field17) {
         this.method28();
      }
   }

   private static void method18(Consumer<GuiIterator> var0) {
      ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
      if (var1.isValid()) {
         var0.accept(var1.method27());
      }
   }

   private void method19(Shaderdebugmod.Type2 var1) {
      method18(var2 -> this.method20(var1, var2));
   }

   private void method20(Shaderdebugmod.Type2 var1, GuiIterator var2) {
      if (var1.usedInDefinitionFile) {
         this.field5 = true;
      }

      switch (var1) {
         case LIMIT_TICK:
            var2.method3("limitTo20FPS", this.field12);
            break;
         case TEX_PREVIEW_SEPARATE:
            var2.method3("texPreviewSeparate", this.field13);
            break;
         case TEX_PREVIEW_LABEL:
            var2.method3("texPreviewLabel", this.field14);
            break;
         case PLAYER_PREVIEW_CLOTH:
            var2.method3("playerPreviewCloth", this.field15);
            break;
         case PLAYER_PREVIEW_MOVE:
            var2.method3("playerPreviewMove", this.field16);
            break;
         case AUTO_HOT_RELOAD:
            var2.method3("autoHotReload", this.field17);
            break;
         case RESOLUTION:
            var2.method3("resX", this.field18);
            var2.method3("resY", this.field19);
            break;
         case UNIFORMS:
            JsonArray var11 = new JsonArray();

            for (GlslBuiltin var17 : this.field20) {
               var11.add(var17.getVarName());
            }

            var2.method3("selectedUniforms", var11);
            break;
         case SAMPLERS:
            JsonArray var10 = new JsonArray();

            for (ShaderResource var16 : this.field22) {
               JsonObject var18 = new JsonObject();
               var18.addProperty("varName", var16.method1());
               var18.addProperty("location", var16.method2().toString());
               var10.add(var18);
            }

            var2.method3("samplers", var10);
            break;
         case HIDDEN_UNIFORMS:
            JsonArray var9 = new JsonArray();

            for (GlslBuiltin var15 : this.field21) {
               var9.add(var15.getVarName());
            }

            var2.method3("hiddenUniforms", var9);
            break;
         case HAS_SHADER_DATA:
            var2.method3("hasShaderData", this.field3);
            break;
         case VSH_TYPE:
            var2.method3("vshCustom", this.field23);
            break;
         case VSH:
            var2.method3("vsh", this.field24);
            break;
         case FSH_TYPE:
            var2.method3("fshCustom", this.field25);
            break;
         case FSH:
            var2.method3("fsh", this.field26);
            break;
         case ERRORS:
         case WARNINGS:
            List var3 = var1 == Shaderdebugmod.Type2.ERRORS ? this.field27 : this.field28;
            String var4 = var1 == Shaderdebugmod.Type2.ERRORS ? "errors" : "warnings";
            JsonArray var5 = new JsonArray();

            for (Shaderdebugmod.Data2 var7 : var3) {
               JsonObject var8 = new JsonObject();
               var8.addProperty("message", var7.field1);
               if (var7.field2 != null) {
                  var8.addProperty("actionId", var7.field2);
               }

               var5.add(var8);
            }

            var2.method3(var4, var5);
      }
   }

   public void method21(GuiIterator var1, int var2) {
      JsonArray var3 = new JsonArray();

      for (GlslBuiltin var7 : GlslBuiltin.values()) {
         var3.add(var7.getVarName());
      }

      JsonArray var11 = new JsonArray();

      for (GlslBuiltin var14 : this.field20) {
         var11.add(var14.getVarName());
      }

      JsonArray var13 = new JsonArray();

      for (ShaderKey.Data3 var17 : ShaderCatalog.method4(JitShaderResource.Type.VERTEX)) {
         var13.add(var17.method2());
      }

      JsonArray var16 = new JsonArray();

      for (ShaderKey.Data3 var8 : ShaderCatalog.method4(JitShaderResource.Type.FRAGMENT)) {
         var16.add(var8.method2());
      }

      var1.method3("cosmeticId", var2);
      var1.method3("cosmeticPath", this.field2.toString());
      var1.method3("allUniforms", var3);
      var1.method3("vshStatics", var13);
      var1.method3("fshStatics", var16);
      var1.method3("warnings", new JsonArray());
      var1.method3("errors", new JsonArray());

      for (Shaderdebugmod.Type2 var10 : Shaderdebugmod.Type2.values()) {
         this.method20(var10, var1);
      }

      this.field5 = false;
   }

   public String method22() {
      String var1 = null;

      label27:
      for (int var2 = this.field22.size(); var2 >= 0; var2--) {
         String var3 = "Sampler" + var2;

         for (ShaderResource var5 : this.field22) {
            if (var5.method1().equals(var3)) {
               continue label27;
            }
         }

         var1 = var3;
      }

      return var1 == null ? UUID.randomUUID().toString() : var1;
   }

   public void method23() {
      String var1 = this.method22();
      ShaderResource var2 = this.method27(var1);
      if (var2 != null) {
         this.field22.add(var2);
         this.method19(Shaderdebugmod.Type2.SAMPLERS);
         this.method17();
      }
   }

   public void method24(String var1, int var2) {
      if (var2 >= 0 && var2 < this.field22.size()) {
         ShaderResource var3 = this.field22.get(var2);
         this.field22.set(var2, new ShaderResource(var1, var3.method2()));
         this.field5 = true;
         this.method17();
      }
   }

   public void method25(int var1) {
      if (var1 >= 0 && var1 < this.field22.size()) {
         ShaderResource var2 = this.field22.get(var1);
         ShaderResource var3 = this.method27(var2.method1());
         if (var3 != null) {
            if (var1 < this.field22.size()) {
               this.field22.set(var1, var3);
               this.method19(Shaderdebugmod.Type2.SAMPLERS);
               this.method17();
            }
         }
      }
   }

   public void method26(int var1) {
      if (var1 >= 0 && var1 < this.field22.size()) {
         this.field22.remove(var1);
         this.method19(Shaderdebugmod.Type2.SAMPLERS);
         this.method17();
      }
   }

   private ShaderResource method27(String var1) {
      Files6_2 var2 = this.method12("Sampler Texture", "png", "webp");
      return var2 == null ? null : new ShaderResource(var1, (ResourceLocationBridge)var2.field1);
   }

   public void method28() {
      if (this.field3) {
         ShaderDebugMod var1 = ThreadModuleDump63.method4().method40().method74();
         if (var1.isValid()) {
            this.field8.clear();
            this.field9 = new Shaderdebugmod.Data();

            try {
               this.method29();
               if (this.method30()) {
                  return;
               }
            } catch (Exception var4) {
               this.field9.method1("Unable to look for definition errors! Check console for more info! " + var4.getMessage());
               var4.printStackTrace();
            }

            try {
               this.method31();
            } catch (Exception var3) {
               this.field9.method1("Unable to properly execute hot reload! Check console for more info! " + var3.getMessage());
               var3.printStackTrace();
            }
         }
      }
   }

   private void method29() {
      boolean var1 = false;

      for (GlslBuiltin var3 : this.field20) {
         if (var3.isNeedsFrameRender()) {
            var1 = true;
            break;
         }
      }

      if (!this.field23 && (this.field24.equalsIgnoreCase("positioned") || this.field24.equalsIgnoreCase("positioned_proj"))) {
         var1 = true;
      }

      if (var1 && this.field12) {
         String var4 = "Some uniforms ("
            + this.field20.stream().filter(GlslBuiltin::isNeedsFrameRender).map(GlslBuiltin::getVarName).collect(Collectors.joining(", "))
            + ") recommend Limit to 20 FPS be turned off!";
         this.field9.method4(var4, Shaderdebugmod.Type.LIMIT_20FPS.create("false"));
      } else if (!var1 && !this.field12) {
         this.field9
            .method4(
               "There are no selected uniforms that recommend Limit to 20 FPS to be turned off! Is this intentional?",
               Shaderdebugmod.Type.LIMIT_20FPS.create("true")
            );
      }
   }

   private boolean method30() {
      if (this.field18 % 22 == 0 && this.field19 % 17 == 0) {
         int var1 = this.field18 / 22;
         int var2 = this.field19 / 17;
         if (var1 != var2) {
            this.field9.method4("Resolution should have an aspect ratio of 22:17!", Shaderdebugmod.Type.BAD_RESOLUTION.id);
         }
      } else {
         this.field9.method2("Bad resolution! Should be a multiple of (22, 17)!", Shaderdebugmod.Type.BAD_RESOLUTION.id);
      }

      boolean var6 = false;
      HashSet var7 = new HashSet();
      int var3 = 0;

      for (ShaderResource var5 : this.field22) {
         if (var7.contains(var5.method1())) {
            var6 = true;
            this.field9.method2("Duplicate Sampler var names! (" + var5.method1() + ")", Shaderdebugmod.Type.DUPLICATE_SAMPLER_NAME.create(var3 + ""));
         } else {
            var7.add(var5.method1());
         }

         var3++;
      }

      short var8 = 32767;
      if (this.field18 > var8 || this.field19 > var8) {
         this.field9.method2("Resolution is too high! Max size: " + var8, this.method41("toohighres", () -> {
            this.field18 = Math.min(var8, this.field18);
            this.field19 = Math.min(var8, this.field19);
            this.method19(Shaderdebugmod.Type2.RESOLUTION);
         }));
      } else if (this.field18 > 8192 || this.field19 > 8192) {
         this.field9.method2("Resolution is too high! Try limiting to 8192", this.method41("toohighres", () -> {
            this.field18 = Math.min(8184, this.field18);
            this.field19 = Math.min(8177, this.field19);
            this.method19(Shaderdebugmod.Type2.RESOLUTION);
         }));
      }

      return var6;
   }

   private void method31() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 == null) {
         DevShaderEditor.method10("Player is null! Try joining a world?");
      } else {
         boolean var2 = false;
         if (this.field24 == null || this.field24.isEmpty()) {
            DevShaderEditor.method10("Vertex Shader is not set!");
            var2 = true;
         }

         if (this.field26 == null || this.field26.isEmpty()) {
            DevShaderEditor.method10("Fragment Shader is not set!");
            var2 = true;
         }

         if (!var2) {
            ShaderDebugMod var3 = ThreadModuleDump63.method4().method40().method74();
            CosmeticManager var4 = ThreadModuleDump63.method4().method53();
            DevShaderEditor var5 = ThreadModuleDump63.method4().method102();
            int var6 = Math.min(this.field18, 8192);
            int var7 = Math.min(this.field19, 8192);
            Alert6 var8 = DevShaderEditor.method8(
               this.field2,
               this.field25 ? "custom" : "static",
               this.field26,
               this.field23 ? "custom" : "static",
               this.field24,
               this.field12,
               var6,
               var7,
               this.field20.stream().toList(),
               this.field22,
               true
            );
            if (this.field25 && ShaderKeyFactory.CUSTOM.createKey(this.field26, true) instanceof ShaderKey.Data2 var10) {
               var5.method6(var10.getLocation());
            }

            if (this.field23 && ShaderKeyFactory.CUSTOM.createKey(this.field24, true) instanceof ShaderKey.Data2 var12) {
               var5.method6(var12.getLocation());
            }

            var4.method4(this.field2, var8);
            var3.method23();
            var3.method14();
            var3.method15();
            var4.method37(this.field2, var1.bridge$getUniqueID());
         }
      }
   }

   public void method32() {
      if (this.field9 != null) {
         try {
            if (!this.field9.field1.equals(this.field27)) {
               this.field27 = this.field9.field1;
               this.method19(Shaderdebugmod.Type2.ERRORS);
            }

            if (!this.field9.field2.equals(this.field28)) {
               this.field28 = this.field9.field2;
               this.method19(Shaderdebugmod.Type2.WARNINGS);
            }
         } catch (Exception var2) {
            Slayer.method6("Shader Editor", "Something went wrong while updating providers during hot reload!");
            var2.printStackTrace();
         }

         this.field9 = null;
      }
   }

   @Nullable
   public static String method33(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         Matcher var1 = field1.matcher(var0);
         StringBuilder var2 = null;

         while (var1.find()) {
            String var3 = var1.group(1);
            if (var3 != null) {
               GlslBuiltin var4 = DevShaderEditor.method1(var3);
               if (var4 != null) {
                  if (var2 == null) {
                     var2 = new StringBuilder(var4.getVarName());
                  } else {
                     var2.append(",").append(var4.getVarName());
                  }
               }
            }
         }

         return var2 != null ? Shaderdebugmod.Type.MISSING_UNIFORM.create(var2.toString()) : null;
      } else {
         return null;
      }
   }

   public void method34(AbstractRenderContext var1, double var2, double var4) {
      ShaderDebugMod var6 = ThreadModuleDump63.method4().method40().method74();
      var6.method17();
      if (this.field10) {
         this.field10 = false;
         this.method35(var1);
      }
   }

   private void method35(AbstractRenderContext var1) {
      ShaderDebugMod var2 = ThreadModuleDump63.method4().method40().method74();
      Shaderdebugmod4 var3 = var2.method28();
      if (var3.isValid() && this.field3) {
         this.field11.method1(var3.method9());
      } else {
         if (this.field3) {
         }
      }
   }

   public boolean method36(GlslBuiltin var1) {
      return this.field21.contains(var1);
   }

   public void cleanup() {
      this.method37(null);
      this.method38(null);
   }

   private void method37(Path var1) {
      if (this.field6 != null) {
         this.field6.shutdown();
         this.field6 = null;
      }

      if (var1 != null) {
         this.field7 = new Shaderdebugmod3(var1, var1x -> {
            if (this.field17) {
               if (this.field24.endsWith(var1x.toString())) {
                  ThreadModuleDump37.method8().execute(this::method28);
               }
            }
         });
      }
   }

   private void method38(Path var1) {
      if (this.field7 != null) {
         this.field7.shutdown();
         this.field7 = null;
      }

      if (var1 != null) {
         this.field7 = new Shaderdebugmod3(var1, var1x -> {
            if (this.field17) {
               if (this.field26.endsWith(var1x.toString())) {
                  ThreadModuleDump37.method8().execute(this::method28);
               }
            }
         });
      }
   }

   public boolean method39() {
      return this.field9 != null;
   }

   public void method40(JitShaderResource.Type var1, int var2) {
      if (this.field9 != null) {
         if (var1 == JitShaderResource.Type.VERTEX) {
            this.field9.field3 = var2;
         } else if (var1 == JitShaderResource.Type.FRAGMENT) {
            this.field9.field4 = var2;
         }
      }
   }

   public String method41(String var1, Runnable var2) {
      this.field8.put(var1, var2);
      return Shaderdebugmod.Type.CUSTOM.create(var1);
   }

   @Generated
   public ResourceLocationBridge getLocation() {
      return this.field2;
   }

   @Generated
   public boolean method42() {
      return this.field3;
   }

   @Generated
   public Shaderdebugmod.Data method43() {
      return this.field9;
   }

   @Generated
   public boolean method44() {
      return this.field13;
   }

   @Generated
   public int method45() {
      return this.field18;
   }

   @Generated
   public int method46() {
      return this.field19;
   }

   @Generated
   public Set<GlslBuiltin> method47() {
      return this.field20;
   }

   @Generated
   public List<ShaderResource> method48() {
      return this.field22;
   }

   @Generated
   public boolean method49() {
      return this.field23;
   }

   @Generated
   public String method50() {
      return this.field24;
   }

   @Generated
   public boolean method51() {
      return this.field25;
   }

   @Generated
   public String method52() {
      return this.field26;
   }

   public static class Data {
      private final List<Shaderdebugmod.Data2> field1 = new ArrayList<>();
      private final List<Shaderdebugmod.Data2> field2 = new ArrayList<>();
      private int field3 = 0;
      private int field4 = 0;

      public Shaderdebugmod.Data method1(String var1) {
         return this.method2(var1, null);
      }

      public Shaderdebugmod.Data method2(String var1, @Nullable String var2) {
         this.method5(true, var1, var2);
         return this;
      }

      public Shaderdebugmod.Data method3(String var1) {
         return this.method4(var1, null);
      }

      public Shaderdebugmod.Data method4(String var1, @Nullable String var2) {
         this.method5(false, var1, var2);
         return this;
      }

      private void method5(boolean var1, String var2, @Nullable String var3) {
         Shaderdebugmod.Data2 var4 = new Shaderdebugmod.Data2(var2, var3);
         if (var1) {
            this.field1.add(var4);
         } else {
            this.field2.add(var4);
         }
      }

      @Generated
      public int method6() {
         return this.field3;
      }

      @Generated
      public int method7() {
         return this.field4;
      }
   }

   private class Data2 {
      private final String field1;
      @Nullable
      private final String field2;

      private Data2(String var1, @Nullable String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String message() {
         return this.field1;
      }

      @Nullable
      public String method1() {
         return this.field2;
      }
   }

   public enum Type {
      MISSING_UNIFORM("uniform"),
      EXPORT("export"),
      BAD_RESOLUTION("bad_resolution"),
      LIMIT_20FPS("limit_20fps"),
      DUPLICATE_SAMPLER_NAME("dupe_sampler_name"),
      CREATE_SHADER_DATA("create_shader_data"),
      FIND_SHADER("find_shader"),
      FIND_SAMPLER("find_sampler"),
      CUSTOM("custom");

      private final String id;

      Type(String var3) {
         this.id = var3;
      }

      public static Shaderdebugmod.Type fromId(String var0) {
         return Arrays.stream(values()).filter(var1 -> var1.id.equals(var0)).findFirst().orElse(null);
      }

      public String create(String var1) {
         return this.id + ":" + var1;
      }
   }

   private enum Type2 {
      RESOLUTION(true),
      LIMIT_TICK(true),
      UNIFORMS(true),
      SAMPLERS(true),
      HIDDEN_UNIFORMS,
      VSH_TYPE(true),
      VSH(true),
      FSH_TYPE(true),
      FSH(true),
      HAS_SHADER_DATA,
      TEX_PREVIEW_SEPARATE,
      TEX_PREVIEW_LABEL,
      PLAYER_PREVIEW_CLOTH,
      PLAYER_PREVIEW_MOVE,
      AUTO_HOT_RELOAD,
      ERRORS,
      WARNINGS;

      private final boolean usedInDefinitionFile;

      Type2() {
         this(false);
      }

      Type2(boolean var3) {
         this.usedInDefinitionFile = var3;
      }
   }
}
