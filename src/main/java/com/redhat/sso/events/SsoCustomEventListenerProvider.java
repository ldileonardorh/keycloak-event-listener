package com.redhat.sso.events;

import java.util.concurrent.CompletableFuture;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.UserModel;
/**
 * 
 * @author Luciano Di Leonardo
 * @email ldileona@redhat.com
 * @date 24 Apr 2023
 * @company Red Hat inc.
 * @role Architect
 */
public class SsoCustomEventListenerProvider	implements EventListenerProvider
{

	private static final Logger LOGGER = Logger.getLogger(SsoCustomEventListenerProvider.class.getName());

	private final KeycloakSession session;
    private final RealmProvider model;

    public SsoCustomEventListenerProvider(KeycloakSession session) {
        this.session = session;
        this.model = session.realms();
    }

	

	@Override public void onEvent(Event event)
	{
		CompletableFuture.runAsync(() -> {
			Long start = System.currentTimeMillis();
//			if (EventType.REGISTER.equals(event.getType())) {
				LOGGER.info("");
				try {
					RealmModel realm = this.model.getRealm(event.getRealmId());
				    UserModel newRegisteredUser = this.session.users().getUserById(event.getUserId(), realm);
					//Do some actions
				    LOGGER.info("Username: "+newRegisteredUser.getUsername());
				    LOGGER.info("Email: "+newRegisteredUser.getEmail());
				    LOGGER.info("FirstName: "+newRegisteredUser.getFirstName());
				    this.sendToHttp(newRegisteredUser);
				    this.sendToJms(newRegisteredUser);
				}catch(Exception ex) {
					LOGGER.error("Exception caught:", ex);
				}finally {
					LOGGER.info("Ends in: "+(System.currentTimeMillis()-start)+" ms.");
				}
//			}
			
		});
	}

	@Override public void onEvent(AdminEvent adminEvent, boolean b)
	{
		//Not useful
	}

	@Override public void close()
	{
		//Not useful
	}

	private void sendToHttp(UserModel model) throws Exception
	{
		LOGGER.log(Level.INFO, "Implement Here HTTP Request");
	}
	
	private void sendToJms(UserModel model) throws Exception
	{
		LOGGER.log(Level.INFO, "Implement Here JMS Request");
	}

}
