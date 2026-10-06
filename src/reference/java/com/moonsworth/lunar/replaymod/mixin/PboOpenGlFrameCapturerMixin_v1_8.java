package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.replaymod.render.capturer.CaptureData;
import com.replaymod.render.capturer.OpenGlFrameCapturer;
import com.replaymod.render.capturer.PboOpenGlFrameCapturer;
import com.replaymod.render.capturer.RenderInfo;
import com.replaymod.render.capturer.WorldRenderer;
import com.replaymod.render.rendering.Frame;
import com.replaymod.render.utils.ByteBufferPool;
import java.nio.ByteBuffer;
import javax.swing.JOptionPane;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PboOpenGlFrameCapturer.class)
public abstract class PboOpenGlFrameCapturerMixin_v1_8<F extends Frame, D extends Enum<D> & CaptureData> extends OpenGlFrameCapturer<F, D> {
   public PboOpenGlFrameCapturerMixin_v1_8(WorldRenderer var1, RenderInfo var2) {
      super(var1, var2);
   }

   @Redirect(method = "readFromPbo", at = @At(target = "Lcom/replaymod/render/utils/ByteBufferPool;allocate(I)Ljava/nio/ByteBuffer;", value = "INVOKE"))
   public ByteBuffer ichor$read(int var1) {
      try {
         return ByteBufferPool.allocate(var1);
      } catch (OutOfMemoryError var4) {
         var4.printStackTrace();
         this.shutdown();
         return null;
      }
   }

   private void shutdown() {
      new Thread(
            () -> JOptionPane.showMessageDialog(
               null,
               "Your client has ran out of memory while rendering a video.\nYou can increase memory allocation in the launcher.\n Another common fix is to reduce the Anti-Aliasing to 2x or 4x before rendering the replay.",
               "Out of Memory",
               2
            )
         )
         .start();

      try {
         Thread.sleep(7000L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      ThreadModuleDump63.method3().bridge$shutdownMinecraftApplet();
   }
}
