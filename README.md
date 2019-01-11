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

## Apis
* [x] getInfo
* [x] getHeadBlockHeaderState
* [x] getCreatedDomain
* [x] getOwnedTokens
* [x] getManagedGroups
* [x] getCreatedFungibles
* [x] getActions
* [x] getTransactionIdsInBlock // needs testing
* [ ] getTokens
* [x] getToken
* [x] getFungibleBalance
* [x] getTransactionDetailById
* [x] getDomainDetail
* [x] getGroupDetail
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

PublicKey: `EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND`

Error: `{"code":500,"message":"Internal Service Error","error":{"code":3100003,"what":"unknown transaction","name":"unknown_transaction_exception","details":[{"file":"evt_pg_query.cpp","method":"get_transaction_resume","line_number":677,"message":"Cannot find transaction"}]}}`
