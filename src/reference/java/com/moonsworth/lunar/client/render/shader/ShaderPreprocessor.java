package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.render.shader.ShaderPipeline;
import com.moonsworth.lunar.client.render.jit.JitShaderResource.Type;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.render.shader.ShaderDefinition;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.framework.Ref;

public class ShaderPreprocessor {
   public static final String field1 = "LUNAR-SHADER-DEFINITION-MARKER";

   public ShaderPreprocessor() {
   }

   private static void reportShaderSizeChange(Type type0, String text1, String text2) {
      ShaderDebugMod shaderdebugmod3 = Ref.method4().method40().method74();
      Shaderdebugmod shaderdebugmod4 = shaderdebugmod3.method26();
      if (shaderdebugmod4 != null && shaderdebugmod4.method39()) {
         int number5 = countOccurrences(text1, '\n');
         int number6 = countOccurrences(text2, '\n');
         shaderdebugmod4.method40(type0, number6 - number5);
      }
   }

   private static int countOccurrences(String text0, char character1) {
      int index2 = 0;

      for (int index3 = 0; index3 < text0.length(); index3++) {
         if (character1 == text0.charAt(index3)) {
            index2++;
         }
      }

      return index2;
   }

   private static String extractBody(String text0) {
      int number1 = text0.indexOf("LUNAR-SHADER-DEFINITION-MARKER");
      if (number1 == -1) {
         return null;
      }

      int index2 = number1 + "LUNAR-SHADER-DEFINITION-MARKER".length();
      return index2 >= text0.length() ? null : text0.substring(index2);
   }

   public static String preprocess(String text0, ShaderDefinition alert61, Type type) {
      String text3 = preprocessWithData(text0, type, ShaderPreprocessor.Data.method8(type, alert61));
      reportShaderSizeChange(type, text0, text3);
      return text3;
   }

   public static String preprocessWithData(String text0, Type type1, ShaderPreprocessor.Data data2) {
      String text3 = extractBody(text0);
      if (text3 == null) {
         return text0;
      }

      boolean flag4 = Bridge.method42().method82();
      int index5 = 0;
      int index6 = 0;
      int index7 = 0;
      if (Ref.MC_VERSION >= 8) {
         data2.method6("LUNAR_IN", "in");
         data2.method6("LUNAR_OUT", "out");
         if (type1 == Type.FRAGMENT) {
            data2.method5(GlslUniformType.VEC4, "fragColor");
         }
      } else if (type1 == Type.VERTEX) {
         data2.method6("LUNAR_IN", "attribute");
         data2.method6("LUNAR_OUT", "varying");
      } else {
         data2.method6("LUNAR_IN", "varying");
      }

      StringBuilder builder8 = new StringBuilder();
      if (flag4) {
         builder8.append("#version 450\n");
      } else if (Ref.MC_VERSION >= 8) {
         builder8.append("#version 330\n");
      } else {
         builder8.append("#version 120\n");
      }

      for (ValuePair files6_210 : data2.field5) {
         builder8.append("#define ").append((String)files6_210.field1).append(" ").append((String)files6_210.field2).append("\n");
      }

      if (Ref.MC_VERSION >= 30) {
         if (!data2.field1.isEmpty()) {
            if (flag4) {
               builder8.append("layout(std140, set = 0, binding = ").append(index5++).append(") ");
            } else {
               builder8.append("layout(std140) ");
            }

            builder8.append("uniform LunarUniforms {\n");

            for (ValuePair files6_216 : data2.field1) {
               builder8.append("    ").append(((GlslUniformType)files6_216.field1).asGlslType()).append(" ").append((String)files6_216.field2).append(";\n");
            }

            builder8.append("};\n");
         }
      } else {
         for (ValuePair files6_217 : data2.field1) {
            if (flag4) {
               builder8.append("layout(set = 0, binding = ").append(index5++).append(") ");
            }

            builder8.append("uniform ").append(((GlslUniformType)files6_217.field1).asGlslType()).append(" ").append((String)files6_217.field2).append(";\n");
         }
      }

      for (String text18 : data2.field2) {
         if (flag4) {
            builder8.append("layout(set = 0, binding = ").append(index5++).append(") ");
         }

         builder8.append("uniform sampler2D ").append(text18).append(";\n");
      }

      for (ValuePair files6_219 : data2.field3) {
         if (flag4) {
            builder8.append("layout(location = ").append(index6++).append(") ");
         }

         builder8.append("LUNAR_IN ").append(((GlslUniformType)files6_219.field1).asGlslType()).append(" ").append((String)files6_219.field2).append(";\n");
      }

      for (ValuePair files6_220 : data2.field4) {
         if (flag4) {
            builder8.append("layout(location = ").append(index7++).append(") ");
         }

         builder8.append("LUNAR_OUT ").append(((GlslUniformType)files6_220.field1).asGlslType()).append(" ").append((String)files6_220.field2).append(";\n");
      }

      return builder8.append(text3).toString();
   }

