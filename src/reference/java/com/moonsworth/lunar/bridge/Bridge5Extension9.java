package com.moonsworth.lunar.bridge;

import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.jetbrains.annotations.Nullable;

public interface Bridge5Extension9 extends Bridge5_15 {
   void bridge$renderGameOverlay(float var1);

   boolean bridge$showCrosshair();

   Bridge5Extension4 bridge$getChatGUI();

   void bridge$displayTitle(@Nullable Component var1, @Nullable Component var2, Times var3, float var4, float var5, float var6);

   void bridge$clearTitle();

   float bridge$getTitleScale();

   Title bridge$getTitle();

   int bridge$titlesTimer();

   @Nullable
   Component bridge$getOverlayMessage();

   int bridge$getOverlayMessageTime();

   boolean bridge$isAnimateOverlayMessageColor();

   void bridge$interpolateTitle();

   int bridge$getTicks();

   List<Bridge2_33> bridge$getPlayerInfoList();

   Bridge5Extension bridge$getTabList();

   boolean bridge$isTabVisible();
}
