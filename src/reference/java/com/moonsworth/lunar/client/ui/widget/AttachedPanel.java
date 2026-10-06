package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public abstract class AttachedPanel extends com.moonsworth.lunar.client.ui.widget.WidgetPanel<GuiWidget> {
   private Object attachment;
   private final boolean field19;

   public AttachedPanel(GuiWidget var1) {
      this(var1, false);
   }

   public AttachedPanel(GuiWidget var1, boolean var2) {
      super(var1);
      this.field19 = var2;
   }

   public void method2(MarkerModel.Data2 var1) {
      this.method2(
         var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 2.0F - (this.field19 ? 100 : 0),
         var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH(),
         100.0F,
         this.field16.size() * 10 + 8
      );
      int var2 = 0;

      for (GuiWidget var4 : this.field16) {
         var4.method2(
            var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 2.0F + 1.0F - (this.field19 ? this.width - 2.0F : 0.0F),
            this.y + 4.0F + var2 * 10,
            this.width - 2.0F,
            10.0F
         );
         var2++;
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      LcuiScreen.method54(var1, this.x, this.y, this.width, this.height, 6.0F, -820175587);
      super.method3(var1, var2, var3);
   }

   public <T> T getAttachment() {
      return (T)this.attachment;
   }

   @Generated
   public void setAttachment(Object var1) {
      this.attachment = var1;
   }
}
