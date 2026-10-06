package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.ui.hud.HudElementRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightButton;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@VersionGate(min = 33)
public final class InventoryButtonRegistry implements HudElementRegistry {
   private final Map<HighlightButton, InventoryButtonWidget> field1 = new HashMap<>();
   private int nextId;

   public InventoryButtonRegistry() {
   }

   public Collection<MovableHudElement> method1() {
      return method7().stream().map(this::method6).collect(Collectors.toList());
   }

   public Optional<MovableHudElement> method2(String text1) {
      return this.method5(text1).map(MovableHudElement.class::cast);
   }

   Optional<HighlightButton> method3(String text1) {
      return this.method5(text1).map(InventoryButtonWidget::method20);
   }

   MovableHudElement method4(HighlightButton highlight51) {
      return this.method6(highlight51);
   }

   private Optional<InventoryButtonWidget> method5(String text1) {
      return this.field1.values().stream().filter(arg1x -> arg1x.id().equals(text1)).findFirst();
   }

   private MovableHudElement method6(HighlightButton highlight51) {
      return this.field1.computeIfAbsent(highlight51, arg1x -> new InventoryButtonWidget(arg1x, "inventoryButton-" + this.nextId++));
   }

   static List<HighlightButton> method7() {
      return Ref.method4().method40().method82().method165().method13();
   }
}
