package org.bos.Achaoub.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;



public class AuthorizationFilter extends BasicAuthenticationFilter {

	
//	@Autowired
//	private JwtUtils jwtUtils;

//	@Autowired
//	private UserDetailsServiceImpl userDetailsService;
	
	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	
	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		try {
//			String jwt = parseJwt(request);
//			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//				String username = jwtUtils.getUserNameFromJwtToken(jwt);
//
//				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//			}
//		} catch (Exception e) {
//			logger.error("Cannot set user authentication: {}", e);
//		}
//
//		filterChain.doFilter(request, response);
//	}
//
//	private String parseJwt(HttpServletRequest request) {
//		String headerAuth = request.getHeader("Authorization");
//
//		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//			return headerAuth.substring(7, headerAuth.length());
//		}
//
//		return null;
//	}
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        
        if (token != null) {
            
            token = token.replace(SecurityConstants.TOKEN_PREFIX,"");
            
            String user = Jwts.parser()
                    .setSigningKey( SecurityConstants.TOKEN_SECRET )
                    .parseClaimsJws( token )
                    .getBody()
                    .getSubject();
            
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            
            return null;
        }
        
        return null;
    }

}
