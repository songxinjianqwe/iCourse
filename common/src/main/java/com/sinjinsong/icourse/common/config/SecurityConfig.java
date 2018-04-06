package com.sinjinsong.icourse.common.config;

import com.sinjinsong.icourse.common.security.endpoint.JWTAuthenticationEntryPoint;
import com.sinjinsong.icourse.common.security.filter.JWTAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JWTAuthenticationEntryPoint unauthorizedHandler;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SecurityConfig(JWTAuthenticationEntryPoint unauthorizedHandler,
                          UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }


    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JWTAuthenticationTokenFilter();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 添加JWT filter
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //添加JWTFilter
                .authorizeRequests()
                //允许跨域的预检请求OPTIONS
                //详情见CORS阮一峰
                .requestMatchers(CorsUtils:: isPreFlightRequest).permitAll()
                //允许访问静态资源
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/image/**").permitAll()
                //允许访问swagger
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagge‌​r-ui.html").permitAll()
                //允许访问Druid监控
                .antMatchers("/druid/**").permitAll()
                //获取图片验证码
                .antMatchers(HttpMethod.GET, "/captchas").permitAll()
                //检查用户名是否重复
                .antMatchers(HttpMethod.GET, "/users/*/duplication").permitAll()
                //注册
                .antMatchers(HttpMethod.POST, "/students").permitAll()
                .antMatchers(HttpMethod.POST, "/institutions").permitAll()
                
                //用户激活
                .antMatchers(HttpMethod.POST, "/students/*/mail_validation").permitAll()
                .antMatchers(HttpMethod.POST, "/students/*/activation").permitAll()
                //用户申请忘记密码
                .antMatchers(HttpMethod.POST, "/students/*/password/reset_validation").permitAll()
                //用户忘记密码后重置密码
                .antMatchers(HttpMethod.PUT, "/students/*/password").permitAll()
                //获取token
                .antMatchers(HttpMethod.POST, "/tokens").permitAll() 
                
                //除上面外的所有请求全部需要鉴权认证
                .and().authorizeRequests().anyRequest().authenticated().and();
                //Filter要放到是否认证的配置之后

        // 禁用缓存
        httpSecurity
                .headers().cacheControl();
    }
}
