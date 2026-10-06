package com.moonsworth.lunar.client.cosmetics.emote;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.BOBJData;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;
import org.jetbrains.annotations.Nullable;

public class BOBJModel {
   private final ImmutableList<BOBJMesh> field1;
   @Nullable
   private final AtomicReference<Object> field2 = new AtomicReference<>();

   public BOBJModel(BOBJData data) {
      Builder builder2 = new Builder();

      for (CompiledData compileddata4 : BOBJLoader.loadMeshes(data).values()) {
         builder2.add(BOBJMesh.method5(compileddata4));
      }

      this.field1 = builder2.build();
   }

   @VersionGate(max = 7)
   public void method1(AbstractRenderContext bridgeextension_91, ResourceLocationBridge horsestats142) {
      this.field1.forEach(arg2x -> arg2x.method2(bridgeextension_91, horsestats142));
   }

   @VersionGate(min = 6)
   public void method2(RenderTypeBridge bridge201, BridgeExtension2_11 bridgeextension2_112, int value) {
      RenderSystemBridge bridge124 = Bridge.method42();
      this.field1.forEach(arg4x -> arg4x.method3(bridge201, bridgeextension2_112, bridge124, value));
   }

   public int method3() {
      return this.field1.stream().mapToInt(BOBJMesh::method1).sum();
   }

   public void free() {
      UnmodifiableIterator unmodifiableiterator1 = this.field1.iterator();

      while (unmodifiableiterator1.hasNext()) {
         BOBJMesh colorsaturation2 = (BOBJMesh)unmodifiableiterator1.next();
         colorsaturation2.delete();
      }
   }

   @Nullable
   private AxisAlignedBBBridge method4() {
      AxisAlignedBBBridge horsestats121 = null;
      UnmodifiableIterator unmodifiableiterator2 = this.field1.iterator();

      while (unmodifiableiterator2.hasNext()) {
         BOBJMesh colorsaturation3 = (BOBJMesh)unmodifiableiterator2.next();
         if (horsestats121 == null) {
            horsestats121 = colorsaturation3.method4();
         } else {
            horsestats121 = horsestats121.method6(colorsaturation3.method4());
         }
      }

      return horsestats121;
   }

   @Generated
   public ImmutableList<BOBJMesh> method5() {
      return this.field1;
   }

   @Nullable
   @Generated
   public AxisAlignedBBBridge method6() {
      Object obj1 = this.field2.get();
      if (obj1 == null) {
         synchronized (this.field2) {
            obj1 = this.field2.get();
            if (obj1 == null) {
               AxisAlignedBBBridge horsestats123 = this.method4();
               obj1 = horsestats123 == null ? this.field2 : horsestats123;
               this.field2.set(obj1);
            }
         }
      }

      return (AxisAlignedBBBridge)(obj1 == this.field2 ? null : obj1);
   }
}
