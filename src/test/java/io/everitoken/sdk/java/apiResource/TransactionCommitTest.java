package io.everitoken.sdk.java.apiResource;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.RequestParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TransactionCommitTest {

    @Test
    @Disabled
    void placeholder() {
        String sampleRes = "{\"transaction_id\":\"39fa265d41bd3b2ba1c0295fcdb95431b6ddfaa241c4a90970beaf66b4f0716b\"," +
                "\"processed\":{\"elapsed\":290,\"charge\":125,\"receipt\":{\"type\":\"input\"," +
                "\"status\":\"executed\"},\"except\":null," +
                "\"id\":\"39fa265d41bd3b2ba1c0295fcdb95431b6ddfaa241c4a90970beaf66b4f0716b\"," +
                "\"action_traces\":[{\"elapsed\":63," +
                "\"trx_id\":\"39fa265d41bd3b2ba1c0295fcdb95431b6ddfaa241c4a90970beaf66b4f0716b\"," +
                "\"block_time\":\"2019-01-28T21:10:39.000\",\"console\":\"\",\"block_num\":6780536," +
                "\"producer_block_id\":null," +
                "\"act\":{\"hex_data" +
                "\":\"4710f541def7681843650c06290000000002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c2000000008052e74c0100000001010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c20100000000b298e982a40100000001000001000000000094135c680100000001010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c20100\",\"data\":{\"creator\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"transfer\":{\"name\":\"transfer\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[G] .OWNER\",\"weight\":1}]},\"issue\":{\"name\":\"issue\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":1}]},\"name\":\"feitestdomainame8\",\"manage\":{\"name\":\"manage\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":1}]}},\"domain\":\"feitestdomainame8\",\"name\":\"newdomain\",\"key\":\".create\"},\"receipt\":{\"act_digest\":\"47c7c962c9a8dceecdd35ff98baa97f801b4e49a48620b17d11738f3a6ca87e8\",\"global_sequence\":2110},\"except\":null},{\"elapsed\":22,\"trx_id\":\"39fa265d41bd3b2ba1c0295fcdb95431b6ddfaa241c4a90970beaf66b4f0716b\",\"block_time\":\"2019-01-28T21:10:39.000\",\"console\":\"\",\"block_num\":6780536,\"producer_block_id\":null,\"act\":{\"hex_data\":\"010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c27d000000\",\"data\":{\"charge\":125,\"payer\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"},\"domain\":\".charge\",\"name\":\"paycharge\",\"key\":\".public-key\"},\"receipt\":{\"act_digest\":\"b30932ff4a639609b582de1c79a46b6d03924b9395914c92f0af9d7e3cca58c3\",\"global_sequence\":2111},\"except\":null}],\"net_usage\":335,\"is_suspend\":false}}";

        String data = "{\"transaction\":{\"actions\":[{\"name\":\"newdomain\"," +
                "\"data" +
                "\":\"4710f541def7681843650c06290000000002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c2000000008052e74c0100000001010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c20100000000b298e982a40100000001000001000000000094135c680100000001010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c20100\",\"domain\":\"feitestdomainame8\",\"key\":\".create\"}],\"expiration\":\"2019-01-28T21:26:45\",\"ref_block_num\":30260,\"ref_block_prefix\":172496955,\"max_charge\":1000000,\"payer\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"},\"compression\":\"none\",\"signatures\":[\"SIG_K1_K1UZUC4t9j96LT31VMkEabnzTVPYTdTqh5L1b8LXLsAuT6vLzHAcr5NniVfP686BwsaCKzutiwRN5BvDvS514zfF2BSobk\"]}";
        TransactionCommit transactionCommit = new TransactionCommit();
        NetParams netParams = new TestNetNetParams();
        RequestParams requestParams = RequestParams.of(netParams, () -> data);

        try {
            TransactionData res = transactionCommit.request(requestParams);
            System.out.println(JSON.toJSONString(res));
        } catch (ApiResponseException ex) {
            System.out.println(ex.getRaw());
        }
    }

}