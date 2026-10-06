package com.moonsworth.lunar.client.driver.core;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.ComponentStyleDataLegacy;
import com.moonsworth.lunar.client.driver.DriverComponentLegacy;
import com.moonsworth.webosr.input.Modifiers;
import com.moonsworth.webosr.input.Mouse.Action;
import com.moonsworth.webosr.input.Mouse.Button;
import com.moonsworth.webosr.wrappers.Browser;
import java.util.OptionalInt;
import java.util.Set;
import lombok.Generated;
import org.apache.commons.lang3.mutable.MutableDouble;

public class MigrationContextLegacy extends DriverComponentLegacy<MigrationContextLegacy> {
   private static final Set<Byte> field25 = ImmutableSet.of((byte)0, (byte)15);
   protected Browser field26;
   protected MarkerIconRendererLegacy field27;
   private OptionalInt field28;
   private com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 field29;
   protected DriverViewportLegacy field30;
   private boolean field31;

   public void method1(boolean var1) {
      this.field31 = var1;
   }

   public void method2() {
      this.method1(true);
   }

   public boolean hasFocus() {
      return this.field31;
   }

   private int method3(int var1) {
      int var2 = this.method5(var1);
      if ((var1 & 8) != 0) {
         var2 |= Modifiers.SUPER.getMask();
      }

      if ((var1 & 16) != 0) {
         var2 |= Modifiers.CAPS_LOCK.getMask();
      }

      if ((var1 & 32) != 0) {
         var2 |= Modifiers.NUM_LOCK.getMask();
      }

      return var2;
   }

   private int method4(int var1) {
      int var2 = this.method5(var1);
      if (this.field28.isPresent()) {
         int var3 = this.field28.getAsInt();
         if (var3 == Button.LEFT.getValue()) {
            var2 |= Modifiers.LEFT_MOUSE_BUTTON.getMask();
         }

         if (var3 == Button.RIGHT.getValue()) {
            var2 |= Modifiers.RIGHT_MOUSE_BUTTON.getMask();
         }

         if (var3 == Button.MIDDLE.getValue()) {
            var2 |= Modifiers.MIDDLE_MOUSE_BUTTON.getMask();
         }
      }

      return var2;
   }

   private int method5(int var1) {
      int var2 = 0;
      if ((var1 & 1) != 0) {
         var2 |= Modifiers.SHIFT.getMask();
      }

      if ((var1 & 2) != 0) {
         var2 |= Modifiers.CONTROL.getMask();
      }

      if ((var1 & 4) != 0) {
         var2 |= Modifiers.ALT.getMask();
      }

      return var2;
   }

