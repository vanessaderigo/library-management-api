package com.vanessa.librarymanagementapi.configuration.web;

import com.vanessa.librarymanagementapi.security.CustomAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Tag(name = "authentication")
public class LoginViewController {
    @GetMapping("/login")
    @Operation(summary = "Login page", description = "Returns the HTML login page.")
    @ApiResponse(responseCode = "200", description = "Login page loaded successfully.")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    @Operation(summary = "Home page", description = "Returns a welcome message with authenticated user's name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User authenticated successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public String homePage(Authentication authentication){
        if (authentication instanceof CustomAuthentication customAuth) System.out.println(customAuth.getUser());
        return "Hello, " + authentication.getName() + "!";
    }

    @GetMapping("/authorized")
    @ResponseBody
    @Operation(summary = "Get authorization code", description = "Returns the OAuth2 authorization code.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authorization code returned."),
            @ApiResponse(responseCode = "400", description = "Missing or invalid 'code' parameter.")})
    public String getAuthorizationCode(@RequestParam("code") String code){
        return "Authorization code: " + code;
    }
}
