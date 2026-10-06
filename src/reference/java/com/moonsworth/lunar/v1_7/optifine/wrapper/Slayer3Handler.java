package com.moonsworth.lunar.v1_7.optifine.wrapper;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.bridge.slayer.Slayer7;
import com.moonsworth.lunar.v1_7.mixin.ShadersMixin;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.Objects;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import shadersmod.client.EnumShaderOption;
import shadersmod.client.Shaders;

public class Slayer3Handler implements Slayer3 {
   private final IntList activeProgramList = new IntArrayList();

   @Override
   public boolean hasShadowPass() {
      return Shaders.isShadowPass;
   }

   @Override
   public String getShaderPack() {
      return Shaders.getShaderPackName();
   }

   @Override
   public boolean setShaderPack(String var1) {
      if (!Objects.equals(Shaders.shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey()), var1)) {
         Shaders.setShaderPack(var1);
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
   public Slayer7 getProgramBasic() {
      return new Slayer7Handler(1);
   }

   @Override
   public void pushUseProgram(Slayer7 var1) {
      this.activeProgramList.add(Shaders.activeProgram);
      Shaders.useProgram(var1.getId());
   }

   @Override
   public void popProgram() {
      int var1 = this.activeProgramList.removeInt(this.activeProgramList.size() - 1);
      Shaders.useProgram(var1);
   }

   @Override
   public void bindTargetDfb() {
      GL30.glBindFramebuffer(36160, ShadersMixin.dfb());
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
      throw new AbstractMethodErrorImpl();
   }

   @Override
   public void endEntitiesGlowing() {
      throw new AbstractMethodErrorImpl();
   }

   @Override
   public void nextEntity(BridgeExtension var1) {
      Shaders.nextEntity((Entity)var1);
   }
}
