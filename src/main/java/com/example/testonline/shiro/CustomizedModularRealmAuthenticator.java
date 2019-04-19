package com.example.testonline.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
/*        if (getRealms() != null ){
            Collection<Realm> realms = getRealms();
            for (Realm realm :
                    realms) {
                System.out.println(realm.getName());
            }
        }
        else
            System.out.println("getRealms : null");*/

        assertRealmsConfigured();
        CostomLoginToken token = (CostomLoginToken) authenticationToken;
        String loginType = token.getLoginType();

        Collection<Realm> realms = getRealms();
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm:
             realms) {
            if (realm.getName().equals(loginType))
                ((ArrayList<Realm>) typeRealms).add(realm);
        }
        if (((ArrayList<Realm>) typeRealms).size() == 1)
            return doSingleRealmAuthentication(typeRealms.iterator().next(),token);
        else
            return doMultiRealmAuthentication(typeRealms,token);
    }
}
