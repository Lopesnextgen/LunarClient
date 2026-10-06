package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.listener.CpsListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.Generated;

public class DefaultKeystrokeKey extends com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeKey {
   private final CpsListener field29 = (CpsListener)this.method10(CpsListener.class);
   private final KeyBindingBridge field30;
   private final KeyCode field31;
   private final String field32;
   private final KeystrokeLayout field33;
   private final Map<ClientOption<?>, Object> field34 = new HashMap<>();
   private float field35;
   private float field36;
   private HudAnchor field37;
   private boolean field38;

   public DefaultKeystrokeKey(Framework7Extension framework7extension1, KeyBindingBridge mixinhelper_152, KeyCode bridgetype_83, String text4, KeystrokeLayout keystrokes5) {
      super(framework7extension1, true);
      this.field30 = mixinhelper_152;
      this.field31 = bridgetype_83;
      this.field32 = text4;
      this.field33 = keystrokes5;
   }

   @Override
   public void load(JsonObject json1) {
      super.load(json1);
      this.field38 = !json1.has("x") || !json1.has("y");
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
      ((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method18();
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

   protected void method6(boolean flag1) {
      com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes keystrokes2 = ((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
      MixinCore9Extension mixincore9extension3 = (MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
      this.method8(
         ((ModEnabledState)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6)).method1().orElseThrow(),
         ((ToggleOptionBuilder)OptionFactory.method7("dummyFeatureEnabled").OOOIROIIOCOOHICRIRHHHRROHHHHIO(flag1)).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
      );
      this.method8(mixincore9extension3.method9(), ((MixinCore9Extension)keystrokes2.copyOptionsToOthers(ModTraits.field1)).method9());
      this.method8(this.ICCCIHOOIHOIROCIOOOCCCOCRIIIHI, keystrokes2.textShadow);
      this.method8(this.CCCHCOOIIHIOHRRICHIRHICHIOIRRC, OptionFactory.method7("dummyBorderOption").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH());
      this.method8(this.IIOOCRHIIHIIOCHIIICHRCHCRRCRII, keystrokes2.borderColor);
      this.method8(this.OIIICRCIOHRCHIIIHCIOCORIOOCIOI, keystrokes2.textColor);
      this.method8(this.ICCICHHOOHIHRICIIHRHHRCCOHHIII, keystrokes2.textPressedColor);
      this.method8(this.CCRIOIHHHORHCHOIOHCHCOOIHOCHIC, keystrokes2.backgroundColor);
      this.method8(this.HRRCRRHOCORRCRIOCRHCOHCHHHOOHO, keystrokes2.backgroundPressedColor);
      this.method8(this.CCORCRRICRIIHROIIOIRHIHRRHIICH, keystrokes2.boxSize);
      this.method8(this.OCCRCROIOHRIOHOOIRRIRRHOCOIOHO, keystrokes2.borderThickness);
      this.method8(this.CHCIIRIHCCCOROIIICOOOHICOCIIHH, keystrokes2.keyFadeDelay);
      this.method8(this.HHHCIOICHCRRCOCOOCHCCHRHIRRCRH, keystrokes2.animate);
      this.method8(this.HORHOIIROCHCRHOOHCICORICRHCRRH, keystrokes2.animationType);
      this.method8(this.OICIOOHRIHROCIIIRHOCCCROHCCRCI, keystrokes2.timerType);
      this.method8(this.CCOCCCIHHRIOICHOIRRRHCHOORRHIC, keystrokes2.animation);
      this.method8(this.OHRHHHHIHHORIOOOIRIIHIIIIRORHH, keystrokes2.animateColor);
      this.method8(this.OHHRHCHCRRCHRCHRICIOOHHHHRIHOO, keystrokes2.animationStartColor);
      this.method8(this.ICHROIOCHOORCROHICHHRRIICOHIIH, keystrokes2.animationCenterColor);
      this.method8(this.HOCIRIOCCIOOORRCHHIRCCHROIIIHR, keystrokes2.animationEndColor);
      this.method8(this.RCOOHHOHCORCRICHOIOICIHIROIHCH, keystrokes2.duration);
      this.method8(this.IHHHOCOCCIRCOIIRCCIOICRICIIIOH, keystrokes2.timingFunction);
      if (this.field31 == KeyCode.KEY_MOUSE1) {
         this.method8(this.OOROHORCOIOOHHHHOHHRIIROHIIROC, keystrokes2.leftCps);
      }

      if (this.field31 == KeyCode.KEY_MOUSE2) {
         this.method8(this.OOROHORCOIOOHHHHOHHRIIROHIIROC, keystrokes2.rightCps);
      }

      this.field35 = mixincore9extension3.getX();
      this.field36 = mixincore9extension3.getY();
      this.field37 = mixincore9extension3.method26();
   }

   protected void method26() {
      for (ClientOption lightingextension2 : this.field34.keySet()) {
         lightingextension2.method13(this.field34.get(lightingextension2));
      }

      this.field34.clear();
      MixinCore9Extension mixincore9extension3 = (MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
      mixincore9extension3.method27(this.field37);
      mixincore9extension3.method17(this.field35, this.field36);
      this.field35 = this.field36 = 0.0F;
   }

   private <V> void method8(ClientOption<V> lightingextension1, ClientOption<V> lightingextension2) {
      if (ColorOption.class.isAssignableFrom(lightingextension1.method5())) {
         ColorOption lightingextension42223 = (ColorOption)((Data)OptionFactory.method8("dummySnapshotColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
            .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
         lightingextension42223.method13(lightingextension1);
         this.field34.put(lightingextension1, lightingextension42223);
         lightingextension1.method13(lightingextension2);
      } else {
         this.field34.put(lightingextension1, lightingextension1.get());
         lightingextension1.method13(lightingextension2.get());
      }
   }

   @Override
   protected String method14() {
      String text1 = this.field30.bridge$getKeyName().toUpperCase(Locale.ROOT);
      if (this.field31 == KeyCode.KEY_MOUSE1) {
         text1 = ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1())
            .method7("lmb", new Object[0]);
      } else if (this.field31 == KeyCode.KEY_MOUSE2) {
         text1 = ((com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1())
            .method7("rmb", new Object[0]);
      }

      if (this.method25() && (Boolean)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1().field17.get()) {
         switch (this.field31) {
            case KEY_W:
               text1 = "▲";
               break;
            case KEY_A:
               text1 = "◀";
               break;
            case KEY_S:
               text1 = "▼";
               break;
            case KEY_D:
               text1 = "▶";
         }
      }

      return text1;
   }

   @Override
   protected KeyBindingBridge method15() {
      return this.field30;
   }

   @Override
   protected int method16() {
      return this.field29.method3(this.field31 == KeyCode.KEY_MOUSE1, false);
   }

   @Override
   protected void method17() {
      ((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1))
         .method16(this.field33.method6(this.field31), this.field33.method7(this.field31));
   }

   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      if (this.method24()) {
         lightingextension231.method9(new ClientOption[]{this.OOROHORCOIOOHHHHOHHRIIROHIIROC});
      }

      super.method2(lightingextension231);
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
