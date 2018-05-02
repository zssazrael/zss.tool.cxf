package zss.tool.cxf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import zss.tool.Version;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CXFConfiguration.class)
@Version("2018.05.02")
public @interface EnableCXF {

}
