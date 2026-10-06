package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.client.render.turbo.FragDataFactory;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.FragData;
import com.moonsworth.lunar.client.render.turbo.PathSearchContext;
import com.moonsworth.lunar.client.render.turbo.RenderGroup;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@Annotation2(min = 8)
public class SpriteHandler implements SpriteSource {
   private final RenderGroup field1 = new RenderGroup();

   @Override
   public void method1(FragData var1) {
      Bridge.method9().bridge$getRenderBuffers().bridge$bufferSource().bridge$contributeWeightedOrderings(var1.method3());
   }

   @Override
   public void method3(BatchEntityType var1, RenderLayerBridge var2, Runnable var3, boolean var4) {
      Bridge.method9().bridge$getRenderBuffers().bridge$bufferSource().bridge$renderSortedBatchable(var2, var3);
   }

   @Override
   public void method4(BatchEntityType var1) {
   }

   @Override
   public void method5() {
      this.field1.method4();
   }

   @Override
   public void method6() {
      this.field1.method5();
   }

   @Override
   public Bridge4_6 method7(Map<RenderLayerBridge, Bridge4Extension> var1, @NotNull RenderLayerBridge var2) {
      var2 = PathSearchContext.method1(var2);
      this.field1.method3(var2);
      return SpriteSource.super.method7(var1, var2);
   }

   @Override
   public <T> FragData method8(Object var1, FragDataFactory<T> var2, List<T> var3) {
      return var2.createFragData(var3, this.field1.method1());
   }

   @Override
   public void method9() {
      this.field1.reset();
   }

   @Generated
   public RenderGroup method10() {
      return this.field1;
   }
}
