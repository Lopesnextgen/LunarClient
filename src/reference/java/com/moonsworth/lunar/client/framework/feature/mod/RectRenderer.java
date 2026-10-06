package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.LineBatchRenderer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class RectRenderer extends GuiComponent {
   private boolean field2;
   private IntRectangle field3;

   public RectRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   @Override
   public void end() {
      this.method17();
   }

   public void method1(int value, int value2, int value3, int value4, int value5) {
      if (!this.method3().method5(value, value2, value3, value4)) {
         if (this.method1().field7) {
            this.method16().method45(arg5x -> arg5x.method1(value, value2, value + value3, value2 + value4, value5));
            this.method16().method44(arg6 -> {
               if (!this.field2) {
                  this.field2 = true;
                  LineBatchRenderer.method3(arg6.method29());
               }

               LineBatchRenderer.method2(value, value2, value3, value4, value5);
               if (Ref.MC_VERSION > 5 && Ref.MC_VERSION < 29) {
                  if (this.field3 == null) {
                     this.field3 = IntRectangle.method3(value, value2, value3, value4);
                  } else {
                     this.field3 = IntRectangle.method7(this.field3, IntRectangle.method3(value, value2, value3, value4));
                  }
               }
            });
         }
      }
   }

   public void method17() {
      if (this.field2) {
         this.field2 = false;
         this.method16().method44(arg1 -> {
            Runnable runnable2 = () -> LineBatchRenderer.method4(arg1.method29());
            if (Ref.MC_VERSION > 5 && Ref.MC_VERSION < 29) {
               this.method14().method2(this.field3, runnable2);
               this.method14().method1(this.field3, 0.0F);
               this.field3 = null;
            } else {
               runnable2.run();
            }
         });
      }
   }
}
