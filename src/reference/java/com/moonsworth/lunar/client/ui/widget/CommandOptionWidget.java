package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;

public class CommandOptionWidget extends OptionWidget<TextOption> {
   private CommandFieldWidget field16;

   public CommandOptionWidget(TextOption var1, int var2, GuiWidget var3) {
      super(var1, var3);
      this.field16 = new CommandFieldWidget(var3, (String)var1.get());
      this.field16.method17(var1::get);
      this.field16.method18(var1::method10);
      this.field16.method14(var1::method7);
      Consumer var4 = var1::method4;
      this.field16.method15(var4.andThen(var1x -> {
         List var2x = var1x ? var1.method8() : var1.method9();
         var2x.forEach(var1xx -> var1xx.accept((String)var1.get()));
      }));
      this.field16.method25().RRORIIRROCCRRRRIIIIOIIRRIHICIH(var2);
      this.field16.method16(() -> true);
      this.field16.method19((String)var1.get());
      this.field16
         .method20(
            (var1x, var2x) -> {
               Bridge5Extension6 var3x = ThreadModuleDump63.method3().bridge$getCurrentScreen();
               if (var3x instanceof Bridge5Extension62
                  && ((Bridge5Extension62)var3x).method2() instanceof FeatureSettingsScreen var5
                  && var5.method10().method17() instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget var6) {
                  Framework5 var11 = (Framework5)var6.getFeature().method1(Framework.field14);
                  if (var11 != null) {
                     for (ClientOption var9 : var11.method2()) {
                        if (var9 instanceof TextOption var10 && !var9.getName().equals(var1.getName())) {
                           var10.method4(false);
                        }
                     }
                  }
               }

               return true;
            }
         );
      this.method3((var1x, var2x) -> this.field16.method2(var1x, var2x));
      this.method4((var2x, var3x) -> {
         if (this.field16.method3(var2x)) {
            this.field16.method6(var2x, var3x);
         } else if (var1.method7()) {
            var1.method4(false);
            var1.method9().forEach(var1xx -> var1xx.accept((String)var1.get()));
         }

         return false;
      });
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
      this.field16.update();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.field16.method2(this.x + this.width - 90.0F, this.y + 2.0F, 90.0F, 12.0F);
      this.field16.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field16.isActive()) {
         Supplier var3 = this.field16.method10();
         Consumer var4 = this.field16.method14();
         if (var2 != KeyCode.KEY_BACK
            && var2 != KeyCode.KEY_ESCAPE
            && var2 != KeyCode.KEY_RETURN
            && var1 != 0
            && !com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown()) {
            String var12 = (String)var3.get();
            var4.accept(var12.substring(this.field16.method25().IRCRCHHORRHRIORHIHICRIIOOROCII().length()));
            Character var13 = (Character)this.option.method10().get(var1);
            var4.accept(var12);
            if (var13 != null) {
               this.field16.method4(var13, var2);
            }
         } else {
            this.field16.method4(var1, var2);
            String var5 = (String)var3.get();
            var4.accept("");
            StringBuilder var6 = new StringBuilder();

            for (char var10 : var5.toCharArray()) {
               Character var11 = (Character)this.option.method10().get(var10);
               if (var11 != null) {
                  var6.append(var11);
               }
            }

            var4.accept(var6.toString());
         }
      }
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field16.method5(var1);
   }

   @Override
   public void close() {
   }

   @Generated
   public CommandFieldWidget method4() {
      return this.field16;
   }
}
