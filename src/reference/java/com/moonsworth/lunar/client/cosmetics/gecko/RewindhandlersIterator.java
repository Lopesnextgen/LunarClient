package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.gecko.VectorConverter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.joml.Vector3f;

public class RewindhandlersIterator implements BoneHierarchySerializer {
   private static final Map<String, BoneHierarchySerializer> field1 = new HashMap<>();
   private static final BoneHierarchySerializer field2 = new RewindhandlersIterator();

   public RewindhandlersIterator() {
   }

   public static void method1(String text0, BoneHierarchySerializer rewindhandlers1) {
      field1.put(text0, rewindhandlers1);
   }

   public static BoneHierarchySerializer method2(String text0) {
      BoneHierarchySerializer rewindhandlers1 = field1.get(text0);
      return rewindhandlers1 == null ? field2 : rewindhandlers1;
   }

   @Override
   public BedrockGeometry method1(com.moonsworth.lunar.client.cosmetics.gecko.BoneHierarchyBuilder rewindhandlers21) {
      BedrockGeometry rewindhandlers2_22 = new BedrockGeometry();
      rewindhandlers2_22.field2 = rewindhandlers21.field2;

      for (com.moonsworth.lunar.client.cosmetics.gecko.BoneHierarchyNode rewindhandlers4 : rewindhandlers21.field1.values()) {
         rewindhandlers2_22.field1.add(this.method2(rewindhandlers4, rewindhandlers21.field2, null));
      }

      return rewindhandlers2_22;
   }

   @Override
   public IBoneSerializer method2(
      com.moonsworth.lunar.client.cosmetics.gecko.BoneHierarchyNode rewindhandlers1, ModelDescription rewindhandlers62, IBoneSerializer iboneserializer3
   ) {
      IBoneSerializer iboneserializer4 = new IBoneSerializer();
      BedrockBone rewindhandlers4_25 = rewindhandlers1.field2;
      Vector3f vector3f6 = VectorConverter.method3(VectorConverter.method1(rewindhandlers4_25.method23()));
      Vector3f vector3f7 = VectorConverter.method3(VectorConverter.method1(rewindhandlers4_25.method15()));
      vector3f6.mul(-1.0F, -1.0F, 1.0F);
      iboneserializer4.field4 = rewindhandlers4_25.method5();
      iboneserializer4.setModelRendererName(rewindhandlers4_25.getName());
      iboneserializer4.setRotationX((float)Math.toRadians(vector3f6.x()));
      iboneserializer4.setRotationY((float)Math.toRadians(vector3f6.y()));
      iboneserializer4.setRotationZ((float)Math.toRadians(vector3f6.z()));
      iboneserializer4.setPivotX(-vector3f7.x());
      iboneserializer4.setPivotY(vector3f7.y());
      iboneserializer4.setPivotZ(vector3f7.z());
      if (!ArrayUtils.isEmpty(rewindhandlers4_25.method3())) {
         for (ModelCube rewindhandlers511 : rewindhandlers4_25.method3()) {
            iboneserializer4.field2.add(CubeMesh.method1(rewindhandlers511, rewindhandlers62, iboneserializer4.field4 == null ? null : iboneserializer4.field4 / 16.0, rewindhandlers4_25.method9()));
         }
      }

      for (com.moonsworth.lunar.client.cosmetics.gecko.BoneHierarchyNode rewindhandlers13 : rewindhandlers1.field1.values()) {
         iboneserializer4.field1.add(this.method2(rewindhandlers13, rewindhandlers62, iboneserializer4));
      }

      return iboneserializer4;
   }
}
