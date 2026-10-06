package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.DepthTestMode;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_27;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.shader.ShaderProgramDefinition;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.render.shader.ShaderPipeline;
import com.moonsworth.lunar.client.render.jit.JitShaderResource;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump94;
import com.moonsworth.lunar.client.util.ThreadModuleDump94.Data;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public interface BundledShaderLoader extends ShaderPipeline {
   String field1 = "LUNAR-SHADER-DEFINITION-MARKER\n\nvoid main(){\n    vec4 outPos = vec4(POS.xy, 0.0, 1.0);\n    outPos -= 0.5;\n    outPos *= 2.0;\n\n    gl_Position = vec4(outPos.xy, 0.2, 1.0);\n    uv = vec2(POS.x, POS.y);\n}\n";

   @NotNull
   @Override
   default String method1() {
      return this.method7(JitShaderResource.Type.VERTEX, this.method9());
   }

   @NotNull
   @Override
   default String method2() {
      return this.method7(JitShaderResource.Type.FRAGMENT, this.method10());
   }

   @Override
   default RenderPipelineBuilder method3() {
      return Bridge.method8()
         .method80()
         .method11(Bridge_27.field1, DrawMode.TRIANGLE_STRIP)
         .method32(null)
         .method29(false)
         .method33(true)
         .method37(false)
         .method27(DepthTestMode.NO_DEPTH_TEST);
   }

   @Override
   default ShaderProgramDefinition method4() {
      ShaderProgramDefinition var1 = new ShaderProgramDefinition();
      this.method8().method1((var1x, var2) -> var1.method3(var2));
      return var1;
   }

   @Override
   default void method6(Consumer<String> var1) {
      var1.accept("DiffuseSampler");
      this.method7(var1);
   }

   void method7(Consumer<String> var1);

   default String method7(JitShaderResource.Type var1, String var2) {
      Data var3 = Data.method9(this).method10(var1, this.method8()).method11(var1, this.method8());
      return ThreadModuleDump94.method5(var2, var1, var3);
   }

   default ShaderUniform method8() {
      return ShaderUniform.field1;
   }

   @NotNull
   default String method9() {
      return "LUNAR-SHADER-DEFINITION-MARKER\n\nvoid main(){\n    vec4 outPos = vec4(POS.xy, 0.0, 1.0);\n    outPos -= 0.5;\n    outPos *= 2.0;\n\n    gl_Position = vec4(outPos.xy, 0.2, 1.0);\n    uv = vec2(POS.x, POS.y);\n}\n";
   }

   @NotNull
   String method10();

   default String method11(String var1) {
      return this.method12(ResourceLocationBridge.create("lunar", "shaders/post/" + var1 + ".glsl"));
   }

   default String method12(ResourceLocationBridge var1) {
      IResourceBridge var2 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var1);
      if (var2 != null) {
         try (InputStream var3 = var2.bridge$getInputStream()) {
            return new String(var3.readAllBytes(), StandardCharsets.UTF_8);
         } catch (IOException var8) {
            throw new IllegalStateException("Failed to read bundled shader: " + var1, var8);
         }
      } else {
         throw new IllegalStateException("Failed to find bundled shader: " + var1);
      }
   }
}
