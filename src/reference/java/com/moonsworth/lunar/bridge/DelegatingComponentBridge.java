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

public class DelegatingComponentBridge implements AdventureComponentBridge, Component {
   private Object field1;
   private int width = -1;
   private final Component field2;

   public DelegatingComponentBridge(Component component1) {
      this.field2 = component1;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int value) {
      this.width = value;
   }

   public Object method1() {
      return this.field1;
   }

   public void method2(Object object) {
      this.field1 = object;
   }

   public Bridge2_42 moonBridge$asBridgeComponent() {
      return ((AdventureComponentBridge)this.field2).moonBridge$asBridgeComponent();
   }

   @Generated
   public List<Component> children() {
      return this.field2.children();
   }

   @Generated
   public Component children(List<? extends ComponentLike> list1) {
      return this.field2.children(list1);
   }

   @Generated
   public boolean contains(Component component1) {
      return this.field2.contains(component1);
   }

   @Generated
   public boolean contains(Component component1, BiPredicate<? super Component, ? super Component> bipredicate2) {
      return this.field2.contains(component1, bipredicate2);
   }

   @Deprecated
   @Generated
   public void detectCycle(Component component1) {
      this.field2.detectCycle(component1);
   }

   @Generated
   public Component append(Component component1) {
      return this.field2.append(component1);
   }

   @Generated
   public Component append(ComponentLike componentlike1) {
      return this.field2.append(componentlike1);
   }

