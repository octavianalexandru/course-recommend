package com.course.recommend.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.course.recommend.config.CourseApplicationConfig;

public class CourseWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { CourseApplicationConfig.class };
    }
 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
 
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
