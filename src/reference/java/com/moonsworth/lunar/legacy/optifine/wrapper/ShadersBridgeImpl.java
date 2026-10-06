package com.moonsworth.lunar.legacy.optifine.wrapper;

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

public class ShadersBridgeImpl implements ShadersBridge {
   public ShadersBridgeImpl() {
   }

   public boolean hasShadowPass() {
      return Shaders.isShadowPass;
   }

   public String getShaderPack() {
      return Shaders.getShaderPackName();
   }

   public VertexFormatBridge getEntityModelVertexFormat() {
      return (VertexFormatBridge)SVertexFormat.defVertexFormatTextured;
   }

   public boolean setShaderPack(String text1) {
      if (!Objects.equals(Shaders.currentShaderName, text1)) {
         Shaders.setShaderPack(text1);
         return true;
      } else {
         return false;
      }
   }

   public String getPackNone() {
      return "OFF";
   }

   public String getPackDefault() {
      return "(internal)";
   }

   public ShaderProgramBridge getProgramBasic() {
      return (ShaderProgramBridge)Shaders.ProgramBasic;
   }

   public void pushUseProgram(ShaderProgramBridge slayer71) {
      Shaders.pushProgram();
      Shaders.useProgram((Program)slayer71);
   }

   public void popProgram() {
      Shaders.popProgram();
   }

   public void bindTargetDfb() {
      GL30.glBindFramebuffer(36160, Shaders.dfb);
   }

   public boolean isRenderingDfb() {
      return Shaders.isRenderingDfb;
   }

   public void restoreViewport() {
      GL11.glViewport(0, 0, Shaders.renderWidth, Shaders.renderHeight);
   }

   public void beginEntitiesGlowing() {
      Shaders.beginEntitiesGlowing();
   }

   public void endEntitiesGlowing() {
      Shaders.endEntitiesGlowing();
   }

   public void nextEntity(BridgeExtension bridgeextension1) {
      Shaders.nextEntity((Entity)bridgeextension1);
   }
}
