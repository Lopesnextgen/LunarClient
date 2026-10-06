package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge2_15;
import com.moonsworth.lunar.bridge.Bridge2_2;
import com.moonsworth.lunar.bridge.Bridge3_7;
import com.moonsworth.lunar.bridge.BridgeType3;
import com.moonsworth.lunar.bridge.BridgeType_12;
import com.moonsworth.lunar.bridge.BridgeType_6;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.Bridge.Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class BridgeHandler$Data implements Extension {
   @Nullable
   private final Horsestats14 field1;
   @Nullable
   private final Horsestats14 field2;
   @Nullable
   private final Bridge_63 field3;
   @Nullable
   private final BridgeType_6 field4;
   @Nullable
   private final Bridge2_2 field5;
   @Nullable
   private final List<String> field6;
   @Nullable
   private final List<Bridge3_7> field7;
   @Nullable
   private final BridgeType_12 field8;
   @Nullable
   private final BridgeType3 field9;
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
   public Horsestats14 bridge$vertexShader() {
      return this.field2;
   }

   @Nullable
   public Horsestats14 bridge$fragmentShader() {
      return this.field1;
   }

   @Nullable
   public Bridge2_2 bridge$shaderDefines() {
      return this.field5;
   }

   @Nullable
   public List<String> bridge$samplers() {
      return this.field6;
   }

   @Nullable
   public List<Bridge3_7> bridge$uniforms() {
      return this.field7;
   }

   @Nullable
   public Bridge2_15 bridge$blendFunction() {
      return this.field14;
   }

   @Nullable
   public BridgeType_12 bridge$depthTestFunction() {
      return this.field8;
   }

   @Nullable
   public BridgeType3 bridge$polygonMode() {
      return this.field9;
   }

   @Nullable
   public Boolean bridge$cull() {
      return this.field10;
   }

   @Nullable
   public Boolean bridge$writeColor() {
      return this.field11;
   }

   @Nullable
   public Boolean bridge$writeAlpha() {
      return this.field12;
   }

   @Nullable
   public Boolean bridge$writeDepth() {
      return this.field13;
   }

   @Nullable
   public Bridge_63 bridge$vertexFormat() {
      return this.field3;
   }

   @Nullable
   public BridgeType_6 bridge$vertexFormatMode() {
      return this.field4;
   }

   @Generated
   public BridgeHandler$Data(
      @Nullable Horsestats14 var1,
      @Nullable Horsestats14 var2,
      @Nullable Bridge_63 var3,
      @Nullable BridgeType_6 var4,
      @Nullable Bridge2_2 var5,
      @Nullable List<String> var6,
      @Nullable List<Bridge3_7> var7,
      @Nullable BridgeType_12 var8,
      @Nullable BridgeType3 var9,
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
