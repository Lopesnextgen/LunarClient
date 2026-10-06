package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.MinimapOptionWidget;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.LinkedHashMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class PlayerMinimapLayer extends MinimapLayer<Markers2> {
   public PlayerMinimapLayer(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap1, double value2, double value4, float value6, float value7, Markers2 markers28) {
      super(minimap1, value2, value4, value6, value7, markers28);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, Bridge5Extension_5 bridge5extension_52, float value3, float value4, float value5) {
      mixinhelper_41.push();
      mixinhelper_41.method38(value4, value5, 0.0F);
      mixinhelper_41.method42(-value3);
      mixinhelper_41.scale(1.5F, 1.5F, 1.0F);
      mixinhelper_41.method44(arg0 -> arg0.method29().method16());
      ((Markers2)this.method10()).method6().method5(mixinhelper_41, 2.0F);
      mixinhelper_41.pop();
   }

   @Override
   public void method2(MinimapOptionWidget calculator2iterator1, MixinHelper_4 mixinhelper_42, Data2 data23) {
      float value4 = data23.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 9.0F;
      float value5 = data23.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F;
      Markers2 markers26 = (Markers2)this.method10();
      Markers markers7 = Ref.method4().method40().method87();
      String[] items8 = new String[]{
         markers26.getOwnerName() + (String)markers7.getOwnerSuffix().get(),
         TextBridge.getTextContent(((TextComponent)Component.empty().append(markers7.getOwnerComponent(markers26))).append(markers7.getFlagComponent(markers26)))
      };
      float value9 = 0.0F;

      for (String text13 : items8) {
         value9 = Math.max(value9, FontRegistry.method9().method4(text13) + 8.0F);
      }

      mixinhelper_42.push();
      mixinhelper_42.method38(0.0F, 0.0F, 10.0F);
      LcuiScreen.method117(mixinhelper_42, value4, value5, value9, FontRegistry.method9().getHeight() * items8.length * 2 + 5, 5.0F, Integer.MIN_VALUE);
      int number15 = 0;

      for (String text14 : items8) {
         FontRegistry.method9().method17(mixinhelper_42, text14, value4 + 4.0F, value5 + 2.0F + number15, -1, false);
         number15 += FontRegistry.method9().getHeight() * 2;
      }

      mixinhelper_42.pop();
   }

   @Nullable
   @Override
   public LinkedHashMap<String, Runnable> method6() {
      return null;
   }
}
