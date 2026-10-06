package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.function.Consumer;

public class HoverInfoOptionWidget extends OptionWidget<ClientOption<Boolean>> {
   private final HoverAnimation field16;
   private final Consumer<ClientOption<Boolean>> field17;

   public HoverInfoOptionWidget(ClientOption<Boolean> var1, GuiWidget var2) {
      this(var1, var2, null);
   }

   public HoverInfoOptionWidget(ClientOption<Boolean> var1, GuiWidget var2, Consumer<ClientOption<Boolean>> var3) {
      super(var1, var2);
      this.field17 = var3;
      this.field16 = new HoverAnimation(100L);
      this.field16.start();
      this.method4(this::method1);
   }

   protected boolean method1(MarkerModel.Data2 var1, int var2) {
      this.field16.start();
      this.option.method10(!(Boolean)this.option.get());
      if (this.field17 != null) {
         this.field17.accept(this.option);
      }

      return true;
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x + 36.0F, this.y + 1.5F, -4079426);
      LcuiScreen.method101(var1, this.x, this.y + 2.0F, 30.0F, 10.0F, 5.0F, var3 && this.method3(var2) ? 1088611042 : 551740130, true, true, true, true);
      LcuiScreen.method51(var1, this.x + 1.0F, this.y + 3.0F, 28.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      float var4 = 10.0F;
      float var5 = this.option.get() ? this.field16.method9() : 1.0F - this.field16.method9();
      LcuiScreen.method101(
         var1, this.x + var4 * var5, this.y + 2.0F, 20.0F, 10.0F, 5.0F, this.option.get() ? -1356212614 : -1344396974, true, true, true, true
      );
      LcuiScreen.method51(var1, this.x + 1.0F + var4 * var5, this.y + 3.0F, 18.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      FontRegistry.method9()
         .method14(var1, this.method5(this.option.get() ? "on" : "off", new Object[0]), this.x + var4 * var5 + 10.0F, this.y + 3.0F, -1);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
