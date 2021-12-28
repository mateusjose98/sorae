package pkg.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.JwtException;
import pkg.exception.CustomException;
import pkg.services.JwtTokenService;

public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenService jwtTokenProvider;

	public JwtTokenFilter(JwtTokenService jwtTokenService) {
		this.jwtTokenProvider = jwtTokenService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);

		if (token != null) {
			if (!jwtTokenProvider.isTokenPresentInDB(token)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Efetue um novo login (token inválido)");
				throw new CustomException("Efetue um novo login (token inválido)", HttpStatus.UNAUTHORIZED);
			}
			try {
				jwtTokenProvider.validateToken(token);
			} catch (JwtException | IllegalArgumentException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Efetue um novo login (token inválido)");
				throw new CustomException("Efetue um novo login (token inválido)", HttpStatus.UNAUTHORIZED);
			}
			Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
			// setting auth in the context.
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(req, res);
	}
}
