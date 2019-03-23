# evt4j<!-- omit in toc -->

Official Java SDK for everiToken public chain.

- [Install](#install)
  - [use with Maven project](#use-with-maven-project)
  - [use with Gradle project](#use-with-gradle-project)
  - [other](#other)
- [Usage overview](#usage-overview)
- [PrivateKey usage](#privatekey-usage)
- [Api usage](#api-usage)
- [Action usage](#action-usage)
- [EvtLink usage](#evtlink-usage)
- [EvtLink](#evtlink)
  - [EvtLink generation](#evtlink-generation)
- [Deploy](#deploy)

## Install

### use with Maven project

### use with Gradle project

### other

Build jar with all the dependencies, run the following command

`mvn clean compile assembly:single`

It will generate jar with all dependencies under `target` folder

## Usage overview

Here is the code example which highlights the common usage of the SDK.

<details>
<summary>Click to see full code example</summary>

```java

package io.everitoken.sdk.java.example;

import java.util.Arrays;
import java.util.List;

import io.everitoken.sdk.java.Address;
import io.everitoken.sdk.java.Api;
import io.everitoken.sdk.java.Asset;
import io.everitoken.sdk.java.PrivateKey;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.abi.TransferFungibleAction;
import io.everitoken.sdk.java.dto.NodeInfo;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.service.TransactionConfiguration;
import io.everitoken.sdk.java.service.TransactionService;

class BasicUsage {
public static void main(String[] args) {
        // generate a key pair
        PrivateKey privateKey = PrivateKey.randomPrivateKey();
        PublicKey publicKey = privateKey.toPublicKey();

        // construct a NetParams to interact with the node
        NetParams netParams = new TestNetNetParams();

        // init Api instance
        Api api = new Api(netParams);

        // get information of the node
        try {
            NodeInfo info = api.getInfo();
            System.out.println(info.getChainId());
        } catch (ApiResponseException e) {
            System.out.println(e.getRaw());
        }

        // get balance of all fungible tokens (for example: EVT Token) for a public key
        try {
            // do something with balance list
            List<Asset> balances = api.getFungibleBalance(Address.of(publicKey));
        } catch (ApiResponseException e) {
            System.out.println(e.getRaw());
        }

        // transfer fungible tokens to others

        // construct an action to represent the transfer
        TransferFungibleAction transferFungibleAction = TransferFungibleAction.of("2.00002 S#20",
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND",
                "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H", "testing java");

        try {
            // init transaction service with net parameters
            TransactionService transactionService = TransactionService.of(netParams);

            // init transaction configuration
            TransactionConfiguration trxConfig = new TransactionConfiguration(1000000, publicKey,
                    KeyProvider.of(privateKey.toWif()));

            // push this action to the node and get back an transaction
            TransactionData txData = transactionService.push(trxConfig, Arrays.asList(transferFungibleAction));
            System.out.println(txData.getTrxId());
        } catch (ApiResponseException ex) {
            System.out.println(ex.getRaw());
        }
    }
}

```

</details>

## PrivateKey usage

**static** `randomPrivateKey`

<details>
<summary>Click to see code example</summary>

```java
import io.everitoken.sdk.java.PrivateKey;

PrivateKey privateKey = PrivateKey.randomPrivateKey();
```

</details>

**static** `seedPrivateKey`

<details>
<summary>Click to see code example</summary>

```java
import io.everitoken.sdk.java.PrivateKey;

PrivateKey seedPrivateKey = PrivateKey.seedPrivateKey("a random string");
```

</details>

**static** `of`

<details>
<summary>Click to see code example</summary>

```java
import io.everitoken.sdk.java.PrivateKey;

PrivateKey privateKeyFromWif = PrivateKey.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");
```

</details>

**static** `isValidPrivateKey`

<details>
<summary>Click to see code example</summary>

```java
import io.everitoken.sdk.java.PrivateKey;

boolean valid = PrivateKey.isValidPrivateKey("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");

```

</details>

`toPublicKey`

<details>
<summary>Click to see code example</summary>

```java
import io.everitoken.sdk.java.PrivateKey;
import io.everitoken.sdk.java.PublicKey;

PrivateKey privateKey = PrivateKey.randomPrivateKey();
PublicKey publicKey = privateKey.toPublicKey();

```

</details>

`toWif`

<details>
<summary>Click to see code example</summary>

```java
import io.everitoken.sdk.java.PrivateKey;

PrivateKey privateKey = PrivateKey.randomPrivateKey();
System.out.println(privateKey.toWif());

```

</details>

## Api usage

By instantiate an `Api` instance, you will be able to use it to interact with the specified remote node.

> Refer to [ApiExample.java](src/main/java/io/everitoken/sdk/java/example/ApiExample.java) in our [example package](src/main/java/io/everitoken/sdk/java/example/) for detailed code examples.

## Action usage

An **Action** in an instruction to perform a given task on everiToken public chain. In order to send **Action**, the workflow is to:

1. construct the given action locally
2. instantiate an `TransactionService` to push the action (or actions) to the chain

> Refer to [example package](src/main/java/io/everitoken/sdk/java/example/) for more code examples of each **Action**.

<details>
<summary>Here is the code example showing how to create a domain on everiToken public chain</summary>

```java
// instantiate net parameter, can also be main net
final NetParams netParam = new TestNetNetParams();

// specify the content of the action
final String actionData = "...";
final JSONObject json = new JSONObject(actionData);

// use json data to build the NewDomainAction, alternatively you can also build with other constructs, check NewDomainAction class for more details
final NewDomainAction newDomainAction = NewDomainAction.ofRaw(json.getString("name"), json.getString("creator"),
        json.getJSONObject("issue"), json.getJSONObject("transfer"), json.getJSONObject("manage"));

try {
    // init *TransactionService* with a net parameter
    TransactionService transactionService = TransactionService.of(netParam);

    // construct *TransactionConfiguration*
    TransactionConfiguration trxConfig = new TransactionConfiguration(1000000,
            PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"),
            KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D"));

    // push the action to chain. Note: you can also pass multiple actions here
    TransactionData txData = transactionService.push(trxConfig, Arrays.asList(newDomainAction));

    // get the transaction data
    System.out.println(txData.getTrxId());
} catch (final ApiResponseException ex) {
    System.out.println(ex.getRaw());
}

```

</details>

## EvtLink usage

## EvtLink

### EvtLink generation

`EvtLink` is the place to generate and parse QR Codes using `EVT Link`'s syntax. `EVT Link` can be used for `everiPass`, `everiPay`, `Address Code for Receiver`.

For further information, read [Documentation for EvtLink / everiPass / everiPay](https://www.everitoken.io/developers/deep_dive/evtlink,_everipay,_everipass).

**static** `getEvtLinkForEveriPass`

Generate `EvtLink` for everiPass.

<details>
<summary>Click to see code example</summary>

```java

NetParams netParams = new TestNetNetParams();

// Init new EvtLink instance with given net param
EvtLink evtLink = new EvtLink(netParams);

// make sure the domain and token you use exist and has correct authorize keys
EvtLink.EveriPassParam everiPassParam = new EvtLink.EveriPassParam(true, "domainName", "tokenName");

String passText = evtLink.getEvtLinkForEveriPass(everiPassParam,
        SignProvider.of(KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D")));

// will print out evt link
System.out.println(passText);

```

</details>

**static** `getEvtLinkForEveriPay`

**static** `getEvtLinkForPayeeCode`

## Deploy

- setup user `settings.xml` for maven, where username and password should be specified
- obtain the gpg key and password
- run `mvn clean javadoc:jar deploy` which will deploy a **stage** version
- run `mvn nexus-staging:release`

> use `master mvn clean compile assembly:single` to build with all dependencies
