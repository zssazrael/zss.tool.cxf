package zss.tool.cxf;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zss.tool.Version;

@Version("2018.05.02")
@Configuration
public class CXFConfiguration {
    @Bean
    public static CXFNonSpringServlet cxfServlet() {
        final CXFNonSpringServlet servlet = new CXFNonSpringServlet();
        servlet.setBus(new SpringBus());
        return servlet;
    }

    @Bean
    public static CXFBeanPostProcessor webServiceBeanPostProcessor(@Autowired @Qualifier("cxfServlet") final CXFNonSpringServlet servlet) throws Exception {
        final CXFBeanPostProcessor beanPostProcessor = new CXFBeanPostProcessor();
        beanPostProcessor.setBus(servlet.getBus());
        return beanPostProcessor;
    }
}
