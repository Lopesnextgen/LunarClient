package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationdebugmodType;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.files.Files6_2;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Click5 {
   private static final boolean field1 = Boolean.getBoolean("lunar.resourceTracking");
   private static Click5 field2;
   private final Map<Integer, Throwable> field3 = new LinkedHashMap<>();
   private final Map<Integer, Throwable> field4 = new LinkedHashMap<>();
   private final Map<Long, Files6_2<Throwable, Long>> field5 = new LinkedHashMap<>();

   public void method1(int var1) {
      if (!this.method11()) {
         this.field3.put(var1, null);
      } else {
         this.field3.put(var1, new Throwable());
      }
   }

   public void method2(int var1) {
      this.field3.remove(var1);
   }

   public void method3(int var1) {
      if (!this.method11()) {
         this.field4.put(var1, null);
      } else {
         this.field4.put(var1, new Throwable());
      }
   }

   public void method4(int var1) {
      this.field4.remove(var1);
   }

   public void method5(long var1, long var3) {
      if (!this.method11()) {
         this.field5.put(var1, Files6_2.method1(null, var3));
      } else {
         this.field5.put(var1, Files6_2.method1(new Throwable(), var3));
      }
   }

   public void method6(long var1) {
      this.field5.remove(var1);
   }

   public int method7() {
      return this.field3.size();
   }

   public int method8() {
      return this.field4.size();
   }

   public String method9() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      try (PrintStream var2 = new PrintStream(var1)) {
         this.field5.forEach((var1x, var2x) -> {
            var2.printf("%d ", var2x.field2);
            ((Throwable)var2x.field1).printStackTrace(var2);
            var2.print("\n");
         });
      }

      return var1.toString();
   }

   public String dump() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      try (PrintStream var2 = new PrintStream(var1)) {
         var2.printf("Texture count %d\n", this.field3.size());
         var2.printf("Buffer count %d\n", this.field4.size());
         var2.println("--- Textures Start ---");
         long var3 = 0L;
         int var5 = GL11.glGetInteger(32873);

         for (Entry var7 : this.field3.entrySet()) {
            int var8 = (Integer)var7.getKey();
            GL11.glBindTexture(3553, var8);
            int var9 = GL11.glGetTexLevelParameteri(3553, 0, 4096);
            int var10 = GL11.glGetTexLevelParameteri(3553, 0, 4097);
            var3 += (long)var9 * var10;
            var2.printf("texture id: %d width: %d height: %d\n", var8, var9, var10);
            ((Throwable)var7.getValue()).printStackTrace(var2);
         }

         GL11.glBindTexture(3553, var5);
         var2.println("--- Buffers Start ---");
         long var16 = 0L;
         int var17 = GL11.glGetInteger(34964);

         for (Entry var19 : this.field4.entrySet()) {
            int var11 = (Integer)var19.getKey();
            GL15.glBindBuffer(34962, var11);
            int var12 = GL15.glGetBufferParameteri(34962, 34660);
            var16 += var12;
            var2.printf("object id: %d size: %d\n", var11, var12);
            ((Throwable)var19.getValue()).printStackTrace(var2);
         }

         GL15.glBindBuffer(34962, var17);
         var2.printf("texture total %d\n", var3);
         var2.printf("buffer total %d\n", var16);
      } catch (Throwable var15) {
         Slayer.error("Failed to write gpu-objects", var15);
         return "Error: " + var15.getMessage();
      }

      return var1.toString();
   }

   public static Click5 method10() {
      if (field2 == null) {
         field2 = new Click5();
      }

      return field2;
   }

   public boolean method11() {
      return field1 || !LunarBuildData.field4 && OptimizationdebugmodType.FAST_TEXT.isEnabled();
   }
}
