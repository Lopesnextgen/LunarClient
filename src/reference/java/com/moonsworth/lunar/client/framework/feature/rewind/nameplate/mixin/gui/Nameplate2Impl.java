package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.BridgeExtension2$Data;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.input.KeyInputTypeLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import lombok.Generated;

public class Nameplate2Impl extends Nameplate2 {
   private char character;
   private int keyCode;
   private int modifiers;
   private boolean field1;
   private boolean field2;
   private KeyInputTypeLegacy field3;

   @Override
   public void method1(ByteBufLoader var1) {
      this.character = var1.readChar();
      this.keyCode = var1.readVarInt();
      this.modifiers = var1.readVarInt();
      this.field1 = var1.readBoolean();
      this.field2 = var1.readBoolean();
      this.field3 = var1.method9(KeyInputTypeLegacy.class);
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeChar(this.character);
      var1.method11(this.keyCode);
      var1.method11(this.modifiers);
      var1.writeBoolean(this.field1);
      var1.writeBoolean(this.field2);
      var1.method10(this.field3);
   }

   @Override
   public void method3(Nameplate4 var1) {
      com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2 var2 = var1.method8();
      Bridge5Extension6 var3 = var2.method5();
      if (var3 != null) {
         var2.method19(this.field1);
         var2.method20(this.field2);
         float var4 = var1.method6().method41().getPartialTick();
         ThreadModuleDump71 var5 = var1.method6().method46().method16();
         int var6 = var2.method3(var4, var5.getScaledWidth());
         int var7 = var2.method4(var4, var5.getScaledHeight());
         AbstractRenderContext var8;
         if (ThreadModuleDump63.MC_VERSION <= 5) {
            var8 = AbstractRenderContext.method32();
         } else {
            BridgeExtension2$Data var9 = BridgeExtension2_11.method50();
            if (ThreadModuleDump63.MC_VERSION >= 17) {
               var9.method4(Bridge.method8().method84());
            }

            var9.method1(Bridge.method8().method61());
            var8 = var9.method7();
         }

         boolean var12 = ThreadModuleDump63.method7() != null;
         if (ThreadModuleDump63.MC_VERSION >= 7 && ThreadModuleDump63.method3().bridge$getGameRenderer().bridge$getCamera() == null) {
            var12 = false;
         }

         if (var12) {
            try {
               var3.bridge$drawScreen(var8, var6, var7, var4);
            } catch (Exception var11) {
            }
         }

         switch (this.field3) {
            case PRESS:
               var3.bridge$keyTyped(this.character, this.keyCode, this.modifiers);
               break;
            case RELEASE:
               if (ThreadModuleDump63.MC_VERSION >= 6) {
                  var3.bridge$keyReleased(this.keyCode, this.modifiers);
               }
               break;
            case CHAR:
               if (ThreadModuleDump63.MC_VERSION >= 6) {
                  var3.bridge$charTyped(this.character, this.modifiers);
               }
         }
      }
   }

   @Generated
   public Nameplate2Impl() {
   }

   @Generated
   public Nameplate2Impl(char var1, int var2, int var3, boolean var4, boolean var5, KeyInputTypeLegacy var6) {
      this.character = var1;
      this.keyCode = var2;
      this.modifiers = var3;
      this.field1 = var4;
      this.field2 = var5;
      this.field3 = var6;
   }
}
