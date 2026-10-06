package com.moonsworth.lunar.bridge;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.Generated;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.ComponentIteratorFlag;
import net.kyori.adventure.text.ComponentIteratorType;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.PatternReplacementResult;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.ShadowColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.Style.Builder;
import net.kyori.adventure.text.format.Style.Merge;
import net.kyori.adventure.text.format.Style.Merge.Strategy;
import net.kyori.adventure.text.format.TextDecoration.State;
import net.kyori.adventure.util.ARGBLike;
import net.kyori.adventure.util.IntFunction2;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;

public class Bridge2Handler_2 implements Bridge2_13, Component {
   private Object field1;
   private int width = -1;
   private final Component field2;

   public Bridge2Handler_2(Component var1) {
      this.field2 = var1;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int var1) {
      this.width = var1;
   }

   public Object method1() {
      return this.field1;
   }

   public void method2(Object var1) {
      this.field1 = var1;
   }

   @Override
   public Bridge2_42 moonBridge$asBridgeComponent() {
      return ((Bridge2_13)this.field2).moonBridge$asBridgeComponent();
   }

   @Generated
   public List<Component> children() {
      return this.field2.children();
   }

   @Generated
   public Component children(List<? extends ComponentLike> var1) {
      return this.field2.children(var1);
   }

   @Generated
   public boolean contains(Component var1) {
      return this.field2.contains(var1);
   }

   @Generated
   public boolean contains(Component var1, BiPredicate<? super Component, ? super Component> var2) {
      return this.field2.contains(var1, var2);
   }

   @Deprecated
   @Generated
   public void detectCycle(Component var1) {
      this.field2.detectCycle(var1);
   }

   @Generated
   public Component append(Component var1) {
      return this.field2.append(var1);
   }

   @Generated
   public Component append(ComponentLike var1) {
      return this.field2.append(var1);
   }

   @Generated
   public Component append(ComponentBuilder<?, ?> var1) {
      return this.field2.append(var1);
   }

   @Generated
   public Component appendNewline() {
      return this.field2.appendNewline();
   }

   @Generated
   public Component appendSpace() {
      return this.field2.appendSpace();
   }

   @Generated
   public Component append(ComponentLike... var1) {
      return this.field2.append(var1);
   }

   @Generated
   public Component append(List<? extends ComponentLike> var1) {
      return this.field2.append(var1);
   }

   @Generated
   public Component applyFallbackStyle(Style var1) {
      return this.field2.applyFallbackStyle(var1);
   }

   @Generated
   public Component applyFallbackStyle(StyleBuilderApplicable... var1) {
      return this.field2.applyFallbackStyle(var1);
   }

   @Generated
   public Style style() {
      return this.field2.style();
   }

   @Generated
   public Component style(Style var1) {
      return this.field2.style(var1);
   }

   @Generated
   public Component style(Consumer<Builder> var1) {
      return this.field2.style(var1);
   }

   @Generated
   public Component style(Consumer<Builder> var1, Strategy var2) {
      return this.field2.style(var1, var2);
   }

   @Generated
   public Component style(Builder var1) {
      return this.field2.style(var1);
   }

   @Generated
   public Component mergeStyle(Component var1) {
      return this.field2.mergeStyle(var1);
   }

   @Generated
   public Component mergeStyle(Component var1, Merge... var2) {
      return this.field2.mergeStyle(var1, var2);
   }

   @Generated
   public Component mergeStyle(Component var1, Set<Merge> var2) {
      return this.field2.mergeStyle(var1, var2);
   }

   @Generated
   public Key font() {
      return this.field2.font();
   }

   @Generated
   public Component font(Key var1) {
      return this.field2.font(var1);
   }

   @Generated
   public TextColor color() {
      return this.field2.color();
   }

   @Generated
   public ShadowColor shadowColor() {
      return this.field2.shadowColor();
   }

   @Generated
   public Component color(TextColor var1) {
      return this.field2.color(var1);
   }

   @Generated
   public Component colorIfAbsent(TextColor var1) {
      return this.field2.colorIfAbsent(var1);
   }

   @Generated
   public Component shadowColor(ARGBLike var1) {
      return this.field2.shadowColor(var1);
   }

   @Generated
   public Component shadowColorIfAbsent(ARGBLike var1) {
      return this.field2.shadowColorIfAbsent(var1);
   }

   @Generated
   public boolean hasDecoration(TextDecoration var1) {
      return this.field2.hasDecoration(var1);
   }

   @Generated
   public Component decorate(TextDecoration var1) {
      return this.field2.decorate(var1);
   }

   @Generated
   public State decoration(TextDecoration var1) {
      return this.field2.decoration(var1);
   }

   @Generated
   public Component decoration(TextDecoration var1, boolean var2) {
      return this.field2.decoration(var1, var2);
   }

   @Generated
   public Component decoration(TextDecoration var1, State var2) {
      return this.field2.decoration(var1, var2);
   }

   @Generated
   public Component decorationIfAbsent(TextDecoration var1, State var2) {
      return this.field2.decorationIfAbsent(var1, var2);
   }

