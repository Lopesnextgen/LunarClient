package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.Animation;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.ui.AnimationTimer;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.Set;
import lombok.Generated;

public class AdvancedOptionWidget extends ListOptionWidget<ClientOption<Boolean>> {
   private static final ResourceLocationBridge field19 = ResourceLocationBridge.create("lunar", "icons/settings/cog-16x16.png");
   private final Framework7Extension field20;
   private final AnimationTimer field21;
   private final HoverAnimation field22;

   public AdvancedOptionWidget(Framework7Extension var1, GuiWidget var2) {
      super(
         (ClientOption<Boolean>)((ModEnabledState)var1.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field6)).method1().orElseThrow(),
         var1.method3(Framework.field14).<Set<ClientOption<?>>>map(Framework5::method1).orElse(Set.of()),
         var2
      );
      this.field20 = var1;
      this.field22 = new HoverAnimation(100L);
      this.field22.start();
      this.field21 = new Animation(4000L);
      this.field21.method4();
   }

   private boolean method5() {
      for (OptionWidget var2 : this.method25()) {
         if (!var2.getOption().isHidden()) {
            return true;
         }
      }

      return false;
   }

   private boolean method6() {
      return this.option instanceof ToggleOption var1 && var1.method9() && this.method5();
   }

   @Override
   protected boolean method7(MarkerModel.Data2 var1) {
      if (!(var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < this.x + 32.0F) && this.method5()) {
         if (this.option instanceof ToggleOption var2) {
            var2.method3(!var2.method9());
         }
      } else {
         this.field22.start();
         this.option.method10(!(Boolean)this.option.get());
      }

      return super.method7(var1);
   }

   @Override
   protected float getHeight2() {
      return 18.0F;
   }

   @Override
   protected float method8() {
      return 4.0F;
   }

   @Override
   protected float method9() {
      return 4.0F;
   }

   @Override
   protected float method10() {
      return -30.0F;
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : (this.method6() ? this.height + 10.0F : this.getHeight2());
   }

   @Override
   public boolean method3() {
      return !this.method6();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = 34.0F;
      Framework10 var5 = (Framework10)this.field20.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
      if (var5 != null && var5.method1() != null) {
         LcuiScreen.method31(var1, var5.method1(), this.x + var4, this.y + 5.0F, 10.0F, 10.0F, 1728053247);
         var4 += 12.0F;
      }

      ModDetails var6 = (ModDetails)this.field20.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      FontRegistry.method14().method13(var1, var6 != null ? var6.getName() : this.field20.getId(), this.x + var4, this.y + 4.5F, -4079426);
      float var7 = 0.0F;
      LcuiScreen.method101(
         var1,
         this.x + var7,
         this.y + 5.0F,
         30.0F,
         10.0F,
         5.0F,
         var3 && this.method3(var2) && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 20.0F && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < this.x + 34.0F
            ? 1088611042
            : 551740130,
         true,
         true,
         true,
         true
      );
      LcuiScreen.method51(var1, this.x + var7 + 1.0F, this.y + 6.0F, 28.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      boolean var8 = (Boolean)this.option.get();
      float var9 = 10.0F;
      float var10 = var8 ? this.field22.method9() : 1.0F - this.field22.method9();
      LcuiScreen.method101(var1, this.x + var7 + var9 * var10, this.y + 5.0F, 20.0F, 10.0F, 5.0F, var8 ? -1356212614 : -1344396974, true, true, true, true);
      LcuiScreen.method51(var1, this.x + var7 + 1.0F + var9 * var10, this.y + 6.0F, 18.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      FontRegistry.method9()
         .method14(
            var1, this.method5(this.option.get() ? "on" : "off", new Object[0]), this.x + var7 + var9 * var10 + 10.0F, this.y + 6.0F, -1
         );
      if (this.method5()) {
         boolean var11 = var3
            && this.method3(var2)
            && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 20.0F
            && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > this.x + 34.0F;
         if (var11 && !this.field21.method7()) {
            this.field21.start();
         } else if (!var11 && this.field21.method7()) {
            this.field21.stop();
         }

         var1.push();
         var1.method38(this.x + this.width - 11.0F, this.y + 10.0F, 1.0F);
         var1.method42(360.0F * this.field21.method9());
         LcuiScreen.method31(var1, field19, -4.0F, -4.0F, 8.0F, 8.0F, var11 ? -1 : -1711276033);
         var1.pop();
         if (this.option instanceof ToggleOption var12 && var12.method9()) {
            LcuiScreen.method117(var1, this.x, this.y + 19.0F, this.width - 2.0F, this.height + 8.0F - this.getHeight2(), 6.0F, 548450480);
            this.method3(var1, var2);
         }
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      if (var2.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.getWidth2()
         && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + this.getHeight2()) {
         ModDetails var3 = (ModDetails)this.field20.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
         String var4 = var3 != null ? var3.getDescription() : null;
         if (var4 != null && !var4.equals("description") && !var4.isEmpty()) {
            this.method4(var1, var2, var4);
         }
      } else {
         super.method14(var1, var2);
      }
   }

   @Override
   public boolean method19(String var1) {
      ModSearchIndex var2 = (ModSearchIndex)this.method14().HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field9);
      if (var2 == null) {
         return false;
      }

      var1 = var1.toLowerCase();
      return var2.method5(var1) || var2.method6(var1);
   }

   public void method12(boolean var1) {
      if (this.method5() && this.option instanceof ToggleOption var2) {
         var2.method3(var1);
      }
   }

   @Generated
   public Framework7Extension method14() {
      return this.field20;
   }
}
