package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Map;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;

public interface GuiRenderState {
   void push();

   void method1(float var1, float var2);

   void pop();

   void method2(CachedFontImpl var1, Map<ResourceLocationBridge, List<CachedFontImpl.Data2>> var2, int var3);

   void method3(List<CachedFontImpl.Data> var1);
}
