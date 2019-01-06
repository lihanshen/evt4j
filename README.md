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

## ApiParams
* PublicKeysParam
* ActionsParam
* TokenParam
* BalanceParam
* IdParam // NameParam

## Apis
* [x] getInfo
* [x] getHeadBlockHeaderState
* [x] getCreatedDomain
* [x] getOwnedTokens
* [x] getManagedGroups
* [x] getCreatedFungibles
* [ ] getActions
* [ ] getToken
* [ ] getFungibleBalance
* [ ] getTransactionDetailById
* [ ] getDomainDetail
* [ ] getGroupDetail
* [ ] getFungibleActionsByAddress
* [ ] getTransactionsDetailOfPublicKeys
* [ ] getFungibleSymbolDetail
* [ ] getRequiredKeysForSuspendedTransaction
* [ ] getStatusOfEvtLink
* [ ] getSuspendedTransactionDetail
* [ ] getEstimatedChargeForTransaction
* [ ] generateUnsignedTransaction
* [ ] pushTransaction


## Misc

PrivateKey: `5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D` 

PubilcKey: `EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND`

