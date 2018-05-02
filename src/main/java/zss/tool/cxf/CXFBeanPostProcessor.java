package zss.tool.cxf;

import javax.jws.WebService;

import org.apache.cxf.Bus;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import zss.tool.Version;

@Version("2018.05.02")
public class CXFBeanPostProcessor implements BeanPostProcessor {
    private Bus bus;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final Class<?> clazz = AopUtils.getTargetClass(bean);
        if (clazz.isAnnotationPresent(WebService.class)) {
            WebService ws = clazz.getAnnotation(WebService.class);
            String url = "/" + ws.serviceName();
            createAndPublishEndpoint(url, bean);
        }
        return bean;
    }

    private void createAndPublishEndpoint(String url, Object implementor) {
        final ServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
        serverFactory.setServiceBean(implementor);
        serverFactory.setServiceClass(AopUtils.getTargetClass(implementor));
        serverFactory.setAddress(url);

        serverFactory.setDataBinding(new JAXBDataBinding());
        serverFactory.setBus(bus);
        serverFactory.create();
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
