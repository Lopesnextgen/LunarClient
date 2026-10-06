package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.bridge.slayer.Slayer7;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.optifine.shaders.Program;
import net.optifine.shaders.SVertexFormat;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Slayer3Handler implements Slayer3 {
   public boolean hasShadowPass() {
      return Shaders.isShadowPass;
   }

   public String getShaderPack() {
      return Shaders.getShaderPackName();
   }

   public Bridge_63 getEntityModelVertexFormat() {
      return (Bridge_63)SVertexFormat.defVertexFormatTextured;
   }

   public boolean setShaderPack(String var1) {
      if (!Objects.equals(Shaders.currentShaderName, var1)) {
         Shaders.setShaderPack(var1);
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

   public Slayer7 getProgramBasic() {
      return (Slayer7)Shaders.ProgramBasic;
   }

   public void pushUseProgram(Slayer7 var1) {
      Shaders.pushProgram();
      Shaders.useProgram((Program)var1);
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

   public void nextEntity(BridgeExtension var1) {
      Shaders.nextEntity((Entity)var1);
   }
}
