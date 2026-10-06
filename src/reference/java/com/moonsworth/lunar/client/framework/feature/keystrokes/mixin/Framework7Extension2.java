package com.moonsworth.lunar.client.framework.feature.keystrokes.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.Generated;

public class Framework7Extension2 extends com.moonsworth.lunar.client.framework.feature.keystrokes.Framework7Extension2 {
   private final GuiRewindhandlersHandler22 field29 = (GuiRewindhandlersHandler22)this.method19(GuiRewindhandlersHandler22.class);
   private final MixinHelper_15 field30;
   private final KeyCode field31;
   private final String field32;
   private final Keystrokes field33;
   private final Map<ClientOption<?>, Object> field34 = new HashMap<>();
   private float field35;
   private float field36;
   private HudAnchor field37;
   private boolean field38;

   public Framework7Extension2(Framework7Extension var1, MixinHelper_15 var2, KeyCode var3, String var4, Keystrokes var5) {
      super(var1, true);
      this.field30 = var2;
      this.field31 = var3;
      this.field32 = var4;
      this.field33 = var5;
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      this.field38 = !var1.has("x") || !var1.has("y");
   }

   @Override
   public String getId() {
      return "KEYSTROKE_KEY_" + this.field31.getName();
   }

   public void method23() {
      if (this.field33 != null && !this.field33.method9().isEmpty()) {
         this.field33.method4(this);
      }
   }

   @Override
   public void method4() {
      super.method4();
      ((MixinCore9Extension)this.method7(Framework.field1)).method18();
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method4(() -> this.field32).method11(this);
   }

   public boolean method24() {
      return this.field31.isMouse();
   }

   public boolean method25() {
      return this.field31 == KeyCode.KEY_W
         || this.field31 == KeyCode.KEY_A
         || this.field31 == KeyCode.KEY_S
         || this.field31 == KeyCode.KEY_D;
   }

   protected void method6(boolean var1) {
      com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes var2 = ((Framework4)this.method7(Framework.field16)).method1();
      MixinCore9Extension var3 = (MixinCore9Extension)this.method7(Framework.field1);
      this.method8(
         ((ModEnabledState)this.method7(Framework.field6)).method1().orElseThrow(),
         ((ToggleOptionBuilder)OptionFactory.method7("dummyFeatureEnabled").OOOIROIIOCOOHICRIRHHHRROHHHHIO(var1)).method31()
      );
      this.method8(var3.method9(), ((MixinCore9Extension)var2.copyOptionsToOthers(Framework.field1)).method9());
      this.method8(this.field9, var2.textShadow);
      this.method8(this.field10, OptionFactory.method7("dummyBorderOption").method31());
      this.method8(this.field11, var2.borderColor);
      this.method8(this.field12, var2.textColor);
      this.method8(this.field13, var2.textPressedColor);
      this.method8(this.field14, var2.backgroundColor);
      this.method8(this.field15, var2.backgroundPressedColor);
      this.method8(this.field16, var2.boxSize);
      this.method8(this.field17, var2.borderThickness);
      this.method8(this.field18, var2.keyFadeDelay);
      this.method8(this.field19, var2.animate);
      this.method8(this.field20, var2.animationType);
      this.method8(this.field21, var2.timerType);
      this.method8(this.field22, var2.animation);
      this.method8(this.field23, var2.animateColor);
      this.method8(this.field24, var2.animationStartColor);
      this.method8(this.field25, var2.animationCenterColor);
      this.method8(this.field26, var2.animationEndColor);
      this.method8(this.field27, var2.duration);
      this.method8(this.field28, var2.timingFunction);
      if (this.field31 == KeyCode.KEY_MOUSE1) {
         this.method8(this.field8, var2.leftCps);
      }

      if (this.field31 == KeyCode.KEY_MOUSE2) {
         this.method8(this.field8, var2.rightCps);
      }

      this.field35 = var3.getX();
      this.field36 = var3.getY();
      this.field37 = var3.method26();
   }

   protected void method26() {
      for (ClientOption var2 : this.field34.keySet()) {
         var2.refreshLayout(this.field34.get(var2));
      }

      this.field34.clear();
      MixinCore9Extension var3 = (MixinCore9Extension)this.method7(Framework.field1);
      var3.method27(this.field37);
      var3.method17(this.field35, this.field36);
      this.field35 = this.field36 = 0.0F;
   }

   private <V> void method8(ClientOption<V> var1, ClientOption<V> var2) {
      if (ColorOption.class.isAssignableFrom(var1.method5())) {
         ColorOption var3 = (ColorOption)((Data)OptionFactory.method8("dummySnapshotColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
            .method31();
         var3.method13(var1);
         this.field34.put(var1, var3);
         var1.method13(var2);
      } else {
         this.field34.put(var1, var1.get());
         var1.method13(var2.get());
      }
   }

   @Override
   protected String method14() {
      String var1 = this.field30.bridge$getKeyName().toUpperCase(Locale.ROOT);
      if (this.field31 == KeyCode.KEY_MOUSE1) {
         var1 = ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((Framework4)this.method7(Framework.field16)).method1())
            .method7("lmb", new Object[0]);
      } else if (this.field31 == KeyCode.KEY_MOUSE2) {
         var1 = ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((Framework4)this.method7(Framework.field16)).method1())
            .method7("rmb", new Object[0]);
      }

      if (this.method25() && (Boolean)((Framework4)this.method7(Framework.field16)).method1().field17.get()) {
         switch (this.field31) {
            case KEY_W:
               var1 = "▲";
               break;
            case KEY_A:
               var1 = "◀";
               break;
            case KEY_S:
               var1 = "▼";
               break;
            case KEY_D:
               var1 = "▶";
         }
      }

      return var1;
   }

   @Override
   protected MixinHelper_15 method15() {
      return this.field30;
   }

   @Override
   protected int method16() {
      return this.field29.method3(this.field31 == KeyCode.KEY_MOUSE1, false);
   }

   @Override
   protected void method17() {
      ((MixinCore9Extension)this.method7(Framework.field1))
         .method16(this.field33.method6(this.field31), this.field33.method7(this.field31));
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      if (this.method24()) {
         var1.method11(new ClientOption[]{this.field8});
      }

      super.method2(var1);
   }

   @Generated
   public KeyCode method27() {
      return this.field31;
   }

   @Generated
   public String getDisplayName() {
      return this.field32;
   }

   @Generated
   public boolean method28() {
      return this.field38;
   }
}
