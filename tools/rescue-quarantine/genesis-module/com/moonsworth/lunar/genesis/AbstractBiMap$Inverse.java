package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import com.google.common.annotations.GwtIncompatible;

class AbstractBiMap$Inverse<K, V> extends MixinHelper3124<K, V> {
   @GwtIncompatible
   private static final long field3 = 0L;

   AbstractBiMap$Inverse(Map<K, V> map1, MixinHelper3124<V, K> mixinhelper31242) {
      super(map1, mixinhelper31242, null);
   }

   K checkKey(K value1) {
      return (K)this.CHROICCOHORIHHIIOCIRCHCHCCCCHO.checkValue(value1);
   }

   V checkValue(V value1) {
      return (V)this.CHROICCOHORIHHIIOCIRCHCHCCCCHO.checkKey(value1);
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream objectoutputstream1) {
      objectoutputstream1.defaultWriteObject();
      objectoutputstream1.writeObject(this.HRROOCOOHIRIIOOOCCRIIICIRRIORO());
   }

   @GwtIncompatible
   private void readObject(ObjectInputStream objectinputstream1) {
      objectinputstream1.defaultReadObject();
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO((MixinHelper3124)objectinputstream1.readObject());
   }

   @GwtIncompatible
   Object readResolve() {
      return this.HRROOCOOHIRIIOOOCCRIIICIRRIORO().method2();
   }
}
