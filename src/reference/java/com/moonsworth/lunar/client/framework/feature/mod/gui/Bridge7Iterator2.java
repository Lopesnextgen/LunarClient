package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.ui.hud.HudElementRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.Highlight5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Annotation2(min = 33)
public final class Bridge7Iterator2 implements HudElementRegistry {
   private final Map<Highlight5, Bridge7Iterator222> field1 = new HashMap<>();
   private int nextId;

   public Collection<MovableHudElement> method1() {
      return method7().stream().map(this::method6).collect(Collectors.toList());
   }

   public Optional<MovableHudElement> method2(String var1) {
      return this.method5(var1).map(MovableHudElement.class::cast);
   }

   Optional<Highlight5> method3(String var1) {
      return this.method5(var1).map(Bridge7Iterator222::method20);
   }

   MovableHudElement method4(Highlight5 var1) {
      return this.method6(var1);
   }

   private Optional<Bridge7Iterator222> method5(String var1) {
      return this.field1.values().stream().filter(var1x -> var1x.id().equals(var1)).findFirst();
   }

   private MovableHudElement method6(Highlight5 var1) {
      return this.field1.computeIfAbsent(var1, var1x -> new Bridge7Iterator222(var1x, "inventoryButton-" + this.nextId++));
   }

   static List<Highlight5> method7() {
      return ThreadModuleDump63.method4().method40().method82().method165().method13();
   }
}
