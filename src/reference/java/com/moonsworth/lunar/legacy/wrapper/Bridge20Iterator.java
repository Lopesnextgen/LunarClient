package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RenderStateLifecycleBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge6_8;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_45;
import com.moonsworth.lunar.bridge.Bridge_63;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Bridge20Iterator implements RenderLayerBridge {
   @NotNull
   private final Bridge_45 field1;
   private final String field2;
   private final List<RenderStateLifecycleBridge> field3;

   public String bridge$getName() {
      return this.field2;
   }

   public void bridge$setupRenderState() {
      for (RenderStateLifecycleBridge var2 : this.field3) {
         var2.bridge$setupState();
      }
   }

   public void bridge$clearRenderState() {
      for (RenderStateLifecycleBridge var2 : this.field3) {
         var2.bridge$clearState();
      }
   }

   public DrawMode bridge$getVertexFormatMode() {
      return this.field1.bridge$vertexFormatMode();
   }

   public Bridge_63 bridge$getVertexFormat() {
      return this.field1.bridge$vertexFormat();
   }

   @NotNull
   public Bridge_45 bridge$getRenderPipeline() {
      return this.field1;
   }

   public Optional<Bridge6_8> bridge$getShaderUniforms() {
      for (RenderStateLifecycleBridge var2 : this.field3) {
         if (var2 instanceof Bridge19Handler var3) {
            return Optional.of((Bridge6_8)var3.field1.theEffectRenderer);
         }
      }

      return Optional.empty();
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Bridge20Iterator var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         Bridge_45 var3 = this.field1;
         Bridge_45 var4 = var2.field1;
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.field2;
            String var6 = var2.field2;
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               List var7 = this.field3;
               List var8 = var2.field3;
               return var7 == null ? var8 == null : var7.equals(var8);
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Bridge20Iterator;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      Bridge_45 var3 = this.field1;
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.field2;
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      List var5 = this.field3;
      return var2 * 59 + (var5 == null ? 43 : var5.hashCode());
   }

   @Generated
   public Bridge20Iterator(@NotNull Bridge_45 var1, String var2, List<RenderStateLifecycleBridge> var3) {
      if (var1 == null) {
         throw new NullPointerException("renderPipeline is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }
}