   @Generated
   public Component append(ComponentBuilder<?, ?> componentbuilder1) {
      return this.field2.append(componentbuilder1);
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
   public Component append(ComponentLike... items1) {
      return this.field2.append(items1);
   }

   @Generated
   public Component append(List<? extends ComponentLike> list1) {
      return this.field2.append(list1);
   }

   @Generated
   public Component applyFallbackStyle(Style style1) {
      return this.field2.applyFallbackStyle(style1);
   }

   @Generated
   public Component applyFallbackStyle(StyleBuilderApplicable... items1) {
      return this.field2.applyFallbackStyle(items1);
   }

   @Generated
   public Style style() {
      return this.field2.style();
   }

   @Generated
   public Component style(Style style1) {
      return this.field2.style(style1);
   }

   @Generated
   public Component style(Consumer<Builder> consumer1) {
      return this.field2.style(consumer1);
   }

   @Generated
   public Component style(Consumer<Builder> consumer1, Strategy strategy2) {
      return this.field2.style(consumer1, strategy2);
   }

   @Generated
   public Component style(Builder builder1) {
      return this.field2.style(builder1);
   }

   @Generated
   public Component mergeStyle(Component component1) {
      return this.field2.mergeStyle(component1);
   }

   @Generated
   public Component mergeStyle(Component component1, Merge... items2) {
      return this.field2.mergeStyle(component1, items2);
   }

   @Generated
   public Component mergeStyle(Component component1, Set<Merge> set2) {
      return this.field2.mergeStyle(component1, set2);
   }

   @Generated
   public Key font() {
      return this.field2.font();
   }

   @Generated
   public Component font(Key key) {
      return this.field2.font(key);
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
   public Component color(TextColor textcolor1) {
      return this.field2.color(textcolor1);
   }

   @Generated
   public Component colorIfAbsent(TextColor textcolor1) {
      return this.field2.colorIfAbsent(textcolor1);
   }

   @Generated
   public Component shadowColor(ARGBLike argblike1) {
      return this.field2.shadowColor(argblike1);
   }

   @Generated
   public Component shadowColorIfAbsent(ARGBLike argblike1) {
      return this.field2.shadowColorIfAbsent(argblike1);
   }

   @Generated
   public boolean hasDecoration(TextDecoration textdecoration1) {
      return this.field2.hasDecoration(textdecoration1);
   }

   @Generated
   public Component decorate(TextDecoration textdecoration1) {
      return this.field2.decorate(textdecoration1);
   }

   @Generated
   public State decoration(TextDecoration textdecoration1) {
      return this.field2.decoration(textdecoration1);
   }

   @Generated
   public Component decoration(TextDecoration textdecoration1, boolean flag2) {
      return this.field2.decoration(textdecoration1, flag2);
   }

   @Generated
   public Component decoration(TextDecoration textdecoration1, State state2) {
      return this.field2.decoration(textdecoration1, state2);
   }

   @Generated
   public Component decorationIfAbsent(TextDecoration textdecoration1, State state2) {
      return this.field2.decorationIfAbsent(textdecoration1, state2);
   }

   @Generated
   public Map<TextDecoration, State> decorations() {
      return this.field2.decorations();
   }

   @Generated
   public Component decorations(Map<TextDecoration, State> map) {
      return this.field2.decorations(map);
   }

   @Generated
   public ClickEvent clickEvent() {
      return this.field2.clickEvent();
   }

   @Generated
   public Component clickEvent(ClickEvent clickevent1) {
      return this.field2.clickEvent(clickevent1);
   }

   @Generated
   public HoverEvent<?> hoverEvent() {
      return this.field2.hoverEvent();
   }

   @Generated
   public Component hoverEvent(HoverEventSource<?> hovereventsource1) {
      return this.field2.hoverEvent(hovereventsource1);
   }

   @Generated
   public String insertion() {
      return this.field2.insertion();
   }

   @Generated
   public Component insertion(String text1) {
      return this.field2.insertion(text1);
   }

   @Generated
   public boolean hasStyling() {
      return this.field2.hasStyling();
   }

   @Generated
   public Component replaceText(Consumer<net.kyori.adventure.text.TextReplacementConfig.Builder> consumer1) {
      return this.field2.replaceText(consumer1);
   }

   @Generated
   public Component replaceText(TextReplacementConfig textreplacementconfig1) {
      return this.field2.replaceText(textreplacementconfig1);
   }

   @Generated
   public Component compact() {
      return this.field2.compact();
   }

   @Generated
   public Component compact(Style style1) {
      return this.field2.compact(style1);
   }

   @Generated
   public Iterable<Component> iterable(ComponentIteratorType componentiteratortype1, ComponentIteratorFlag... items2) {
      return this.field2.iterable(componentiteratortype1, items2);
   }

   @Generated
   public Iterable<Component> iterable(ComponentIteratorType componentiteratortype1, Set<ComponentIteratorFlag> set2) {
      return this.field2.iterable(componentiteratortype1, set2);
   }

   @Generated
   public Iterator<Component> iterator(ComponentIteratorType componentiteratortype1, ComponentIteratorFlag... items2) {
      return this.field2.iterator(componentiteratortype1, items2);
   }

   @Generated
   public Iterator<Component> iterator(ComponentIteratorType componentiteratortype1, Set<ComponentIteratorFlag> set2) {
      return this.field2.iterator(componentiteratortype1, set2);
   }

   @Generated
   public Spliterator<Component> spliterator(ComponentIteratorType componentiteratortype1, ComponentIteratorFlag... items2) {
      return this.field2.spliterator(componentiteratortype1, items2);
   }

   @Generated
   public Spliterator<Component> spliterator(ComponentIteratorType componentiteratortype1, Set<ComponentIteratorFlag> set2) {
      return this.field2.spliterator(componentiteratortype1, set2);
   }

   @Deprecated
   @Generated
   public Component replaceText(String text1, ComponentLike componentlike2) {
      return this.field2.replaceText(text1, componentlike2);
   }

   @Deprecated
   @Generated
   public Component replaceText(Pattern pattern1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> function2) {
      return this.field2.replaceText(pattern1, function2);
   }

   @Deprecated
   @Generated
   public Component replaceFirstText(String text1, ComponentLike componentlike2) {
      return this.field2.replaceFirstText(text1, componentlike2);
   }

   @Deprecated
   @Generated
   public Component replaceFirstText(Pattern pattern1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> function2) {
      return this.field2.replaceFirstText(pattern1, function2);
   }

   @Deprecated
   @Generated
   public Component replaceText(String text1, ComponentLike componentlike2, int number3) {
      return this.field2.replaceText(text1, componentlike2, number3);
   }

   @Deprecated
   @Generated
   public Component replaceText(Pattern pattern1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> function2, int number3) {
      return this.field2.replaceText(pattern1, function2, number3);
   }

   @Deprecated
   @Generated
   public Component replaceText(String text1, ComponentLike componentlike2, IntFunction2<PatternReplacementResult> intfunction23) {
      return this.field2.replaceText(text1, componentlike2, intfunction23);
   }

   @Deprecated
   @Generated
   public Component replaceText(
      Pattern pattern1, Function<net.kyori.adventure.text.TextComponent.Builder, ComponentLike> function2, IntFunction2<PatternReplacementResult> intfunction23
   ) {
      return this.field2.replaceText(pattern1, function2, intfunction23);
   }

   @Generated
   public void componentBuilderApply(ComponentBuilder<?, ?> componentbuilder1) {
      this.field2.componentBuilderApply(componentbuilder1);
   }

   @Generated
   public Component asComponent() {
      return this.field2.asComponent();
   }

   @Generated
   public HoverEvent<Component> asHoverEvent(UnaryOperator<Component> unaryoperator1) {
      return this.field2.asHoverEvent(unaryoperator1);
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
   public <R> R examine(Examiner<R> examiner1) {
      return (R)this.field2.examine(examiner1);
   }

   @Generated
   public HoverEvent<Component> asHoverEvent() {
      return this.field2.asHoverEvent();
   }

   @Generated
   public Component decorate(TextDecoration... items1) {
      return (Component)this.field2.decorate(items1);
   }

   @Generated
   public Component decorations(Set<TextDecoration> set, boolean flag2) {
      return (Component)this.field2.decorations(set, flag2);
   }
}
