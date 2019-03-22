package io.everitoken.sdk.java.example;

import io.everitoken.sdk.java.EvtLink;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.provider.SignProvider;

class GetEvtLinkForEveriPassExample {
    public static void main(String[] args) {
        NetParams netParams = new TestNetNetParams();

        // Init new EvtLink instance with given net param
        EvtLink evtLink = new EvtLink(netParams);

        // make sure the domain and token you use exist and has correct authorize keys
        EvtLink.EveriPassParam everiPassParam = new EvtLink.EveriPassParam(true, "domainName", "tokenName");

        String passText = evtLink.getEvtLinkForEveriPass(everiPassParam,
                SignProvider.of(KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D")));

        // will print out evt link
        System.out.println(passText);
    }

}