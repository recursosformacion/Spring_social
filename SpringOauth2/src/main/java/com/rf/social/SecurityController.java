package com.rf.social;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class SecurityController {

	@GetMapping("**")
	public OAuth2User home(Model model, @AuthenticationPrincipal OAuth2User user, Authentication authentication,
			HttpServletRequest request) {
		//
		System.out.println(authentication.getName());
		System.out.println(request.getRequestURL());
		//
		String name = user.getAttribute("name");
		String email = user.getAttribute("email");
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return user;
	}

	@GetMapping("/oauth2/code/google")
	public String oauthSuccessCallbackGoogle(Authentication authentication, HttpServletRequest request) {
		// You can grab the access + refresh tokens as well via the "client"
		System.out.println("Llegando desde google");
		System.out.println(authentication.getName());
		System.out.println(request.getRequestURL());
		return "redirect:/user";
	}
	
	@GetMapping("/oauth2/code/github")
	public String oauthSuccessCallbackGithub(Authentication authentication, HttpServletRequest request) {
		// You can grab the access + refresh tokens as well via the "client"
		System.out.println("Llegando desde github");
		System.out.println(authentication.getName());
		System.out.println(request.getRequestURL());
		return "";
	}
	
	@GetMapping("/oauth2/code/linkedin")
	public String oauthSuccessCallbacklinkedin(Authentication authentication, HttpServletRequest request) {
		// You can grab the access + refresh tokens as well via the "client"
		System.out.println("Llegando desde linkedin");
		System.out.println(authentication.getName());
		System.out.println(request.getRequestURL());
		return "";
	}

	@GetMapping("/user")
	public Principal user(Principal principal) {
		
		return principal;
	}
	
	@GetMapping("/user2")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

	
	/**
	 * El token se ha de enviar a partir del logon
	 * String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

		if (!StringUtils.isEmpty(userInfoEndpointUri)) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
			HttpEntity entity = new HttpEntity("", headers);
			ResponseEntity<map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity,
					Map.class);
			Map userAttributes = response.getBody();
			model.addAttribute("name", userAttributes.get("name"));
		}
		
	 */
}
