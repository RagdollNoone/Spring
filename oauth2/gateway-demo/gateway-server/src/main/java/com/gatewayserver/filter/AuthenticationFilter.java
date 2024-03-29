package com.gatewayserver.filter;

import com.gatewayserver.common.MDA;
import com.gatewayserver.common.TokenInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.*;

import java.util.LinkedHashSet;
import java.util.Set;

// 验证token是否有效 是不是仿冒的
@Order(0)
@Component
public class AuthenticationFilter implements GlobalFilter, InitializingBean {
    @Autowired
    private RestTemplate restTemplate;

    private static Set<String> shouldSkipUrl = new LinkedHashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        // 不拦截认证的请求
        shouldSkipUrl.add("/oauth/token");
        shouldSkipUrl.add("/oauth/check_token");
        shouldSkipUrl.add("/user/getCurrentUser");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getURI().getPath();

        //不需要认证的url
        if (shouldSkip(requestPath)) {
            return chain.filter(exchange);
        }

        //获取请求头
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        //请求头为空
        if (StringUtils.isEmpty(authHeader)) {
            throw new RuntimeException("请求头为空");
        }

        TokenInfo tokenInfo = null;
        try {
            //获取token信息
            tokenInfo = getTokenInfo(authHeader);
        } catch (Exception e) {
            throw new RuntimeException("校验令牌异常");
        }
        // tokenInfo
        exchange.getAttributes().put("tokenInfo", tokenInfo);
        return chain.filter(exchange);
    }

    private boolean shouldSkip(String reqPath) {

        for (String skipPath : shouldSkipUrl) {
            if (reqPath.contains(skipPath)) {
                return true;
            }
        }
        return false;
    }

    private TokenInfo getTokenInfo(String authHeader) {
        // 往授权服务发请求 /oauth/check_token
        // 获取token的值
        String token = StringUtils.substringAfter(authHeader, "bearer ");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(MDA.clientId, MDA.clientSecret); // 必须 basicAuth clienId clientSecret

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        ResponseEntity<TokenInfo> response = restTemplate.exchange(MDA.checkTokenUrl, HttpMethod.POST, entity, TokenInfo.class);

        return response.getBody();
    }
}

