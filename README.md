## Extend OpenID Connect with Okta Token Hooks

This is a Spring Boot example application that exposes an API to manage a list of 
favorite beers. These endpoints are protected with OpenID Connect and integrate with Okta.

It also exposes an endpoint to alter the content of an ID token in-flight using Okta's 
Token Hooks features. This endpoint is protected with basic authentication and can be
configured in Okta's Workflow menus.

This repo goes along with the blog post found 
[here](https://developer.okta.com/blog/2019/12/23/extend-oidc-okta-token-hooks), 
which goes into all the details of understanding Okta's Token Hooks and how to 
configure this project.