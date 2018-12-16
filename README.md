# evt4j
Official Java SDK for everiToken public chain.

## Api (working in progress)

### PrivateKey
* `toPublicKey`
* `toWif`
* **static** `randomPrivateKey`
* **static** `seedPrivateKey`
* **static** `fromWif`
* **static** `isValidPrivateKey`

### PublicKey
* **static** `isValidPublicKey`
* **static** `isValidAddress`
* **static** `getNullAddress`
* `toString`
* `getEncoded`

### Signature
* `getR`
* `getS`
* `isCanonical`
* **static** `sign`
* **static** `verify`
* **recover** `PublicKey`


## Test

Make sure maven is installed

`mvn test`
