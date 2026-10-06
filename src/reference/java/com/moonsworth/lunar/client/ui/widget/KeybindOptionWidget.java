package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.AutoTextHotkeyOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;
import lombok.Generated;

public abstract class KeybindOptionWidget<T extends ClientOption<?>> extends OptionWidget<T> {
   protected TextLabelWidget field16;

   public KeybindOptionWidget(T var1, GuiWidget var2) {
      super((T)var1, var2);
   }

   public void method1(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      if ((!(this.option instanceof SimpleKeybindOption) || !this.option.get().equals("NONE"))
         && (!(this.option instanceof ModifierKeybindOption) || !((KeyCombo)this.option.get()).method8().equals(KeyCode.KEY_NONE))
         && (!(this.option instanceof AutoTextHotkeyOption) || !((KeyCombo)((AutoTextHotkeyOption)this.option).method7().get()).equals(KeyCode.KEY_NONE))
         )
       {
         Set var3;
         if (this.option instanceof AbstractKeybindOption var4) {
            var3 = var4.method9();
         } else if (this.option instanceof AutoTextHotkeyOption var5) {
            var3 = var5.method7().RHCHRCRHICICRRRIRROCHROOHICRRC();
         } else {
            var3 = null;
         }

         if (var3 != null && !var3.isEmpty()) {
            String var7 = this.method1(
               "clashesWith", new Object[]{AdventureChatFormatting.RED + ThreadModuleDump63.method27(var3, this.option.getId())}
            );
            float var8 = FontRegistry.method8().method4(var7);
            var1.method38(0.0F, 0.0F, 100.0F);
            LcuiScreen.method54(
               var1,
               (float)(var2.HHHCHORHIHRCOHIOICICICHCRRICCI() + 8.0),
               (float)(var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() + 6.0),
               var8 + 10.0F,
               14.0F,
               4.0F,
               -1879048192
            );
            FontRegistry.method8()
               .method13(var1, var7, (float)(var2.HHHCHORHIHRCOHIOICICICHCRRICCI() + 12.5), (float)(var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() + 9.0), -1);
            var1.method38(0.0F, 0.0F, -100.0F);
         }
      }
   }

   @Generated
   public TextLabelWidget method2() {
      return this.field16;
   }
}
