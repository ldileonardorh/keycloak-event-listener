## Custom Event Listener Provider for Keycloak / Red Hat SSO
This extensions intercept all user events. it is build arounf Keycloak libraries and extensions and can be deployed

## Requirements
* Java: JDK 11 (must be the same running Keycloak / SSO)
* Apache Maven
* Running instance of Keycloak

## Tested Configurations
* Red Hat SSO 7.6.* Single Node
* Red Hat SSO 7.6.* HA Cluster
* Java 11

## Build
Java: JDK 11 (must be the same running Keycloak / SSO)
Packaging: Maven
```
mvn clean package
```
This generates target/sso-event-listener-provider.jar**


# Deploy Single Node

* Deploy **target/keycloak-crm-event-listener-provider.jar** to {KEYCLOAK_HOME}/standalone/deployments

# Deploy Cluster

* Deploy **target/keycloak-crm-event-listener-provider.jar** to {KEYCLOAK_HOME}/standalone/deployments on all cluster noded

# How to activate Event Listener
1. Access to Selected realm (/auth/admin/{REALM}/console). User privileges must allow event management
2. Click on "Events" link on left menu (see image)
3. Click on "Event Listener" field, a drowdown will apper and will see 'Pernexus.EventListener-v1'
4. By Clicking on that Event listener will be added to the combo.
5. Click Save.

# Make it operate
1. Tail server.log on your server (all nodes if running a cluster, you don't know which node will be involved in user events, it depends on load balancer).
2. If you already have a Client Id up&Running, try to do some user activities (login/logout/register).
3. For testing purpose only, this module can be activated in "Master" realm and can be used with "account" application, by "Impersonatig" user.

### Effects
In log tail you shuld be able to see messages like this one
![Alt text](SSO_2.png"Log outcome")


