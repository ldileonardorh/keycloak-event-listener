package com.redhat.sso.events;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
/**
 * 
 * @author Luciano Di Leonardo
 * @email ldileona@redhat.com
 * @date 24 Apr 2023
 * @company Red Hat inc.
 * @role Architect
 */
public class SsoCustomEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {

        return new SsoCustomEventListenerProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {
    	
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        //Not useful
    }

    @Override
    public void close() {
        //Not useful
    }

    @Override
    public String getId() {
        return "Pernexus.EventListener-v1";
    }
}
