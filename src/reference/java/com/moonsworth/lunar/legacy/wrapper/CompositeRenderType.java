package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RenderStateShardBridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.ShaderManagerBridge;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.RenderPipelineBridge;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class CompositeRenderType implements RenderTypeBridge {
   @NotNull
   private final RenderPipelineBridge field1;
   private final String field2;
   private final List<RenderStateShardBridge> field3;

   @Override
   public String bridge$getName() {
      return this.field2;
   }

   @Override
   public void bridge$setupRenderState() {
      for (RenderStateShardBridge bridge192 : this.field3) {
         bridge192.bridge$setupState();
      }
   }

   @Override
   public void bridge$clearRenderState() {
      for (RenderStateShardBridge bridge192 : this.field3) {
         bridge192.bridge$clearState();
      }
   }

   @Override
   public DrawMode bridge$getVertexFormatMode() {
      return this.field1.bridge$vertexFormatMode();
   }

   @Override
   public VertexFormatBridge bridge$getVertexFormat() {
      return this.field1.bridge$vertexFormat();
   }

   @NotNull
   @Override
   public RenderPipelineBridge bridge$getRenderPipeline() {
      return this.field1;
   }

   @Override
   public Optional<ShaderManagerBridge> bridge$getShaderUniforms() {
      for (RenderStateShardBridge bridge192 : this.field3) {
         if (bridge192 instanceof ShaderRenderStateShard bridge19handler3) {
            return Optional.of((ShaderManagerBridge)bridge19handler3.field1.theEffectRenderer);
         }
      }

      return Optional.empty();
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof CompositeRenderType bridge20iterator2)) {
         return false;
      } else {
         if (!bridge20iterator2.canEqual(this)) {
            return false;
         }

         RenderPipelineBridge bridge_453 = this.field1;
         RenderPipelineBridge bridge_454 = bridge20iterator2.field1;
         if (bridge_453 == null ? bridge_454 == null : bridge_453.equals(bridge_454)) {
            String text5 = this.field2;
            String text6 = bridge20iterator2.field2;
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               List list7 = this.field3;
               List list8 = bridge20iterator2.field3;
               return list7 == null ? list8 == null : list7.equals(list8);
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof CompositeRenderType;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      RenderPipelineBridge bridge_453 = this.field1;
      number2 = number2 * 59 + (bridge_453 == null ? 43 : bridge_453.hashCode());
      String text4 = this.field2;
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      List list5 = this.field3;
      return number2 * 59 + (list5 == null ? 43 : list5.hashCode());
   }

   @Generated
   public CompositeRenderType(@NotNull RenderPipelineBridge bridge_451, String text2, List<RenderStateShardBridge> list3) {
      if (bridge_451 == null) {
         throw new NullPointerException("renderPipeline is marked non-null but is null");
      }

      this.field1 = bridge_451;
      this.field2 = text2;
      this.field3 = list3;
   }
}
