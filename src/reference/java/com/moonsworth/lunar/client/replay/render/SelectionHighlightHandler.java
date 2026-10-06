package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.EntityHitResult.Data;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.ui.CursorManager;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.ScreenProjection;
import com.moonsworth.lunar.client.ui.CursorManager.Type;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder;
import com.moonsworth.lunar.client.util.raytrace.EntityRaycastContext;
import lombok.Generated;
import org.joml.Vector3d;

public class SelectionHighlightHandler extends RewindHandler {
   private BridgeExtension field9 = null;
   private boolean field10 = false;

   public SelectionHighlightHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
   }

   public int getHighlightColor() {
      int number1 = (int)(Math.abs(Math.sin(System.currentTimeMillis() % 4000L / 4000.0 * Math.PI * 2.0)) * 50.0);
      return ColorUtils.method10(Math.min(255, 26 + number1), Math.min(255, 189 + number1), Math.min(255, 226 + number1), 255);
   }

   public boolean method1(int number1) {
      if (this.field9 != null && number1 == 2) {
         this.field10 = !this.field10;
         RewindEditorContext.refreshEntityContextMenu();
         return true;
      } else if (this.field10) {
         this.field10 = false;
         RewindEditorContext.refreshEntityContextMenu();
         return true;
      } else {
         return false;
      }
   }

   public void method2(ExportTargetHandler rewindhandlers3impl81, ReplayDriverHandler rewindhandlers3handler2, float value3) {
      RewindHandlers rewindhandlers4 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      ReplayTimeline highlight_35 = rewindhandlers4.method40().method37();
      if (highlight_35 == null || rewindhandlers4.method57().method25() || rewindhandlers4.method40().method31() || rewindhandlers4.method62() || rewindhandlers3impl81.method26() == rewindhandlers4.method58()) {
         this.field9 = null;
         if (this.field10) {
            this.field10 = false;
            RewindEditorContext.refreshEntityContextMenu();
         }
      } else if (!this.field10) {
         double value6 = rewindhandlers3handler2.method15();
         double value8 = rewindhandlers3handler2.method16();
         double value10 = highlight_35.method13().getWidth();
         double value12 = highlight_35.method13().getHeight();
         if (!rewindhandlers4.method44()) {
            value6 = Bridge.method20().getX();
            if (!Bridge.getMinecraftVersion().method19()) {
               value8 = rewindhandlers3impl81.method27() - Bridge.method20().getY();
            } else {
               value8 = Bridge.method20().getY();
            }

            value10 = rewindhandlers4.method58();
            value12 = rewindhandlers4.method59();
         }

         if (!(value6 < 0.0) && !(value8 < 0.0) && !(value6 > value10) && !(value8 > value12) && CursorManager.method4() != Type.HAND) {
            WorldBridgeExtension itemcounter6extension14 = Ref.method8();
            if (itemcounter6extension14 != null) {
               RayBuilder data215 = Ray.method9(Raycaster.field9).method1(new EntityRaycastContext(false, 0.0F, value3)).method15(arg0 -> true);
               double value16 = 0.0;
               BridgeExtension bridgeextension18 = Ref.method3().bridge$getRenderViewEntity();
               if (Ref.MC_VERSION == 0 && bridgeextension18 != null && bridgeextension18 != Ref.method7()) {
                  value16 = 1.8 - bridgeextension18.bridge$getEyeHeight();
               }

               Vector3d vector3d19 = ScreenProjection.screenToRay(
                  value6 / value10 * ScreenProjection.getScaledWidth(), value8 / value12 * ScreenProjection.getScaledHeight()
               );
               Vector3d vector3d20 = ScreenProjection.getCameraPosition();
               double value21 = 0.5;
               double value23 = 50.0;
               data215.method2(
                  Vec3Bridge.method2(vector3d20.x() + vector3d19.x() * value21, vector3d20.y() + vector3d19.y() * value21 + value16, vector3d20.z() + vector3d19.z() * value21),
                  Vec3Bridge.method2(vector3d20.x() + vector3d19.x() * value23, vector3d20.y() + vector3d19.y() * value23 + value16, vector3d20.z() + vector3d19.z() * value23)
               );
               Ray sextension25 = data215.method18();
               if (sextension25.method8(itemcounter6extension14) instanceof Data data27) {
                  this.field9 = data27.method5();
               } else {
                  this.field9 = null;
               }
            } else {
               this.field9 = null;
            }
         } else {
            this.field9 = null;
         }
      }
   }

   @Generated
   public BridgeExtension method14() {
      return this.field9;
   }

   @Generated
   public boolean method15() {
      return this.field10;
   }

   @Generated
   public void method5(boolean flag1) {
      this.field10 = flag1;
   }
}
