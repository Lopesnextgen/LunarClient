package com.moonsworth.lunar.client.inactive;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.IOException22;
import com.moonsworth.lunar.MixinHelper27;
import com.moonsworth.lunar.MixinHelper27$Data3;
import com.moonsworth.lunar.MixinHelper28;
import com.moonsworth.lunar.MixinHelper312;
import com.moonsworth.lunar.MixinHelper32;
import com.moonsworth.lunar.MixinHelper3222;
import com.moonsworth.lunar.MixinHelper3223;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import com.moonsworth.lunar.client.inactive.gui.Gui;
import com.moonsworth.lunar.client.inactive.gui.GuiHandler;
import com.moonsworth.lunar.client.inactive.gui.GuiHandler2;
import java.util.Iterator;
import java.util.Map.Entry;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.easing.EasingType;

class Inactive3$Data extends Inactive3$Data7<Gui> {
   public Inactive3$Data(MixinHelper27$Data3 var1, MixinHelper312 var2) {
      super(var1, var2);
   }

   public Gui method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      MixinHelper9_9 var3 = var1.method98();
      if (var3 instanceof MixinHelper3234 var18) {
         AnimationBuilder var19 = new AnimationBuilder().addAnimation(var18.method39(), true);
         return new GuiHandler(var19);
      } else {
         MixinHelper27 var4 = this.method1();
         if (var3 instanceof MixinHelper3222 var5) {
            if (var5.has("anim")) {
               return Inactive3_2.method3(var1, var4.method223(GuiHandler.class).method98(var3.toString()));
            }

            int var20 = 0;
            MixinHelper32 var22 = var5.method8("transition_ticks");
            Evaluatable var21;
            if (var22 != null) {
               var21 = var4.method223(Evaluatable.class).method108(var22);
               var20--;
            } else {
               var21 = null;
            }

            MixinHelper32 var11 = var5.method8("transition_args");
            Double[] var10;
            if (var22 != null) {
               var10 = var4.method223(Double[].class).method108(var11);
               var20--;
            } else {
               var10 = null;
            }

            MixinHelper32 var13 = var5.method8("transition_easing");
            EasingType var12;
            if (var13 != null) {
               var12 = var4.method223(EasingType.class).method108(var13);
               var20--;
            } else {
               var12 = EasingType.NONE;
            }

            GuiHandler2.Data2[] var14 = new GuiHandler2.Data2[var5.size() + var20];
            int var15 = 0;

            for (Iterator var16 = var5.method52(); var16.hasNext(); var15++) {
               Entry var17 = (Entry)var16.next();
               var14[var15] = new GuiHandler2.Data2(
                  new AnimationBuilder().addAnimation((String)var17.getKey(), true),
                  var21,
                  var10,
                  var12,
                  Inactive3_2.method3(var1, var4.method223(Evaluatable.class).method98(((MixinHelper32)var17.getValue()).toString()))
               );
            }

            return new GuiHandler2(var14);
         } else if (!(var3 instanceof MixinHelper3223 var6)) {
            throw new IOException22(var1, "Animation provider must be either a string, object, or array: " + var3.method1().name());
         } else {
            MixinHelper28 var7 = var4.method223(GuiHandler2.Data2.class);
            GuiHandler2.Data2[] var8 = new GuiHandler2.Data2[var6.size()];

            for (int var9 = 0; var9 < var6.size(); var9++) {
               var8[var9] = Inactive3_2.method3(var1, var7.method98(var6.method6(var9).toString()));
            }

            return new GuiHandler2(var8);
         }
      }
   }
}
