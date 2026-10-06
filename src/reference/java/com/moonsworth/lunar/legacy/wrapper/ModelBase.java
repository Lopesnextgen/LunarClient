package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.EntityModelBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.ShadeModel;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.math.FastMath;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelBase extends net.minecraft.client.model.ModelBase implements EntityModelBridge {
   private final net.minecraft.client.model.ModelBase field1;

   public ModelBase() {
      this.textureWidth = 256;
      this.textureHeight = 256;
      this.setTextureOffset("wing.skin", -56, 88);
      this.setTextureOffset("wingtip.skin", -56, 144);
      this.setTextureOffset("wing.bone", 112, 88);
      this.setTextureOffset("wingtip.bone", 112, 136);
      ModelRenderer modelrenderer1 = new ModelRenderer(this, "wing");
      modelrenderer1.setRotationPoint(-12.0F, 5.0F, 2.0F);
      modelrenderer1.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      modelrenderer1.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      ModelRenderer modelrenderer2 = new ModelRenderer(this, "wingtip");
      modelrenderer2.setRotationPoint(-56.0F, 0.0F, 0.0F);
      modelrenderer2.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      modelrenderer2.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      modelrenderer1.addChild(modelrenderer2);
      this.field1 = new net.minecraft.client.model.ModelBase() {};
      this.field1.textureHeight = 256;
      this.field1.textureWidth = 256;
      this.field1.setTextureOffset("wing.skin", -56, 88);
      this.field1.setTextureOffset("wingtip.skin", -56, 144);
      this.field1.setTextureOffset("wing.bone", 112, 88);
      this.field1.setTextureOffset("wingtip.bone", 112, 136);
      ModelRenderer modelrenderer3 = new ModelRenderer(this.field1, "wing");
      modelrenderer3.setRotationPoint(-12.0F, 5.0F, 2.0F);
      modelrenderer3.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      modelrenderer3.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      ModelRenderer modelrenderer4 = new ModelRenderer(this.field1, "wingtip");
      modelrenderer4.setRotationPoint(-56.0F, 0.0F, 0.0F);
      modelrenderer4.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      modelrenderer4.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      modelrenderer3.addChild(modelrenderer4);
   }

   public void method1(BridgeExtension3_5 bridgeextension3_51, float value2, float value3, ResourceLocationBridge horsestats144) {
      bridgeextension3_51.method12(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
      Minecraft.getMinecraft().getTextureManager().bindTexture((ResourceLocation)horsestats144);
      bridgeextension3_51.push();
      bridgeextension3_51.scale(value2, value2, value2);
      GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
      GL11.glTranslatef(0.0F, 0.5F, 0.25F);
      long number5 = System.currentTimeMillis();
      if (Ref.method4().method40().method85().method19()) {
         number5 = Ref.method4().method40().method85().method35().method41().getTime();
      }

      float value7 = (float)(number5 % 2000L) / 2000.0F * (float) Math.PI * 2.0F;
      List list8 = Ref.MC_VERSION >= 1 ? this.field1.boxList : this.field1.boxList$v1_7;
      ModelRenderer modelrenderer9 = (ModelRenderer)list8.get(0);
      ModelRenderer modelrenderer10 = (ModelRenderer)list8.get(1);

      for (int index11 = 0; index11 < 2; index11++) {
         GL11.glEnable(2884);
         modelrenderer9.rotateAngleX = -0.125F - (float)FastMath.method1(value7) * 0.2F;
         modelrenderer9.rotateAngleY = 0.75F;
         modelrenderer9.rotateAngleZ = (float)(FastMath.sin(value7) + 0.125) * 0.8F;
         modelrenderer10.rotateAngleZ = (float)(FastMath.sin(value7 + 2.0F) + 0.5) * 0.75F;
         modelrenderer9.render(value3);
         bridgeextension3_51.scale(-1.0F, 1.0F, 1.0F);
         if (index11 == 0) {
            GL11.glCullFace(1028);
         }

         bridgeextension3_51.method12(ShadeModel.GL_FLAT);
      }

      bridgeextension3_51.pop();
      GL11.glCullFace(1029);
      GL11.glDisable(2884);
   }
}
