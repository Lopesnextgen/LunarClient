package com.moonsworth.lunar.client.util.colorsaturation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.BOBJData;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;
import org.jetbrains.annotations.Nullable;

public class Colorsaturation2 {
   private final ImmutableList<Colorsaturation> field1;
   @Nullable
   private final AtomicReference<Object> field2 = new AtomicReference<>();

   public Colorsaturation2(BOBJData var1) {
      Builder var2 = new Builder();

      for (CompiledData var4 : BOBJLoader.loadMeshes(var1).values()) {
         var2.add(Colorsaturation.method5(var4));
      }

      this.field1 = var2.build();
   }

   @Annotation2(max = 7)
   public void method1(AbstractRenderContext var1, ResourceLocationBridge var2) {
      this.field1.forEach(var2x -> var2x.method2(var1, var2));
   }

   @Annotation2(min = 6)
   public void method2(RenderLayerBridge var1, BridgeExtension2_11 var2, int var3) {
      RenderSystemBridge var4 = Bridge.method42();
      this.field1.forEach(var4x -> var4x.method3(var1, var2, var4, var3));
   }

   public int method3() {
      return this.field1.stream().mapToInt(Colorsaturation::method1).sum();
   }

   public void free() {
      UnmodifiableIterator var1 = this.field1.iterator();

      while (var1.hasNext()) {
         Colorsaturation var2 = (Colorsaturation)var1.next();
         var2.delete();
      }
   }

   @Nullable
   private AxisAlignedBBBridge method4() {
      AxisAlignedBBBridge var1 = null;
      UnmodifiableIterator var2 = this.field1.iterator();

      while (var2.hasNext()) {
         Colorsaturation var3 = (Colorsaturation)var2.next();
         if (var1 == null) {
            var1 = var3.method4();
         } else {
            var1 = var1.method6(var3.method4());
         }
      }

      return var1;
   }

   @Generated
   public ImmutableList<Colorsaturation> method5() {
      return this.field1;
   }

   @Nullable
   @Generated
   public AxisAlignedBBBridge method6() {
      Object var1 = this.field2.get();
      if (var1 == null) {
         synchronized (this.field2) {
            var1 = this.field2.get();
            if (var1 == null) {
               AxisAlignedBBBridge var3 = this.method4();
               var1 = var3 == null ? this.field2 : var3;
               this.field2.set(var1);
            }
         }
      }

      return (AxisAlignedBBBridge)(var1 == this.field2 ? null : var1);
   }
}
