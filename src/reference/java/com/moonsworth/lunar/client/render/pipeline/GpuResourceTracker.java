package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationDebugOption;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.files.ValuePair;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class GpuResourceTracker {
   private static final boolean TRACKING_ENABLED = Boolean.getBoolean("lunar.resourceTracking");
   private static GpuResourceTracker field2;
   private final Map<Integer, Throwable> textures = new LinkedHashMap<>();
   private final Map<Integer, Throwable> buffers = new LinkedHashMap<>();
   private final Map<Long, ValuePair<Throwable, Long>> offHeapAllocations = new LinkedHashMap<>();

   public GpuResourceTracker() {
   }

   public void trackTexture(int number1) {
      if (!this.isTrackingEnabled()) {
         this.textures.put(number1, null);
      } else {
         this.textures.put(number1, new Throwable());
      }
   }

   public void untrackTexture(int index1) {
      this.textures.remove(index1);
   }

   public void trackBuffer(int number1) {
      if (!this.isTrackingEnabled()) {
         this.buffers.put(number1, null);
      } else {
         this.buffers.put(number1, new Throwable());
      }
   }

   public void untrackBuffer(int index1) {
      this.buffers.remove(index1);
   }

   public void trackOffHeap(long number1, long number3) {
      if (!this.isTrackingEnabled()) {
         this.offHeapAllocations.put(number1, ValuePair.method1(null, number3));
      } else {
         this.offHeapAllocations.put(number1, ValuePair.method1(new Throwable(), number3));
      }
   }

   public void untrackOffHeap(long index1) {
      this.offHeapAllocations.remove(index1);
   }

   public int getTextureCount() {
      return this.textures.size();
   }

   public int getBufferCount() {
      return this.buffers.size();
   }

   public String dumpOffHeapAllocations() {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();

      try (PrintStream stream2 = new PrintStream(bytearrayoutputstream1)) {
         this.offHeapAllocations.forEach((arg1x, arg2x) -> {
            stream2.printf("%d ", arg2x.field2);
            ((Throwable)arg2x.field1).printStackTrace(stream2);
            stream2.print("\n");
         });
      }

      return bytearrayoutputstream1.toString();
   }

   public String dump() {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();

      try (PrintStream stream2 = new PrintStream(bytearrayoutputstream1)) {
         stream2.printf("Texture count %d\n", this.textures.size());
         stream2.printf("Buffer count %d\n", this.buffers.size());
         stream2.println("--- Textures Start ---");
         long number3 = 0L;
         int number5 = GL11.glGetInteger(32873);

         for (Entry entry7 : this.textures.entrySet()) {
            int number8 = (Integer)entry7.getKey();
            GL11.glBindTexture(3553, number8);
            int number9 = GL11.glGetTexLevelParameteri(3553, 0, 4096);
            int number10 = GL11.glGetTexLevelParameteri(3553, 0, 4097);
            number3 += (long)number9 * number10;
            stream2.printf("texture id: %d width: %d height: %d\n", number8, number9, number10);
            ((Throwable)entry7.getValue()).printStackTrace(stream2);
         }

         GL11.glBindTexture(3553, number5);
         stream2.println("--- Buffers Start ---");
         long number16 = 0L;
         int number17 = GL11.glGetInteger(34964);

         for (Entry entry19 : this.buffers.entrySet()) {
            int number11 = (Integer)entry19.getKey();
            GL15.glBindBuffer(34962, number11);
            int number12 = GL15.glGetBufferParameteri(34962, 34660);
            number16 += number12;
            stream2.printf("object id: %d size: %d\n", number11, number12);
            ((Throwable)entry19.getValue()).printStackTrace(stream2);
         }

         GL15.glBindBuffer(34962, number17);
         stream2.printf("texture total %d\n", number3);
         stream2.printf("buffer total %d\n", number16);
      } catch (Throwable exception15) {
         LunarLogger.error("Failed to write gpu-objects", exception15);
         return "Error: " + exception15.getMessage();
      }

      return bytearrayoutputstream1.toString();
   }

   public static GpuResourceTracker getInstance() {
      if (field2 == null) {
         field2 = new GpuResourceTracker();
      }

      return field2;
   }

   public boolean isTrackingEnabled() {
      return field1 || !LunarBuildData.field4 && OptimizationDebugOption.ENABLE_RESOURCE_TRACKING.isEnabled();
   }
}
