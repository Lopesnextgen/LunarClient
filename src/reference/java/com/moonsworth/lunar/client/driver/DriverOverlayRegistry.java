package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class DriverOverlayRegistry implements DriverElement {
   private static final Set<DriverOverlayRegistry> field1 = new HashSet<>();
   public static final DriverOverlayRegistry field2 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("friends").method10();
   public static final DriverOverlayRegistry field3 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("hostedWorldsConfirmation").method10();
   public static final DriverOverlayRegistry field4 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("lockerSettings").method4(false).method10();
   public static final DriverOverlayRegistry field5 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("sbStorageOverlaySettings").method4(false).method10();
   public static final DriverOverlayRegistry field6 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("accountSettings").method10();
   public static final DriverOverlayRegistry field7 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("accountSwitcher").method10();
   public static final DriverOverlayRegistry field8 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("themeSelector").method10();
   public static final DriverOverlayRegistry field9 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("socialLink").method10();
   public static final DriverOverlayRegistry field10 = method4().RHCRCIIHIHHOCCOHOROORROOHIRIOR("cosmeticPreviewModal").method5(true).method6(false).method9(() -> {
      if (Ref.method4() != null) {
         Ref.method4().method56().method6();
      }
   }).method10();
   private final String field11;
   private boolean active;
   private final boolean field12;
   private final boolean field13;
   private final boolean field14;
   private final boolean field15;
   private final Class<? extends GuiScreenBridge> field16;
   private final Runnable field17;
   private final Runnable field18;

   @Override
   public boolean method2() {
      return this.field13;
   }

   public static Set<DriverOverlayRegistry> method3() {
      return field1;
   }

   public static DriverOverlayRegistry.DriverOverlayRegistrar method4() {
      return new DriverOverlayRegistry.DriverOverlayRegistrar();
   }

   @Generated
   private static boolean method5() {
      return false;
   }

   @Generated
   private static boolean method6() {
      return true;
   }

   @Generated
   private static boolean method7() {
      return true;
   }

   @Generated
   private static boolean method8() {
      return false;
   }

   @Generated
   private static boolean method9() {
      return true;
   }

   @Generated
   private static Runnable method10() {
      return null;
   }

   @Generated
   private static Runnable method11() {
      return null;
   }

   @Generated
   DriverOverlayRegistry(
      String text1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, Class<? extends GuiScreenBridge> clazz7, Runnable runnable8, Runnable runnable9
   ) {
      this.field11 = text1;
      this.active = flag2;
      this.field12 = flag3;
      this.field13 = flag4;
      this.field14 = flag5;
      this.field15 = flag6;
      this.field16 = clazz7;
      this.field17 = runnable8;
      this.field18 = runnable9;
   }

   @Generated
   public String getId() {
      return this.field11;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   @Override
   public boolean method1() {
      return this.field12;
   }

   @Generated
   public boolean method12() {
      return this.field13;
   }

   @Generated
   public boolean method13() {
      return this.field14;
   }

   @Generated
   public boolean method14() {
      return this.field15;
   }

   @Generated
   public Class<? extends GuiScreenBridge> method15() {
      return this.field16;
   }

   @Generated
   public Runnable method16() {
      return this.field17;
   }

   @Generated
   public Runnable method17() {
      return this.field18;
   }

   @Generated
   public void setActive(boolean flag1) {
      this.active = flag1;
   }

   @Generated
   public static class Data {
      @Generated
      private String id;
      @Generated
      private boolean field1;
      @Generated
      private boolean field2;
      @Generated
      private boolean field3;
      @Generated
      private boolean field4;
      @Generated
      private boolean field5;
      @Generated
      private boolean field6;
      @Generated
      private boolean field7;
      @Generated
      private boolean field8;
      @Generated
      private boolean field9;
      @Generated
      private boolean field10;
      @Generated
      private Class<? extends GuiScreenBridge> field11;
      @Generated
      private boolean field12;
      @Generated
      private Runnable field13;
      @Generated
      private boolean field14;
      @Generated
      private Runnable field15;

      @Generated
      Data() {
      }

      @Generated
      public DriverOverlayRegistry.Data method1(String text1) {
         this.id = text1;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method2(boolean flag1) {
         this.field2 = flag1;
         this.field1 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method3(boolean flag1) {
         this.field4 = flag1;
         this.field3 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method4(boolean flag1) {
         this.field6 = flag1;
         this.field5 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method5(boolean flag1) {
         this.field8 = flag1;
         this.field7 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method6(boolean flag1) {
         this.field10 = flag1;
         this.field9 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method7(Class<? extends GuiScreenBridge> clazz1) {
         this.field11 = clazz1;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method8(Runnable runnable1) {
         this.field13 = runnable1;
         this.field12 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry.Data method9(Runnable runnable1) {
         this.field15 = runnable1;
         this.field14 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistry method10() {
         boolean flag1 = this.field2;
         if (!this.field1) {
            flag1 = DriverOverlayRegistry.method5();
         }

         boolean flag2 = this.field4;
         if (!this.field3) {
            flag2 = DriverOverlayRegistry.method6();
         }

         boolean flag3 = this.field6;
         if (!this.field5) {
            flag3 = DriverOverlayRegistry.method7();
         }

         boolean flag4 = this.field8;
         if (!this.field7) {
            flag4 = DriverOverlayRegistry.method8();
         }

         boolean flag5 = this.field10;
         if (!this.field9) {
            flag5 = DriverOverlayRegistry.method9();
         }

         Runnable runnable6 = this.field13;
         if (!this.field12) {
            runnable6 = DriverOverlayRegistry.method10();
         }

         Runnable runnable7 = this.field15;
         if (!this.field14) {
            runnable7 = DriverOverlayRegistry.method11();
         }

         return new DriverOverlayRegistry(this.id, flag1, flag2, flag3, flag4, flag5, this.field11, runnable6, runnable7);
      }

      @Generated
      @Override
      public String toString() {
         return "DriverOverlay.DriverOverlayBuilder0(id="
            + this.id
            + ", active$value="
            + this.field2
            + ", inputFocus$value="
            + this.field4
            + ", restoreOldUi$value="
            + this.field6
            + ", modelInteraction$value="
            + this.field8
            + ", pauseGame$value="
            + this.field10
            + ", replaces="
            + this.field11
            + ", onDisplay$value="
            + this.field13
            + ", onClose$value="
            + this.field15
            + ")";
      }
   }

   public static class DriverOverlayRegistrar extends DriverOverlayRegistry.Data {
      public DriverOverlayRegistrar() {
      }

      @Override
      public DriverOverlayRegistry method10() {
         DriverOverlayRegistry markers2handler1 = super.method10();
         DriverOverlayRegistry.field1.add(markers2handler1);
         return markers2handler1;
      }
   }
}
