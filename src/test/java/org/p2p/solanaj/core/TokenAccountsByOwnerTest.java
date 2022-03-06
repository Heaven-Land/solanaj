package org.p2p.solanaj.core;

import org.junit.Test;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.AccountInfo;
import org.p2p.solanaj.rpc.types.TokenAccountsByOwner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TokenAccountsByOwnerTest {

    @Test
    public void testTokenAccountsOwnerExisting() {

        final RpcClient client = new RpcClient(Cluster.DEVNET);
        final PublicKey owner = new PublicKey("HLdFXNAFds7jzfxoGQKTYdDcxmYU3xFZjk9zEpdVE4tq");
        final PublicKey mint = new PublicKey("G2HNM5sAELALAwMqGi4FcA4tEo3APYi5pvzcTY2Btcm2");

        try {
            // Get account Info
            final TokenAccountsByOwner tokenAccounts = client.getApi().getTokenAccountsByOwner(owner, mint, null);
            var value = tokenAccounts.getValue();
            assertEquals(1, value.size());
            assertEquals("Cf5ai5BLtxZZCCcxgQ65o2cAKXA7RQXj37ZVHmaYTPBg", value.get(0).getPubkey().toString());
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTokenAccountsOwnerNonExisting() {

        final RpcClient client = new RpcClient(Cluster.DEVNET);
        final PublicKey owner = new PublicKey("HLdFXNAFds7jzfxoGQKTYdDcxmYU3xFZjk9zEpdVE4tq");
        final PublicKey mint = new PublicKey("HthJxcM5Q1rfvAXurQP5jxGEz11kSoNQXKfYJdzeRCF6");

        try {
            // Get account Info
            final TokenAccountsByOwner tokenAccounts = client.getApi().getTokenAccountsByOwner(owner, mint, null);
            var value = tokenAccounts.getValue();
            assertEquals(0, value.size());
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }



}
