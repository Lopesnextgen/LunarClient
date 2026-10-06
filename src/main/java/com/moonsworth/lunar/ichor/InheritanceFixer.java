package com.moonsworth.lunar.ichor;

import javax.annotation.Nullable;
import org.cadixdev.bombe.analysis.InheritanceProvider;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;

@FunctionalInterface
public interface InheritanceFixer {
   @Nullable
   ClassInfo fix(InheritanceProvider inheritanceprovider1, String text2, @Nullable ClassInfo classinfo3);
}
