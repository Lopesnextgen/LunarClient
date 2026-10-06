package com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.gui.EventLocationChange;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import javax.annotation.Nullable;
import lombok.Generated;

public class HighlightTypeListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final ScreenTitleListener field7 = (ScreenTitleListener)this.method3(ScreenTitleListener.class);
   @Nullable
   private SkyblockMenuType field8 = null;

   public HighlightTypeListener() {
      this.method12(EventScreenChange.class, this::method1, 190);
      this.handle(EventLocationChange.class, this::method3);
   }

   private void method1(EventScreenChange highlightimpl71) {
      if (highlightimpl71.method1() != null) {
         this.method5();
      } else {
         this.field8 = null;
      }
   }

   protected void onEnable() {
      this.method5();
   }

   private void method5() {
      if (IslandUtils.isOnIsland()) {
         String text1 = this.field7.method5();
         if (text1 != null && !text1.isEmpty()) {
            for (SkyblockMenuType highlighttype5 : SkyblockMenuType.values()) {
               Matcher matcher6 = highlighttype5.getPattern().matcher(text1);
               if (matcher6.find()) {
                  this.field8 = highlighttype5;
                  return;
               }
            }
         }
      }
   }

   private void method3(EventLocationChange highlightimpl201) {
      if (this.field8 == null) {
         HypixelLocation rewindhandlers22 = highlightimpl201.method2();
         if (rewindhandlers22 != null) {
            if (highlightimpl201.method2().field2.equals("SKYBLOCK")) {
               String text3 = this.field7.method5();
               if (text3 == null || text3.isEmpty()) {
                  return;
               }

               for (SkyblockMenuType highlighttype7 : SkyblockMenuType.values()) {
                  Matcher matcher8 = highlighttype7.getPattern().matcher(text3);
                  if (matcher8.find()) {
                     LunarEventBus.method29().method12(EventScreenInitPost.class, () -> new EventScreenInitPost(Ref.method3().bridge$getCurrentScreen()));
                  }
               }
            }
         }
      }
   }

   @Generated
   public ScreenTitleListener method6() {
      return this.field7;
   }

   @Nullable
   @Generated
   public SkyblockMenuType method7() {
      return this.field8;
   }
}
