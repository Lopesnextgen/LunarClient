package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.google.common.collect.Iterators;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.LinkedHashSetImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.Annotation7;
import java.util.Iterator;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@Annotation7
public class Gui2_2 implements Iterable<Gui_2<?>> {
   @SerializedName("effects")
   private Set<GuiImpl> field1;
   @SerializedName("gameplay")
   private Set<GuiImpl3> field2;
   @SerializedName("audios")
   private Set<GuiImpl2> field3;
   private final HashMapImpl field4 = new HashMapImpl();
   private Highlight2 field5 = new Highlight2(this.field4);

   public Gui2_2(Nameplate2 var1) {
      this.field1 = new LinkedHashSetImpl<>(var1);
      this.field2 = new LinkedHashSetImpl<>(var1);
      this.field3 = new LinkedHashSetImpl<>(var1);
   }

   @NotNull
   @Override
   public Iterator<Gui_2<?>> iterator() {
      return Iterators.concat(this.field3.iterator(), this.field2.iterator(), this.field1.iterator());
   }

   public int size() {
      return this.field1.size() + this.field2.size() + this.field3.size();
   }

   private void method1(Gui_2<?> var1) {
      this.field4.putAll(var1.method5().method12());
      var1.method5().method14(this.field4);
      var1.method5().method15(() -> var1.method3(null));

      for (RewindIterator var3 : var1.method5().method11().values()) {
         this.field5.method7(var3.method17(), var3.getId());
      }
   }

   @Generated
   public Set<GuiImpl> method2() {
      return this.field1;
   }

   @Generated
   public Set<GuiImpl3> method3() {
      return this.field2;
   }

   @Generated
   public Set<GuiImpl2> method4() {
      return this.field3;
   }

   @Generated
   public HashMapImpl method5() {
      return this.field4;
   }

   @Generated
   public Highlight2 method6() {
      return this.field5;
   }

   @Generated
   public void method7(Highlight2 var1) {
      this.field5 = var1;
   }
}
