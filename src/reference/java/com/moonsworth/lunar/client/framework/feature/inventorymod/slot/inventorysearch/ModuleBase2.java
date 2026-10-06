package com.moonsworth.lunar.client.framework.feature.inventorymod.slot.inventorysearch;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.MixinHelper;
import com.moonsworth.lunar.client.framework.feature.mod.MixinHelperType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.function.Supplier;
import lombok.Generated;
import org.joml.Vector2i;

@Annotation2(min = 1)
public class ModuleBase2 extends com.moonsworth.lunar.client.framework.feature.mod.ModuleBase2 {
   private final Supplier<MixinHelperType> field2;
   private final Supplier<Double> field3;
   private final Supplier<Gui2Extension> field4;
   private final Supplier<String> field5;
   private final Supplier<Boolean> field6;
   private final Click4 field7 = new Click4(0.0, Click4.Type.SIN_OUT);
   private final Click4 field8 = new Click4(0.0, Click4.Type.SIN_OUT);
   private String field9 = "";
   private boolean field10;
   private boolean field11;

   @Override
   public boolean method1(Bridge5Extension6 var1) {
      return var1 instanceof Bridge5Extension_3;
   }

   @Override
   public void method2(Bridge5Extension6 var1, MixinHelper_4 var2, int var3, int var4, float var5) {
      double var6 = this.field3.get();
      var2.push();
      var2.method40((float)var6, (float)var6);
      var3 = (int)(var3 / var6);
      var4 = (int)(var4 / var6);
      MixinHelper.field1.setTheme(this.field2.get());
      MixinHelper.field1.method1("InvSearchOverlay", var1, var2, var3, var4, false);
      MixinHelper.field1.method3();
      MixinHelper.field1.method20(var6, var6, 1.0);
      double var8 = LcuiScreen.method151().getScaledWidth() / var6;
      double var10 = LcuiScreen.method151().getScaledHeight() / var6;

      Vector2i var12 = switch ((Gui2Extension)this.field4.get()) {
         case TOP_LEFT -> new Vector2i(6, 6);
         case TOP_MIDDLE -> new Vector2i((int)(var8 / 2.0 - 50.0), 6);
         case BOTTOM_LEFT -> new Vector2i(6, (int)(var10 - 20.0));
         default -> new Vector2i((int)(var8 / 2.0 - 50.0), (int)(var10 - 20.0));
      };
      if (this.field6.get()) {
         int var13 = (int)(25.0 * (1.0 - this.field8.getValue()));
         if (this.field4.get() == Gui2Extension.TOP_LEFT || this.field4.get() == Gui2Extension.TOP_MIDDLE) {
            var13 *= -1;
         }

         var12.y += var13;
      }

      MixinHelper.field1.method37(var12.x - 2, var12.y - 2, 104 + (int)this.field7.getValue(), 14);
      this.field9 = MixinHelper.field1.method45("InvSearchOverlay-search", var12.x, var12.y, 100, 10, this.field5.get(), false);
      String var17 = Click2.formatResult(this.field9, true);
      if (var17 != null) {
         this.field9 = "";
         var17 = " = " + var17;
         int var14 = MixinHelper.field1.getStringWidth(var17);
         MixinHelper.field1.method3();
         MixinHelper.field1.method18(var12.x - 2, var12.y - 2, 104 + (int)this.field7.getValue(), 14);
         MixinHelper.field1.method26(var17, var12.x + 100, var12.y + 1, true);
         MixinHelper.field1.method4();
         this.field7.animateTo(var14, 100L);
      } else {
         this.field7.animateTo(0.0, 100L);
      }

      MixinHelper.field1.method4();
      MixinHelper.field1.end();
      var2.pop();
   }

   @Override
   public boolean method3(Bridge5Extension6 var1, int var2, int var3, int var4) {
      double var5 = this.field3.get() / ThreadModuleDump63.method4().method40().method96().method3(var1);
      return MixinHelper.field1.method27("InvSearchOverlay", (int)(var2 / var5), (int)(var3 / var5), var4);
   }

   @Override
   public boolean method4(Bridge5Extension6 var1, int var2, int var3, int var4) {
      double var5 = this.field3.get() / ThreadModuleDump63.method4().method40().method96().method3(var1);
      return MixinHelper.field1.method28("InvSearchOverlay", (int)(var2 / var5), (int)(var3 / var5), var4);
   }

   @Override
   public boolean method10(Bridge5Extension6 var1, Bridge_7 var2) {
      if (!this.isOpen()) {
         return false;
      } else if (this.field11) {
         this.field11 = false;
         return true;
      } else {
         return MixinHelper.field1.method29("InvSearchOverlay", var2);
      }
   }

   @Override
   public void onClose() {
      super.onClose();
      if (this.field10 && this.field9.isBlank()) {
         this.field8.animateTo(0.0, 0L);
         this.field10 = false;
      }
   }

   public boolean isOpen() {
      return !this.field6.get() || this.field10;
   }

   public void open() {
      this.field10 = true;
      this.field8.animateTo(1.0, 100L);
      MixinHelper.field1.method10("textinput", "InvSearchOverlay", "InvSearchOverlay-search");
   }

   public void close() {
      this.field10 = false;
      this.field8.animateTo(0.0, 100L);
   }

   public void method6() {
      this.field9 = "";
      MixinHelper.field1.method5("InvSearchOverlay");
   }

   public void method10() {
      this.field11 = true;
   }

   @Generated
   public ModuleBase2(Supplier<MixinHelperType> var1, Supplier<Double> var2, Supplier<Gui2Extension> var3, Supplier<String> var4, Supplier<Boolean> var5) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var4;
      this.field6 = var5;
   }

   @Generated
   public String method11() {
      return this.field9;
   }
}
