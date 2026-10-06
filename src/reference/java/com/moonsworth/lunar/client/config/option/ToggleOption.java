package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.Alert2Handler;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.ExpandableListOptionWidget;
import com.moonsworth.lunar.client.ui.widget.HoverInfoOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class ToggleOption extends AbstractBooleanOption {
   private boolean toggled = false;

   protected ToggleOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, Codec<Boolean> var2, Boolean var3) {
      super(var1, var2, var3);
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return !this.getChildren().isEmpty() ? new ExpandableListOptionWidget(this, var1) : new HoverInfoOptionWidget(this, var1);
   }

   @Generated
   public boolean method9() {
      return this.toggled;
   }

   @Generated
   public void method3(boolean var1) {
      this.toggled = var1;
   }

   public static class ToggleOptionBuilder extends BooleanOptionBuilder<ToggleOption.ToggleOptionBuilder, ToggleOption> {
      @Nullable
      private BooleanSupplier field15;

      protected ToggleOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected boolean method8() {
         return true;
      }

      @Contract("_->this")
      public ToggleOption.ToggleOptionBuilder method2(BooleanSupplier var1) {
         this.field15 = var1;
         return this;
      }

      protected ToggleOption method3(ToggleOption var1) {
         super.method17(var1);
         if (this.field15 != null) {
            Alert2 var2 = (Alert2)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field5);
            var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(OptionTraits.field5, new Alert2Handler<>(var2, this.field15, true));
         }

         return var1;
      }

      protected ToggleOption method11() {
         return new ToggleOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7);
      }

      public ToggleOption method5(final String var1, final boolean var2) {
         return this.method3(new ToggleOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7) {
            public void method1(Boolean var1x, boolean var2x) {
               if (var2x || !Objects.equals(this.isValue(), var1x)) {
                  System.out.println("[DEBUG] " + var1 + ": update(" + var1x + ", " + var2x + ")");
                  if (var2) {
                     Thread.dumpStack();
                  }
               }

               super.ICRHORIIHOHROHOHOCOOHOOCOORRHO(var1x, var2x);
            }

            public void method2(Boolean var1x) {
               if (!Objects.equals(this.isValue(), var1x)) {
                  System.out.println("[DEBUG] " + var1 + ": silentUpdate(" + var1x + ")");
                  if (var2) {
                     Thread.dumpStack();
                  }
               }

               super.method12(var1x);
            }

            @Override
            public void method13(Object var1x) {
               if (var1x instanceof ClientOption var2x) {
                  var1x = var2x.get();
               }

               if (var1x.getClass().isInstance(this.isValue())) {
                  System.out.println("[DEBUG] " + var1 + ": silentUpdateNoSave(" + var1x + ")");
                  if (var2) {
                     Thread.dumpStack();
                  }
               }

               super.method13(var1x);
            }
         });
      }
   }
}
