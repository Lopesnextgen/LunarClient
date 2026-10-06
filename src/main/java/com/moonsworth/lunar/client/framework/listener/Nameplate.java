package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.guiRewindhandlers.Annotation2;

@FunctionalInterface
public interface Nameplate {
   @Annotation2(method1 = Annotation2.Type.DYNAMICLISTENER_ISENABLED)
   boolean isEnabled();
}
