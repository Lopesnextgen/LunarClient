package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.ClothCloakUpdater;

public class MeshPassRunner {
   public MeshPassRunner() {
   }

   public static void method1(com.moonsworth.lunar.client.cosmetics.ClothCloakSolver fov30, VertexSink fov2$extension1) {
      boolean flag2 = true;
      Runnable runnable3 = fov2$extension1::method2;
      ClothCloakUpdater.Extension extension4 = (arg1x, arg2x, arg3x, arg4x) -> fov2$extension1.method1(arg2x, arg3x, arg1x.x, arg1x.y, arg1x.z, arg4x.x, arg4x.y, arg4x.z);
      ClothCloakUpdater.method11(fov30, flag2, extension4, runnable3);
      ClothCloakUpdater.method10(fov30, flag2, extension4, runnable3);
      ClothCloakUpdater.method12(fov30, flag2, extension4, runnable3);
      ClothCloakUpdater.method13(fov30, flag2, extension4, runnable3);
      ClothCloakUpdater.method14(fov30, flag2, extension4, runnable3);
      ClothCloakUpdater.method15(fov30, flag2, extension4, runnable3);
   }
}
