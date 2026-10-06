package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.RenderBlocksBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.cosmetics.skin.CubeDirection;
import com.moonsworth.lunar.client.render.particle.HologramBatchRenderer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector4f;
import mchorse.emoticons.morph.Morph;

public class BlockMorphRenderer implements MorphRenderer {
   private final String field1;
   private final MorphTransform field2;
   public BridgeExtension field3;
   private final Vector4f field4 = new Vector4f();
   private Matrix3f field5 = new Matrix3f();
   private final Bridge3_23 field6;
   private final byte field7;

   public BlockMorphRenderer(Morph morph1) {
      this.field7 = morph1.getMorphNbt().bridge$getByte("Meta");
      String text2 = morph1.getMorphNbt().bridge$getString("Block");
      if (text2 == null || text2.isEmpty()) {
         text2 = "minecraft:stone";
      }

      this.field6 = Bridge.method34().method111(ResourceLocationBridge.create(text2), this.field7);
      this.field1 = morph1.getBone();
      this.field2 = new MorphTransform(morph1);
      this.field5.setIdentity();
   }

   @Override
   public String getBone() {
      return this.field1;
   }

   @Override
   public MorphTransform method2() {
      return this.field2;
   }

   @Override
   public void method3() {
   }

   @Override
   public void method4(Vector4f vector4f1, Matrix3f matrix3f2) {
      this.field4.set(vector4f1);
      this.field5 = matrix3f2;
   }

   @Override
   public void reset(boolean flag1) {
   }

   @Override
   public void method5(BridgeExtension bridgeextension1) {
      this.field3 = bridgeextension1;
   }

   @Override
   public void method7(AbstractRenderContext bridgeextension_91, CameraBridge bridge2_192) {
      RenderBlocksBridge bridge6_23 = bridgeextension_91.method37();
      this.method8(bridgeextension_91, bridge6_23, bridge2_192);
      bridge6_23.method2();
   }

   @Override
   public void method8(AbstractRenderContext bridgeextension_91, RenderBlocksBridge bridge6_22, CameraBridge bridge2_193) {
      bridgeextension_91.push();
      bridgeextension_91.translate(this.field4.x - bridge2_193.bridge$getPosX(), this.field4.y - bridge2_193.bridge$getPosY(), this.field4.z - bridge2_193.bridge$getPosZ());
      if (this.field3 instanceof EntityLivingBridge bridgeextension2_54) {
         float value15 = bridgeextension2_54.bridge$getScale();
         bridgeextension_91.scale(value15, value15, value15);
      }

      HologramBatchRenderer.method2(bridgeextension_91, this.field5);
      Config config14 = Bridge.getMinecraftVersion();
      if (config14 != Config.field1) {
         bridgeextension_91.translate(-0.5, -0.5, config14.method19() ? -0.5 : 0.5);
      }

      WorldBridgeExtension itemcounter6extension16 = Ref.method8();
      int number6;
      if (itemcounter6extension16 != null) {
         int number7 = (int)this.field4.x;
         int number8 = (int)this.field4.y;
         int number9 = (int)this.field4.z;
         number6 = method8(itemcounter6extension16, number7, number8, number9);

         for (CubeDirection pkgtype13 : CubeDirection.VALUES) {
            number6 = Math.max(number6, method8(itemcounter6extension16, number7 + pkgtype13.getStepX(), number8 + pkgtype13.getStepY(), number9 + pkgtype13.getStepZ()));
         }
      } else {
         number6 = 15728880;
      }

      bridge6_22.method1(bridgeextension_91, this.field6, this.field7, number6);
      bridgeextension_91.pop();
   }

   private static int method8(Itemcounter6 itemcounter60, int number1, int number2, int number3) {
      Horsestats20Extension2 horsestats20extension24 = Bridge.method8().method4(number1, number2, number3);
      return itemcounter60.bridge$getPackedLight(horsestats20extension24);
   }

   @Override
   public void method9() {
   }

   @Override
   public boolean method12() {
      return false;
   }

   @Override
   public MorphRenderType method11() {
      return MorphRenderType.BLOCK;
   }

   @Override
   public Vec3iBridge method14() {
      return Bridge.method8().method6(this.field4.x, this.field4.y, this.field4.z);
   }

   @Override
   public BridgeExtension method6() {
      return this.field3;
   }
}
