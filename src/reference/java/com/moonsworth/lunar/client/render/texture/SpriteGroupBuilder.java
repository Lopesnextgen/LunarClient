package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.TransparencyMode;
import com.moonsworth.lunar.client.render.turbo.TransparencyLayerMap;
import com.moonsworth.lunar.client.render.turbo.FragDataFactory;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.FragData;
import com.moonsworth.lunar.client.render.turbo.RenderGroup;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.render.particle.Group;

@Annotation2(min = 8)
public class SpriteGroupBuilder implements SpriteSource {
   private final SpriteSource field1;
   private final Map<RenderLayerBridge, Integer> field2 = new HashMap<>();

   @Override
   public void method1(FragData var1) {
      this.field1.method1(var1);
   }

   @Override
   public void method3(BatchEntityType var1, RenderLayerBridge var2, Runnable var3, boolean var4) {
      this.field1.method3(var1, var2, var3, var4);
   }

   @Override
   public void method4(BatchEntityType var1) {
      this.field1.method4(var1);
   }

   @Override
   public void method5() {
      this.field1.method5();
   }

   @Override
   public void method6() {
      this.field1.method6();
   }

   @Override
   public Bridge4_6 method7(Map<RenderLayerBridge, Bridge4Extension> var1, @NotNull RenderLayerBridge var2) {
      this.field2.compute(var2, (var0, var1x) -> var1x == null ? 1 : var1x + 1);
      return this.field1.method7(var1, var2);
   }

   @Override
   public <T> FragData method8(@Nullable Object var1, FragDataFactory<T> var2, List<T> var3) {
      if (!this.field2.isEmpty()) {
         StringBuilder var4 = new StringBuilder("Rebuild ");
         if (var1 != null) {
            var4.append(var1.toString());
         }

         var4.append("\n").append("RenderTypes: ");
         boolean var5 = true;

         for (Entry var7 : this.field2.entrySet()) {
            if (!var5) {
               var4.append(", ");
            }

            var5 = false;
            var4.append(((RenderLayerBridge)var7.getKey()).bridge$getName()).append("(").append(var7.getValue()).append(")");
         }

         var4.append("\nCreated Group - size: ").append(var3.size());
         if (this.field1 instanceof SpriteHandler var17) {
            RenderGroup var19 = var17.method10();
            if (var19 != null) {
               var4.append("\n");
               TransparencyLayerMap var8 = var19.method1();

               for (TransparencyMode var12 : TransparencyMode.values()) {
                  Map var13 = (Map)var8.get(var12);
                  if (var13 != null && !var13.isEmpty()) {
                     var4.append(" - ").append(var12.name()).append("(").append(var13.size()).append(")\n");

                     for (Entry var15 : var13.entrySet()) {
                        Pair var16 = (Pair)var15.getKey();
                        var4.append("   + ")
                           .append(((RenderLayerBridge)var16.key()).bridge$getName())
                           .append(" <-> ")
                           .append(((RenderLayerBridge)var16.first()).bridge$getName())
                           .append(" (")
                           .append(var15.getValue())
                           .append(")\n");
                     }
                  }
               }
            }
         }

         System.out.println(var4);
      }

      return this.field1.method8(var1, var2, var3);
   }

   @Override
   public void method9() {
      this.field2.clear();
      this.field1.method9();
   }

   @Generated
   public SpriteGroupBuilder(SpriteSource var1) {
      this.field1 = var1;
   }
}
