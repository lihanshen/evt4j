# evt4j

- [evt4j](#evt4j)
  - [Install](#install)
    - [use with Maven project](#use-with-maven-project)
    - [use with Gradle project](#use-with-gradle-project)
    - [other](#other)
  - [Usage overview](#usage-overview)
  - [PrivateKey usage](#privatekey-usage)
  - [Api usage overview](#api-usage-overview)

Official Java SDK for everiToken public chain.

## Install

### use with Maven project

### use with Gradle project

### other

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

## Api usage overview

By instantiate an `Api` instance, you will be able to use it to interact with the specified remote node.

Please refer to [ApiExample.java](src/main/java/io/everitoken/sdk/java/example/ApiExample.java) in our [example package](src/main/java/io/everitoken/sdk/java/example/) for detailed code examples.
