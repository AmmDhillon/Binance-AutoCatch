//package com.ammdhillon.autocatch.filter;
//
//import com.ammdhillon.autocatch.Constants;
//import com.ammdhillon.autocatch.helper.Utils;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.hibernate.usertype.UserType;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
//public class CustomAuthorizationFilter extends OncePerRequestFilter {
//
//    private final UserService userService;
//
//    public CustomAuthorizationFilter(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader(AUTHORIZATION);
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            try {
//                String token = authHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("user_secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(token);
//                String mobileNum = decodedJWT.getSubject();
//                String claim = decodedJWT.getClaim(UserType.class.getSimpleName()).asString();
//
//                if (request.getServletPath().startsWith("/api/v1/admin/") && !claim.equals(UserType.ADMIN.name())) {
//                    Utils.raiseError(response, HttpStatus.UNAUTHORIZED, false, Constants.UNAUTHORIZED_ACCESS);
//                    return;
//                }
//
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mobileNum, null, null);
//
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
////                if (!userService.isUserEnabled(Long.valueOf(mobileNum))) {
////                    Utils.raiseError(response, HttpStatus.UNAUTHORIZED, false, Constants.BANNED_MSG);
////                    return;
////                }
//
//                cors(response);
//                filterChain.doFilter(request, response);
//            } catch (Exception e) {
//                Utils.raiseError(response, HttpStatus.BAD_REQUEST, false, e.getMessage());
//            }
//        } else {
//            cors(response);
//            filterChain.doFilter(request, response);
//        }
//    }
//
//    private void cors(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
//        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.addIntHeader("Access-Control-Max-Age", 10);
//    }
//}