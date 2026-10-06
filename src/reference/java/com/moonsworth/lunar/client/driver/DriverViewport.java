package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public abstract class DriverViewport implements EventBusAccess, Translatable {
   public static boolean debug = Boolean.getBoolean("lunar.webosr.debug");
   protected final MinecraftBridge field1 = Ref.method3();
   protected int width;
   protected int height;
   protected int framebufferWidth;
   protected int framebufferHeight;
   private final List<DriverComponent<?>> field2 = Collections.synchronizedList(new ArrayList<>());

   public DriverViewport() {
   }

   public final int getWidth() {
      return this.width;
   }

   public final int getHeight() {
      return this.height;
   }

   public final void method3(int number1, int number2, int number3, int number4) {
      this.method4(number1, number2, number3, number4);
      this.width = number1;
      this.height = number2;
      this.framebufferWidth = number3;
      this.framebufferHeight = number4;

      for (DriverComponent markers_26 : this.field2) {
         markers_26.method22(number1, number2, number3, number4);
      }

      if (debug) {
         System.out.printf("[%s - COMPONENTS: %d]%n", this.getClass().getSimpleName(), this.method19(this.method20()));

         for (DriverComponent markers_28 : this.method20()) {
            this.method18("", markers_28);
         }
      }
   }

   public void method4(int number1, int number2, int number3, int number4) {
   }

   public final void method5(MixinHelper_4 mixinhelper_41, MarkerModel.Data5 data52) {
      this.method15(mixinhelper_41, data52);

      for (DriverComponent markers_24 : this.field2) {
         markers_24.method3(mixinhelper_41, data52);
      }
   }

   public void method6(int number1, int number2) {
      this.framebufferWidth = number1;
      this.framebufferHeight = number2;

      for (DriverComponent markers_24 : this.field2) {
         markers_24.method13(number1, number2);
      }
   }

   public void method7(int number1, int number2) {
      this.width = number1;
      this.height = number2;

      for (DriverComponent markers_24 : this.field2) {
         markers_24.method14(number1, number2);
      }
   }

   public void method8(boolean flag) {
      for (DriverComponent markers_23 : this.field2) {
         markers_23.method15(flag);
      }
   }

   public void method9(KeyCode bridgetype_81, int number2, int number3, int number4, int value) {
      for (DriverComponent markers_27 : this.field2) {
         markers_27.method16(bridgetype_81, number2, number3, number4, value);
      }
   }

   public void method10(int number1) {
      for (DriverComponent markers_23 : this.field2) {
         markers_23.method17(number1);
      }
   }

   public void method11(MarkerModel.Data5 data) {
      for (DriverComponent markers_23 : this.field2) {
         markers_23.method18(data);
      }
   }

   public void method12(int number1, int number2, int number3, MarkerModel.Data5 data) {
      for (DriverComponent markers_26 : this.field2) {
         markers_26.method19(number1, number2, number3, data);
      }
   }

   public void method13(double value, double value2) {
      for (DriverComponent markers_26 : this.field2) {
         markers_26.method20(value, value2);
      }
   }

   public void method14(List<Path> list1) {
      for (DriverComponent markers_23 : this.field2) {
         markers_23.method21(list1);
      }
   }

   public void method15(MixinHelper_4 mixinhelper_41, MarkerModel.Data5 data52) {
   }

   public final void method16() {
      for (DriverComponent markers_22 : this.field2) {
         markers_22.method12();
      }

      this.tick();
   }

   public void tick() {
   }

   public final void method17() {
      for (DriverComponent markers_22 : this.field2) {
         markers_22.onClose();
      }
   }

   public void method18(String text, DriverComponent<?> markers_22) {
      System.out.println(text + String.format("C: [%s]: %s", markers_22.getClass().getSimpleName(), markers_22.toString()));

      for (DriverComponent markers_24 : markers_22.getChildren()) {
         this.method18(text + "    ", markers_24);
      }
   }

   private int method19(List<DriverComponent<?>> list1) {
      int number2 = list1.size();

      for (DriverComponent markers_24 : list1) {
         number2 += this.method19(markers_24.getChildren());
      }

      return number2;
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   @Generated
   public List<DriverComponent<?>> method20() {
      return this.field2;
   }
}
