//package org.example.mall.filtter;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.mall.utils.JwtUtil;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
//        String token = request.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            try {
//                // 去除 "Bearer " 前缀，获取实际的 token
//                token = token.substring(7);
//
//                // 解析 token 并获取用户信息
//                Map<String, Object>obj = JwtUtil.parseToken(token);
//                String userId =   obj["userID"]// 获取存储的用户 ID
//
//                // 创建认证信息，设置用户信息到 SecurityContextHolder 中
//                Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
//                SecurityContextHolder.getContext().setAuthentication(authentication);  // 设置认证信息
//
//            } catch (Exception e) {
//                //返回状态码401
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid or expired token");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);  // 继续执行下一个过滤器或请求
//    }
//}
