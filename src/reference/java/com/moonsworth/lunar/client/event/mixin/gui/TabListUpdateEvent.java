package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;

@Annotation3(GuiRewindhandlersHandler24.class)
public class TabListUpdateEvent extends Highlight implements Nameplate2 {
}
