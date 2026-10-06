package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.Staffxray;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import lombok.Generated;

public class SearchResultsWidget extends com.moonsworth.lunar.client.ui.widget.WidgetPanel {
   private List<EditorToolbarWidget> field19;
   private com.moonsworth.lunar.client.ui.widget.DropdownWidget field20 = new com.moonsworth.lunar.client.ui.widget.DropdownWidget(
      this
   );
   private IconTextButton field21;

   public SearchResultsWidget(GuiWidget var1) {
      super(var1);
      this.method4(
         (var1x, var2) -> {
            MarkerModel.Data2 var3x = this.field20.method4(var1x);

            for (EditorToolbarWidget var5 : this.field19) {
               if (var1x.method9() > this.y + 20.0F && var5.method3(var3x)) {
                  return var5.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var3x, var2);
               }
            }

            return this.field20.method1(var1x) && this.field20.method6(var1x, var2)
               || this.field21.method3(var1x) && this.field21.method6(var1x, var2);
         }
      );
      this.method3((var1x, var2) -> this.field21.method2(var1x, var2));
      this.field19 = new ArrayList<>();

      for (Framework7Extension var3 : this.field15.method44().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (((Staffxray)var3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field2)).method1()) {
            this.field19.add(new EditorToolbarWidget(this, (ModMenuWidget)var1, var3));
         }
      }
   }

   @Override
   public void method11() {
      this.method2(this.x, this.y, this.width, this.height);
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      int var5 = 0;
      int var6 = 0;
      float var7 = 112.0F;
      float var8 = 115.0F;
      HashMap var9 = new HashMap();
      if (!this.field21.getText().isEmpty()) {
         for (EditorToolbarWidget var11 : this.field19) {
            Framework7Extension var12 = var11.getValue();
            ModDetails var13 = (ModDetails)var12.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
            String var14 = var13 != null ? var13.getName() : var12.getId();
            if (!var9.containsKey(var14)) {
               String var15 = AdventureChatFormatting.getTextWithoutFormattingCodes(var14);
               String var16 = this.field21.getText();
               var16 = ThreadModuleDump56.method4(var16);
               String[] var17 = var15.split(" ");

               for (String var21 : var17) {
                  var21 = ThreadModuleDump56.method4(var21);
                  if (var21.startsWith(var16)) {
                     var9.put(var14, var11);
                  }
               }
            }
         }
      }

      for (EditorToolbarWidget var24 : this.field19) {
         Framework7Extension var26 = var24.getValue();
         ModDetails var27 = (ModDetails)var26.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
         if (!this.field21.getText().isEmpty() && !var9.containsKey(var27 != null ? var27.getName() : var26.getId())) {
            var24.method2(var1 - var8 - 10.0F, var2, var8, var7);
         } else {
            if (var5 == 3) {
               var5 = 0;
               var6++;
            }

            var24.method2(var1 + (var8 + 8.0F) * var5, var2 + 22.0F + (var7 + 8.0F) * var6, var8, var7);
            var5++;
         }
      }

      this.field20.method2(var1 + var3 - 6.0F, var2 + 20.0F, 4.0F, var4 - 20.0F);
      this.field20.method15(var6 == 0 ? var7 + 4.0F : 4.0F + var7 + (var7 + 8.0F) * var6);
      float var23 = 100.0F;
      float var25 = 16.0F;
      this.field21.method2(var1 + var3 - var23 - 15.0F, var2 + 1.0F, var23, var25);
   }

   @Override
   protected List<GuiWidget> method5() {
      return Arrays.asList(
         this.field21 = new IconTextButton(
            this, ResourceLocationBridge.create("lunar", "icons/assets/magnifying-glass-12x12.png"), FontRegistry.field14, "searchPlaceholder", 553648127, 905969663
         )
      );
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      var1.push();
      com.moonsworth.lunar.client.ui.LcuiScreen.method111(var1, this.x - 2.0F, this.y + 20.0F, this.width + 4.0F, this.height - 15.0F, 1.0F);
      this.field20.method5(var1, var2, var3);
      MarkerModel.Data2 var4 = this.field20.method4(var2);

      for (EditorToolbarWidget var6 : this.field19) {
         boolean var7 = var6.getY() + var6.getHeight() + this.field20.method3() < this.field20.getY();
         boolean var8 = var6.getY() + this.field20.method3() > this.field20.getY() + this.field20.getHeight();
         if (var6.getX() >= this.x && !var7 && !var8) {
            var6.method3(var1, var4, var3 && !this.field20.method17());
         }
      }

      this.field20.method7(var1, var2, var3);
      com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
      var1.pop();
      super.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (var2 == KeyCode.KEY_ESCAPE && this.field21.method18()) {
         this.field21.method17(false);
         this.field21.setText("");
      } else {
         super.method4(var1, var2);
         if (!this.field21.method18()) {
            this.field21.method17(true);
            this.field21.method4(var1, var2);
         }
      }
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field20.method5(var1);
   }

   @Override
   public void close() {
      super.close();
   }

   @Generated
   public com.moonsworth.lunar.client.ui.widget.DropdownWidget method7() {
      return this.field20;
   }

   @Generated
   public IconTextButton method8() {
      return this.field21;
   }
}
