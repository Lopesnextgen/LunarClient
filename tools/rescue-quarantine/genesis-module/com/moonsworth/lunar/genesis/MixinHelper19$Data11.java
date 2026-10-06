package com.moonsworth.lunar.genesis;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;

@Annotation3
class MixinHelper19$Data11<K, V1, V2> extends MixinHelper19$Data12<K, V1, V2> implements NavigableMap<K, V2> {
   MixinHelper19$Data11(NavigableMap<K, V1> var1, MixinHelper19$Extension<? super K, ? super V1, V2> var2) {
      super(var1, var2);
   }

   @Override
   public Entry<K, V2> ceilingEntry(K var1) {
      return this.transformEntry(this.fromMap().ceilingEntry((K)var1));
   }

   @Override
   public K ceilingKey(K var1) {
      return this.fromMap().ceilingKey((K)var1);
   }

   @Override
   public NavigableSet<K> descendingKeySet() {
      return this.fromMap().descendingKeySet();
   }

   @Override
   public NavigableMap<K, V2> descendingMap() {
      return Maps.method26(this.fromMap().descendingMap(), this.field2);
   }

   @Override
   public Entry<K, V2> firstEntry() {
      return this.transformEntry(this.fromMap().firstEntry());
   }

   @Override
   public Entry<K, V2> floorEntry(K var1) {
      return this.transformEntry(this.fromMap().floorEntry((K)var1));
   }

   @Override
   public K floorKey(K var1) {
      return this.fromMap().floorKey((K)var1);
   }

   public NavigableMap<K, V2> headMap(K var1) {
      return this.headMap((K)var1, false);
   }

   @Override
   public NavigableMap<K, V2> headMap(K var1, boolean var2) {
      return Maps.method26(this.fromMap().headMap((K)var1, var2), this.field2);
   }

   @Override
   public Entry<K, V2> higherEntry(K var1) {
      return this.transformEntry(this.fromMap().higherEntry((K)var1));
   }

   @Override
   public K higherKey(K var1) {
      return this.fromMap().higherKey((K)var1);
   }

   @Override
   public Entry<K, V2> lastEntry() {
      return this.transformEntry(this.fromMap().lastEntry());
   }

   @Override
   public Entry<K, V2> lowerEntry(K var1) {
      return this.transformEntry(this.fromMap().lowerEntry((K)var1));
   }

   @Override
   public K lowerKey(K var1) {
      return this.fromMap().lowerKey((K)var1);
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      return this.fromMap().navigableKeySet();
   }

   @Override
   public Entry<K, V2> pollFirstEntry() {
      return this.transformEntry(this.fromMap().pollFirstEntry());
   }

   @Override
   public Entry<K, V2> pollLastEntry() {
      return this.transformEntry(this.fromMap().pollLastEntry());
   }

   @Override
   public NavigableMap<K, V2> subMap(K var1, boolean var2, K var3, boolean var4) {
      return Maps.method26(this.fromMap().subMap((K)var1, var2, (K)var3, var4), this.field2);
   }

   public NavigableMap<K, V2> subMap(K var1, K var2) {
      return this.subMap((K)var1, true, (K)var2, false);
   }

   public NavigableMap<K, V2> tailMap(K var1) {
      return this.tailMap((K)var1, true);
   }

   @Override
   public NavigableMap<K, V2> tailMap(K var1, boolean var2) {
      return Maps.method26(this.fromMap().tailMap((K)var1, var2), this.field2);
   }

   private @Nullable Entry<K, V2> transformEntry(@Nullable Entry<K, V1> var1) {
      return var1 == null ? null : Maps.method30(this.field2, var1);
   }

   protected NavigableMap<K, V1> fromMap() {
      return (NavigableMap<K, V1>)super.fromMap();
   }
}
