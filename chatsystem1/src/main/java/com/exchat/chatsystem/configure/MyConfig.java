package com.exchat.chatsystem.configure;



import com.exchat.chatsystem.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class  MyConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login.html");//不加html就没发跳
        registry.addViewController("/register").setViewName("register.html");

    }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // super.addInterceptors(registry);
            registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/login","/","/user/login","/user/save","/register");
        }
    }

