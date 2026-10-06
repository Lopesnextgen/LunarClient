package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.bridge.Bridge_53;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Project;

public class LegacyGlStateManagerBridge implements Extension {
   @Annotation2(min = 1)
   public static Bridge_53 field1;

   public boolean method1() {
      return Minecraft.theMinecraft.isCallingFromMinecraftThread();
   }

   public boolean method2() {
      return this.method1() ? true : this.method3();
   }

   public boolean method3() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? Minecraft.theMinecraft.debugRenderer$v1_12 == null : Minecraft.theMinecraft.loadingScreen == null;
   }

   public void method4() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.pushMatrix();
      } else {
         GL11.glPushMatrix();
      }
   }

   public void method5() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.popMatrix();
      } else {
         GL11.glPopMatrix();
      }
   }

   public void bridge$scale(float var1, float var2, float var3) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.scale(var1, var2, var3);
      } else {
         GL11.glScaled(var1, var2, var3);
      }
   }

   public void method12(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.bindTexture(var1);
      } else {
         GL11.glBindTexture(3553, var1);
      }
   }

   public int method13() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName : GL11.glGetInteger(3553);
   }

   public int method14() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return GlStateManager.activeTextureUnit;
      } else {
         throw new RuntimeException("getActiveTexture() isn't supported in 1.7");
      }
   }

   public void method7(float var1, float var2, float var3, float var4) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.color(var1, var2, var3, var4);
      } else {
         GL11.glColor4f(var1, var2, var3, var4);
      }
   }

   public void method8(float var1, float var2, float var3) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.color(var1, var2, var3);
      } else {
         GL11.glColor3f(var1, var2, var3);
      }
   }

   public void method34() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableBlend();
      } else {
         GL11.glEnable(3042);
      }
   }

   public boolean method37() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.blendState.blend.currentState : GL11.glGetBoolean(3042);
   }

   public void method31() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableTexture2D();
      } else {
         GL11.glEnable(3553);
      }
   }

   public void method35() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableBlend();
      } else {
         GL11.glDisable(3042);
      }
   }

   public void method32() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableTexture2D();
      } else {
         GL11.glDisable(3553);
      }
   }

   public boolean method33() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? GlStateManager.textureState[GlStateManager.activeTextureUnit].texture2DState.currentState
         : GL11.glGetBoolean(3553);
   }

   public void method15(int var1, int var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.blendFunc(var1, var2);
      } else {
         GL11.glBlendFunc(var1, var2);
      }
   }

   public void method40() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableAlpha();
      } else {
         GL11.glDisable(3008);
      }
   }

   public void method16(int var1, float var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.alphaFunc(var1, var2);
      } else {
         GL11.glAlphaFunc(var1, var2);
      }
   }

   public int method17() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.alphaState.func : GL11.glGetInteger(3009);
   }

   public void method18(int var1, int var2, int var3, int var4) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.tryBlendFuncSeparate(var1, var2, var3, var4);
      } else {
         OpenGlHelper.glBlendFunc(var1, var2, var3, var4);
      }
   }

   public void method26(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.shadeModel(var1);
      } else {
         GL11.glShadeModel(var1);
      }
   }

   public int method27() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.activeShadeModel : GL11.glGetInteger(2900);
   }

   public void method38() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableAlpha();
      } else {
         GL11.glEnable(3008);
      }
   }

   public boolean method39() {
      return LegacyRenderTypeFactory.field9.field3;
   }

   public void bridge$translate(float var1, float var2, float var3) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.translate(var1, var2, var3);
      } else {
         GL11.glTranslatef(var1, var2, var3);
      }
   }

   public void method6(float var1, float var2, float var3, float var4) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.rotate(var1, var2, var3, var4);
      } else {
         GL11.glRotatef(var1, var2, var3, var4);
      }
   }

   public boolean[] method19() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return new boolean[]{
            GlStateManager.colorMaskState.red, GlStateManager.colorMaskState.green, GlStateManager.colorMaskState.blue, GlStateManager.colorMaskState.alpha
         };
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   public void method21(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.depthMask(var1);
      } else {
         GL11.glDepthMask(var1);
      }
   }

   public void method20(boolean var1, boolean var2, boolean var3, boolean var4) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.colorMask(var1, var2, var3, var4);
      } else {
         GL11.glColorMask(var1, var2, var3, var4);
      }
   }

   public boolean method22() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.depthState.maskEnabled : GL11.glGetBoolean(2930);
   }

   public void method23(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.depthFunc(var1);
      } else {
         GL11.glDepthFunc(var1);
      }
   }

   public void method29() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableLighting();
      } else {
         GL11.glDisable(2896);
      }
   }

   public void method28() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableLighting();
      } else {
         GL11.glEnable(2896);
      }
   }

   public boolean method30() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.lightingState.currentState : GL11.glGetBoolean(2896);
   }

   public void method24(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.matrixMode(var1);
      } else {
         GL11.glMatrixMode(var1);
      }
   }

   public int method25() {
      return GL11.glGetInteger(2976);
   }

   public void method42() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableDepth();
      } else {
         GL11.glDisable(2929);
      }
   }

   public void method41() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableDepth();
      } else {
         GL11.glEnable(2929);
      }
   }

   public boolean method43() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.depthState.depthTest.currentState : GL11.glGetBoolean(2929);
   }

   public void method47() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableRescaleNormal();
      } else {
         GL11.glEnable(32826);
      }
   }

   public void method48() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableRescaleNormal();
      } else {
         GL11.glDisable(32826);
      }
   }

   public boolean method49() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.rescaleNormalState.currentState : GL11.glGetBoolean(32826);
   }

   public void method44() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableCull();
      } else {
         GL11.glEnable(2884);
      }
   }

   public void method45() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableCull();
      } else {
         GL11.glDisable(2884);
      }
   }

   public boolean method46() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.cullState.cullFace.currentState : GL11.glGetBoolean(2884);
   }

   public int method6() {
      return GLAllocation.generateDisplayLists(1);
   }

   public void method2(int var1) {
      GL11.glNewList(var1, 4864);
   }

   public void method3(int var1) {
      GL11.glEndList();
   }

   public void method80(int var1) {
      GL11.glCallList(var1);
   }

   public void method81(int var1, int var2) {
      GL11.glDeleteLists(var1, var2);
   }

   public boolean method82() {
      return false;
   }

   public void method55() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableColorMaterial();
      } else {
         GL11.glEnable(2903);
      }
   }

   public void method58(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.setActiveTexture(var1);
      } else {
         OpenGlHelper.setActiveTexture(var1);
      }
   }

   public void method50() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enablePolygonOffset();
      } else {
         GL11.glEnable(32823);
      }
   }

   public void method53(float var1, float var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.doPolygonOffset(var1, var2);
      } else {
         GL11.glPolygonOffset(var1, var2);
      }
   }

   public void method51() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disablePolygonOffset();
      } else {
         GL11.glDisable(32823);
      }
   }

   public boolean method52() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.polygonOffsetState.polygonOffsetFill.currentState : GL11.glGetBoolean(32823);
   }

   public void method70(FloatBuffer var1) {
      GL11.glMultMatrix(var1);
   }

   public void method71(float var1) {
      GL11.glLineWidth(var1);
   }

   public void method72(boolean var1) {
      if (var1) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }
   }

   public void method73(int var1, int var2, FloatBuffer var3) {
      switch (var2) {
         case 2:
            GL20.glUniformMatrix2(var1, false, var3);
            break;
         case 3:
            GL20.glUniformMatrix3(var1, false, var3);
            break;
         case 4:
            GL20.glUniformMatrix4(var1, false, var3);
      }
   }

   public void method54(int var1, int var2, int var3, int var4) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.viewport(var1, var2, var3, var4);
      } else {
         GL11.glViewport(var1, var2, var3, var4);
      }
   }

   public void bridge$loadIdentity() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.loadIdentity();
      } else {
         GL11.glLoadIdentity();
      }
   }

   public void method75(float var1, float var2, float var3, float var4) {
      Project.gluPerspective(var1, var2, var3, var4);
   }

   public void method76(float var1, float var2, float var3, FloatBuffer var4, FloatBuffer var5, IntBuffer var6, FloatBuffer var7) {
      GLU.gluProject(var1, var2, var3, var4, var5, var6, var7);
   }

   public void method79(MixinHelper_21 var1) {
   }

   public void method56() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableColorMaterial();
      } else {
         GL11.glDisable(2903);
      }
   }

   public boolean method57() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.colorMaterialState.colorMaterial.currentState : GL11.glGetBoolean(2903);
   }

   public void method59(double var1, double var3, double var5, double var7, double var9, double var11) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.ortho(var1, var3, var5, var7, var9, var11);
      } else {
         GL11.glOrtho(var1, var3, var5, var7, var9, var11);
      }
   }

   public boolean method60() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.fogState.fog.currentState : GL11.glGetBoolean(2912);
   }

   public void method61() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableFog();
      } else {
         GL11.glEnable(2912);
      }
   }

   public void method62() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.disableFog();
      } else {
         GL11.glDisable(2912);
      }
   }

   public float method64() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.fogState.start : GL11.glGetFloat(2915);
   }

   public float method65() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? GlStateManager.fogState.end : GL11.glGetFloat(2916);
   }

   public void method68(float var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.setFogStart(var1);
      } else {
         GL11.glFogf(2915, var1);
      }
   }

   public void method69(float var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.setFogEnd(var1);
      } else {
         GL11.glFogf(2916, var1);
      }
   }

   public void method63(float var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.setFogDensity(var1);
      } else {
         GL11.glFogf(2914, var1);
      }
   }

   public Bridge_53 method7() {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return null;
      }

      if (field1 == null) {
         field1 = new Bridge_53();
      }

      return field1;
   }

   public void method8() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         field1 = null;
      }
   }

   public Bridge7_2 method85() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         String var6 = GlStateManager.glGetString$v1_12(7936);
         String var7 = "OpenGL";
         String var8 = GlStateManager.glGetString$v1_12(7938);
         String var9 = GlStateManager.glGetString$v1_12(7937);
         int var10 = GL11.glGetInteger(3379);
         return new Bridge7_2(var6, var7, var8, var9, var10);
      } else {
         String var1 = GL11.glGetString(7936);
         String var2 = "OpenGL";
         String var3 = GL11.glGetString(7938);
         String var4 = GL11.glGetString(7937);
         int var5 = GL11.glGetInteger(3379);
         return new Bridge7_2(var1, var2, var3, var4, var5);
      }
   }

   public void method86(boolean var1) {
      if (var1) {
         GL11.glEnable(3089);
      } else {
         GL11.glDisable(3089);
      }
   }

   public void method87(int var1, int var2, int var3, int var4) {
      GL11.glScissor(var1, var2, var3, var4);
   }
}
