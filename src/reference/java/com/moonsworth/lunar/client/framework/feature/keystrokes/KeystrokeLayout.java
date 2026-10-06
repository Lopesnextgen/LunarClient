package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.HudElement;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class KeystrokeLayout {
   private final Map<KeyCode, DefaultKeystrokeKey> field1 = new HashMap<>();
   private final com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes field2;
   private float field3;
   private float field4;

   public KeystrokeLayout(com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes keystrokes1) {
      this.field2 = keystrokes1;
   }

   public void init() {
      if (this.field1.isEmpty()) {
         KeyBindingBridge mixinhelper_151 = Ref.method3().bridge$getGameSettings().bridge$keyBindForward();
         KeyBindingBridge mixinhelper_152 = Ref.method3().bridge$getGameSettings().bridge$keyBindLeft();
         KeyBindingBridge mixinhelper_153 = Ref.method3().bridge$getGameSettings().bridge$keyBindBack();
         KeyBindingBridge mixinhelper_154 = Ref.method3().bridge$getGameSettings().bridge$keyBindRight();
         KeyBindingBridge mixinhelper_155 = Ref.method3().bridge$getGameSettings().bridge$keyBindJump();
         KeyBindingBridge mixinhelper_156 = Ref.method3().bridge$getGameSettings().bridge$keyBindAttack();
         KeyBindingBridge mixinhelper_157 = Ref.method3().bridge$getGameSettings().bridge$keyBindUseItem();
         this.method2(
            new DefaultKeystrokeKey(this.field2, mixinhelper_151, KeyCode.KEY_W, this.field2.copyOptions("forward", new Object[0]), this),
            new DefaultKeystrokeKey(this.field2, mixinhelper_152, KeyCode.KEY_A, this.field2.copyOptions("left", new Object[0]), this),
            new DefaultKeystrokeKey(this.field2, mixinhelper_153, KeyCode.KEY_S, this.field2.copyOptions("back", new Object[0]), this),
            new DefaultKeystrokeKey(this.field2, mixinhelper_154, KeyCode.KEY_D, this.field2.copyOptions("right", new Object[0]), this),
            new DefaultKeystrokeKey(this.field2, mixinhelper_155, KeyCode.KEY_SPACE, this.field2.copyOptions("jump", new Object[0]), this),
            new DefaultKeystrokeKey(this.field2, mixinhelper_156, KeyCode.KEY_MOUSE1, this.field2.copyOptions("attack", new Object[0]), this),
            new DefaultKeystrokeKey(this.field2, mixinhelper_157, KeyCode.KEY_MOUSE2, this.field2.copyOptions("use", new Object[0]), this)
         );
      }
   }

   private DefaultKeystrokeKey method1(KeyCode bridgetype_81) {
      return this.field1.get(bridgetype_81);
   }

   private void method2(DefaultKeystrokeKey... items1) {
      for (DefaultKeystrokeKey framework7extension25 : items1) {
         this.field1.put(framework7extension25.method27(), framework7extension25);
      }
   }

   public void method3(Collection<DefaultKeystrokeKey> list1) {
      for (DefaultKeystrokeKey framework7extension23 : list1) {
         float value4 = 0.0F;
         float value5 = 0.0F;
         KeyCode bridgetype_86 = framework7extension23.method27();
         if (bridgetype_86 == KeyCode.KEY_W) {
            value4 += this.method5(KeyCode.KEY_A) + 1.0F;
         }

         if (bridgetype_86 == KeyCode.KEY_A || bridgetype_86 == KeyCode.KEY_S || bridgetype_86 == KeyCode.KEY_D || bridgetype_86 == KeyCode.KEY_SPACE || framework7extension23.method24()) {
            value5 += this.method7(KeyCode.KEY_W) + 1.0F;
            if (bridgetype_86 == KeyCode.KEY_S || bridgetype_86 == KeyCode.KEY_D) {
               value4 += this.method5(KeyCode.KEY_A) + 1.0F;
            }

            if (bridgetype_86 == KeyCode.KEY_D) {
               value4 += this.method5(KeyCode.KEY_S) + 1.0F;
            }

            if (bridgetype_86 == KeyCode.KEY_SPACE || framework7extension23.method24()) {
               value5 += this.max(this.method7(KeyCode.KEY_A), this.method7(KeyCode.KEY_S), this.method7(KeyCode.KEY_D)) + 1.0F;
               if (framework7extension23.method24()) {
                  value5 += this.method7(KeyCode.KEY_SPACE) + 1.0F;
                  if (bridgetype_86 == KeyCode.KEY_MOUSE2) {
                     value4 += this.method5(KeyCode.KEY_MOUSE1) + 1.0F;
                  }
               }
            }
         }

         MixinCore9Extension mixincore9extension7 = (MixinCore9Extension)framework7extension23.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
         mixincore9extension7.method27(HudAnchor.TOP_LEFT);
         mixincore9extension7.method17(value4, value5);
      }
   }

   protected void method4(DefaultKeystrokeKey framework7extension21) {
      this.method3(Collections.singletonList(framework7extension21));
   }

   private float method5(KeyCode bridgetype_81) {
      DefaultKeystrokeKey framework7extension22 = this.method1(bridgetype_81);
      if (bridgetype_81 == KeyCode.KEY_SPACE) {
         return this.method6(KeyCode.KEY_A) + 1.0F + this.method6(KeyCode.KEY_S) + 1.0F + this.method6(KeyCode.KEY_D);
      } else {
         return bridgetype_81 != KeyCode.KEY_MOUSE1 && bridgetype_81 != KeyCode.KEY_MOUSE2
            ? (Float)framework7extension22.CCORCRRICRIIHROIIOIRHIHRRHIICH.get()
            : (this.method6(KeyCode.KEY_SPACE) - 1.0F) / 2.0F;
      }
   }

   protected float method6(KeyCode bridgetype_81) {
      DefaultKeystrokeKey framework7extension22 = this.method1(bridgetype_81);
      if (bridgetype_81 == KeyCode.KEY_SPACE) {
         return (Float)framework7extension22.CCORCRRICRIIHROIIOIRHIHRRHIICH.get() * 3.0F + 2.0F;
      } else {
         return bridgetype_81 != KeyCode.KEY_MOUSE1 && bridgetype_81 != KeyCode.KEY_MOUSE2
            ? (Float)framework7extension22.CCORCRRICRIIHROIIOIRHIHRRHIICH.get()
            : ((Float)framework7extension22.CCORCRRICRIIHROIIOIRHIHRRHIICH.get() * 3.0F + 1.0F) / 2.0F;
      }
   }

   protected float method7(KeyCode bridgetype_81) {
      DefaultKeystrokeKey framework7extension22 = this.method1(bridgetype_81);
      if (!framework7extension22.isEnabled()) {
         return 0.0F;
      } else {
         return bridgetype_81 == KeyCode.KEY_SPACE ? (Float)framework7extension22.CCORCRRICRIIHROIIOIRHIHRRHIICH.get() / 2.0F : (Float)framework7extension22.CCORCRRICRIIHROIIOIRHIHRRHIICH.get();
      }
   }

   private float max(float... items1) {
      float value2 = 0.0F;

      for (float value6 : items1) {
         value2 = Math.max(value2, value6);
      }

      return value2;
   }

   public void method8(MixinHelper_4 mixinhelper_41, float value2) {
      Collection list3 = this.method9();

      for (DefaultKeystrokeKey framework7extension25 : list3) {
         boolean flag6 = !framework7extension25.method25() || (Boolean)this.field2.keyStrokesMovement.get();
         if (framework7extension25.method24() && !(Boolean)this.field2.keyStrokesClicks.get()) {
            flag6 = false;
         }

         if (framework7extension25.method27() == KeyCode.KEY_SPACE && !(Boolean)this.field2.keyStrokesSpacebar.get()) {
            flag6 = false;
         }

         framework7extension25.method6(flag6);
         framework7extension25.method17();
      }

      this.method3(list3);
      mixinhelper_41.push();
      float value19 = Float.MAX_VALUE;
      float value20 = Float.MAX_VALUE;
      float value21 = 0.0F;
      float value7 = 0.0F;

      for (DefaultKeystrokeKey framework7extension29 : list3) {
         MixinCore9Extension mixincore9extension10 = (MixinCore9Extension)framework7extension29.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
         if (framework7extension29.isEnabled()) {
            mixinhelper_41.push();
            mixinhelper_41.method38(mixincore9extension10.getX() - 0.0F, mixincore9extension10.getY() - 0.0F, 0.0F);
            ((com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeKey.KeystrokeKeyElement)mixincore9extension10)
               .method6(mixinhelper_41, value2 + mixincore9extension10.getX() + mixincore9extension10.getY() - 0.0F - 0.0F);
            mixinhelper_41.pop();
         }

         value19 = Math.min(mixincore9extension10.getX(), value19);
         value20 = Math.min(mixincore9extension10.getY(), value20);
         value21 = Math.max(mixincore9extension10.getX() + mixincore9extension10.getWidth(), value21);
         value7 = Math.max(mixincore9extension10.getY() + mixincore9extension10.getHeight(), value7);
      }

      this.field3 = value21 - value19;
      this.field4 = value7 - value20;
      boolean flag22 = (Boolean)this.field2.border.get();
      boolean flag23 = (Boolean)this.field2.innerBorder.get();
      if (flag22 && !flag23) {
         float value24 = (Float)this.field2.borderThickness.get();
         float value11 = (Float)this.field2.boxSize.get();
         float value12 = 0.0F;
         float value13 = this.field4;
         boolean flag14 = (Boolean)this.field2.keyStrokesMovement.get();
         boolean flag15 = (Boolean)this.field2.keyStrokesClicks.get();
         boolean flag16 = (Boolean)this.field2.keyStrokesSpacebar.get();
         boolean flag17 = flag15 && flag16;
         if ((Boolean)this.field2.keyStrokesMovement.get()) {
            if (!flag15 && !flag16) {
               value13 -= 2.0F;
            } else if (!flag15) {
               value13--;
            }

            value12 += value11 + 1.0F;
            value13 -= value11 + 1.0F;
         } else {
            int number18 = !flag17 && !flag16 ? 3 : 2;
            value12 += number18;
            value13 -= flag17 ? 2.0F : 3.0F;
         }

         if (!flag14) {
            this.field2.field21.method11(mixinhelper_41, (HudElement)this.field2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field1), 0.0F, value12, this.field3, value13, value24);
         } else {
            this.field2.field21.method11(mixinhelper_41, -value24, value12, value24, value13);
            this.field2.field21.method11(mixinhelper_41, this.field3, value12, value24, value13);
            this.field2.field21.method11(mixinhelper_41, -value24, value12 + value13, this.field3 + value24 * 2.0F, value24);
            this.field2.field21.method11(mixinhelper_41, -value24, value12 - value24, value11 + value24 / 2.0F + 1.0F, value24);
            this.field2.field21.method11(mixinhelper_41, this.field3 - value11 - 1.0F, value12 - value24, value11 + value24 + 1.0F, value24);
            this.field2.field21.method11(mixinhelper_41, value11 - value24 + 1.0F, -value24, value24, value11 + 1.0F + value24);
            this.field2.field21.method11(mixinhelper_41, value11 * 2.0F + 1.0F, -value24, value24, value11 + 1.0F + value24);
            this.field2.field21.method11(mixinhelper_41, value11 + 1.0F, -value24, value11 + 1.0F, value24);
         }
      }

      for (DefaultKeystrokeKey framework7extension226 : list3) {
         MixinCore9Extension mixincore9extension28 = (MixinCore9Extension)framework7extension226.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
         if (framework7extension226.isEnabled() && flag22 && flag23) {
            this.field2
               .field21
               .method11(mixinhelper_41, mixincore9extension28, mixincore9extension28.getX() - 0.0F, mixincore9extension28.getY() - 0.0F, mixincore9extension28.getWidth(), mixincore9extension28.getHeight(), (Float)this.field2.borderThickness.get());
         }

         framework7extension226.method26();
         mixincore9extension28.method16(0.0F, 0.0F);
      }

      mixinhelper_41.pop();
   }

   public Collection<DefaultKeystrokeKey> method9() {
      return this.field1.values();
   }

   @Generated
   public float method10() {
      return this.field3;
   }

   @Generated
   public float method11() {
      return this.field4;
   }
}
