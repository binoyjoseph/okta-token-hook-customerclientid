package com.okta.examples.apiamhooks.model.hooks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateHookRequest {

    private String oktaOrg;
    private String hookUrl;
}