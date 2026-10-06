package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.MinimapOptionWidget;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.LinkedHashMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class Minimap2Impl extends Minimap2_2<Markers2> {
   public Minimap2Impl(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, double var2, double var4, float var6, float var7, Markers2 var8) {
      super(var1, var2, var4, var6, var7, var8);
   }

   @Override
   public void method1(MixinHelper_4 var1, Bridge5Extension_5 var2, float var3, float var4, float var5) {
      var1.push();
      var1.method38(var4, var5, 0.0F);
      var1.method42(-var3);
      var1.scale(1.5F, 1.5F, 1.0F);
      var1.method44(var0 -> var0.method29().method16());
      ((Markers2)this.method10()).method6().method5(var1, 2.0F);
      var1.pop();
   }

   @Override
   public void method2(MinimapOptionWidget var1, MixinHelper_4 var2, Data2 var3) {
      float var4 = var3.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 9.0F;
      float var5 = var3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F;
      Markers2 var6 = (Markers2)this.method10();
      Markers var7 = ThreadModuleDump63.method4().method40().method87();
      String[] var8 = new String[]{
         var6.getOwnerName() + (String)var7.getOwnerSuffix().get(),
         AdventureTextBridge.getTextContent(((TextComponent)Component.empty().append(var7.getOwnerComponent(var6))).append(var7.getFlagComponent(var6)))
      };
      float var9 = 0.0F;

      for (String var13 : var8) {
         var9 = Math.max(var9, FontRegistry.method9().method4(var13) + 8.0F);
      }

      var2.push();
      var2.method38(0.0F, 0.0F, 10.0F);
      LcuiScreen.method117(var2, var4, var5, var9, FontRegistry.method9().getHeight() * var8.length * 2 + 5, 5.0F, Integer.MIN_VALUE);
      int var15 = 0;

      for (String var14 : var8) {
         FontRegistry.method9().method17(var2, var14, var4 + 4.0F, var5 + 2.0F + var15, -1, false);
         var15 += FontRegistry.method9().getHeight() * 2;
      }

      var2.pop();
   }

   @Nullable
   @Override
   public LinkedHashMap<String, Runnable> method6() {
      return null;
   }
}