   @Generated
   public Map<TextDecoration, State> decorations() {
      return this.field2.decorations();
   }

   @Generated
   public Component decorations(Map<TextDecoration, State> var1) {
      return this.field2.decorations(var1);
   }

   @Generated
   public ClickEvent clickEvent() {
      return this.field2.clickEvent();
   }

   @Generated
   public Component clickEvent(ClickEvent var1) {
      return this.field2.clickEvent(var1);
   }

   @Generated
   public HoverEvent<?> hoverEvent() {
      return this.field2.hoverEvent();
   }

   @Generated
   public Component hoverEvent(HoverEventSource<?> var1) {
      return this.field2.hoverEvent(var1);
   }

   @Generated
   public String insertion() {
      return this.field2.insertion();
   }

   @Generated
   public Component insertion(String var1) {
      return this.field2.insertion(var1);
   }

   @Generated
   public boolean hasStyling() {
      return this.field2.hasStyling();
   }

   @Generated
   public Component replaceText(Consumer<net.kyori.adventure.text.TextReplacementConfig.Builder> var1) {
      return this.field2.replaceText(var1);
   }

   @Generated
   public Component replaceText(TextReplacementConfig var1) {
      return this.field2.replaceText(var1);
   }

   @Generated
   public Component compact() {
      return this.field2.compact();
   }

   @Generated
   public Component compact(Style var1) {
      return this.field2.compact(var1);
   }

   @Generated
   public Iterable<Component> iterable(ComponentIteratorType var1, ComponentIteratorFlag... var2) {
      return this.field2.iterable(var1, var2);
   }

   @Generated
   public Iterable<Component> iterable(ComponentIteratorType var1, Set<ComponentIteratorFlag> var2) {
      return this.field2.iterable(var1, var2);
   }

   @Generated
   public Iterator<Component> iterator(ComponentIteratorType var1, ComponentIteratorFlag... var2) {
      return this.field2.iterator(var1, var2);
   }

   @Generated
   public Iterator<Component> iterator(ComponentIteratorType var1, Set<ComponentIteratorFlag> var2) {
      return this.field2.iterator(var1, var2);
   }

   @Generated
   public Spliterator<Component> spliterator(ComponentIteratorType var1, ComponentIteratorFlag... var2) {
      return this.field2.spliterator(var1, var2);
   }

   @Generated
   public Spliterator<Component> spliterator(ComponentIteratorType var1, Set<ComponentIteratorFlag> var2) {
      return this.field2.spliterator(var1, var2);
   }

   @Deprecated
   @Generated
   public Component replaceText(String var1, ComponentLike var2) {
      return this.field2.replaceText(var1, var2);
   }

   @Deprecated
   @Generated
   public Component replaceText(Pattern var1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> var2) {
      return this.field2.replaceText(var1, var2);
   }

   @Deprecated
   @Generated
   public Component replaceFirstText(String var1, ComponentLike var2) {
      return this.field2.replaceFirstText(var1, var2);
   }

   @Deprecated
   @Generated
   public Component replaceFirstText(Pattern var1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> var2) {
      return this.field2.replaceFirstText(var1, var2);
   }

   @Deprecated
   @Generated
   public Component replaceText(String var1, ComponentLike var2, int var3) {
      return this.field2.replaceText(var1, var2, var3);
   }

   @Deprecated
   @Generated
   public Component replaceText(Pattern var1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> var2, int var3) {
      return this.field2.replaceText(var1, var2, var3);
   }

   @Deprecated
   @Generated
   public Component replaceText(String var1, ComponentLike var2, IntFunction2<PatternReplacementResult> var3) {
      return this.field2.replaceText(var1, var2, var3);
   }

   @Deprecated
   @Generated
   public Component replaceText(
      Pattern var1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> var2, IntFunction2<PatternReplacementResult> var3
   ) {
      return this.field2.replaceText(var1, var2, var3);
   }

   @Generated
   public void componentBuilderApply(ComponentBuilder<?, ?> var1) {
      this.field2.componentBuilderApply(var1);
   }

   @Generated
   public Component asComponent() {
      return this.field2.asComponent();
   }

   @Generated
   public HoverEvent<Component> asHoverEvent(UnaryOperator<Component> var1) {
      return this.field2.asHoverEvent(var1);
   }

   @Generated
   public Stream<? extends ExaminableProperty> examinableProperties() {
      return this.field2.examinableProperties();
   }

   @Generated
   public String examinableName() {
      return this.field2.examinableName();
   }

   @Generated
   public <R> R examine(Examiner<R> var1) {
      return (R)this.field2.examine(var1);
   }

   @Generated
   public HoverEvent<Component> asHoverEvent() {
      return this.field2.asHoverEvent();
   }

   @Generated
   public Component decorate(TextDecoration... var1) {
      return (Component)this.field2.decorate(var1);
   }

   @Generated
   public Component decorations(Set<TextDecoration> var1, boolean var2) {
      return (Component)this.field2.decorations(var1, var2);
   }
}
