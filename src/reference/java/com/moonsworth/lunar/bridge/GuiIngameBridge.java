package com.moonsworth.lunar.bridge;

import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.jetbrains.annotations.Nullable;

public interface GuiIngameBridge extends GuiBridge {
   void bridge$renderGameOverlay(float value1);

   boolean bridge$showCrosshair();

   GuiNewChatBridge bridge$getChatGUI();

   void bridge$displayTitle(@Nullable Component component1, @Nullable Component component2, Times times3, float value4, float value5, float value6);

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

   List<PlayerInfoBridge> bridge$getPlayerInfoList();

   GuiPlayerTabOverlayBridge bridge$getTabList();

   boolean bridge$isTabVisible();
}
