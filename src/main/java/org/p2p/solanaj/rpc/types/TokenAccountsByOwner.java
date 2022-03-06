package org.p2p.solanaj.rpc.types;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Map;

public class TokenAccountsByOwner extends RpcResultObject {

    public static class Value {
        @Json(name = "pubkey")
        private String pubkey;

        @Json(name = "account")
        private Account account;


        public String getPubkey() {
            return pubkey;
        }

        public Account getAccount() {
            return account;
        }
    }

    public static class Account {

        @Json(name = "lamports")
        private long lamports;

        @Json(name = "owner")
        private String owner;

        @Json(name = "data")
        private Map<String, Object> data = null;

        @Json(name = "executable")
        private boolean executable;

        @Json(name = "rentEpoch")
        private long rentEpoch;

        public Map<String, Object> getData() {
            return data;
        }

        public boolean isExecutable() {
            return executable;
        }

        public long getLamports() {
            return lamports;
        }

        public String getOwner() {
            return owner;
        }

        public long getRentEpoch() {
            return rentEpoch;
        }
    }

    @Json(name = "value")
    private List<Value> value;

    public List<Value> getValue() {
        return value;
    }

}
