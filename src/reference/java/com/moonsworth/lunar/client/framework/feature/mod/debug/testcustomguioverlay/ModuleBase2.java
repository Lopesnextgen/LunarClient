package com.moonsworth.lunar.client.framework.feature.mod.debug.testcustomguioverlay;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.MixinHelper;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class ModuleBase2 extends com.moonsworth.lunar.client.framework.feature.mod.ModuleBase2 {
   @Override
   public boolean method1(Bridge5Extension6 var1) {
      return "testoverlay".equals(this.method11(var1));
   }

   @Override
   public void method2(Bridge5Extension6 var1, MixinHelper_4 var2, int var3, int var4, float value) {
      MixinHelper.field1.method1("TestOverlay", var1, var2, var3, var4, false);
      String var6 = MixinHelper.field1.method45("TestOverlay-Textinput", 10, 10, 100, 10, "Text box", true);
      if (MixinHelper.field1.method35("Text: " + var6, 10, 30, 100, 16) != -1) {
         System.out.println("Text button was clicked with '" + var6 + "' as input!");
      }

      MixinHelper.field1.end();
   }

   @Override
   public boolean method3(Bridge5Extension6 var1, int var2, int var3, int var4) {
      return MixinHelper.field1.method27("TestOverlay", var2, var3, var4);
   }

   @Override
   public boolean method4(Bridge5Extension6 var1, int var2, int var3, int var4) {
      return MixinHelper.field1.method28("TestOverlay", var2, var3, var4);
   }

   @Override
   public boolean method10(Bridge5Extension6 var1, Bridge_7 var2) {
      return MixinHelper.field1.method29("TestOverlay", var2);
   }

   @Override
   public void method9() {
      System.out.println("Opened TestOverlay");
   }

   @Override
   public void onClose() {
      System.out.println("Closed TestOverlay");
      MixinHelper.field1.method5("TestOverlay");
   }
}
