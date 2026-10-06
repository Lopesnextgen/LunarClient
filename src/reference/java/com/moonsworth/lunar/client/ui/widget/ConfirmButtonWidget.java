package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class ConfirmButtonWidget extends GuiWidget {
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "icons/assets/accept-16x16.png");
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/assets/deny-16x16.png");
   private String text;
   private boolean state;
   private final CachedFontImpl field18;
   private Runnable field19;

   public ConfirmButtonWidget(GuiWidget var1, String var2) {
      this(var1, var2, false);
   }

   public ConfirmButtonWidget(GuiWidget var1, String var2, boolean var3) {
      this(var1, var2, var3, FontRegistry.method17());
   }

   public ConfirmButtonWidget(GuiWidget var1, String var2, boolean var3, CachedFontImpl var4) {
      super(var1);
      this.text = var2;
      this.state = var3;
      this.field18 = var4;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = var3 && this.method3(var2);
      LcuiScreen.method100(var1, this.x, this.y, this.width, this.height, 0.5F, -820044001, -820175587);
      LcuiScreen.method31(
         var1, this.state ? field16 : field17, this.x + 4.0F, this.y + 4.0F, this.height - 8.0F, this.height - 8.0F, this.state ? -863961216 : -855670656
      );
      this.field18
         .method13(var1, this.text, this.x + this.height, this.y + this.height / 2.0F - (this.field18.getHeight() + 4.0F) / 2.0F, var4 ? -1 : -1342177281);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (this.field19 != null && var1.method12() > this.x && var1.method12() < this.x + this.width) {
         this.field19.run();
         return true;
      } else {
         return super.method6(var1, var2);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Generated
   public void setText(String var1) {
      this.text = var1;
   }

   @Generated
   public String getText() {
      return this.text;
   }

   @Generated
   public boolean isState() {
      return this.state;
   }

   @Generated
   public void setState(boolean var1) {
      this.state = var1;
   }

   @Generated
   public void method6(Runnable var1) {
      this.field19 = var1;
   }
}
