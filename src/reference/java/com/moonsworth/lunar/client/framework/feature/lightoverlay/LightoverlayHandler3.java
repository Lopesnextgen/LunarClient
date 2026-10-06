package com.moonsworth.lunar.client.framework.feature.lightoverlay;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.client.mod.render.lightoverlay.LightOverlay;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;

class LightoverlayHandler3 implements Lightoverlay {
   private final LightOverlay field1;
   private final Bridge2_32 field2;
   private final int field3;

   protected LightoverlayHandler3(AbstractRenderContext var1, LightOverlay var2) {
      this.field1 = var2;
      this.field2 = var1.method10(LunarRenderTypes.field15);
      this.field2.method1();
      this.field3 = var2.getSkylightSubtract();
   }

   @Override
   public void method1(double var1, double var3, double var5, byte var7, byte var8) {
      int var9 = this.field1.method15(var7, var8, this.field3, (float)(var1 + var5));
      float var10 = ThreadModuleDump23.method5(var9);
      float var11 = ThreadModuleDump23.greenFloat(var9);
      float var12 = ThreadModuleDump23.method7(var9);
      float var13 = ThreadModuleDump23.method8(var9) * 0.66F;
      this.field2.method2(var1, var3, var5).method8(var10, var11, var12, var13).method16();
      this.field2.method2(var1, var3, var5 + 1.0).method8(var10, var11, var12, var13).method16();
      this.field2.method2(var1 + 1.0, var3, var5 + 1.0).method8(var10, var11, var12, var13).method16();
      this.field2.method2(var1 + 1.0, var3, var5).method8(var10, var11, var12, var13).method16();
   }

   @Override
   public void end() {
      this.field2.method17(BufferBuildMode.IMMEDIATE);
   }
}
