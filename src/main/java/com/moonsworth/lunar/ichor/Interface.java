package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
@KeepName
public @interface Interface {
   Class<?> value();

   com.moonsworth.lunar.ichor.VersionGate available();
}
