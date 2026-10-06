package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.Sliceable;
import com.moonsworth.lunar.client.replay.timeline.LinkedHashMapImpl;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public abstract class PropertyGroup implements Sliceable<Integer, PropertyGroup> {
   protected LinkedHashMapImpl<String, PropertyGroup> field1;
   protected LinkedHashMapImpl<String, KeyframeProperty<?, ?>> field2;
   private boolean field3 = true;
   private boolean enabled = true;
   private boolean field4 = false;
   private boolean field5 = true;
   private boolean field6 = true;
   private PropertyGroup.NodeType field7 = PropertyGroup.NodeType.NONE;
   private boolean field8 = false;
   private boolean field9 = false;

   public PropertyGroup(com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21) {
      this.field1 = new LinkedHashMapImpl<>(nameplate21);
      this.field2 = new LinkedHashMapImpl<>(nameplate21);
      this.field1.method5(this::method4);
      this.field2.method5(this::method5);
   }

   public PropertyGroup method1() {
      this.field3 = false;
      this.enabled = false;
      return this;
   }

   protected void method2(PropertyGroup fishing2iterator1) {
      this.field1.put(fishing2iterator1.type(), fishing2iterator1);
   }

   protected void method3(KeyframeProperty<?, ?> fishing2loader1) {
      this.field2.put(fishing2loader1.type(), fishing2loader1);
   }

   protected void method4(String text1) {
      PropertyGroup fishing2iterator2 = this.method11().get(text1);
      fishing2iterator2.method7();
   }

   protected void method5(String text1) {
   }

   public void method6(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      if (this.enabled) {
         ((ReplayContext)threadmoduledump61.get()).method9().method1(this);

         for (PropertyGroup fishing2iterator4 : this.field1.values()) {
            fishing2iterator4.method6(threadmoduledump61, number2);
         }

         for (KeyframeProperty fishing2loader6 : this.field2.values()) {
            fishing2loader6.method12(threadmoduledump61, number2);
         }
      }
   }

   public void method7() {
      for (KeyframeProperty fishing2loader2 : this.field2.values()) {
         fishing2loader2.method3();
      }
   }

   public PropertyGroup method8(
      PropertyGroup fishing2iterator1, com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate22, Range<Integer> range3, Range<Integer> range4
   ) {
      LinkedHashMapImpl linkedhashmapimpl5 = new LinkedHashMapImpl(nameplate22);

      for (Entry entry7 : this.field1.entrySet()) {
         linkedhashmapimpl5.put((String)entry7.getKey(), (PropertyGroup)((PropertyGroup)entry7.getValue()).HRICOROOOCCOCOROCRHHCRRIRCOICO(nameplate22, range3, range4));
      }

      fishing2iterator1.field1 = linkedhashmapimpl5;
      LinkedHashMapImpl linkedhashmapimpl9 = new LinkedHashMapImpl(nameplate22);

      for (Entry entry8 : this.field2.entrySet()) {
         linkedhashmapimpl9.put((String)entry8.getKey(), (KeyframeProperty)((KeyframeProperty)entry8.getValue()).method7(nameplate22, range3, range4));
      }

      fishing2iterator1.field2 = linkedhashmapimpl9;
      fishing2iterator1.field3 = this.field3;
      fishing2iterator1.enabled = this.enabled;
      fishing2iterator1.field4 = this.field4;
      fishing2iterator1.field5 = this.field5;
      fishing2iterator1.field6 = this.field6;
      if (fishing2iterator1 instanceof AnimatedPropertyGroup fishing2iterator311) {
         fishing2iterator311.setType(fishing2iterator1.type());
      }

      return fishing2iterator1;
   }

   public String method9() {
      return Client.method109().method67().method2("rewind", this.type(), new Object[0]);
   }

   public abstract String type();

   public abstract List<String> method10();

   @Generated
   public LinkedHashMapImpl<String, PropertyGroup> method11() {
      return this.field1;
   }

   @Generated
   public LinkedHashMapImpl<String, KeyframeProperty<?, ?>> method12() {
      return this.field2;
   }

   @Generated
   public boolean method13() {
      return this.field3;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public boolean isExtended() {
      return this.field4;
   }

   @Generated
   public boolean method14() {
      return this.field5;
   }

   @Generated
   public boolean method15() {
      return this.field6;
   }

   @Generated
   public PropertyGroup.NodeType method16() {
      return this.field7;
   }

   @Generated
   public boolean method17() {
      return this.field8;
   }

   @Generated
   public boolean method18() {
      return this.field9;
   }

   @Generated
   public void setEnabled(boolean flag1) {
      this.enabled = flag1;
   }

   @Generated
   public void method19(boolean flag1) {
      this.field4 = flag1;
   }

   @Generated
   public void method20(boolean flag1) {
      this.field5 = flag1;
   }

   @Generated
   public void method21(boolean flag1) {
      this.field6 = flag1;
   }

   @Generated
   public void method22(PropertyGroup.NodeType type1) {
      this.field7 = type1;
   }

   @Generated
   public void method23(boolean flag1) {
      this.field8 = flag1;
   }

   @Generated
   public void method24(boolean flag1) {
      this.field9 = flag1;
   }

   public enum NodeType {
      NONE,
      KEYFRAMES,
      CHILD_PROPERTIES;

      NodeType() {
      }
   }
}
