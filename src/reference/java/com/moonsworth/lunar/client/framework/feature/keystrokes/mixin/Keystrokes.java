package com.moonsworth.lunar.client.framework.feature.keystrokes.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.Module;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class Keystrokes {
   private final Map<KeyCode, Framework7Extension2> field1 = new HashMap<>();
   private final com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes field2;
   private float field3;
   private float field4;

   public Keystrokes(com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes var1) {
      this.field2 = var1;
   }

   public void init() {
      if (this.field1.isEmpty()) {
         MixinHelper_15 var1 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindForward();
         MixinHelper_15 var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindLeft();
         MixinHelper_15 var3 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindBack();
         MixinHelper_15 var4 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindRight();
         MixinHelper_15 var5 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindJump();
         MixinHelper_15 var6 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindAttack();
         MixinHelper_15 var7 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindUseItem();
         this.method2(
            new Framework7Extension2(this.field2, var1, KeyCode.KEY_W, this.field2.copyOptions("forward", new Object[0]), this),
            new Framework7Extension2(this.field2, var2, KeyCode.KEY_A, this.field2.copyOptions("left", new Object[0]), this),
            new Framework7Extension2(this.field2, var3, KeyCode.KEY_S, this.field2.copyOptions("back", new Object[0]), this),
            new Framework7Extension2(this.field2, var4, KeyCode.KEY_D, this.field2.copyOptions("right", new Object[0]), this),
            new Framework7Extension2(this.field2, var5, KeyCode.KEY_SPACE, this.field2.copyOptions("jump", new Object[0]), this),
            new Framework7Extension2(this.field2, var6, KeyCode.KEY_MOUSE1, this.field2.copyOptions("attack", new Object[0]), this),
            new Framework7Extension2(this.field2, var7, KeyCode.KEY_MOUSE2, this.field2.copyOptions("use", new Object[0]), this)
         );
      }
   }

   private Framework7Extension2 method1(KeyCode var1) {
      return this.field1.get(var1);
   }

   private void method2(Framework7Extension2... var1) {
      for (Framework7Extension2 var5 : var1) {
         this.field1.put(var5.method27(), var5);
      }
   }

   public void method3(Collection<Framework7Extension2> var1) {
      for (Framework7Extension2 var3 : var1) {
         float var4 = 0.0F;
         float var5 = 0.0F;
         KeyCode var6 = var3.method27();
         if (var6 == KeyCode.KEY_W) {
            var4 += this.method5(KeyCode.KEY_A) + 1.0F;
         }

         if (var6 == KeyCode.KEY_A || var6 == KeyCode.KEY_S || var6 == KeyCode.KEY_D || var6 == KeyCode.KEY_SPACE || var3.method24()) {
            var5 += this.method7(KeyCode.KEY_W) + 1.0F;
            if (var6 == KeyCode.KEY_S || var6 == KeyCode.KEY_D) {
               var4 += this.method5(KeyCode.KEY_A) + 1.0F;
            }

            if (var6 == KeyCode.KEY_D) {
               var4 += this.method5(KeyCode.KEY_S) + 1.0F;
            }

            if (var6 == KeyCode.KEY_SPACE || var3.method24()) {
               var5 += this.max(this.method7(KeyCode.KEY_A), this.method7(KeyCode.KEY_S), this.method7(KeyCode.KEY_D)) + 1.0F;
               if (var3.method24()) {
                  var5 += this.method7(KeyCode.KEY_SPACE) + 1.0F;
                  if (var6 == KeyCode.KEY_MOUSE2) {
                     var4 += this.method5(KeyCode.KEY_MOUSE1) + 1.0F;
                  }
               }
            }
         }

         MixinCore9Extension var7 = (MixinCore9Extension)var3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1);
         var7.method27(HudAnchor.TOP_LEFT);
         var7.method17(var4, var5);
      }
   }

   protected void method4(Framework7Extension2 var1) {
      this.method3(Collections.singletonList(var1));
   }

   private float method5(KeyCode var1) {
      Framework7Extension2 var2 = this.method1(var1);
      if (var1 == KeyCode.KEY_SPACE) {
         return this.method6(KeyCode.KEY_A) + 1.0F + this.method6(KeyCode.KEY_S) + 1.0F + this.method6(KeyCode.KEY_D);
      } else {
         return var1 != KeyCode.KEY_MOUSE1 && var1 != KeyCode.KEY_MOUSE2
            ? (Float)var2.CCORCRRICRIIHROIIOIRHIHRRHIICH.get()
            : (this.method6(KeyCode.KEY_SPACE) - 1.0F) / 2.0F;
      }
   }

   protected float method6(KeyCode var1) {
      Framework7Extension2 var2 = this.method1(var1);
      if (var1 == KeyCode.KEY_SPACE) {
         return (Float)var2.CCORCRRICRIIHROIIOIRHIHRRHIICH.get() * 3.0F + 2.0F;
      } else {
         return var1 != KeyCode.KEY_MOUSE1 && var1 != KeyCode.KEY_MOUSE2
            ? (Float)var2.CCORCRRICRIIHROIIOIRHIHRRHIICH.get()
            : ((Float)var2.CCORCRRICRIIHROIIOIRHIHRRHIICH.get() * 3.0F + 1.0F) / 2.0F;
      }
   }

   protected float method7(KeyCode var1) {
      Framework7Extension2 var2 = this.method1(var1);
      if (!var2.isEnabled()) {
         return 0.0F;
      } else {
         return var1 == KeyCode.KEY_SPACE ? (Float)var2.CCORCRRICRIIHROIIOIRHIHRRHIICH.get() / 2.0F : (Float)var2.CCORCRRICRIIHROIIOIRHIHRRHIICH.get();
      }
   }

   private float max(float... var1) {
      float var2 = 0.0F;

      for (float var6 : var1) {
         var2 = Math.max(var2, var6);
      }

      return var2;
   }

   public void method8(MixinHelper_4 var1, float var2) {
      Collection var3 = this.method9();

      for (Framework7Extension2 var5 : var3) {
         boolean var6 = !var5.method25() || (Boolean)this.field2.keyStrokesMovement.get();
         if (var5.method24() && !(Boolean)this.field2.keyStrokesClicks.get()) {
            var6 = false;
         }

         if (var5.method27() == KeyCode.KEY_SPACE && !(Boolean)this.field2.keyStrokesSpacebar.get()) {
            var6 = false;
         }

         var5.method6(var6);
         var5.method17();
      }

      this.method3(var3);
      var1.push();
      float var19 = Float.MAX_VALUE;
      float var20 = Float.MAX_VALUE;
      float var21 = 0.0F;
      float var7 = 0.0F;

      for (Framework7Extension2 var9 : var3) {
         MixinCore9Extension var10 = (MixinCore9Extension)var9.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1);
         if (var9.isEnabled()) {
            var1.push();
            var1.method38(var10.getX() - 0.0F, var10.getY() - 0.0F, 0.0F);
            ((com.moonsworth.lunar.client.framework.feature.keystrokes.Framework7Extension2.Data4)var10)
               .method6(var1, var2 + var10.getX() + var10.getY() - 0.0F - 0.0F);
            var1.pop();
         }

         var19 = Math.min(var10.getX(), var19);
         var20 = Math.min(var10.getY(), var20);
         var21 = Math.max(var10.getX() + var10.getWidth(), var21);
         var7 = Math.max(var10.getY() + var10.getHeight(), var7);
      }

      this.field3 = var21 - var19;
      this.field4 = var7 - var20;
      boolean var22 = (Boolean)this.field2.border.get();
      boolean var23 = (Boolean)this.field2.innerBorder.get();
      if (var22 && !var23) {
         float var24 = (Float)this.field2.borderThickness.get();
         float var11 = (Float)this.field2.boxSize.get();
         float var12 = 0.0F;
         float var13 = this.field4;
         boolean var14 = (Boolean)this.field2.keyStrokesMovement.get();
         boolean var15 = (Boolean)this.field2.keyStrokesClicks.get();
         boolean var16 = (Boolean)this.field2.keyStrokesSpacebar.get();
         boolean var17 = var15 && var16;
         if ((Boolean)this.field2.keyStrokesMovement.get()) {
            if (!var15 && !var16) {
               var13 -= 2.0F;
            } else if (!var15) {
               var13--;
            }

            var12 += var11 + 1.0F;
            var13 -= var11 + 1.0F;
         } else {
            int var18 = !var17 && !var16 ? 3 : 2;
            var12 += var18;
            var13 -= var17 ? 2.0F : 3.0F;
         }

         if (!var14) {
            this.field2.field21.method11(var1, (Module)this.field2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1), 0.0F, var12, this.field3, var13, var24);
         } else {
            this.field2.field21.method11(var1, -var24, var12, var24, var13);
            this.field2.field21.method11(var1, this.field3, var12, var24, var13);
            this.field2.field21.method11(var1, -var24, var12 + var13, this.field3 + var24 * 2.0F, var24);
            this.field2.field21.method11(var1, -var24, var12 - var24, var11 + var24 / 2.0F + 1.0F, var24);
            this.field2.field21.method11(var1, this.field3 - var11 - 1.0F, var12 - var24, var11 + var24 + 1.0F, var24);
            this.field2.field21.method11(var1, var11 - var24 + 1.0F, -var24, var24, var11 + 1.0F + var24);
            this.field2.field21.method11(var1, var11 * 2.0F + 1.0F, -var24, var24, var11 + 1.0F + var24);
            this.field2.field21.method11(var1, var11 + 1.0F, -var24, var11 + 1.0F, var24);
         }
      }

      for (Framework7Extension2 var26 : var3) {
         MixinCore9Extension var28 = (MixinCore9Extension)var26.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1);
         if (var26.isEnabled() && var22 && var23) {
            this.field2
               .field21
               .method11(var1, var28, var28.getX() - 0.0F, var28.getY() - 0.0F, var28.getWidth(), var28.getHeight(), (Float)this.field2.borderThickness.get());
         }

         var26.method26();
         var28.method16(0.0F, 0.0F);
      }

      var1.pop();
   }

   public Collection<Framework7Extension2> method9() {
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
