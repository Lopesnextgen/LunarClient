package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public interface ShaderPipeline {
   @NotNull
   String method1();

   @NotNull
   String method2();

   RenderPipelineBuilder method3();

   ShaderProgramDefinition method4();

   void method5(BiConsumer<String, GlslUniformType> var1);

   void method6(Consumer<String> var1);
}
