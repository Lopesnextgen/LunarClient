package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.calculator.Calculator;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.ColorTransition;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework12;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.mod.Alert2Iterator;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.LongOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

public abstract class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   protected final ToggleOption field8 = (ToggleOption)OptionFactory.method7("showCps").method31();
   protected final ToggleOption field9 = (ToggleOption)OptionFactory.method7("textShadow").method31();
   protected final ToggleOption field10 = (ToggleOption)OptionFactory.method7("border").method31();
   protected final ColorOption keystrokesMode = (ColorOption)((Data)OptionFactory.method8("borderColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("textColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ColorOption field13 = (ColorOption)((Data)OptionFactory.method8("textPressedColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
      .method31();
   protected final ColorOption field14 = (ColorOption)((Data)OptionFactory.method8("backgroundColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final ColorOption field15 = (ColorOption)((Data)OptionFactory.method8("backgroundPressedColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .method31();
   public final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "boxSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(18.0F))
         .method8(10.0F, 32.0F))
      .method31();
   protected final FloatOption field17 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 3.0F))
      .method31();
   protected final LongOption field18 = (LongOption)((com.moonsworth.lunar.client.config.option.LongOption.Data)((com.moonsworth.lunar.client.config.option.LongOption.Data)OptionFactory.method3(
               "keyFadeDelay"
            )
            .HRHCHRICROCCHOHOROROIRIICHCRHH(75L))
         .HRICOROOOCCOCOROCRHHCRRIRCOICO(0L, 500L))
      .method31();
   public final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("animate").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final EnumOption<Gui2Extension> field20 = (EnumOption<Gui2Extension>)OptionFactory.method10("animationType", Gui2Extension.STACKED)
      .method31();
   public final EnumOption<Gui2Extension_2> field21 = (EnumOption<Gui2Extension_2>)OptionFactory.method10("timerType", Gui2Extension_2.HALF)
      .method31();
   public final EnumOption<Gui2Extension3> field22 = (EnumOption<Gui2Extension3>)OptionFactory.method10("animation", Gui2Extension3.RIPPLE)
      .method31();
   public final ToggleOption field23 = (ToggleOption)OptionFactory.method7("animateColor").method31();
   public final ColorOption field24 = (ColorOption)((Data)OptionFactory.method8("animationStartColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1056964609))
      .method31();
   public final ColorOption field25 = (ColorOption)((Data)OptionFactory.method8("animationCenterColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .method31();
   public final ColorOption field26 = (ColorOption)((Data)OptionFactory.method8("animationEndColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1879048191))
      .method31();
   public final FloatOption field27 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "duration"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.1F, 1.0F))
      .method31();
   public final EnumOption<Gui2Extension2> field28 = (EnumOption<Gui2Extension2>)OptionFactory.method10("timingFunction", Gui2Extension2.LINEAR)
      .method31();

   public Framework7Extension2(Framework7Extension var1, boolean var2) {
      super(var2);
      this.method14(Framework.field16, Framework4.method5(() -> ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)var1).keystrokesMode.get() == Type.INDIVIDUAL, var1));
      this.method14(Framework.field1, new Framework7Extension2.Data4());
   }

   @Override
   public void method1(boolean var1) {
      this.method14(Framework.field6, ModEnabledState.method6(var1));
      this.method14(Framework.field4, new Alert2Iterator());
      this.method14(Framework.field9, ModSearchIndex.method7());
      this.method14(Framework.field10, Framework12.method9());
   }

   protected String method13() {
      MixinHelper_15 var1 = this.method15();
      return var1 == null ? "NONE" : var1.bridge$getUntranslatedKeyDescription();
   }

   protected abstract String method14();

   @Nullable
   protected abstract MixinHelper_15 method15();

   protected abstract int method16();

   protected abstract void method17();

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method45(var1);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field9, this.field10});
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         this.field19,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  OptionFactory.method14("applyToAll")
                     .method4(
                        () -> ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1())
                           .method6(this)
                     )
               }
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field20, this.field21, this.field22, this.field27, this.field28});
            var1x.method6(
               this.field23, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field24, this.field25, this.field26})
            );
         }
      );
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
         new ClientOption[]{this.field17, this.field16, this.keystrokesMode, this.field12, this.field13, this.field14, this.field15}
      );
      ((SettingsSectionImpl)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field18})).method2(this.field19::get);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
         new OptionSupplier[]{
            OptionFactory.method14("copyOptions")
               .method4(
                  () -> ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1())
                     .method8(this)
               )
         }
      );
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
         new OptionSupplier[]{
            OptionFactory.method14("applyToAll")
               .method4(
                  () -> {
                     Framework5 var1x = (Framework5)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field14);
                     ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1())
                        .method7(this, var1x.method2().stream().map(ClientOption::getId).toList());
                  }
               )
         }
      );
   }

   @Override
   public boolean isEnabled() {
      this.updateEnabled();
      return super.isEnabled();
   }

   public class Data4 extends HudElementBase {
      private boolean field9 = false;
      private Keystrokes2Handler3 field10;
      private Keystrokes3 keystrokesMode;
      private final List<Keystrokes> field12 = new LinkedList<>();
      private final ColorTransition field13 = new ColorTransition(0L, Framework7Extension2.this.field14, Framework7Extension2.this.field15);

      public Data4() {
         super(0.0F, 0.0F, com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT);
      }

      @Override
      public boolean method31() {
         return false;
      }

      private void method2(boolean var1) {
         if ((Boolean)Framework7Extension2.this.field19.get()) {
            if (Framework7Extension2.this.field20.get() == Gui2Extension.SYNCED) {
               if (this.field10 == null || this.field10.isDone()) {
                  this.field10 = new Keystrokes2Handler3(((Float)Framework7Extension2.this.field27.get()).floatValue());
                  this.keystrokesMode = ((Gui2Extension3)Framework7Extension2.this.field22.get()).create();
               }

               if (var1) {
                  this.field10.method3();
               } else {
                  this.field10.method4();
               }
            } else if (var1) {
               Keystrokes2 var2 = ((Gui2Extension_2)Framework7Extension2.this.field21.get())
                  .create(((Float)Framework7Extension2.this.field27.get()).floatValue());
               this.field12
                  .add(
                     new Keystrokes(
                        var2, ((Gui2Extension3)Framework7Extension2.this.field22.get()).create(), (Gui2Extension2)Framework7Extension2.this.field28.get()
                     )
                  );
            }
         }
      }

      private int method3(float var1) {
         if (!(Boolean)Framework7Extension2.this.field23.get()) {
            return Framework7Extension2.this.field15.method14(this.getX() + this.getY());
         } else {
            return var1 < 1.0F
               ? this.method4(
                  Framework7Extension2.this.field24.method14(this.getX() + this.getY()),
                  Framework7Extension2.this.field25.method14(this.getX() + this.getY()),
                  ThreadModuleDump67.method1(var1, 0.0F, 1.0F)
               )
               : this.method4(
                  Framework7Extension2.this.field25.method14(this.getX() + this.getY()),
                  Framework7Extension2.this.field26.method14(this.getX() + this.getY()),
                  ThreadModuleDump67.method1(var1 - 1.0F, 0.0F, 1.0F)
               );
         }
      }

      private int method4(int var1, int var2, float var3) {
         int var4 = var1 >> 24 & 0xFF;
         int var5 = var1 >> 16 & 0xFF;
         int var6 = var1 >> 8 & 0xFF;
         int var7 = var1 & 0xFF;
         int var8 = var2 >> 24 & 0xFF;
         int var9 = var2 >> 16 & 0xFF;
         int var10 = var2 >> 8 & 0xFF;
         int var11 = var2 & 0xFF;
         float[] var12 = Color.RGBtoHSB(var5, var6, var7, null);
         float[] var13 = Color.RGBtoHSB(var9, var10, var11, null);
         float var14 = var12[0];
         float var15 = var13[0];
         if (var15 - var14 > 0.5F) {
            var14++;
         } else if (var15 - var14 < -0.5F) {
            var15++;
         }

         float var16 = (var14 + (var15 - var14) * var3) % 1.0F;
         float var17 = var12[1] + (var13[1] - var12[1]) * var3;
         float var18 = var12[2] + (var13[2] - var12[2]) * var3;
         int var19 = Color.HSBtoRGB(var16, var17, var18);
         int var20 = Math.round(var4 + (var8 - var4) * var3);
         int var21 = var19 >> 16 & 0xFF;
         int var22 = var19 >> 8 & 0xFF;
         int var23 = var19 & 0xFF;
         return var20 << 24 | var21 << 16 | var22 << 8 | var23;
      }

      private void method5(MixinHelper_4 var1) {
         var1.push();
         Iterator var2 = this.field12.iterator();

         while (var2.hasNext()) {
            Keystrokes var3 = (Keystrokes)var2.next();
            if (var3.method2().isDone()) {
               var2.remove();
            } else {
               var3.method1(this, this.method3(var3.method2().method2()), var1);
            }
         }

         if (Framework7Extension2.this.field20.get() == Gui2Extension.SYNCED && this.field10 != null && this.keystrokesMode != null) {
            float var4 = ThreadModuleDump67.method1(this.field10.method1(), 0.0F, 1.0F);
            if (var4 != 0.0F) {
               this.keystrokesMode.method1(this, var4, this.method3(this.field10.method2()), var1);
            }
         }

         var1.pop();
      }

      public void method6(MixinHelper_4 var1, float var2) {
         KeyCode var3 = Framework7Extension2.this.method15() == null ? KeyCode.KEY_NONE : Framework7Extension2.this.method15().bridge$getKey();
         com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes var4 = ((Framework4)Framework7Extension2.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16))
            .method1();
         boolean var5 = Framework7Extension2.this.method15() != null
            && var4.RHHHIHOHHHRHHCIROOCCIHOCICHIIC()
               .method1(Framework7Extension2.this.method13(), Bridge.method18().method1(var3) && ThreadModuleDump63.method3().bridge$getCurrentScreen() == null);
         if (this.field9 != var5) {
            this.field9 = var5;
            this.method2(var5);
         }

         if ((Boolean)Framework7Extension2.this.field19.get()) {
            Framework7Extension2.this.field14.method11(var1, 0.0F, 0.0F, this.getWidth(), this.getHeight());
         } else {
            this.field13.setDurationMs((Long)Framework7Extension2.this.field18.get());
            LcuiScreen.method94(var1, 0.0F, 0.0F, this.getWidth(), this.getHeight(), this.field13.method2(var5, var2));
         }

         this.method5(var1);
         ColorOption var6 = var5 ? Framework7Extension2.this.field13 : Framework7Extension2.this.field12;
         if (Framework7Extension2.this instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.mixin.Framework7Extension2
            && var3 == Framework7Extension2.this.mc.bridge$getGameSettings().bridge$keyBindJump().bridge$getKey()) {
            float var12 = (Float)((Framework4)Framework7Extension2.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1().field29.get();
            var6.method11(
               var1, this.getWidth() / 2.0F - this.getWidth() / 6.0F, 3.0F, this.getWidth() / 3.0F, var12, (Boolean)Framework7Extension2.this.field9.get()
            );
         } else {
            float var7 = ThreadModuleDump63.method10().method19();
            float var8 = this.getWidth() / 2.0F;
            float var13;
            if ((Boolean)Framework7Extension2.this.field8.get()) {
               var13 = this.getHeight() - var7 + 2.0F;
               float var10 = 0.6F;
               var1.push();
               var1.scale(var10, var10, 1.0F);
               String var11 = Framework7Extension2.this.method16() + " " + Calculator.field1;
               var1.method44(var0 -> var0.method29().method14());
               var1.method29(
                  ThreadModuleDump63.method10(),
                  var11,
                  var8 / var10,
                  var13 / var10,
                  ThreadModuleDump23.method14(var6.method14(var8 + var13), 4),
                  (Boolean)Framework7Extension2.this.field9.get()
               );
               var1.pop();
               var13 = this.getHeight() / 4.0F - var7 / 4.0F;
            } else {
               var13 = this.getHeight() / 2.0F - var7 / 2.0F + 1.0F;
            }

            String var14 = Framework7Extension2.this.method14();
            if ((Float)Framework7Extension2.this.field16.get() < 14.0F
               && Framework7Extension2.this instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.mixin.Framework7Extension2) {
               var14 = var14.substring(0, 1);
            }

            var1.method44(var0 -> var0.method29().method14());
            var6.method11(var1, var14, var8, var13, (Boolean)Framework7Extension2.this.field9.get());
         }

         if ((Boolean)Framework7Extension2.this.field10.get()) {
            Framework7Extension2.this.keystrokesMode
               .method11(var1, this, 0.0F, 0.0F, this.getWidth(), this.getHeight(), (Float)Framework7Extension2.this.field17.get());
         }
      }

      @Override
      public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         Framework7Extension2.this.method17();
         var1.method2().push();
         var1.method2().method38(var2, var3, 0.0F);
         this.method6(var1.method2(), var2 + var3);
         var1.method2().pop();
      }

      @Override
      public boolean method4(boolean var1) {
         if (!Framework7Extension2.this.isEnabled()) {
            this.method58(0.0F, 0.0F);
            return false;
         } else if (((Framework4)Framework7Extension2.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1().keystrokesMode.get() == Type.GROUPED) {
            this.method58(0.0F, 0.0F);
            return false;
         } else {
            return true;
         }
      }

      @Override
      public boolean method30() {
         return this.method4(false);
      }

      @Override
      public void method18() {
         this.method6(com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT);
         this.ICHRCHCIHROCHRCIRCCCCHCRCCCHOH().reset();
         Framework7Extension2.this.field16.reset();
         if (Framework7Extension2.this instanceof com.moonsworth.lunar.client.framework.feature.keystrokes.mixin.Framework7Extension2 var1) {
            var1.method23();
         } else {
            super.method18();
         }
      }
   }
}
