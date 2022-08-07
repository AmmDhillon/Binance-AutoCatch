//package com.ammdhillon.autocatch.filter;
//
//import com.ammdhillon.autocatch.Constants;
//import com.ammdhillon.autocatch.helper.Utils;
//import com.ammdhillon.autocatch.service.apis.VerificationService;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import org.hibernate.usertype.UserType;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//import static com.ammdhillon.autocatch.model.enums.UserType.ADMIN;
//
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final UserService userService;
//    private final VerificationService verificationService;
//    private final AuthenticationManager authenticationManager;
//
//    public CustomAuthenticationFilter(VerificationService verificationService, UserService userService, AuthenticationManager authenticationManager) {
//        this.verificationService = verificationService;
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String mobileNum = request.getParameter(Constants.MOBILE_NUM);
//        String password = request.getParameter(Constants.PASSWORD);
//        String otpToken = request.getParameter(Constants.OTP_TOKEN);
//
//        System.out.println("Login Attempt:");
//        System.out.println(mobileNum);
//        System.out.println(password);
//        System.out.println(otpToken);
//
//        UserModel userModel = userService.authenticateUser(Long.valueOf(mobileNum), password);
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mobileNum, password);
//
//        if (userModel != null && userModel.getStatus().equals(UserStatus.APPROVED)) {
//            if (true) {
//                authenticationToken.setDetails(true);
//            } else {
//                authenticationToken.setDetails(false);
//            }
//        } else {
//            authenticationToken.setDetails(false);
//        }
//
//        return authenticationManager.authenticate(authenticationToken);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        Utils.raiseError(response, HttpStatus.BAD_REQUEST, false, failed.getMessage());
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        if (((Boolean) authResult.getDetails())) {
//            UserModel userModel = (UserModel) authResult.getPrincipal();
//
//            boolean isAdmin = false;
//
//            for (GrantedAuthority s: userModel.getAuthorities()) {
//                if (s.getAuthority().equals(ADMIN.name())) {
//                    isAdmin = true;
//                    break;
//                }
//            }
//
//            Algorithm algorithm = Algorithm.HMAC256("user_secret".getBytes());
////            Algorithm algorithm = Algorithm.HMAC256(isAdmin ? "admin_secret".getBytes() : "user_secret".getBytes());
//
//            String accessToken = JWT.create()
//                    .withSubject(userModel.getUsername())
//                    .withClaim(UserType.class.getSimpleName(), userModel.getUserType().name())
//                    .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
//                    .withIssuer(request.getServletPath())
//                    .sign(algorithm);
//
//            String refreshToken = JWT.create()
//                    .withSubject(userModel.getUsername())
//                    .withClaim(UserType.class.getName(), userModel.getUserType().name())
//                    .withExpiresAt(new Date(System.currentTimeMillis() + 300 * 60 * 1000))
//                    .withIssuer(request.getServletPath())
//                    .sign(algorithm);
//
////            response.setHeader(Constants.ACCESS_TOKEN, accessToken);
//
//            Utils.loginResponse(response, HttpStatus.OK, false, accessToken, refreshToken, userModel);
//
//            System.out.println("TOKEN: " + accessToken);
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(request, response, chain);
////        Utils.logRequest(request);
//    }
//}
