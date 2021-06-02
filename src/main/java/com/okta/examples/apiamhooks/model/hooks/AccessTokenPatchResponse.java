package com.okta.examples.apiamhooks.model.hooks;

import lombok.Data;

@Data
public class AccessTokenPatchResponse extends TokenPatchResponse {

    public AccessTokenPatchResponse() {
        super.setType("com.okta.access.patch");
    }
}
