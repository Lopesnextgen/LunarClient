package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class TextButtonWidget extends GuiWidget {
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "icons/assets/arrow-up-17x17-small.png");
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/assets/arrow-down-17x17-small.png");
   private String text;
   private Runnable field18;
   private Runnable field19;
   private boolean isFirst;
   private boolean field20;
   private final CachedFontImpl field21;

   public TextButtonWidget(GuiWidget var1, String var2) {
      this(var1, var2, FontRegistry.method17());
   }

   public TextButtonWidget(GuiWidget var1, String var2, CachedFontImpl var3) {
      super(var1);
      this.text = var2;
      this.field21 = var3;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = var3 && this.method3(var2);
      LcuiScreen.method100(var1, this.x, this.y, this.width, this.height, 0.5F, -820044001, -820175587);
      float var5 = this.height - 8.0F;
      float var6 = this.y + 4.0F;
      float var7 = this.x + 4.0F;
      float var8 = this.x + this.height + 4.0F;
      boolean var9 = !this.isFirst && var4 && var2.method9() >= this.x && var2.method9() < this.x + this.height;
      boolean var10 = !this.field20
         && var4
         && var2.method9() >= this.x + this.height
         && var2.method9() < this.x + 2.0F * this.height;
      if (!this.isFirst) {
         LcuiScreen.method31(var1, field16, var7, var6, var5, var5, var9 ? -1 : -1342177281);
      }

      if (!this.field20) {
         LcuiScreen.method31(var1, field17, var8, var6, var5, var5, var10 ? -1 : -1342177281);
      }

      this.field21
         .method13(
            var1,
            this.text,
            this.x + 2.0F * this.height + 4.0F,
            this.y + this.height / 2.0F - (this.field21.getHeight() + 4.0F) / 2.0F,
            var4 ? -1 : -1342177281
         );
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (!this.method3(var1)) {
         return super.method6(var1, var2);
      }

      if (!this.isFirst && var1.method9() >= this.x && var1.method9() < this.x + this.height) {
         if (this.field18 != null) {
            this.field18.run();
         }

         return true;
      } else if (!this.field20
         && var1.method9() >= this.x + this.height
         && var1.method9() < this.x + 2.0F * this.height) {
         if (this.field19 != null) {
            this.field19.run();
         }

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
   public String getText() {
      return this.text;
   }

   @Generated
   public void setText(String var1) {
      this.text = var1;
   }

   @Generated
   public void method4(Runnable var1) {
      this.field18 = var1;
   }

   @Generated
   public void method5(Runnable var1) {
      this.field19 = var1;
   }

   @Generated
   public void setIsFirst(boolean var1) {
      this.isFirst = var1;
   }

   @Generated
   public void method7(boolean var1) {
      this.field20 = var1;
   }
}
