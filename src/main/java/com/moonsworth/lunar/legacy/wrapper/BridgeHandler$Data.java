package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge$Extension;
import com.moonsworth.lunar.bridge.Bridge2_15;
import com.moonsworth.lunar.bridge.Bridge2_2;
import com.moonsworth.lunar.bridge.Bridge3_7;
import com.moonsworth.lunar.bridge.PolygonDrawMode;
import com.moonsworth.lunar.bridge.DepthTestMode;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class BridgeHandler$Data implements Bridge$Extension {
   @Nullable
   private final ResourceLocationBridge field1;
   @Nullable
   private final ResourceLocationBridge field2;
   @Nullable
   private final Bridge_63 field3;
   @Nullable
   private final DrawMode field4;
   @Nullable
   private final Bridge2_2 field5;
   @Nullable
   private final List<String> field6;
   @Nullable
   private final List<Bridge3_7> field7;
   @Nullable
   private final DepthTestMode field8;
   @Nullable
   private final PolygonDrawMode field9;
   @Nullable
   private final Boolean field10;
   @Nullable
   private final Boolean field11;
   @Nullable
   private final Boolean field12;
   @Nullable
   private final Boolean field13;
   @Nullable
   private final Bridge2_15 field14;

   @Nullable
   @Override
   public ResourceLocationBridge bridge$vertexShader() {
      return this.field2;
   }

   @Nullable
   @Override
   public ResourceLocationBridge bridge$fragmentShader() {
      return this.field1;
   }

   @Nullable
   @Override
   public Bridge2_2 bridge$shaderDefines() {
      return this.field5;
   }

   @Nullable
   @Override
   public List<String> bridge$samplers() {
      return this.field6;
   }

   @Nullable
   @Override
   public List<Bridge3_7> bridge$uniforms() {
      return this.field7;
   }

   @Nullable
   @Override
   public Bridge2_15 bridge$blendFunction() {
      return this.field14;
   }

   @Nullable
   @Override
   public DepthTestMode bridge$depthTestFunction() {
      return this.field8;
   }

   @Nullable
   @Override
   public PolygonDrawMode bridge$polygonMode() {
      return this.field9;
   }

   @Nullable
   @Override
   public Boolean bridge$cull() {
      return this.field10;
   }

   @Nullable
   @Override
   public Boolean bridge$writeColor() {
      return this.field11;
   }

   @Nullable
   @Override
   public Boolean bridge$writeAlpha() {
      return this.field12;
   }

   @Nullable
   @Override
   public Boolean bridge$writeDepth() {
      return this.field13;
   }

   @Nullable
   @Override
   public Bridge_63 bridge$vertexFormat() {
      return this.field3;
   }

   @Nullable
   @Override
   public DrawMode bridge$vertexFormatMode() {
      return this.field4;
   }

   @Generated
   public BridgeHandler$Data(
      @Nullable ResourceLocationBridge var1,
      @Nullable ResourceLocationBridge var2,
      @Nullable Bridge_63 var3,
      @Nullable DrawMode var4,
      @Nullable Bridge2_2 var5,
      @Nullable List<String> var6,
      @Nullable List<Bridge3_7> var7,
      @Nullable DepthTestMode var8,
      @Nullable PolygonDrawMode var9,
      @Nullable Boolean var10,
      @Nullable Boolean var11,
      @Nullable Boolean var12,
      @Nullable Boolean var13,
      @Nullable Bridge2_15 var14
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
      this.field10 = var10;
      this.field11 = var11;
      this.field12 = var12;
      this.field13 = var13;
      this.field14 = var14;
   }
}
