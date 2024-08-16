package com.ahmed.secoopecproductnetwork.config;

import com.ahmed.secoopecproductnetwork.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAWARE implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken)
        {return Optional.empty();}
        User userPricipal=(User)authentication.getPrincipal();
        //if userpricipal.getid is null in tack to me optional.emty
        return Optional.ofNullable((userPricipal.getId()));

    }
}
