package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;

public interface HudEditorWidget extends Calculator2 {
   float method1();

   float method2();

   boolean method3(MarkerModel.Data2 var1);

   List<EditorShortcut> method5();
}
