package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class EditorToolbarWidget extends SelectionWidget<Framework7Extension> {
   public static final float field17 = 112.0F;
   public static final float field18 = 115.0F;
   private final ModStateWidget field19;
   private final OptionsButtonWidget field20;
   private final FavoriteStarWidget field21;
   private final AnimatedValue field22 = new AnimatedValue(542594903, 1348953959);

   public EditorToolbarWidget(GuiWidget var1, ModMenuWidget var2, Framework7Extension var3) {
      super(var1, var3);
      this.setWidth(115.0F);
      this.setHeight(112.0F);
      Alert2 var4 = (Alert2)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
      ClientOption var5 = var3.method3(Framework.field6).<ClientOption>flatMap(ModEnabledState::method1).orElse(null);
      ModDetails var6 = (ModDetails)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      this.field19 = new ModStateWidget(
         this,
         var5,
         var6 != null ? var6.getName() : var3.getId(),
         var4 == null ? null : (Boolean)var4.method3().orElse(null),
         var4 == null ? null : var4.method2()
      );
      this.field20 = new OptionsButtonWidget(this, var2, var3);
      this.field21 = new FavoriteStarWidget(this, var2, var3);
      this.method4((var1x, var2x) -> {
         if (this.field19.method3(var1x)) {
            return this.field19.method4(var1x, var2x);
         }

         if (this.field20.method3(var1x)) {
            Framework10 var3x = (Framework10)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
            if (var3x != null) {
               var3x.setLastModified(System.currentTimeMillis());
            }

            return this.field20.method4(var1x, var2x);
         } else {
            return this.field21.method3(var1x) ? this.field21.method4(var1x, var2x) : false;
         }
      });
   }

   @Override
   public void update() {
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.field19.method2(var1, var2 + var4 - 20.0F, var3, 20.0F);
      this.field20.method2(var1, var2 + var4 - 40.0F, var3, 20.0F);
      this.field21.method2(var1 + 3.0F, var2 + 3.0F, 11.0F, 11.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(
         var1, this.x, this.y, this.width, this.height, 6.0F, 0, 0, this.field22.method2(var3 && this.method3(var2))
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x, this.y, this.width, this.height, 6.0F, 551740130);
      ModDetails var4 = (ModDetails)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      String var5 = var4 != null ? var4.getName() : ((Framework7Extension)this.field16).getId();
      Framework10 var6 = (Framework10)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
      if (var6 != null) {
         ResourceLocationBridge var7 = var6.method2();
         if (var7 != null) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method31(
               var1, var7, this.x + this.width / 2.0F - 13.0F, this.y + this.height / 2.0F - 40.0F, 26.0F, 26.0F, -637534209
            );
         } else {
            var6.method8(var1, this.x, this.y, this.width, this.height / 2.0F, var2, var3);
         }

         if (var4 != null && var4.method1().contains(com.moonsworth.lunar.client.framework.mod.Calculator2Handler.field3) && !var6.method3()) {
            float var8 = FontRegistry.method19().method4(var5);
            FontRegistry.method9()
               .method14(
                  var1,
                  this.OHROCHICOIOICHOCRROORRCIIICIHO("new", new Object[0]).toUpperCase(),
                  this.x + this.width / 2.0F + var8 / 2.0F,
                  this.y + this.height / 2.0F - 8.0F,
                  -1879104990
               );
         }
      }

      FontRegistry.method19().method14(var1, var5, this.x + this.width / 2.0F, this.y + this.height / 2.0F - 3.0F, -8487555);
      this.field20.method3(var1, var2, var3);
      this.field19.method3(var1, var2, var3);
      this.field21.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
