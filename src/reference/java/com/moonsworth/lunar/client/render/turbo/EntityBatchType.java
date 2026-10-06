package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import org.joml.Vector3d;

@Annotation2(min = 8)
public abstract class EntityBatchType<ID> {
   public static final LivingEntityBatchType field1 = new LivingEntityBatchType();
   public static final List<EntityBatchType<?>> field2 = List.of(field1);

   public abstract ID method1(Vec3Bridge var1);

   public abstract boolean method2(Vec3Bridge var1, Vector3d var2);

   public abstract void method3(List<ID> var1, Vec3Bridge var2);

   public abstract AxisAlignedBBBridge method4(ID var1);

   public abstract Vector3iBridge method5(ID var1);

   public abstract Vector3iBridge method6(ID var1);

   public abstract boolean method7(Bridge14_3 var1, ID var2, AxisAlignedBBBridge var3);

   public abstract boolean method8(Bridge14_3 var1, ID var2, AxisAlignedBBBridge var3, int var4);

   public void method9(TurboEngineManager var1) {
   }

   public void method10(TurboEngineManager var1) {
   }
}