   public void method6() {
      if (this.field28.isPresent()) {
         this.method14(this.field28.getAsInt(), 0, 0, this.field29);
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var2) {
      this.field27.method1(var1, this.x, this.y, var2);
      super.method3(var1, var2);
   }

   @Override
   public <K extends DriverComponentLegacy<K>> void method4(int var1, int var2) {
      this.field27 = new MarkerIconRendererLegacy(this);
      super.method4(var1, var2);
   }

   public static MigrationContextLegacy.Data2<?, ?> method9(com.moonsworth.lunar.client.driver.DriverViewportLegacy var0) {
      MutableDouble var1 = new MutableDouble();
      MutableDouble var2 = new MutableDouble();
      return (MigrationContextLegacy.Data2<?, ?>)((MigrationContextLegacy.Data)((MigrationContextLegacy.Data)((MigrationContextLegacy.Data)((MigrationContextLegacy.Data)((MigrationContextLegacy.Data)((MigrationContextLegacy.Data)((MigrationContextLegacy.Data)method14(
                              new MigrationContextLegacy.Data(), var0, null
                           ))
                           .method3(OptionalInt.empty()))
                        .method6(true))
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var0x, var1x) -> {
                        if (var0x.field26 != null) {
                           var0x.field29 = var1x;
                           int var2x = var0x.method4(0);
                           Button var3 = Button.NONE;
                           if (var0x.field28.isPresent()) {
                              if ((var0x.field28.getAsInt() & Modifiers.LEFT_MOUSE_BUTTON.getMask()) != 0) {
                                 var3 = Button.LEFT;
                              } else if ((var0x.field28.getAsInt() & Modifiers.RIGHT_MOUSE_BUTTON.getMask()) != 0) {
                                 var3 = Button.RIGHT;
                              } else if ((var0x.field28.getAsInt() & Modifiers.MIDDLE_MOUSE_BUTTON.getMask()) != 0) {
                                 var3 = Button.MIDDLE;
                              }
                           }

                           var1x = (com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5)var1x.HICCRORORCRIHCORCCIORIOROORIHR(var0x.getX(), var0x.getY());
                           var0x.field26.triggerMouseEvent(var3, Action.MOVED, var1x.xi(), var1x.RROCOHICOORRHCIHHHCHRCICHIIHCO(), var2x);
                        }
                     }))
                  .HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     (var2x, var3, var5) -> {
                        if (!LcuiScreen.isCtrlKeyDown()) {
                           if (var2x.field26 != null) {
                              int var7 = Client.method109().method41().method6().method78().get();
                              int var8 = var2x.method4(0);
                              com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var9 = (com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5)var2x.field29
                                 .HICCRORORCRIHCORCCIORIOROORIHR(var2x.getX(), var2x.getY());
                              var1.add(var3 * var7);
                              var2.add(var5 * var7);
                              int var10 = var1.getValue().intValue();
                              int var11 = var2.getValue().intValue();
                              var1.subtract(var10);
                              var2.subtract(var11);
                              if (var10 != 0 || var11 != 0) {
                                 var2x.field26.triggerScrollEvent(var10, var11, var9.xi(), var9.RROCOHICOORRHCIHHHCHRCICHIIHCO(), var8);
                                 var2x.field26.triggerMouseEvent(Button.NONE, Action.MOVED, var9.xi(), var9.RROCOHICOORRHCIHHHCHRCICHIIHCO(), var8);
                              }
                           }
                        }
                     }
                  ))
               .HORHROIOIOICIRHIOCOICHHHIHCIIO((var0x, var1x, var2x, var3, var4) -> {
                  if (var0x.field26 != null) {
                     Action var5 = var2x == 0 ? Action.UP : Action.DOWN;

                     Button var6 = switch (var1x) {
                        case 0 -> Button.LEFT;
                        case 1 -> Button.RIGHT;
                        case 2 -> Button.MIDDLE;
                        default -> null;
                     };
                     if (var6 != null) {
                        if (var5 == Action.DOWN) {
                           var0x.field28 = OptionalInt.of(var1x);
                        } else {
                           var0x.field28 = OptionalInt.empty();
                        }

                        int var7 = var0x.method4(var3);
                        var4 = (com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5)var4.HICCRORORCRIHCORCCIORIOROORIHR(var0x.getX(), var0x.getY());
                        var0x.field26.triggerMouseEvent(var6, var5, var4.xi(), var4.RROCOHICOORRHCIHHHCHRCICHIIHCO(), var7);
                     }
                  }
               }))
            .HORHROIOIOICIRHIOCOICHHHIHCIIO((var0x, var1x, var2x, var3, var4, var5) -> {
               if (var0x.field26 != null) {
                  if (var1x != KeyCode.KEY_NONE) {
                     com.moonsworth.webosr.input.Keyboard.Action var6 = switch (var4) {
                        case 0 -> com.moonsworth.webosr.input.Keyboard.Action.UP;
                        case 1, 2 -> com.moonsworth.webosr.input.Keyboard.Action.RAW_DOWN;
                        default -> null;
                     };
                     var0x.field26.triggerKeyEvent(null, var3, null, var1x.getVk(), var6, var0x.method3(var5));
                     if (var6 == com.moonsworth.webosr.input.Keyboard.Action.RAW_DOWN && (var1x == KeyCode.KEY_RETURN || var1x == KeyCode.KEY_TAB)) {
                        int var7 = var1x == KeyCode.KEY_RETURN ? 13 : 9;
                        var0x.field26.triggerKeyEvent(Character.toString((char)var7), 0, Character.toString((char)var7), 0, var6, 0);
                     }
                  }
               }
            }))
         .HORHROIOIOICIRHIOCOICHHHIHCIIO((var0x, var1x) -> {
            if (var0x.field26 != null) {
               com.moonsworth.webosr.input.Keyboard.Action var2x = com.moonsworth.webosr.input.Keyboard.Action.CHAR;
               char[] var3 = Character.toChars(var1x);
               if (Bridge.getMinecraftVersion().method21()) {
                  int var4 = Character.getType(var3[0]);
                  if (field25.contains((byte)var4)) {
                     return;
                  }
               }

               var0x.field26.triggerKeyEvent(Character.toString(var3[0]), var1x, Character.toString(var3[0]), var1x, var2x, 0);
            }
         });
   }

   @Override
   public void method1(int var1, int var2) {
      super.method1(var1, var2);
   }

   public Browser method13() {
      return this.field26;
   }

   public void method12(Browser var1) {
      this.field26 = var1;
   }

   @Generated
   private static com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 method14() {
      return new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5(0.0, 0.0);
   }

   @Generated
   protected MigrationContextLegacy(MigrationContextLegacy.Data2<?, ?> var1) {
      super(var1);
      this.field26 = var1.field23;
      this.field27 = var1.field24;
      this.field28 = var1.field25;
      if (var1.field26) {
         this.field29 = var1.field27;
      } else {
         this.field29 = method14();
      }

      this.field30 = var1.field28;
      this.field31 = var1.field29;
   }

   @Generated
   @Override
   public String toString() {
      return "LBrowser()";
   }

   @Generated
   private static final class Data extends MigrationContextLegacy.Data2<MigrationContextLegacy, MigrationContextLegacy.Data> {
      @Generated
      protected MigrationContextLegacy.Data method1() {
         return this;
      }

      @Generated
      @Override
      public MigrationContextLegacy method8() {
         return new MigrationContextLegacy(this);
      }
   }

   @Generated
   public abstract static class Data2<C extends MigrationContextLegacy, B extends MigrationContextLegacy.Data2<C, B>> extends ComponentStyleDataLegacy<MigrationContextLegacy, C, B> {
      @Generated
      private Browser field23;
      @Generated
      private MarkerIconRendererLegacy field24;
      @Generated
      private OptionalInt field25;
      @Generated
      private boolean field26;
      @Generated
      private com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 field27;
      @Generated
      private DriverViewportLegacy field28;
      @Generated
      private boolean field29;

      @Generated
      public B method1(Browser var1) {
         this.field23 = var1;
         return this.method7();
      }

      @Generated
      public B method2(MarkerIconRendererLegacy var1) {
         this.field24 = var1;
         return this.method7();
      }

      @Generated
      public B method3(OptionalInt var1) {
         this.field25 = var1;
         return this.method7();
      }

      @Generated
      public B method4(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var1) {
         this.field27 = var1;
         this.field26 = true;
         return this.method7();
      }

      @Generated
      public B method5(DriverViewportLegacy var1) {
         this.field28 = var1;
         return this.method7();
      }

      @Generated
      public B method6(boolean var1) {
         this.field29 = var1;
         return this.method7();
      }

      @Generated
      protected abstract B method7();

      @Generated
      public abstract C method8();

      @Generated
      @Override
      public String toString() {
         return "LBrowser.LBrowserBuilder(super="
            + super.toString()
            + ", browser="
            + this.field23
            + ", painter="
            + this.field24
            + ", optMouseButtonHeld="
            + this.field25
            + ", mouse$value="
            + this.field27
            + ", gameUI="
            + this.field28
            + ", hasFocus="
            + this.field29
            + ")";
      }
   }
}
