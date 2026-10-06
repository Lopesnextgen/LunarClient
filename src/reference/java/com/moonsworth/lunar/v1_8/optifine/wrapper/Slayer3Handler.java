package com.moonsworth.lunar.v1_8.optifine.wrapper;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.bridge.optifine.ShaderProgramBridge;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.optifine.shaders.Program;
import net.optifine.shaders.SVertexFormat;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Slayer3Handler implements ShadersBridge {
   public Slayer3Handler() {
   }

   @Override
   public boolean hasShadowPass() {
      return Shaders.isShadowPass;
   }

   @Override
   public String getShaderPack() {
      return Shaders.getShaderPackName();
   }

   @Override
   public VertexFormatBridge getEntityModelVertexFormat() {
      return (VertexFormatBridge)SVertexFormat.defVertexFormatTextured;
   }

   @Override
   public boolean setShaderPack(String text1) {
      if (!Objects.equals(Shaders.currentShaderName, text1)) {
         Shaders.setShaderPack(text1);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String getPackNone() {
      return "OFF";
   }

   @Override
   public String getPackDefault() {
      return "(internal)";
   }

   @Override
   public ShaderProgramBridge getProgramBasic() {
      return (ShaderProgramBridge)Shaders.ProgramBasic;
   }

   @Override
   public void pushUseProgram(ShaderProgramBridge slayer71) {
      Shaders.pushProgram();
      Shaders.useProgram((Program)slayer71);
   }

   @Override
   public void popProgram() {
      Shaders.popProgram();
   }

   @Override
   public void bindTargetDfb() {
      GL30.glBindFramebuffer(36160, Shaders.dfb);
   }

   @Override
   public boolean isRenderingDfb() {
      return Shaders.isRenderingDfb;
   }

   @Override
   public void restoreViewport() {
      GL11.glViewport(0, 0, Shaders.renderWidth, Shaders.renderHeight);
   }

   @Override
   public void beginEntitiesGlowing() {
      Shaders.beginEntitiesGlowing();
   }

   @Override
   public void endEntitiesGlowing() {
      Shaders.endEntitiesGlowing();
   }

   @Override
   public void nextEntity(BridgeExtension bridgeextension1) {
      Shaders.nextEntity((Entity)bridgeextension1);
   }
}
