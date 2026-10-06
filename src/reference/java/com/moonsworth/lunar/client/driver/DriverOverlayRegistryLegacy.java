package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class DriverOverlayRegistryLegacy implements DriverElementLegacy {
   private static final Set<DriverOverlayRegistryLegacy> field1 = new HashSet<>();
   public static final DriverOverlayRegistryLegacy field2 = method4().method1("friends").method10();
   public static final DriverOverlayRegistryLegacy field3 = method4().method1("hostedWorldsConfirmation").method10();
   public static final DriverOverlayRegistryLegacy field4 = method4().method1("lockerSettings").method4(false).method10();
   public static final DriverOverlayRegistryLegacy field5 = method4().method1("sbStorageOverlaySettings").method4(false).method10();
   public static final DriverOverlayRegistryLegacy field6 = method4().method1("accountSettings").method10();
   public static final DriverOverlayRegistryLegacy field7 = method4().method1("accountSwitcher").method10();
   public static final DriverOverlayRegistryLegacy field8 = method4().method1("themeSelector").method10();
   public static final DriverOverlayRegistryLegacy field9 = method4().method1("socialLink").method10();
   public static final DriverOverlayRegistryLegacy field10 = method4().method1("cosmeticPreviewModal").method5(true).method6(false).method9(() -> {
      if (ThreadModuleDump63.method4() != null) {
         ThreadModuleDump63.method4().method56().method6();
      }
   }).method10();
   private final String field11;
   private boolean active;
   private final boolean field12;
   private final boolean field13;
   private final boolean field14;
   private final boolean field15;
   private final Class<? extends Bridge5Extension6> field16;
   private final Runnable field17;
   private final Runnable field18;

   @Override
   public boolean method2() {
      return this.field13;
   }

   public static Set<DriverOverlayRegistryLegacy> method3() {
      return field1;
   }

   public static DriverOverlayRegistryLegacy.Data2 method4() {
      return new DriverOverlayRegistryLegacy.Data2();
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
   DriverOverlayRegistryLegacy(
      String var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, Class<? extends Bridge5Extension6> var7, Runnable runnable, Runnable runnable2
   ) {
      this.field11 = var1;
      this.active = var2;
      this.field12 = var3;
      this.field13 = var4;
      this.field14 = var5;
      this.field15 = var6;
      this.field16 = var7;
      this.field17 = runnable;
      this.field18 = runnable2;
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
   public Class<? extends Bridge5Extension6> method15() {
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
   public void setActive(boolean var1) {
      this.active = var1;
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
      private Class<? extends Bridge5Extension6> field11;
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
      public DriverOverlayRegistryLegacy.Data method1(String var1) {
         this.id = var1;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method2(boolean var1) {
         this.field2 = var1;
         this.field1 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method3(boolean var1) {
         this.field4 = var1;
         this.field3 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method4(boolean var1) {
         this.field6 = var1;
         this.field5 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method5(boolean var1) {
         this.field8 = var1;
         this.field7 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method6(boolean var1) {
         this.field10 = var1;
         this.field9 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method7(Class<? extends Bridge5Extension6> var1) {
         this.field11 = var1;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method8(Runnable var1) {
         this.field13 = var1;
         this.field12 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy.Data method9(Runnable var1) {
         this.field15 = var1;
         this.field14 = true;
         return this;
      }

      @Generated
      public DriverOverlayRegistryLegacy method10() {
         boolean var1 = this.field2;
         if (!this.field1) {
            var1 = DriverOverlayRegistryLegacy.method5();
         }

         boolean var2 = this.field4;
         if (!this.field3) {
            var2 = DriverOverlayRegistryLegacy.method6();
         }

         boolean var3 = this.field6;
         if (!this.field5) {
            var3 = DriverOverlayRegistryLegacy.method7();
         }

         boolean var4 = this.field8;
         if (!this.field7) {
            var4 = DriverOverlayRegistryLegacy.method8();
         }

         boolean var5 = this.field10;
         if (!this.field9) {
            var5 = DriverOverlayRegistryLegacy.method9();
         }

         Runnable var6 = this.field13;
         if (!this.field12) {
            var6 = DriverOverlayRegistryLegacy.method10();
         }

         Runnable var7 = this.field15;
         if (!this.field14) {
            var7 = DriverOverlayRegistryLegacy.method11();
         }

         return new DriverOverlayRegistryLegacy(this.id, var1, var2, var3, var4, var5, this.field11, var6, var7);
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

   public static class Data2 extends DriverOverlayRegistryLegacy.Data {
      @Override
      public DriverOverlayRegistryLegacy method10() {
         DriverOverlayRegistryLegacy var1 = super.method10();
         DriverOverlayRegistryLegacy.field1.add(var1);
         return var1;
      }
   }
}
