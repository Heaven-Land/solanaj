package org.p2p.solanaj.core;

import org.junit.Test;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.CommitmentType;
import org.p2p.solanaj.rpc.types.TokenAccountsByOwner;

import static org.junit.Assert.assertEquals;

public class GetTransactionTest {

    @Test
    public void testGettingTransaction() {

        final RpcClient client = new RpcClient(Cluster.DEVNET);

        try {
            final var transaction = client.getApi().getTransaction("2Nfeq923n2Ejxxtkn2vdTLmhFemcgjni5xaj6VSJHL7eocn9ALUTJNtK4Z2jV1mFmCDaSRcJzoZNFnojkA3ZXBz2", CommitmentType.FINALIZED);
            var accountKeys = transaction.getTransaction().getMessage().getAccountKeys();
            var key = accountKeys.indexOf("9tHxTM2cxAwEEEqyhYpqdAekNeVG2cny3PMRVQdbAQbV");
            assertEquals(2, key);
            var preBalance = transaction.getMeta().getPreBalances().get(key);
            var postBalance = transaction.getMeta().getPostBalances().get(key);
            var amount = postBalance - preBalance;
            var amountInSol = amount / Math.pow(10, 9);
            assertEquals(500000000, amount);
            assertEquals(0.5, amountInSol, 0);
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }
}