   public static class Data {
      private final List<ValuePair<GlslUniformType, String>> field1 = new ArrayList<>();
      private final List<String> field2 = new ArrayList<>();
      private final List<ValuePair<GlslUniformType, String>> field3 = new ArrayList<>();
      private final List<ValuePair<GlslUniformType, String>> field4 = new ArrayList<>();
      private final List<ValuePair<String, String>> field5 = new ArrayList<>();

      private Data() {
      }

      public ShaderPreprocessor.Data reportShaderSizeChange(String text1, GlslUniformType bridgetype_152) {
         return this.countOccurrences(bridgetype_152, text1);
      }

      public ShaderPreprocessor.Data countOccurrences(GlslUniformType bridgetype_151, String text2) {
         this.DEFINITION_MARKER.add(ValuePair.method1(bridgetype_151, text2));
         return this;
      }

      public ShaderPreprocessor.Data extractBody(String text1) {
         this.field2.add(text1);
         return this;
      }

      public ShaderPreprocessor.Data preprocess(GlslUniformType bridgetype_151, String text2) {
         this.field3.add(ValuePair.method1(bridgetype_151, text2));
         return this;
      }

      public ShaderPreprocessor.Data preprocessWithData(GlslUniformType bridgetype_151, String text2) {
         this.field4.add(ValuePair.method1(bridgetype_151, text2));
         return this;
      }

      public ShaderPreprocessor.Data method6(String text1, String text2) {
         this.field5.add(ValuePair.method1(text1, text2));
         return this;
      }

      public static ShaderPreprocessor.Data method7() {
         return new ShaderPreprocessor.Data();
      }

      public static ShaderPreprocessor.Data method8(Type type0, ShaderDefinition alert61) {
         ShaderPreprocessor.Data data2 = method7();
         alert61.method7().forEach(arg1x -> data2.method2(arg1x.getType(), arg1x.getVarName()));
         ShaderDefinition.method3(alert61.method2(), data2::method1);
         alert61.method8().forEach(arg1x -> data2.method3(arg1x.method1()));
         data2.method10(type0, alert61.method2());
         data2.method11(type0, alert61.method2());
         return data2;
      }

      public static ShaderPreprocessor.Data method9(ShaderPipeline colorsaturation_20) {
         ShaderPreprocessor.Data data1 = method7();
         colorsaturation_20.method5(data1::method1);
         colorsaturation_20.method6(data1::method3);
         return data1;
      }

      public ShaderPreprocessor.Data method10(Type type1, ShaderUniform colorsaturation42) {
         if (type1 == Type.VERTEX) {
            colorsaturation42.method1(this::preprocess);
            colorsaturation42.method2(this::preprocessWithData);
         } else {
            colorsaturation42.method2(this::preprocess);
         }

         return this;
      }

      public ShaderPreprocessor.Data method11(Type type1, ShaderUniform colorsaturation42) {
         boolean flag3 = colorsaturation42.method3();
         if (Ref.MC_VERSION >= 8) {
            this.method6("SAMPLE(tex, uv)", "texture(tex, uv)");
            this.method6("OUT_COLOR", "fragColor");
            if (type1 == Type.VERTEX) {
               this.method6("POS", "Position");
               if (flag3) {
                  this.method6("UV", "UV0");
                  this.method6("NORMAL", "Normal");
               }
            }
         } else {
            this.method6("SAMPLE(tex, uv)", "texture2D(tex, uv)");
            this.method6("OUT_COLOR", "gl_FragColor");
            if (type1 == Type.VERTEX) {
               this.method6("POS", "gl_Vertex.xyz");
               if (flag3) {
                  this.method6("UV", "gl_MultiTexCoord0");
                  this.method6("NORMAL", "gl_Normal");
               }
            }
         }

         if (type1 == Type.VERTEX && flag3) {
            if (Ref.MC_VERSION < 6) {
               this.method6("MODEL_VIEW_MAT", "gl_ModelViewMatrix");
               this.method6("NORMAL_MAT", "gl_NormalMatrix");
               this.method6("LEGACY_MODELVIEW_RESIZE", "LunarLegacyUISize");
            } else {
               this.method6("MODEL_VIEW_MAT", "LunarModelViewMat");
               this.method6("NORMAL_MAT", "mat3(LunarNormalMat)");
            }
         }

         if (colorsaturation42 == ShaderUniform.field3) {
            if (Ref.MC_VERSION < 6) {
               this.method6("PROJECTION_MAT", "gl_ProjectionMatrix");
            } else {
               this.method6("PROJECTION_MAT", "LunarProjectionMat");
            }
         }

         return this;
      }
   }
}
