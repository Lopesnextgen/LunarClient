package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Ref;
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
public abstract class PboOpenGlFrameCapturerMixin<F extends Frame, D extends Enum<D> & CaptureData> extends OpenGlFrameCapturer<F, D> {
   public PboOpenGlFrameCapturerMixin(WorldRenderer worldrenderer1, RenderInfo renderinfo2) {
      super(worldrenderer1, renderinfo2);
   }

   @Redirect(method = "readFromPbo", at = @At(target = "Lcom/replaymod/render/utils/ByteBufferPool;allocate(I)Ljava/nio/ByteBuffer;", value = "INVOKE"))
   public ByteBuffer ichor$read(int value) {
      try {
         return ByteBufferPool.allocate(value);
      } catch (OutOfMemoryError outofmemoryerror4) {
         outofmemoryerror4.printStackTrace();
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
      } catch (InterruptedException interruptedexception2) {
         interruptedexception2.printStackTrace();
      }

      Ref.method3().bridge$shutdownMinecraftApplet();
   }
}
