package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.render.shader.ShaderPipeline;
import com.moonsworth.lunar.client.render.jit.JitShaderResource;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.util.alert.Alert6;
import com.moonsworth.lunar.files.Files6_2;
import java.util.ArrayList;
import java.util.List;

public class ThreadModuleDump94 {
   public static final String field1 = "LUNAR-SHADER-DEFINITION-MARKER";

   private static void method1(JitShaderResource.Type var0, String var1, String var2) {
      ShaderDebugMod var3 = ThreadModuleDump63.method4().method40().method74();
      Shaderdebugmod var4 = var3.method26();
      if (var4 != null && var4.method39()) {
         int var5 = method2(var1, '\n');
         int var6 = method2(var2, '\n');
         var4.method40(var0, var6 - var5);
      }
   }

   private static int method2(String var0, char var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.length(); var3++) {
         if (var1 == var0.charAt(var3)) {
            var2++;
         }
      }

      return var2;
   }

   private static String method3(String var0) {
      int var1 = var0.indexOf("LUNAR-SHADER-DEFINITION-MARKER");
      if (var1 == -1) {
         return null;
      }

      int var2 = var1 + "LUNAR-SHADER-DEFINITION-MARKER".length();
      return var2 >= var0.length() ? null : var0.substring(var2);
   }

   public static String method4(String var0, Alert6 var1, JitShaderResource.Type var2) {
      String var3 = method5(var0, var2, ThreadModuleDump94.Data.method8(var2, var1));
      method1(var2, var0, var3);
      return var3;
   }

   public static String method5(String var0, JitShaderResource.Type var1, ThreadModuleDump94.Data var2) {
      String var3 = method3(var0);
      if (var3 == null) {
         return var0;
      }

      boolean var4 = Bridge.method42().method82();
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      if (ThreadModuleDump63.MC_VERSION >= 8) {
         var2.method6("LUNAR_IN", "in");
         var2.method6("LUNAR_OUT", "out");
         if (var1 == JitShaderResource.Type.FRAGMENT) {
            var2.method5(GlslUniformType.VEC4, "fragColor");
         }
      } else if (var1 == JitShaderResource.Type.VERTEX) {
         var2.method6("LUNAR_IN", "attribute");
         var2.method6("LUNAR_OUT", "varying");
      } else {
         var2.method6("LUNAR_IN", "varying");
      }

      StringBuilder var8 = new StringBuilder();
      if (var4) {
         var8.append("#version 450\n");
      } else if (ThreadModuleDump63.MC_VERSION >= 8) {
         var8.append("#version 330\n");
      } else {
         var8.append("#version 120\n");
      }

      for (Files6_2 var10 : var2.field5) {
         var8.append("#define ").append((String)var10.field1).append(" ").append((String)var10.field2).append("\n");
      }

      if (ThreadModuleDump63.MC_VERSION >= 30) {
         if (!var2.field1.isEmpty()) {
            if (var4) {
               var8.append("layout(std140, set = 0, binding = ").append(var5++).append(") ");
            } else {
               var8.append("layout(std140) ");
            }

            var8.append("uniform LunarUniforms {\n");

            for (Files6_2 var16 : var2.field1) {
               var8.append("    ").append(((GlslUniformType)var16.field1).asGlslType()).append(" ").append((String)var16.field2).append(";\n");
            }

            var8.append("};\n");
         }
      } else {
         for (Files6_2 var17 : var2.field1) {
            if (var4) {
               var8.append("layout(set = 0, binding = ").append(var5++).append(") ");
            }

            var8.append("uniform ").append(((GlslUniformType)var17.field1).asGlslType()).append(" ").append((String)var17.field2).append(";\n");
         }
      }

      for (String var18 : var2.field2) {
         if (var4) {
            var8.append("layout(set = 0, binding = ").append(var5++).append(") ");
         }

         var8.append("uniform sampler2D ").append(var18).append(";\n");
      }

      for (Files6_2 var19 : var2.field3) {
         if (var4) {
            var8.append("layout(location = ").append(var6++).append(") ");
         }

         var8.append("LUNAR_IN ").append(((GlslUniformType)var19.field1).asGlslType()).append(" ").append((String)var19.field2).append(";\n");
      }

      for (Files6_2 var20 : var2.field4) {
         if (var4) {
            var8.append("layout(location = ").append(var7++).append(") ");
         }

         var8.append("LUNAR_OUT ").append(((GlslUniformType)var20.field1).asGlslType()).append(" ").append((String)var20.field2).append(";\n");
      }

      return var8.append(var3).toString();
   }

   public static class Data {
      private final List<Files6_2<GlslUniformType, String>> field1 = new ArrayList<>();
      private final List<String> field2 = new ArrayList<>();
      private final List<Files6_2<GlslUniformType, String>> field3 = new ArrayList<>();
      private final List<Files6_2<GlslUniformType, String>> field4 = new ArrayList<>();
      private final List<Files6_2<String, String>> field5 = new ArrayList<>();

      private Data() {
      }

      public ThreadModuleDump94.Data method1(String var1, GlslUniformType var2) {
         return this.method2(var2, var1);
      }

      public ThreadModuleDump94.Data method2(GlslUniformType var1, String var2) {
         this.field1.add(Files6_2.method1(var1, var2));
         return this;
      }

      public ThreadModuleDump94.Data method3(String var1) {
         this.field2.add(var1);
         return this;
      }

      public ThreadModuleDump94.Data method4(GlslUniformType var1, String var2) {
         this.field3.add(Files6_2.method1(var1, var2));
         return this;
      }

      public ThreadModuleDump94.Data method5(GlslUniformType var1, String var2) {
         this.field4.add(Files6_2.method1(var1, var2));
         return this;
      }

      public ThreadModuleDump94.Data method6(String var1, String var2) {
         this.field5.add(Files6_2.method1(var1, var2));
         return this;
      }

      public static ThreadModuleDump94.Data method7() {
         return new ThreadModuleDump94.Data();
      }

      public static ThreadModuleDump94.Data method8(JitShaderResource.Type var0, Alert6 var1) {
         ThreadModuleDump94.Data var2 = method7();
         var1.getUniforms().forEach(var1x -> var2.method2(var1x.getType(), var1x.getVarName()));
         Alert6.declareUniforms(var1.getPipeline(), var2::method1);
         var1.getSamplers().forEach(var1x -> var2.method3(var1x.method1()));
         var2.method10(var0, var1.getPipeline());
         var2.method11(var0, var1.getPipeline());
         return var2;
      }

      public static ThreadModuleDump94.Data method9(ShaderPipeline var0) {
         ThreadModuleDump94.Data var1 = method7();
         var0.method5(var1::method1);
         var0.method6(var1::method3);
         return var1;
      }

      public ThreadModuleDump94.Data method10(JitShaderResource.Type var1, ShaderUniform var2) {
         if (var1 == JitShaderResource.Type.VERTEX) {
            var2.method1(this::method4);
            var2.method2(this::method5);
         } else {
            var2.method2(this::method4);
         }

         return this;
      }

      public ThreadModuleDump94.Data method11(JitShaderResource.Type var1, ShaderUniform var2) {
         boolean var3 = var2.method3();
         if (ThreadModuleDump63.MC_VERSION >= 8) {
            this.method6("SAMPLE(tex, uv)", "texture(tex, uv)");
            this.method6("OUT_COLOR", "fragColor");
            if (var1 == JitShaderResource.Type.VERTEX) {
               this.method6("POS", "Position");
               if (var3) {
                  this.method6("UV", "UV0");
                  this.method6("NORMAL", "Normal");
               }
            }
         } else {
            this.method6("SAMPLE(tex, uv)", "texture2D(tex, uv)");
            this.method6("OUT_COLOR", "gl_FragColor");
            if (var1 == JitShaderResource.Type.VERTEX) {
               this.method6("POS", "gl_Vertex.xyz");
               if (var3) {
                  this.method6("UV", "gl_MultiTexCoord0");
                  this.method6("NORMAL", "gl_Normal");
               }
            }
         }

         if (var1 == JitShaderResource.Type.VERTEX && var3) {
            if (ThreadModuleDump63.MC_VERSION < 6) {
               this.method6("MODEL_VIEW_MAT", "gl_ModelViewMatrix");
               this.method6("NORMAL_MAT", "gl_NormalMatrix");
               this.method6("LEGACY_MODELVIEW_RESIZE", "LunarLegacyUISize");
            } else {
               this.method6("MODEL_VIEW_MAT", "LunarModelViewMat");
               this.method6("NORMAL_MAT", "mat3(LunarNormalMat)");
            }
         }

         if (var2 == ShaderUniform.field3) {
            if (ThreadModuleDump63.MC_VERSION < 6) {
               this.method6("PROJECTION_MAT", "gl_ProjectionMatrix");
            } else {
               this.method6("PROJECTION_MAT", "LunarProjectionMat");
            }
         }

         return this;
      }
   }
}
