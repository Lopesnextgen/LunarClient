package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class RenderPipelineBuilder {
   protected ResourceLocationBridge field1;
   protected ResourceLocationBridge field2;
   protected ResourceLocationBridge field3;
   protected Bridge_63 field4;
   protected DrawMode field5;
   @Nullable
   protected BridgeImplementation.Bridge2$Extension2 field6;
   @Nullable
   protected List<String> field7;
   @Nullable
   protected List<Bridge3_7> field8;
   @Nullable
   protected DepthTestMode field9;
   @Nullable
   protected PolygonDrawMode field10;
   @Nullable
   protected Boolean field11;
   @Nullable
   protected Boolean field12;
   @Nullable
   protected Boolean field13;
   @Nullable
   protected Boolean field14;
   @Nullable
   protected Bridge2_15 field15;
   @Nullable
   protected ResourceLocationBridge field16;
   protected float field17;
   protected float field18;
   protected boolean field19;

   @Contract("_->this")
   public RenderPipelineBuilder method1(@NotNull String var1) {
      this.field1 = ResourceLocationBridge.create(var1);
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method2(@NotNull String var1, @NotNull String var2) {
      this.field1 = ResourceLocationBridge.create(var1, var2);
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method3(@NotNull ResourceLocationBridge var1) {
      this.field1 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method4(@NotNull String var1) {
      this.field2 = ResourceLocationBridge.create(var1);
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method5(@NotNull String var1, @NotNull String var2) {
      this.field2 = ResourceLocationBridge.create(var1, var2);
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method6(@NotNull ResourceLocationBridge var1) {
      this.field2 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method7(@NotNull String var1) {
      this.field3 = ResourceLocationBridge.create(var1);
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method8(@NotNull String var1, @NotNull String var2) {
      this.field3 = ResourceLocationBridge.create(var1, var2);
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method9(@NotNull ResourceLocationBridge var1) {
      this.field3 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method10(@NotNull ResourceLocationBridge var1) {
      this.field16 = var1;
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method11(@NotNull Bridge_63 var1, @NotNull DrawMode var2) {
      this.field4 = var1;
      this.field5 = var2;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method12(@NotNull String var1) {
      if (this.field6 == null) {
         this.field6 = this.method46();
      }

      this.field6.bridge$define(var1);
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method13(String var1, int var2) {
      if (this.field6 == null) {
         this.field6 = this.method46();
      }

      this.field6.bridge$define(var1, String.valueOf(var2));
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method14(String var1, float var2) {
      if (this.field6 == null) {
         this.field6 = this.method46();
      }

      this.field6.bridge$define(var1, String.valueOf(var2));
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method15(String var1, String var2) {
      if (this.field6 == null) {
         this.field6 = this.method46();
      }

      this.field6.bridge$define(var1, var2);
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method16(@Nullable BridgeImplementation.Bridge2$Extension2 var1) {
      if (this.field6 == null) {
         this.field6 = this.method46();
      }

      this.field6.bridge$copyFrom(var1);
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method17(@Nullable Bridge2_2 var1) {
      if (this.field6 == null) {
         this.field6 = this.method46();
      }

      this.field6.bridge$copyFrom(var1);
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method18(@NotNull String var1) {
      if (this.field7 == null) {
         this.field7 = new ArrayList<>();
         this.field7.add(var1);
      } else if (!this.field7.contains(var1)) {
         this.field7.add(var1);
      }

      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method19(@NotNull List<String> var1) {
      if (this.field7 == null) {
         this.field7 = new ArrayList<>(var1);
      } else {
         for (String var3 : var1) {
            if (!this.field7.contains(var3)) {
               this.field7.add(var3);
            }
         }
      }

      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method20(@NotNull String var1) {
      if (this.field7 != null) {
         this.field7.remove(var1);
      }

      return this;
   }

   @Contract("->this")
   public RenderPipelineBuilder method21() {
      this.field7 = null;
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method22(@NotNull String var1, @NotNull GlslUniformType var2) {
      return this.method23(new Bridge3_7(var1, var2));
   }

   @Contract("_->this")
   public RenderPipelineBuilder method23(@NotNull Bridge3_7 var1) {
      if (this.field8 == null) {
         this.field8 = new ArrayList<>();
         this.field8.add(var1);
      } else if (!this.field8.contains(var1)) {
         this.field8.add(var1);
      }

      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method24(@NotNull List<Bridge3_7> var1) {
      if (this.field8 == null) {
         this.field8 = new ArrayList<>(var1);
      } else {
         for (Bridge3_7 var3 : var1) {
            if (!this.field8.contains(var3)) {
               this.field8.add(var3);
            }
         }
      }

      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method25(@NotNull String var1) {
      if (this.field8 != null) {
         this.field8.removeIf(var1x -> var1x.name().equals(var1));
      }

      return this;
   }

   @Contract("->this")
   public RenderPipelineBuilder method26() {
      this.field8 = null;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method27(@Nullable DepthTestMode var1) {
      this.field9 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method28(@Nullable PolygonDrawMode var1) {
      this.field10 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method29(boolean var1) {
      this.field11 = var1;
      return this;
   }

   @Contract("->this")
   public RenderPipelineBuilder method30() {
      this.field19 = true;
      return this;
   }

   @Contract("->this")
   public RenderPipelineBuilder method31() {
      this.field11 = null;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method32(@Nullable Bridge2_15 var1) {
      this.field15 = var1;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method33(boolean var1) {
      this.field12 = var1;
      this.field13 = var1;
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method34(boolean var1, boolean var2) {
      this.field12 = var1;
      this.field13 = var2;
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method35(@Nullable Boolean var1, @Nullable Boolean var2) {
      this.field12 = var1;
      this.field13 = var2;
      return this;
   }

   @Contract("->this")
   public RenderPipelineBuilder method36() {
      this.field12 = null;
      this.field13 = null;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method37(boolean var1) {
      this.field14 = var1;
      return this;
   }

   @Contract("->this")
   public RenderPipelineBuilder method38() {
      this.field14 = null;
      return this;
   }

   public RenderPipelineBuilder method39(Consumer<RenderPipelineBuilder> var1) {
      var1.accept(this);
      return this;
   }

   @Contract("_,_->this")
   public RenderPipelineBuilder method40(float var1, float var2) {
      this.field17 = var1;
      this.field18 = var2;
      return this;
   }

   @Contract("_->this")
   public RenderPipelineBuilder method41(RenderPipelineBuilder var1) {
      if (var1.field1 != null) {
         this.method3(var1.field1);
      }

      if (var1.field2 != null) {
         this.method6(var1.field2);
      }

      if (var1.field3 != null) {
         this.method9(var1.field3);
      }

      if (var1.field4 != null) {
         if (var1.field5 != null) {
            this.method11(var1.field4, var1.field5);
         } else {
            this.method11(var1.field4, this.field5);
         }
      } else if (var1.field5 != null) {
         this.method11(this.field4, var1.field5);
      }

      if (var1.field6 != null) {
         this.method16(var1.field6);
      }

      if (var1.field7 != null) {
         this.method19(var1.field7);
      }

      if (var1.field8 != null) {
         this.method24(var1.field8);
      }

      if (var1.field9 != null) {
         this.method27(var1.field9);
      }

      if (var1.field10 != null) {
         this.method28(var1.field10);
      }

      if (var1.field11 != null) {
         this.method29(var1.field11);
      }

      if (var1.field19) {
         this.method30();
      }

      if (var1.field12 != null) {
         if (var1.field13 != null) {
            this.method35(var1.field12, var1.field13);
         } else {
            this.method35(var1.field12, this.field13);
         }
      } else if (var1.field13 != null) {
         this.method35(this.field12, var1.field13);
      }

      if (var1.field14 != null) {
         this.method37(var1.field14);
      }

      if (var1.field15 != null) {
         this.method32(var1.field15);
      }

      if (var1.field17 != 0.0F) {
         if (var1.field18 != 0.0F) {
            this.method40(var1.field17, var1.field18);
         } else {
            this.method40(var1.field17, this.field18);
         }
      } else if (var1.field18 != 0.0F) {
         this.method40(this.field17, var1.field18);
      }

      return this;
   }

   public RenderPipelineBuilder method42(Bridge$Extension var1) {
      ResourceLocationBridge var2 = var1.bridge$vertexShader();
      if (var2 != null) {
         this.method9(var2);
      }

      ResourceLocationBridge var3 = var1.bridge$fragmentShader();
      if (var3 != null) {
         this.method6(var3);
      }

      Bridge2_2 var4 = var1.bridge$shaderDefines();
      if (var4 != null) {
         this.method17(var4);
      }

      List var5 = var1.bridge$samplers();
      if (var5 != null) {
         this.method19(var5);
      }

      List var6 = var1.bridge$uniforms();
      if (var6 != null) {
         this.method24(var6);
      }

      DepthTestMode var7 = var1.bridge$depthTestFunction();
      if (var7 != null) {
         this.method27(var7);
      }

      Boolean var8 = var1.bridge$cull();
      if (var8 != null) {
         this.method29(var8);
      }

      Boolean var9 = var1.bridge$writeColor();
      if (var9 != null) {
         this.field12 = var9;
      }

      Boolean var10 = var1.bridge$writeAlpha();
      if (var10 != null) {
         this.field13 = var10;
      }

      Boolean var11 = var1.bridge$writeDepth();
      if (var11 != null) {
         this.method37(var11);
      }

      Bridge2_15 var12 = var1.bridge$blendFunction();
      if (var12 != null) {
         this.method32(var12);
      }

      Bridge_63 var13 = var1.bridge$vertexFormat();
      if (var13 != null) {
         this.field4 = var13;
      }

      DrawMode var14 = var1.bridge$vertexFormatMode();
      if (var14 != null) {
         this.field5 = var14;
      }

      return this;
   }

   public abstract Bridge$Extension method43();

   @Contract("->new")
   public final Bridge_45 method44() {
      if (this.field1 == null) {
         throw new IllegalStateException("Missing location");
      } else if (this.field3 == null) {
         throw new IllegalStateException("Missing vertex shader");
      } else if (this.field2 == null) {
         throw new IllegalStateException("Missing fragment shader");
      } else if (this.field4 == null) {
         throw new IllegalStateException("Missing vertex buffer format");
      } else if (this.field5 == null) {
         throw new IllegalStateException("Missing vertex mode");
      } else {
         return this.method45();
      }
   }

   protected abstract Bridge_45 method45();

   protected abstract Bridge2$Extension2 method46();
}
